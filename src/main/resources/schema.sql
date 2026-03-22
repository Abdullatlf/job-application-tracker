CREATE DATABASE job_tracker;

USE job_tracker;

CREATE TABLE IF NOT EXISTS `APPLICATION`(

    `applicationID` INTEGER NOT NULL AUTO_INCREMENT,
    `companyName` VARCHAR(100) NOT NULL,
    `role` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NULL,
    `submissionDate` DATE NOT NULL,
    `nextActionDate` DATE NULL,
    `action` VARCHAR(100) NULL,
    `notes` VARCHAR(300) NULL,
    `status` ENUM('ACCEPTED','REJECTED','INTERVIEWED','APPLIED') NOT NULL,
    PRIMARY KEY (applicationID)


)