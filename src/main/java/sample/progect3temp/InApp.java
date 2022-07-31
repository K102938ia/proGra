package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class InApp {

    ChatController chatController=new ChatController();
    Main main=new Main();

    @FXML
    private Button chatButton;
    @FXML
    private Button backButton;

    @FXML
    void goToChatScene(ActionEvent event) throws IOException {


        main.changeScene("chat-page.fxml");

    }

    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {
        main.changeScene("first-page.fxml");

    }


}
