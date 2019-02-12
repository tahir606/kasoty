import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int UNIVERSAL_WIDTH = 700, UNIVERSAL_HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("dashboard/dashboard.fxml"));
        primaryStage.setTitle("Kasoty");
        primaryStage.setScene(new Scene(root, UNIVERSAL_WIDTH, UNIVERSAL_HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
