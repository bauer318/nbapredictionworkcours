package rsreu.workcours.nbaprediction.data;

public class Bettor extends User{
    private int idBettor;
    private String email;
    private String firstname;
    private String lastname;

    public Bettor(int id,
                  int idGroup,
                  String login,
                  String password,
                  int blockingStatus,
                  int authorizationStatus,
                  int idBettor,
                  String email,
                  String firstname,
                  String lastname) {
        super(id, idGroup, login, password, blockingStatus, authorizationStatus);
        this.idBettor = idBettor;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Bettor(Bettor bettor){
        super(bettor);
        this.idBettor = bettor.idBettor;
        this.email=bettor.email;
        this.firstname = bettor.firstname;
        this.lastname = bettor.lastname;
    }
    public Bettor clone(){
        return new Bettor(this);
    }
    public int getIdBettor() {
        return idBettor;
    }
    public void setIdBettor(int idBettor) {
        this.idBettor = idBettor;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
