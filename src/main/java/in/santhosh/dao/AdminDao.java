package in.santhosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.santhosh.exception.DBException;
import in.santhosh.util.ConnectionUtil;

public class AdminDao {
	/**
	 * This method used to validate whether entered mobile number and password are
	 * correct
	 * 
	 * @param mobileNumber
	 * @param password
	 * @return
	 */
	public boolean validAdminLogin(long mobileNumber, String password) {
		boolean validLogin = false;
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select * from admin_detail where mobile_number=? AND user_password=?";
			pst = connection.prepareStatement(sql);
			pst.setLong(1, mobileNumber);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				validLogin = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("unable to fetch record from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

		return validLogin;
	}

}
