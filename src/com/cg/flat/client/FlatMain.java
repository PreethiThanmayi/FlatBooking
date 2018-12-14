package com.cg.flat.client;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.flat.exception.CustomerException;
import com.cg.flat.service.CustomerServiceImpl;
import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.exception.CustomerException;
import com.cg.flat.service.CustomerService;
import com.cg.flat.service.CustomerServiceImpl;

public class FlatMain {
	
	static Scanner scanner=new Scanner(System.in);
	static CustomerServiceImpl customerServiceImpl=null;
	static	CustomerService customerService=null;
	
public static void main(String[] args) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException {
	
	CustomerEntity customerEntity=null;
	String cusId=null;
	int opt=0;
	while(true)
	{
		//System.out.println("Enter your required area");
		System.out.println("Menu");
		
		System.out.println("1-Add Customer");
		System.out.println("2-view Customer");
		System.out.println("3-Display available Flats");
		System.out.println("4-Display Flat Details you have booked");
		System.out.println("5-exit");
		System.out.println("select an option");

		
	try
	{
		opt=scanner.nextInt();
		switch(opt) {
		case 1:while(customerEntity==null)
		{
			customerEntity=retrieveCustomerEntity();
		}
		try
		{
		    customerService = new CustomerServiceImpl();
		    cusId=customerService.addCustomer(customerEntity);
		    System.out.println("Flat is booked succesfully");
		   // System.out.println("your Flat Details are ");
			
		}
	
		finally
		{
			cusId=null;
			customerService=null;
			customerEntity=null;
			
		}
		break;
		case 2:
			customerServiceImpl=new CustomerServiceImpl();
			System.out.println("Enter Customer Id:");
			cusId = scanner.next();

			while (true) 
			{
				if ( customerServiceImpl.validatecusId(cusId)) 
				{
					break;
				} 
				else
				{
					System.err.println("Please enter correct customer Id ");
					cusId = scanner.next();
				}
			}

			customerEntity = getCustomerDetails(cusId);

			if (customerEntity != null)
			{
				System.out.println("Name               :"+ customerEntity.getCusName());
				System.out.println("Phone Number       :"+ customerEntity.getCusNum());
				System.out.println("Email Id           :"+ customerEntity.getCusEmail());
				System.out.println("Address            :"+ customerEntity.getCusAddress());
				System.out.println("Aadhar Details     :"+ customerEntity.getCusAadhar());
			} 
			else
			{
				System.err.println("There are no donation details associated with donor id "
								+ cusId);
			}

			break;
		case 3: 
			System.out.println("enter your id number to see your booked flat");
			cusId=scanner.next();
			while (true) 
			{
				if ( customerServiceImpl.validatecusId(cusId)) 
				{
					break;
				} 
				else
				{
					System.err.println("Please enter correct Flat Id ");
					cusId = scanner.next();
				}
			}
			System.out.println("Your Flat Details:");
			customerServiceImpl.getFlatDetails();
			break;
			
		case 4:
			System.out.println("Enter your flat Id to see your booked flat ");
			String flatId=scanner.next();
			
		case 5: 
			System.out.print("Exit Trust Application");
			System.exit(0);
			break;
			
		default:System.out.println("enter valid option");	
		}
		}
	catch(InputMismatchException ime)
	{
		System.out.println(ime);
	}
		
 }
	
}




private static CustomerEntity getCustomerDetails(String cusId) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException {
	CustomerEntity customerEntity = null;
	customerService = new CustomerServiceImpl();

	customerEntity = customerService.viewCustomerDetails(cusId);

	customerService = null;
	return customerEntity;
}

	

private static CustomerEntity retrieveCustomerEntity() {
	
	CustomerEntity customerEntity=new CustomerEntity();
	
		
	System.out.println("enter your Name ");
	String cusName=scanner.next();
	customerEntity.setCusName(cusName);
	
	System.out.println("enter your Number ");
	String cusNum=scanner.next();
	customerEntity.setCusNum(cusNum);
	
	System.out.println("enter your Email ");
	String cusEmail=scanner.next();
	customerEntity.setCusEmail(cusEmail);
	
	System.out.println("enter your Aadhar number ");
	String cusAadhar=scanner.next();
	customerEntity.setCusAadhar(cusAadhar);
	
	System.out.println("enter your Address ");
	String cusAddress=scanner.next();
	customerEntity.setCusAddress(cusAddress);
	
	System.out.println("enter the Flat Id which you wnat to book");
	String cusFlatId=scanner.next();
	customerEntity.setCusFlatId(cusFlatId);
	
	customerServiceImpl =new CustomerServiceImpl(); 
	
	try
	{
		customerServiceImpl.validateCustomer(customerEntity);
		return customerEntity;
	}
	
	catch(CustomerException customerException)
	{
		System.err.println(customerException.getMessage());
		System.exit(0);
		
	}
	
	return customerEntity;
}
}
	
	
	
	
	/*System.out.println("Enter your required area");
	System.out.println("Menu");
	
	System.out.println("1-Shollinganallur");
	System.out.println("2-Navallur");
	System.out.println("3-siruseri");

	int area=scanner.nextInt();
	
	do {
	switch(area)
	{
	
	case 1:do {
		System.out.println("Available Flat Types are: 1BH \t 2BH \t 3BH");
		   System.out.println("Enter your required flat type");
		   String type1=scanner.next();
			
		   switch(type1) {
			case "1BH":System.out.println(" Flat area:sithalapakkam,shollinganallur");
						System.out.println("Total sq.ft:480 ");
			            System.out.println("price:19L ");break;
			case "2BH":System.out.println(" Flat area:Nexterra,shollinganallur");
						System.out.println("Total sq.ft:605 ");
						System.out.println("price:29.7L ");break;
			case "3BH":System.out.println(" Flat area:Baashyaam Pinnacle Crest,shollinganallur");
						System.out.println("Total sq.ft:736 ");
						System.out.println("price:68.98L ");break;
			default:System.out.println("wrong input");

			}
			System.out.println("do u want to go back(y/n)");
			y=scanner.nextBoolean();
			}while(y!=true);
			break;
	
	case 2:System.out.println("Available Flat Types are:2BH \t 3BH ");
	   System.out.println("Enter your required flat type");
	   String type2=scanner.next();
		do {
	   switch(type2) {
		case "3BH":System.out.println(" Flat area:Claritas,Old Mahabalipuram road,Navalur");
					System.out.println("Total sq.ft:1414 ");
		            System.out.println("price:86.86L ");break;
		case "2BH":System.out.println(" Flat area:Thalambur,Navalur");
					System.out.println("Total sq.ft:753 ");
					System.out.println("price:24.47L ");break;
		default:System.out.println("wrong input");

		
		}
		System.out.println("do u want to go back(y/n)");
		y=scanner.nextBoolean();
		}while(y!=true);
		break;
	
	case 3:System.out.println("Available Flat Types are: 1-1BH \t 2-2BH \t 3-3BH");
	   System.out.println("Enter your required flat type");
	   String type3=scanner.next();
		do {
	   switch(type3) {
		case "1BH":System.out.println(" Flat area:Shantiniketan Vega,siruseri");
					System.out.println("Total sq.ft:580 ");
		            System.out.println("price:20L ");break;
		case "2BH":System.out.println(" Flat area:semmancheri,Chennai Green apple,siruseri");
					System.out.println("Total sq.ft:654 ");
					System.out.println("price:29.43L ");break;
		case "3BH":System.out.println(" Flat area:L and T Eden Park Phase 2,siruseri");
					System.out.println("Total sq.ft:836 ");
					System.out.println("price:88.7L ");break;
					default:System.out.println("wrong input");
		}
	   System.out.println("do u want to go back(y/n)");
		y=scanner.nextBoolean();
		
		}while(y!=true);
			break;
	default:System.out.println("wrong input");

	}
	}while(area!=3);*/
	


