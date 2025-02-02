package Register.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static Register.ActionButtons.BrowserManager.activeDriver;

public class OutputGUI extends JPanel {
    public static JTextArea output;
    private JFrame outputFrame;

    public OutputGUI() {
        setLayout(new BorderLayout());
        //Initialize the JTextArea
        output = new JTextArea();
        output.setEditable(false);

        //Initialize the buttons
        JButton openLogsButton = new JButton("Open Logs");
        JButton resetLogsButton = new JButton("Reset Logs");
        JButton exitButton = new JButton("Exit");
        //Align the buttons openLogsButton and resetLogsButton to the left and exitButton to the right
        JPanel buttonContainer = new JPanel(new BorderLayout());
        JPanel buttonLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonContainer.add(buttonLeftPanel, BorderLayout.WEST);
        buttonContainer.add(buttonRightPanel, BorderLayout.EAST);
        buttonLeftPanel.add(openLogsButton);
        buttonLeftPanel.add(resetLogsButton);
        buttonRightPanel.add(exitButton);
        //Add the buttons to the SOUTH section
        add(buttonContainer, BorderLayout.SOUTH);
        //Remove focus
        exitButton.setFocusable(false);
        resetLogsButton.setFocusable(false);
        openLogsButton.setFocusable(false);
    //Add action listeners
    openLogsButton.addActionListener(e -> {
        //Open outputFrame
        if (outputFrame == null || !outputFrame.isVisible()) {
            outputFrame = new JFrame("Logs");
            outputFrame.setSize(700, 400);
            outputFrame.setLayout(new BorderLayout());
            outputFrame.setVisible(true);
            outputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            //Close outputFrame
            outputFrame.dispatchEvent(new WindowEvent(outputFrame, WindowEvent.WINDOW_CLOSING));

        }
            //Add scroll pane
            JScrollPane scrollPane = new JScrollPane(output);
            outputFrame.add(scrollPane, BorderLayout.CENTER);

            outputFrame.setAlwaysOnTop(true);
    });
    //Add action listeners
    resetLogsButton.addActionListener(e ->
            output.setText(""));

    exitButton.addActionListener(e -> {
        //null is needed to prevent a NullPointerException
        if (activeDriver != null) {
            activeDriver.quit();
        }
        System.exit(0);
    });
    }
}
