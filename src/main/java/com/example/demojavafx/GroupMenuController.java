package com.example.demojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupMenuController {
    @FXML private TableColumn<Group, String> groupColumn;
    @FXML private TableView<Group> groupTable;
    @FXML private TextField groupNameInput;
    @FXML private Button addGroupButton;
    @FXML private Button seeSelectedGroupButton;
    @FXML private Button deleteGroupButton;
    @FXML private Button closeButton;
    @FXML private ResourceBundle resources;
    @FXML private URL location;

    ObservableList<Group> groupList = FXCollections.observableArrayList();


    //method to set addGroupController
    AddGroupController addGroupController;
    private void setAddGroupController(FXMLLoader loader){
        addGroupController = loader.getController();
    }

    //AddGroup
    @FXML
    void openAddGroupAction(ActionEvent event) {
        try {
            if (groupNameInput.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("EMPTY GROUP NAME FIELD");
                alert.setContentText("CHECK and FILL group name field!");
                alert.showAndWait();
            } else {
                //Parent root = FXMLLoader.load(getClass().getResource("AddGroupWindow.fxml"));
                //So basically if I want to show inputted info in a label, I need to redo fxml loader like this below
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddGroupWindow.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);

                Group group = new Group();
                group.setGroupName(groupNameInput.getText());

                groupList.add(group);

                groupColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("groupName"));
                //groupTable.setItems(groupList);
                groupTable.getItems().add(group);
                System.out.println(group);
                System.out.println(groupList);

                setAddGroupController(fxmlLoader);
                addGroupController.setGroupName(group);

                groupNameInput.clear();
                window.setTitle("Add Group");
                window.setScene(new Scene(root));
                window.setResizable(false);
                window.show();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("CAN'T LOAD A WINDOW");
            alert.setContentText("Window you are trying to open cannot be reached at the moment!");
            alert.showAndWait();
        }
    }

    //method to set studentMenuController
    StudentMenuController studentMenuController;
    private void setStudentMenuController(FXMLLoader loader){
        studentMenuController = loader.getController();
    }

    @FXML
    void seeSelectedGroupAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentMenuWindow.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            setStudentMenuController(fxmlLoader);
            studentMenuController.initData(groupTable.getSelectionModel().getSelectedItem());


            //window.setTitle(groupTable.getSelectionModel().getSelectedItem().getGroupName());
            window.setTitle("Student Menu");
            window.setScene(new Scene(root));
            window.setResizable(false);
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("CAN'T LOAD A WINDOW");
            alert.setContentText("Window you are trying to open cannot be reached at the moment!");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteGroupAction(ActionEvent event){
        try {
            Group group = groupTable.getSelectionModel().getSelectedItem();
            if (group!=null) {
                groupTable.getItems().remove(group);
                groupList.remove(group);
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
    void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
