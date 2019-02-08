package jcode;

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

    public List<Team> getTeams() throws SQLException {
        String query = "SELECT TNO, TNAME FROM SCOREBOARD WHERE 1";

        List<Team> teams = new ArrayList<>();

        try {
            PreparedStatement statement = static_connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Team team = new Team(set.getInt("TNO"), set.getString("TNAME"));
                teams.add(team);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return teams;
    }


}
