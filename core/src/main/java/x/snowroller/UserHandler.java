package x.snowroller;

import java.util.List;

public class UserHandler {

    public static void createUser(String id, String firstName, String lastName){
    UserDao udao = new UserDAOWithJPAImpl();
    User a = new User(id, firstName, lastName);
    udao.createUser(a);}

    public static List<User> getAll(){
        UserDao udao = new UserDAOWithJPAImpl();
        return udao.getAll();
    }
}
