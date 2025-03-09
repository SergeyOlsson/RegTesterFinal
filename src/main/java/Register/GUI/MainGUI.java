package Register.GUI;

import javax.swing.*;
import java.awt.*;


public class MainGUI {

    public static void main(String[] args) {
        //Create frame
        JFrame frame = new JFrame("Reg_Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 300);
        frame.setLayout(new BorderLayout());

        //Create BrowserSelectionGUI instance and add it to the NORTH section of the frame
        BrowserSelectionGUI browserSelectionGUI = new BrowserSelectionGUI();
        JPanel northContainer = new JPanel(new BorderLayout());
        northContainer.add(browserSelectionGUI, BorderLayout.CENTER);
        northContainer.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
        northContainer.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH);
        frame.add(northContainer, BorderLayout.NORTH);

        //Create MissingButtonsGUI instance and add it to the CENTER section of the frame
        MissingButtonsGUI missingButtonsGUI = new MissingButtonsGUI();
        ActionButtonsGUI actionButtonsGUI = new ActionButtonsGUI();
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.add(missingButtonsGUI);
        centerContainer.add(actionButtonsGUI);
        frame.add(centerContainer, BorderLayout.CENTER);
        //Create ActionButtonsGUI instance and add it to the CENTER section of the frame, inside the centerContainer

        //Create OutputGUI and add it to the SOUTH section of the frame
        OutputGUI outputGUI = new OutputGUI();
        JPanel southContainer = new JPanel(new BorderLayout());
        southContainer.add(outputGUI);
        southContainer.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
        frame.add(southContainer, BorderLayout.SOUTH);
        //Make the frame visible
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }
}
