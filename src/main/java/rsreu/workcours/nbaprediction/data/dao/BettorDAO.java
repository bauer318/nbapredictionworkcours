package rsreu.workcours.nbaprediction.data.dao;

import rsreu.workcours.nbaprediction.data.Bettor;

import java.sql.SQLException;
import java.util.List;

public interface BettorDAO {
    List<Bettor> getAllBettors() throws SQLException;
}
