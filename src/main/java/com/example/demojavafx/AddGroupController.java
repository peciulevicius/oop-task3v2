package com.example.demojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGroupController {

    private Group group;

    @FXML private Label groupNameDisplay = new Label();
    @FXML private TextField semesterInput;
    @FXML private TextField subjectName1;
    @FXML private TextField subjectName2;
    @FXML private TextField subjectName3;
    @FXML private TextField subjectName4;
    @FXML private TextField subjectName5;
    @FXML private TextField subjectName6;
    @FXML private TextField subjectCredits1;
    @FXML private TextField subjectCredits2;
    @FXML private TextField subjectCredits3;
    @FXML private TextField subjectCredits4;
    @FXML private TextField subjectCredits5;
    @FXML private TextField subjectCredits6;
    @FXML private Button closeButton;


    public void setGroupName(Group group){
        this.group = group;
        groupNameDisplay.setText(this.group.getGroupName());
    }

    @FXML
    void addGroupAction(ActionEvent event) {
        try {
            group.setSemester(Integer.parseInt(semesterInput.getText()));

            group.subjectList.add(new Subject(subjectName1.getText()));
            group.subjectList.add(new Subject(subjectName2.getText()));
            group.subjectList.add(new Subject(subjectName3.getText()));
            group.subjectList.add(new Subject(subjectName4.getText()));
            group.subjectList.add(new Subject(subjectName5.getText()));
            group.subjectList.add(new Subject(subjectName6.getText()));

            group.subjectList.get(0).setSubjectCredits(Integer.parseInt(subjectCredits1.getText()));
            group.subjectList.get(1).setSubjectCredits(Integer.parseInt(subjectCredits2.getText()));
            group.subjectList.get(2).setSubjectCredits(Integer.parseInt(subjectCredits3.getText()));
            group.subjectList.get(3).setSubjectCredits(Integer.parseInt(subjectCredits4.getText()));
            group.subjectList.get(4).setSubjectCredits(Integer.parseInt(subjectCredits5.getText()));
            group.subjectList.get(5).setSubjectCredits(Integer.parseInt(subjectCredits6.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GROUP HAS BEEN ADDED");
            alert.setHeaderText(null);
            alert.setContentText("New group has been successfully added!");
            alert.showAndWait();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("CAN'T ADD A GROUP");
            alert.setContentText("Check input! Either not fully filled in or wrong information");
            alert.showAndWait();
        }
    }

    @FXML
    void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
