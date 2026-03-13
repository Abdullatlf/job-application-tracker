package com.jobtracker;

import com.jobtracker.dao.JobApplicationDAO;
import com.jobtracker.model.JobApplication;

import com.jobtracker.model.Status;

import java.time.LocalDate;
import java.util.List;

public class test{

    public static void main(String[] args) {


        List<JobApplication> l = JobApplicationDAO.listOfFinished();

        for(int i = 0 ; i < l.size();i++){

            System.out.println(l.get(i).toString());
        }


    }
}