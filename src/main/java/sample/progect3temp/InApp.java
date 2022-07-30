package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class InApp {

    Main main=new Main();

    @FXML
    private Button chatButton;

    @FXML
    void goToChatScene(ActionEvent event) throws IOException {

        main.changeScene("chat-page.fxml");

    }

}
