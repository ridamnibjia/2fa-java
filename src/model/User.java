public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private int emailVerify;
    String secret;

    // Constructor
    public User(int id, String name, String email, String password, int emailVerify) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.emailVerify = emailVerify;
    }
    public User(int id, String name, String email, String password, int emailVerify, String secret) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.emailVerify = emailVerify;
        this.secret = secret;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(int emailVerify) {
        this.emailVerify = emailVerify;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    
}
