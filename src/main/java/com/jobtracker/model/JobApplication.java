package com.jobtracker.model;

import java.time.LocalDate;

public class JobApplication {

    private int applicationId;
    private String companyName;
    private String role;
    private String email;
    private Status status;
    private LocalDate submissionDate;
    private LocalDate nextActionDate;
    private String action;
    private String note;

    public JobApplication(String companyName, String role, Status status,LocalDate submissionDate){
        this.companyName = companyName;
        this.role = role;
        this.status = status;
        this.submissionDate = submissionDate;
    }

    //Setters

    public void setApplicationId(int applicationId){
        this.applicationId = applicationId;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void setRole(String role){
        this.role = role;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setStatus(Status status){
        this.status = status;
    }
    public void setSubmissionDate(LocalDate submissionDate){
        this.submissionDate = submissionDate;
    }
    public void setNextActionDate(LocalDate nextActionDate){
        this.nextActionDate = nextActionDate;
    }
    public void setAction(String action){
        this.action = action;
    }
    public void setNote(String note){
        this.note = note;
    }

    //Getters
    public int getApplicationId(){
        return applicationId;
    }
    public String getCompanyName(){
        return companyName;
    }
    public String getRole(){
        return role;
    }
    public Status getStatus(){
        return status;
    }
    public LocalDate getSubmissionDate(){
        return submissionDate;
    }
    public LocalDate getNextActionDate(){
        return nextActionDate;
    }
    public String getEmail(){
        return email;
    }
    public String getAction(){
        return action;
    }
    public String getNote(){
        return note;
    }




}