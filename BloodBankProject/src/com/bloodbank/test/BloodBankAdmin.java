package com.bloodbank.test;
import java.sql.SQLException;
import java.util.Scanner;

import com.bloodbank.model.BloodBank;
import com.bloodbank.util.Jdbc;
public class BloodBankAdmin {
	public void admin() throws ClassNotFoundException, SQLException
	{
		BloodBank a=new BloodBank();
		Validation c=new Validation();
		Scanner d=new Scanner(System.in);
		while(true)
		{
		System.out.println("Enter admin name:");
		String adminName=d.next();
		System.out.println("Enter password:");
		String password=d.next();
		if(Jdbc.adminCheck(adminName, password))
		{
			BloodBankAdmin.adminadd(d,c,a);
			break;
		}
		else
		{
			System.out.println("Enter valid name and password");
		}
		}
	}
		public static  void adminadd(Scanner d,Validation c,BloodBank a)throws ClassNotFoundException,SQLException
		{
		System.out.println("-----------login successfully-------------");
		System.out.println("Select \n1.adding stock \n2.view DonorDetails \n3.Delete \n4.Exit");
		try
		{
		int Details=d.nextInt();
		while(true)
		switch(Details)
		{
		case 1:
		{
			System.out.println("Enter type of blood:");
			String bloodType=d.next();
			a.setBloodType(c.BloodTypeCheck(d, bloodType));
			System.out.println("Enter quantity of blood to be added:");
			int quantityAdded=d.nextInt();
			a.setQuantity(c.quantityCheck(d, quantityAdded));
			Jdbc.update(quantityAdded,bloodType);
			return;
		}
		case 2:
		{
			Jdbc.donorList();
			return;
		}
		case 3:
		{
			System.out.println("Please enter the id ");
			String donorId = d.next();
			Jdbc.delete(donorId);
			return;
		}
		case 4:
		{
			System.out.println("          Exited Successfully       ");
			System.out.println("Select \n1.adding stock \n2.Delete \n3.Exit");
			Details=d.nextInt();
		}
	
			
		
	}
	
		
		
	
		}
	catch(Exception ex)
	{
		System.out.println(ex.getMessage());
	}
	
	}
}


