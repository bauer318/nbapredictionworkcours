package rsreu.workcours.nbaprediction.data;

public class Team {
    private int id;
    private String team;

    public Team(int id, String team) {
        this.id = id;
        this.team = team;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }

}
