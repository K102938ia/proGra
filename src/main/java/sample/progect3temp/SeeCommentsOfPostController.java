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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeeCommentsOfPostController  implements Initializable {

    @FXML
    Button commentBack;
    @FXML
    ListView<String> commentList;
    @FXML
    Label commentLabel;
    @FXML
    Label commentorLabel;
    @FXML
    ListView<String > commentor;
    @FXML
    Label commentSavedLabel;
    @FXML
    TextArea commentTextArea;
    @FXML
    private Button likeButton;
    @FXML
    private ImageView likedImage;
    @FXML
    Label likeLabel;
    @FXML
    private Button commentOnCommentButton;
    @FXML
    private Button seeCommentsOfACommentButton;
    @FXML
    private Button seeLikesOfCommentButton;




    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    String currentComment;
    String currentCommentor;
    ArrayList < String > commentsIDs = new ArrayList <> ();
    String commentId;
    int commentNum = 0;
    boolean coc = false;

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        commentor.getItems ().clear ();
        commentList.getItems ().clear ();
        coc = false;
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seeMyOwnPosts(PersonalHomePageController.username);
            try {
                commentsIDs = seePostsController.seeCommentsSpecified (String.valueOf (PersonalHomePageController.currentPost),PersonalHomePageController.username);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            //commentsIDs = seePostsController.seeComments ();
            commentList.getItems ().addAll (seePostsController.specifiedComments);
            commentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                @Override
                public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                    currentComment = commentList.getSelectionModel().getSelectedItem();
                    try {
                        commentsIDs = seePostsController.seeCommentsSpecified (String.valueOf (PersonalHomePageController.currentPost),PersonalHomePageController.username);
                    } catch ( FileNotFoundException e ) {
                        e.printStackTrace ();
                    }
                   // commentId = commentsIDs.get (commentNum);
                    if(coc){
                        try {
                            commentsIDs = seePostsController.seeCommentsOfAComment (currentComment,PersonalHomePageController.username,
                                    commentId);
                            seePostsController.seeCommentsOfAComment(currentComment,PersonalHomePageController.username,commentId);
                            commentNum = seePostsController.seeCommentOnComment.indexOf (currentComment);
                        } catch ( FileNotFoundException e ) {
                            e.printStackTrace ();
                        }
                    }
                    else {
                        //commentNum = seePostsController.specifiedComments.indexOf (currentComment);
                        commentId = commentsIDs.get (commentNum);
                        //commentNum = seePostsController.myComments.indexOf (currentComment);
                        commentNum = seePostsController.specifiedComments.indexOf (currentComment);
                    }
                    commentLabel.setText(currentComment);

                }
            });
            commentor.getItems ().addAll (seePostsController.specifiedCommentors);
            commentor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                @Override
                public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                    currentCommentor = commentor.getSelectionModel().getSelectedItem();
                    commentorLabel.setText(currentCommentor);
                    commentNum = seePostsController.myComments.indexOf (currentComment);
                }
            });


        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
            try {
                commentsIDs =  seePostsController.seeCommentsSpecified (String.valueOf (BusinessHomepageController.currentPost),BusinessHomepageController.username);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            //commentsIDs = seePostsController.seeComments ();
            //System.out.println (seePostsController.myComments);
            commentList.getItems ().addAll (seePostsController.specifiedComments);
            commentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                @Override
                public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                    currentComment = commentList.getSelectionModel().getSelectedItem();
                    try {
                        commentsIDs = seePostsController.seeCommentsSpecified (String.valueOf (BusinessHomepageController.currentPost),BusinessHomepageController.username);
                    } catch ( FileNotFoundException e ) {
                        e.printStackTrace ();
                    }
                    commentLabel.setText(currentComment);
                    if(coc){
                        try {
                            commentNum = seePostsController.seeCommentOnComment.indexOf (currentComment);
                            commentsIDs = seePostsController.seeCommentsOfAComment (currentComment,BusinessHomepageController.username,
                                    commentId);
                            seePostsController.seeCommentsOfAComment(currentComment,BusinessHomepageController.username,commentId);
                            //commentNum = seePostsController.seeCommentOnComment.indexOf (currentComment);
                        } catch ( FileNotFoundException e ) {
                            e.printStackTrace ();
                        }
                    }
                    else {
                        commentId = commentsIDs.get (commentNum);
                        commentNum = seePostsController.myComments.indexOf (currentComment);
                    }
                    commentLabel.setText(currentComment);

                }
            });
            commentor.getItems ().addAll (seePostsController.specifiedCommentors);
            commentor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                @Override
                public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                    currentCommentor = commentor.getSelectionModel().getSelectedItem();
                    commentorLabel.setText(currentCommentor);

                }
            });
        }
    }

    @FXML
    void addComment(ActionEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seeMyOwnPosts(PersonalHomePageController.username);
            boolean b = seePostsController.commentOnAPost (PersonalHomePageController.username,PersonalHomePageController.username,
                    String.valueOf (PersonalHomePageController.currentPost),commentTextArea.getText ());
            commentor.getItems ().add (PersonalHomePageController.username);
            commentList.getItems ().add (commentTextArea.getText ());
            commentSavedLabel.setText ("comment saved");
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
            boolean b = seePostsController.commentOnAPost (BusinessHomepageController.username,BusinessHomepageController.username,
                    String.valueOf (BusinessHomepageController.currentPost),commentTextArea.getText ());
            commentor.getItems ().add (BusinessHomepageController.username);
            commentList.getItems ().add (commentTextArea.getText ());
            commentSavedLabel.setText ("comment saved");
        }
        commentTextArea.clear ();
    }

    @FXML
    void likeAComment(ActionEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seeMyOwnPosts(PersonalHomePageController.username);
            //commentsIDs = seePostsController.seeCommentsSpecified (String.valueOf (PersonalHomePageController.currentPost),PersonalHomePageController.username);
            //commentNum = seePostsController.specifiedComments.indexOf (currentComment);
//            if(coc){
//                //seePostsController.seeCommentsOfAComment(currentComment,PersonalHomePageController.username,commentId);
//                commentNum = seePostsController.specifiedComments.indexOf (currentComment);
//            }
            commentId = commentsIDs.get (commentNum);
            boolean canLike = seePostsController.likeAComment (commentId, PersonalHomePageController.username);
            if(canLike){
                //Image liked = new Image((new File ("red.png")).toURI().toString());
                Image myImage = new Image(getClass().getResourceAsStream("red.png"));
                likedImage.setImage (myImage);
                likeLabel.setText ("You liked the selected comment");
            }
            else {
                likedImage.setImage (null);
                likeLabel.setText ("You have liked this comment before");
            }
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
            //commentNum = seePostsController.myComments.indexOf (currentComment);
            //commentsIDs = seePostsController.seeCommentsSpecified (String.valueOf (BusinessHomepageController.currentPost),BusinessHomepageController.username);
            //commentNum = seePostsController.specifiedComments.indexOf (currentComment);
            commentId = commentsIDs.get (commentNum);
            boolean canLike = seePostsController.likeAComment (commentId, BusinessHomepageController.username);
            if(canLike){
                Image myImage = new Image(getClass().getResourceAsStream("red.png"));
                //Image liked = new Image(getClass().getResourceAsStream("red.png"));
                likedImage.setImage (myImage);
                likeLabel.setText ("You liked the selected comment");
            }
            else {
                likedImage.setImage (null);
                likeLabel.setText ("You have liked this comment before");
            }
        }
    }

    @FXML
    void commentOnTheSelectedComment(ActionEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seeMyOwnPosts(PersonalHomePageController.username);
            seePostsController.seeCommentsOfAComment(currentComment,PersonalHomePageController.username,commentId);
            //commentNum = seePostsController.seeCommentOnComment.indexOf (currentComment);
            //int commentNum = seePostsController.myComments.indexOf (currentComment);
            commentId = commentsIDs.get (commentNum);
            String commentText =  commentTextArea.getText ();
            seePostsController.commentOnComment (commentId,commentText,PersonalHomePageController.username);
            commentSavedLabel.setText ("comment saved");
            commentTextArea.clear ();
        }

        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
            seePostsController.seeCommentsOfAComment(currentComment,BusinessHomepageController.username,commentId);
           // commentNum = seePostsController.seeCommentOnComment.indexOf (currentComment);
            //String commentIdTemp = commentsIDs.get (commentNum);
            //commentsIDs = seePostsController.seeCommentsOfAComment ("", BusinessHomepageController.username, commentIdTemp);
             commentId = commentsIDs.get (commentNum);
            String commentText =  commentTextArea.getText ();
            seePostsController.commentOnComment (commentId,commentText,BusinessHomepageController.username);
            commentSavedLabel.setText ("comment saved");
            commentTextArea.clear ();
        }
    }

    @FXML
    void seeCommentsOfAComment(ActionEvent event) throws FileNotFoundException {
        coc = true;
        SeePostsController seePostsController = new SeePostsController ();
        //commentsIDs = seePostsController.seeComments ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seeMyOwnPosts(PersonalHomePageController.username);
            commentId = commentsIDs.get (commentNum);
            commentsIDs = seePostsController.seeCommentsOfAComment (currentComment,PersonalHomePageController.username
                    ,commentId);
            commentor.getItems ().clear ();
            commentor.getItems ().addAll (seePostsController.seeCommentOnCommentCommentors);
            commentList.getItems ().clear ();
            commentList.getItems ().addAll (seePostsController.seeCommentOnComment);

        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
            //System.out.println (currentComment);
            //System.out.println (commentsIDs);
            //System.out.println (commentId);
            commentId = commentsIDs.get (commentNum);
            commentsIDs = seePostsController.seeCommentsOfAComment (currentComment,BusinessHomepageController.username
                    ,commentId);
            commentor.getItems ().clear ();
            commentor.getItems ().addAll (seePostsController.seeCommentOnCommentCommentors);
            commentList.getItems ().clear ();
            commentList.getItems ().addAll (seePostsController.seeCommentOnComment);
        }
    }

    @FXML
    void LikesOfComment(ActionEvent event) throws FileNotFoundException {
        ArrayList<String > commentLikes = new ArrayList<> ();
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seeMyOwnPosts(PersonalHomePageController.username);
            if(!coc){
                //commentNum = seePostsController.myComments.indexOf (currentComment);
                commentId = commentsIDs.get (commentNum);
            }
            else{
                commentId = commentsIDs.get (commentNum);
            }
            commentLikes = seePostsController.seeCommentLikes ("", commentId);
            commentor.getItems ().clear ();
            commentList.getItems ().clear ();
            commentor.getItems ().addAll (commentLikes);
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
            if(!coc){
                //commentNum = seePostsController.myComments.indexOf (currentComment);
                commentId = commentsIDs.get (commentNum);
            }
            else {
                commentId = commentsIDs.get (commentNum);
            }
            commentLikes = seePostsController.seeCommentLikes ("", commentId);
            commentor.getItems ().clear ();
            commentList.getItems ().clear ();
            commentor.getItems ().addAll (commentLikes);
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
