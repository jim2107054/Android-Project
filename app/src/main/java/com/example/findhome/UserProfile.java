package com.example.findhome;

public class UserProfile {
    private String name, email, department, division, phone, postCode;

    // Default constructor (required by Firebase)
    public UserProfile() {}

    public UserProfile(String name, String email, String department, String division, String phone, String postCode) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.division = division;
        this.phone = phone;
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getDivision() {
        return division;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostCode() {
        return postCode;
    }
}
