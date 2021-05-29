package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;



public class Controller implements Initializable {

    Contacts contactsToBeSent;

    String intent= "";
    //this String stores the value for which button was clicked on sample.fxml

    @FXML
    private TableView<Contacts> tvPhoneTable;

    @FXML
    private TableColumn<Contacts, Integer> colId;

    @FXML
    private TableColumn<Contacts, String> colName;

    @FXML
    private TableColumn<Contacts, String> colEmail;

    @FXML
    private TableColumn<Contacts, String> colGender;

    @FXML
    private TableColumn<Contacts, String> colPhone;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showContacts();
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

    @FXML
    private void handleMouseAction(MouseEvent event){
        contactsToBeSent= tvPhoneTable.getSelectionModel().getSelectedItem();
    }



    public void onClickAdd(ActionEvent event)throws Exception{

        intent= "add";

        Parent root2= FXMLLoader.load(getClass().getResource("RegisterUser.fxml"));
        Scene secondScene= new Scene(root2, 500, 500);
        Stage secondStage= new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("Add a new user");
        secondStage.show();
    }

    @FXML
    private void onClickDelete(ActionEvent event) throws IOException {

        intent= "delete";

        FXMLLoader loader= new FXMLLoader(getClass().getResource("RegisterUser.fxml"));
        try {
            Parent root= (Parent) loader.load();
            RegisterUserController registerUserController= loader.getController();

            if(contactsToBeSent==null){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Select a row to be deleted first");
                alert.show();
            }
            else {
                registerUserController.sendData(contactsToBeSent, intent);
                Stage stage= new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Delete an existing user");
                stage.show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    private void onClickUpdate(ActionEvent event){

        intent= "update";

        FXMLLoader loader= new FXMLLoader(getClass().getResource("RegisterUser.fxml"));

        try{
            Parent root= (Parent) loader.load();

            RegisterUserController registerUserController= loader.getController();

            if(contactsToBeSent==null){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Select a row to be updated first");
                alert.show();
            }
            else {
                registerUserController.sendData(contactsToBeSent, intent);
                Stage stage= new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Update an existing user");
                stage.show();
            }

        }
        catch (IOException e){
           e.printStackTrace();
        }


    }



}
