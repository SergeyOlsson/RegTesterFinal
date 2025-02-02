package Register.ActionButtons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Register.GUI.OutputGUI.output;

public class FirstName {
    public static String firstNameValue;
    public void firstNameToField(WebDriver driver, String firstName) {
    boolean firstMethodSuccessful = false;
try {
    //Looks for the input fields
    List<WebElement> inputFields = driver.findElements(By.tagName("input"));
    for (WebElement inputField : inputFields) {
        //Narrow it down to the name and id
        String name = inputField.getDomAttribute("name");
        String id = inputField.getDomAttribute("id");
        String keyword = "name";
        //If the name or id contains the keyword
        if ((name != null && name.toLowerCase().contains(keyword)) ||
                (id != null && id.toLowerCase().contains(keyword))) {
            //If the input field is displayed
            if (inputField.isDisplayed() && inputField.isEnabled()) {
                inputField.clear(); //Clear previous value
                inputField.sendKeys(firstName);//Send new value
                inputField.click(); //Click for focus
                firstNameValue = inputField.getAttribute("value"); //Get the input value
                //if the input is not Empty
                if (!firstNameValue.isEmpty()) {
                output.append("Successfully sent " + firstNameValue + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
                }
                //Second method is not needed
                firstMethodSuccessful = true;
                //Only perform the action once
                break;
            }
        }
    }
} catch (Exception _) {
    output.append("Error: Could not find the first name field! Trying second method... \n");
}
try {
    //If the first method was not successful
    if (!firstMethodSuccessful) {
        //Look for a label that contains "name" and send the first name to the following input
        List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'name')]//following::input"));
        alternativeInput.get(0).clear();
        alternativeInput.get(0).sendKeys(firstName);
        String name = alternativeInput.get(0).getDomAttribute("name");
        String id = alternativeInput.get(0).getDomAttribute("id");
        firstNameValue = alternativeInput.get(0).getAttribute("value");
        assert firstNameValue != null;
        if (!firstNameValue.isEmpty()) {
        output.append("Successfully sent " + firstNameValue + " to field! " + "(Name: " + name + ") (ID: " + id + ") \n");
        }
    }
} catch (Exception _) {
    output.append("Error: Could not find the first name field! \n");
}
    }
}