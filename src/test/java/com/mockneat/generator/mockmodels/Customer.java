package com.mockneat.generator.mockmodels;

import java.util.Map;

/**
 * Created by andreinicolinciobanu on 22/02/2017.
 */
public class Customer {

    // Personal info
    public String UUID;
    public String firstName;
    public String lastName;
    public String userName;
    public String email;
    public String description;
    public String country;

    // Audit
    public String lastIpAddress;
    public String registeredMacAddress;

    // Financial
    public Map<String, String> creditCardsCVVs;

    public Customer() {
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastIpAddress() {
        return lastIpAddress;
    }

    public void setLastIpAddress(String lastIpAddress) {
        this.lastIpAddress = lastIpAddress;
    }

    public String getRegisteredMacAddress() {
        return registeredMacAddress;
    }

    public void setRegisteredMacAddress(String registeredMacAddress) {
        this.registeredMacAddress = registeredMacAddress;
    }

    public Map<String, String> getCreditCardsCVVs() {
        return creditCardsCVVs;
    }

    public void setCreditCardsCVVs(Map<String, String> creditCardsCVVs) {
        this.creditCardsCVVs = creditCardsCVVs;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "UUID='" + UUID + '\'' +
                ", fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", lastIpAddress='" + lastIpAddress + '\'' +
                ", registeredMacAddress='" + registeredMacAddress + '\'' +
                ", creditCardsCVVs=" + creditCardsCVVs +
                '}';
    }
}
