package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstAdRecommendationController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button back;

    @FXML
    private Button confirm;

    @FXML
    private CheckBox followersAndFollowings;
    @FXML
    private Label noModeLabel;

    @FXML
    private CheckBox random;

    @FXML
    void confirmChoice(ActionEvent event) throws IOException {
        if(followersAndFollowings.isSelected ()){
            noModeLabel.setText ("");
            FAndFAd(event);
        }
        else if(random.isSelected ()){
            noModeLabel.setText ("");
            randomAd(event);
        }
        else if(random.isSelected () && followersAndFollowings.isSelected ()){
            noModeLabel.setText ("You cannot select both modes");
        }
        else {
            noModeLabel.setText ("No mode is selected");
        }
    }



    public void goBackToHomePage( ActionEvent event ) throws IOException {
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")) {
            root = FXMLLoader.load (getClass ().getResource ("PersonalHomePage.fxml"));
            stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            scene = new Scene (root);
            stage.setScene (scene);
            stage.show ();
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            root = FXMLLoader.load (getClass ().getResource ("BusinessHomePage.fxml"));
            stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            scene = new Scene (root);
            stage.setScene (scene);
            stage.show ();
        }

    }

    public void randomAd( ActionEvent event ) throws IOException {
            root = FXMLLoader.load (getClass ().getResource ("RandomAd.fxml"));
            stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            scene = new Scene (root);
            stage.setScene (scene);
            stage.show ();
    }

    public void FAndFAd( ActionEvent event ) throws IOException {
        root = FXMLLoader.load (getClass ().getResource ("FAndFAd.fxml"));
        stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        scene = new Scene (root);
        stage.setScene (scene);
        stage.show ();
    }
}
