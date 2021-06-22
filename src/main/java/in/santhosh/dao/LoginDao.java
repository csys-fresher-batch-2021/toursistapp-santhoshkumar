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
			String sql = "SELECT mobile_number FROM user_detail WHERE mobile_number=? AND user_password=?";
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
			String sql = "SELECT user_id,name,age,gender,mobile_number,user_password,"
					+ "retype_password FROM user_detail WHERE mobile_number=?";
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
	/**
	 * This method is used to update the user password
	 * @param newPassword
	 */
	public void updatePassword(String newPassword,int userId)
	{
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql="UPDATE user_detail SET user_password=?,retype_password=? WHERE user_id=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1,newPassword);
			pst.setString(2,newPassword);
			pst.setInt(3,userId);
			pst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to update user password");
		}
		finally {
			ConnectionUtil.close(pst, connection);
		}
		
	}
	/**
	 * This method is used to validate the old password
	 * @param userId
	 * @param oldPassword
	 * @return
	 */
	public boolean validOldPassword(int userId,String oldPassword)
	{
		Connection connection = null;
		PreparedStatement pst = null;
		boolean isMatched=false;
		try {
			connection = ConnectionUtil.getConnection();
			String sql="SELECT name FROM user_detail WHERE user_id=? AND user_password=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1,userId);
			pst.setString(2,oldPassword);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				isMatched=true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to validate old password");
		}
		finally {
			ConnectionUtil.close(pst, connection);
		}
		return isMatched;
		
	}
	/**
	 * This method is used to update the user forgot password
	 * @param userId
	 * @param oldPassword
	 * @return
	 */
	public void updateForgotPassword(long mobileNumber,String password)
	{
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql="UPDATE user_detail SET user_password=?,retype_password=? WHERE mobile_number=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1,password);
			pst.setString(2,password);
			pst.setLong(3,mobileNumber);
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("unable to validate old password");
		}
		finally {
			ConnectionUtil.close(pst, connection);
		}
	}
}
