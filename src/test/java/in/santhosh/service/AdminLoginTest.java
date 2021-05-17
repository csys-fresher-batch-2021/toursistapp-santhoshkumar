package in.santhosh.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdminLoginTest {

	@Test
	public void adminLoginWithCorrectDetail() {
		long mobileNumber=9865940407l;
		String password="Admin123";
		boolean validLoginDetail=AdminLogin.adminLogin(mobileNumber, password);
		assertTrue(validLoginDetail);
		
	}
	@Test
	public void adminLoginWithWrongDetail() {
		long mobileNumber=9865940406l;
		String password="Admin123";
		boolean validLoginDetail=AdminLogin.adminLogin(mobileNumber, password);
		assertFalse(validLoginDetail);
		
	}

}
