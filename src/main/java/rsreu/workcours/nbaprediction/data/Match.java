package rsreu.workcours.nbaprediction.data;

import rsreu.workcours.nbaprediction.datetime.DateTimeWorker;
import rsreu.workcours.nbaprediction.moderator.logic.AddMatchLogic;

import java.sql.Date;
import java.sql.Time;

public class Match {
    private int idMatch;
    private int idGuestTeam; //Team1
    private int idHomeTeam; //Team2
    private Date matchDate;
    private Time matchTime;
    private String strDate;
    private String strTime;
    private String strGuestTeam;
    private String strHomeTeam;

    public Match(int idMatch, int idGuestTeam, int idHomeTeam, Date matchDate, Time matchTime) {
        this.idMatch=idMatch;
        this.idGuestTeam = idGuestTeam;
        this.idHomeTeam = idHomeTeam;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.strDate = DateTimeWorker.dateToString(matchDate);
        this.strTime = DateTimeWorker.timeToString(matchTime);
        this.strGuestTeam = AddMatchLogic.getTeamNameById(idGuestTeam);
        this.strHomeTeam = AddMatchLogic.getTeamNameById(idHomeTeam);
    }

    public int getIdMatch() {
        return idMatch;
    }
    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }
    public int getIdGuestTeam() {
        return idGuestTeam;
    }
    public void setIdGuestTeam(int idGuestTeam) {
        this.idGuestTeam = idGuestTeam;
    }
    public int getIdHomeTeam() {
        return idHomeTeam;
    }
    public void setIdHomeTeam(int idHomeTeam) {
        this.idHomeTeam = idHomeTeam;
    }
    public Date getMatchDate() {
        return matchDate;
    }
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Time getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Time matchTime) {
        this.matchTime = matchTime;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getStrGuestTeam() {
        return strGuestTeam;
    }

    public void setStrGuestTeam(String strGuestTeam) {
        this.strGuestTeam = strGuestTeam;
    }

    public String getStrHomeTeam() {
        return strHomeTeam;
    }

    public void setStrHomeTeam(String strHomeTeam) {
        this.strHomeTeam = strHomeTeam;
    }
}
