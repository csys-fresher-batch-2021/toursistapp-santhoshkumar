package in.santhosh.service;

import java.util.List;

import in.santhosh.dao.ContactUsDao;
import in.santhosh.exception.DBException;
import in.santhosh.exception.ServiceException;
import in.santhosh.model.ContactUsDetails;
import in.santhosh.validator.UserValidation;
import in.santhosh.validator.Validation;

public class ContactDetail {
	private ContactDetail() {

	}

	/**
	 * This method is used to add contact details into the database
	 * 
	 * @param details
	 * @return
	 */

	public static boolean contactDetail(ContactUsDetails details) {
		boolean validDetails = false;
		try {
			if (Validation.stringValidation(details.getName())
					&& UserValidation.validateMobileNumber(details.getMobileNumber())) {
				ContactUsDao dao = new ContactUsDao();
				dao.addContactUsDetail(details);
				validDetails = true;
			}
		} catch (DBException e) {
			throw new ServiceException("unable to add details into database");
		}
		return validDetails;
	}

	/**
	 * To check whether user responds exists
	 * 
	 * @param details
	 * @return
	 */
	public static boolean existsEnquiry(ContactUsDetails details) {
		boolean isMatched = false;
		ContactUsDao dao = new ContactUsDao();
		try {
			isMatched = dao.existsEnquiryDetail(details);
		} catch (DBException e) {
			e.printStackTrace();
		}
		return isMatched;
	}

	/**
	 * This method is used to display all enquiry
	 * 
	 * @return
	 */
	public static List<ContactUsDetails> displayAllEnquiry() {
		ContactUsDao dao = new ContactUsDao();
		return dao.displayEnquiry();

	}

}
