package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.dao.TeamDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OracleTeamDAO implements TeamDAO {

    private Connection connection;

    public OracleTeamDAO(Connection connection) {
        this.connection = connection;
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<Integer, String> getAllNbaTeams() throws SQLException {
        Map<Integer, String> nbaTeams = new HashMap<Integer, String>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT id_team, team_name FROM teams WHERE id_sport=2");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_team");
                    String teamStr = resultSet.getString("team_name");
                    nbaTeams.put(id, teamStr);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get nba teams");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return nbaTeams;
    }

    @Override
    public String getTeamNameById(int id) throws SQLException {
        String team = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT team_name FROM teams WHERE id_team=?");
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    team = resultSet.getString("team_name");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get team name by id");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return team;
    }
}
