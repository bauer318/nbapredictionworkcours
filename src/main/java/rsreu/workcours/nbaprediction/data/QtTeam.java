package rsreu.workcours.nbaprediction.data;

import rsreu.workcours.nbaprediction.moderator.logic.AddMatchLogic;

public class QtTeam {
    private int idMatch;
    private int idTeam;
    private int qt1;
    private int qt2;
    private int qt3;
    private int qt4;
    private int qt5;
    private String team;

    public QtTeam(int idMatch, int idTeam, int qt1,int qt2,int qt3,int qt4,int qt5) {
        this.idMatch = idMatch;
        this.idTeam = idTeam;
        this.qt1 = qt1;
        this.qt2 = qt2;
        this.qt3 = qt3;
        this.qt4 = qt4;
        this.qt5 = qt5;
        this.team = AddMatchLogic.getTeamNameById(idTeam);
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
    public int getQt1() {
        return qt1;
    }
    public void setQt1(int qt1) {
        this.qt1 = qt1;
    }
    public int getQt2() {
        return qt2;
    }
    public void setQt2(int qt2) {
        this.qt2 = qt2;
    }
    public int getQt3() {
        return qt3;
    }
    public void setQt3(int qt3) {
        this.qt3 = qt3;
    }
    public int getQt4() {
        return qt4;
    }
    public void setQt4(int qt4) {
        this.qt4 = qt4;
    }
    public int getQt5() {
        return qt5;
    }
    public void setQt5(int qt5) {
        this.qt5 = qt5;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
