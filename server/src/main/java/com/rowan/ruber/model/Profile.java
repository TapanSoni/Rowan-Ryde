package com.rowan.ruber.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

/**
 * Class to set up the JPA Entity for the Profile table in the database
 */
@Entity
@Table(name = "profile")
public class Profile implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profileID")
    private int id;

    private String name;

    @Column(name="EmailAddress")
    private String email;

    // Refers to address table
    // ** Leaving out ON UPDATE ON DELETE for now since they are defined in MySQL - will add later if needed.
    @OneToOne
    @JoinColumn(name="AddressID")
    private Address address;

    @Column(name="CreatedDate")
    private Date createdDate;

    @OneToMany(mappedBy="profile")
    private List<Schedule> schedules = new ArrayList<Schedule>(); // Maintain bi-directional 1 to Many w/ Schedule

    /** 
     *  Default constructor for JPA.
     *  It should not be used directly as no values will be initialized.
     */
    public Profile(){
        
    }

    public Profile(String name, String email, Address address, Date createdDate){
        this.name = name;
        this.email = email;
        this.address = address;
        this.createdDate = createdDate;
    }

    /** Returns the string representation for Profile*/
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        return String.format("Name: %s %n Email: %s %n AddressID: %d %n Created Date: %s %n", 
                            name, email, address.toString(), sdf.format(createdDate));
    }

    /** Gets the primary ID*/
    public int getID(){
        return id;
    }

    /** Gets the name */
    public String getName(){
        return name;
    }

    /** Gets the email */
    public String getEmail(){
        return email;
    }

    /** Gets the address */
    public Address getAddressID(){
        return address;
    }

    /** Gets the createdDate */
    public Date getCreatedDate(){
        return createdDate;
    }

    /** Sets the name */
    public void setName(String name){
        this.name = name;
    }

    /** Sets the email */
    public void setEmail(String email){
        this.email = email;
    }
}
