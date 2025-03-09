package Register.Inputs;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

import static Register.Inputs.BrowserManager.activeDriver;
import static Register.GUI.BrowserSelectionGUI.currentURL;
import static Register.GUI.OutputGUI.output;

public class InputValues {
    private static final Random random = new Random();
    private static final Date date = new Date();
    private static final FirstName firstName = new FirstName();
    private static final LastName lastName = new LastName();
    private static final Email email = new Email();
    private static final ConfirmEmail confirmEmail = new ConfirmEmail();
    private static final Password password = new Password();
    private static final ConfirmPassword confirmPassword = new ConfirmPassword();
    private static final Submit submit = new Submit();

    public static final String[] arrayFirstName = {"John", "Jane", "Alice", "Steve", "Emily", "Paul", "Carol", "Maria"};
    public static final String[] arrayLastName = {"Doe", "Smith", "Johnson", "Williams", "Carter", "Brown", "White", "Green"};
    public static String randomEmail1, randomEmail2, randomFirstName, randomLastName;
    public static String randomPassword1, randomPassword2, randomDate;
    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("hh.mm.ss");
    public static boolean isInProgress = false;
    public static boolean unchecked = true;

    //Define random values if not already defined
    public static void randomValues(boolean checked) {
        if (randomPassword1 == null) randomPassword1 = "Pass1234";
        if (randomPassword2 == null) randomPassword2 = "Pass1234";
        if (randomDate == null) randomDate = "01/01/2000";
        if (randomFirstName == null) randomFirstName = arrayFirstName[random.nextInt(arrayFirstName.length)];//Select a random name from the array and convert it to string
        if (randomLastName == null) randomLastName = arrayLastName[random.nextInt(arrayLastName.length)];
        if (randomEmail1 == null) randomEmail1 = RandomStringUtils.randomAlphanumeric(5) + "@example.com"; //Generate random email
        if (randomEmail2 == null) randomEmail2 = randomEmail1;
        randomEmail1 = randomEmail1.toLowerCase();
        randomEmail2 = randomEmail2.toLowerCase();

        //This is used to keep track of the state of the checkboxes
        if (checked && unchecked) {
            Checkbox.checkboxClick(BrowserManager.getActiveDriver());
            unchecked = false;
        }
        //Send random values
        date.dateToField(BrowserManager.getActiveDriver(), randomDate);
        lastName.lastNameToField(BrowserManager.getActiveDriver(), randomLastName);
        firstName.firstNameToField(BrowserManager.getActiveDriver(), randomFirstName);
        email.emailToField(BrowserManager.getActiveDriver(), randomEmail1);
        confirmEmail.confirmEmailToField(BrowserManager.getActiveDriver(), randomEmail2);
        password.passwordToField(BrowserManager.getActiveDriver(), randomPassword1);
        confirmPassword.confirmPasswordToField(BrowserManager.getActiveDriver(), randomPassword2);
        CheckboxIdentifier.checkboxCheck(BrowserManager.getActiveDriver());
        submit.submitClick(BrowserManager.getActiveDriver());
    }

    public static void resetDefaults() {
        //Reset all variables back to null
        randomFirstName = null;
        randomLastName = null;
        randomEmail1 = null;
        randomEmail2 = null;
        randomPassword1 = null;
        randomPassword2 = null;
        randomDate = null;

        Password.passwordValue1 = null;
        ConfirmPassword.passwordValue2 = null;
        Date.dateValue = null;
        FirstName.firstNameValue = null;
        LastName.lastNameValue = null;
        Email.emailValue1 = null;
        ConfirmEmail.emailValue2 = null;
        CheckboxIdentifier.checkboxValue = "";
        CheckboxIdentifier.checkboxExists = false;
    }

    public static void sessionInProgress() {
        //Mark the session as in progress
        isInProgress = true;
        //Create a new thread for the action
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, "Thread interrupted!", "Error", JOptionPane.ERROR_MESSAGE);
                output.append("Thread interrupted! \n");
                return;
            }
            //Mark the session as not in progress
            isInProgress = false;
            //Start the actions in the thread
        }).start();
    }
    public static void webWait() {
        WebDriverWait wait = new WebDriverWait(activeDriver, Duration.ofSeconds(2));
        wait.until(driverInstance -> !Objects.equals(driverInstance.getCurrentUrl(), currentURL));
    }
}



