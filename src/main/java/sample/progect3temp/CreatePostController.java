package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreatePostController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label commentLabel;
    @FXML
    ImageView postImage;
    @FXML
    Button addImageButton;
    @FXML
    Button postButton;
    @FXML
    Button createpostBackButton;
    @FXML
    TextArea postTextTextField;
    @FXML
    TextArea postImageTextField;
    @FXML
    TextField commentableTextField;
    @FXML
    Label postLabel;
    @FXML
    CheckBox yesComment;
    @FXML
    CheckBox noComment;

    public static boolean hasImage = false;
    public static boolean hasText = false;
    public static boolean isCommentable = false;
    public static boolean validCommentable = false;
    public static String text;
    public static String pathOfImage;

    public void addPost () {

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

    public boolean addImage(ActionEvent event){
        pathOfImage = "";
       FileChooser fileChooser = new FileChooser ();
       fileChooser.setTitle ("Select an image");
       File file = fileChooser.showOpenDialog (null);
        pathOfImage = file.getAbsolutePath ();
       postImage.setImage (new Image (pathOfImage));
       hasImage = true;
        return true;
    }

    public void addText(ActionEvent event){
        text = postTextTextField.getText ();
        hasText = true;
    }

    public void commentable(ActionEvent event){
//        String s = commentableTextField.getText ();
//        if(s.equalsIgnoreCase ("yes")){
//            isCommentable = true;
//            commentLabel.setText ("yes");
//            validCommentable = true;
//        }
//        else if(s.equalsIgnoreCase ("no")){
//            isCommentable = false;
//            commentLabel.setText ("no");
//            validCommentable = true;
//        }
//        else {
//            commentLabel.setText ("invalid answer");
//        }
        if(yesComment.isSelected ()){
            isCommentable = true;
            commentLabel.setText ("yes");
            validCommentable = true;
            commentLabel.setText ("");
        }
        else if (noComment.isSelected ()){
            isCommentable = false;
            commentLabel.setText ("no");
            validCommentable = true;
            commentLabel.setText ("");
        }
        else {
            commentLabel.setText ("You have not selected any option");
        }
    }

    public void createPost(ActionEvent event) throws FileNotFoundException {
        if(validCommentable){
            boolean validPost = false;
            String postTextAndPic = "";
            if(hasText && hasImage){
                 postTextAndPic = text + "&" + pathOfImage + "&";
                 validPost = true;
            }
            else if ( !hasText && hasImage ){
                postTextAndPic = "&" + pathOfImage + "&";
                validPost = true;
            }
            else if(hasText && !hasImage){
                postTextAndPic = text + "&";
                validPost = true;
            }
            else if(!hasImage && !hasText){
                validPost = false;
            }
            if(validPost){
                PostController postController = new PostController ();
                if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
                    postController.addPost (PersonalHomePageController.username,postTextAndPic,isCommentable);
                }
                else if(BusinessHomepageController.type.equalsIgnoreCase ("Business")){
                    postController.addPost (BusinessHomepageController.username,postTextAndPic,isCommentable);
                }
                postLabel.setText ("added successfully");
            }
            else if ( !validPost ){
                postLabel.setText ("Your post must contain some text or an image or both");
            }
        }
        else if(!validCommentable){
            postLabel.setText ("Your answer to the question is invalid");
        }
    }
}
