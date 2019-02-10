package jcode;

import objects.Question;

import java.io.IOException;

public class ImportQuestions {
    
    private MySqlCon sql;
    
    private final static String CHEM = "data/chemistry.txt",
            BIO = "data/bio.txt",
            PHY = "data/physics.txt",
            GEN = "data/generalKnowledge.txt";
    
    public ImportQuestions() {
        sql = new MySqlCon();
//        importQuestions(CHEM);
//        importQuestions(BIO);
        importQuestions(PHY);
//        importQuestions(GEN);
    }
    
    private void importQuestions(String sub) {
        String readFile = null;
        try {
            readFile = FileHelper.readFileText(sub);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String lines[] = readFile.split(";");
        for (String line : lines) {
            try {
                String[] q = line.split("\\^");
                Question question = new Question();
//                System.out.println(q[0]);
//                System.out.println(q[0].length());
//                if (q[0].length() == 16) {
//                    q[0] = q[0].substring(2);
//                    System.out.println(q[0]);
//                }
                question.setCategory(q[0]);
                question.setQuestion(q[1]);
                question.setAnswer(q[2]);
                question.setHintone(q[3]);
                question.setHinttwo(q[4]);
                question.setHintthree(q[5]);
                System.out.println(question);
                sql.insertQuestion(question);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]) {
        new ImportQuestions();
    }
    
    
}
