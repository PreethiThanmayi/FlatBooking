package com.cg.flat.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.exception.CustomerException;
import com.cg.flat.util.DBConnection;

public class CustomerDaoImpl implements ICustomerDao {
	static PreparedStatement preparedStatement;
	static Connection connection;
	
	public String addCustomer(CustomerEntity customerEntity)throws CustomerException, ClassNotFoundException{
		String cusId=null;
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement preparedStatement=null;		
			
			preparedStatement=connection.prepareStatement("insert into customer values(cust_seq.nextval,?,?,?,?,?,sysdate,?)");

			preparedStatement.setString(1,customerEntity.getCusName());			
			preparedStatement.setString(2,customerEntity.getCusNum());
			preparedStatement.setString(3,customerEntity.getCusEmail());
			preparedStatement.setString(4,customerEntity.getCusAadhar());
			preparedStatement.setString(5,customerEntity.getCusAddress());
			preparedStatement.setString(6,customerEntity.getCusFlatId());
									
			preparedStatement.executeUpdate();
		}
			catch(Exception e) 
			{
				System.out.println(e);
			}
		finally
		{
			try 
			{
				//resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) 
			{
				sqlException.printStackTrace();
				
			}
		}
		
			return cusId;
	}
		
				
	public CustomerEntity viewCustomerDetails(String cusId) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException
	{
		Connection connection=DBConnection.getConnection();
		
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		CustomerEntity customerEntity=null;
		
		try
		{
			preparedStatement=connection.prepareStatement("select * from customer where cusId=?");
			preparedStatement.setString(1,cusId);
			resultset=preparedStatement.executeQuery();
			
			if(resultset.next())
			{
				customerEntity = new CustomerEntity();
				customerEntity.setCusName(resultset.getString(1));
				customerEntity.setCusNum(resultset.getString(2));
				customerEntity.setCusEmail(resultset.getString(3));
				customerEntity.setCusAddress(resultset.getString(4));
				customerEntity.setCusAadhar(resultset.getString(5));
				//customerEntity.setCusBookedDate(resultset.getCusBookedDate(6));

			}
			
		}
		catch(Exception e)
		{
			
			throw new CustomerException(e.getMessage());
		}
		finally
		{
			try 
			{
				resultset.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				
				throw new CustomerException("Error in closing db connection");

			}
		}
		return customerEntity;
		
	}
}
		
