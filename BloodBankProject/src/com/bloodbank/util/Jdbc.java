package com.bloodbank.util;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bloodbank.model.BloodBank;

public class Jdbc {
	public static boolean Login(String id) throws ClassNotFoundException, SQLException {
        Connection connection = Util.getConnection();
        String checkUser = "select donor_id=? from DonorDetails";
        PreparedStatement prepareStatement = connection.prepareStatement(checkUser);
        prepareStatement.setString(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
      
        if (resultSet.next()) {
           
            return true;

        } else
            System.out.println("please register us new donor");

        return false;

    }
	

	public static void update(int quantity,String bloodType)throws ClassNotFoundException, SQLException {
		Connection connection = Util.getConnection();
		int totalQty=0;
		String updateStock="select quantity_available from stock where blood_type=?";
		PreparedStatement p = connection.prepareStatement(updateStock);
		p.setString(1,bloodType);
		ResultSet resultSet= p.executeQuery();
		if (resultSet.next()) {
			int qty= resultSet.getInt("quantity_available");
			totalQty= qty+quantity;
		}
		String updatedStock = "update stock set quantity_available = "+totalQty+" where blood_type= '"+bloodType+"';";
		 p = connection.prepareStatement(updatedStock);
		 int row=p.executeUpdate();
		 System.out.println("updated :"+row);
		connection.close();
	}

	public static void delete(String donorId) throws ClassNotFoundException, SQLException {
		Connection connection = Util.getConnection();
		String deleteid = "delete from DonorDetails  where donor_id=?";
		PreparedStatement p = connection.prepareStatement(deleteid);
		p.setString(1, donorId);
		int row = p.executeUpdate();
		System.out.println("Deleted data: " + row);
		connection.close();
		}
	

	public static void insert(String donorId,String donorName,String bloodType,String phoneNumber,String location)throws ClassNotFoundException,SQLException
	         {
	          Connection connection = Util.getConnection();
	          String insertdata="insert into DonorDetails values(?,?,?,?,?,?)";
	          PreparedStatement p= connection.prepareStatement(insertdata);
	          p.setString(1,donorId);
	          p.setString(2,donorName);
	          p.setString(3,bloodType);
	          p.setString(4,phoneNumber);
	          p.setString(5,location);
	          p.setDate(6, new java.sql.Date(new Date().getTime()));
	        int row=  p.executeUpdate();
	        System.out.println("inserted data:"+row);
	          connection.close();
	         }


	public static boolean register(BloodBank a,String donorID,Scanner scanner) throws ClassNotFoundException, SQLException {
		boolean flag = false;
        ArrayList<String> existingList = new ArrayList<String>();
        Connection connection =Util.getConnection();
        String check = "select donor_id from DonorDetails";
        PreparedStatement ps = connection.prepareStatement(check);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
             donorID = resultSet.getString("donor_id");
            existingList.add(donorID);
        }
        while(true) {
            if (existingList.contains(a.getDonorId())) {
                System.out.println("Donor ID already exist");
                System.out.println("Enter Donor Id");
    			donorID = scanner.next();
    			a.setDonorId(donorID);
                flag = false;
            } else {
                System.out.println("Donor Id available for Registration");
                flag = true;
                break;
            }
        }
        return flag;
	}

	public static boolean adminCheck(String adminName,String adminPassword) throws ClassNotFoundException, SQLException
	{
		Connection connection=Util.getConnection();
		String query="select admin_name from stock where admin_name=? and admin_password=?";
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setString(1,adminName);
		ps.setString(2,adminPassword);
		ResultSet rs=ps.executeQuery();
		if(!rs.next())
		{
			System.out.println("username and password doesnt match");
			return false;
		}
		else
		{
			System.out.println("username and password matches");
			return true;
		}
		
	}
	public static void donorList() throws ClassNotFoundException, SQLException
	{
		Connection connection=Util.getConnection();
		String query="select donor_name,blood_type,phonenumber,location from DonorDetails";
	    PreparedStatement ps = connection.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();
        ResultSetMetaData rsmd=resultSet.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.printf("%-15s", rsmd.getColumnName(i)); 
        }
        System.out.println(); 

        
        while (resultSet.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String value = resultSet.getString(i);
                System.out.printf("%-15s", value); 
            }
            System.out.println(); 
        }

	}

}	

