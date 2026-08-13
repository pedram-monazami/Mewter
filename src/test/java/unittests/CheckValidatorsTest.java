package unittests;

import ir.ac.kntu.user.service.UserServiceImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckValidatorsTest {

    @Test
    public void EmailValidationTest() throws Exception {
        UserServiceImplementation userService = new UserServiceImplementation();
        String email1 = "abc";
        String email2 = "abc@gmail.com";
        String email3 = "abc@gmail.com@";
        String email5 = "abc#gmail.com";
        String email6 = "abcgmail.com";

        // tests that fail
        assertThrows(Exception.class, () -> userService.isEmailValid(email1));
        assertThrows(Exception.class, () -> userService.isEmailValid(email3));
        assertThrows(Exception.class, () -> userService.isEmailValid(email5));
        assertThrows(Exception.class, () -> userService.isEmailValid(email6));

        // acceptable test
        assertTrue(userService.isEmailValid(email2));
    }

    @Test
    public void PasswordValidationTest() throws Exception {
        UserServiceImplementation userService = new UserServiceImplementation();
        String password1 = "abc";
        String password2 = "abc@gmail.com";
        String password3 = "1231231232";
        String password4 = "abc@gmail.com###aaaaa";
        String password5 = "aB@4";
        String password6 = "thisISAstrongP@ssword3!!!";

        // tests that fail
        assertThrows(Exception.class, () -> userService.isPasswordValid(password1));
        assertThrows(Exception.class, () -> userService.isPasswordValid(password2));
        assertThrows(Exception.class, () -> userService.isPasswordValid(password3));
        assertThrows(Exception.class, () -> userService.isPasswordValid(password4));
        assertThrows(Exception.class, () -> userService.isPasswordValid(password5));

        // acceptable test
        assertTrue(userService.isPasswordValid(password6));
    }

}
