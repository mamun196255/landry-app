package com.example.laundry_app.Login;

import java.security.MessageDigest;

public class onCheckPassword {

    public boolean validPassword(String password){
        int count=0;
        if(password.length()<6){
            return false;
        }
        else{
            for(int i=0;i<password.length();i++){

                if(password.charAt(i)=='!'||password.charAt(i)=='@'||password.charAt(i)=='#'||
                        password.charAt(i)=='$'|| password.charAt(i)=='%'|| password.charAt(i)=='^'||
                        password.charAt(i)=='&'||password.charAt(i)=='*'||password.charAt(i)=='+'||
                        password.charAt(i)=='='||password.charAt(i)=='?'||password.charAt(i)=='-'
                )
                {
                    count++;
                    break;
                }
            }
            if(count==1){
                return true;
            }
            else{
                return false;
            }
        }
    }
    public boolean validEmail(String email) {
        String matchString = "@gmail.com";

        if (email.length() < matchString.length()) {
            return false;
        }
        String lastPart = email.substring(email.length() - matchString.length());
        return matchString.equals(lastPart);
    }

    public boolean ValidNumber(String string){
        if(string.length()==11){
            return true;
        }
        else {
            return false;
        }
    }

    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
