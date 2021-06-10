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

public class RegistrationDao {
	/**
	 * This method is used to add user detail into the database
	 * 
	 * @param userDetail
	 */
	public void addUserDetail(UserDetail userDetail) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO user_detail(name,age,gender,mobile_number,user_password,retype_password)"
					+ "VALUES(?,?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, userDetail.getName());
			pst.setInt(2, userDetail.getAge());
			pst.setString(3, userDetail.getGender());
			pst.setLong(4, userDetail.getMobileNumber());
			pst.setString(5, userDetail.getPassword());
			pst.setString(6, userDetail.getReTypePassword());
			pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("unable to add records into the database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method is used to fetch all the records from the database
	 * 
	 * @return
	 */
	public List<UserDetail> getAllUser() {
		List<UserDetail> user = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM user_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				long mobileNumber = rs.getLong("mobile_number");
				String password = rs.getString("user_password");
				String reTypePassword = rs.getString("retype_password");
				UserDetail userDetail = new UserDetail(userId, name, age, gender, mobileNumber, password,
						reTypePassword);
				user.add(userDetail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("unable to fetch all records from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return user;
	}

}
