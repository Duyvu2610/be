package com.gmo.gmo.services;

import com.gmo.gmo.dto.StudentDTO;
import com.gmo.gmo.dto.TransferDTO;
import com.gmo.gmo.entities.StudentInfo;
import com.gmo.gmo.repository.StudentInfoRepository;
import com.gmo.gmo.repository.StudentRepository;
import com.gmo.gmo.entities.Student;
import com.gmo.gmo.utils.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository repository;
    private StudentInfoRepository studentInfoRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, StudentInfoRepository studentInfoRepository) {
        this.repository = repository;
        this.studentInfoRepository = studentInfoRepository;
    }

    @Override
    public Student save(Student student) {
        if (repository.findByStudentName(student.getStudentName()) == null) return repository.save(student);
        return null;
    }

    @Override
    public StudentDTO findById(Integer id) {
        Student st = repository.findById(id).orElse(null);
        StudentDTO stDTO = TransferDTO.toStDTO(studentInfoRepository.findByStudent(st));
        return stDTO;
    }

    @Override
    @Transactional
    public StudentDTO delete(Integer id) {
        StudentDTO st = findById(id);
        if (st!= null) {
            repository.deleteById(id);
            return st;
        }
        return null;

    }

    @Override
    public Student findByName(String userName) {
        return repository.findByStudentName(userName);
    }

    @Override
    public Student update(Student user) {
        return null;
    }

    @Override
    @Transactional
    public StudentDTO update(int id, StudentDTO stu) {
        Student st = repository.findById(id).orElse(null);
        StudentInfo stInfo = studentInfoRepository.findByStudent(st);
        if (stInfo == null || st == null) return null;
        if (id != stu.getStudentId()) return null;


        st.setStudentName(stu.getStudentName());
        stInfo.setDateOfBirth(stu.getDateOfBirth());
        stInfo.setAddress(stu.getAddress());
        stInfo.setAverageScore(stu.getAverageScore());

        studentInfoRepository.save(stInfo);
        repository.save(st);

        return stu;
    }

    @Override
    public String generateCode() {
        boolean isDuplicated;
        StringBuffer res = new StringBuffer("STU");
        do{
            int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            Random random = new Random();
            for (int i = 0; i < 7; i++) {
                int randomNumber = random.nextInt(arr.length);
                res.append(arr[randomNumber]);
            }
            isDuplicated = repository.findByStudentCode(res.toString()) != null;
            if (isDuplicated) res = new StringBuffer("STU");
        }while (isDuplicated);

        return res.toString();
    }

    @Override
    public Page<StudentDTO> find(String stCode, String stName, Date dob, int page, int size, String sortBy, String sortOrder) {
        if (dob != null){
            if (dob.after(new Date())) return null;
        }
        Pageable pageable = null;
        // Check if sortOrder is provided
        if (!sortBy.equals("")) {
            Sort sort = Sort.by(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }
        // get filter
        Specification<StudentInfo> spec = filter(stCode, stName, dob);


        // Lấy danh sách StudentInfo từ cơ sở dữ liệu
        Page<StudentInfo> studentInfoPage = studentInfoRepository.findAll(spec, pageable);

        // Chuyển đổi danh sách StudentInfo thành danh sách StudentDTO
        List<StudentDTO> studentDTOList = studentInfoPage.getContent().stream()
                .map(TransferDTO::toStDTO)
                .collect(Collectors.toList());

        // Tạo Page<StudentDTO> từ danh sách StudentDTO và Pageable
        return new PageImpl<>(studentDTOList, pageable, studentInfoPage.getTotalElements());
    }

    @Override
    @Transactional
    public StudentDTO save(StudentDTO studentDTO) {
        if (repository.findByStudentCode(studentDTO.getStudentCode()) != null) return null;
        Student student = TransferDTO.toStudent(studentDTO);
        student = repository.save(student);

        StudentInfo studentInfo = TransferDTO.toStudentInfo(studentDTO);

        studentInfo.getStudent().setStudentId(student.getStudentId());
        studentDTO.setStudentId(student.getStudentId());

        studentInfo = studentInfoRepository.save(studentInfo);
        return studentDTO;
    }

    private Specification<StudentInfo> filter(String stCode, String stName, Date dob) {
        Specification<StudentInfo> spec = Specification.where(null);
        if (!stCode.isEmpty() && !stName.isEmpty() && dob != null) {
            spec = spec.and(StudentSpecification.withAll(stCode, stName, dob));
        }
        if (!stCode.isEmpty()) {
            if (!stName.isEmpty()) {
                spec = spec.and(StudentSpecification.withCodeAndName(stCode, stName));
            } else if (dob != null) {
                spec = spec.and(StudentSpecification.withCodeAndDob(stCode, dob));
            } else {
                spec = spec.and(StudentSpecification.withCode(stCode));
            }


        }
        if (stName != null && !stName.isEmpty()) {
            if (dob != null) spec = spec.and(StudentSpecification.withNameAndDob(stName, dob));
            else spec = spec.and(StudentSpecification.withName(stName));

        }
        if (dob != null) {
            spec = spec.and(StudentSpecification.withDob(dob));
        }
        return spec;
    }

}
