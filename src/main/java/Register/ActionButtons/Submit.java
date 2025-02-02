package Register.ActionButtons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
/* Code explained in FirstName */
public class Submit {
    public void submitClick(WebDriver driver) {
        boolean firstMethodSuccessful = false;
        try {
            List<WebElement> inputFields = driver.findElements(By.tagName("input"));
            for (WebElement inputField : inputFields) {
                String name = inputField.getDomAttribute("name");
                String id = inputField.getDomAttribute("id");
                String keyword = "name";
                if ((name != null && name.toLowerCase().contains(keyword)) ||
                        (id != null && id.toLowerCase().contains(keyword))) {
                        inputField.submit();
                        firstMethodSuccessful = true;
                        break;
                }
            }
        } catch (Exception _) {}

        try {
            if (!firstMethodSuccessful) {
                List<WebElement> alternativeButton = driver.findElements(By.xpath("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'name')]//following::input"));
                alternativeButton.get(0).submit();
            }

        } catch (Exception _) {}
    }
}
