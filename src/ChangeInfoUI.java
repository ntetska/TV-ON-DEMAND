import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeInfoUI implements ActionListener, ItemListener {
    Login reconnection = new Login();
    private int customerID;

    //text input
    private final JTextField firstNameText = new JTextField(20);
    private final JTextField lastNameText = new JTextField(20);
    private final JTextField addressText = new JTextField(20);
    private final JTextField errorText = new JTextField(10);

    //buttons
    private final JButton changeFirstName = new JButton("CHANGE");
    private final JButton changeLastName = new JButton("CHANGE");
    private final JButton changeAddress = new JButton("CHANGE");
    private final JCheckBox moviesCheckBox = new JCheckBox("Only Movies");
    private final JCheckBox seriesCheckBox = new JCheckBox("Only Series");
    private final JCheckBox bothCheckBox = new JCheckBox("Movies And Series");
    private final JCheckBox activeCheckBox = new JCheckBox("Yes");
    private final JCheckBox inactiveCheckBox = new JCheckBox("No");


    private final JButton reconnectButton = new JButton("RECONNECT");

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    public void changeInfo(int customerID) {

        this.customerID = customerID;

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 350;
        int height = 300;

        //Calculate the frame location
        int x = ((screenSize.width - width) / 2);
        int y = ((screenSize.height - height) / 2);

        frame.setTitle("Change Current Info");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocation(x, y);

        frame.add(panel);
        panel.setLayout(null);

        //=======================================================================================//
        //firstname Name label
        int firstnameLabelXPos = (width - 315) / 2;
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(firstnameLabelXPos, 10, 70, 20);
        panel.add(firstNameLabel);

        //firstname text
        int firstNameTextXPos = (width - 180) / 2;

        firstNameText.setBounds(firstNameTextXPos, 10, 150, 20);
        firstNameText.setBorder(BorderFactory.createEmptyBorder());
        panel.add(firstNameText);
        //======================================================================================//

        //=======================================================================================//
        //lastname label
        int lastnameLabelXPos = (width - 295) / 2;
        JLabel lastnameLabel = new JLabel("Surname:");
        lastnameLabel.setBounds(lastnameLabelXPos, 50, 60, 20);
        panel.add(lastnameLabel);

        //lastname text
        int lastnameTextXPos = (width - 180) / 2;

        lastNameText.setBounds(lastnameTextXPos, 50, 150, 20);
        lastNameText.setBorder(BorderFactory.createEmptyBorder());
        panel.add(lastNameText);
        //======================================================================================//

        //=======================================================================================//
        //address label
        int addressLabelXPos = (width - 287) / 2;
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(addressLabelXPos, 90, 55, 20);
        panel.add(addressLabel);

        //address text
        int addressTextXPos = (width - 180) / 2;

        addressText.setBounds(addressTextXPos, 90, 150, 20);
        addressText.setBorder(BorderFactory.createEmptyBorder());
        panel.add(addressText);
        //======================================================================================//

        //=======================================================================================//
        //recordType label
        int recordTypeLabelXPos = (width - 327) / 2;
        JLabel recordTypeLabel = new JLabel("Record type:");
        recordTypeLabel.setBounds(recordTypeLabelXPos, 130, 75, 20);
        panel.add(recordTypeLabel);

        //======================================================================================//

        //=======================================================================================//
        //active label
        int activeLabelXPos = 230;
        JLabel activeLabel = new JLabel("Active:");
        activeLabel.setBounds(activeLabelXPos, 130, 75, 20);
        panel.add(activeLabel);

        //======================================================================================//

        //change firstName button
        int changeFirstNameButtonXPos = 245;
        changeFirstName.setBounds(changeFirstNameButtonXPos, 10, 82, 20);
        changeFirstName.setFocusPainted(false);
        panel.add(changeFirstName);
        changeFirstName.addActionListener(this);

        //change lastName button
        int changeLastNameButtonXPos = 245;
        changeLastName.setBounds(changeLastNameButtonXPos, 50, 82, 20);
        changeLastName.setFocusPainted(false);
        panel.add(changeLastName);
        changeLastName.addActionListener(this);

        //change address button
        int changeAddressButtonXPos = 245;
        changeAddress.setBounds(changeAddressButtonXPos, 90, 82, 20);
        changeAddress.setFocusPainted(false);
        panel.add(changeAddress);
        changeAddress.addActionListener(this);

        //reconnect button
        int reconnectButtonXPos = (width - 110) / 2;
        reconnectButton.setBounds(reconnectButtonXPos, 225, 110, 25);
        reconnectButton.setFocusPainted(false);
        panel.add(reconnectButton);
        reconnectButton.setToolTipText("Reconnect To Accept Changes");
        reconnectButton.addActionListener(this);

        //change record checkboxes
        //movies checkbox
        int moviesCheckboxXPos = (width - 180) / 2;
        moviesCheckBox.setBounds(moviesCheckboxXPos, 130, 100, 20);
        panel.add(moviesCheckBox);
        moviesCheckBox.addItemListener(this);

        //series checkbox
        int seriesCheckboxXPos = (width - 180) / 2;
        seriesCheckBox.setBounds(seriesCheckboxXPos, 160, 100, 20);
        panel.add(seriesCheckBox);
        seriesCheckBox.addItemListener(this);

        //both checkbox
        int bothCheckboxXPos = (width - 180) / 2;
        bothCheckBox.setBounds(bothCheckboxXPos, 190, 150, 20);
        panel.add(bothCheckBox);
        bothCheckBox.addItemListener(this);

        //active checkbox
        int activeCheckboxXPos = 270;
        activeCheckBox.setBounds(activeCheckboxXPos, 130, 150, 20);
        panel.add(activeCheckBox);
        activeCheckBox.addItemListener(this);

        //inactive checkbox
        int inactiveCheckboxXPos = 270;
        inactiveCheckBox.setBounds(inactiveCheckboxXPos, 150, 150, 20);
        panel.add(inactiveCheckBox);
        inactiveCheckBox.addItemListener(this);


        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeFirstName){
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "SET c.first_name = '" + firstNameText.getText() + "' " +
                            "WHERE c.customer_id = '" + customerID + "'");
        }

        if(e.getSource() == changeLastName){
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "SET c.last_name = '" + lastNameText.getText() + "' " +
                            "WHERE c.customer_id = '" + customerID + "'");
        }

        if(e.getSource() == changeAddress){
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "INNER JOIN address AS a " +
                            "ON a.address = '" + addressText.getText() + "' " +
                            "SET c.address_id = a.address_id " +
                            "WHERE c.customer_id = '" + customerID + "'");
        }

        if(e.getSource() == reconnectButton){
            frame.setVisible(false);
            reconnection.login();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String recordType = "";

        if(e.getSource() == moviesCheckBox){
            if(seriesCheckBox.isEnabled() && bothCheckBox.isEnabled() ){
                seriesCheckBox.setEnabled(false);
                bothCheckBox.setEnabled(false);
            }else{
                seriesCheckBox.setEnabled(true);
                bothCheckBox.setEnabled(true);
            }
        }

        if(e.getSource() == seriesCheckBox){
            if(bothCheckBox.isEnabled() && moviesCheckBox.isEnabled()){
                bothCheckBox.setEnabled(false);
                moviesCheckBox.setEnabled(false);
            }else{
                bothCheckBox.setEnabled(true);
                moviesCheckBox.setEnabled(true);
            }
        }

        if(e.getSource() == bothCheckBox){
            if(moviesCheckBox.isEnabled() && seriesCheckBox.isEnabled()){
                moviesCheckBox.setEnabled(false);
                seriesCheckBox.setEnabled(false);
            }else{
                moviesCheckBox.setEnabled(true);
                seriesCheckBox.setEnabled(true);
            }
        }

        if(e.getSource() == activeCheckBox){
            inactiveCheckBox.setEnabled(!inactiveCheckBox.isEnabled());
        }

        if(e.getSource() == inactiveCheckBox){
            activeCheckBox.setEnabled(!activeCheckBox.isEnabled());
        }

        if(moviesCheckBox.isSelected()){
            recordType = "MOVIES";
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "INNER JOIN record_type AS rt ON rt.record_type = '" + recordType + "' " +
                            "SET c.record_type_id = rt.record_type_id " +
                            "WHERE c.customer_id = '" + customerID + "'");
        }

        if(seriesCheckBox.isSelected()){
            recordType = "SERIES";
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "INNER JOIN record_type AS rt ON rt.record_type = '" + recordType + "' " +
                            "SET c.record_type_id = rt.record_type_id " +
                            "WHERE c.customer_id = '" + customerID + "'");
        }

        if(bothCheckBox.isSelected()){
            recordType = "BOTH";
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "INNER JOIN record_type AS rt ON rt.record_type = '" + recordType + "' " +
                            "SET c.record_type_id = rt.record_type_id " +
                            "WHERE c.customer_id = '" + customerID + "'");
        }

        if(activeCheckBox.isSelected()){
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "SET c.active = true " +
                            "WHERE c.customer_id = '" + customerID + "'");
        }

        if(inactiveCheckBox.isSelected()){
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "SET c.active = false " +
                            "WHERE c.customer_id = '" + customerID + "'");
        }
    }
}
