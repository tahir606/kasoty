package jcode;

import objects.Question;
import objects.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCon {

    private static Connection static_connection;

    public MySqlCon() {
        if (static_connection == null)
            static_connection = createConnection();
    }

    private Connection createConnection() {
        String URL = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "kasoty",
                USER = "crm",
                PASSWORD = "crm123!@#";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveTeamName(Team team) throws SQLException {
        String query = "INSERT INTO SCOREBOARD(TNO, TNAME) VALUES(?,?)";

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            statement.setInt(1, team.getNo());
            statement.setString(2, team.getName());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void saveTeamNames(List<Team> teams) throws SQLException {
        for (Team team : teams) {
            saveTeamName(team);
        }
    }

    public List<Team> getTeams(String orderBy, String limit) throws SQLException {
        String query = "SELECT TNO, TNAME, TSCORE FROM SCOREBOARD WHERE 1 " + orderBy + " "  + limit;

        List<Team> teams = new ArrayList<>();

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Team team = new Team(set.getInt("TNO"), set.getString("TNAME"), set.getInt("TSCORE"));
                teams.add(team);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return teams;
    }

    public void insertQuestion(Question question) throws SQLException {
        String query = "INSERT INTO QUESTIONS (QNO, CATEGORY, QUESTION, ANSWER, HINTONE, HINTTWO, HINTTHREE) " +
                " SELECT IFNULL(MAX(QNO),0)+1,?,?,?,?,?,? FROM QUESTIONS";

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            statement.setInt(1, question.getCategory());
            statement.setString(2, question.getQuestion());
            statement.setString(3, question.getAnswer());
            statement.setString(4, question.getHintone());
            statement.setString(5, question.getHinttwo());
            statement.setString(6, question.getHintthree());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public Question getRandomUnusedQuestion(String category) throws SQLException {

        String query = "SELECT QNO, QUESTION, ANSWER, HINTONE, HINTTWO, HINTTHREE FROM questions " +
                "WHERE CATEGORY LIKE '%" + category + "%' " +
                "AND USED = 0 " +
                "ORDER BY RAND() " +
                "LIMIT 1 ";

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Question question = new Question();
                question.setCode(set.getInt("QNO"));
                question.setQuestion(set.getString("QUESTION"));
                question.setAnswer(set.getString("ANSWER"));
                question.setHintone(set.getString("HINTONE"));
                question.setHinttwo(set.getString("HINTTWO"));
                question.setHintthree(set.getString("HINTTHREE"));

                markQuestionUsed(question);
                return question;
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return getRandomUsedQuestion(category);
        }

        return getRandomUsedQuestion(category);

    }

    public Question getRandomUsedQuestion(String category) throws SQLException {
        
        System.out.println("Trying to get already used questions");

        String query = "SELECT QNO, QUESTION, ANSWER, HINTONE, HINTTWO, HINTTHREE FROM questions " +
                "WHERE CATEGORY LIKE '%" + category + "%' " +
                "ORDER BY RAND() " +
                "LIMIT 1 ";

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Question question = new Question();
                question.setCode(set.getInt("QNO"));
                question.setQuestion(set.getString("QUESTION"));
                question.setAnswer(set.getString("ANSWER"));
                question.setHintone(set.getString("HINTONE"));
                question.setHinttwo(set.getString("HINTTWO"));
                question.setHintthree(set.getString("HINTTHREE"));

                markQuestionUsed(question);
                return question;
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return null;

    }


    private void markQuestionUsed(Question question) {
        
        String query = "UPDATE QUESTIONS SET USED = 1 WHERE QNO = ?";

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            statement.setInt(1, question.getCode());

            statement.executeUpdate();

            System.out.println("marked q: " + question.getCode() + " as used");
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateScoreInTeam(Team team, int score) {

        score = score + getCurrentScoreInTeam(team);

        String query = "UPDATE SCOREBOARD SET TSCORE = ? WHERE TNO = ?";

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            statement.setInt(1, score);
            statement.setInt(2, team.getNo());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentScoreInTeam(Team team) {

        String query = "SELECT IFNULL(TSCORE,0) AS TSCORE FROM scoreboard where TNO = ?";

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            statement.setInt(1, team.getNo());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                return set.getInt("TSCORE");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }


    public void updateCategoryUsed(Team team, int category) {

        System.out.println(category);
        String query = "UPDATE SCOREBOARD SET " + GenTasks.returnCategoryNameScoreboard(category) + "  = 1 WHERE TNO = ?";
        System.out.println(query);

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            statement.setInt(1, team.getNo());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Team getCategoriesUsed(Team team) {

        String query = "SELECT PHY, CHEM, BIO, MATH, GENKNOW, INVENTOR, INVENTION FROM scoreboard where TNO = ?";

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            statement.setInt(1, team.getNo());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                team.setPhy(set.getBoolean("PHY"));
                team.setChem(set.getBoolean("CHEM"));
                team.setBio(set.getBoolean("BIO"));
                team.setMath(set.getBoolean("MATH"));
                team.setGenknow(set.getBoolean("GENKNOW"));
                team.setInventor(set.getBoolean("INVENTOR"));
                team.setInvention(set.getBoolean("INVENTION"));

                return team;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return team;
    }
}
