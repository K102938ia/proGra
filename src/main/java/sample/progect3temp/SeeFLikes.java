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
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeeFLikes implements Initializable {

    @FXML
    Button likesBackButton;
    @FXML
    ListView <String > likesList;
    @FXML
    Label likeLabel;
    @FXML
    private Label feedBack;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    String currentLike;

    public void goBackToHomePage( ActionEvent event ) throws IOException {
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")) {
            root = FXMLLoader.load (getClass ().getResource ("SeePosts.fxml"));
            stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            scene = new Scene (root);
            stage.setScene (scene);
            stage.show ();
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            root = FXMLLoader.load (getClass ().getResource ("SeePosts.fxml"));
            stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            scene = new Scene (root);
            stage.setScene (scene);
            stage.show ();
        }

    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        likesList.getItems ().clear ();
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            try {
                //SeePostsF.seePostsController.seeMyOwnPosts(PersonalHomePageController.username);
                seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
                ArrayList <String> likers =  seePostsController.seeLikes(String.valueOf (SeePostsF.currentPost),PersonalHomePageController.currentFF);
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
                //SeePostsF.seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
                seePostsController.seePosts (BusinessHomepageController.username,BusinessHomepageController.currentFF);
                ArrayList <String> likers =  seePostsController.seeLikes(String.valueOf (SeePostsF.postNum),BusinessHomepageController.currentFF);
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

    @FXML
    void like(ActionEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
            boolean liked = false;
           liked =  seePostsController.likeAPost(PersonalHomePageController.username,PersonalHomePageController.currentFF
            , String.valueOf (SeePostsF.currentPost));
           if(liked){
               likesList.getItems ().add (PersonalHomePageController.username);
               feedBack.setText ("liked successfully");
           }
           else {
               feedBack.setText ("you have liked this post before");
           }

        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            boolean liked = false;
          liked =  seePostsController.likeAPost(BusinessHomepageController.username,BusinessHomepageController.currentFF
                    , String.valueOf (SeePostsF.currentPost));
            if(liked){
                likesList.getItems ().add (PersonalHomePageController.username);
                feedBack.setText ("liked successfully");
            }
            else {
                feedBack.setText ("you have liked this post before");
            }
        }
    }
}
