package com.example.msuba.andre_login.Adapters;

/**
 * Created by msuba on 1/6/2018.
 */

public class UserCreds
{
    public UserCreds(){}
    public static String Uname,Pass,Email;

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUname() {

        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }
}
