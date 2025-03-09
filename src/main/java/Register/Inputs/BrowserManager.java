package Register.Inputs;

import org.openqa.selenium.WebDriver;

import javax.swing.*;

import static Register.GUI.OutputGUI.output;
import static Register.GUI.BrowserSelectionGUI.currentURL;

public class BrowserManager {
    public static WebDriver activeDriver;

    public static void setActiveDriver(WebDriver driver) {
        //Exit the current driver if it is active
        if (activeDriver != null) {
            try {
                activeDriver.quit();
            } catch (Exception e) {
                output.append("Error: " + e.getMessage() + "\n");
                JOptionPane.showMessageDialog(null, "Unable to close the current driver!" , "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
            //Assign the new driver and configure it
            activeDriver = driver;
            activeDriver.manage().window().maximize();
            activeDriver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
            //https://membership.basketballengland.co.uk/NewSupporterAccount
            //https://demowebshop.tricentis.com/register
        }

    //Getter method to retrieve the active driver
    public static WebDriver getActiveDriver() {
        return activeDriver;
    }
}