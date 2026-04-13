/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author phoph
 */
public class JUnitTest {
    
   
     private java loginSystem;

    @BeforeEach
    void setUp() {
        loginSystem = new java();
    }

    
    // create test of checkUserName 
    

    @Test
    void testCheckUserName_ValidUsername() {
        // Contains underscore and length <= 5
        assertTrue(loginSystem.checkUserName("po_g"), 
            "Username with underscore and length <= 5 should be valid");
    }

    @Test
    void testCheckUserName_NoUnderscore() {
        assertFalse(loginSystem.checkUserName("pogp"), 
            "Username without underscore should be invalid");
    }

    @Test
    void testCheckUserName_TooLong() {
        // teste weather username Has underscore but length > 5
        assertFalse(loginSystem.checkUserName("pogp_mod"), 
            "Username longer than 5 characters should be invalid");
    }

    @Test
    void testCheckUserName_ExactlyFiveChars() {
        assertTrue(loginSystem.checkUserName("p_mod"), 
            "Username with exactly 5 characters and underscore should be valid");
    }

    @Test
    void testCheckUserName_OnlyUnderscore() {
        assertTrue(loginSystem.checkUserName("_"), 
            "A single underscore is technically valid (length 1, contains '_')");
    }

    
    // create a test to checkPasswordComplexity 
    

    @Test
    void testCheckPasswordComplexity_ValidPassword() {
        assertTrue(loginSystem.checkPasswordComplexity("Mufha@26"), 
            "Password with capital, number, special char, and 8+ chars should be valid");
    }

    @Test
    void testCheckPasswordComplexity_NoCapital() {
        assertFalse(loginSystem.checkPasswordComplexity("mufha@26"), 
            "Password without a capital letter should be invalid");
    }

    @Test
    void testCheckPasswordComplexity_NoNumber() {
        assertFalse(loginSystem.checkPasswordComplexity("Mufhae&"), 
            "Password without a number should be invalid");
    }

    @Test
    void testCheckPasswordComplexity_NoSpecialChar() {
        assertFalse(loginSystem.checkPasswordComplexity("Mufha45"), 
            "Password without a special character should be invalid");
    }

    @Test
    void testCheckPasswordComplexity_TooShort() {
        assertFalse(loginSystem.checkPasswordComplexity("Mu2%"), 
            "Password shorter than 8 characters should be invalid");
    }

    @Test
    void testCheckPasswordComplexity_ExactlyEightChars() {
        assertTrue(loginSystem.checkPasswordComplexity("Muf@67"), 
            "Password with exactly 8 characters meeting all requirements should be valid");
    }

 
    //  create a test checkCellphoneNumber 
    

    @Test
    void testCheckCellphoneNumber_ValidNumber() {
        assertTrue(loginSystem.checkCellphoneNumber("+27766223171"), 
            "Valid cell phone number starting with +27 and 9 digits should pass");
    }

    @Test
    void testCheckCellphoneNumber_MissingCountryCode() {
        assertFalse(loginSystem.checkCellphoneNumber("0766223171"), 
            "Number without +27 prefix should be invalid");
    }

    @Test
    void testCheckCellphoneNumber_TooShort() {
        assertFalse(loginSystem.checkCellphoneNumber("+27766223"), 
            "Number with fewer than 9 digits after +27 should be invalid");
    }

    @Test
    void testCheckCellphoneNumber_TooLong() {
        assertFalse(loginSystem.checkCellphoneNumber("+2776622317138"), 
            "Number with more than 9 digits after +27 should be invalid");
    }

    @Test
    void testCheckCellphoneNumber_WithLetters() {
        assertFalse(loginSystem.checkCellphoneNumber("+27dfz067891"), 
            "Number containing letters should be invalid");
    }

    
    // create  to test registerUser 
    

    @Test
    void testRegisterUser_Success() {
        String result = loginSystem.registerUser("po_g", "Mufha@67", "+27766223171");
        assertEquals("User successfully registered.", result, 
            "Valid inputs should result in successful registration");
    }

    @Test
    void testRegisterUser_InvalidUsername() {
        String result = loginSystem.registerUser("pogpmod", "Mufha@67", "+27766223171");
        assertTrue(result.contains("Username is not correctly formatted"), 
            "Invalid username should return appropriate error message");
    }

