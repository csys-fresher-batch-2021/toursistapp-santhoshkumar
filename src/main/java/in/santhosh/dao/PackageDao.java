package in.santhosh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.santhosh.exception.DBException;
import in.santhosh.model.TourPackageDetail;
import in.santhosh.util.ConnectionUtil;

public class PackageDao {
	/**
	 * This method is use to add package into database
	 * 
	 * @param packages
	 */
	public void addPackage(TourPackageDetail packages) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(packages.getStartDate());
			Date endDate = Date.valueOf(packages.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "insert into package_detail(package_name,package_price,number_of_days,start_date,end_date) values(?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, packages.getPackageName());
			pst.setInt(2, packages.getPackagePrice());
			pst.setInt(3, packages.getNumberOfDays());
			pst.setDate(4, startDate);
			pst.setDate(5, endDate);
			pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Package cannot be added to the database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * This method is used to fetch all packageDetail from database
	 * 
	 * @return
	 */
	public List<TourPackageDetail> displayPackage() {
		List<TourPackageDetail> packageDetails = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select* from package_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString("package_name");
				int packagePrice = rs.getInt("package_price");
				int numberOfDays = rs.getInt("number_of_days");
				LocalDate startDate = rs.getDate("start_date").toLocalDate();
				LocalDate endDate = rs.getDate("end_date").toLocalDate();

				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate);
				packageDetails.add(packages);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(pst, connection);
		}

		return packageDetails;

	}

	/**
	 * This method is used to remove package from database
	 * 
	 * @param packageDetail
	 */
	public void removePackage(TourPackageDetail packageDetail) {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			Date startDate = Date.valueOf(packageDetail.getStartDate());
			Date endDate = Date.valueOf(packageDetail.getEndDate());
			connection = ConnectionUtil.getConnection();
			String sql = "delete from package_detail where package_name=? AND package_price=? AND number_of_days=? AND start_date =? AND end_date=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, packageDetail.getPackageName());
			pst.setInt(2, packageDetail.getPackagePrice());
			pst.setInt(3, packageDetail.getNumberOfDays());
			pst.setDate(4, startDate);
			pst.setDate(5, endDate);
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot delete the package from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}
	/**
	 * This method is used to return all the package details from database
	 */

	public List<TourPackageDetail> getAllPackages() {
		Connection connection = null;
		PreparedStatement pst = null;

		List<TourPackageDetail> packageDetails = new ArrayList<>();

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "Select * from package_detail";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString("package_name");
				int packagePrice = rs.getInt("package_price");
				int numberOfDays = rs.getInt("number_of_days");
				LocalDate startDate = rs.getDate("start_date").toLocalDate();
				LocalDate endDate = rs.getDate("end_date").toLocalDate();

				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate);
				packageDetails.add(packages);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get all the records from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

		return packageDetails;
	}
	/**
	 * This method is used to fetch packages by country name from database
	 * @return
	 */
	public List<TourPackageDetail> searchPackageByCountryName(String countryName) {
		Connection connection = null;
		PreparedStatement pst = null;

		List<TourPackageDetail> packageDetails = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "Select * from package_detail where package_name=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1,countryName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString("package_name");
				int packagePrice = rs.getInt("package_price");
				int numberOfDays = rs.getInt("number_of_days");
				LocalDate startDate = rs.getDate("start_date").toLocalDate();
				LocalDate endDate = rs.getDate("end_date").toLocalDate();

				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate);
				packageDetails.add(packages);
		}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get all the records from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return packageDetails;
	}
	/**
	 * This method used to fetch packages by price from database
	 * @param price
	 * @return
	 */
	public List<TourPackageDetail> searchPackageByPrice(int price) {
		Connection connection = null;
		PreparedStatement pst = null;

		List<TourPackageDetail> packageDetails = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "Select * from package_detail where package_price=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1,price);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String packageName = rs.getString("package_name");
				int packagePrice = rs.getInt("package_price");
				int numberOfDays = rs.getInt("number_of_days");
				LocalDate startDate = rs.getDate("start_date").toLocalDate();
				LocalDate endDate = rs.getDate("end_date").toLocalDate();

				TourPackageDetail packages = new TourPackageDetail(packageName, packagePrice, numberOfDays, startDate,
						endDate);
				packageDetails.add(packages);
		}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get all the records from database");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
		return packageDetails;
	}
}
