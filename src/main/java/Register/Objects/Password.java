package Register.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import java.util.List;

import static Register.GUI.OutputGUI.output;
/* Code explained in FirstName */
public class Password {
    public static String passwordValue1;
    public void passwordToField(WebDriver driver, String password) {
        boolean firstMethodSuccessful = false;
        try {
            List<WebElement> inputFields = driver.findElements(By.tagName("input"));
            for (WebElement inputField : inputFields) {
                String name = inputField.getDomAttribute("name");
                String id = inputField.getDomAttribute("id");
                String keyword = "pass";
                if ((name != null && name.toLowerCase().contains(keyword)) ||
                        (id != null && id.toLowerCase().contains(keyword))) {

                    if (inputField.isDisplayed() && inputField.isEnabled()) {
                        inputField.clear();
                        inputField.sendKeys(password);
                        inputField.click();
                        passwordValue1 = inputField.getAttribute("value");
                        assert passwordValue1 != null;
                        if (!passwordValue1.isEmpty()) {
                            output.append("Successfully sent " + passwordValue1 + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                        }
                        firstMethodSuccessful = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            output.append("Error: Could not find the password field! Trying second method... \n");
        }
        try {
            if (!firstMethodSuccessful) {
                List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'pass')]//following::input"));
                alternativeInput.get(0).clear();
                alternativeInput.get(0).sendKeys(password);
                String name = alternativeInput.get(0).getDomAttribute("name");
                String id = alternativeInput.get(0).getDomAttribute("id");
                passwordValue1 = alternativeInput.get(0).getAttribute("value");
                assert passwordValue1 != null;
                if (!passwordValue1.isEmpty()) {
                    output.append("Successfully sent " + passwordValue1 + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                }
            }
        } catch (Exception e) {
            output.append("Error: Could not find the password field! \n");
        }
    }
}