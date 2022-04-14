package rsreu.workcours.nbaprediction.data;

import rsreu.workcours.nbaprediction.decimal.DecimalFormater;

public class Result {
    private int idMatch;
    private int idTeam;
    private double average;
    private short regime;
    private short offensiveRegime;
    private short deffensiveRegime;
    private short teamOffensive;
    private short opponentDeffensive;
    private double averageDifference;
    private double averagePercent;
    private double mostQt4Percent;
    private double qt13Average;
    private String decision;
    private int matchQt4;
    private short observation;
    private int totalMatch;
    private int interestedMatch;
    private double averageMatchQt4Difference;

    public Result(int idMatch, int idTeam, double average, short regime, short offensiveRegime, short deffensiveRegime,
                  short teamOffensive, short opponentDeffensive, double averageDifference, double averagePercent,
                  double mostQt4Percent, double qt13Average, String decision, int matchQt4, short observation, int totalMatch,
                  int interestedMatch) {
        this.idMatch = idMatch;
        this.idTeam = idTeam;
        this.average = average;
        this.regime = regime;
        this.offensiveRegime = offensiveRegime;
        this.deffensiveRegime = deffensiveRegime;
        this.teamOffensive = teamOffensive;
        this.opponentDeffensive = opponentDeffensive;
        this.averageDifference = averageDifference;
        this.mostQt4Percent = mostQt4Percent;
        this.qt13Average = qt13Average;
        this.decision = decision;
        this.matchQt4 = matchQt4;
        this.observation = observation;
        this.totalMatch = totalMatch;
        this.interestedMatch = interestedMatch;
        this.averagePercent = averagePercent;
    }

    public Result(short regime, short offensiveRegime, short defensiveRegime) {
        this.regime = regime;
        this.offensiveRegime = offensiveRegime;
        this.deffensiveRegime = defensiveRegime;
    }

    public Result(String decision, double average, int matchQt4, double averageDifference) {
        this.decision = decision;
        this.average = average;
        this.matchQt4 = matchQt4;
        this.averageDifference = averageDifference;
        this.averageMatchQt4Difference = DecimalFormater.aroundDoubleToOnePlace(matchQt4 - average);
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public short getRegime() {
        return regime;
    }

    public void setRegime(short regime) {
        this.regime = regime;
    }

    public short getOffensiveRegime() {
        return offensiveRegime;
    }

    public void setOffensiveRegime(short offensiveRegime) {
        this.offensiveRegime = offensiveRegime;
    }

    public short getDeffensiveRegime() {
        return deffensiveRegime;
    }

    public void setDeffensiveRegime(short deffensiveRegime) {
        this.deffensiveRegime = deffensiveRegime;
    }

    public short getTeamOffensive() {
        return teamOffensive;
    }

    public void setTeamOffensive(short teamOffensive) {
        this.teamOffensive = teamOffensive;
    }

    public short getOpponentDeffensive() {
        return opponentDeffensive;
    }

    public void setOpponentDeffensive(short opponentDeffensive) {
        this.opponentDeffensive = opponentDeffensive;
    }

    public double getAverageDifference() {
        return averageDifference;
    }

    public void setAverageDifference(double averageDifference) {
        this.averageDifference = averageDifference;
    }

    public double getAveragePercent() {
        return averagePercent;
    }

    public void setAveragePercent(double averagePercent) {
        this.averagePercent = averagePercent;
    }

    public double getMostQt4Percent() {
        return mostQt4Percent;
    }

    public void setMostQt4Percent(double mostQt4Percent) {
        this.mostQt4Percent = mostQt4Percent;
    }

    public double getQt13Average() {
        return qt13Average;
    }

    public void setQt13Average(double qt13Average) {
        this.qt13Average = qt13Average;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getMatchQt4() {
        return matchQt4;
    }

    public void setMatchQt4(int matchQt4) {
        this.matchQt4 = matchQt4;
    }

    public short getObservation() {
        return observation;
    }

    public void setObservation(short observation) {
        this.observation = observation;
    }

    public int getTotalMatch() {
        return totalMatch;
    }

    public void setTotalMatch(int totalMatch) {
        this.totalMatch = totalMatch;
    }

    public int getInterestedMatch() {
        return interestedMatch;
    }

    public void setInterestedMatch(int interestedMatch) {
        this.interestedMatch = interestedMatch;
    }

    public double getAverageMatchQt4Difference() {
        return averageMatchQt4Difference;
    }

    public void setAverageMatchQt4Difference(double averageMatchQt4Difference) {
        this.averageMatchQt4Difference = averageMatchQt4Difference;
    }

}
