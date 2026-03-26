package com.jobtracker.ui;

import com.jobtracker.model.JobApplication;
import com.jobtracker.model.Status;
import com.jobtracker.service.JobApplicationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class HomeController {

    @FXML
    private VBox applicationList;

    @FXML
    private VBox detailPanel;

    private HBox operation;



    private void loadApplications(){
        List<JobApplication> listOfApplications = JobApplicationService.listOfJobApplication();
        applicationList.getChildren().clear();
        Label title = new Label("Applications List");
        title.setPadding(new Insets(10, 15, 10, 15));
        title.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        title.setStyle("-fx-font-weight: bold;");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setPrefWidth(200);

        applicationList.getChildren().add(title);
        for(JobApplication jobApplication : listOfApplications){
            HBox card = createCard(jobApplication);
            applicationList.getChildren().add(card);
        }
    }
    private void loadUrgents(){
        List<JobApplication> listOfUrgents = JobApplicationService.listOfUrgent();
        applicationList.getChildren().clear();
        Label title = new Label("Urgent List");
        title.setPadding(new Insets(10, 15, 10, 15));
        title.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        title.setStyle("-fx-font-weight: bold;");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setPrefWidth(200);
        applicationList.getChildren().add(title);
        for(JobApplication jobApplication : listOfUrgents){
            HBox card = createCard(jobApplication);
            applicationList.getChildren().add(card);
        }


    }
    private void loadFinished(){
        List<JobApplication> listOfFinished = JobApplicationService.listOfFinished();
        applicationList.getChildren().clear();
        Label title = new Label("Finished List");
        title.setPadding(new Insets(10, 15, 10, 15));
        title.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        title.setStyle("-fx-font-weight: bold;");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setPrefWidth(200);
        applicationList.getChildren().add(title);
        for(JobApplication jobApplication : listOfFinished){
            HBox card = createCard(jobApplication);
            applicationList.getChildren().add(card);
        }

    }
    @FXML
    public void initialize() {
        loadApplications();
    }

    public HBox createCard(JobApplication jobApplication){
        HBox card = new HBox(20);
        card.setPadding(new Insets(10, 15, 10, 15));
        card.setPrefWidth(Double.MAX_VALUE);

        Label companyName = new Label(jobApplication.getCompanyName());
        companyName.setPrefWidth(150);
        companyName.setStyle("-fx-font-weight: bold;");

        Label role = new Label(jobApplication.getRole());
        role.setPrefWidth(150);

        Label status = new Label(jobApplication.getStatus().name());
        status.setPrefWidth(120);

        card.getChildren().addAll(companyName, role, status);
        card.setOnMouseClicked(event -> showDetail(jobApplication));
        card.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        card.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Mouse entered - apply a visual effect
                card.setEffect(new DropShadow(5, Color.DARKGRAY));
            } else {
                // Mouse exited - remove the effect
                card.setEffect(null);
            }
        });
        return card;
    }
    private void showDetail(JobApplication jobApplication){
        detailPanel.getChildren().clear();




        TextField companyNameTextField = new TextField();
        companyNameTextField.setPromptText(jobApplication.getCompanyName());
        companyNameTextField.focusVisibleProperty();
        Label companyNameLabel = new Label("Company Name");
        HBox companyHbox = new HBox(20);
        companyHbox.getChildren().addAll(companyNameLabel,companyNameTextField);
        companyHbox.setPadding(new Insets(10, 15, 10, 15));
        companyHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        companyHbox.setPrefWidth(Double.MAX_VALUE);

        TextField roleTextField = new TextField();
        roleTextField.setPromptText(jobApplication.getRole());
        roleTextField.focusVisibleProperty();
        Label roleLabel = new Label("Role");
        HBox roleHbox = new HBox(20);
        roleHbox.getChildren().addAll(roleLabel,roleTextField);
        roleHbox.setPadding(new Insets(10, 15, 10, 15));
        roleHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        roleHbox.setPrefWidth(Double.MAX_VALUE);


        ComboBox<Status> statusComboBox = loadStatusToComboBox(jobApplication);
        Label statusLabel = new Label("Status");
        HBox statusHbox = new HBox(20);
        statusHbox.getChildren().addAll(statusLabel,statusComboBox);
        statusHbox.setPadding(new Insets(10, 15, 10, 15));
        statusHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        statusHbox.setPrefWidth(Double.MAX_VALUE);

        TextField submissionDateTextField = new TextField(jobApplication.getSubmissionDate().toString());
        Label submissionDateLabel = new Label("Submission Date");
        HBox submissionDateHbox = new HBox(20);
        submissionDateHbox.getChildren().addAll(submissionDateLabel,submissionDateTextField);
        submissionDateHbox.setPadding(new Insets(10, 15, 10, 15));
        submissionDateHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        submissionDateHbox.setPrefWidth(Double.MAX_VALUE);

        TextField actionTextField = new TextField();
        actionTextField.setPromptText(jobApplication.getAction() != null ? jobApplication.getAction() : "N/A");
        actionTextField.focusVisibleProperty();
        Label actionLabel = new Label("Action");
        HBox actionHbox = new HBox(20);
        actionHbox.getChildren().addAll(actionLabel,actionTextField);
        actionHbox.setPadding(new Insets(10, 15, 10, 15));
        actionHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        actionHbox.setPrefWidth(Double.MAX_VALUE);

        TextField noteTextField = new TextField();
        noteTextField.setPromptText(jobApplication.getNote() != null ? jobApplication.getNote() : "N/A");
        noteTextField.focusVisibleProperty();
        Label notelabel = new Label("Note");
        HBox noteHbox = new HBox(20);
        noteHbox.getChildren().addAll(notelabel,noteTextField);
        noteHbox.setPadding(new Insets(10, 15, 10, 15));
        noteHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        noteHbox.setPrefWidth(Double.MAX_VALUE);

        Label applicationValue = new Label(String.valueOf(jobApplication.getApplicationId()));
        Label applicationIdLabel = new Label("Application ID");
        HBox applicationIdHbox = new HBox(20);
        applicationIdHbox.getChildren().addAll(applicationIdLabel,applicationValue);
        applicationIdHbox.setPadding(new Insets(10, 15, 10, 15));
        applicationIdHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        applicationIdHbox.setPrefWidth(Double.MAX_VALUE);

        TextField emailTextField = new TextField();
        emailTextField.setPromptText(jobApplication.getEmail() != null ? jobApplication.getEmail() : "N/A");
        emailTextField.focusVisibleProperty();
        Label emailLabel = new Label("Email");
        HBox emailHbox = new HBox(20);
        emailHbox.getChildren().addAll(emailLabel,emailTextField);
        emailHbox.setPadding(new Insets(10, 15, 10, 15));
        emailHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        emailHbox.setPrefWidth(Double.MAX_VALUE);

        TextField nextActionDateTextField = new TextField(jobApplication.getNextActionDate() != null ? jobApplication.getNextActionDate().toString() : "N/A");
        Label nextActionDateLabel = new Label("Next Action Date");
        HBox nextActionDateHbox = new HBox(20);
        nextActionDateHbox.getChildren().addAll(nextActionDateLabel, nextActionDateTextField);
        nextActionDateHbox.setPadding(new Insets(10, 15, 10, 15));
        nextActionDateHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        nextActionDateHbox.setPrefWidth(Double.MAX_VALUE);

        Button updateBtn = new Button("Update");
        updateBtn.setPadding(new Insets(10, 15, 10, 15));
        updateBtn.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        updateBtn.setPrefWidth(Double.MAX_VALUE);
        updateBtn.setOnAction(event -> {

            jobApplication.setCompanyName(companyNameTextField.getText().isEmpty() ?jobApplication.getCompanyName() : companyNameTextField.getText());
            jobApplication.setRole(roleTextField.getText().isEmpty() ?jobApplication.getRole() : roleTextField.getText());
            jobApplication.setEmail(emailTextField.getText());
            jobApplication.setStatus(statusComboBox.getValue());
            jobApplication.setAction(actionTextField.getText());
            jobApplication.setNote(noteTextField.getText());
            jobApplication.setSubmissionDate(
                    submissionDateTextField.getText().isEmpty()
                            ? jobApplication.getSubmissionDate()
                            : LocalDate.parse(submissionDateTextField.getText())
            );
            jobApplication.setNextActionDate(
                    nextActionDateTextField.getText().equals("N/A") || nextActionDateTextField.getText().isEmpty()
                            ? null
                            : LocalDate.parse(nextActionDateTextField.getText())
            );            JobApplicationService.updateJobApplication(jobApplication);
            loadApplications();
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setPadding(new Insets(10, 15, 10, 15));
        deleteBtn.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        deleteBtn.setPrefWidth(Double.MAX_VALUE);
        deleteBtn.setOnAction(event -> {
            if(confirmation()) {
                jobApplication.setCompanyName(companyNameTextField.getText().isEmpty() ? jobApplication.getCompanyName() : companyNameTextField.getText());
                jobApplication.setRole(roleTextField.getText().isEmpty() ? jobApplication.getRole() : roleTextField.getText());
                jobApplication.setEmail(emailTextField.getText());
                jobApplication.setStatus(statusComboBox.getValue());
                jobApplication.setAction(actionTextField.getText());
                jobApplication.setNote(noteTextField.getText());
                jobApplication.setSubmissionDate(submissionDateTextField.getText().isEmpty()
                        ? jobApplication.getSubmissionDate()
                        : LocalDate.parse(submissionDateTextField.getText())
                );
                jobApplication.setNextActionDate(
                        nextActionDateTextField.getText().equals("N/A") || nextActionDateTextField.getText().isEmpty()
                                ? null
                                : LocalDate.parse(nextActionDateTextField.getText())
                );
                JobApplicationService.deleteJobApplication(jobApplication);
                loadApplications();
            }

        });



        detailPanel.getChildren().addAll(applicationIdHbox,companyHbox,roleHbox,emailHbox,statusHbox,submissionDateHbox,nextActionDateHbox
        ,actionHbox,noteHbox, updateBtn, deleteBtn);

    }

    private ComboBox<Status> loadStatusToComboBox(JobApplication jobApplication){
        ComboBox<Status> statusComboBox = new ComboBox<>();
        List<Status> statusList = Arrays.stream(Status.values()).toList();
        ObservableList<Status> observableList = FXCollections.observableArrayList(statusList);
        statusComboBox.setItems(observableList);
        statusComboBox.getSelectionModel().select(jobApplication.getStatus());
        return statusComboBox;
    }
    private ComboBox<Status> loadStatusToComboBox1( ){
        ComboBox<Status> statusComboBox = new ComboBox<>();
        List<Status> statusList = Arrays.stream(Status.values()).toList();
        ObservableList<Status> observableList = FXCollections.observableArrayList(statusList);
        statusComboBox.setItems(observableList);
        return statusComboBox;
    }

    public void homeBtn(ActionEvent actionEvent){
        detailPanel.getChildren().clear();
        applicationList.getChildren().clear();
        loadApplications();
    }
    public void urgentBtn(ActionEvent actionEvent){
        detailPanel.getChildren().clear();
        applicationList.getChildren().clear();
        loadUrgents();
    }
    public void applicationBtn(ActionEvent actionEvent){
        detailPanel.getChildren().clear();
        applicationList.getChildren().clear();
        loadApplications();
    }
    public void finishedBtn(ActionEvent actionEvent){
        detailPanel.getChildren().clear();
        applicationList.getChildren().clear();
        loadFinished();
    }
    public void exitBtn(ActionEvent actionEvent){
        System.exit(0);
    }

    public void createBtn(ActionEvent actionEvent){
        detailPanel.getChildren().clear();

        TextField companyNameTextField = new TextField();
        Label companyNameLabel = new Label("Company Name");
        HBox companyHbox = new HBox(20);
        companyHbox.getChildren().addAll(companyNameLabel,companyNameTextField);
        companyHbox.setPadding(new Insets(10, 15, 10, 15));
        companyHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        companyHbox.setPrefWidth(Double.MAX_VALUE);

        TextField roleTextField = new TextField();
        Label roleLabel = new Label("Role");
        HBox roleHbox = new HBox(20);
        roleHbox.getChildren().addAll(roleLabel,roleTextField);
        roleHbox.setPadding(new Insets(10, 15, 10, 15));
        roleHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        roleHbox.setPrefWidth(Double.MAX_VALUE);


        ComboBox<Status> statusComboBox = loadStatusToComboBox1();
        Label statusLabel = new Label("Status");
        HBox statusHbox = new HBox(20);
        statusHbox.getChildren().addAll(statusLabel,statusComboBox);
        statusHbox.setPadding(new Insets(10, 15, 10, 15));
        statusHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        statusHbox.setPrefWidth(Double.MAX_VALUE);

        TextField submissionDateTextField = new TextField();
        submissionDateTextField.setPromptText("YYYY-MM-DD");
        submissionDateTextField.focusVisibleProperty();
        Label submissionDateLabel = new Label("Submission Date");
        HBox submissionDateHbox = new HBox(20);
        submissionDateHbox.getChildren().addAll(submissionDateLabel,submissionDateTextField);
        submissionDateHbox.setPadding(new Insets(10, 15, 10, 15));
        submissionDateHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        submissionDateHbox.setPrefWidth(Double.MAX_VALUE);

        TextField actionTextField = new TextField();
        Label actionLabel = new Label("Action");
        HBox actionHbox = new HBox(20);
        actionHbox.getChildren().addAll(actionLabel,actionTextField);
        actionHbox.setPadding(new Insets(10, 15, 10, 15));
        actionHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        actionHbox.setPrefWidth(Double.MAX_VALUE);

        TextField noteTextField = new TextField();
        Label notelabel = new Label("Note");
        HBox noteHbox = new HBox(20);
        noteHbox.getChildren().addAll(notelabel,noteTextField);
        noteHbox.setPadding(new Insets(10, 15, 10, 15));
        noteHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        noteHbox.setPrefWidth(Double.MAX_VALUE);

        Label applicationValue = new Label("Auto");
        Label applicationIdLabel = new Label("Application ID");
        HBox applicationIdHbox = new HBox(20);
        applicationIdHbox.getChildren().addAll(applicationIdLabel,applicationValue);
        applicationIdHbox.setPadding(new Insets(10, 15, 10, 15));
        applicationIdHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        applicationIdHbox.setPrefWidth(Double.MAX_VALUE);

        TextField emailTextField = new TextField();
        Label emailLabel = new Label("Email");
        HBox emailHbox = new HBox(20);
        emailHbox.getChildren().addAll(emailLabel,emailTextField);
        emailHbox.setPadding(new Insets(10, 15, 10, 15));
        emailHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        emailHbox.setPrefWidth(Double.MAX_VALUE);

        TextField nextActionDateTextField = new TextField();
        nextActionDateTextField.setPromptText("YYYY-MM-DD");
        nextActionDateTextField.focusVisibleProperty();
        Label nextActionDateLabel = new Label("Next Action Date");
        HBox nextActionDateHbox = new HBox(20);
        nextActionDateHbox.getChildren().addAll(nextActionDateLabel, nextActionDateTextField);
        nextActionDateHbox.setPadding(new Insets(10, 15, 10, 15));
        nextActionDateHbox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        nextActionDateHbox.setPrefWidth(Double.MAX_VALUE);

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setPadding(new Insets(10, 15, 10, 15));
        confirmBtn.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0; -fx-cursor: hand;");
        confirmBtn.setPrefWidth(Double.MAX_VALUE);
        confirmBtn.setOnAction(event -> {
            JobApplication jobApplication = new JobApplication(companyNameTextField.getText()
                    ,roleTextField.getText()
                    ,statusComboBox.getValue()
                    ,LocalDate.parse(submissionDateTextField.getText())
            );
            jobApplication.setEmail(emailTextField.getText());
            jobApplication.setStatus(statusComboBox.getValue());
            jobApplication.setAction(actionTextField.getText());
            jobApplication.setNote(noteTextField.getText());
            jobApplication.setNextActionDate(
                    nextActionDateTextField.getText().equals("N/A") || nextActionDateTextField.getText().isEmpty()
                            ? null
                            : LocalDate.parse(nextActionDateTextField.getText())
            );            JobApplicationService.createJobApplication(jobApplication);
            loadApplications();

        });



        detailPanel.getChildren().addAll(applicationIdHbox,companyHbox,roleHbox,emailHbox,statusHbox,submissionDateHbox,nextActionDateHbox
                ,actionHbox,noteHbox, confirmBtn);



    }
    public boolean confirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Action Required: Application Deletion");
        alert.setContentText("Are you sure you want to delete this application?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            return true;
        }else {
            return false;
        }
    }



}