package Register.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Register.GUI.OutputGUI.output;
import static Register.Objects.CheckboxIdentifier.checkboxExists;

public class Checkbox {
    public static void checkboxClick(WebDriver driver) {
        //If the webpage requires to select a gender
        try {
            List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'gend')]//following::input"));
                    checkboxExists = true;
                    alternativeInput.get(0).click();
                    String name = alternativeInput.get(0).getDomAttribute("name");
                    String id = alternativeInput.get(0).getDomAttribute("id");
                    output.append("Successfully clicked checkbox! " + "(Name: " + name + ") (ID: " + id + ") \n");
        } catch (Exception _) {}

        try {
        List<WebElement> inputFields = driver.findElements(By.cssSelector("label:has(span.box):not(input)"));

        for (WebElement inputField : inputFields) {
            String text = inputField.getText().trim();
            //The important checkboxes often have an asterisk
            if (!text.isEmpty() && text.toLowerCase().contains("*")) {
                checkboxExists = true;
                inputField.click();
                inputField.getText();
                String checkboxText = inputField.getText();
                output.append("Successfully clicked checkbox! " + checkboxText + "\n");
            }
        }
    } catch (Exception _) {}
    }
}
