package com.example.harwaqt;

public class DataModel {
    private String name;
    private String fatherName;
    private String dateOfBirth;
    private String education;
    private String maritalStatus;
    private String occupation;
    private String monthlyIncome;
    private String email;
    private String phone;
    private String city;
    private String address;
    private String method;
    private String holderName;
    private String accountNumber1;
    private String confirmAccountNum;
    private String loanType;
    private String loanAmount1;
    private String loanDuration1;


    public DataModel() {
        // Default constructor required for calls to DataSnapshot.getValue(DataModel.class)
    }

    public DataModel(String name, String fatherName, String dateOfBirth, String education, String maritalStatus, String occupation,
                     String monthlyIncome, String email, String phone, String city, String address, String method, String holderName, String accountNumber1, String confirmAccountNum,
                     String loanType, String loanAmount1, String loanDuration1) {
        this.name = name;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.education = education;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.monthlyIncome = monthlyIncome;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.method = method;
        this.holderName = holderName;
        this.accountNumber1 = accountNumber1;
        this.confirmAccountNum = confirmAccountNum;
        this.loanType = loanType;
        this.loanAmount1 = loanAmount1;
        this.loanDuration1 = loanDuration1;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType() {
        this.loanType = loanType;
    }

    public String getLoanAmount1() {
        return loanAmount1;
    }

    public void setLoanAmount1() {
        this.loanAmount1 = loanAmount1;
    }

    public String getLoanDuration1() {
        return loanDuration1;
    }

    public void setLoanDuration1() {
        this.loanDuration1 = loanDuration1;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod() {
        this.method = method;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName() {
        this.holderName = holderName;
    }

    public String getAccountNumber1() {
        return accountNumber1;
    }

    public void setAccountNumber1() {
        this.accountNumber1 = accountNumber1;
    }

    public String getConfirmAccountNum() {
        return confirmAccountNum;
    }

    public void setConfirmAccountNum() {
        this.confirmAccountNum = confirmAccountNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone() {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity() {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress() {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
