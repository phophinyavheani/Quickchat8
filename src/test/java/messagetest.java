/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author phoph
 */
public class messagetest {
    
   private Message message;
 
    //  Setup 
 
    @BeforeEach
    public void setUp() {
        message = new Message();
        message.messageID  = "1234567890";
        message.recipient  = "+27831234567";
        message.message    = "Hello World";
    }
 
    //  generateMessageID 
 
    @Test
    public void testGenerateMessageID_ReturnsNonNull() {
        assertNotNull(message.generateMessageID());
    }
 
    @Test
    public void testGenerateMessageID_IsBetween10And11Digits() {
        String id = message.generateMessageID();
        assertTrue(id.length() >= 10 && id.length() <= 11,
            "Expected ID length between 10 and 11 but was: " + id.length());
    }
 
    @Test
    public void testGenerateMessageID_ContainsOnlyDigits() {
        String id = message.generateMessageID();
        assertTrue(id.matches("\\d+"),
            "Expected only digits but got: " + id);
    }
 
    //  checkMessageID 
 
    @Test
    public void testCheckMessageID_ReturnsTrueForTenDigitID() {
        message.messageID = "1234567890";
        assertTrue(message.checkMessageID());
    }
 
    @Test
    public void testCheckMessageID_ReturnsTrueForShortID() {
        message.messageID = "12345";
        assertTrue(message.checkMessageID());
    }
 
    @Test
    public void testCheckMessageID_ReturnsFalseForIDOver10Chars() {
        // 11 digits
        message.messageID = "12345678901"; 
        assertFalse(message.checkMessageID());
    }
 
    //  checkRecipientCell 
 
    @Test
    public void testCheckRecipientCell_ValidNumberReturnSuccess() {
        assertEquals(
            "Cell phone number successfully captured.",
            message.checkRecipientCell("+27831234567")
        );
    }
 
