package nl.cjm.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class MyUser implements Principal {
    private static List<MyUser> allMyUsers = new ArrayList<>();
    private String username, plainpassword, role;

    public MyUser(String naam, String ww) {
        username = naam;
        plainpassword = ww;
        role = "administrator";
        if (!allMyUsers.contains(this)) allMyUsers.add(this);
    }

    @Override
    public String getName() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public static MyUser getUserByName(String uname) {
        return allMyUsers.stream()
                .filter(e -> e.username.equals(uname))
                .findFirst()
                .orElse(null);
    }

    public static String validateLogin(String uname, String pwd) {
        MyUser foundUser = getUserByName(uname);
        if (foundUser != null) return pwd.equals(foundUser.plainpassword) ? foundUser.getRole() : null;
        return null;
    }

    public static List<MyUser> getAlleEigenaren() {
        return allMyUsers;
    }


    @Override
    public String toString() {
        return "MyUser{" +
                " username='" + username + '\'' +
                ", plainpassword='" + plainpassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
