package rsreu.workcours.nbaprediction.data.dao;

import rsreu.workcours.nbaprediction.data.Bettor;

import java.sql.SQLException;
import java.util.List;

public interface BettorDAO {
    List<Bettor> getAllBettors() throws SQLException;
    Bettor getBettorByIdUser(int idUser) throws SQLException;
    void updateBettor(Bettor bettor) throws  SQLException;
    void deleteBettorById(int id) throws SQLException;
}
