/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
/**
 *
 * @author phoph
 */
public class massagemaneger {
   


    ArrayList<String> sentMessages = new ArrayList<>();
    ArrayList<String> storedMessages = new ArrayList<>();
    ArrayList<String> discardedMessages = new ArrayList<>();
    ArrayList<String> messageHashes = new ArrayList<>();
    ArrayList<String> messageIDs = new ArrayList<>();
    ArrayList<String> recipients = new ArrayList<>();

    // Add message
    public void addMessage(String id, String hash, String recipient, String message, int type) {

        messageIDs.add(id);
        messageHashes.add(hash);
        recipients.add(recipient);

        if (type == 1) {
            sentMessages.add(message);
        } else if (type == 2) {
            discardedMessages.add(message);
        } else if (type == 3) {
            storedMessages.add(message);
        }
    }

    // Display sender + recipient
    public void displaySendersAndRecipients() {
        for (int i = 0; i < recipients.size(); i++) {
            System.out.println("Recipient: " + recipients.get(i));
        }
    }

    // Longest message
    public String getLongestMessage() {
        String longest = "";
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    // Search by ID
    public void searchByID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                System.out.println("Message: " + sentMessages.get(i));
                return;
            }
        }
        System.out.println("Message not found.");
    }

    // Search by recipient
    public void searchByRecipient(String number) {
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(number)) {
                System.out.println(sentMessages.get(i));
            }
        }
    }

    // Delete by hash
    public void deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                System.out.println("Message: " + sentMessages.get(i) + " successfully deleted.");
                sentMessages.remove(i);
                return;
            }
        }
    }

    // Report
    public void displayReport() {
        for (int i = 0; i < sentMessages.size(); i++) {
            System.out.println("\nMessage Hash: " + messageHashes.get(i));
            System.out.println("Recipient: " + recipients.get(i));
            System.out.println("Message: " + sentMessages.get(i));
        }
    }
}
    

