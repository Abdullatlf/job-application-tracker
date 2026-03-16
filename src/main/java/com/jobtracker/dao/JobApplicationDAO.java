package com.jobtracker.dao;

import com.jobtracker.model.JobApplication;
import com.jobtracker.model.Status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO{

    public static boolean createJobApplication(JobApplication jobApplication){
        String query = "INSERT INTO APPLICATION (companyName, role, email, submissionDate, nextActionDate, action, notes, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = DatabaseConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, jobApplication.getCompanyName());
            preparedStatement.setString(2, jobApplication.getRole());
            preparedStatement.setString(3, jobApplication.getEmail());
            preparedStatement.setObject(4, jobApplication.getSubmissionDate());
            preparedStatement.setObject(5, jobApplication.getNextActionDate());
            preparedStatement.setString(6, jobApplication.getAction());
            preparedStatement.setString(7, jobApplication.getNote());
            preparedStatement.setString(8, jobApplication.getStatus().name());
            int row = preparedStatement.executeUpdate();
            if(row == 1){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()){
                    jobApplication.setApplicationId(generatedKeys.getInt(1));
                }
            }
            return row == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteJobApplication(int applicationId){
        String query = "DELETE FROM APPLICATION WHERE applicationID = ?";
        try(Connection connection = DatabaseConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setInt(1, applicationId);
            int row = preparedStatement.executeUpdate();
            return row == 1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean updateJobApplication(JobApplication jobApplication){
        String query = "UPDATE APPLICATION \n" +
                "SET companyName=?, role=?, email=?, submissionDate=?, nextActionDate=?, action=?, notes=?, status=? \n" +
                "WHERE applicationID=?";
        try(Connection connection = DatabaseConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, jobApplication.getCompanyName());
            preparedStatement.setString(2, jobApplication.getRole());
            preparedStatement.setString(3, jobApplication.getEmail());
            preparedStatement.setObject(4, jobApplication.getSubmissionDate());
            preparedStatement.setObject(5, jobApplication.getNextActionDate());
            preparedStatement.setString(6, jobApplication.getAction());
            preparedStatement.setString(7, jobApplication.getNote());
            preparedStatement.setString(8, jobApplication.getStatus().name());
            preparedStatement.setInt(9, jobApplication.getApplicationId());
            int row = preparedStatement.executeUpdate();
            return row == 1;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean updateStatus(int applicationId, Status status){
        String query = "UPDATE APPLICATION SET status = ? WHERE applicationID = ?";
        try(Connection connection = DatabaseConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, applicationId);
            int row = preparedStatement.executeUpdate();
            return row == 1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static List<JobApplication> listOfJobApplication(){
        String query = "SELECT * FROM APPLICATION";
        List<JobApplication> listOfJobApplication = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();){
            while(resultSet.next()){
                JobApplication jobApplication = new JobApplication(resultSet.getString("companyName").trim(),
                        resultSet.getString("role").trim(),
                        Status.valueOf(resultSet.getString("status")),
                        resultSet.getObject("submissionDate", LocalDate.class));
                jobApplication.setApplicationId(resultSet.getInt("applicationID"));
                jobApplication.setEmail(resultSet.getString("email"));
                jobApplication.setNextActionDate(resultSet.getObject("nextActionDate", LocalDate.class));
                jobApplication.setAction(resultSet.getString("action"));
                jobApplication.setNote(resultSet.getString("notes"));
                listOfJobApplication.add(jobApplication);
            }
            return listOfJobApplication;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<JobApplication> listOfUrgent(){
        String query = "SELECT * FROM APPLICATION WHERE nextActionDate <= CURDATE() + INTERVAL 3 DAY AND status NOT IN ('ACCEPTED','REJECTED')";
        List<JobApplication> listOfUrgent = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();){
            while (resultSet.next()){
                JobApplication jobApplication = new JobApplication(resultSet.getString("companyName").trim(),
                        resultSet.getString("role").trim(),
                        Status.valueOf(resultSet.getString("status")),
                        resultSet.getObject("submissionDate", LocalDate.class));
                jobApplication.setApplicationId(resultSet.getInt("applicationID"));
                jobApplication.setEmail(resultSet.getString("email"));
                jobApplication.setNextActionDate(resultSet.getObject("nextActionDate", LocalDate.class));
                jobApplication.setAction(resultSet.getString("action"));
                jobApplication.setNote(resultSet.getString("notes"));
                listOfUrgent.add(jobApplication);
            }
            return listOfUrgent;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static List<JobApplication> listOfFinished(){
        String query = "SELECT * FROM APPLICATION WHERE status = 'ACCEPTED' OR status = 'REJECTED'";
        List<JobApplication> listOfFinished = new ArrayList<>();
        try(Connection connection = DatabaseConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();){
            while(resultSet.next()){
                JobApplication jobApplication = new JobApplication(resultSet.getString("companyName").trim(),
                        resultSet.getString("role").trim(),
                        Status.valueOf(resultSet.getString("status")),
                        resultSet.getObject("submissionDate", LocalDate.class));
                jobApplication.setApplicationId(resultSet.getInt("applicationID"));
                jobApplication.setEmail(resultSet.getString("email"));
                jobApplication.setNextActionDate(resultSet.getObject("nextActionDate", LocalDate.class));
                jobApplication.setAction(resultSet.getString("action"));
                jobApplication.setNote(resultSet.getString("notes"));
                listOfFinished.add(jobApplication);
            }
            return listOfFinished;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static boolean existsById(JobApplication jobApplication){
        String query = "SELECT COUNT(*) FROM APPLICATION WHERE applicationID = ?";
        try(Connection connection = DatabaseConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, jobApplication.getApplicationId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1)>0;
            }
            return false;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static boolean existsByCompanyNameAndRole(JobApplication jobApplication){
        String query = "SELECT * FROM APPLICATION WHERE companyName = ? AND role = ?";
        try(Connection connection = DatabaseConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, jobApplication.getCompanyName());
            preparedStatement.setString(2, jobApplication.getRole());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }






}