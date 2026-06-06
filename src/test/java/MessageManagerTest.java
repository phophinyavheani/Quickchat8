/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author phoph
 */


public class MessageManagerTest {

    MessageManager manager = new MessageManager();

    @Test
    public void testAddMessage() {

        manager.addMessage(  "001","HASH001", "+27831234567", "Hello World",  1);

        assertEquals(1, manager.sentMessages.size());
    }

    @Test
    public void testLongestMessage() {

        manager.addMessage("001","HASH001","+27831234567", "Short",3 );

        manager.addMessage(
                "002",
                "HASH002",
                "+27831234567",
                "This is a much longer message",
                3
        );

        assertEquals(
                "This is a much longer message",
                manager.getLongestMessage()
        );
    }

    @Test
    public void testSentMessagesStored() {

        manager.addMessage(
                "001",
                "HASH001",
                "+27831234567",
                "Message One",
                1
        );

        assertTrue(
                manager.sentMessages.contains("Message One")
        );
    }

    @Test
    public void testStoredMessagesStored() {

        manager.addMessage(  "001", "HASH001", "+27831234567","Stored Message", 3);

        assertTrue(
                manager.storedMessages.contains("Stored Message")
        );
    }

    @Test
    public void testDiscardedMessagesStored() {

        manager.addMessage("001","HASH001",  "+27831234567", "Discarded Message",    2 );

        assertTrue(
                manager.discardedMessages.contains("Discarded Message")
        );
    }

    @Test
    public void testMessageIDStored() {

        manager.addMessage("12345", "HASH001","+27831234567", "Hello", 1 );

        assertTrue(
                manager.messageIDs.contains("12345")
        );
    }

    @Test
    public void testMessageHashStored() {

        manager.addMessage( "12345","HASH123", "+27831234567","Hello",   1 );

        assertTrue(
                manager.messageHashes.contains("HASH123")
        );
    }

    @Test
    public void testRecipientStored() {

        manager.addMessage("12345","HASH123", "+27831234567", "Hello", 1);

        assertTrue(
                manager.recipients.contains("+27831234567")
        );
    }
}