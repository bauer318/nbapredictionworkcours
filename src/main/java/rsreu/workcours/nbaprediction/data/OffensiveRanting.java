package rsreu.workcours.nbaprediction.data;

import java.sql.Date;

public class OffensiveRanting {
    private int idTeam;
    private Date offensiveRantingDate;
    private String teamName;
    private double offensiveAverage;

    public OffensiveRanting(int idTeam, Date offensiveRantingDate, String teamName, double offensiveAverage) {
        this.idTeam = idTeam;
        this.offensiveRantingDate = offensiveRantingDate;
        this.teamName = teamName;
        this.offensiveAverage = offensiveAverage;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public Date getOffensiveRantingDate() {
        return offensiveRantingDate;
    }

    public void setOffensiveRantingDate(Date offensiveRantingDate) {
        this.offensiveRantingDate = offensiveRantingDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public double getOffensiveAverage() {
        return offensiveAverage;
    }

    public void setOffensiveAverage(double offensiveAverage) {
        this.offensiveAverage = offensiveAverage;
    }

}
