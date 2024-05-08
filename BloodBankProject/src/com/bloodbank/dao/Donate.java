package com.bloodbank.dao;
import java.util.Scanner;

import com.bloodbank.model.BloodBank;
public abstract class Donate {
	BloodBank a=new BloodBank();
	public void donate(String donorId, String bloodType) {
		System.out.println("Donor ID:" + donorId + "\n" + "Blood Type:" + bloodType + "ve");
	}

	public void donate(String donorName,int quantity) {
		System.out.println("Quantity Of Blood Donated:" + quantity + "unit");
		System.out.println("              ----------Thanks For Donating Blood----------");
		 Donate.Feedback(donorName);
	}

	public abstract void donate(String donorName, String bloodType, int quantity);

	public void bloodReceived(String recipientName, String bloodGroup, int quantityReceived) {
		int cost = quantityReceived * 500;
		System.out.println("Recipient name:" + recipientName);
		System.out.println("quantity Of Blood Received:" + quantityReceived + "unit" + "\n" + "cost:" + cost + "Rs");
		Donate.Feedback(recipientName);
	}
	public static void Feedback(String user)
	{
		System.out.println("          --------------Feedback For The App----------------");
		String rating,review;
		Scanner feed=new Scanner(System.in);
		System.out.println("1.Rating\n2.Review\n3.exit");
		int feedBack=feed.nextInt();
		switch(feedBack)
		{
			case 1:
			{
				System.out.println("Enter Rating(*):");
				 rating=feed.next();
				while(true)
				{
				if(rating.matches("[*]{1,5}"))
				{
				System.out.println("user:"+user+"\nRating for the App:"+rating);
				break;
				}
				else
				{
					System.out.println("Enter valid Rating(*):");
					 rating=feed.next();
				}
				
			}
				break;
			}
			case 2:
			{
				System.out.println("Enter Review:");
				feed.next();
				review=feed.nextLine();
				 System.out.println("user:"+user+"\nReview for the app:"+review);
				 break;
			}
			case 3:
			{
				break;
			}
		}
	}
	}

