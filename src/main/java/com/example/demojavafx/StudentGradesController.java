package com.example.demojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentGradesController {

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
    @FXML private TextField subjectGrade1;
    @FXML private TextField subjectGrade2;
    @FXML private TextField subjectGrade3;
    @FXML private TextField subjectGrade4;
    @FXML private TextField subjectGrade5;
    @FXML private TextField subjectGrade6;
    @FXML private Button addGradesButton; //= new Button()??
    @FXML private Button closeButton;

    @FXML
    void initialize() {

    }

    //student from the table
    Student student;
    //group for subject list
    Group group;


    public void initData(Student selectedStudent, Group selectedGroup) {
        //references to observable lists
        student = selectedStudent;
        group = selectedGroup;

        //setting student full name display
        studentNameDisplay.setText(student.getStudentName() + " " + student.getStudentSurname());
        //setting student group display
        groupNameDisplay.setText(group.getGroupName());
        //setting student group semester display
        semesterDisplay.setText(String.valueOf(group.getSemester()));

        //setting student current subjects
        setSubjectLabel(subjectNameOut1, 0);
        setSubjectLabel(subjectNameOut2, 1);
        setSubjectLabel(subjectNameOut3, 2);
        setSubjectLabel(subjectNameOut4, 3);
        setSubjectLabel(subjectNameOut5, 4);
        setSubjectLabel(subjectNameOut6, 5);

        //setting student grades for each subject
        subjectGrade1.setText(String.valueOf(selectedStudent.getGradesList().get(0).getGrade()));
        subjectGrade2.setText(String.valueOf(selectedStudent.getGradesList().get(1).getGrade()));
        subjectGrade3.setText(String.valueOf(selectedStudent.getGradesList().get(2).getGrade()));
        subjectGrade4.setText(String.valueOf(selectedStudent.getGradesList().get(3).getGrade()));
        subjectGrade5.setText(String.valueOf(selectedStudent.getGradesList().get(4).getGrade()));
        subjectGrade6.setText(String.valueOf(selectedStudent.getGradesList().get(5).getGrade()));
    }

    private void setSubjectLabel(Label subjectLabel, int subjectArrNumber){
        subjectLabel.setText(group.subjectList.get(subjectArrNumber).getSubjectName());
    }

    @FXML
    void addGradesAction(ActionEvent event) {
        //setting grades entered
        setGrade(subjectGrade1, 0);
        setGrade(subjectGrade2, 1);
        setGrade(subjectGrade3, 2);
        setGrade(subjectGrade4, 3);
        setGrade(subjectGrade5, 4);
        setGrade(subjectGrade6, 5);

        //now with set grade values we calculate student average
        student.studentAverageCalculation();

        Stage window = (Stage) addGradesButton.getScene().getWindow();
        window.close();
    }

    //assigns a new grade to a specific subject and stores the grade information in student class
    private void setGrade(TextField gradeField, int gradeArrNumber){
        student.getGradesList().get(gradeArrNumber).subject = group.subjectList.get(gradeArrNumber);
        student.getGradesList().get(gradeArrNumber).setGrade(Double.parseDouble(gradeField.getText()));
    }

    @FXML
    void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