    @Test
    public void testCheckRecipientCell_MissingPlusCodeReturnsFail() {
        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code.",
            message.checkRecipientCell("0831234567")
        );
    }
 
    @Test
    public void testCheckRecipientCell_WrongCountryCodeReturnsFail() {
        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code.",
            message.checkRecipientCell("+44831234567")
        );
    }
 
    @Test
    public void testCheckRecipientCell_TooShortNumberReturnsFail() {
        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code.",
            message.checkRecipientCell("+2783123")
        );
    }
 
    // validateMessageLength 
 
    @Test
    public void testValidateMessageLength_ShortMessageReturnsReady() {
        assertEquals("Message ready to send.", message.validateMessageLength("Hello"));
    }
 
    @Test
    public void testValidateMessageLength_Exactly250CharsReturnsReady() {
        String msg = "A".repeat(250);
        assertEquals("Message ready to send.", message.validateMessageLength(msg));
    }
 
    @Test
    public void testValidateMessageLength_Over250CharsReturnsError() {
        String msg = "A".repeat(260);
        assertEquals(
            "Message exceeds 250 characters by 10, please reduce the size.",
            message.validateMessageLength(msg)
        );
    }
 
    // createMessageHash 
 
    @Test
    public void testCreateMessageHash_ReturnsUpperCase() {
        String hash = message.createMessageHash(1);
        assertEquals(hash, hash.toUpperCase());
    }
 
    @Test
    public void testCreateMessageHash_ContainsFirstAndLastWord() {
        message.message = "Hello Beautiful World";
        String hash = message.createMessageHash(1);
        assertTrue(hash.contains("HELLO"));
        assertTrue(hash.contains("WORLD"));
    }
 
    @Test
    public void testCreateMessageHash_ContainsMessageNumber() {
        String hash = message.createMessageHash(3);
        assertTrue(hash.contains(":3:"));
    }
 
    @Test
    public void testCreateMessageHash_StartsWithFirstTwoCharsOfID() {
        message.messageID = "9900000000";
        String hash = message.createMessageHash(1);
        assertTrue(hash.startsWith("99"));
    }
 
    //  sentMessage 
 
    @Test
    public void testSentMessage_Choice1ReturnsSentConfirmation() {
        assertEquals("Message successfully sent", message.sentMessage(1));
    }
 
    @Test
    public void testSentMessage_Choice1IncrementsMessageCount() {
        message.sentMessage(1);
        assertEquals(1, message.messageCount);
    }
 
    @Test
    public void testSentMessage_Choice2ReturnsDeletePrompt() {
        assertEquals("Press 0 to delete the message", message.sentMessage(2));
    }
 
    @Test
    public void testSentMessage_Choice3ReturnsStoredConfirmation() {
        assertEquals("Message successfully stored", message.sentMessage(3));
    }
 
    @Test
    public void testSentMessage_InvalidChoiceReturnsInvalidOption() {
        assertEquals("Invalid option", message.sentMessage(9));
    }
 
    //  addMessage 
 
    @Test
    public void testAddMessage_Type1IncrementsSentCount() {
        message.addMessage("ID001", "HASH001", "+27831234567", "Hello", 1);
        assertEquals(1, message.sentCount);
    }
 
    @Test
    public void testAddMessage_Type2IncrementsDiscardedCount() {
        message.addMessage("ID001", "HASH001", "+27831234567", "Discard", 2);
        assertEquals(1, message.discardedCount);
    }
 
    @Test
    public void testAddMessage_Type3IncrementsStoredCount() {
        message.addMessage("ID001", "HASH001", "+27831234567", "Store", 3);
        assertEquals(1, message.storedCount);
    }
 
    @Test
    public void testAddMessage_Type1DoesNotAffectDiscardedOrStored() {
        message.addMessage("ID001", "HASH001", "+27831234567", "Hello", 1);
        assertEquals(0, message.discardedCount);
        assertEquals(0, message.storedCount);
    }
 
    //  getLongestMessage 
 
    @Test
    public void testGetLongestMessage_ReturnsLongest() {
        message.addMessage("ID001", "H1", "+27831234567", "Short", 1);
        message.addMessage("ID002", "H2", "+27831234567", "This is much longer message", 1);
        assertEquals("This is much longer message", message.getLongestMessage());
    }
 
    @Test
    public void testGetLongestMessage_EmptyWhenNoMessages() {
        assertEquals("", message.getLongestMessage());
    }
 
    @Test
    public void testGetLongestMessage_SingleMessageReturnsItself() {
        message.addMessage("ID001", "H1", "+27831234567", "Only one", 1);
        assertEquals("Only one", message.getLongestMessage());
    }
 
    //  searchByID 
 
    @Test
    public void testSearchByID_ReturnsCorrectMessage() {
        message.addMessage("ID001", "H1", "+27831234567", "Find me", 1);
        assertEquals("Find me", message.searchByID("ID001"));
    }
 
    @Test
    public void testSearchByID_ReturnsNotFoundForMissingID() {
        message.addMessage("ID001", "H1", "+27831234567", "Hello", 1);
        assertEquals("Message not found", message.searchByID("ID999"));
    }
 
    @Test
    public void testSearchByID_ReturnsCorrectOneAmongMany() {
        message.addMessage("ID001", "H1", "+27831234567", "First", 1);
        message.addMessage("ID002", "H2", "+27839876543", "Second", 1);
        message.addMessage("ID003", "H3", "+27831111111", "Third", 1);
        assertEquals("Second", message.searchByID("ID002"));
    }
 
    //  searchByRecipient 
 
    @Test
    public void testSearchByRecipient_ReturnsAllMatchingMessages() {
        message.addMessage("ID001", "H1", "+27831234567", "Hello", 1);
        message.addMessage("ID002", "H2", "+27831234567", "World", 1);
        String result = message.searchByRecipient("+27831234567");
        assertTrue(result.contains("Hello"));
        assertTrue(result.contains("World"));
    }
 
    @Test
    public void testSearchByRecipient_EmptyStringForUnknownRecipient() {
        message.addMessage("ID001", "H1", "+27831234567", "Hello", 1);
        assertEquals("", message.searchByRecipient("+27830000000"));
    }
 
    @Test
    public void testSearchByRecipient_DoesNotReturnOtherRecipientsMessages() {
        message.addMessage("ID001", "H1", "+27831234567", "For Alice", 1);
        message.addMessage("ID002", "H2", "+27839876543", "For Bob", 1);
        assertFalse(message.searchByRecipient("+27831234567").contains("For Bob"));
    }
 
    //  deleteByHash 
 
    @Test
    public void testDeleteByHash_ReturnsTrueForExistingHash() {
        message.addMessage("ID001", "HASH001", "+27831234567", "Delete me", 1);
        assertTrue(message.deleteByHash("HASH001"));
    }
 
    @Test
    public void testDeleteByHash_MessageBecomesNullAfterDeletion() {
        message.addMessage("ID001", "HASH001", "+27831234567", "Delete me", 1);
        message.deleteByHash("HASH001");
        assertNull(message.sentMessages[0]);
    }
 
    @Test
    public void testDeleteByHash_ReturnsFalseForNonExistentHash() {
        message.addMessage("ID001", "HASH001", "+27831234567", "Hello", 1);
        assertFalse(message.deleteByHash("HASH999"));
    }
 
    //  generateReport 
 
    @Test
    public void testGenerateReport_ContainsHashRecipientAndMessage() {
        message.addMessage("ID001", "HASH001", "+27831234567", "Report message", 1);
        String report = message.generateReport();
        assertTrue(report.contains("HASH001"));
        assertTrue(report.contains("+27831234567"));
        assertTrue(report.contains("Report message"));
    }
 
    @Test
    public void testGenerateReport_EmptyWhenNoSentMessages() {
        assertEquals("", message.generateReport());
    }
 
    @Test
    public void testGenerateReport_ContainsAllSentMessages() {
        message.addMessage("ID001", "H1", "+27831234567", "First", 1);
        message.addMessage("ID002", "H2", "+27839876543", "Second", 1);
        String report = message.generateReport();
        assertTrue(report.contains("First"));
        assertTrue(report.contains("Second"));
    }
 
    //  getSentMessages 
 
    @Test
    public void testGetSentMessages_ReturnsNonNullArray() {
        assertNotNull(message.getSentMessages());
    }
 
    @Test
    public void testGetSentMessages_ArrayLengthIsOneHundred() {
        assertEquals(100, message.getSentMessages().length);
    }
 
    @Test
    public void testGetSentMessages_ContainsAddedMessage() {
        message.addMessage("ID001", "H1", "+27831234567", "Test msg", 1);
        assertEquals("Test msg", message.getSentMessages()[0]);
    }
 
    // returnTotalMessages 
 
    @Test
    public void testReturnTotalMessages_StartsAtZero() {
        assertEquals(0, message.returnTotalMessages());
    }
 
    @Test
    public void testReturnTotalMessages_IncrementsWhenMessageSent() {
        message.sentMessage(1);
        message.sentMessage(1);
        assertEquals(2, message.returnTotalMessages());
    }
 
    @Test
    public void testReturnTotalMessages_DoesNotIncrementOnDiscard() {
        message.sentMessage(2);
        assertEquals(0, message.returnTotalMessages());
    }
}
 