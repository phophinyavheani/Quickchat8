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
             boolean login;login = loginSystem.loginUser(loginUser, loginPass);
            System.out.println(loginSystem.returnLoginStatus(login, firstName, lastName));

            
            if (loginStatus) {

                System.out.println("\nWelcome to QuickChat");

                System.out.print("How many messages do you want to send? ");
                int total = input.nextInt();
                input.nextLine();

                int sentCount = 0;

                while (true) {

                    System.out.println("\n1) Send Messages");
                    System.out.println("2) Show recent messages");
                    System.out.println("3) Quit");

                    int choice = input.nextInt();
                    input.nextLine();

                    if (choice == 1) {

                        if (sentCount >= total) {
                            System.out.println("Message limit reached.");
                            continue;
                        }

                        Message msg = new Message();

                        msg.generateMessageID();

                        System.out.print("Enter recipient (+27...): ");
                        String recipient = input.nextLine();
                        System.out.println(msg.checkRecipientCell(recipient));
                        msg.setRecipient(recipient);

                        System.out.print("Enter message: ");
                        String messageText = input.nextLine();
                        System.out.println(msg.validateMessageLength(messageText));
                        msg.setMessage(messageText);

                        String hash = msg.createMessageHash(sentCount);

                        System.out.println("\nChoose option:");
                        System.out.println("1) Send");
                        System.out.println("2) Discard");
                        System.out.println("3) Store");

                        int action = input.nextInt();
                        input.nextLine();

                        System.out.println(msg.sentMessage(action));

                        if (action == 1) {
                            sentCount++;
                            System.out.println(msg.printMessages(hash));
                        }

                    } else if (choice == 2) {
                        System.out.println("Coming Soon.");
                    } else if (choice == 3) {
                        System.out.println("Goodbye!");
                        break;
                    }
                }

                System.out.println("Total messages sent: " + sentCount);
            }
        }
    }
}
        
    

    


    


  

