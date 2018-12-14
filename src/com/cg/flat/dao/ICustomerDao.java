package com.cg.flat.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.exception.CustomerException;

public interface ICustomerDao {

	String addCustomer(CustomerEntity customerEntity) throws CustomerException, ClassNotFoundException, FileNotFoundException;
	
	CustomerEntity viewCustomerDetails(String cusId) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException;

}
