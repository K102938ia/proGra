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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeeFComments implements Initializable {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea commentArea;

    @FXML
    private Label commentL;

    @FXML
    private Label commentSavedLabel;

    @FXML
    private Label likedLabel;

    @FXML
    private Label commentorL;
    @FXML
    private ListView <String > commentList;
    @FXML
    private ListView<String> commentor;
    @FXML
    private ImageView likedImage;


    String currentComment;
    String currentCommentor;
    ArrayList < String > commentsIDs = new ArrayList <> ();
    String commentId;
    int commentNum = 0;
    boolean coc = false;

    @FXML
    void commentOnAComment(ActionEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
            seePostsController.seeCommentsOfAComment(currentComment,PersonalHomePageController.username,commentId);
            //commentNum = seePostsController.seeCommentOnComment.indexOf (currentComment);
            //int commentNum = seePostsController.myComments.indexOf (currentComment);
            commentId = commentsIDs.get (commentNum);
            String commentText =  commentArea.getText ();
            seePostsController.commentOnComment (commentId,commentText,PersonalHomePageController.username);
            commentSavedLabel.setText ("comment saved");
            commentArea.clear ();
        }

        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seeMyOwnPosts(BusinessHomepageController.username);
            seePostsController.seeCommentsOfAComment(currentComment,BusinessHomepageController.username,commentId);
            // commentNum = seePostsController.seeCommentOnComment.indexOf (currentComment);
            //String commentIdTemp = commentsIDs.get (commentNum);
            //commentsIDs = seePostsController.seeCommentsOfAComment ("", BusinessHomepageController.username, commentIdTemp);
            commentId = commentsIDs.get (commentNum);
            String commentText =  commentArea.getText ();
            seePostsController.commentOnComment (commentId,commentText,BusinessHomepageController.username);
            commentSavedLabel.setText ("comment saved");
            commentArea.clear ();
        }
    }

    @FXML
    void commnetOnPost(ActionEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
            boolean b = seePostsController.commentOnAPost(PersonalHomePageController.username,PersonalHomePageController.currentFF,
                    String.valueOf (SeePostsF.postNum),commentArea.getText ());

            commentor.getItems ().add (PersonalHomePageController.username);
            commentList.getItems ().add (commentArea.getText ());
            commentSavedLabel.setText ("comment saved");
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seePosts (BusinessHomepageController.username,BusinessHomepageController.currentFF);
            boolean b = seePostsController.commentOnAPost (BusinessHomepageController.username,BusinessHomepageController.currentFF,
                    String.valueOf (SeePostsF.currentPost),commentArea.getText ());
            commentor.getItems ().add (BusinessHomepageController.username);
            commentList.getItems ().add (commentArea.getText ());
            commentSavedLabel.setText ("comment saved");
        }
        commentArea.clear ();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
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

    @FXML
    void likeAComment(ActionEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
            seePostsController.seeCommentsSpecified(String.valueOf (SeePostsF.currentPost),PersonalHomePageController.currentFF);
            //commentNum = seePostsController.myComments.indexOf (currentComment);
            commentNum = seePostsController.specifiedComments.indexOf (currentComment);
            commentId = commentsIDs.get (commentNum);
            boolean canLike = seePostsController.likeAComment (commentId, PersonalHomePageController.username);
            if(canLike){
                //Image liked = new Image((new File ("red.png")).toURI().toString());
                //likedImage.setImage (liked);
                Image myImage = new Image(getClass().getResourceAsStream("red.png"));
                likedImage.setImage (myImage);
                likedLabel.setText ("You liked the selected comment");
            }
            else {
                likedImage.setImage (null);
                likedLabel.setText ("You have liked this comment before");
            }
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seePosts (BusinessHomepageController.username,BusinessHomepageController.currentFF);
            //commentNum = seePostsController.myComments.indexOf (currentComment);
            commentId = commentsIDs.get (commentNum);
            boolean canLike = seePostsController.likeAComment (commentId, BusinessHomepageController.username);
            if(canLike){
                //Image liked = new Image(getClass().getResourceAsStream("red.png"));
                //likedImage.setImage (liked);
                Image myImage = new Image(getClass().getResourceAsStream("red.png"));
                likedImage.setImage (myImage);
                likedLabel.setText ("You liked the selected comment");
            }
            else {
                likedImage.setImage (null);
                likedLabel.setText ("You have liked this comment before");
            }
        }
    }

    @FXML
    void seeCommentsOfComment(ActionEvent event) throws FileNotFoundException {
        coc = true;
        SeePostsController seePostsController = new SeePostsController ();
        //commentsIDs = seePostsController.seeComments ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
            commentId = commentsIDs.get (commentNum);
            commentsIDs = seePostsController.seeCommentsOfAComment (currentComment,PersonalHomePageController.username
                    ,commentId);
            commentor.getItems ().clear ();
            commentor.getItems ().addAll (seePostsController.seeCommentOnCommentCommentors);
            commentList.getItems ().clear ();
            commentList.getItems ().addAll (seePostsController.seeCommentOnComment);

        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seePosts (BusinessHomepageController.username,BusinessHomepageController.currentFF);
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
    void seeLikesOfComment(ActionEvent event) throws FileNotFoundException {
        ArrayList<String > commentLikes = new ArrayList<> ();
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
            if(!coc){
                //System.out.println (commentsIDs);
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
           seePostsController.seePosts (BusinessHomepageController.username,BusinessHomepageController.currentFF);
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


    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        commentor.getItems ().clear ();
        commentList.getItems ().clear ();
        coc = false;
        SeePostsController seePostsController = new SeePostsController ();
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            seePostsController.seePosts (PersonalHomePageController.username,PersonalHomePageController.currentFF);
            try {
                commentsIDs = seePostsController.seeCommentsSpecified (String.valueOf (SeePostsF.currentPost),PersonalHomePageController.currentFF);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            //System.out.println (commentsIDs);
            //commentsIDs = seePostsController.seeComments ();
            commentList.getItems ().addAll (seePostsController.specifiedComments);
            commentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                @Override
                public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                    currentComment = commentList.getSelectionModel().getSelectedItem();
                    try {
                        commentsIDs = seePostsController.seeCommentsSpecified (String.valueOf (SeePostsF.currentPost),PersonalHomePageController.currentFF);
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
                        commentId = commentsIDs.get (commentNum);
                        try {
                            seePostsController.seeCommentsSpecified(String.valueOf (SeePostsF.currentPost),PersonalHomePageController.currentFF);
                        } catch ( FileNotFoundException e ) {
                            e.printStackTrace ();
                        }
                        commentNum = seePostsController.specifiedComments.indexOf (currentComment);
                    }
                    commentL.setText(currentComment);

                }
            });
            commentor.getItems ().addAll (seePostsController.specifiedCommentors);
            commentor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                @Override
                public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                    currentCommentor = commentor.getSelectionModel().getSelectedItem();
                    commentorL.setText(currentCommentor);
                    commentNum = seePostsController.myComments.indexOf (currentComment);
                }
            });
        }

        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            seePostsController.seePosts (BusinessHomepageController.username,BusinessHomepageController.currentFF);
            try {
                commentsIDs =  seePostsController.seeCommentsSpecified (String.valueOf (SeePostsF.currentPost),BusinessHomepageController.currentFF);
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
                    commentL.setText(currentComment);
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
                        try {
                            seePostsController.seeCommentsSpecified(String.valueOf (SeePostsF.currentPost),PersonalHomePageController.currentFF);
                        } catch ( FileNotFoundException e ) {
                            e.printStackTrace ();
                        }
                        commentNum = seePostsController.specifiedComments.indexOf (currentComment);
                    }
                    commentL.setText(currentComment);

                }
            });
            commentor.getItems ().addAll (seePostsController.specifiedCommentors);
            commentor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <String> () {
                @Override
                public void changed( ObservableValue <? extends String> arg0, String arg1, String arg2) {
                    currentCommentor = commentor.getSelectionModel().getSelectedItem();
                    commentorL.setText(currentCommentor);

                }
            });
        }
    }
}
