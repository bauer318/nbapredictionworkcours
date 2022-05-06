package rsreu.workcours.nbaprediction.data;

public class Bettor extends User{
    private int id;
    private String email;
    private String firstname;
    private String lastname;

    public Bettor(int idUser,
                  int idGroup,
                  String login,
                  String password,
                  int blockingStatus,
                  int authorizationStatus,
                  int id,
                  String email,
                  String firstname,
                  String lastname) {
        super(idUser, idGroup, login, password, blockingStatus, authorizationStatus);
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
