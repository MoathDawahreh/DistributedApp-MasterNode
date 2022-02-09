package Services;

import MasterData.UserDBDao;
import Models.User;

public class LoginService {

    public boolean validateUser(String userName, String password,String role) {

        UserDBDao userDao = new UserDBDao();
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
