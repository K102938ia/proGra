package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuggestUsers implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label recomError;

    @FXML
    private TextField recomResult;

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        RecommendController recommendController = new RecommendController ();
        recomError.setText("");
        recomResult.setText("");
        String recommendation = "";
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            recommendation=recommendController.findFofUrFollowers(PersonalHomePageController.username);
        }
        if(BusinessHomepageController.type.equalsIgnoreCase ("Business")){
            recommendation=recommendController.findFofUrFollowers(BusinessHomepageController.username);
        }

        if (recommendation.equals("    "))
        {
            recomError.setText("there is no recommendation for you. At least one follower or following is needed !");
        }

        else
        {
            recomResult.setText(recommendation);

        }
    }

    @FXML
    void back( ActionEvent event) throws IOException {
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
}
