/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Random;
/**
 *
 * @author phoph
 */
class Message {
    private String messageID;
    private String recipient;
    private String message;
    private static int messageCount = 0;

    // Generate random 10-digit message ID
    public String generateMessageID() {
        Random rand = new Random();
        long num = (long)(rand.nextDouble() * 10000000000L);
        messageID = String.format("%010d", num);
        return messageID;
    }

    // Check message ID length
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Check recipient number
    public String checkRecipientCell(String recipient) {
        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
    }

    // Check message length
    public String validateMessageLength(String msg) {
        if (msg.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by " + (msg.length() - 250) + ", please reduce the size.";
        }
    }

    // Create message hash
    public String createMessageHash(int messageNumber) {
        String[] words = message.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Send / Store / Discard
    public String sentMessage(int choice) {
        if (choice == 1) {
            messageCount++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // Print message
    public String printMessages(String hash) {
        return "Message ID: " + messageID +
               "\nMessage Hash: " + hash +
               "\nRecipient: " + recipient +
               "\nMessage: " + message;
    }

    public int returnTotalMessages() {
        return messageCount;
    }

    // Setters
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
