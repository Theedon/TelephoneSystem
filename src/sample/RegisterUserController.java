package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;



import java.net.URL;
import java.sql.Connection;

import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterUserController implements Initializable {
    Stage stage;


    @FXML
    private TextField idName;

    @FXML
    private TextField idEmail;

    @FXML
    private TextField idGender;

    @FXML
    private TextField idPhone;

    @FXML
    private RadioButton Male;

    @FXML
    private RadioButton Female;

    ToggleGroup group;

    public void initialize(URL location, ResourceBundle resources){
        group= new ToggleGroup();
        Male.setToggleGroup(group);
        Female.setToggleGroup(group);


    }





    public void onClickSubmit(ActionEvent event){
        DatabaseConnection databaseConnection= new DatabaseConnection();
        Connection connection= databaseConnection.getConnection();

        String table_name= "phone_table";
        String name= idName.getText();
        String email= idEmail.getText();
        String gender = null;
        String phone= idPhone.getText();

        RadioButton selectedRadioButton= (RadioButton) group.getSelectedToggle();
        String toggleGroupValue= selectedRadioButton.getText();

        if(toggleGroupValue.equals("Male")){
            gender= "M";
        }
        else if(toggleGroupValue.equals("Female")){
            gender= "F";
        }







        String query= "INSERT INTO "+table_name+"(name, email, gender, phone)" +
                "VALUES('"+name+"', '"+email+"', '"+gender+"', '"+phone+"')";

        try{
            Statement statement= connection.createStatement();
            statement.executeUpdate(query);
            //System.out.println("done!");
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.show();
            alert.setContentText("successful!");
            stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
