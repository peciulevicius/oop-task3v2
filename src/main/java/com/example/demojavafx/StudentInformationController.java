package com.example.demojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentInformationController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Label studentNameDisplay;
    @FXML private Label groupNameDisplay;
    @FXML private Label semesterDisplay;
    @FXML private Label subjectNameOut1;
    @FXML private Label subjectNameOut2;
    @FXML private Label subjectNameOut3;
    @FXML private Label subjectNameOut4;
    @FXML private Label subjectNameOut5;
    @FXML private Label subjectNameOut6;
    @FXML private Label subjectGradeOut1;
    @FXML private Label subjectGradeOut2;
    @FXML private Label subjectGradeOut3;
    @FXML private Label subjectGradeOut4;
    @FXML private Label subjectGradeOut5;
    @FXML private Label subjectGradeOut6;
    @FXML private Label subjectCreditsOut1;
    @FXML private Label subjectCreditsOut2;
    @FXML private Label subjectCreditsOut3;
    @FXML private Label subjectCreditsOut4;
    @FXML private Label subjectCreditsOut5;
    @FXML private Label subjectCreditsOut6;
    @FXML private Label studentAverageOutput;
    @FXML private Button closeButton;

    Group selectedGroup;
    Student selectedStudent;

    public void initData(Student student, Group group) {

        selectedStudent = student;
        selectedGroup = group;

        studentNameDisplay.setText(selectedStudent.getStudentName() + " " + selectedStudent.getStudentSurname());

        groupNameDisplay.setText(this.selectedGroup.getGroupName());
        semesterDisplay.setText(String.valueOf(this.selectedGroup.getSemester()));

        setSubjectLabel(subjectNameOut1, 0);
        setSubjectLabel(subjectNameOut2, 1);
        setSubjectLabel(subjectNameOut3, 2);
        setSubjectLabel(subjectNameOut4, 3);
        setSubjectLabel(subjectNameOut5, 4);
        setSubjectLabel(subjectNameOut6, 5);

        setGradeLabel(subjectGradeOut1, 0);
        setGradeLabel(subjectGradeOut2, 1);
        setGradeLabel(subjectGradeOut3, 2);
        setGradeLabel(subjectGradeOut4, 3);
        setGradeLabel(subjectGradeOut5, 4);
        setGradeLabel(subjectGradeOut6, 5);

        setCreditLabel(subjectCreditsOut1, 0);
        setCreditLabel(subjectCreditsOut2, 1);
        setCreditLabel(subjectCreditsOut3, 2);
        setCreditLabel(subjectCreditsOut4, 3);
        setCreditLabel(subjectCreditsOut5, 4);
        setCreditLabel(subjectCreditsOut6, 5);

        selectedStudent.studentAverageCalculation();
        studentAverageOutput.setText(String.valueOf(selectedStudent.getGradeAverage()));
    }

    private void setSubjectLabel(Label subjectLabel, int subjectArrNumber){
        subjectLabel.setText(selectedGroup.subjectList.get(subjectArrNumber).getSubjectName());
        //subjectLabel.setText(selectedStudent.getGradesList().get(subjectArrNumber).subject.getSubjectName());
    }

    private void setGradeLabel(Label gradeLabel, int subjectArrNumber){
        gradeLabel.setText(String.valueOf(selectedStudent.getGradesList().get(subjectArrNumber).getGrade()));
    }

    private void setCreditLabel(Label creditLabel, int subjectArrNumber){
        creditLabel.setText(String.valueOf(selectedGroup.subjectList.get(subjectArrNumber).getSubjectCredits()));
    }

    @FXML
    void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}