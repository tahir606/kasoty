package scoreboard;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jcode.MySqlCon;
import objects.Team;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {

    @FXML
    private VBox vbox_teams;

    private MySqlCon sqlCon;

    private List<Team> teams;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sqlCon = new MySqlCon();

        try {
            teams = sqlCon.getTeams();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Team team: teams) {
            HBox hBox = new HBox();
            JFXButton button = new JFXButton(team.getNo() + " - " + team.getName());
            hBox.getChildren().addAll(button, new Label("Score: 0"));
            vbox_teams.getChildren().add(hBox);
        }
    }
}
