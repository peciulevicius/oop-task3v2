package com.example.demojavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Student {
    private SimpleStringProperty studentName;
    private SimpleStringProperty studentSurname;
    private double studentAverage;
    private ObservableList<Grades> gradesList = FXCollections.observableArrayList();

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        //GOT ERRORS WHILE THIS WAS NOT = TO NEW SimpleStringProperty
        //and table was empty till Property() was empty
        this.studentName = new SimpleStringProperty(studentName);
    }

    public String getStudentSurname() {
        return studentSurname.get();
    }

    public void setStudentSurname(String studentSurname) {
        //GOT ERRORS WHILE THIS WAS NOT = TO NEW SimpleStringProperty
        //and table was empty till Property() was empty
        this.studentSurname = new SimpleStringProperty(studentSurname);
    }

    public double getStudentAverage() {
        return studentAverage;
    }

    public void setStudentAverage(double studentAverage) {
        this.studentAverage = studentAverage;
    }

    public ObservableList<Grades> getGradesList() {
        return gradesList;
    }

    public void setGradesList(ObservableList<Grades> gradesList) {
        this.gradesList = gradesList;
    }

    public double getGradeAverage(){
        return studentAverage;
    }

    public void studentAverageCalculation() {
        double creditSum = 0;
        double sum = 0;
        for (int i = 0; i < gradesList.size(); i ++){
            sum += gradesList.get(i).getGrade()*gradesList.get(i).subject.getSubjectCredits();
            creditSum += gradesList.get(i).subject.getSubjectCredits();
        }
        studentAverage = sum/creditSum;
    }

}
