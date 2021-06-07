package in.santhosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.santhosh.exception.DBException;
import in.santhosh.model.UserDetail;
import in.santhosh.util.ConnectionUtil;

public class LoginDao {
	/**
	 * This method verify the entered username and password are present in database
	 * 
	 * @param mobileNumber
	 * @param password
	 * @return
	 */

	public boolean loginDao(long mobileNumber, String password) {
		boolean validLogin = false;
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select * from user_detail where mobile_number=? AND user_password=?";
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

	/**
	 * This method is used to get detail of specific user
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public List<UserDetail> getallDetailsOfUser(long mobileNumber) {
		Connection connection = null;
		PreparedStatement pst = null;
		List<UserDetail> details = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select* from user_detail where mobile_number=?";
			pst = connection.prepareStatement(sql);
			pst.setLong(1, mobileNumber);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				long mobileNo = rs.getLong("mobile_number");
				String password = rs.getString("user_password");
				String reTypePassword = rs.getString("retype_password");
				UserDetail user = new UserDetail(id, name, age, gender, mobileNo, password, reTypePassword);
				details.add(user);

			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("unable to fetch record from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

		return details;

	}
}
