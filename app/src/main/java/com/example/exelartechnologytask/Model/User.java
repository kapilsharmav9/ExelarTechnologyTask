package com.example.exelartechnologytask.Model;

public class User {
    private  String fname, lname, email_id, password, mobile, madd, madd_from, newsletter, address, timezone;

    public User(String fname, String lname, String email_id, String password, String mobile, String madd, String madd_from, String newsletter, String address, String timezone) {
        this.fname = fname;
        this.lname = lname;
        this.email_id = email_id;
        this.password = password;
        this.mobile = mobile;
        this.madd = madd;
        this.madd_from = madd_from;
        this.newsletter = newsletter;
        this.address = address;
        this.timezone = timezone;
    }

    public User() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMadd() {
        return madd;
    }

    public void setMadd(String madd) {
        this.madd = madd;
    }

    public String getMadd_from() {
        return madd_from;
    }

    public void setMadd_from(String madd_from) {
        this.madd_from = madd_from;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
