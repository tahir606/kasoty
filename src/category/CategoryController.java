package category;

import com.jfoenix.controls.JFXButton;
import dashboard.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jcode.FileHelper;
import jcode.GenTasks;
import jcode.MySqlCon;
import objects.Team;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private Label label_tName;
    @FXML
    private JFXButton btn_physics, btn_chemistry, btn_maths, btn_bio, btn_genKnowledge, btn_inventor, btn_inventions;

    private FileHelper fileHelper;
    private MySqlCon sqlCon;

    private Team team;
    
    private int roundno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileHelper = new FileHelper();
        sqlCon = new MySqlCon();

        roundno = fileHelper.readRoundNo();
        
        team = fileHelper.readCurrentTeamDets();
        team = sqlCon.getCategoriesUsed(team);

        label_tName.setText(String.valueOf(team));

        if (roundno == 1) {
            btn_physics.setVisible(true);
            btn_chemistry.setVisible(true);
            btn_bio.setVisible(true);
            btn_maths.setVisible(true);
            btn_genKnowledge.setVisible(true);
        } else {
            btn_inventor.setVisible(true);
            btn_inventions.setVisible(true);
        }
        
        if (team.isPhy())
            btn_physics.setDisable(true);
        if (team.isChem())
            btn_chemistry.setDisable(true);
        if (team.isBio())
            btn_bio.setDisable(true);
        if (team.isMath())
            btn_maths.setDisable(true);
        if (team.isGenknow())
            btn_genKnowledge.setDisable(true);
        if (team.isInventor())
            btn_inventor.setDisable(true);
        if (team.isInvention())
            btn_inventions.setDisable(true);

        btn_physics.setOnAction(event -> selectCategory("Physics"));
        btn_chemistry.setOnAction(event -> selectCategory("Chemistry"));
        btn_maths.setOnAction(event -> selectCategory("Maths"));
        btn_bio.setOnAction(event -> selectCategory("Biology"));
        btn_genKnowledge.setOnAction(event -> selectCategory("General Knowledge"));
        btn_inventor.setOnAction(event -> selectCategory("Inventor"));
        btn_inventions.setOnAction(event -> selectCategory("Inventions"));

    }

    private void selectCategory(String category) {
        fileHelper.writeCategorySelected(GenTasks.returnCategoryNumber(category));
        sqlCon.updateCategoryUsed(team, GenTasks.returnCategoryNumber(category));

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
        stageN.setScene(new Scene(root1, Controller.UNIVERSAL_WIDTH, Controller.UNIVERSAL_HEIGHT));
        stageN.show();
    }


}
