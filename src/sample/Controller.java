package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentNavigableMap;


public class Controller implements Initializable {

    @FXML
    private TableView<Contacts> tvPhoneTable;

    @FXML
    TableColumn<Contacts, Integer> colId;

    @FXML
    TableColumn<Contacts, String> colName;

    @FXML
    TableColumn<Contacts, String> colEmail;

    @FXML
    TableColumn<Contacts, String> colGender;

    @FXML
    TableColumn<Contacts, String> colPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showContacts();
    }


    public void onClickButton(ActionEvent event)throws Exception{
        Parent root2= FXMLLoader.load(getClass().getResource("RegisterUser.fxml"));
        Scene secondScene= new Scene(root2, 500, 500);
        Stage secondStage= new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("This has worked succesfully");
        secondStage.show();

    }

    public ObservableList<Contacts> getContactList(){
        ObservableList<Contacts> contactList= FXCollections.observableArrayList();
        DatabaseConnection databaseConnection= new DatabaseConnection();
        Connection connection= databaseConnection.getConnection();
        String query= "SELECT * from phone_table";

        Statement statement;
        ResultSet resultSet;

        try{
            statement= connection.createStatement();
            resultSet= statement.executeQuery(query);
            Contacts contact;

            while(resultSet.next()){
                contact= new Contacts(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getString("phone")
                        );
                contactList.add(contact);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return contactList;

    }

    public void showContacts(){
        ObservableList<Contacts> list= getContactList();

        colId.setCellValueFactory(new PropertyValueFactory<Contacts, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Contacts, String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Contacts, String>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<Contacts, String>("gender"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Contacts, String>("phone"));

        tvPhoneTable.setItems(list);

    }



}
