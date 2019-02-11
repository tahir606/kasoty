package objects;

public class Team {

    private int no, score;
    private String name;
    private boolean phy, chem, bio, math, genknow, inventor, invention;

    public Team() {
    }

    @Override
    public String toString() {
        return no + "-" + name;
    }

    public Team(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public Team(int no, String name, int score) {
        this.no = no;
        this.score = score;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPhy() {
        return phy;
    }

    public void setPhy(boolean phy) {
        this.phy = phy;
    }

    public boolean isChem() {
        return chem;
    }

    public void setChem(boolean chem) {
        this.chem = chem;
    }

    public boolean isBio() {
        return bio;
    }

    public void setBio(boolean bio) {
        this.bio = bio;
    }

    public boolean isMath() {
        return math;
    }

    public void setMath(boolean math) {
        this.math = math;
    }

    public boolean isGenknow() {
        return genknow;
    }

    public void setGenknow(boolean genknow) {
        this.genknow = genknow;
    }

    public boolean isInventor() {
        return inventor;
    }

    public void setInventor(boolean inventor) {
        this.inventor = inventor;
    }

    public boolean isInvention() {
        return invention;
    }

    public void setInvention(boolean invention) {
        this.invention = invention;
    }
}
