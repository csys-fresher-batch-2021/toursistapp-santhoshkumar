package in.santhosh.service;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import in.santhosh.exception.ServiceException;

public class UserLoginTest {

	@Test
	public void userLoginWithCorrectDetail(){
		long mobileNumber=6381532548l;
		String password="Prakash123";
		boolean validLogin=UserLogin.validLogin(mobileNumber, password);
		assertEquals(true,validLogin);
	}
	@Test
	public void userLoginWithWrongDetail(){
		long mobileNumber=6381532532l;
		String password="Prakash123";
		try {
			UserLogin.validLogin(mobileNumber, password);
		} catch (ServiceException e) {
			assertEquals("unable to verify user credentials",e.getMessage());
		}
	}

}
