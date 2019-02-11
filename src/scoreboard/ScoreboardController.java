package scoreboard;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    private MySqlCon sqlCon;
    private FileHelper fileHelper;

    private List<Team> teams;

    private int roundNo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileHelper = new FileHelper();
        sqlCon = new MySqlCon();

        roundNo = fileHelper.readRoundNo();

        try {
            if (roundNo == 1)
                teams = sqlCon.getTeams("", "");
            else
                teams = sqlCon.getTeams(" order by TSCORE", " LIMIT 2 ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Team team : teams) {
            HBox hBox = new HBox();
            JFXButton teamBtn = new JFXButton(team.getNo() + " - " + team.getName());
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
                stageN.setScene(new Scene(root1, 300, 300));
                stageN.show();
            });

            hBox.getChildren().addAll(teamBtn, new Label("Score: " + team.getScore()));
            vbox_teams.getChildren().add(hBox);
        }
    }
}