    @Test
    void testRegisterUser_InvalidPassword() {
        String result = loginSystem.registerUser("po_g", "mufhata", "+27766223171");
        assertTrue(result.contains("Password is not correctly formatted"), 
            "Weak password should return appropriate error message");
    }

    @Test
    void testRegisterUser_InvalidPhone() {
        String result = loginSystem.registerUser("po_g", "Mufha@67", "0766223171");
        assertTrue(result.contains("Cell phone number incorrectly formatted"), 
            "Invalid phone number should return appropriate error message");
    }

    @Test
    void testRegisterUser_AllInvalid_UsernameCheckedFirst() {
        // Username is checked first, so that error should be returned
        String result = loginSystem.registerUser("popy", "rudzani", "phone12");
        assertTrue(result.contains("Username is not correctly formatted"), 
            "Username validation should be checked before other validations");
    }

    
    // create a test loginUser 
    

    @Test
    void testLoginUser_CorrectCredentials() {
        loginSystem.registerUser("po_g", "Mufha@67", "+27766223171");
        assertTrue(loginSystem.loginUser("po_g", "Mufha@67"), 
            "Login with correct credentials should return true");
    }

    @Test
    void testLoginUser_WrongPassword() {
        loginSystem.registerUser("po_g", "Mufha@67", "+27766223171");
        assertFalse(loginSystem.loginUser("jo_n", "mufhaMu!24"), 
            "Login with wrong password should return false");
    }

    @Test
    void testLoginUser_WrongUsername() {
        loginSystem.registerUser("po_g", "Mufha@67", "+27831234567");
        assertFalse(loginSystem.loginUser("ASAQ", "Mufha@67"), 
            "Login with wrong username should return false");
    }

    @Test
    void testLoginUser_BothWrong() {
        loginSystem.registerUser("po_g", "Mufha@67", "+27766223171");
        assertFalse(loginSystem.loginUser("popt", "Asa1265"), 
            "Login with both wrong username and password should return false");
    }

    @Test
    void testLoginUser_WithoutRegistration() {
        // No registration done  storedUsername/Password are null
        assertFalse(loginSystem.loginUser("po_g", "Mufha@67"), 
            "Login without prior registration should return false");
    }

    
    // create a test returnLoginStatus 
    

    @Test
    void testReturnLoginStatus_SuccessfulLogin() {
        String result = loginSystem.returnLoginStatus(true, "popy", "Mod");
        assertEquals("Welcome John, Doe it is great to see you again.", result, 
            "Successful login should return a welcome message with the user's full name");
    }

    @Test
    void testReturnLoginStatus_FailedLogin() {
        String result = loginSystem.returnLoginStatus(false, "Popy", "Mod");
        assertEquals("Username or password incorrect, please try again.", result, 
            "Failed login should return an error message");
    }

    @Test
    void testReturnLoginStatus_FailedLogin_IgnoresName() {
        // Names should be irrelevant when login fails
        String result = loginSystem.returnLoginStatus(false, "Asatu", "Sarah");
        assertEquals("Username or password incorrect, please try again.", result, 
            "Failed login message should not include the user's name");
    }

    
    //create a test Integrationstyle 
    

    @Test
    void testFullFlow_RegisterThenLogin() {
        String regResult = loginSystem.registerUser("x_i", "Zarah@56", "+27766223171");
        assertEquals("User successfully registered.", regResult);

        BooleanSupplier loginStatus = loginSystem.loginUser("x_z", "Zarah@56");
        assertTrue(loginStatus);

        String welcomeMsg = loginSystem.returnLoginStatus(loginStatus, "Phoph", "Asatu");
        assertEquals("Welcome Alice, Brown it is great to see you again.", welcomeMsg);
    }

    @Test
    void testFullFlow_FailedRegistration_PreventLogin() {
        // Bad username  registration should fail
        String regResult = loginSystem.registerUser("Asatu", "Zarah@56", "+27766223171");
        assertFalse(regResult.contains("successfully"), 
            "Registration should fail for invalid username");

        // Login should also fail since nothing was stored
        assertFalse(loginSystem.loginUser("Asatu", "Zarah@56"), 
            "Login should fail if registration did not succeed");
    }
}


    
    

