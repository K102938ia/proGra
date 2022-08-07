package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    static int currentPost = 0;

    @FXML
    private Button back;

    @FXML
    private Button backward;

    @FXML
    private Button forward;

    @FXML
    private ImageView image;

    @FXML
    private TextArea text;
    @FXML
    private ListView <String > usersList;
    @FXML
    private ListView<String> commentsList;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    MainMenuController mainMenuController = new MainMenuController ();
    ArrayList < Post > recentPosts = new ArrayList<> ();
    ArrayList < String  > postComments = new ArrayList<> ();
    ArrayList < String  > postCommentor = new ArrayList<> ();


    @FXML
    void goBack(ActionEvent event) throws IOException {
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

    @FXML
    void goBackward(ActionEvent event) {
        usersList.getItems ().clear ();
        commentsList.getItems ().clear ();
        int n = currentPost-1;
        if(n < recentPosts.size () && n >= 0){
            usersList.getItems ().clear ();
            commentsList.getItems ().clear ();
            postComments.clear ();
            postCommentor.clear ();
            String s = recentPosts.get (n).text;
            String[] str = s.split ("&");
            String txt = str[0];
            if(str.length > 1){
                String img = str[1];
                File file = new File(img);
                if (file.isAbsolute()){
                    Image recentImg = new Image (img);
                    image.setImage (recentImg);

                }
            }
            else {
                image.setImage (null);
            }
            text.setText (txt);
            for ( Comment comment : recentPosts.get (n).comments ) {
                postComments.add (comment.text);
                postCommentor.add (comment.commentorName);
            }
            commentsList.getItems ().addAll (postComments);
            usersList.getItems ().addAll (postCommentor);
            currentPost = n;
        }
        else if( n == -1){
            n = currentPost;
        }
    }

    @FXML
    void goForward( ActionEvent event) {
        usersList.getItems ().clear ();
        commentsList.getItems ().clear ();
        int n = currentPost +1;
        if(n < recentPosts.size () && n >= 0){
            usersList.getItems ().clear ();
            commentsList.getItems ().clear ();
            postComments.clear ();
            postCommentor.clear ();
            String s = recentPosts.get (n).text;
            String[] str = s.split ("&");
            String txt = str[0];
            if(str.length > 1){
                String img = str[1];
                File file = new File(img);
                if (file.isAbsolute()){
                    Image recentImg = new Image (img);
                    image.setImage (recentImg);

                }

            }
            else {
                image.setImage (null);
            }
            text.setText (txt);
            for ( Comment comment : recentPosts.get (n).comments ) {
                postComments.add (comment.text);
                postCommentor.add (comment.commentorName);
            }
            commentsList.getItems ().addAll (postComments);
            usersList.getItems ().addAll (postCommentor);
            currentPost = n;
        }
        else if( n == recentPosts.size ()){
            n = currentPost;
        }
    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        currentPost = 0;
        usersList.getItems ().clear ();
        commentsList.getItems ().clear ();
        postComments.clear ();
        postCommentor.clear ();
       //MainMenuController mainMenuController = new MainMenuController ();
//        ArrayList < Post > recentPosts = new ArrayList<> ();
//        ArrayList < String  > postComments = new ArrayList<> ();
//        ArrayList < String  > postCommentor = new ArrayList<> ();
       if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            recentPosts = mainMenuController.showRecent (PersonalHomePageController.username);
       }
        else if(BusinessHomepageController.type.equalsIgnoreCase ("Business")){
            recentPosts = mainMenuController.showRecent (BusinessHomepageController.username);
        }

       String s = recentPosts.get (0).text;
        String[] str = s.split ("&");
        String txt = str[0];
        if(str.length > 1){
            String img = str[1];
            File file = new File(img);
            if (file.isAbsolute()){
                Image recentImg = new Image (img);
                image.setImage (recentImg);

            }
        }

        text.setText (txt);
        currentPost = 0;
        for ( Comment comment : recentPosts.get (0).comments ) {
            postComments.add (comment.text);
            postCommentor.add (comment.commentorName);
        }
        commentsList.getItems ().addAll (postComments);
        usersList.getItems ().addAll (postCommentor);

    }
}
