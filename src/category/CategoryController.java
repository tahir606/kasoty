package category;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import jcode.FileHelper;
import objects.Team;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    
    @FXML
    private Label label_tName;
    
    private FileHelper fileHelper;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        fileHelper = new FileHelper();
        
        Team team = fileHelper.readCurrentTeamDets();
        
        label_tName.setText(String.valueOf(team));
    
    }
}
