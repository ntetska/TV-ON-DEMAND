import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CreateUserUI extends JFrame implements ActionListener, ItemListener {
    private final JTextField onActionText = new JTextField(10);

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    private int isActive;
    private boolean isAdmin;

    private int recordType;

    JLabel firstNameCustomerLabel, lastNameCustomerLabel, emailCustomerLabel, addressCustomerLabel,
            recordTypeCustomerLabel, activeCustomerLabel, adminLabel,
            firstNameEmployeeLabel, lastNameEmployeeLabel, emailEmployeeLabel, addressEmployeeLabel;
    JTextField firstNameCustomerText, lastNameCustomerText,
            emailCustomerText, addressCustomerText,
            firstNameEmployeeText, lastNameEmployeeText, emailEmployeeText, addressEmployeeText;

    private final JButton createCustomerButton = new JButton("CREATE");
    private final JButton createEmployeeButton = new JButton("CREATE");

    private final JCheckBox activeCheckBox = new JCheckBox("Yes");
    private final JCheckBox inactiveCheckBox = new JCheckBox("No");

    private final JCheckBox adminTrueCheckBox = new JCheckBox("Yes");
    private final JCheckBox adminFalseCheckBox = new JCheckBox("No");

    private final JCheckBox moviesCheckBox = new JCheckBox("Only Movies");
    private final JCheckBox seriesCheckBox = new JCheckBox("Only Series");
    private final JCheckBox bothCheckBox = new JCheckBox("Movies And Series");

    //Get the screen size
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    //size of frame
    int width = 500;
    int height = 300;

    CreateUserUI() {

    }

    int x = (int) ((screenSize.getWidth() - width) / 2);
    int y = (int) ((screenSize.getHeight() - height) / 2);


    public void createCustomerUI() {

        frame.setVisible(true);
        frame.setTitle("Create Customer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocation(x, y);


        //onAction text
        onActionText.setBounds(130, 5, 250, 25);
        onActionText.setVisible(false);
        onActionText.setEditable(false);
        onActionText.setBorder(BorderFactory.createEmptyBorder());

        firstNameCustomerLabel = new JLabel("First Name:");
        firstNameCustomerLabel.setBounds(20, 30, 105, 20);

        firstNameCustomerText = new JTextField(50);
        firstNameCustomerText.setBounds(130, 30, 120, 20);

        lastNameCustomerLabel = new JLabel("Last Name:");
        lastNameCustomerLabel.setBounds(20, 60, 105, 20);

        lastNameCustomerText = new JTextField(50);
        lastNameCustomerText.setBounds(130, 60, 120, 20);

        emailCustomerLabel = new JLabel("Email:");
        emailCustomerLabel.setBounds(20, 90, 100, 20);

        emailCustomerText = new JTextField(100);
        emailCustomerText.setBounds(130, 90, 200, 20);

        addressCustomerLabel = new JLabel("Address ID:");
        addressCustomerLabel.setBounds(20, 120, 90, 20);

        addressCustomerText = new JTextField(50);
        addressCustomerText.setBounds(130, 120, 70, 20);

        recordTypeCustomerLabel = new JLabel("Record Type:");
        recordTypeCustomerLabel.setBounds(20, 150, 100, 20);

        //movies checkbox
        moviesCheckBox.setBounds(130, 150, 95, 20);
        panel.add(moviesCheckBox);
        moviesCheckBox.addItemListener(this);

        //series checkbox
        seriesCheckBox.setBounds(230, 150, 90, 20);
        panel.add(seriesCheckBox);
        seriesCheckBox.addItemListener(this);

        //both checkbox
        bothCheckBox.setBounds(330, 150, 130, 20);
        panel.add(bothCheckBox);
        bothCheckBox.addItemListener(this);

        activeCustomerLabel = new JLabel("Active:");
        activeCustomerLabel.setBounds(20, 180, 80, 20);

        //active checkbox
        activeCheckBox.setBounds(130, 180, 50, 20);
        panel.add(activeCheckBox);
        activeCheckBox.addItemListener(this);

        //inactive checkbox
        inactiveCheckBox.setBounds(180, 180, 50, 20);
        panel.add(inactiveCheckBox);
        inactiveCheckBox.addItemListener(this);

        //create button
        int createButtonXPos = (width - 85) / 2;
        createCustomerButton.setBounds(createButtonXPos, 220, 85, 25);
        createCustomerButton.setFocusPainted(false);
        panel.add(createCustomerButton);
        createCustomerButton.addActionListener(this);

        frame.setLayout(null);
        frame.add(panel);
        //add
        frame.add(firstNameCustomerLabel);
        frame.add(firstNameCustomerText);
        frame.add(lastNameCustomerLabel);
        frame.add(lastNameCustomerText);
        frame.add(emailCustomerLabel);
        frame.add(emailCustomerText);
        frame.add(addressCustomerLabel);
        frame.add(addressCustomerText);
        frame.add(recordTypeCustomerLabel);
        frame.add(activeCustomerLabel);
        frame.add(activeCheckBox);
        frame.add(inactiveCheckBox);
        frame.add(moviesCheckBox);
        frame.add(seriesCheckBox);
        frame.add(bothCheckBox);
        frame.add(createCustomerButton);
        frame.add(onActionText);
    }

    public void createEmployeeUI() {

        frame.setVisible(true);
        frame.setTitle("Create Employee");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocation(x, y);


        //onAction text
        onActionText.setBounds(130, 5, 250, 25);
        onActionText.setVisible(false);
        onActionText.setEditable(false);
        onActionText.setBorder(BorderFactory.createEmptyBorder());

        firstNameEmployeeLabel = new JLabel("First Name:");
        firstNameEmployeeLabel.setBounds(20, 30, 105, 20);

        firstNameEmployeeText = new JTextField(50);
        firstNameEmployeeText.setBounds(130, 30, 120, 20);

        lastNameEmployeeLabel = new JLabel("Last Name:");
        lastNameEmployeeLabel.setBounds(20, 60, 105, 20);

        lastNameEmployeeText = new JTextField(50);
        lastNameEmployeeText.setBounds(130, 60, 120, 20);

        emailEmployeeLabel = new JLabel("Email:");
        emailEmployeeLabel.setBounds(20, 90, 100, 20);

        emailEmployeeText = new JTextField(100);
        emailEmployeeText.setBounds(130, 90, 200, 20);

        addressEmployeeLabel = new JLabel("Address ID:");
        addressEmployeeLabel.setBounds(20, 120, 90, 20);

        addressEmployeeText = new JTextField(50);
        addressEmployeeText.setBounds(130, 120, 70, 20);

        adminLabel = new JLabel("Admin:");
        adminLabel.setBounds(20, 150, 100, 20);

        //is admin checkbox
        adminTrueCheckBox.setBounds(130, 150, 50, 20);
        panel.add(adminTrueCheckBox);
        adminTrueCheckBox.addItemListener(this);

        //is not admin checkbox
        adminFalseCheckBox.setBounds(180, 150, 50, 20);
        panel.add(adminFalseCheckBox);
        adminFalseCheckBox.addItemListener(this);

        //create button
        int createButtonXPos = (width - 85) / 2;
        createEmployeeButton.setBounds(createButtonXPos, 220, 85, 25);
        createEmployeeButton.setFocusPainted(false);
        panel.add(createEmployeeButton);
        createEmployeeButton.addActionListener(this);

        frame.setLayout(null);
        frame.add(panel);
        //add
        frame.add(firstNameEmployeeLabel);
        frame.add(firstNameEmployeeText);
        frame.add(lastNameEmployeeLabel);
        frame.add(lastNameEmployeeText);
        frame.add(emailEmployeeLabel);
        frame.add(emailEmployeeText);
        frame.add(addressEmployeeLabel);
        frame.add(addressEmployeeText);
        frame.add(adminLabel);
        frame.add(adminTrueCheckBox);
        frame.add(adminFalseCheckBox);
        frame.add(createEmployeeButton);
        frame.add(onActionText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createCustomerButton) {
            if (firstNameCustomerText.getText().isEmpty() ||
                    lastNameCustomerText.getText().isEmpty() ||
                    emailCustomerText.getText().isEmpty() ||
                    addressCustomerText.getText().isEmpty()) {
                onActionText.setVisible(true);
                onActionText.setText("ALL FIELDS MUST BE FILLED");
                onActionText.setForeground(Color.RED);
            } else {
                DatabaseQueries.createCustomerQuery(firstNameCustomerText.getText(),
                        lastNameCustomerText.getText(),
                        emailCustomerText.getText(),
                        Integer.parseInt(addressCustomerText.getText()),
                        this.isActive,
                        this.recordType
                );
                onActionText.setVisible(false);
            }
        }

        if (e.getSource() == createEmployeeButton) {
            if (firstNameEmployeeText.getText().isEmpty() ||
                    lastNameEmployeeText.getText().isEmpty() ||
                    emailEmployeeText.getText().isEmpty() ||
                    addressEmployeeText.getText().isEmpty()) {
                onActionText.setVisible(true);
                onActionText.setText("ALL FIELDS MUST BE FILLED");
                onActionText.setForeground(Color.RED);
            } else {
                DatabaseQueries.createEmployeeQuery(firstNameEmployeeText.getText(),
                        lastNameEmployeeText.getText(),
                        emailEmployeeText.getText(),
                        Integer.parseInt(addressEmployeeText.getText()),
                        this.isAdmin
                );
                onActionText.setVisible(false);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == activeCheckBox) {
            inactiveCheckBox.setEnabled(!inactiveCheckBox.isEnabled());
        }

        if (e.getSource() == inactiveCheckBox) {
            activeCheckBox.setEnabled(!activeCheckBox.isEnabled());
        }

        if (activeCheckBox.isSelected()) {
            this.isActive = 1;
        }

        if (inactiveCheckBox.isSelected()) {
            this.isActive = 0;
        }

        if (e.getSource() == adminTrueCheckBox) {
            adminFalseCheckBox.setEnabled(!adminFalseCheckBox.isEnabled());
        }

        if (e.getSource() == adminFalseCheckBox) {
            adminTrueCheckBox.setEnabled(!adminTrueCheckBox.isEnabled());
        }

        if (adminTrueCheckBox.isSelected()) {
            this.isAdmin = true;
        }

        if (adminFalseCheckBox.isSelected()) {
            this.isAdmin = false;
        }

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


        if(moviesCheckBox.isSelected()){
            this.recordType = 1;
        }

        if(seriesCheckBox.isSelected()){
            this.recordType = 2;
        }

        if(bothCheckBox.isSelected()){
            this.recordType = 3;
        }
    }
}
