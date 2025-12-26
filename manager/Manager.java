package manager;

public class Manager {
    private int mgId;
    private String mgName;
    private String password;

    public Manager(int mgId, String mgName, String password) {
        this.mgId = mgId;
        this.mgName = mgName;
        this.password = password;
    }

    // Getter and Setter for mgId
    public int getMgId() {
        return mgId;
    }

    public void setMgId(int mgId) {
        this.mgId = mgId;
    }

    // Getter and Setter for mgName
    public String getMgName() {
        return mgName;
    }

    public void setMgName(String mgName) {
        this.mgName = mgName;
    }

    // Getter and Setter for password
    public String getPasswd() {
        return password;
    }

    public void setPasswd(String password) {
        this.password = password;
    }
}
