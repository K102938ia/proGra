package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SecureCacheResponse;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RandomAd implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button adBack;

    @FXML
    private ImageView adImage;

    @FXML
    private TextArea adText;

    @FXML
    private Button moreAds;
    @FXML
    private Label noSuitableLabel;
    @FXML
    private Label adUser;
    @FXML
    private Label noAd;
    @FXML
    private Label likeLabel;

    String wholePost;
    String userWhole;

    AdRecommendation adRecommendation = new AdRecommendation ();

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            boolean b = false;
            try {
                b = adRecommendation.findAdRandomly (PersonalHomePageController.username);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            if(b){
                noAd.setText ("");
                int n = adRecommendation.postToRecomRand.size () - 1;
                // String s  = (String) adRecommendation.randomRecomm.keySet ().toArray ()[n];
                //String s2 = adRecommendation.randomRecomm.get (s);
                String s = adRecommendation.peopleToRecomRand.get (n);
                String s2 = adRecommendation.postToRecomRand.get (n);
                String temp = adRecommendation.imageToRecomRand.get (n);
                File file = new File(temp);
                if (file.isAbsolute()){
                    Image img = new Image (temp);
                    adImage.setImage (img);
                    adText.setText (s2);
                    adUser.setText (s);
                    wholePost = s2 + "&" + temp;
                    userWhole = s;
                }
                else {
                    adText.setText (s2);
                    adImage.setImage (null);
                    adUser.setText (s);
                    wholePost = s2;
                    userWhole = s;
                }
            }
            else {
                noAd.setText ("no suitable Ads");
            }
        }
        else if(BusinessHomepageController.type.equalsIgnoreCase ("Business")){
            boolean b = false;
            try {
                b = adRecommendation.findAdRandomly (BusinessHomepageController.username);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            if(b){
                noAd.setText ("");
                int n = adRecommendation.postToRecomRand.size () - 1;
                // String s  = (String) adRecommendation.randomRecomm.keySet ().toArray ()[n];
                //String s2 = adRecommendation.randomRecomm.get (s);
                String s = adRecommendation.peopleToRecomRand.get (n);
                String s2 = adRecommendation.postToRecomRand.get (n);
                String temp = adRecommendation.imageToRecomRand.get (n);
                File file = new File(temp);
                if (file.isAbsolute()){
                    Image img = new Image (temp);
                    adImage.setImage (img);
                    adText.setText (s2);
                    adUser.setText (s);
                    userWhole = s;
                    wholePost = s2 + "&" + temp;
                }
                else {
                    adText.setText (s2);
                    adImage.setImage (null);
                    adUser.setText (s);
                    wholePost = s2;
                    userWhole = s;
                }
            }
            else {
                noAd.setText ("no suitable Ads");
            }
        }
    }
    @FXML
    void moreAds( ActionEvent event) {
        likeLabel.setText ("");
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            boolean b = false;
            try {
                b = adRecommendation.findAdRandomly (PersonalHomePageController.username);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            if(b){
                noAd.setText ("");
                int n = adRecommendation.postToRecomRand.size () - 1;
                // String s  = (String) adRecommendation.randomRecomm.keySet ().toArray ()[n];
                //String s2 = adRecommendation.randomRecomm.get (s);
                String s = adRecommendation.peopleToRecomRand.get (n);
                String s2 = adRecommendation.postToRecomRand.get (n);
                String temp = adRecommendation.imageToRecomRand.get (n);
                File file = new File(temp);
                if (file.isAbsolute()){
                    Image img = new Image (temp);
                    adImage.setImage (img);
                    adText.setText (s2);
                    adUser.setText (s);
                    wholePost = s2 + "&" + temp;
                    userWhole = s;
                }
                else {
                    adText.setText (s2);
                    adImage.setImage (null);
                    adUser.setText (s);
                    wholePost = s2;
                    userWhole = s;
                }
            }
            else {
                noAd.setText ("no suitable Ads");
            }
        }
        else if(BusinessHomepageController.type.equalsIgnoreCase ("Business")){
            boolean b = false;
            try {
                b = adRecommendation.findAdRandomly (BusinessHomepageController.username);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            if(b){
                noAd.setText ("");
                int n = adRecommendation.postToRecomRand.size () - 1;
                // String s  = (String) adRecommendation.randomRecomm.keySet ().toArray ()[n];
                //String s2 = adRecommendation.randomRecomm.get (s);
                String s = adRecommendation.peopleToRecomRand.get (n);
                String s2 = adRecommendation.postToRecomRand.get (n);
                String temp = adRecommendation.imageToRecomRand.get (n);
                File file = new File(temp);
                if (file.isAbsolute()){
                    Image img = new Image (temp);
                    adImage.setImage (img);
                    adText.setText (s2);
                    adUser.setText (s);
                    wholePost = s2 + "&" + temp;
                    userWhole = s;
                }
                else {
                    adText.setText (s2);
                    adImage.setImage (null);
                    adUser.setText (s);
                    wholePost = s2;
                    userWhole = s;
                }
            }
            else {
                noAd.setText ("no suitable Ads");
            }
        }
    }

    @FXML
    void like( MouseEvent event) throws IOException {
        SeePostsController seePostsController = new SeePostsController ();
        File file = new File (userWhole + "Posts.txt");
        Scanner scanner = new Scanner (file);
        while(scanner.hasNextLine ()){
            String s = scanner.nextLine ();
            String[] str = s.split ("&:");
            if(str[0].equals (wholePost)){
                String[] s1 = s.split ("\\*");
                String id = s1[1];
                if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
                    File file1 = new File (id + "PostLikers.txt");
                    FileWriter fileWriter = new FileWriter (file1,true);
                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myObj.format(myFormatObj);
                    fileWriter.write (PersonalHomePageController.username + ":@" + formattedDate + "\n");
                    fileWriter.close ();
                }
                else  if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
                    File file1 = new File (id + "PostLikers.txt");
                    FileWriter fileWriter = new FileWriter (file1,true);
                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myObj.format(myFormatObj);
                    fileWriter.write (BusinessHomepageController.username + ":@" + formattedDate + "\n");
                    fileWriter.close ();
                }
                likeLabel.setText ("liked successfully");
                break;
            }
        }
    }

    @FXML
    void goBackRandAd(ActionEvent event) throws IOException {
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
