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
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FAndFAd implements Initializable {
    AdRecommendation adRecommendation = new AdRecommendation ();
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView adImage;

    @FXML
    private Label adName;

    @FXML
    private TextArea adText;

    @FXML
    private Button back;

    @FXML
    private Button moreAds;
    @FXML
    private Label noAd;

   static int n;

    @FXML
    void goBack( ActionEvent event) throws IOException {
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

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            boolean b = false;
            try {
              b = adRecommendation.findAdBasedOnPeople (PersonalHomePageController.username);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            if(b){
                noAd.setText ("");
                 n = adRecommendation.postToRecomRand.size () - 1;
                String s = adRecommendation.peopleToRecomRand.get (n);
                String s2 = adRecommendation.postToRecomRand.get (n);
                String temp = adRecommendation.imageToRecomRand.get (n);
                File file = new File(temp);
                if (file.isAbsolute()){
                    Image img = new Image (temp);
                    adImage.setImage (img);
                    adText.setText (s2);
                    adName.setText (s);
                }
                else {
                    adText.setText (s2);
                    adImage.setImage (null);
                    adName.setText (s);
                }
            }
            else {
                noAd.setText ("no suitable Ads");
            }
        }
        if(BusinessHomepageController.type.equalsIgnoreCase ("Business")){
            boolean b = false;
            try {
                b = adRecommendation.findAdBasedOnPeople (BusinessHomepageController.username);
            } catch ( FileNotFoundException e ) {
                e.printStackTrace ();
            }
            if(b){
                noAd.setText ("");
                 n = adRecommendation.postToRecomRand.size () - 1;
                String s = adRecommendation.peopleToRecomRand.get (n);
                String s2 = adRecommendation.postToRecomRand.get (n);
                String temp = adRecommendation.imageToRecomRand.get (n);
                File file = new File(temp);
                if (file.isAbsolute()){
                    Image img = new Image (temp);
                    adImage.setImage (img);
                    adText.setText (s2);
                    adName.setText (s);
                }
                else {
                    adText.setText (s2);
                    adImage.setImage (null);
                    adName.setText (s);
                }
            }
            else {
                noAd.setText ("no suitable Ads");
            }
        }
    }

    @FXML
    void showMoreAds(ActionEvent event) {
        n--;
        if(n < adRecommendation.postToRecom.size () && n > 0){
            noAd.setText ("");
            String s = adRecommendation.peopleToRecomRand.get (n);
            String s2 = adRecommendation.postToRecomRand.get (n);
            String temp = adRecommendation.imageToRecomRand.get (n);
            File file = new File(temp);
            if (file.isAbsolute()){
                Image img = new Image (temp);
                adImage.setImage (img);
                adText.setText (s2);
                adName.setText (s);
            }
            else {
                adText.setText (s2);
                adImage.setImage (null);
                adName.setText (s);
            }
        }
        else {
            noAd.setText ("no suitable Ads");
        }
    }
}
