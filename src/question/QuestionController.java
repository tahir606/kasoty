package question;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import jcode.FileHelper;
import objects.Team;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {

    @FXML
    private Label label_category, label_timer, label_question;
    @FXML
    private JFXButton btn_hint1, btn_hint2, btn_hint3, btn_reveal_answer, btn_correctAnswer, btn_incorrectAnswer;

    private FileHelper fileHelper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileHelper = new FileHelper();

        String category = fileHelper.readCategorySelected();
        Team team = fileHelper.readCurrentTeamDets();

        label_category.setText(category);

        startTimer();

    }

    private void startTimer() {
        new Thread(() -> {
            for (int i = 40; i >= 1; i--) {
                int finalI = i;
                Platform.runLater(() -> label_timer.setText(String.valueOf(finalI)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
