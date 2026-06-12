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
    
//  Message Fields 
    String messageID;
    String recipient;
    String message;
    int messageCount = 0;
 
    //  Message Manager Arrays 
    String[] sentMessages     = new String[100];
    String[] storedMessages   = new String[100];
    String[] discardedMessages = new String[100];
    String[] messageHashes    = new String[100];
    String[] messageIDs       = new String[100];
    String[] recipients       = new String[100];
 
    int sentCount      = 0;
    int storedCount    = 0;
    int discardedCount = 0;
 
    //  Message ID 
 
    // Generates a random 10-digit message ID. 
    public String generateMessageID() {
        Random rand = new Random();
        long num = (long) (rand.nextDouble() * 20000000000L);
        messageID = String.format("%010d", num);
        return messageID;
    }
 
    // Returns true if the message ID is 10 characters or fewer. 
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
 
    // Validation 
 
    /** Validates that the recipient number uses the +27 format. */
    public String checkRecipientCell(String recipient) {
        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
    }
 
    /** Validates that the message does not exceed 250 characters. */
    public String validateMessageLength(String msg) {
        if (msg.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by " + (msg.length() - 250) + ", please reduce the size.";
        }
    }
 
    //  Hash 
 
    // Creates a hash from the message ID, message number, and first/last words. 
    public String createMessageHash(int messageNumber) {
        String[] words = message.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }
 
    //  Send / Store / Discard ─
 
    
    public String sentMessage(int choice) {
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
 
    //  Add to Arrays 
 
    
     // Adds a message to the appropriate array based on type:
     // 1 = Sent, 2 = Discarded, 3 = Stored
     
    public void addMessage(String id, String hash, String recipient, String message, int type) {
        if (type == 1) {
            sentMessages[sentCount]   = message;
            messageIDs[sentCount]     = id;
            messageHashes[sentCount]  = hash;
            recipients[sentCount]     = recipient;
            sentCount++;
        } else if (type == 2) {
            discardedMessages[discardedCount] = message;
            discardedCount++;
        } else if (type == 3) {
            storedMessages[storedCount] = message;
            storedCount++;
        }
    }
 
    //  Display 
 
    // Prints all recipients of sent messages to the console. 
    public void displayRecipients() {
        for (int i = 0; i < sentCount; i++) {
            System.out.println(recipients[i]);
        }
    }
 
    // Returns a formatted string of a single message's details. 
    public String printMessages(String hash) {
        return "Message ID: " + messageID
             + "\nMessage Hash: " + hash
             + "\nRecipient: " + recipient
             + "\nMessage: " + message;
    }
 
    //  Search 
 
    // Finds and returns a sent message by its ID. 
    public String searchByID(String id) {
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i].equals(id)) {
                return sentMessages[i];
            }
        }
        return "Message not found";
    }
 
    // Returns all sent messages for a given recipient number. 
    public String searchByRecipient(String number) {
        String result = "";
        for (int i = 0; i < sentCount; i++) {
            if (recipients[i].equals(number)) {
                result += sentMessages[i] + "\n";
            }
        }
        return result;
    }
 
    //  Longest Message 
 
    // Returns the longest message among all sent messages. 
    public String getLongestMessage() {
        String longest = "";
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }
        return longest;
    }
 
    //  Delete 
 
    // Deletes a sent message by its hash. Returns true if found and deleted. 
    public boolean deleteByHash(String hash) {
        for (int i = 0; i < sentCount; i++) {
            if (messageHashes[i].equals(hash)) {
                sentMessages[i] = null;
                return true;
            }
        }
        return false;
    }
 
    //  Report 
 
    // Generates a full report of all sent messages. 
    public String generateReport() {
        String report = "";
        for (int i = 0; i < sentCount; i++) {
            report += "\nMessage Hash: " + messageHashes[i]
                    + "\nRecipient: "    + recipients[i]
                    + "\nMessage: "      + sentMessages[i]
                    + "\n";
        }
        return report;
    }
 
    //  Counts 
 
    // Returns the total number of messages sent in this session. 
    public int returnTotalMessages() {
        return messageCount;
    }
 
    // Returns the full sent messages array.
    public String[] getSentMessages() {
        return sentMessages;
    }
 
    //  Setters 
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    //  JSON Storag
 
    // Saves the current message to a JSON file (messages.json). 
    public void storeMessageJSON(String hash, String status) {
        String json = "{\n"
                + "\"messageID\": \""  + messageID  + "\",\n"
                + "\"recipient\": \"" + recipient  + "\",\n"
                + "\"message\": \""   + message    + "\",\n"
                + "\"hash\": \""      + hash       + "\",\n"
                + "\"status\": \""    + status     + "\"\n"
                + "},\n";
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
 