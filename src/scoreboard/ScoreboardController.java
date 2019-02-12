package scoreboard;

import com.jfoenix.controls.JFXButton;
import dashboard.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jcode.FileHelper;
import jcode.MySqlCon;
import objects.Team;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {

    @FXML
    private VBox vbox_teams;
    @FXML
    private JFXButton btn_back;

    private MySqlCon sqlCon;
    private FileHelper fileHelper;

    private List<Team> teams;

    private int roundNo,
    round2TeamNo = 20;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileHelper = new FileHelper();
        sqlCon = new MySqlCon();

        roundNo = fileHelper.readRoundNo();

        try {
            if (roundNo == 1)
                teams = sqlCon.getTeams("", "");
            else
                teams = sqlCon.getTeams(" order by TSCORE DESC", " LIMIT " + round2TeamNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Team team : teams) {
            HBox hBox = new HBox();
            hBox.setSpacing(15);
            JFXButton teamBtn = new JFXButton(team.getNo() + " - " + team.getName());
//            teamBtn.setStyle("-fx-alignment: LEFT; -fx-background-color: #fcfcfc");
            teamBtn.setAlignment(Pos.CENTER_LEFT);
            teamBtn.setButtonType(JFXButton.ButtonType.FLAT);
            teamBtn.setMinWidth(200);
            teamBtn.setMinHeight(30);
            teamBtn.setFont(new Font(16));
            teamBtn.setOnAction(event -> {
                fileHelper.deleteCurrentTeamDets();
                fileHelper.writeCurrentTeamDets(team);

                Stage stage = (Stage) teamBtn.getScene().getWindow();
                stage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource
                        ("category/category.fxml"));
                Parent root1 = null;
                try {
                    root1 = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stageN = new Stage();
                stageN.setTitle("Choose Category");
                stageN.setScene(new Scene(root1, Controller.UNIVERSAL_WIDTH, Controller.UNIVERSAL_HEIGHT));
                stageN.show();
            });

            Label label = new Label("Score: " + team.getScore());
            label.setStyle("-fx-text-fill: purple;");
            label.setFont(new Font(18));

            hBox.getChildren().addAll(teamBtn, label);
            vbox_teams.getChildren().add(hBox);
        }

        btn_back.setOnAction(event -> {
            Stage stage = (Stage) btn_back.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("dashboard/dashboard.fxml"));
            Parent root1 = null;
            try {
                root1 = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stageN = new Stage();
            stageN.setTitle("Dashboard");
            stageN.setScene(new Scene(root1, Controller.UNIVERSAL_WIDTH, Controller.UNIVERSAL_HEIGHT));
            stageN.show();
        });
    }
}
