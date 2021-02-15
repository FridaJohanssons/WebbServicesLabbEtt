package x.snowroller;

import java.util.List;

public interface UserDao {

    //Create
    void createUser(User a);

    //Read
    List<User> getAll();
    // returns user with the id
}
