package in.santhosh.service;

import in.santhosh.dao.LoginDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.ServiceException;

public class UserLogin {
	private UserLogin()
	{
		
	}
	public static boolean validLogin(long mobileNumber,String password)
	{
		boolean validLogin;
		try {
			LoginDao dao=new LoginDao();
			validLogin = dao.loginDao(mobileNumber,password);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("unable to verify user credentials");
		}
		return validLogin;
		
	}

}
