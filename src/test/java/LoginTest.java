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
public class LoginTest {

    Login login = new Login();

    @Test
    public void testUsernameCorrect() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrect() {
        assertFalse(login.checkUserName("kyle!!!!"));
    }

    @Test
    public void testPasswordCorrect() {
        assertTrue(login.checkPasswordComplexity("Password1!"));
    }

    @Test
    public void testPhoneCorrect() {
        assertTrue(login.checkCellphoneNumber("+27831234567"));
    }

    @Test
    public void testLoginSuccess() {
        login.registerUser("kyl_1", "Password1!", "+27831234567");
        assertTrue(login.loginUser("kyl_1", "Password1!"));
    }
}
