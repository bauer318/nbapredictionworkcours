package rsreu.workcours.nbaprediction.data;

public class Bettor {
    private int id;
    private int idUser;
    private String login;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private int blockingStatus;
    public Bettor(
            int id,
            int idUser,
            String login,
            String password,
            String email,
            String firstname,
            String lastname,
            int blockingStatus) {
        this.id=id;
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.blockingStatus = blockingStatus;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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

    public int getBlockingStatus() {
        return blockingStatus;
    }

    public void setBlockingStatus(int blockingStatus) {
        this.blockingStatus = blockingStatus;
    }
}
