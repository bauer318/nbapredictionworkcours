package rsreu.workcours.nbaprediction.data;

import java.sql.Date;

public class Ranting {
    private int idRanting;
    private int idTeam;
    private Date rantingDate;
    private int matchNumber;
    private int offensive;
    private int defensive;
    private int totalOpponentsPoints;
    private int totalPoints;

    public Ranting(int idRanting, int idTeam, Date rantingDate, int matchNumber, int offensive, int deffensive,
                   int totalPoints, int totalOpponentsPoints) {
        this.idRanting = idRanting;
        this.idTeam = idTeam;
        this.rantingDate = rantingDate;
        this.matchNumber = matchNumber;
        this.offensive = offensive;
        this.defensive = deffensive;
        this.totalPoints = totalPoints;
        this.totalOpponentsPoints = totalOpponentsPoints;
    }

    public int getIdRanting() {
        return idRanting;
    }

    public void setIdRanting(int idRanting) {
        this.idRanting = idRanting;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public Date getRantingDate() {
        return rantingDate;
    }

    public void setRantingDate(Date rantingDate) {
        this.rantingDate = rantingDate;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public int getOffensive() {
        return offensive;
    }

    public void setOffensive(int offensive) {
        this.offensive = offensive;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getDefensive() {
        return defensive;
    }

    public void setDefensive(int defensive) {
        this.defensive = defensive;
    }

    public int getTotalOpponentsPoints() {
        return totalOpponentsPoints;
    }

    public void setTotalOpponentsPoints(int totalOpponentsPoints) {
        this.totalOpponentsPoints = totalOpponentsPoints;
    }
}
