package lk.sliit.TropicoMushrooms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //--- Catch fxml file
        URL resource = this.getClass().getResource("/lk/sliit/TropicoMushrooms/view/LoginForm.fxml");

        //--- load
        Parent load = FXMLLoader.load(resource);

        //--- Create Scene
        Scene scene = new Scene(load);

        //--- To remove Title Bar
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);


        //--- Set stage
        primaryStage.setScene(scene);

        //--- Show
        primaryStage.show();

        primaryStage.setTitle("Tropico Mushrooms | Sale Management System");
    }
}
