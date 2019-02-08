package dashboard;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jcode.MySqlCon;
import objects.Team;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private JFXButton btn_kasoty1, btn_kasoty2;

    private MySqlCon sql;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sql = new MySqlCon();

        btn_kasoty1.setOnAction(event -> {
            System.out.println("Round 1");
            try {
                if (sql.getTeams().size() < 1)
                    teamNames();
                else
                    openScoreBoard(null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btn_kasoty2.setOnAction(event -> {
            System.out.println("Round 2");
        });

    }

    int noOfTeams = 5;

    private void teamNames() {
        AnchorPane root = new AnchorPane();

        VBox vBox = new VBox();
        for (int i = 1; i <= noOfTeams; i++) {
            TextField team = new TextField();
            team.setPromptText("Team " + i);
            team.setId("t_" + i);
            vBox.getChildren().add(team);
        }

        JFXButton btn_save = new JFXButton("Save Teams");
        btn_save.setOnAction(event -> {
            List<Team> teams = new ArrayList<>();
            int counter = 0;
            for (Node node : vBox.getChildren()) {
                if (node instanceof TextField) {
                    counter++;
                    TextField tf = (TextField) node;
                    String name = tf.getText();
                    if (name.equals("")) {
                        return;
                    }
                    teams.add(new Team(counter, name));
                }
            }
            try {
                sql.saveTeamNames(teams);
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            openScoreBoard(btn_save);
        });

        vBox.getChildren().add(btn_save);

        root.getChildren().addAll(vBox);

        ScrollPane sp = new ScrollPane();
        sp.setContent(root);
        sp.setPannable(true);

        Scene scene = new Scene(sp, 200, 300);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Team Names");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void openScoreBoard(JFXButton btn) {
        if (btn != null) {
            Stage stage2 = (Stage) btn.getScene().getWindow();
            stage2.close();
        }

        Stage stage3 = (Stage) btn_kasoty1.getScene().getWindow();
        stage3.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource
                ("scoreboard/scoreboard.fxml"));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Scoreboard");
        stage.setScene(new Scene(root1, 300, 300));
        stage.show();
    }

}
