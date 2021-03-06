package zula.gui;

import zula.client.ConnectionManager;
import zula.common.commands.Add;
import zula.common.data.*;
import zula.common.exceptions.GetServerMessageException;
import zula.common.exceptions.SendException;
import zula.common.exceptions.WrongArgumentException;
import zula.common.util.ArgumentParser;
import zula.util.Constants;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPanel {
    private JFrame mainFrame;
    private ConnectionManager connectionManager;
    private final JPanel northPanel = new JPanel();
    private final JPanel centralPanel = new JPanel();
    private final JPanel southPanel = new JPanel();
    private final JPanel errorPanel = new JPanel();
    private final JButton submitButton = new JButton("SUBMIT");
    private final ArrayList<JComponent> elementsList = new ArrayList<>();


    ArgumentParser argumentParser = new ArgumentParser();


    private final JLabel fieldText =BasicGUIElementsFabric.createBasicLabel("FIELD");
    private final JLabel nameText = BasicGUIElementsFabric.createBasicLabel("name");
    private final JLabel xText  = BasicGUIElementsFabric.createBasicLabel("x");
    private final JLabel yText = BasicGUIElementsFabric.createBasicLabel("y");
    private final JLabel ageText = BasicGUIElementsFabric.createBasicLabel("age");
    private final JLabel wingspanText  = BasicGUIElementsFabric.createBasicLabel("wingspan");
    private final JLabel colorText = BasicGUIElementsFabric.createBasicLabel("color");
    private final JLabel typeText = BasicGUIElementsFabric.createBasicLabel("type");
    private final JLabel depthText = BasicGUIElementsFabric.createBasicLabel("depth");
    private final JLabel numberOfTreasuresText = BasicGUIElementsFabric.createBasicLabel("num. of tres.");
    private final JLabel valueText = BasicGUIElementsFabric.createBasicLabel("value");
    private final JLabel requirementText = BasicGUIElementsFabric.createBasicLabel("REQUIREMENT");
    private final JLabel nameReq = BasicGUIElementsFabric.createBasicLabel("NOT NULL");
    private final JLabel xReq = BasicGUIElementsFabric.createBasicLabel(">=-23, double");
    private final JLabel yReq = BasicGUIElementsFabric.createBasicLabel("<=160, integer");
    private final JLabel ageReq =BasicGUIElementsFabric.createBasicLabel("long, >=0");
    private final JLabel wingspanReq = BasicGUIElementsFabric.createBasicLabel("float, >=0");
    private final JLabel colorReq = BasicGUIElementsFabric.createBasicLabel("may be null");
    private final JLabel typeReq = BasicGUIElementsFabric.createBasicLabel("NOT NULL");
    private final JLabel depthReq = BasicGUIElementsFabric.createBasicLabel("Float, may be null");
    private final JLabel numberOfTreasuresReq = BasicGUIElementsFabric.createBasicLabel("Double, may be null");
    private final String[] colors = new String[]{"BLACK", "ABOBA"};
    private final String[] types = new String[]{"AIR", "ABOBA"};
    private final JTextField nameField = BasicGUIElementsFabric.createBasicJTextField();
    private final JTextField xField = BasicGUIElementsFabric.createBasicJTextField();
    private final JTextField yField = BasicGUIElementsFabric.createBasicJTextField();
    private final JTextField ageField = BasicGUIElementsFabric.createBasicJTextField();
    private final JTextField wingspanField = BasicGUIElementsFabric.createBasicJTextField();
    private final JComboBox<String> colorField = BasicGUIElementsFabric.createBasicComboBox(colors);
    private final JComboBox<String> typeField = BasicGUIElementsFabric.createBasicComboBox(types);
    private final JTextField depthField = BasicGUIElementsFabric.createBasicJTextField();
    private final JTextField numberOfTreasuresField = BasicGUIElementsFabric.createBasicJTextField();
    private ResourceBundle currentBundle;


    public AddPanel(JFrame mainFrame, ConnectionManager connectionManager, ResourceBundle resourceBundle) {
        this.currentBundle = resourceBundle;
        this.connectionManager = connectionManager;
        mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        mainFrame.setVisible(false);
        mainFrame.setTitle("Add window");
        this.mainFrame = mainFrame;
        northPanel.setPreferredSize(new Dimension(Constants.screenWidth, Constants.screenHeight/10));
        centralPanel.setPreferredSize(new Dimension(Constants.screenWidth, Constants.screenHeight*6/10));
        southPanel.setPreferredSize(new Dimension(Constants.screenWidth, Constants.screenHeight*3/20));
        errorPanel.setPreferredSize(new Dimension(Constants.screenWidth, Constants.screenHeight*3/20));
        errorPanel.setBackground(Color.RED);
        /*ridBagLayout gridBagLayout = new GridBagLayout();
        int ints[] = new int[]{Constants.screenWidth/5, Constants.screenWidth*3/5, Constants.screenWidth/5};
        gridBagLayout.columnWidths(ints)*/
        northPanel.setLayout(new FlowLayout());
        centralPanel.setLayout(new GridLayout(10, 3));
        southPanel.setLayout(new GridBagLayout());
        errorPanel.setLayout(new GridBagLayout());

/*
        leftOfCentralPanel.setLayout(new BoxLayout(leftOfCentralPanel, BoxLayout.Y_AXIS));
        centreOfCentralPanel.setLayout(new BoxLayout(centreOfCentralPanel, BoxLayout.Y_AXIS));
        rightOfCentralPanel.setLayout(new BoxLayout(rightOfCentralPanel, BoxLayout.Y_AXIS));

        leftOfCentralPanel.setPreferredSize(new Dimension(Constants.screenWidth/5, Constants.screenHeight));
        centreOfCentralPanel.setPreferredSize(new Dimension(Constants.screenWidth*3/5, Constants.screenHeight));
        rightOfCentralPanel.setPreferredSize(new Dimension(Constants.screenWidth/5, Constants.screenHeight));*/

        fieldText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        valueText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        requirementText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        nameText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        nameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        nameReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        xText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        xField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        xReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        yText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        yField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        yReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        ageText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        ageField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        ageReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        wingspanText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        wingspanField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        wingspanReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        colorText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        colorField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        colorReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        typeText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        typeField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        typeReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        depthText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        depthField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        depthReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        numberOfTreasuresText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        numberOfTreasuresField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        numberOfTreasuresReq.setBorder(BorderFactory.createLineBorder(Color.black, 1));



        submitButton.setFont(Constants.mainFont);

        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DragonValidator dragonValidator = new DragonValidator();
                    String name = argumentParser.parseArgFromString(nameField.getText(), dragonValidator::nameValidator, (String s) -> s);
                    Double x = argumentParser.parseArgFromString(xField.getText(), dragonValidator::xValidator, Double::parseDouble);
                    Integer y = argumentParser.parseArgFromString(yField.getText(), dragonValidator::yValidator, Integer::parseInt);
                    Coordinates coordinates = new Coordinates(x, y);
                    Long age = argumentParser.parseArgFromString(ageField.getText(), dragonValidator::ageValidator, Long::parseLong);
                    Float wingspan = argumentParser.parseArgFromString(wingspanField.getText(), dragonValidator::wingspanValidator, Float::parseFloat);
                    zula.common.data.Color color = argumentParser.parseArgFromString(colorField.getSelectedItem().toString(), dragonValidator::colorValidator, zula.common.data.Color::valueOf);
                    DragonType type = argumentParser.parseArgFromString( typeField.getSelectedItem().toString(), dragonValidator::typeValidator, DragonType::valueOf);
                    Float depth = argumentParser.parseArgFromString(depthField.getText(), dragonValidator::depthValidator, Float::parseFloat);
                    Double numberOfTreasures = argumentParser.parseArgFromString(numberOfTreasuresField.getText(), dragonValidator::numberOfTreasuresValidator, Double::parseDouble);
                    DragonCave dragonCave = new DragonCave(depth, numberOfTreasures);
                    Dragon dragon = new Dragon(name, coordinates, age, wingspan, color, type, dragonCave);
                    try {
                        connectionManager.sendToServer(new Add(), new Serializable[]{dragon});
                        connectionManager.getMessage();
                    } catch (SendException ex) {
                        ex.printStackTrace();
                    } catch (GetServerMessageException getServerMessageException) {
                        getServerMessageException.printStackTrace();
                    }
                    MainScreen mainScreen = new MainScreen(connectionManager, mainFrame, currentBundle);
                    mainScreen.startMain();

                } catch (WrongArgumentException wrongArgumentException) {
                    System.out.println("CHE");
                    JLabel errorLabel = new JLabel("CHECK THE CURRENCY OF THE DATA");
                    errorLabel.setFont(Constants.mainFont);
                    errorPanel.add(errorLabel);
                    mainFrame.revalidate();
                    mainFrame.repaint();
                }
            }
        });
    }

    public void drawAddPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(northPanel);
        mainPanel.add(centralPanel);
        mainPanel.add(southPanel);
        mainPanel.add(errorPanel);
        centralPanel.add(fieldText);
        centralPanel.add(valueText);
        centralPanel.add(requirementText);
        centralPanel.add(nameText);
        centralPanel.add(nameField);
        centralPanel.add(nameReq);
        centralPanel.add(xText);
        centralPanel.add(xField);
        centralPanel.add(xReq);
        centralPanel.add(yText);
        centralPanel.add(yField);
        centralPanel.add(yReq);
        centralPanel.add(ageText);
        centralPanel.add(ageField);
        centralPanel.add(ageReq);
        centralPanel.add(wingspanText);
        centralPanel.add(wingspanField);
        centralPanel.add(wingspanReq);
        centralPanel.add(colorText);
        centralPanel.add(colorField);
        centralPanel.add(colorReq);
        centralPanel.add(typeText);
        centralPanel.add(typeField);
        centralPanel.add(typeReq);
        centralPanel.add(depthText);
        centralPanel.add(depthField);
        centralPanel.add(depthReq);
        centralPanel.add(numberOfTreasuresText);
        centralPanel.add(numberOfTreasuresField);
        centralPanel.add(numberOfTreasuresReq);
        mainFrame.setContentPane(mainPanel);
        southPanel.add(submitButton);






        mainFrame.setVisible(true);
    }
}
