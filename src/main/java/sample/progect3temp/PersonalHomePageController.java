package sample.progect3temp;

import javafx.application.Application;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PersonalHomePageController implements Initializable {

    public static String username;
    public static String type;
    public static String password;
    public static int followers = 0;
    public static int followings = 0;
    public static int postNum = 0;
    public static int currentPost = 0;
    public static HashMap<String ,String> posts = new HashMap<> ();//1.text     2.image
    public ArrayList<String> textPosts = new ArrayList<> ();
    public ArrayList<String> imagePosts = new ArrayList<> ();

    static String currentFF;

    @Override
    public void initialize( URL location, ResourceBundle resources ){
        followers = 0;
        followings = 0;
        currentPost = 0;
        nameLabel.setText (username);
        File followersFile = new File (username + "follower.txt");
        try {
            Scanner scanner = new Scanner (followersFile);
            while (scanner.hasNextLine ()){
                String s = scanner.nextLine ();
                followers++;
            }
        } catch ( FileNotFoundException e ) {
        }

        File followingsFile = new File (username + "following.txt");
        try {
            Scanner scanner = new Scanner (followingsFile);
            while (scanner.hasNextLine ()){
                String s = scanner.nextLine ();
                followings++;
            }
        } catch ( FileNotFoundException e ) {

        }
        numberOfFollowersLabel.setText (String.valueOf (followers));
        numberOfFollowingsLabel.setText (String.valueOf (followings));


        String str;
        String[] temp = new String[0];
        File postsFile = new File (username + "Posts.txt");
        try {
            Scanner readPosts = new Scanner (postsFile);
            postNum = 0;
            while (readPosts.hasNextLine ()){
                str = readPosts.nextLine ();
                 temp = str.split ("&");
               posts.put (temp[0],temp[1]);
               postNum++;
               textPosts.add (temp[0]);
               imagePosts.add (temp[1]);

            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace ();
        }
        if(temp.length >= 1){
            String s = temp[1];
            String s2 = temp[0];
            String[] strings = temp[1].split ("&:");
            File file = new File(strings[0]);
            if (file.isAbsolute()) {
                Image recentImg = new Image (s);
                pHomePageImage.setImage (recentImg);
                pHomePageText.setText (s2);
                //postNum = posts.size ();
            }
            else {
                pHomePageText.setText (s2);
            }
            currentPost = postNum;
            //currentPost--;
//            if(currentPost != 0 && currentPost != 1){
//                currentPost -= 2;
//            }
//            if(currentPost == 1){
//                currentPost--;
//            }
            if(postNum != 0){
                currentPost = postNum - 1;
            }

        }

        fAndFList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
            @Override
            public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                currentFF = fAndFList.getSelectionModel().getSelectedItem();
                nameLabelOfFF.setText (currentFF);

            }
        });

    }

    @FXML
    private BorderPane personalPane;

    @FXML
    Label numberOfFollowingsLabel;
    @FXML
    Label numberOfFollowersLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label idLabel;
    @FXML
    ImageView likeImageView;
    @FXML
    ImageView commentImageView;
    @FXML
    ImageView userRecomImage;
    @FXML
    ImageView adRecomImage;
    @FXML
    ImageView allChatsImage;
    @FXML
    ImageView newGroupImage;
    @FXML
    ImageView newChatImage;
    @FXML
    ImageView unfollowImage;
    @FXML
    ImageView followImage;
    @FXML
    ImageView createPostImage;
    @FXML
    ImageView mainMenuImage;
    @FXML
    ImageView logoutImage;
    @FXML
    ImageView pHomePageImage;
    @FXML
    TextArea pHomePageText;
    @FXML
    Label commentLabel;
    @FXML
    Button seeOlderPosts;
    @FXML
    private ListView <String> fAndFList;
    @FXML
    private Label nameLabelOfFF;
    @FXML
    private Label feedBack;


    //Image likeImg = new Image (getClass ().getResourceAsStream ("like.png").toURI().toString());

    private Image likeImg = new Image((new File ("like.png")).toURI().toString());
    public  void setPic(){
       // likeImageView.setImage (likeImg);
    }


    public void suggestAd(MouseEvent event) throws IOException {
        FirstAdScene(event);
    }

    public void logout(MouseEvent event) throws IOException {
        logoutOfAccount(event);
    }

    public void createPost( MouseEvent event ) throws IOException {
        pHomePageImage.setImage (null);
        CreatePostController.hasImage = false;
        CreatePostController.hasText = false;
        createPostScene(event);

    }

    public void mainMenu(MouseEvent event) throws IOException {
        mainmenu(event);
    }

    @FXML
    void seeFollowers(MouseEvent event) throws FileNotFoundException {
        fAndFList.getItems ().clear ();
        ArrayList<String> f = new ArrayList <> ();
        File file = new File (username + "follower.txt");
        Scanner scanner = new Scanner (file);
        while (scanner.hasNextLine ()){
            String s= scanner.nextLine ();
            f.add (s);
        }
        fAndFList.getItems ().addAll (f);
    }

    @FXML
    void seePosts(ActionEvent event) throws IOException {
        ArrayList<String> f = new ArrayList <> ();
        File file = new File (username + "following.txt");
        Scanner scanner = new Scanner (file);
        while (scanner.hasNextLine ()){
            String s = scanner.nextLine ();
            f.add (s);
        }
        if(f.contains (currentFF)){
            seePostsScene(event);
        }
        else {
            feedBack.setText ("You can see posts of your followings");
        }
    }

    @FXML
    void chatGo(MouseEvent event) throws IOException {
        Main main = new Main ();
        main.changeScene("chat-page.fxml");
    }



    @FXML
    void seeFollowings(MouseEvent event) throws FileNotFoundException {
        fAndFList.getItems ().clear ();
        ArrayList<String> f = new ArrayList <> ();
        File file = new File (username + "following.txt");
        Scanner scanner = new Scanner (file);
        while (scanner.hasNextLine ()){
            String s= scanner.nextLine ();
            f.add (s);
        }
        fAndFList.getItems ().addAll (f);
    }

    @FXML
    void exit(MouseEvent event) {
        stage = (Stage) personalPane.getScene ().getWindow ();
        stage.close ();
    }


    public void follow(MouseEvent event) throws IOException {
        followScene (event);
    }

    public void showLikes(MouseEvent event) throws IOException {
        likesScene(event);
    }

    public void showComments(MouseEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        seePostsController.seeMyOwnPosts (username);
        boolean b = seePostsController.myCanComment (username, String.valueOf (postNum));
        if(b){
            commentsScene(event);
        }
        else{
            commentLabel.setText ("Comments are not available for this post");
        }
    }

    public void suggestUsers(MouseEvent event) throws IOException {
        suggestScene(event);
    }

    public void setSeeOlderPosts(ActionEvent event){
        int n = currentPost - 1;
        if(n < posts.size () && n >= 0){
           // String s  = (String) posts.keySet ().toArray ()[n];
            //String s2 = posts.get (s);
            String s = textPosts.get (n);
            String s2 = imagePosts.get (n);
            String[] strings = s2.split ("&:");
            File file = new File(strings[0]);
            if (file.isAbsolute()){
                Image recentImg = new Image (s2);
                pHomePageImage.setImage (recentImg);
                pHomePageText.setText (s);
            }
            else {
                pHomePageText.setText (s);
                pHomePageImage.setImage (null);
            }
            currentPost = n;
        }
        else if( n == -1){
            n = posts.size () - 1;
            //String s  = (String) posts.keySet ().toArray ()[n];
            //String s2 = posts.get (s);
            String s = textPosts.get (n);
            String s2 = imagePosts.get (n);
            String[] strings = s2.split ("&:");
            File file = new File(strings[0]);
            if (file.isAbsolute()){
                Image recentImg = new Image (s2);
                pHomePageImage.setImage (recentImg);
                pHomePageText.setText (s);
            }
            else {
                pHomePageText.setText (s);
                pHomePageImage.setImage (null);
            }
            currentPost = n;
        }
    }

    @FXML
    void unfollow(MouseEvent event) throws IOException {
        ArrayList<String> tempFollowing = new ArrayList<> ();
        ArrayList<String> tempFollower = new ArrayList<> ();
       String s = nameLabelOfFF.getText ();
       if(!s.equals ("")){
           File file = new File (username + "following.txt");
           Scanner scanner = new Scanner (file);
           while (scanner.hasNextLine ()){
               String str = scanner.nextLine ();
               if(!str.equals (s)){
                   tempFollowing.add (str);
               }
           }
           FileWriter fileWriter = new FileWriter (file,false);
           for ( String s1 : tempFollowing ) {
               fileWriter.write (s1);
           }

           fAndFList.getItems ().remove (s);
          String t = numberOfFollowingsLabel.getText ();
          int i = Integer.parseInt (t) - 1;

           File file1 = new File (s + "follower.txt");
           Scanner scanner1 = new Scanner (file1);
           while (scanner1.hasNextLine ()){
               String str = scanner1.nextLine ();
               if(!str.equals (s)){
                   tempFollower.add (str);
               }
           }
           FileWriter fileWriter1 = new FileWriter (file1,false);
           for ( String s1 : tempFollower ) {
               fileWriter1.write (s1);
           }

           numberOfFollowingsLabel.setText (String.valueOf (i));
           feedBack.setText ("user unfollowed");
       }
       else if(s.equals ("")){
           feedBack.setText ("no selected following");
       }
    }

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void createPostScene( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreatePost.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void logoutOfAccount( MouseEvent event ) throws IOException {
        username = "";
        root = FXMLLoader.load(getClass().getResource("first-page.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void likesScene( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SeeLikesOfPost.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void commentsScene( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SeeCommentsOfPost.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void FirstAdScene( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FirstAdRecommendation.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void followScene( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FollowScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void seePostsScene( ActionEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SeePosts.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void mainmenu( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void suggestScene( MouseEvent event ) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SuggestUsers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

}
