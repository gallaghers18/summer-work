package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("dots.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dots.fxml"));
        BorderPane root = loader.load();
        Controller controller = loader.getController();


        primaryStage.setTitle("Bouncing Dots");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
