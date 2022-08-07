package sample.progect3temp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeeLikesOfPostController implements Initializable {

    @FXML
    Button likesBackButton;
    @FXML
    ListView<String > likesList;
    @FXML
    Label likeLabel;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    String currentLike;
    //ArrayList<String > seePostsArray;
    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            try {
               seePostsController.seeMyOwnPosts(PersonalHomePageController.username);
                ArrayList <String> likers =  seePostsController.seeLikes(String.valueOf (PersonalHomePageController.currentPost),
                        PersonalHomePageController.username);
                likesList.getItems ().addAll (likers);
                likesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                    @Override
                    public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                        currentLike = likesList.getSelectionModel().getSelectedItem();
                        likeLabel.setText(currentLike);

                    }
                });
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }

        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            try {
                seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
                ArrayList <String> likers =  seePostsController.seeLikes(String.valueOf (BusinessHomepageController.currentPost),
                        BusinessHomepageController.username);
                likesList.getItems ().addAll (likers);
                likesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                    @Override
                    public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                        currentLike = likesList.getSelectionModel().getSelectedItem();
                        likeLabel.setText(currentLike);

                    }
                });
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }

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
}
