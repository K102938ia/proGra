package sample.progect3temp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg =stage;

        stage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("first-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("program");
        stage.setScene(scene);
        stage.show();
        Image image = new Image(getClass().getResourceAsStream("icon4.png"));
        stage.getIcons ().add (image);
        ManagerBasic managerBasic = new ManagerBasic ();
        managerBasic.addUsersObjLoggedIn();

    }

    public void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);

        stg.setScene(scene);

    }



    public static void main(String[] args) {
        launch();
    }
}