package rsreu.workcours.nbaprediction.data;

public class User {
    private int id;
    private int idGroup;
    private String login;
    private String password;
    private int blockingStatus;
    private int authorizationStatus;

    public User(int id, int idGroup, String login, String password, int blockingStatus, int authorizationStatus) {
        this.id = id;
        this.idGroup = idGroup;
        this.login = login;
        this.password = password;
        this.authorizationStatus = authorizationStatus;
        this.blockingStatus = blockingStatus;
    }

    public User(User user){
        this.id = user.id;
        this.idGroup = user.idGroup;
        this.login = user.login;
        this.password = user.password;
        this.authorizationStatus = user.authorizationStatus;
        this.blockingStatus = user.blockingStatus;
    }
    public User clone(){
        return new User(this);
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
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

    public int getBlockingStatus() {
        return blockingStatus;
    }

    public void setBlockingStatus(int blockingStatus) {
        this.blockingStatus = blockingStatus;
    }

    public int getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(int authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }
}
