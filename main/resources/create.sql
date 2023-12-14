CREATE DATABASE gmo;
USE gmo;

CREATE TABLE `user`(
                       `user_id` INT PRIMARY KEY AUTO_INCREMENT,
                       `user_name` VARCHAR(20) NOT NULL,
                       `password`  VARCHAR(15) NOT NULL
);

CREATE TABLE `student`(
                          `student_id` INT PRIMARY KEY AUTO_INCREMENT,
                          `student_name`varchar(20) NOT NULL,
                          `student_code` varchar(10) NOT NULL
);

CREATE TABLE `student_info`(
                               `info_id` INT AUTO_INCREMENT,
                               `student_id` INT,
                               `address` VARCHAR(255),
                               `average_score` DOUBLE,
                               `date_of_birth` DATETIME,
                               PRIMARY KEY (`info_id`,`student_id`),
                               FOREIGN KEY (`student_id`) REFERENCES `student`(`student_id`) ON DELETE CASCADE
);
