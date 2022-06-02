package rsreu.workcours.nbaprediction.data;

import rsreu.workcours.nbaprediction.datetime.DateTimeWorker;
import rsreu.workcours.nbaprediction.moderator.logic.AddMatchLogic;
import rsreu.workcours.nbaprediction.moderator.logic.UpdatePointsLogic;

import java.time.LocalDate;

public class Prediction {
    private int idMatch;
    private double total;
    private double superiority;
    private int idHomeTeam;
    private int idGuestTeam;
    private String strGuestTeam;
    private String strHomeTeam;
    private String strDate;

    public Prediction(int idMatch, double total, double superiority){
        this.setIdMatch(idMatch);
        this.setTotal(total);
        this.setSuperiority(superiority);
        setIdHomeTeam(UpdatePointsLogic.getIdHomeTeamByIdMatch(idMatch));
        setIdGuestTeam(UpdatePointsLogic.getIdGuestTeamByIdMatch(idMatch));
        setStrGuestTeam(AddMatchLogic.getTeamNameById(getIdGuestTeam()));
        setStrHomeTeam(AddMatchLogic.getTeamNameById(getIdHomeTeam()));
        setStrDate(DateTimeWorker.dateToString(DateTimeWorker.localDateToDate(LocalDate.now())));
    }
    public Prediction (int idMatch){
        this.idMatch = idMatch;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSuperiority() {
        return superiority;
    }

    public void setSuperiority(double superiority) {
        this.superiority = superiority;
    }

    public int getIdHomeTeam() {
        return idHomeTeam;
    }

    public void setIdHomeTeam(int idHomeTeam) {
        this.idHomeTeam = idHomeTeam;
    }

    public int getIdGuestTeam() {
        return idGuestTeam;
    }

    public void setIdGuestTeam(int idGuestTeam) {
        this.idGuestTeam = idGuestTeam;
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

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }
}
