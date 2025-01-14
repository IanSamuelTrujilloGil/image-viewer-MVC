package software.ulpgc.imageviewer.mvp.apps.windows;

import software.ulpgc.imageviewer.mvp.architecture.control.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class MainFrame extends JFrame {
    private final Map<String, Command> commandMap;
    private final SwingImageDisplay imageDisplay;

    public MainFrame() throws HeadlessException {
        this.commandMap = new HashMap<>();
        this.setTitle("Image Viewer App");
        this.setSize(1200, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = createImageDisplay());
        this.add(createActionBar(), BorderLayout.SOUTH);
    }

    private JPanel createStyledPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#ECF0F1"));
        jPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
        return jPanel;
    }

    private Component createActionBar() {
        JPanel jPanel = createStyledPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.add(createButton("previous"));
        jPanel.add(createButton("next"));
        return jPanel;
    }

    private Component createButton(String buttonName) {
        JButton jButton = new JButton(buttonName);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandMap.get(buttonName).execute();
            }
        });
        return jButton;
    }


    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    public MainFrame add(String name, Command command){
        commandMap.put(name, command);
        return this;
    }

    public SwingImageDisplay getImageDisplay() {
        return imageDisplay;
    }


}
