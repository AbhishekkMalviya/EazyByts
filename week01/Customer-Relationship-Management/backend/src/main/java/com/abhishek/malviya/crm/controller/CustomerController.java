package com.abhishek.malviya.crm.controller;

import com.abhishek.malviya.crm.entity.Customer;
import com.abhishek.malviya.crm.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")//NEW
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }
    @PostMapping("/insert")
    public String insertCustomer(@RequestBody Customer customer){
        String msg = customerService.insertCustomer(customer);
        return msg;
    }


    @GetMapping
    public List<Customer> getAll(){
        List<Customer> list= customerService.getAll();
        return list;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id){
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }

    @PutMapping
    public String updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }


    @DeleteMapping("/{id}")
    public String deleteCustomerById(@PathVariable int id){
        return customerService.deleteCustomerById(id);
    }

    @PostMapping("/insertMultiple")
    public String insertMultipleCustomers(@RequestBody List<Customer> customers) {
        return customerService.insertMultipleCustomers(customers);
    }

    @GetMapping("/byName/{firstName}")
    public List<Customer> getCustomerByFirstName(@PathVariable String firstName) {
        return customerService.getCustomerByFirstName(firstName);
    }

    @GetMapping("/byAge/lessThan/{age}")
    public List<Customer> getCustomerLessThanAge(@PathVariable int age) {
        return customerService.getCustomerLessThanAge(age);
    }

    @GetMapping("/byAge/greaterThan/{age}")
    public List<Customer> getCustomerGreaterThanAge(@PathVariable int age) {
        return customerService.getCustomerGreaterThanAge(age);
    }

    @GetMapping("/byAge/{age}")
    public List<Customer> getCustomerByAge(@PathVariable int age){
        return customerService.getCustomerByAge(age);
    }

    @GetMapping("/byLastName/{lastName}")
    public List<Customer> getCustomerByLastName(@PathVariable String lastName){
        return customerService.getCustomerByLastName(lastName);
    }

    @GetMapping("/byEmail/{email}")
    public List<Customer> getCustomerByEmail(@PathVariable String email){
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/byMobileNumber/{mobileNumber}")
    public List<Customer> getCustomerByMobileNumber(@PathVariable String mobileNumber){
        return customerService.getCustomerByMobileNumber(mobileNumber);
    }

    @PutMapping("updateFirstName/{id}/{firstName}")
    String updateFirstName(@PathVariable int id,@PathVariable String firstName){
        return customerService.updateFirstName(id , firstName);
    }
}