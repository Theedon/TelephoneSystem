package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller {

    public void onClickButton(ActionEvent event)throws Exception{
        Parent root2= FXMLLoader.load(getClass().getResource("RegisterUser.fxml"));
        Scene secondScene= new Scene(root2, 500, 500);
        Stage secondStage= new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle("This has worked succesfully");
        secondStage.show();
    }
}
