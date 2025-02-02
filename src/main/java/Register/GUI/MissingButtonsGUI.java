package Register.GUI;

import Register.ActionButtons.*;
import Register.ActionButtons.Checkbox;
import Register.ActionButtons.BrowserManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.TimeoutException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static Register.ActionButtons.ActionValues.*;
import static Register.ActionButtons.CheckboxIdentifier.checkboxExists;
import static Register.ActionButtons.CheckboxIdentifier.checkboxValue;
import static Register.ActionButtons.BrowserManager.activeDriver;
import static Register.GUI.BrowserSelectionGUI.currentURL;
import static Register.GUI.OutputGUI.output;

public class MissingButtonsGUI extends JPanel {
    private static JToggleButton firstNameButton, lastNameButton, emailButton, confirmEmailButton,
            passwordButton, confirmPasswordButton, dateButton, checkboxesButton, everythingButton;
    private static JToggleButton[] buttons;

    public MissingButtonsGUI()  {
        setLayout(new FlowLayout());
        // Initialize the buttons and set color to red
        firstNameButton = new JToggleButton("Missing First Name");
        firstNameButton.setForeground(Color.RED);
        lastNameButton = new JToggleButton("Missing Last Name");
        lastNameButton.setForeground(Color.RED);
        emailButton = new JToggleButton("Missing Email");
        emailButton.setForeground(Color.RED);
        confirmEmailButton = new JToggleButton("Missing Confirm Email");
        confirmEmailButton.setForeground(Color.RED);
        passwordButton = new JToggleButton("Missing Password");
        passwordButton.setForeground(Color.RED);
        confirmPasswordButton = new JToggleButton("Missing Confirm Password");
        confirmPasswordButton.setForeground(Color.RED);
        dateButton = new JToggleButton("Missing Date");
        dateButton.setForeground(Color.RED);
        checkboxesButton = new JToggleButton("Missing Checkboxes");
        checkboxesButton.setForeground(Color.RED);
        everythingButton = new JToggleButton("Missing Everything");
        everythingButton.setForeground(Color.RED);
        //Put all buttons in an array to make it easier to access
        buttons = new JToggleButton[]{firstNameButton, lastNameButton, emailButton, confirmEmailButton,
                passwordButton, confirmPasswordButton, dateButton, checkboxesButton, everythingButton};
        //Remove focus
        for (JToggleButton button : buttons) {
            button.setFocusable(false);
        }
        //Add buttons to the frame
        add(firstNameButton);
        add(lastNameButton);
        add(emailButton);
        add(confirmEmailButton);
        add(passwordButton);
        add(confirmPasswordButton);
        add(dateButton);
        add(checkboxesButton);
        add(everythingButton);
    //Add action listeners
firstNameButton.addActionListener(e -> {
    //Check if there is a browser session
    if (activeDriver != null) {
        //This gives some time for the page to load
        if (!isInProgress) {
            try {
                //Set the first name to an empty string
                randomFirstName = "";
                //Generate random values
                randomValues(true);
                //Wait to see if the page will redirect
                webWait();
                //If the page has redirected, when it shouldn't
                if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                    output.append("Action 'Missing First Name' - Warning! \n");
                    currentURL = activeDriver.getCurrentUrl();
                    output.append("Current URL: " + currentURL + "\n");
                    JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            //If the page has not redirected
            } catch (TimeoutException _) {}
            String time = LocalDateTime.now().format(format);
            //This is a way to check if sending to the first name field was successful
            if (Objects.equals(FirstName.firstNameValue, randomFirstName)) {
                output.append("Action 'Missing First Name' - Done! Complete time: " + time + "\n");
                JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            //If sending to the first name field was not successful
            } else {
                output.append("Action 'Missing First Name' - Failed! Complete time: " + time + " \n");
                JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Reset random values back to default
        resetDefaults();
    } else {
        JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
    }
    //Deselect the button
    toggleButtonReset();
    });
lastNameButton.addActionListener(e -> {
    if (activeDriver != null) {
        if (!isInProgress) {
            try {
                randomLastName = "";
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
    emailButton.addActionListener(e -> {
        if (activeDriver != null) {
            if (!isInProgress) {
                try {
                    randomEmail1 = "";
                    //Needed because default value of randomEmail2 is randomEmail1
                    randomEmail2 = RandomStringUtils.randomAlphanumeric(5) + "@example.com";
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
                }
                else {
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
    confirmEmailButton.addActionListener(e -> {
        if (activeDriver != null) {
            if (!isInProgress) {
                try {
                    randomEmail2 = "";
                    randomValues(true);
                    webWait();
                    if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                        output.append("Action 'Missing Confirm Email' - Warning! \n");
                        currentURL = activeDriver.getCurrentUrl();
                        output.append("Current URL: " + currentURL + "\n");
                        JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (TimeoutException _) {}
                String time = LocalDateTime.now().format(format);
                if (Objects.equals(ConfirmEmail.emailValue2, randomEmail2)) {
                    output.append("Action 'Missing Confirm Email' - Done! Complete time: " + time + "\n");
                    JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    output.append("Action 'Missing Confirm Email' - Failed! Complete time: " + time + " \n");
                    JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        resetDefaults();
        } else {
            JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    toggleButtonReset();
    });
    passwordButton.addActionListener(e -> {
        if (activeDriver != null) {
            if (!isInProgress) {
                try {
                    randomPassword1 = "";
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
                }
                else {
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
    confirmPasswordButton.addActionListener(e -> {
        if (activeDriver != null) {
            if (!isInProgress) {
                try {
                    randomPassword2 = "";
                    randomValues(true);
                    webWait();
                    if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                        output.append("Action 'Missing Confirm Password' - Warning! \n");
                        currentURL = activeDriver.getCurrentUrl();
                        output.append("Current URL: " + currentURL + "\n");
                        JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (TimeoutException _) {}
                String time = LocalDateTime.now().format(format);
                if (Objects.equals(ConfirmPassword.passwordValue2, randomPassword2)) {
                    output.append("Action 'Missing Confirm Password' - Done! Complete time: " + time + "\n");
                    JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    output.append("Action 'Missing Confirm Password' - Failed! Complete time: " + time + " \n");
                    JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        resetDefaults();
        } else {
            JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    toggleButtonReset();
    });
dateButton.addActionListener(e -> {
    if (activeDriver != null) {
        if (!isInProgress) {
            try {
                randomDate = "";
                randomValues(true);
                webWait();
                if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                    output.append("Action 'Missing Date' - Warning! \n");
                    currentURL = activeDriver.getCurrentUrl();
                    output.append("Current URL: " + currentURL + "\n");
                    JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (TimeoutException _) {}
            String time = LocalDateTime.now().format(format);
            if (Objects.equals(Date.dateValue, randomDate)) {
                output.append("Action 'Missing Date' - Done! Complete time: " + time + "\n");
                JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                output.append("Action 'Missing Date' - Failed! Complete time: " + time + " \n");
                JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        resetDefaults();
    } else {
        JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
    }
    toggleButtonReset();
    });
    checkboxesButton.addActionListener(e -> {
                if (activeDriver != null) {
                    if (!isInProgress) {
                        try {
                            //Check if checkboxes are present
                            CheckboxIdentifier.checkboxCheck(BrowserManager.getActiveDriver());
                            //If checkboxes are not present
                            if (!checkboxExists) {
                                JOptionPane.showMessageDialog(null, "No checkboxes found!", "Warning", JOptionPane.WARNING_MESSAGE);
                            } else {
                            //Uncheck all checkboxes
                            if (!unchecked) {
                                Checkbox.checkboxClick(BrowserManager.getActiveDriver());
                                unchecked = true;
                            }
                            //Because checkboxes are unchecked, the click checkboxes action will not be performed
                            randomValues(false);
                            webWait();
                            if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                                output.append("Action 'Missing Checkboxes' - Warning! \n");
                                currentURL = activeDriver.getCurrentUrl();
                                output.append("Current URL: " + currentURL + "\n");
                                JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    } catch (TimeoutException _) {}
                    String time = LocalDateTime.now().format(format);
                    if (!checkboxValue.isEmpty()) {
                        output.append("Action 'Missing Checkboxes' - Done! Complete time: " + time + "\n");
                        JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        output.append("Action 'Missing Checkboxes' - Failed! Complete time: " + time + " \n");
                        JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
        resetDefaults();
        } else {
            JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    toggleButtonReset();
    });
everythingButton.addActionListener(e -> {
    if (activeDriver != null) {
        if (!isInProgress) {
            try {
                sessionInProgress();
                randomFirstName = "";
                randomLastName = "";
                randomEmail1 = "";
                randomEmail2 = "";
                randomPassword1 = "";
                randomPassword2 = "";
                randomDate = "";
                if (!unchecked) {
                    Checkbox.checkboxClick(BrowserManager.getActiveDriver());
                    unchecked = true;
                }
                randomValues(false);
                webWait();
                if (!Objects.equals(activeDriver.getCurrentUrl(), currentURL)) {
                    output.append("Action 'Missing Everything' - Warning! \n");
                    currentURL = activeDriver.getCurrentUrl();
                    output.append("Current URL: " + currentURL + "\n");
                    JOptionPane.showMessageDialog(null, "Page redirected!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (TimeoutException _) {}
            String time = LocalDateTime.now().format(format);
            if (FirstName.firstNameValue.isEmpty() && LastName.lastNameValue.isEmpty() && Email.emailValue1.isEmpty() &&
                    ConfirmEmail.emailValue2.isEmpty() && Password.passwordValue1.isEmpty() &&
                    ConfirmPassword.passwordValue2.isEmpty()) {
                output.append("Action 'Missing Everything' - Done! Complete time: " + time + "\n");
                JOptionPane.showMessageDialog(null, "Action successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                output.append("Action 'Missing Everything' - Failed! Complete time: " + time + " \n");
                JOptionPane.showMessageDialog(null, "Action failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        resetDefaults();
    } else {
        JOptionPane.showMessageDialog(null, "No browser session found", "Error", JOptionPane.ERROR_MESSAGE);
    }
    toggleButtonReset();
});

    //Needed to stay on top of the other windows
    JOptionPane.getRootFrame().setAlwaysOnTop(true);
}

    private static void toggleButtonReset() {
            for (JToggleButton button : buttons) {
                button.setSelected(false);
        }
    }
}

