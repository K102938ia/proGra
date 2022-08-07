package sample.progect3temp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;


public class SeePostsF implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label commentLabel;

    @FXML
    private Label followersLabel;

    @FXML
    private Label followingsLabel;

    @FXML
    private ImageView image;

    @FXML
    private Label likeLabel;
    @FXML
    private Label feedBackLabel;
    @FXML
    private Label name;



    @FXML
    private TextArea text;

    static int following = 0;
    static int follower = 0;
    static int currentPost = 0;
    static int postNum = 0;
    static SeePostsController seePostsController = new SeePostsController ();

    public ArrayList<String> postsTxt = new ArrayList<> ();
    public ArrayList<String> postsImage = new ArrayList<> ();


    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        following = 0;
         follower = 0;
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            name.setText (PersonalHomePageController.currentFF);
            File file = new File (PersonalHomePageController.currentFF + "follower.txt");
            try {
                Scanner scanner = new Scanner (file);
                while (scanner.hasNextLine ()){
                    String s = scanner.nextLine ();
                    follower++;
                }
                followersLabel.setText (String.valueOf (follower));
                File file1 =  new File (PersonalHomePageController.currentFF + "following.txt");
                Scanner scanner1 = new Scanner (file1);
                while (scanner1.hasNextLine ()){
                    try {
                        String s = scanner1.nextLine ();
                        following++;
                    }
                    catch ( NoSuchElementException e ){
                        break;
                    }
                }
                followingsLabel.setText (String.valueOf (following));
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }

            String id = "";
            String str;
            String[] temp = new String[0];
            File postsFile = new File (PersonalHomePageController.currentFF + "Posts.txt");
            try {
                Scanner readPosts = new Scanner (postsFile);
                postNum = 0;
                while (readPosts.hasNextLine ()){
                    str = readPosts.nextLine ();
                    temp = str.split ("&");
                    postsTxt.add (temp[0]);
                    postsImage.add (temp[1]);
                    //posts.put (temp[0],temp[1]);
                    postNum++;
                    String[] temp2 = str.split ("\\*");
                    id =temp2[1];
                }
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            if(temp.length >= 1){
                String s = temp[1];
                String s2 = temp[0];
                String[] strings = temp[1].split ("&:");
                File file3 = new File(strings[0]);
                if (file3.isAbsolute()) {
                    Image recentImg = new Image (s);
                    image.setImage (recentImg);
                    text.setText (s2);
                    //postNum = posts.size ();
                }
                else {
                    text.setText (s2);
                }
                currentPost = postNum;
                currentPost--;
                //System.out.println (currentPost);
            }

            seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
            likeLabel.setText (String.valueOf (seePostsController.likesNumer.get (seePostsController.likesNumer.size () - 1)));
            commentLabel.setText (String.valueOf (seePostsController.commentsNumer.get (seePostsController.commentsNumer.size () - 1)));
            try {
                seePostsController.selectedSeen(currentPost,PersonalHomePageController.username
                        ,PersonalHomePageController.currentFF);
            } catch ( IOException e ) {
                e.printStackTrace ();
            }
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            name.setText (BusinessHomepageController.currentFF);
            File file = new File (BusinessHomepageController.currentFF + "follower.txt");
            try {
                Scanner scanner = new Scanner (file);
                while (scanner.hasNextLine ()){
                    String s = scanner.nextLine ();
                    follower++;
                }
                followersLabel.setText (String.valueOf (follower));
                File file1 =  new File (BusinessHomepageController.currentFF + "following.txt");
                Scanner scanner1 = new Scanner (file1);
                while (scanner1.hasNextLine ()){
                    try {
                        String s = scanner1.nextLine ();
                        following++;
                    }
                    catch ( NoSuchElementException e ){
                        break;
                    }
                }
                followingsLabel.setText (String.valueOf (following));
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }

            String id = "";
            String str;
            String[] temp = new String[0];
            File postsFile = new File (BusinessHomepageController.currentFF + "Posts.txt");
            try {
                Scanner readPosts = new Scanner (postsFile);
                postNum = 0;
                while (readPosts.hasNextLine ()){
                    str = readPosts.nextLine ();
                    temp = str.split ("&");
                    postsTxt.add (temp[0]);
                    postsImage.add (temp[1]);
                    //posts.put (temp[0],temp[1]);
                    postNum++;
                    String[] temp2 = str.split ("\\*");
                    id =temp2[1];
                }
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            if(temp.length >= 1){
                String s = temp[1];
                String s2 = temp[0];
                String[] strings = temp[1].split ("&:");
                File file3 = new File(strings[0]);
                if (file3.isAbsolute()) {
                    Image recentImg = new Image (s);
                    image.setImage (recentImg);
                    text.setText (s2);
                    //postNum = posts.size ();
                }
                else {
                    text.setText (s2);
                }
                currentPost = postNum;
                currentPost--;


            }
            seePostsController.seePosts (BusinessHomepageController.username,BusinessHomepageController.currentFF);
            likeLabel.setText (String.valueOf (seePostsController.likesNumer.get (seePostsController.likesNumer.size () - 1)));
            commentLabel.setText (String.valueOf (seePostsController.commentsNumer.get (seePostsController.commentsNumer.size () -1)));
            try {
                seePostsController.selectedSeen(currentPost,BusinessHomepageController.username
                        ,BusinessHomepageController.currentFF);
            } catch ( IOException e ) {
                e.printStackTrace ();
            }

        }
    }

    @FXML
    void seeOlder(ActionEvent event) throws IOException {
        int n = currentPost - 1;
        if(n < postsTxt.size () && n >= 0){
            String s  = postsTxt.get (n);
            String s2 = postsImage.get (n);
            String[] strings = s2.split ("&:");
            File file = new File(strings[0]);
            if (file.isAbsolute()){
                Image recentImg = new Image (s2);
                image.setImage (recentImg);
                text.setText (s);
            }
            else {
                text.setText (s);
                image.setImage (null);
            }
            String sr = text + "&" + image;
            currentPost = n;
        }
        else if( n == -1){
            n = postsImage.size () - 1;
            String s  = postsTxt.get (n);
            String s2 = postsImage.get (n);
            String[] strings = s2.split ("&:");
            File file = new File(strings[0]);
            if (file.isAbsolute()){
                Image recentImg = new Image (s2);
                image.setImage (recentImg);
                text.setText (s);
            }
            else {
                text.setText (s);
                image.setImage (null);
            }
            currentPost = n;

        }
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            //seePostsController.seePosts(PersonalHomePageController.currentFF,PersonalHomePageController.currentFF);
            //System.out.println ("numer   " + seePostsController.likesNumer);
            likeLabel.setText (String.valueOf (seePostsController.likesNumer.get (currentPost)));
            commentLabel.setText (String.valueOf (seePostsController.commentsNumer.get (currentPost)));
            seePostsController.selectedSeen(currentPost,PersonalHomePageController.username,
                    PersonalHomePageController.currentFF);
        }
        else if(BusinessHomepageController.type.equalsIgnoreCase ("Business")){
            //seePostsController.seePosts(BusinessHomepageController.currentFF,BusinessHomepageController.currentFF);
            likeLabel.setText (String.valueOf (seePostsController.likesNumer.get (currentPost)));
            commentLabel.setText (String.valueOf (seePostsController.commentsNumer.get (currentPost)));
            seePostsController.selectedSeen(currentPost,BusinessHomepageController.username,
                    BusinessHomepageController.currentFF);
        }
    }

    @FXML
    void seeComments( MouseEvent event) throws IOException {
        seeCommentsScene(event);
    }

    @FXML
    void seelikes(MouseEvent event) throws IOException {
        seeLikesScene(event);
    }

    public void goBack(ActionEvent event) throws IOException {
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

    public void seeLikesScene( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SeeFLikes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void seeCommentsScene( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SeeFComments.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }
}
