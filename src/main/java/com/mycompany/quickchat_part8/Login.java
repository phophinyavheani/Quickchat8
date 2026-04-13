/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat_part8;

/**
 *
 * @author phoph
 */
class Login {
      String storedUsername;
     String storedPassword;
     String storedcellphonenumber;

     // =====create a methods=======
     
    // create methods of Username validation
     boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // create methods of check Password validation
     boolean checkPasswordComplexity(String password) {
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()].*");
        boolean correctLength = password.length() >= 8;

        return hasCapital && hasNumber && hasSpecial && correctLength;
    }

    // create a methods that contains checkcellphonenumber SA Phone validation using regex
     boolean checkCellphoneNumber(String phone) {
        return phone.matches("^\\+27\\d{9}$");
    }

    // create a Registration method
    public String registerUser(String username, String password, String phone) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellphoneNumber(phone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        storedUsername = username;
        storedPassword = password;
        
        return "User successfully registered.";
    }

    // create methods of Login validation
    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // Login message
    public String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}



    



