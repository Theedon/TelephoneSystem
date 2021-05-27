package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterUserController {

    @FXML
    private TextField idName;

    @FXML
    private TextField idEmail;

    @FXML
    private TextField idGender;

    @FXML
    private TextField idPhone;

    Stage stage;

    public void onClickSubmit(javafx.event.ActionEvent event){
        DatabaseConnection databaseConnection= new DatabaseConnection();
        Connection connection= databaseConnection.getConnection();

        String table_name= "phone_table";
        String name= idName.getText();
        String email= idEmail.getText();
        String gender= idGender.getText();
        String phone= idPhone.getText();


        String query= "INSERT INTO "+table_name+"(name, email, gender, phone)" +
                "VALUES('"+name+"', '"+email+"', '"+gender+"', '"+phone+"')";

        try{
            Statement statement= connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("done!");
            stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
