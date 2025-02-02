import static org.junit.Assert.*;

import Register.ActionButtons.BrowserManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserManagerTest {

    private WebDriver chromeDriver;
    private WebDriver firefoxDriver;
    private WebDriver edgeDriver;

    @Before
    public void setUp() {
        //Initialize drivers
        chromeDriver = new ChromeDriver();
        firefoxDriver = new FirefoxDriver();
        edgeDriver = new EdgeDriver();
    }

    @After
    public void tearDown() {
        //Quit drivers
        if (chromeDriver != null) {
            chromeDriver.quit();
        }
        if (firefoxDriver != null) {
            firefoxDriver.quit();
        }
        if (edgeDriver != null) {
            edgeDriver.quit();
        }
    }

    @Test
    public void testSetActiveDriver() {
        //Test the browser selector
        BrowserManager.setActiveDriver(chromeDriver);
        assertEquals(chromeDriver, BrowserManager.getActiveDriver());

        BrowserManager.setActiveDriver(firefoxDriver);
        assertEquals(firefoxDriver, BrowserManager.getActiveDriver());

        BrowserManager.setActiveDriver(edgeDriver);
        assertEquals(edgeDriver, BrowserManager.getActiveDriver());
    }
}