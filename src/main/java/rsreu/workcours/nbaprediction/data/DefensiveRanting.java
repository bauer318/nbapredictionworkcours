package rsreu.workcours.nbaprediction.data;

import java.sql.Date;

public class DefensiveRanting {
    private int idTeam;
    private Date defensiveRantingDate;
    private String teamName;
    private double defensiveAverage;

    public DefensiveRanting(int idTeam, Date defensiveRantingDate, String teamName, double defensiveAverage) {
        this.idTeam = idTeam;
        this.defensiveAverage = defensiveAverage;
        this.teamName = teamName;
        this.defensiveRantingDate = defensiveRantingDate;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public Date getDefensiveRantingDate() {
        return defensiveRantingDate;
    }

    public void setDefensiveRantingDate(Date defensiveRantingDate) {
        this.defensiveRantingDate = defensiveRantingDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public double getDefensiveAverage() {
        return defensiveAverage;
    }

    public void setDefensiveAverage(double defensiveAverage) {
        this.defensiveAverage = defensiveAverage;
    }

}
