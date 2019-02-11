package objects;

public class Question {

    private int code, category;
    private String question, answer, hintone, hinttwo, hintthree;
    private boolean used;
    
    public Question() {
    }
    
    @Override
    public String toString() {
        return "Question{" +
                "category='" + category + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", hintone='" + hintone + '\'' +
                ", hinttwo='" + hinttwo + '\'' +
                ", hintthree='" + hintthree + '\'' +
                ", used=" + used +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public String getHintone() {
        return hintone;
    }
    
    public void setHintone(String hintone) {
        this.hintone = hintone;
    }
    
    public String getHinttwo() {
        return hinttwo;
    }
    
    public void setHinttwo(String hinttwo) {
        this.hinttwo = hinttwo;
    }
    
    public String getHintthree() {
        return hintthree;
    }
    
    public void setHintthree(String hintthree) {
        this.hintthree = hintthree;
    }
    
    public boolean isUsed() {
        return used;
    }
    
    public void setUsed(boolean used) {
        this.used = used;
    }
}
