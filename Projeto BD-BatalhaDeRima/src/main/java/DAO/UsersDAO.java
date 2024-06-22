package DAO;

public class UsersDAO extends ConnectionDAO{

    public void loginUser(String user, String password){
        connectLikeAdmin(user, password);
    }
}
