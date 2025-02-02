package Register.ActionButtons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

import static Register.GUI.OutputGUI.output;
/* Code explained in FirstName */
public class ConfirmEmail {
    public static String emailValue2;
    public void confirmEmailToField(WebDriver driver, String email) {
        boolean firstMethodSuccessful = false;
try {
    List<WebElement> inputFields = driver.findElements(By.tagName("input"));
    int element = 0;
    for (WebElement inputField : inputFields) {
        String name = inputField.getDomAttribute("name");
        String id = inputField.getDomAttribute("id");
        String keyword = "mail";
        if ((name != null && name.toLowerCase().contains(keyword)) ||
                (id != null && id.toLowerCase().contains(keyword))) {

            if (inputField.isDisplayed() && inputField.isEnabled()) {
                element++;
            }
            if (element == 2) {
                inputField.clear();
                inputField.sendKeys(email);
                inputField.click();
                emailValue2 = inputField.getAttribute("value");
                assert emailValue2 != null;
                if (!emailValue2.isEmpty()) {
                    output.append("Successfully sent " + emailValue2 + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                }
                firstMethodSuccessful = true;
                break;
            }
        }
    }
} catch (Exception e) {
    output.append("Error: Could not find the confirm email field! Trying second method... \n");
}
try {
    if (!firstMethodSuccessful) {
        List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'mail')]//following::input"));
        alternativeInput.get(1).clear();
        alternativeInput.get(1).sendKeys(email);
        String name = alternativeInput.get(1).getDomAttribute("name");
        String id = alternativeInput.get(1).getDomAttribute("id");
        emailValue2 = alternativeInput.get(1).getAttribute("value");
        assert emailValue2 != null;
        if (!emailValue2.isEmpty()) {
            output.append("Successfully sent " + emailValue2 + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
        }
    }
} catch (Exception e) {
    output.append("Error: Could not find the confirm email field! \n");
}
    }
}