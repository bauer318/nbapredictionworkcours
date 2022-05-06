package rsreu.workcours.nbaprediction.moderator.logic;

import rsreu.workcours.nbaprediction.data.*;
import rsreu.workcours.nbaprediction.data.DefensiveRanting;
import rsreu.workcours.nbaprediction.data.OffensiveRanting;
import rsreu.workcours.nbaprediction.data.Ranting;
import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.dao.DefensiveRantingDAO;
import rsreu.workcours.nbaprediction.data.dao.OffensiveRantingDAO;
import rsreu.workcours.nbaprediction.data.dao.RantingDAO;
import rsreu.workcours.nbaprediction.decimal.DecimalFormater;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RantingLogic {
    private static List<Ranting> rantings;

    public static List<Ranting> getAllRantings(Date date) {
        List<Ranting> rantings = new ArrayList<Ranting>();
        Ranting ranting = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            RantingDAO rantingDAO = factory.getRatingDAO();
            for (int i = 1; i <= 30; i++) {
                ranting = rantingDAO.getTeamRantings(i,date);
                if (ranting != null) {
                    rantings.add(ranting);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rantings;
    }

    public static List<Ranting> getAllRantings() {
        List<Ranting> rantings = new ArrayList<Ranting>();
        Ranting ranting = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            RantingDAO rantingDAO = factory.getRatingDAO();
            for (int i = 1; i <= 30; i++) {
                ranting = rantingDAO.getTeamRantings(i);
                if (ranting != null) {
                    rantings.add(ranting);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rantings;
    }

    private static Map<Integer, Double> getPartialOffensiveAveargeTeams() {
        Map<Integer, Double> mapAverage = new HashMap<Integer, Double>();
        int size = rantings.size();
        for (int i = 0; i < size; i++) {
            int id_team = rantings.get(i).getIdTeam();
            int totalPoints = rantings.get(i).getTotalPoints();
            int numberMatch = rantings.get(i).getMatchNumber();
            double offensiveAverage = (double) totalPoints / numberMatch;
            offensiveAverage = DecimalFormater.aroundDoubleToOnePlace(offensiveAverage);
            mapAverage.put(id_team, offensiveAverage);
        }
        return mapAverage;
    }

    private static Map<Integer, Double> getPartialDefensiveAverageTeams() {
        Map<Integer, Double> mapAverage = new HashMap<Integer, Double>();
        int size = rantings.size();
        for (int i = 0; i < size; i++) {
            int id_team = rantings.get(i).getIdTeam();
            int totalOpponentsPoints = rantings.get(i).getTotalOpponentsPoints();
            int numberMatch = rantings.get(i).getMatchNumber();
            double defensiveAverage = (double) totalOpponentsPoints / numberMatch;
            defensiveAverage = DecimalFormater.aroundDoubleToOnePlace(defensiveAverage);
            mapAverage.put(id_team, defensiveAverage);
        }
        return mapAverage;
    }

    public static Map<Integer, Double> getFullOffensiveAverageTeams() {
        Map<Integer, Double> mapAverage = new HashMap<Integer, Double>();
        Map<Integer, Double> partielOffensiveAverage = getPartialOffensiveAveargeTeams();
        for (int i = 1; i <= 30; i++) {
            if (partielOffensiveAverage.containsKey(i)) {
                mapAverage.put(i, partielOffensiveAverage.get(i));
            } else {
                mapAverage.put(i, 0.0);
            }
        }
        return mapAverage;
    }

    public static Map<Integer, Double> getFullDefensiveAverageTeams() {
        Map<Integer, Double> mapAverage = new HashMap<Integer, Double>();
        Map<Integer, Double> partialODefensiveAverage = getPartialDefensiveAverageTeams();
        for (int i = 1; i <= 30; i++) {
            if (partialODefensiveAverage.containsKey(i)) {
                mapAverage.put(i, partialODefensiveAverage.get(i));
            } else {
                mapAverage.put(i, 0.0);
            }
        }
        return mapAverage;
    }

    public static List<OffensiveRanting> getOffensiveRantingByDate(Date date) {
        List<OffensiveRanting> offensiveRantings = new ArrayList<OffensiveRanting>();
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            OffensiveRantingDAO offRantingDAO = factory.getOffensiveRantingDAO();
            offensiveRantings = offRantingDAO.getAllOffensiveRantingSortedByDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offensiveRantings;
    }

    public static List<DefensiveRanting> getDefensiveRantingByDate(Date date) {
        List<DefensiveRanting> defensiveRantings = new ArrayList<DefensiveRanting>();
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            DefensiveRantingDAO defRantingDAO = factory.getDefensiveRantingDAO();
            defensiveRantings = defRantingDAO.getAllDefensiveRantingSortedByDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defensiveRantings;
    }

    private static void insertOffensiveRanting(int idTeam, Date date, String team, double average) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            OffensiveRantingDAO offRantingDAO = factory.getOffensiveRantingDAO();
            offRantingDAO.insertOffensiveRanting(idTeam, date, team, average);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertDefensiveRanting(int idTeam, Date date, String team, double average) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            DefensiveRantingDAO defRantingDAO = factory.getDefensiveRantingDAO();
            defRantingDAO.insertDefensiveRanting(idTeam, date, team, average);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isNotExistingOffensiveRanting(int idTeam, Date date) {
        OffensiveRanting offensiveRanting = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            OffensiveRantingDAO offRantingDAO = factory.getOffensiveRantingDAO();
            offensiveRanting = offRantingDAO.getOffensiveRantingByTeamDate(idTeam, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (offensiveRanting == null);
    }

    private static void updateOffensiveRanting(int idTeam, Date date, double average) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            OffensiveRantingDAO offRantingDAO = factory.getOffensiveRantingDAO();
            offRantingDAO.updateOffensiveRanting(idTeam, date, average);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertNewOffensiveRanting(Map<Integer, Double> fullMapOffensiveAverage, Date date) {
        for (int i = 1; i <= 30; i++) {
            String teamName = AddMatchLogic.getTeamNameById(i);
            double average = fullMapOffensiveAverage.get(i);
            if (isNotExistingOffensiveRanting(i, date)) {
                insertOffensiveRanting(i, date, teamName, average);
            } else {
                updateOffensiveRanting(i, date, average);
            }
        }
    }

    private static boolean isNotExistingDefensiveRanting(int idTeam, Date date) {
        DefensiveRanting defRanting = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            DefensiveRantingDAO defRantingDAO = factory.getDefensiveRantingDAO();
            defRanting = defRantingDAO.getDefensiveRantingByTeamDate(idTeam, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (defRanting == null);
    }

    private static void updateDefensiveRanting(int idTeam, Date date, double average) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            DefensiveRantingDAO defRantingDAO = factory.getDefensiveRantingDAO();
            defRantingDAO.updateDefensiveRanting(idTeam, date, average);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertNewDefensiveRanting(Map<Integer, Double> fullMapDefensiveAverage, Date date) {
        for (int i = 1; i <= 30; i++) {
            String teamName = AddMatchLogic.getTeamNameById(i);
            double average = fullMapDefensiveAverage.get(i);
            if (isNotExistingDefensiveRanting(i, date)) {
                insertDefensiveRanting(i, date, teamName, average);
            } else {
                updateDefensiveRanting(i, date, average);
            }

        }
    }

    public static Map<Integer, Integer> getMapOffensiveRanking(List<OffensiveRanting> offensiveRankings) {
        Map<Integer, Integer> mapRes = new HashMap<Integer, Integer>();
        int size = offensiveRankings.size();
        if (!offensiveRankings.isEmpty()) {
            for (int i = 0; i < size; i++) {
                mapRes.put(offensiveRankings.get(i).getIdTeam(), i + 1);
            }
        }
        return mapRes;
    }

    public static Map<Integer, Integer> getMapDefensiveRanking(List<DefensiveRanting> defensiveRantings) {
        Map<Integer, Integer> mapRes = new HashMap<Integer, Integer>();
        int size = defensiveRantings.size();
        if (!defensiveRantings.isEmpty()) {
            for (int i = 0; i < size; i++) {
                mapRes.put(defensiveRantings.get(i).getIdTeam(), i + 1);
            }
        }
        return mapRes;
    }

    public static Ranting getLastRantingByTeamIdBeforeDate(int idTeam, Date date) {
        Ranting ranting = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            RantingDAO rantingDAO = factory.getRatingDAO();
            ranting = rantingDAO.getTeamRantings(idTeam, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ranting;
    }

    public static Ranting getLastRantingByTeamId(int idTeam) {
        Ranting ranting = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            RantingDAO rantingDAO = factory.getRatingDAO();
            ranting = rantingDAO.getTeamRantings(idTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ranting;
    }

    public static void insertNewRanking(int idTeam, Date date, int numberMatch, int offensive, int defensive,
                                        int totalPoints, int totalOpponentsPoints) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            RantingDAO rantingDAO = factory.getRatingDAO();
            rantingDAO.insertNewRantingTeam(idTeam, date, numberMatch, offensive, defensive, totalPoints,
                    totalOpponentsPoints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRanking(int idTeam, Date date, int offensive, int defensive) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            RantingDAO rantingDAO = factory.getRatingDAO();
            rantingDAO.updateRantingByIdTeamRantingDate(idTeam, date, offensive, defensive);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRankingByIdTeamRantingDate(int idTeam, Date date, int numberMatch, int offensive, int defensive,
                                                        int totalPoints, int totalOpponentsPoints) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            RantingDAO rantingDAO = factory.getRatingDAO();
            rantingDAO.updateRantingByIdTeamRantingDate(idTeam, date, numberMatch, offensive, defensive, totalPoints, totalOpponentsPoints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNotExistingRanting(int idTeam, Date ranting_date) {
        Ranting ranting = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            RantingDAO rantingDAO = factory.getRatingDAO();
            ranting = rantingDAO.getRantingByItTeamRantingDate(idTeam, ranting_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ranting == null);
    }

    public static void setRantings(List<Ranting> rantings) {
        RantingLogic.rantings = rantings;
    }

    public static List<Ranting> getRantings() {
        return rantings;
    }
}
