package category;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jcode.FileHelper;
import objects.Team;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    
    @FXML
    private Label label_tName;
    @FXML
    private JFXButton btn_physics, btn_chemistry, btn_maths, btn_bio, btn_genKnowledge;

    private FileHelper fileHelper;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        fileHelper = new FileHelper();
        
        Team team = fileHelper.readCurrentTeamDets();

        label_tName.setText(String.valueOf(team));

        btn_physics.setOnAction(event -> selectCategory("Physics"));
        btn_chemistry.setOnAction(event -> selectCategory("Chemistry"));
        btn_maths.setOnAction(event -> selectCategory("Maths"));
        btn_bio.setOnAction(event -> selectCategory("Biology"));
        btn_genKnowledge.setOnAction(event -> selectCategory("General Knowledge"));
    
    }

    private void selectCategory(String category) {
        fileHelper.writeCategorySelected(category);

        Stage stage = (Stage) btn_physics.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource
                ("question/question.fxml"));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stageN = new Stage();
        stageN.setTitle("Answer Question");
        stageN.setScene(new Scene(root1, 600, 400));
        stageN.show();
    }


}
