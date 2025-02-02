package Register.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberID {
    public static String memberIDValue;

    public static void memberIDFind(WebDriver driver) {
        //Get the body element
        WebElement element = driver.findElement(By.tagName("body"));
        //Get the text of the element
        String pageText = element.getText();
        //Define the pattern A followed by 6 digits
        String regex = "A\\d{6}";
        //Create a pattern object
        Pattern pattern = Pattern.compile(regex);
        //Create a matcher object
        Matcher matcher = pattern.matcher(pageText);
        //Search for matches
        while(matcher.find()) {
            //Retrieve part of the string, if found
            memberIDValue = matcher.group();
        }
    }
}
