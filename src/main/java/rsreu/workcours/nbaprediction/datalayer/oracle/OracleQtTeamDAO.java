package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.dao.ConnectionCloser;
import rsreu.workcours.nbaprediction.data.dao.QtTeamDAO;
import rsreu.workcours.nbaprediction.data.QtTeam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleQtTeamDAO implements QtTeamDAO {
    private Connection connection;

    public OracleQtTeamDAO(Connection connection) {
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
    public void insertQtTeam(int id_match, int id_team) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO nbaQtTeamsMod (id_match, id_team) VALUES (?,?)");
            preparedStatement.setInt(1, id_match);
            preparedStatement.setInt(2, id_team);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to insert Qt Team");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public List<QtTeam> getQtTeamByMatchDate(Date date) throws SQLException {
        List<QtTeam> qtTeams = new ArrayList<QtTeam>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbaQtTeamsMod WHERE id_match IN (SELECT id_match FROM nbaMatchsMod WHERE TRUNC (match_date)=?) ORDER BY id_match ASC");
            preparedStatement.setDate(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idMatch = resultSet.getInt("id_match");
                    int idTeam = resultSet.getInt("id_team");
                    int qt1 = resultSet.getInt("qt1");
                    int qt2 = resultSet.getInt("qt2");
                    int qt3 = resultSet.getInt("qt3");
                    int qt4 = resultSet.getInt("qt4");
                    int qt5 = resultSet.getInt("qt5");
                    QtTeam qtTeam = new QtTeam(idMatch, idTeam, qt1, qt2, qt3, qt4, qt5);
                    qtTeams.add(qtTeam);
                }
            }
        } catch (Exception e) {
            throw new SQLException("fail to get qt team");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return qtTeams;
    }

    @Override
    public QtTeam getQtTeamByIdTeamIdMatch(int idTeam, int idMatch) throws SQLException {
        QtTeam qtTeam = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM nbaQtTeamsMod WHERE id_team=? AND id_match=?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setInt(2, idMatch);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_Match = resultSet.getInt("id_match");
                    int id_Team = resultSet.getInt("id_team");
                    int qt1 = resultSet.getInt("qt1");
                    int qt2 = resultSet.getInt("qt2");
                    int qt3 = resultSet.getInt("qt3");
                    int qt4 = resultSet.getInt("qt4");
                    int qt5 = resultSet.getInt("qt5");
                    qtTeam = new QtTeam(id_Match, id_Team, qt1, qt2, qt3, qt4, qt5);

                }
            }
        } catch (Exception e) {
            throw new SQLException("fail to get qt team");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return qtTeam;
    }

    @Override
    public void updateQtTeam(int id_match, int id_team, int qt1, int qt2, int qt3, int qt4, int qt5, int newIdMatch)
            throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE nbaQtTeamsMod SET qt1=?, qt2=?, qt3=?, qt4=?, qt5=?, id_team=? WHERE id_match=? AND id_team=?");
            preparedStatement.setInt(1, qt1);
            preparedStatement.setInt(2, qt2);
            preparedStatement.setInt(3, qt3);
            preparedStatement.setInt(4, qt4);
            preparedStatement.setInt(5, qt5);
            preparedStatement.setInt(6, newIdMatch);
            preparedStatement.setInt(7, id_match);
            preparedStatement.setInt(8, id_team);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update Qt Teams");
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    public void updateQtTeam(int id_match, int id_team, int qt1, int qt2, int qt3, int qt4, int qt5)
            throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE nbaQtTeamsMod SET qt1=?, qt2=?, qt3=?, qt4=?, qt5=? WHERE id_match=? AND id_team=?");
            preparedStatement.setInt(1, qt1);
            preparedStatement.setInt(2, qt2);
            preparedStatement.setInt(3, qt3);
            preparedStatement.setInt(4, qt4);
            preparedStatement.setInt(5, qt5);
            preparedStatement.setInt(6, id_match);
            preparedStatement.setInt(7, id_team);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update Qt Teams");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    /*
     * Most points in Qt4 then average Qt1, Qt2 and Qt3
     */
    @Override
    public int countTeamMatchWithMostQt4BeforeDate(int idTeam, Date date) throws SQLException {
        int count = 1;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT count(id_team) AS totalCount FROM nbaQtTeamsMod WHERE id_team=? AND ((qt1+qt2+qt3)/3)<qt4 AND (qt1+qt2+qt3)!=0 AND id_match "
                            + "IN (SELECT id_match FROM nbaMatchsMod WHERE TRUNC (match_date)<?)");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("totalCount");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to count most qt4");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return count;
    }

    @Override
    public int countTeamMatchWithQt4BiggestThanAvg(int idTeam, double avg, Date date) throws SQLException {
        int count = 1;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT count(id_team) AS totalCount FROM nbaQtTeamsMod WHERE id_team=? AND qt4>=? AND id_match IN(SELECT id_match FROM nbaMatchsMod WHERE TRUNC (match_date) <?)");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDouble(2, avg);
            preparedStatement.setDate(3, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt("totalCount");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to count most qt4");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return count;
    }

    @Override
    public List<Integer> getListIdMatchWithBiggestQt4ThanAvg(int idTeam, double avg) throws SQLException {
        List<Integer> listIdMatch = new ArrayList<Integer>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT id_match FROM nbaQtTeamsMod WHERE id_team=? AND qt4>=?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDouble(2, avg);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idMatch = resultSet.getInt("id_match");
                    listIdMatch.add(idMatch);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get list Id");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return listIdMatch;
    }

    @Override
    public List<QtTeam> getListQtTeamByIdMatch(int idMatch) throws SQLException {
        List<QtTeam> qtTeams = new ArrayList<QtTeam>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM nbaQtTeamsMod WHERE id_match=?");
            preparedStatement.setInt(1, idMatch);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_match = resultSet.getInt("id_match");
                    int idTeam = resultSet.getInt("id_team");
                    int qt1 = resultSet.getInt("qt1");
                    int qt2 = resultSet.getInt("qt2");
                    int qt3 = resultSet.getInt("qt3");
                    int qt4 = resultSet.getInt("qt4");
                    int qt5 = resultSet.getInt("qt5");
                    QtTeam qtTeam = new QtTeam(id_match, idTeam, qt1, qt2, qt3, qt4, qt5);
                    qtTeams.add(qtTeam);
                }
            }
            ;
        } catch (SQLException e) {
            throw new SQLException("Fail to get Qt Team By idMatch");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return qtTeams;
    }

    @Override
    public double getQt13Average(int idTeam, Date date) throws SQLException {
        double avgQt1 = 1;
        double avgQt2 = 1;
        double avgQt3 = 1;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT AVG(qt1) AS AvgQt1 FROM nbaQtTeamsMod WHERE id_team=? AND id_match IN (SELECT id_match FROM nbaMatchsMod WHERE TRUNC (match_date) <?)");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    avgQt1 = resultSet.getDouble("AvgQt1");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get Qt1 AVG");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT AVG(qt2) AS AvgQt2 FROM nbaQtTeamsMod WHERE id_team=? AND id_match IN (SELECT id_match FROM nbaMatchsMod WHERE TRUNC (match_date) <?)");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    avgQt2 = resultSet.getDouble("AvgQt2");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get Qt2 AVG");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT AVG(qt3) AS AvgQt3 FROM nbaQtTeamsMod WHERE id_team=? AND id_match IN (SELECT id_match FROM nbaMatchsMod WHERE TRUNC (match_date) <?)");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    avgQt3 = resultSet.getDouble("AvgQt3");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get Qt3 AVG");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return (avgQt1 + avgQt2 + avgQt3) / 3;
    }

    @Override
    public void deleteQtTeam(int id_match, int id_team) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("DELETE FROM nbaQtTeamsMod WHERE id_match = ? AND id_team = ?");
            preparedStatement.setInt(1, id_match);
            preparedStatement.setInt(2,id_team);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to delete qt team by idMatch and idTeam");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
    }

}
