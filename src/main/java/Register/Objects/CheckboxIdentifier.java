package Register.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Register.GUI.OutputGUI.output;

/* Same as checkboxClick but without the click */
public class CheckboxIdentifier {
    public static boolean checkboxExists = false;
    public static String checkboxValue = "";
    public static void checkboxCheck(WebDriver driver) {
        try {
            List<WebElement> alternativeInput = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'gend')]//following::input"));
            if (alternativeInput.get(0).isDisplayed() && alternativeInput.get(0).isEnabled()) {
                checkboxExists = true;
                checkboxValue = alternativeInput.get(0).getDomAttribute("name");
            }
        } catch (Exception _) {
        }

        try {
            List<WebElement> inputFields = driver.findElements(By.cssSelector("label:has(span.box):not(input)"));

            for (WebElement inputField : inputFields) {
                String text = inputField.getText().trim();
                if (!text.isEmpty() && text.toLowerCase().contains("*")) {
                    checkboxExists = true;
                    checkboxValue = inputField.getText().trim();
                }
            }
        } catch (Exception _) {
            output.append("Error: Could not find the checkboxes! ");
        }
    }
}