package com.abhishek.malviya.crm.service;

import com.abhishek.malviya.crm.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {

    String insertCustomer(Customer customer);

    List<Customer> getAll();

    Customer getCustomerById(int id);

    String updateCustomer(Customer customer);

    String deleteCustomerById(int id);

    String insertMultipleCustomers(List<Customer> customers);

    List<Customer> getCustomerByFirstName(String firstName);

    List<Customer> getCustomerLessThanAge(int age);

    List<Customer> getCustomerGreaterThanAge(int age);

    List<Customer> getCustomerByAge(int age);

    List<Customer> getCustomerByLastName(String lastName);

    List<Customer> getCustomerByEmail(String email);

    List <Customer> getCustomerByMobileNumber(String mobileNumber);

    String updateFirstName(int id, String firstName);
}
