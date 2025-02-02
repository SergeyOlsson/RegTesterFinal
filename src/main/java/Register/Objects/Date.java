package Register.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Register.GUI.OutputGUI.output;
/* Code explained in FirstName */
public class Date {
    public static String dateValue;
    public void dateToField(WebDriver driver, String date) {
        boolean firstMethodSuccessful = false;
    try {
        List<WebElement> inputFields = driver.findElements(By.tagName("input"));
        for (WebElement inputField : inputFields) {

            String name = inputField.getDomAttribute("name");
            String id = inputField.getDomAttribute("id");
            String keyword = "date";
            if ((name != null && name.toLowerCase().contains(keyword)) ||
                    (id != null && id.toLowerCase().contains(keyword))) {

                if (inputField.isDisplayed() && inputField.isEnabled()) {
                    inputField.clear();
                    inputField.sendKeys(date);
                    inputField.click();
                    dateValue = inputField.getAttribute("value");
                    assert dateValue != null;
                    if (!dateValue.isEmpty()) {
                        output.append("Successfully sent " + dateValue + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                    }
                    firstMethodSuccessful = true;
                    break;
                }
            }
        }
    } catch (Exception e) {
        output.append("Error: Could not find the date field! Trying second method... \n");
    }
    try {
        if (!firstMethodSuccessful) {
            List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'date')]//following::input"));
            alternativeInput.get(0).clear();
            alternativeInput.get(0).sendKeys(date);
            String name = alternativeInput.get(0).getDomAttribute("name");
            String id = alternativeInput.get(0).getDomAttribute("id");
            dateValue = alternativeInput.get(0).getAttribute("value");
            assert dateValue != null;
            if (!dateValue.isEmpty()) {
                output.append("Successfully sent " + dateValue + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
            }
        }
    } catch (Exception e) {
        output.append("Error: Could not find the date field! \n");
    }
    }
}

