package Register.Inputs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Register.GUI.OutputGUI.output;
/* Code explained in FirstName */
public class ConfirmPassword {
public static String passwordValue2;
public void confirmPasswordToField(WebDriver driver, String confirmPassword) {
    boolean firstMethodSuccessful = false;
try {
    List<WebElement> inputFields = driver.findElements(By.tagName("input"));
    int element = 0;
    for (WebElement inputField : inputFields) {
        String name = inputField.getDomAttribute("name");
        String id = inputField.getDomAttribute("id");
        String keyword = "password";
        if ((name != null && name.toLowerCase().contains(keyword)) ||
                (id != null && id.toLowerCase().contains(keyword))) {

            if (inputField.isDisplayed() && inputField.isEnabled()) {
                element++;
            }
            if (element == 2) {
                inputField.clear();
                inputField.sendKeys(confirmPassword);
                passwordValue2 = inputField.getAttribute("value");
                assert passwordValue2 != null;
                if (!passwordValue2.isEmpty()) {
                    output.append("Successfully sent " + passwordValue2 + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                }
                firstMethodSuccessful = true;
                break;
            }
        }
    }
} catch (Exception e) {
    output.append("Error: Could not find the confirm password field! Trying second method... \n");
}
try {
    if (!firstMethodSuccessful) {
        List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'password')]//following::input"));
        alternativeInput.get(1).clear();
        alternativeInput.get(1).sendKeys(confirmPassword);
        String name = alternativeInput.get(1).getDomAttribute("name");
        String id = alternativeInput.get(1).getDomAttribute("id");
        passwordValue2 = alternativeInput.get(1).getAttribute("value");
        assert passwordValue2 != null;
        if (!passwordValue2.isEmpty()) {
            output.append("Successfully sent " + passwordValue2 + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
        }
    }
} catch (Exception e) {
    output.append("Error: Could not find the confirm password field! \n");
}
}
}