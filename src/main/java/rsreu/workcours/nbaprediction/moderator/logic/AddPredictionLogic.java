package rsreu.workcours.nbaprediction.moderator.logic;

import rsreu.workcours.nbaprediction.data.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.QtTeamDAO;
import rsreu.workcours.nbaprediction.data.ResultDAO;
import rsreu.workcours.nbaprediction.data.QtTeam;
import rsreu.workcours.nbaprediction.data.Result;
import rsreu.workcours.nbaprediction.data.XY;
import rsreu.workcours.nbaprediction.decimal.DecimalFormater;

import java.sql.Date;
import java.util.*;

public class AddPredictionLogic {
    private static Map<Integer, Result> mapResults = new HashMap<Integer, Result>();
    private static Map<Integer, Double> mapTotalPointsMatch = new HashMap<Integer, Double>();
    private static Map<Integer, List<Result>> mapPrevResultByRegime = new HashMap<Integer, List<Result>>();
    private static Map<Integer, List<List<QtTeam>>> mapListQtTeams = new HashMap<Integer, List<List<QtTeam>>>();
    private static Map<Integer, List<List<Result>>> mapListResults = new HashMap<Integer, List<List<Result>>>();
    private static Map<Integer, XY> mapXY = new HashMap<Integer, XY>();
    private static Map<Integer, Double> mapDecisions = new HashMap<Integer, Double>();
    private static Map<Integer, String> mapConfirmsText = new HashMap<Integer, String>();

    public static Map<Integer, Result> getMapResults() {
        return mapResults;
    }

    public static void setMapResults(Map<Integer, Result> mapResults) {
        AddPredictionLogic.mapResults = mapResults;
    }

    public static double getTotalPointsMatch(int... qt) {
        double total = 0.0;
        for (int i = 0; i < qt.length; i++) {
            total += qt[i];
        }
        return total;
    }

