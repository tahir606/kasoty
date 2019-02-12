package question;

import com.jfoenix.controls.JFXButton;
import dashboard.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import jcode.FileHelper;
import jcode.MySqlCon;
import objects.Question;
import objects.Team;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    
    @FXML
    private Label label_category, label_timer;
    @FXML
    private TextArea  label_question;
    @FXML
    private JFXButton btn_hint1, btn_hint2, btn_hint3, btn_reveal_answer, btn_correctAnswer, btn_incorrectAnswer, btn_back;
    
    private FileHelper fileHelper;
    private MySqlCon sql;
    
    private Question question = null;
    
    private int score = 0,
            roundNo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        fileHelper = new FileHelper();
        sql = new MySqlCon();
        
        roundNo = fileHelper.readRoundNo();
        
        score = 50;
        
        String category = fileHelper.readCategorySelected();
        Team team = fileHelper.readCurrentTeamDets();
        try {
            question = sql.getRandomUnusedQuestion(getCategory(category));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        label_question.setText(question.getQuestion());
        
        btn_hint1.setOnAction(event -> getHint(1));
        btn_hint2.setOnAction(event -> getHint(2));
        if (roundNo == 1) {
            btn_hint3.setVisible(true);
            btn_hint3.setOnAction(event -> getHint(3));
        }
        
        btn_correctAnswer.setOnAction(event -> {
            sql.updateScoreInTeam(team, score);
            goBack();
        });
        
        btn_incorrectAnswer.setOnAction(event -> goBack());
        
        btn_reveal_answer.setOnAction(event -> btn_reveal_answer.setText(question.getAnswer()));
        
        label_category.setText(category);
        
        startTimer();

        btn_back.setOnAction(event -> {
            Stage stage = (Stage) btn_back.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("scoreboard/scoreboard.fxml"));
            Parent root1 = null;
            try {
                root1 = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stageN = new Stage();
            stageN.setTitle("Scoreboard");
            stageN.setScene(new Scene(root1, Controller.UNIVERSAL_WIDTH, Controller.UNIVERSAL_HEIGHT));
            stageN.show();
        });
        
    }
    
    private void goBack() {
        Stage stage = (Stage) btn_incorrectAnswer.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource
                ("scoreboard/scoreboard.fxml"));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stageN = new Stage();
        stageN.setTitle("Scoreboard");
        stageN.setScene(new Scene(root1, Controller.UNIVERSAL_WIDTH, Controller.UNIVERSAL_HEIGHT));
        stageN.show();
    }
    
    private String getCategory(String category) {
        switch (category) {
            case "Physics":
                return "p";
            default:
                return category;
        }
    }
    
    private void getHint(int no) {
        switch (no) {
            case 1: {
                btn_hint1.setText(question.getHintone());
                break;
            }
            case 2: {
                btn_hint2.setText(question.getHinttwo());
                break;
            }
            case 3: {
                btn_hint3.setText(question.getHintthree());
                break;
            }
        }
        if (roundNo == 1) { //Round 2 has -20
            score = score - 10;
        } else {
            score = score - 20;
        }
    }
    
    int time;
    
    private void startTimer() {
        
        if (roundNo == 1)
            time = 40;
        else
            time = 30;
        
        new Thread(() -> {
            for (int i = time; i >= 1; i--) {
                int finalI = i;
                Platform.runLater(() -> label_timer.setText(String.valueOf(finalI)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            btn_correctAnswer.setDisable(true);
        }).start();
    }
}
