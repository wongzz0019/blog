package test;

import javax.jws.soap.SOAPBinding;
import java.util.Objects;

/**
 * @author Bosco
 * @date 2021/4/19
 */
public class User {

    private String username;
    private Integer password;

    public User(String username, Integer password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User){
            String objUserName = ((User)obj).getUsername();
            Integer objPassWord = ((User)obj).getPassword();

            return this.getUsername().equals(objUserName) && this.getPassword().equals(objPassWord);
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
