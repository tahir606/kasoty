package jcode;

import objects.Question;

import java.io.IOException;

public class ImportQuestions {

    private MySqlCon sql;

    private final static String CHEM = "data/chemistry.txt",
            BIO = "data/bio.txt",
            PHY = "data/physics.txt",
            MATH = "data/maths.txt",
            MATH2 = "data/maths2.txt",
            GEN = "data/gen.txt",
            INVENTORS = "data/inventors.txt",
            INVENTIONS = "data/inventions.txt";

    public ImportQuestions() {
        sql = new MySqlCon();
//        importQuestions(CHEM);
//        importQuestions(BIO);
//        importQuestions(PHY);
//        importQuestions(GEN);
        importQuestions(MATH);
        importQuestions(MATH2);
//        importQuestions(MATH2);
//        importQuestions(INVENTORS);
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
                int cat = GenTasks.returnCategoryNumber(q[0]);
                question.setCategory(cat);
                question.setQuestion(q[1]);
                question.setAnswer(q[2]);
                question.setHintone(q[3]);
                question.setHinttwo(q[4]);
                if (cat < 6)
                    question.setHintthree(q[5]);
                else
                    question.setHintthree("");
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
