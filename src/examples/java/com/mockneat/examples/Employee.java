package com.mockneat.examples;

import java.time.LocalDate;
import java.util.List;

public class Employee {
    private String uniqueId;
    private Long id;
    private String fullName;
    private String companyEmail;
    private String personalEmail;
    private String salaryCreditCard;
    private Boolean external;
    private LocalDate hireDate;
    private LocalDate birthDate;
    private List<EmployeePC> pcs;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getSalaryCreditCard() {
        return salaryCreditCard;
    }

    public void setSalaryCreditCard(String salaryCreditCard) {
        this.salaryCreditCard = salaryCreditCard;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<EmployeePC> getPcs() {
        return pcs;
    }

    public void setPcs(List<EmployeePC> pcs) {
        this.pcs = pcs;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "uniqueId='" + uniqueId + '\'' +
                ", id=" + id +
                ", fullName='" + fullName + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", salaryCreditCard='" + salaryCreditCard + '\'' +
                ", external=" + external +
                ", hireDate=" + hireDate +
                ", birthDate=" + birthDate +
                ", pcs=" + pcs +
                '}';
    }
}
