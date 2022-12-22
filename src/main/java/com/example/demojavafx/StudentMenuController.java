package com.example.demojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentMenuController {

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> surnameColumn;
    @FXML private TextField studentNameInput;
    @FXML private TextField studentSurnameInput;
    @FXML private Label groupNameDisplay;
    @FXML private Label semesterDisplay;
    @FXML private Label subjectNameOut1;
    @FXML private Label subjectNameOut2;
    @FXML private Label subjectNameOut3;
    @FXML private Label subjectNameOut4;
    @FXML private Label subjectNameOut5;
    @FXML private Label subjectNameOut6;
    @FXML private Label groupAverageOutput;
    @FXML private Button closeButton;

    //reference to selected group from main window
    Group selectedGroup;
    Student selectedStudent;

    public void initData(Group group) {
        //set selected group reference
        selectedGroup = group;

        //add group info to the table
        //set group average info
        if(selectedGroup.studentList != null) {
            studentTable.setItems(selectedGroup.studentList);
            selectedGroup.groupAverageCalculation();
            groupAverageOutput.setText(String.valueOf(selectedGroup.getGroupAverage()));
        }

        //student group display
        groupNameDisplay.setText(group.getGroupName());
        //student group semester display
        semesterDisplay.setText(String.valueOf(group.getSemester()));


        //student subjects
        setSubjectLabel(subjectNameOut1, 0);
        setSubjectLabel(subjectNameOut2, 1);
        setSubjectLabel(subjectNameOut3, 2);
        setSubjectLabel(subjectNameOut4, 3);
        setSubjectLabel(subjectNameOut5, 4);
        setSubjectLabel(subjectNameOut6, 5);

        //setGradeLabel(subjectGradeOut1, 0);
        //setGradeLabel(subjectGradeOut2, 1);
        //setGradeLabel(subjectGradeOut3, 2);
        //setGradeLabel(subjectGradeOut4, 3);
        //setGradeLabel(subjectGradeOut5, 4);
        //setGradeLabel(subjectGradeOut6, 5);
    }

    private void setSubjectLabel(Label subjectLabel, int subjectArrNumber){
        subjectLabel.setText(selectedGroup.subjectList.get(subjectArrNumber).getSubjectName());
    }

    @FXML
    void addStudentAction(ActionEvent event) {
        if (studentNameInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("EMPTY STUDENT NAME FIELD");
            alert.setContentText("CHECK and FILL student name field!");
            alert.showAndWait();
        } if (studentSurnameInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("EMPTY STUDENT SURNAME FIELD");
            alert.setContentText("CHECK and FILL student surname field!");
            alert.showAndWait();
        } else {
            if (selectedGroup.studentList.size() < 30) {
                Student student = new Student();
                student.setStudentName(studentNameInput.getText());
                student.setStudentSurname(studentSurnameInput.getText());

                for (int i = 0; i< selectedGroup.subjectList.size(); i++){
                    Grades grades = new Grades();
                    //GRADES LIST BELOW DOES NOT WORK PROPERLY, AND I DUNNO WHY yet
                    //CANT I DO THIS WITHOUT A GETTER? Doesnt even work if attribute is public
                    student.getGradesList().add(grades);
                }

                studentTable.setItems(selectedGroup.studentList);
                selectedGroup.studentList.add(student);

                //set columns
                nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
                surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentSurname"));

                studentNameInput.clear();
                studentSurnameInput.clear();

                System.out.println(selectedGroup.studentList);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("LIST IS FULL");
                alert.setContentText("You can only have maximum of 30 students per group!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void deleteStudentAction(ActionEvent event) {
        try {
            Student student = studentTable.getSelectionModel().getSelectedItem();
            if (student!=null) {
                selectedGroup.studentList.remove(student);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("LIST EMPTY");
                alert.setContentText("Your list is empty. There is nothing to remove!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error/List is empty");
        }
    }

    @FXML
    void addStudentGradesAction(ActionEvent event) {
        try {
            //Tried try catch but today is not the day
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentGradesWindow.fxml"));
            Parent studentGrades = (Parent) fxmlLoader.load();
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            //set controller
            StudentGradesController studentGradesController = fxmlLoader.getController();
            //load the selected data into this window
            studentGradesController.initData(studentTable.getSelectionModel().getSelectedItem(), selectedGroup);

            window.setTitle("Add grades");
            window.setScene(new Scene(studentGrades));
            window.setResizable(false);
            window.show();

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("CAN'T LOAD");
            alert.setContentText("The window you have tried opening, could not be opened!");
            alert.showAndWait();
        }
    }

    /*
    //method to set studentInformationController
    StudentInformationController studentInformationController;
    private void setStudentInformationController(FXMLLoader loader){
        studentInformationController = loader.getController();
    }
*/
    //when pushed, shows selected students information from the table and his average
    @FXML
    void showStudentInformationAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentInformationWindow.fxml"));
            Parent studentInformation = (Parent) fxmlLoader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            //setting controller
            //setStudentInformationController(fxmlLoader);
            StudentInformationController studentInformationController = fxmlLoader.getController();
            //loading controller data into a window
            studentInformationController.initData(studentTable.getSelectionModel().getSelectedItem(), selectedGroup);

            window.setTitle("Student information");
            window.setScene(new Scene(studentInformation));
            window.setResizable(false);
            window.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("CAN'T LOAD");
            alert.setContentText("The window you have tried opening, could not be opened!");
            alert.showAndWait();
        }
    }

    @FXML
    void refreshWindowAction(ActionEvent event) {
        selectedGroup.groupAverageCalculation();
        groupAverageOutput.setText(String.valueOf(selectedGroup.getGroupAverage()));
    }
    @FXML
    void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
