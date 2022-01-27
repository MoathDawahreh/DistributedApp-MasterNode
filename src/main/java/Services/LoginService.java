package Services;

import MasterData.UserDao;
import Models.User;

public class LoginService {

    public boolean validateUser(String userName, String password,String role) {

        UserDao userDao = new UserDao();
        User user = new User();
        user = userDao.getUser(userName);

        if (user.getUserName() == null) return false;
        //  System.out.println(user.getUserName()==null);

//        try{
//           //user = userDao.getUser(userName);
//
//        }catch (Exception e){
//            System.out.println(e);
//
//        }
         return userName.equalsIgnoreCase(user.getUserName()) && password.equals(user.getPassword()) &&role.equals(user.getRole()) ;

        //return userName.equalsIgnoreCase("userName1") && password.equals("pass1");
    }

}
