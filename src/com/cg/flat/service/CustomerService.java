package com.cg.flat.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.exception.CustomerException;

public interface CustomerService {

	CustomerEntity viewCustomerDetails(String cusId) throws ClassNotFoundException, FileNotFoundException, CustomerException, SQLException;

	String addCustomer(CustomerEntity customerEntity) throws ClassNotFoundException, FileNotFoundException, CustomerException;

}
