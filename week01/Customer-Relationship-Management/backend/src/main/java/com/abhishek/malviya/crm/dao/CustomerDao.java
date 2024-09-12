package com.abhishek.malviya.crm.dao;

import com.abhishek.malviya.crm.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {

    // Database code here

    // below two steps are direct injection
//    @Autowired
    SessionFactory sessionFactory;

    //COnstructor INjection

    public CustomerDao(SessionFactory sessionFactory)
    {
        super();
        this.sessionFactory=sessionFactory;
    }

    public String insertCustomer(Customer customer){
        Session session =sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //For insert, update delete we have to use Transactions
        //Session have save method
        session.save(customer);
        transaction.commit(); //to close transaction
        session.close(); // to close session
        return "Customer Inserted SuccessFully!.";
    }

    public List<Customer> getAll(){
        Session session =  sessionFactory.openSession();
        return session.createQuery("from Customer").list();
    }

    public Customer getCustomerById(int id){
        Session session = sessionFactory.openSession();
        Customer customer =  session.get(Customer.class,id);
        // here the alternate of get method is load method i.e. ,
        //Customer customer =session.load(Customer.class,id);
        // but the main difference is when the object is not found in db get will return null object
        //but load will will ObjectNotFoundException
        return customer;
    }

//    public String updateCustomer(Customer customer){
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(customer);
//        transaction.commit();
//        session.close();
//        return "Customer is updated Sucessfully!.";
//    }


    // NEW below

    public String updateCustomer(Customer customer) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    try {
        Customer existingCustomer = session.get(Customer.class, customer.getId());

        if (existingCustomer == null) {
            return "Customer not found!";
        }

        // Update only if the versions match (optimistic locking)
        session.update(customer);
        transaction.commit();
    } catch (org.hibernate.StaleObjectStateException e) {
        transaction.rollback();
        return "Error: Customer has already been updated by another transaction!";
    } finally {
        session.close();
    }

    return "Customer updated successfully!";
}
    // NEW above


    public String deleteCustomerById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();

        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
        transaction.commit();
        session.close();
        return "Customer deleted";
    }

    public String insertMultipleCustomers(List<Customer> customers){
        Session session = sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        for(Customer customer: customers){
            session.save(customer);
        }
        transaction.commit();
        session.close();
        return "Customers inserted Successfully!.";
    }

    public List<Customer> getCustomerByFirstName(String firstName){
        Session session =sessionFactory.openSession();
        Query<Customer> customerQuery = session.createQuery("from Customer c where c.firstName =: firstName", Customer.class);
        customerQuery.setParameter("firstName" , firstName);
        List<Customer> customerList =customerQuery.list();
        return customerList;
    }

    public List<Customer> getCustomerLessThanAge(int age){
        Session session =sessionFactory.openSession();
        Query<Customer> customerQuery = session.createQuery("from Customer c where c.age <: age", Customer.class);
        customerQuery.setParameter("age" , age);
        List<Customer> customerList =customerQuery.list();
        return customerList;
    }

    public List<Customer> getCustomerGreaterThanAge(int age){
        Session session =sessionFactory.openSession();
        Query<Customer> customerQuery = session.createQuery("from Customer c where c.age >: age", Customer.class);
        customerQuery.setParameter("age" , age);
        List<Customer> customerList =customerQuery.list();
        return customerList;
    }

    public List<Customer> getCustomerByAge(int age){
        Session session =sessionFactory.openSession();
        Query<Customer> customerQuery=session.createQuery("from Customer c where c.age =: age ",Customer.class);
        customerQuery.setParameter("age",age);
        List<Customer> customerList =customerQuery.list();
        return customerList;
    }


    public List<Customer> getCustomerByLastName(String lastName){
        Session session =sessionFactory.openSession();
        Query<Customer> customerQuery = session.createQuery("from Customer c where c.lastName =: lastName", Customer.class);
        customerQuery.setParameter("lastName" , lastName);
        List<Customer> customerList =customerQuery.list();
        return customerList;
    }

    public List<Customer> getCustomerByEmail(String email){
        Session session =sessionFactory.openSession();
        Query<Customer> customerQuery = session.createQuery("from Customer c where c.email =:email",Customer.class);
        customerQuery.setParameter("email",email);
        List<Customer> customerList = customerQuery.list();
        return customerList;
    }

    public List<Customer> getCustomerByMobileNumber(String mobileNumber){
        Session session = sessionFactory.openSession();
        Query<Customer> customerQuery = session.createQuery("from Customer c where c.mobileNumber =: mobileNumber", Customer.class);
        customerQuery.setParameter("mobileNumber", mobileNumber);
        List<Customer> customerList = customerQuery.list();
        return customerList;
    }

    public String updateFirstName(int id, String firstName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class , id);
        customer.setFirstName(firstName);
        transaction.commit();
        session.close();
        return "First Name Updated!";
    }
}
