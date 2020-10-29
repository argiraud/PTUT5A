package fr.polyrecrute.responceType;

public class UserSignin {
    private Long id;
    private String username;
    private String email;
    private boolean enable;
    private String tokenJWT;

    public UserSignin(Long id, String username, String email, boolean enable, String tokenJWT) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enable = enable;
        this.tokenJWT = tokenJWT;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getTokenJWT() {
        return tokenJWT;
    }
}
