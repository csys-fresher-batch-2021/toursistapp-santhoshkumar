package in.santhosh.validator;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import in.santhosh.model.UserDetail;
public class RegistrationValidationTest {

	@Test
	public void userRegistrationWithCorrectDetail() {
		String name="Saran";
		int age=21;
		String gender="Male";
		long mobileNumber=9843210984l;
		String password="Saran123";
		String reTypePassword="Saran123";
		UserDetail user=new UserDetail(name,age,gender,mobileNumber,password,reTypePassword);
		boolean validRegistration=RegistrationValidation.validRegistration(user);
		assertEquals(true,validRegistration);
	}
	
		@Test
		public void userRegistrationWithWrongDetail() {
			String name="";
			int age=21;
			String gender="Male";
			long mobileNumber=9823218984l;
			String password="Santhosh123";
			String reTypePassword="Santhosh123";
			UserDetail user=new UserDetail(name,age,gender,mobileNumber,password,reTypePassword);
			try {
				RegistrationValidation.validRegistration(user);
			} catch (Exception e) {
				assertEquals("Enter all details correctly",e.getMessage());
			}
	}
}

