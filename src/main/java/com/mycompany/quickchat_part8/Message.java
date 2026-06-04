/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat_part8;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author phoph
 */
class Message {
    
String messageID;
     String recipient;
     String message;
     int messageCount = 0;

    // Generate the random 10-digit message ID
     String generateMessageID() {
        Random rand = new Random();
        long num = (long)(rand.nextDouble() * 20000000000L);
        messageID = String.format("%010d", num);
        return messageID;
    }

    // Check the message of ID length
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Check the recipient number
     String checkRecipientCell(String recipient) {
        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
    }

    // Check the message length which you can able to send
     String validateMessageLength(String msg) {
        if (msg.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by " + (msg.length() - 250) + ", please reduce the size.";
        }
    }

    // Create the message hash
     String createMessageHash(int messageNumber) {
        String[] words = message.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Send & Store & Discard
     String sentMessage(int choice) {
        if (choice == 1) {
            messageCount++;
            return "Message successfully sent";
        } else if (choice == 2) {
            return "Press 0 to delete the message";
        } else if (choice == 3) {
            return "Message successfully stored";
        } else {
            return "Invalid option";
        }
    }

    // Print the  message
     String printMessages(String hash) {
        return "Message ID: " + messageID +"\nMessage Hash: " + hash + "\nRecipient: " + recipient +"\nMessage: " + message;
    }

    public int returnTotalMessages() {
        return messageCount;
    }

    // create the Setters
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public void storeMessageJSON(String hash, String status) {

        String json = "{\n" + "\"messageID\": \"" + messageID + "\",\n" + "\"recipient\": \"" + recipient + "\",\n" +"\"message\": \"" + message + "\",\n" + "\"hash\": \"" + hash + "\",\n" +"\"status\": \"" + status + "\"\n" +"},\n";

        try {

            FileWriter writer = new FileWriter("messages.json", true);

            writer.write(json);

            writer.close();

            System.out.println("Message stored in JSON file.");

        } catch (IOException e) {

            System.out.println("Error saving message.");
        }
    }
    
    
    
    
 }
