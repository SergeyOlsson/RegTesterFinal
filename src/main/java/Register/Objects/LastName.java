package Register.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Register.GUI.OutputGUI.output;
/* Code explained in FirstName */
public class LastName {
    public static String lastNameValue;
    public void lastNameToField(WebDriver driver, String lastName) {
        boolean firstMethodSuccessful = false;
        int element = 0;
try {
        List<WebElement> inputFields = driver.findElements(By.tagName("input"));
        for (WebElement inputField : inputFields) {

            String name = inputField.getDomAttribute("name");
            String id = inputField.getDomAttribute("id");
            String keyword = "name";
            if ((name != null && name.toLowerCase().contains(keyword)) ||
                    (id != null && id.toLowerCase().contains(keyword))) {
                //Skip the first name field
                if (inputField.isDisplayed() && inputField.isEnabled()) {
                    element++;
                }
                //Send the last name to the last name field
                if (element == 2) {
                    inputField.clear();
                    inputField.sendKeys(lastName);
                    inputField.click();
                    lastNameValue = inputField.getAttribute("value");
                    assert lastNameValue != null;
                    if (!lastNameValue.isEmpty()) {
                        output.append("Successfully sent " + lastNameValue + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                }
                    firstMethodSuccessful = true;
                    break;
                }
            }
        }
        } catch (Exception e) {
            output.append("Error: Could not find the last name field! Trying second method... \n");
        }
        try {
            if (!firstMethodSuccessful) {
                List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'name')]//following::input"));
                alternativeInput.get(1).clear();
                alternativeInput.get(1).sendKeys(lastName);
                String name = alternativeInput.get(1).getDomAttribute("name");
                String id = alternativeInput.get(1).getDomAttribute("id");
                lastNameValue = alternativeInput.get(1).getAttribute("value");
                assert lastNameValue != null;
                if (!lastNameValue.isEmpty()) {
                output.append("Successfully sent " + lastNameValue + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                }

            }
        } catch (Exception e) {
            output.append("Error: Could not find the last name field! \n");
        }
    }
}