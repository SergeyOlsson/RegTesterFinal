package Register.Inputs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import java.util.List;

import static Register.GUI.OutputGUI.output;
/* Code explained in FirstName */
public class Email {
    public static String emailValue1;
    public void emailToField(WebDriver driver, String email) {
        boolean firstMethodSuccessful = false;
    try {
        List<WebElement> inputFields = driver.findElements(By.tagName("input"));
        for (WebElement inputField : inputFields) {

            String name = inputField.getDomAttribute("name");
            String id = inputField.getDomAttribute("id");
            String keyword = "mail";
            if ((name != null && name.toLowerCase().contains(keyword)) ||
                    (id != null && id.toLowerCase().contains(keyword))) {

                if (inputField.isDisplayed() && inputField.isEnabled()) {
                    inputField.clear();
                    inputField.sendKeys(email);
                    inputField.click();
                    emailValue1 = inputField.getAttribute("value");
                    if (!emailValue1.isEmpty()) {
                        output.append("Successfully sent " + emailValue1 + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                    }
                    firstMethodSuccessful = true;
                    break;
                }
            }
        }
    } catch (Exception e) {
        output.append("Error: Could not find the email field! Trying second method... \n");
    }
    try {
        if (!firstMethodSuccessful) {
            List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'mail')]//following::input"));
            alternativeInput.get(0).clear();
            alternativeInput.get(0).sendKeys(email);
            String name = alternativeInput.get(0).getDomAttribute("name");
            String id = alternativeInput.get(0).getDomAttribute("id");
            emailValue1 = alternativeInput.get(0).getAttribute("value");
            if (!emailValue1.isEmpty()) {
                output.append("Successfully sent " + emailValue1 + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
            }
        }
    } catch (Exception e) {
        output.append("Error: Could not find the email field! \n");
    }
    }
}