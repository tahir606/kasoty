package objects;

public class Team {

    private int no;
    private String name;

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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
