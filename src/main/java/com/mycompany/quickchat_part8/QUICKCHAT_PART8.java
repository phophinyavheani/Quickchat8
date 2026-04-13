/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat_part8;

import java.util.Scanner;

/**
 *
 * @author phoph
 */
public class QUICKCHAT_PART8 {

    public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("=== USER REGISTRATION ===");

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter SA Cell Number (+27...): ");
        String phone = input.nextLine();

        String message = loginSystem.registerUser(username, password, phone);
        System.out.println(message);

        // Only allow login if registration is successful
        if (message.contains("successfully")) {

            System.out.println("\n=== LOGIN ===");

            System.out.print("Enter Username: ");
            String loginUser = input.nextLine();

            System.out.print("Enter Password: ");
            String loginPass = input.nextLine();

            boolean loginStatus = loginSystem.loginUser(loginUser, loginPass);
            String loginMessage = loginSystem.returnLoginStatus(loginStatus, firstName, lastName);

            System.out.println(loginMessage);
        }
    }
}
    


    


  

