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
public class messagetest {
    
    Message msg = new Message();

    @Test
    public void testMessageIDGenerated() {

        String id = msg.generateMessageID();

        assertNotNull(id);
        assertTrue(id.length() == 10);
    }

    @Test
    public void testMessageIDLengthCorrect() {

        msg.generateMessageID();

        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testRecipientNumberCorrect() {

        String result = msg.checkRecipientCell("+27831234567");

        assertEquals(
                "Cell phone number successfully captured.",
                result
        );
    }

    @Test
    public void testRecipientNumberIncorrect() {

        String result = msg.checkRecipientCell("0831234567");

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code.",
                result
        );
    }

    @Test
    public void testMessageLengthValid() {

        String result = msg.validateMessageLength(
                "Hi, how are you?"
        );

        assertEquals(
                "Message ready to send.",
                result
        );
    }

    @Test
    public void testMessageLengthTooLong() {

        String longMessage =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
              + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
              + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
              + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
              + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
              + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        String result = msg.validateMessageLength(longMessage);

        assertTrue(
                result.contains("Message exceeds 250 characters")
        );
    }

    @Test
    public void testSendMessage() {

        String result = msg.sentMessage(1);

        assertEquals(
                "Message successfully sent",
                result
        );
    }

    @Test
    public void testStoreMessage() {

        String result = msg.sentMessage(3);

        assertEquals(
                "Message successfully stored",
                result
        );
    }

    @Test
    public void testDiscardMessage() {

        String result = msg.sentMessage(2);

        assertEquals(
                "Press 0 to delete the message",
                result
        );
    }

    @Test
    public void testTotalMessages() {

        msg.sentMessage(1);
        msg.sentMessage(1);

        assertEquals(
                2,
                msg.returnTotalMessages()
        );
    }
}
   