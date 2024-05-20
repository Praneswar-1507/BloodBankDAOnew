package com.bloodbank.dao;

public class BloodBankAbstract extends Donate {
	public  void donate(String donorName,String bloodType)
	{
	System.out.println("Donor name:"+donorName+"\n"+"Blood Type:"+bloodType+"ve");
	System.out.println("              ---------Thanks For Donating Blood----------");
	Donate.Feedback(donorName);
	}
}
