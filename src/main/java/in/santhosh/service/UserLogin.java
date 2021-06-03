package in.santhosh.service;

import java.util.List;

import in.santhosh.dao.LoginDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.ServiceException;
import in.santhosh.model.UserDetail;

public class UserLogin {
	private UserLogin() {

	}

	public static boolean validLogin(long mobileNumber, String password) {
		boolean validLogin;
		try {
			LoginDao dao = new LoginDao();
			validLogin = dao.loginDao(mobileNumber, password);
		} catch (DBException e) {
			throw new ServiceException("unable to verify user credentials");
		}
		return validLogin;

	}

	public static List<UserDetail> getUserDetail(long mobileNumber) {
		LoginDao dao = new LoginDao();
		return dao.getallDetailsOfUser(mobileNumber);

	}

}
