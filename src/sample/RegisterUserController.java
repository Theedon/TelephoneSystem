package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javax.xml.crypto.Data;
import java.net.URL;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterUserController implements Initializable {
    Stage stage;
    Contacts contacts;


    @FXML
    private TextField idName;

    @FXML
    private TextField idEmail;

    @FXML
    private TextField idPhone;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private RadioButton Male;

    @FXML
    private RadioButton Female;

    ToggleGroup group;

    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        Male.setToggleGroup(group);
        Female.setToggleGroup(group);

        if (true) {
            btnUpdate.setVisible(false);
            btnDelete.setVisible(false);
        }


    }


    public void onClickAdd(ActionEvent event) {


        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String table_name = "phone_table";
        String name = idName.getText();
        String email = idEmail.getText();
        String gender = null;
        String phone = idPhone.getText();

        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();


        if (name.isEmpty() || email.isEmpty() || selectedRadioButton == null || phone.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please fill out the fields");
            alert.show();
        }
        else {
            String toggleGroupValue = selectedRadioButton.getText();

            if (toggleGroupValue.equals("Male")) {
                gender = "M";
            } else if (toggleGroupValue.equals("Female")) {
                gender = "F";
            }


            String query = "INSERT INTO " + table_name + "(name, email, gender, phone)" +
                    "VALUES('" + name + "', '" + email + "', '" + gender + "', '" + phone + "')";

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                //System.out.println("done!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.show();
                alert.setContentText("successful!");
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void onClickUpdate(ActionEvent event){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String table_name = "phone_table";
        String name = idName.getText();
        String email = idEmail.getText();
        String gender = null;
        String phone = idPhone.getText();

        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();


        if (name.isEmpty() || email.isEmpty() || selectedRadioButton == null || phone.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please fill out the fields");
            alert.show();
        }
        else {
            String toggleGroupValue = selectedRadioButton.getText();

            if (toggleGroupValue.equals("Male")) {
                gender = "M";
            } else if (toggleGroupValue.equals("Female")) {
                gender = "F";
            }


            String query = "UPDATE phone_table SET name = '"+name+"', email = '"+email+
                    "', gender = '"+gender+"', phone = '"+phone+"' WHERE id = "+contacts.getId()+"";

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                //System.out.println("done!");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.show();
                alert.setContentText("successful!");
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void onClickDelete(ActionEvent event){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();



        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();

            idName.setEditable(false);
            idEmail.setEditable(false);
            idPhone.setEditable(false);
            String query = "DELETE FROM phone_table WHERE id = '"+contacts.getId()+"'";

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                //System.out.println("done!");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.show();
                alert.setContentText("user "+contacts.getName()+" deleted succesfully");
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

    }


    public void sendData(Contacts contactReceived, String intent) {



        if (intent.equals("update")) {

            contacts = contactReceived;
            idName.setText(contacts.getName());
            idEmail.setText(contacts.getEmail());

            if (contacts.getGender().equals("M")) {

                group.selectToggle(Male);
            } else if (contacts.getGender().equals("F")) {
                group.selectToggle(Female);
            }

            idPhone.setText(contacts.getPhone());

            btnUpdate.setVisible(true);
            btnAdd.setVisible(false);
            btnDelete.setVisible(false);


        }

        if(intent.equals("delete")){
            contacts = contactReceived;
            idName.setText(contacts.getName());
            idEmail.setText(contacts.getEmail());

            if (contacts.getGender().equals("M")) {

                group.selectToggle(Male);
            } else if (contacts.getGender().equals("F")) {
                group.selectToggle(Female);
            }

            idPhone.setText(contacts.getPhone());

            btnUpdate.setVisible(false);
            btnAdd.setVisible(false);
            btnDelete.setVisible(true);

        }


    }








}
