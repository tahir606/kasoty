package jcode;

import objects.Team;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class FileHelper {
    
    private static final String FADD = "settings/",
            TEAMDETS = "currentTeamDets",
            CATEGORY = "categoryChosen";
    
    public FileHelper() {
    }
    
    public boolean writeCurrentTeamDets(Team team) {
        
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(
                    new File(FADD + TEAMDETS));
            
            writer.write(team.getNo() + "^" + team.getName());
            
            return true;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            writer.close();
        }
    }
    
    public Team readCurrentTeamDets() {
        String text = "";
        InputStreamReader isReader = null;
        try {
            isReader =
                    new InputStreamReader(new FileInputStream(new File(FADD + TEAMDETS)));
            BufferedReader br = new BufferedReader(isReader);
            
            text = br.readLine();
            
            if (text == null) {
                return null;
            }
            
            String[] t = text.split("\\^");
            
            return new Team(Integer.parseInt(t[0]), t[1]);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                isReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean deleteCurrentTeamDets() {
        
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(
                    new File(FADD + TEAMDETS));
            
            writer.write("");
            
            return true;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            writer.close();
        }
    }
    
    public boolean writeCategorySelected(String category) {
        
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(
                    new File(FADD + CATEGORY));
            
            writer.write(category);
            
            return true;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            writer.close();
        }
    }
    
    public String readCategorySelected() {
        String text = "";
        InputStreamReader isReader = null;
        try {
            isReader =
                    new InputStreamReader(new FileInputStream(new File(FADD + CATEGORY)));
            BufferedReader br = new BufferedReader(isReader);
            
            text = br.readLine();
            
            if (text == null) {
                return null;
            }
            
            return text;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                isReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean deleteCategorySelected() {
        
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(
                    new File(FADD + CATEGORY));
            
            writer.write("");
            
            return true;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            writer.close();
        }
    }
    
    public static String readFileText(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath), "utf-8");
    }
}