    public static void insertNewResult(int idMatch, int idTeam, double average, short regime, short offensiveRegime,
                                       short deffensiveRegime, short teamOffensive, short opponentDeffensive, double averageDifference,
                                       double averagePercent, double mostQt4Percent, double qt13Average, String decision, int matchQt4,
                                       short observation, int totalMatch, int interestedMatch) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            ResultDAO resultDAO = factory.getResultDAO();
            resultDAO.insertNewResult(idMatch, idTeam, average, regime, offensiveRegime, deffensiveRegime,
                    teamOffensive, opponentDeffensive, averageDifference, averagePercent, mostQt4Percent, qt13Average,
                    decision, matchQt4, observation, totalMatch, interestedMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNotExistingResult(int idMatch, int idTeam) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            ResultDAO resultDAO = factory.getResultDAO();
            return (resultDAO.getResultByTeamMatch(idMatch, idTeam) == null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getDecision(int matchQt4, double average) {
        String result = "";
        double difference = (double) matchQt4 - average;
        if (difference > 0) {
            result = "s";
        } else if (difference < 0) {
            result = "i";
        } else {
            result = "e";
        }
        return result;
    }

    public static void updateResultByIdMatcIdTeam(int idMatch, int idTeam, int matchQt4, String decision) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            ResultDAO resultDAO = factory.getResultDAO();
            resultDAO.updateResultByTeamMatch(idMatch, idTeam, matchQt4, decision);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getQt13Average(int idTeam, Date date) {
        double res = 1;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            res = qtTeamDAO.getQt13Average(idTeam, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DecimalFormater.aroundDoubleToOnePlace(res);
    }

    private static List<QtTeam> getListQtTeambyIdMatch(int idMatch) {
        List<QtTeam> qtTeams = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            qtTeams = qtTeamDAO.getListQtTeamByIdMatch(idMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qtTeams;
    }

    public static List<List<QtTeam>> getListQtTeamByTeamAVG(int idTeam, double avg) {
        List<QtTeam> qtTeams = new ArrayList<QtTeam>();
        List<List<QtTeam>> listQtTeams = new ArrayList<List<QtTeam>>();
        List<Integer> listIdMatch = getListIdMatchWithQt4BiggestThanAVG(idTeam, avg);
        if (listIdMatch.size() > 0) {
            for (int i = 0; i < listIdMatch.size(); i++) {
                qtTeams = getListQtTeambyIdMatch(listIdMatch.get(i)); // two match in list for current i
                listQtTeams.add(qtTeams);
            }
        }
        return listQtTeams;
    }

    public static List<List<Result>> getListResultTeamByTeamAVG(int idTeam, double avg) {
        List<List<Result>> listResults = new ArrayList<List<Result>>();
        List<Result> results = new ArrayList<Result>();
        List<Integer> listIdMatch = getListIdMatchWithQt4BiggestThanAVG(idTeam, avg);
        if (listIdMatch.size() > 0) {
            for (Integer idMatch : listIdMatch) {
                results = getListResultByMatch(idMatch);
                listResults.add(results);
            }
        }
        return listResults;
    }

    private static List<Integer> getListIdMatchWithQt4BiggestThanAVG(int idTeam, double avg) {
        List<Integer> listId = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            listId = qtTeamDAO.getListIdMatchWithBiggestQt4ThanAvg(idTeam, avg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    public static int countTeamMatchWihtQt4BiggestThanAVGBeforeDate(int idTeam, double avg, Date date) {
        int res = 1;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            res = qtTeamDAO.countTeamMatchWithQt4BiggestThanAvg(idTeam, avg, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static int countTeamMatchWithMostPointQt4BeforeDate(int idTeam, Date date) {
        int res = 1;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            res = qtTeamDAO.countTeamMatchWithMostQt4BeforeDate(idTeam, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private static List<Result> getListResultByMatch(int idMatch) {
        List<Result> results = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            ResultDAO resultDAO = factory.getResultDAO();
            results = resultDAO.getListResultByMatch(idMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void updateResultByTeamMatch(int idMatch, int idTeam, double average, short regime,
                                               short offensiveRegime, short deffensiveRegime, short teamOffensive, short opponentDeffensive,
                                               double averageDifference, double averagePercent, double mostQt4Percent, double qt13Average, String decision,
                                               int matchQt4, short observation, int totalMatch, int interestedMatch) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            ResultDAO resultDAO = factory.getResultDAO();
            resultDAO.updateResultByTeamMatch(idMatch, idTeam, average, regime, offensiveRegime, deffensiveRegime,
                    teamOffensive, opponentDeffensive, averageDifference, averagePercent, mostQt4Percent, qt13Average,
                    decision, matchQt4, observation, totalMatch, interestedMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Result> getListPrevResultByRegime(short regime, short offensiveRegime, short deffensiveRegime) {
        List<Result> results = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            ResultDAO resultDAO = factory.getResultDAO();
            results = resultDAO.getListPrevResultByRegime(regime, offensiveRegime, deffensiveRegime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void addResult(int idTeam, Result result) {
        mapResults.put(idTeam, result);
    }

    public static void addXY(int idTeam, XY xy) {
        mapXY.put(idTeam, xy);
    }

    public static void addPrevResult(int idTeam, List<Result> result) {
        mapPrevResultByRegime.put(idTeam, result);
    }

    public static void addListQtTeams(int idTeam, List<List<QtTeam>> listQtTeam) {
        mapListQtTeams.put(idTeam, listQtTeam);
    }

    public static void addTotalPoints(int idMatch, double total) {
        mapTotalPointsMatch.put(idMatch, total);
    }

    public static void addListResults(int idTeam, List<List<Result>> listResult) {
        mapListResults.put(idTeam, listResult);
    }

    public static void addDecision(int idTeam, double superiorProbability) {
        mapDecisions.put(idTeam, superiorProbability);
    }

    public static void addConfirmsText(int idTeam, double avg, int... qts) {
        if (confirmsSuperior(avg, qts) && avg >= 25.0) {
            int min = getMinQt(qts);
            mapConfirmsText.put(idTeam, "scores at least " + min);
        } else {
            mapConfirmsText.put(idTeam, "initial result");
        }
    }

    public static double getTeamAvg(int qt1, int qt2, int qt3) {
        double avg = (double) (qt1 + qt2 + qt3) / 3;
        return DecimalFormater.aroundDoubleToOnePlace(avg);
    }

    public static int getDifference(int qt11, int qt12, int qt13, int qt21, int qt22, int qt23) {
        return (qt11 + qt12 + qt13) - (qt21 + qt22 + qt23);
    }

    public static double getAveragePercent(int totalMatch, int matchWithCondition) {
        if (totalMatch == 0) {
            totalMatch = 1;
        }
        double res = (double) matchWithCondition / (double) totalMatch;
        res *= 100;
        return DecimalFormater.aroundDoubleToOnePlace(res);
    }

    public static short getRegime(double avgPercent) {
        short res = 0;
        if (avgPercent >= 0 && avgPercent <= 9.9) {
            res = 1;
        } else if (avgPercent <= 19.9) {
            res = 2;
        } else if (avgPercent <= 29.9) {
            res = 3;
        } else if (avgPercent <= 39.9) {
            res = 4;
        } else if (avgPercent <= 49.9) {
            res = 5;
        } else if (avgPercent <= 59.9) {
            res = 6;
        } else if (avgPercent <= 69.9) {
            res = 7;
        } else if (avgPercent <= 79.9) {
            res = 8;
        } else if (avgPercent <= 89.9) {
            res = 9;
        } else if (avgPercent <= 100) {
            res = 10;
        }
        return res;
    }

    public static short getOffensiveDefensiveRegime(int offensiveDefensive) {
        short res = 0;
        if (offensiveDefensive >= 1 && offensiveDefensive <= 5) {
            res = 1;
        } else if (offensiveDefensive <= 8) {
            res = 2;
        } else if (offensiveDefensive <= 11) {
            res = 3;
        } else if (offensiveDefensive <= 14) {
            res = 4;
        } else if (offensiveDefensive <= 17) {
            res = 5;
        } else if (offensiveDefensive <= 20) {
            res = 6;
        } else if (offensiveDefensive <= 24) {
            res = 7;
        } else if (offensiveDefensive <= 30) {
            res = 8;
        }
        return res;
    }

    public static Map<Integer, List<Result>> getMapPrevResultByRegime() {
        return mapPrevResultByRegime;
    }

    public static void setMapPrevResultByRegime(Map<Integer, List<Result>> mapPrevResultByRegime) {
        AddPredictionLogic.mapPrevResultByRegime = mapPrevResultByRegime;
    }

    public static Map<Integer, List<List<QtTeam>>> getMapListQtTeams() {
        return mapListQtTeams;
    }

    public static void setMapListQtTeams(Map<Integer, List<List<QtTeam>>> mapListQtTeams) {
        AddPredictionLogic.mapListQtTeams = mapListQtTeams;
    }

    public static Map<Integer, List<List<Result>>> getMapListResults() {
        return mapListResults;
    }

    public static void setMapListResults(Map<Integer, List<List<Result>>> mapListResults) {
        AddPredictionLogic.mapListResults = mapListResults;
    }

    public static Map<Integer, Double> getMapTotalPointsMatch() {
        return mapTotalPointsMatch;
    }

    public static void setMapTotalPointsMatch(Map<Integer, Double> mapTotalPointsMatch) {
        AddPredictionLogic.mapTotalPointsMatch = mapTotalPointsMatch;
    }

    public static XY getXY(int idTeam, double avg) {
        XY xy = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            ResultDAO resultDAO = factory.getResultDAO();
            xy = resultDAO.getXY(idTeam, avg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xy;
    }

    public static Map<Integer, XY> getMapXY() {
        return mapXY;
    }

    public static void setMapXY(Map<Integer, XY> mapXY) {
        AddPredictionLogic.mapXY = mapXY;
    }

    public static List<Result> getListAllResultByClubStatut(double teamStatut, List<Result> listPrevResultByRegime) {
        List<Result> listAllResultByTeamStatut = new ArrayList<Result>();
        Iterator<Result> it = listPrevResultByRegime.iterator();
        Result result = null;
        // Team leads the match
        if (teamStatut > 0) {
            while (it.hasNext()) {
                result = it.next();
                if (result.getAverageDifference() > 0 && !result.getDecision().equals("e")
                        && !result.getDecision().equals("n")) {
                    listAllResultByTeamStatut.add(result);
                }
            }
        } else if (teamStatut < 0) {
            while (it.hasNext()) {
                result = it.next();
                if (result.getAverageDifference() < 0 && !result.getDecision().equals("e")
                        && !result.getDecision().equals("n")) {
                    listAllResultByTeamStatut.add(result);
                }
            }
        }
        return listAllResultByTeamStatut;
    }

    private static int countSuperiorResultInPrevResult(List<Result> allResultByTeamStatutList) {
        int count = 0;
        Iterator<Result> it = allResultByTeamStatutList.iterator();
        while (it.hasNext()) {
            if (it.next().getDecision().equals("s")) {
                count++;
            }
        }
        return count;
    }

    private static double getSuperiorPrevResultProbability(double teamStatut, List<Result> listPrevResultByRegime) {
        double result = 0;
        List<Result> allPrevResultByTeamStatut = getListAllResultByClubStatut(teamStatut, listPrevResultByRegime);
        if (allPrevResultByTeamStatut.isEmpty()) {
            return -1.0;
        }
        int countSuperiorPrevResult = countSuperiorResultInPrevResult(allPrevResultByTeamStatut);
        int size = allPrevResultByTeamStatut.size();
        result = (countSuperiorPrevResult / (double) size) * 100;
        return result;
    }

    private static int countTotalXY(double teamStatut, XY xy) {
        int count = 0;
        if (teamStatut > 0) {
            count = xy.getY1() + xy.getX1();
        } else if (teamStatut < 0) {
            count = xy.getY2() + xy.getX2();
        }
        return count;

    }

    private static double getSuperiorXYPrevResultProbability(double teamStatut, XY xy) {
        double result = 0;
        int totalXY = countTotalXY(teamStatut, xy);
        if (totalXY == 0) {
            return -1.0;
        }
        if (teamStatut > 0) {
            result = xy.getY1() / (double) totalXY;
        } else if (teamStatut < 0) {
            result = xy.getY2() / (double) totalXY;
        }
        return result * 100;
    }

    public static double getSuperiorProbability(double teamStatut, XY xy, List<Result> listPrevResultByRegime,
                                                double teamPercent) {
        double result = teamPercent;
        int divider = 1;
        double superiorXY = getSuperiorXYPrevResultProbability(teamStatut, xy);
        if (superiorXY != -1.0) {
            divider++;
            result += superiorXY;
        }
        double superiorPrevResultByTeamStatut = getSuperiorPrevResultProbability(teamStatut, listPrevResultByRegime);
        if (superiorPrevResultByTeamStatut != -1.0) {
            divider++;
            result += superiorPrevResultByTeamStatut;
        }
        result /= (double) divider;
        return DecimalFormater.aroundDoubleToOnePlace(result);
    }

    public static Map<Integer, Double> getMapDecisions() {
        return mapDecisions;
    }

    public static void setMapDecisions(Map<Integer, Double> mapDecisions) {
        AddPredictionLogic.mapDecisions = mapDecisions;
    }

    public static short getObservation(double probability) {
        if (probability >= 50.0) {
            return 1;
        }
        return 2;
    }

    private static int getMinQt(int... qts) {
        int minQt = qts[0];
        for (int i = 1; i < qts.length; i++) {
            if (qts[i] < minQt) {
                minQt = qts[i];
            }
        }
        return minQt;
    }

    public static boolean confirmsSuperior(double avg, int... qts) {
        int min = getMinQt(qts);
        double diffMinAvg = min - avg;
        return (diffMinAvg >= -4);
    }

    public static Map<Integer, String> getMapConfirmsText() {
        return mapConfirmsText;
    }

    public static void setMapConfirmsText(Map<Integer, String> mapConfirmsText) {
        AddPredictionLogic.mapConfirmsText = mapConfirmsText;
    }
}
