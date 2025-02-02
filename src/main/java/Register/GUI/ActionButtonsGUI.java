package Register.GUI;
import Register.ActionButtons.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import static Register.ActionButtons.ActionValues.*;
import static Register.ActionButtons.BrowserManager.activeDriver;
import static Register.GUI.BrowserSelectionGUI.currentURL;
import static Register.GUI.OutputGUI.output;

public class ActionButtonsGUI extends JPanel {
    private static final MemberID memberID = new MemberID();
    private static JToggleButton successfulButton, badFirstNameButton, badLastNameButton, badEmailButton, badPasswordButton;
    private static final String sessionTime = LocalDateTime.now().format(format);

    public ActionButtonsGUI() {
        setLayout(new FlowLayout());
        badFirstNameButton = new JToggleButton("Bad First Name");
        badFirstNameButton.setForeground(Color.RED);
        badLastNameButton = new JToggleButton("Bad Last Name");
        badLastNameButton.setForeground(Color.RED);
        badEmailButton = new JToggleButton("Bad Email");
        badEmailButton.setForeground(Color.RED);
        badPasswordButton = new JToggleButton("Bad Password");
        badPasswordButton.setForeground(Color.RED);
        successfulButton = new JToggleButton("Everything in order");
        successfulButton.setForeground(new Color(0, 128, 0)); //Set colour green
        badFirstNameButton.setFocusable(false);
        badLastNameButton.setFocusable(false);
        badEmailButton.setFocusable(false);
        badPasswordButton.setFocusable(false);
        successfulButton.setFocusable(false);
        add(badFirstNameButton);
        add(badLastNameButton);
        add(badEmailButton);
        add(badPasswordButton);
        add(successfulButton);

    successfulButton.addActionListener(e -> {
            if (activeDriver != null) {
                if (!isInProgress) {
                    sessionInProgress();
                    randomValues(true);
                    //Get the current time when the action is finished
                    String time = LocalDateTime.now().format(format);
                    try {
                        //Wait for the page to redirect to the next page
                         webWait();
                        //If the page redirection was successful
                        if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                            currentURL = activeDriver.getCurrentUrl();
                            output.append("Current URL: " + currentURL + "\n");
                            //Find the member ID
                            memberID.memberIDFind(BrowserManager.getActiveDriver());
                            JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            //Save the values from the current session to file in C:\Eclipse
                            try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Eclipse\\saved values " + sessionTime + ".txt", true))) {
                                writer.println("First name: " + FirstName.firstNameValue);
                                writer.println("Last name: " + LastName.lastNameValue);
                                writer.println("Email: " + Email.emailValue1);
                                writer.println("Password: " + Password.passwordValue1);
                                writer.println("Member ID: " + MemberID.memberIDValue + "\n");
                                output.append("Action 'Everything in order' - Done! Complete time: " + time + "\n");
                                output.append("Values saved in: " + "C:\\Eclipse\\" + "\n");
                            } catch (IOException ex) {
                                output.append("Failed to save values to: " + "C:\\Eclipse\\" + "\n" + ex.getMessage() + "\n");
                            }
                        }
                    } catch (TimeoutException _) {
                        JOptionPane.showMessageDialog(null, "Action Failed!", "Error", JOptionPane.ERROR_MESSAGE);
                        output.append("Action 'Everything in order' - Failed! Complete time: " + time + " \n");
                    }
                }
            resetDefaults();
            } else {
                JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        toggleButtonReset();
        });
    badFirstNameButton.addActionListener(e -> {
            if (activeDriver != null) {
                if (!isInProgress) {
                    try {
                        randomFirstName = "1";
                        randomValues(true);
                        webWait();
                        if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                            output.append("Action 'Missing First Name' - Warning! \n");
                            currentURL = activeDriver.getCurrentUrl();
                            output.append("Current URL: " + currentURL + "\n");
                            JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (TimeoutException _) {}
                    String time = LocalDateTime.now().format(format);
                    if (Objects.equals(FirstName.firstNameValue, randomFirstName)) {
                        output.append("Action 'Missing First Name' - Done! Complete time: " + time + "\n");
                        JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        output.append("Action 'Missing First Name' - Failed! Complete time: " + time + " \n");
                        JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                resetDefaults();
            } else {
                JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            toggleButtonReset();
        });
    badLastNameButton.addActionListener(e -> {
            if (activeDriver != null) {
                if (!isInProgress) {
                    try {
                        randomLastName = "1";
                        randomValues(true);
                        webWait();
                        if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                            output.append("Action 'Missing Last Name' - Warning! \n");
                            currentURL = activeDriver.getCurrentUrl();
                            output.append("Current URL: " + currentURL + "\n");
                            JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (TimeoutException _) {}
                    String time = LocalDateTime.now().format(format);
                    if (Objects.equals(LastName.lastNameValue, randomLastName)) {
                        output.append("Action 'Missing Last Name' - Done! Complete time: " + time + "\n");
                        JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        output.append("Action 'Missing Last Name' - Failed! Complete time: " + time + " \n");
                        JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                resetDefaults();
            } else {
                JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            toggleButtonReset();
        });
    badEmailButton.addActionListener(e -> {
            if (activeDriver != null) {
                if (!isInProgress) {
                    try {
                        randomEmail1 = "bad.emailexample.com";
                        randomEmail2 = randomEmail1;
                        randomValues(true);
                        webWait();
                        if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                            output.append("Action 'Missing Email' - Warning! \n");
                            currentURL = activeDriver.getCurrentUrl();
                            output.append("Current URL: " + currentURL + "\n");
                            JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (TimeoutException _) {}
                    String time = LocalDateTime.now().format(format);
                    if (Objects.equals(Email.emailValue1, randomEmail1)) {
                        output.append("Action 'Missing Email' - Done! Complete time: " + time + "\n");
                        JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        output.append("Action 'Missing Email' - Failed! Complete time: " + time + " \n");
                        JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                resetDefaults();
            } else {
                JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            toggleButtonReset();
        });
        badPasswordButton.addActionListener(e -> {
            if (activeDriver != null) {
                if (!isInProgress) {
                    try {
                        randomPassword1 = "1";
                        randomPassword2 = randomPassword1;
                        randomValues(true);
                        webWait();
                        if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                            output.append("Action 'Missing Password' - Warning! \n");
                            currentURL = activeDriver.getCurrentUrl();
                            output.append("Current URL: " + currentURL + "\n");
                            JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (TimeoutException _) {}
                    String time = LocalDateTime.now().format(format);
                    if (Objects.equals(Password.passwordValue1, randomPassword1)) {
                        output.append("Action 'Missing Password' - Done! Complete time: " + time + "\n");
                        JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        output.append("Action 'Missing Password' - Failed! Complete time: " + time + " \n");
                        JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                resetDefaults();
            } else {
                JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            toggleButtonReset();
        });
    }

    private static void toggleButtonReset() {
        successfulButton.setSelected(false);
        badFirstNameButton.setSelected(false);
        badLastNameButton.setSelected(false);
        badEmailButton.setSelected(false);
        badPasswordButton.setSelected(false);
    }
}
