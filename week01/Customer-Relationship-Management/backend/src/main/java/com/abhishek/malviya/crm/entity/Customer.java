package com.abhishek.malviya.crm.entity;

import jakarta.persistence.*;

import java.security.Identity;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    //@Column(name = "age")
    private int age;

    @Version  // NEW
    private int version;  // NEW

    //constructor
    public Customer(){
        // This no argument constructor is important for get method.
    }
    public Customer(int id, int age, String mobileNumber, String email, String lastName, String firstName) {
        this.id = id;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }


    //Getter and Setter
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
