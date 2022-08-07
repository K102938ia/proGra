package sample.progect3temp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StatsController implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button statsBack;
    @FXML
    private BarChart<String, Number> likeChart;

    @FXML
    private BarChart <String, Number> seenChart;
    @FXML
    private CategoryAxis xLike;

    @FXML
    private CategoryAxis xSeen;

    @FXML
    private NumberAxis yLike;

    @FXML
    private NumberAxis ySeen;



    @FXML
    void statsGoBack( ActionEvent event) throws IOException {
        goBackToHomePage(event);
    }

    public void goBackToHomePage( ActionEvent event ) throws IOException {
            root = FXMLLoader.load (getClass ().getResource ("BusinessHomePage.fxml"));
            stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            scene = new Scene (root);
            stage.setScene (scene);
            stage.show ();
    }


    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        SeePostsController seePostsController = new SeePostsController ();
        try {
            seePostsController.showStats(BusinessHomepageController.username, BusinessHomepageController.currentPost);
            XYChart.Series<String,Number> series = new XYChart.Series <> ();
            series.setName ("Seen");
            for ( LocalDate localDate : seePostsController.seenByDay.keySet () ){
                int  n = seePostsController.seenByDay.get (localDate);
                series.getData ().add (new XYChart.Data <String, Number> ((String.valueOf (localDate)),n ));
            }

            seenChart.getData ().add (series);

            XYChart.Series<String,Number> series1 = new XYChart.Series <> ();
            series1.setName ("Like");
            for ( LocalDate localDate : seePostsController.likeByDay.keySet () ){
                int  n = seePostsController.likeByDay.get (localDate);
                series1.getData ().add (new XYChart.Data <String, Number> ((String.valueOf (localDate)),n ));
            }

            likeChart.getData ().add (series1);

        } catch ( FileNotFoundException e ) {
            e.printStackTrace ();
        }
    }
}
