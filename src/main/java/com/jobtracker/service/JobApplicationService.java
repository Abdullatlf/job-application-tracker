package com.jobtracker.service;

import com.jobtracker.dao.JobApplicationDAO;
import com.jobtracker.model.JobApplication;

import java.util.List;

public class JobApplicationService {

    public boolean createJobApplication(JobApplication jobApplication){
        if(!hasRequiredFields(jobApplication))
            return false;
        if(JobApplicationDAO.existsByCompanyNameAndRole(jobApplication))
            return false;
        JobApplicationDAO.createJobApplication(jobApplication);
        return true;
    }
    public boolean deleteJobApplication(JobApplication jobApplication){
        if(!JobApplicationDAO.existsById(jobApplication))
            return false;
        JobApplicationDAO.deleteJobApplication(jobApplication.getApplicationId());
        return true;
    }
    public static boolean updateJobApplication(JobApplication jobApplication){
        if (!hasRequiredFields(jobApplication))
            return false;
        if(!JobApplicationDAO.existsById(jobApplication))
            return false;
        JobApplicationDAO.updateJobApplication(jobApplication);
        return true;

    }
    public boolean updateJobApplicationStatus(JobApplication jobApplication){
        if (jobApplication.getStatus() == null)
            return false;
        if(!JobApplicationDAO.existsById(jobApplication))
            return false;
        JobApplicationDAO.updateStatus(jobApplication.getApplicationId(), jobApplication.getStatus());
        return true;

    }
    public static List<JobApplication> listOfJobApplication(){
        return JobApplicationDAO.listOfJobApplication();
    }
    public static List<JobApplication> listOfUrgent(){
        return JobApplicationDAO.listOfUrgent();
    }
    public static List<JobApplication> listOfFinished(){
        return JobApplicationDAO.listOfFinished();
    }

    private static boolean hasRequiredFields(JobApplication jobApplication){
        if(jobApplication.getCompanyName() == null
        || jobApplication.getRole() == null
        || jobApplication.getSubmissionDate() == null
        || jobApplication.getStatus() == null){
            return false;
        }
        if(jobApplication.getCompanyName().isEmpty()
                || jobApplication.getRole().isEmpty())
            return false;
        return true;
    }




}