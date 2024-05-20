package com.bloodbank.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bloodbank.dao.BloodBankAbstract;
import com.bloodbank.model.BloodBank;
import com.bloodbank.util.Jdbc;

public class BloodBankUser {
	TestPhoneNumber testMobile =new TestPhoneNumber();
	public void user() throws ClassNotFoundException, SQLException {
		String bloodType, donorName,phoneNumber,password,  donorID ,consumeAlcohol;
		int quantity, noOfDays;
		char donatedBefore;
		BloodBank a = new BloodBank();
		BloodBankAbstract b = new BloodBankAbstract();
		BloodBankMain callMain = new BloodBankMain();
		Validation c = new Validation();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to donate blood(yes for donating blood/no for receiving blood):");
		String donor = scanner.next();
		List<String> bloodgroup = new ArrayList<>();
		bloodgroup.add("A+");
		bloodgroup.add("B+");
		bloodgroup.add("O+");
		if (donor.equalsIgnoreCase("yes")) {
			System.out.println("Are You New Donor(yes/no):");
			String user = scanner.next();
			if (user.equalsIgnoreCase("No")) {
				System.out.println("enter Donor name:");
				donorName = scanner.next();
				a.setDonorName(donorName);
				a.setDonorName(c.namecheck(scanner,donorName));
				System.out.println("Enter Donor id:");
				donorID=scanner.next();
				Jdbc.Login(donorID);
					System.out.println("Enter Password:");
					password = scanner.next();
					a.setPassword(c.passwordCheck(scanner, password));
					System.out.println("have you consumed alcohol(y/n):");
					 consumeAlcohol=scanner.next();
					if(consumeAlcohol.equals("y"))
					{
						System.out.println("not eligible to donate");
						callMain.main(null);
					}
					System.out.println("Have You Donated Before(y/n):");
					donatedBefore = scanner.next().charAt(0);
					while (!(donatedBefore == 'y' || donatedBefore == 'n')) {
						System.out.println("Enter Either y or n:");
						donatedBefore = scanner.next().charAt(0);
					}
					if (donatedBefore == 'n') {
						System.out.println("Enter Blood Type(A+,AB+,AB-,O+,O-,A-,B-,B+):");
						bloodType = scanner.next();
						a.setBloodType(c.BloodTypeCheck(scanner, bloodType));
						b.donate1(a.getDonorId(), a.getBloodType());
						b.donate(a.getDonorName());
					}
					if (donatedBefore == 'y') {
						System.out.println("Enter number of days since previous blood donation:");
						noOfDays = scanner.nextInt();
						if (noOfDays > 90) {
							System.out.println("Enter Blood Type(A+,AB+,AB-,O+,O-,A-,B-,B+):");
							bloodType = scanner.next();
							a.setBloodType(c.BloodTypeCheck(scanner, bloodType));
							b.donate(a.getDonorId(), a.getBloodType());
							b.donate(a.getDonorName());
						} else {
							System.out.println("Not Eligible To Donate");
							callMain.main(null);
						}
					}
				
			} else {
				System.out.println("Enter Donor Id");
				donorID = scanner.next();
				a.setDonorId(donorID);
				if(Jdbc.register(a,a.getDonorId(),scanner)) {
				System.out.println("have you consumed alcohol(y/n):");
					 consumeAlcohol=scanner.next();
					if(consumeAlcohol.equals("y"))
					{
						System.out.println("not eligible to donate");
						callMain.main(null);
					}
				System.out.println("Enter user name:");
				donorName = scanner.next();
				a.setDonorName(c.namecheck(scanner,donorName));
				while(true)
				{
					System.out.println("Enter Phone Number:");
					 phoneNumber = scanner.next();
				 try {
					 testMobile.validate(phoneNumber);
					 break;
				 }catch(InvalidPhoneNumber e) {
					 System.out.println(e);
				 }
				}
				System.out.println("Enter Location");
				String location=scanner.next();
				System.out.println("create password:");
				 password = scanner.next();
				a.setPassword(c.passwordCheck(scanner, password));
				System.out.println("Enter password again:");
				String confirmPassword = scanner.next();
				while (!password.matches(confirmPassword)) {
					System.out.println("Enter correctly:");
					confirmPassword = scanner.next();
				}
				System.out.println("          ------------Registered Succesfully-------------");
				System.out.println("Enter Blood Type(A+,AB+,AB-,O+,O-,A-,B-,B+):");
				bloodType = scanner.next();
				a.setBloodType(c.BloodTypeCheck(scanner, bloodType));
				a.setLocation(location);
				a.setPhonenumber(phoneNumber);
				b.donate(a.getDonorName(), a.getBloodType());
				Jdbc.insert(a.getDonorId(), a.getDonorName(), a.getBloodType(),a.getPhonenumber(),a.getLocation());
				}
			}
			
			} 
		else {
			System.out.println("Enter Recipient name:");
			String recipientName = scanner.next();
			a.setDonorName(c.namecheck(scanner,recipientName));
			System.out.println("Enter blood group(A+,AB+ve,AB-ve,o+ve,o-ve,A-,b-):");
			String bloodGroup = scanner.next();
			a.setBloodGroup(bloodGroup);
			if (bloodGroup.equalsIgnoreCase("Ab+")) {
				System.out.println("your universal recepient you can receive blood from every bloodgroup");
				System.out.println("Enter quantity of blood(unit):");
				int quantityReceived = scanner.nextInt();
				a.setRecipientName(recipientName);
				a.setQuantityReceived(c.quantityCheck(scanner, quantityReceived));
				b.bloodReceived(a.getRecipientName(), a.getBloodGroup(), a.getQuantityReceived());
			}
			for (String s : bloodgroup) {
				if (s.equalsIgnoreCase(bloodGroup)) {
					System.out.println("blood available");
					System.out.println("Enter quantity of blood(unit):");
					quantity = scanner.nextInt();
					a.setQuantity(c.quantityCheck(scanner, quantity));
					a.setRecipientName(recipientName);
					b.bloodReceived(a.getRecipientName(), a.getBloodGroup(), a.getQuantity());
				}
			}
			System.out.println("            --------Thankyou for contacting us--------         ");
		}
	}
}
class TestPhoneNumber{
	void validate(String mobile) throws InvalidPhoneNumber{
		if(!mobile.matches("[6789]{1}[0-9]{9}")) {
		throw new InvalidPhoneNumber("not valid number");
	}else {
		System.out.println("valid");
	}
	}
	}
