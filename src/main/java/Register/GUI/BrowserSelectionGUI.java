package Register.GUI;

import Register.ActionButtons.BrowserManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.swing.*;
import java.awt.*;

import static Register.ActionButtons.ActionValues.sessionInProgress;
import static Register.ActionButtons.ActionValues.unchecked;
import static Register.GUI.OutputGUI.output;

//Extends JPanel is used to create a container for the browser selection buttons
public class BrowserSelectionGUI extends JPanel {
    public static String currentURL;
    private final JToggleButton edgeButton;
    private final JToggleButton chromeButton;
    private final JToggleButton firefoxButton;
    private static JToggleButton[] buttons;
    public BrowserSelectionGUI() {
        setLayout(new FlowLayout());
        //Initialize the buttons
        edgeButton = new JToggleButton("Open Edge Browser");
        chromeButton = new JToggleButton("Open Chrome Browser");
        firefoxButton = new JToggleButton("Open Firefox Browser");
        buttons = new JToggleButton[]{edgeButton, chromeButton, firefoxButton};
        //Add action listeners
    edgeButton.addActionListener(e -> {
        BrowserManager.setActiveDriver(new EdgeDriver());
        resetToggleButtons();
        edgeButton.setEnabled(false);
        edgeButton.setSelected(true);
        sessionInProgress();
        currentBrowser();
    });
    chromeButton.addActionListener(e -> {
        BrowserManager.setActiveDriver(new ChromeDriver());
        resetToggleButtons();
        chromeButton.setEnabled(false);
        chromeButton.setSelected(true);
        sessionInProgress();
        currentBrowser();
    });
    firefoxButton.addActionListener(e -> {
        BrowserManager.setActiveDriver(new FirefoxDriver());
        resetToggleButtons();
        firefoxButton.setEnabled(false);
        firefoxButton.setSelected(true);
        sessionInProgress();
        currentBrowser();
    });
    //Remove focus
    for (JToggleButton button : buttons) {
            button.setFocusable(false);
        }
    //Add buttons to the frame
    add(edgeButton);
    add(chromeButton);
    add(firefoxButton);
}
    private void resetToggleButtons() {
        //Reset all browser buttons to default state
        for (JToggleButton button : buttons) {
            button.setSelected(false);
            button.setEnabled(true);
        }
    }
    private void currentBrowser() {
        unchecked = true;
        String browserName = BrowserManager.getActiveDriver().getClass().getSimpleName();
        currentURL = BrowserManager.getActiveDriver().getCurrentUrl();
        output.append("Current browser: " + browserName + "\n");
        output.append("Current URL: " + currentURL + "\n");
    }
}
