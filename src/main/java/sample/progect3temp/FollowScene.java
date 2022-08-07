package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FollowScene {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button backButton;

    @FXML
    private Button followButton;

    @FXML
    private TextField searchForName;
    @FXML
    private Label feedBackLabel;

    @FXML
    void follow(ActionEvent event) throws IOException {
        String name = searchForName.getText ();
        if(searchForName.equals (null)){
            feedBackLabel.setText ("no user is selected");
        }
        else if(PersonalHomePageController.type.equalsIgnoreCase ("Personal")){
            boolean followedBefore = false;
            boolean exists = false;
            for ( User user : ManagerBasic.users ) {
                if(user.userName.equals (name)){
                    exists = true;
                    break;
                }
            }
            if(name.equals (PersonalHomePageController.username)){
                feedBackLabel.setText ("you cannot follow yourself");
            }
            else if(exists){
                File file = new File (PersonalHomePageController.username + "following.txt");
                Scanner scanner = new Scanner (file);
                while (scanner.hasNextLine ()){
                    String s = scanner.nextLine ();
                    if(s.equals (name)){
                        feedBackLabel.setText ("You have followed this user before");
                        followedBefore = true;
                        break;
                    }
                }
                if ( !followedBefore ){
                    File file1 = new File (name+ "follower.txt");
                    FileWriter fileWriter = new FileWriter (file1,true);
                    fileWriter.write (PersonalHomePageController.username + "\n");
                    fileWriter.close ();
                    File file2 = new File (PersonalHomePageController.username+ "following.txt");
                    FileWriter fileWriter2 = new FileWriter (file2,true);
                    fileWriter2.write (name + "\n");
                    fileWriter2.close ();
                    feedBackLabel.setText ("followed successfully");

                }
            }
            else if(!exists){
                feedBackLabel.setText ("this user does not exist");
            }
        }
        else if ( BusinessHomepageController.type.equalsIgnoreCase ("Business") ){
            boolean followedBefore = false;
            boolean exists = false;
            for ( User user : ManagerBasic.users ) {
                if(user.userName.equals (name)){
                    exists = true;
                    break;
                }
            }
            if(name.equals (BusinessHomepageController.username)){
                feedBackLabel.setText ("you cannot follow yourself");
            }
            else if(exists){
                File file = new File (BusinessHomepageController.username + "following.txt");
                Scanner scanner = new Scanner (file);
                while (scanner.hasNextLine ()){
                    String s = scanner.nextLine ();
                    if(s.equals (name)){
                        feedBackLabel.setText ("You have followed this user before");
                        followedBefore = true;
                        break;
                    }
                }
                if ( !followedBefore ){
                    File file1 = new File (name+ "follower.txt");
                    FileWriter fileWriter = new FileWriter (file1,true);
                    fileWriter.write (BusinessHomepageController.username + "\n");
                    fileWriter.close ();
                    File file2 = new File (BusinessHomepageController.username+ "following.txt");
                    FileWriter fileWriter2 = new FileWriter (file2,true);
                    fileWriter2.write (name + "\n");
                    fileWriter2.close ();
                    feedBackLabel.setText ("followed successfully");

                }
            }
            else if(!exists){
                feedBackLabel.setText ("this user does not exist");
            }
        }
    }

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
}
