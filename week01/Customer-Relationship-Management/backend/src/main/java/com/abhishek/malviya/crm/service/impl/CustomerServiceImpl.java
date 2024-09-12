package com.abhishek.malviya.crm.service.impl;

import com.abhishek.malviya.crm.dao.CustomerDao;
import com.abhishek.malviya.crm.entity.Customer;
import com.abhishek.malviya.crm.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao;
    public CustomerServiceImpl(CustomerDao customerDao){
        super();
        this.customerDao=customerDao;
    }
    @Override
    public String insertCustomer(Customer customer){

        String msg = customerDao.insertCustomer(customer);
        return msg;
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();

    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public String updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public String deleteCustomerById(int id) {
        return customerDao.deleteCustomerById(id);
    }

    @Override
    public String insertMultipleCustomers(List<Customer> customers) {
        return customerDao.insertMultipleCustomers(customers);
    }

    @Override
    public List<Customer> getCustomerByFirstName(String firstName) {
        return customerDao.getCustomerByFirstName(firstName);
    }

    @Override
    public List<Customer> getCustomerLessThanAge(int age) {
        return customerDao.getCustomerLessThanAge(age);
    }

    @Override
    public List<Customer> getCustomerGreaterThanAge(int age) {
        return customerDao.getCustomerGreaterThanAge(age);
    }

    @Override
    public List<Customer> getCustomerByAge(int age) {
        return customerDao.getCustomerByAge(age);
    }

    @Override
    public List<Customer> getCustomerByLastName(String lastName) {
        return customerDao.getCustomerByLastName(lastName);
    }

    @Override
    public List<Customer> getCustomerByEmail(String email) {
        return customerDao.getCustomerByEmail(email);
    }

    @Override
    public List<Customer> getCustomerByMobileNumber(String mobileNumber) {
        return customerDao.getCustomerByMobileNumber(mobileNumber);
    }

    @Override
    public String updateFirstName(int id, String firstName) {
        return customerDao.updateFirstName(id,firstName);
    }


}
