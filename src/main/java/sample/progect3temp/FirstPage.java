package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class FirstPage {

    @FXML
    private Button loginButton;

    @FXML
    private Button signinButton;

    Main main=new Main();
    @FXML
    void login(ActionEvent event) throws IOException {

        main.changeScene("login-page.fxml");


    }

    @FXML
    void signin(ActionEvent event) throws IOException {

        main.changeScene("signin-page.fxml");


    }

}
