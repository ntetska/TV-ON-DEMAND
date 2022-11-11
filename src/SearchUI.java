import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class SearchUI extends JFrame implements ActionListener, ItemListener {

    private final JTextField onActionText = new JTextField(10);
    private String email = "";
    private int customerID;

    private final JCheckBox activeCheckBox = new JCheckBox("Yes");
    private final JCheckBox inactiveCheckBox = new JCheckBox("No");


    JLabel lb, lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
    JButton submitBtn, setFirstName, setLastName,
            setAddress, setRecord, updateFirstName,
            updateLastname, updateAddress, updateRecord,
            seeRentals, setActive;
    //Get the screen size
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    //size of frame
    int width = 500;
    int height = 500;

    //Creating Constructor for initializing JFrame components
    SearchUI() {
        //Providing Title
        super("Search Customer Information");


        int x = (int) ((screenSize.getWidth() - width) / 2);
        int y = (int) ((screenSize.getHeight() - height) / 2);

        lb5 = new JLabel("Enter Email:");
        lb5.setBounds(60, 20, 75, 20);

        tf5 = new JTextField(20);
        tf5.setBounds(130, 20, 200, 20);


        //active checkbox
        int activeCheckboxXPos = 290;
        activeCheckBox.setBounds(activeCheckboxXPos, 330, 50, 20);
        add(activeCheckBox);
        activeCheckBox.setVisible(false);
        activeCheckBox.addItemListener(this);

        //inactive checkbox
        int inactiveCheckboxXPos = 340;
        inactiveCheckBox.setBounds(inactiveCheckboxXPos, 330, 50, 20);
        add(inactiveCheckBox);
        inactiveCheckBox.setVisible(false);
        inactiveCheckBox.addItemListener(this);

        //see rentals
        seeRentals = new JButton("RENTALS");
        seeRentals.setBounds(200, 380, 100, 25);
        seeRentals.addActionListener(this);
        seeRentals.setFocusPainted(false);

        //submit
        submitBtn = new JButton("SUBMIT");
        submitBtn.setBounds(180, 50, 100, 20);
        submitBtn.addActionListener(this);
        submitBtn.setFocusPainted(false);

        //setFirstName
        setFirstName = new JButton("SET");
        setFirstName.setBounds(260, 120, 60, 20);
        setFirstName.addActionListener(this);
        setFirstName.setFocusPainted(false);

        //updateFirstName
        updateFirstName = new JButton("UPDATE");
        updateFirstName.setBounds(330, 120, 85, 20);
        updateFirstName.addActionListener(this);
        updateFirstName.setFocusPainted(false);

        //setLastName
        setLastName = new JButton("SET");
        setLastName.setBounds(260, 150, 60, 20);
        setLastName.addActionListener(this);
        setLastName.setFocusPainted(false);

        //updateLastName
        updateLastname = new JButton("UPDATE");
        updateLastname.setBounds(330, 150, 85, 20);
        updateLastname.addActionListener(this);
        updateLastname.setFocusPainted(false);

        //setAddress
        setAddress = new JButton("SET");
        setAddress.setBounds(320, 210, 60, 20);
        setAddress.addActionListener(this);
        setAddress.setFocusPainted(false);

        //updateAddress
        updateAddress = new JButton("UPDATE");
        updateAddress.setBounds(390, 210, 85, 20);
        updateAddress.addActionListener(this);
        updateAddress.setFocusPainted(false);

        //setRecord
        setRecord = new JButton("SET");
        setRecord.setBounds(240, 300, 60, 20);
        setRecord.addActionListener(this);
        setRecord.setFocusPainted(false);

        //updateRecord
        updateRecord = new JButton("UPDATE");
        updateRecord.setBounds(310, 300, 85, 20);
        updateRecord.addActionListener(this);
        updateRecord.setFocusPainted(false);

        //setActive
        setActive = new JButton("SET");
        setActive.setBounds(220, 330, 60, 20);
        setActive.addActionListener(this);
        setActive.setFocusPainted(false);


        lb = new JLabel("Customer Information From Database");
        lb.setBounds(30, 80, 450, 30);
        lb.setForeground(Color.RED);
        lb.setFont(new Font("Serif", Font.BOLD, 20));

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLocation(x, y);


        lb1 = new JLabel("First Name:");
        lb1.setBounds(20, 120, 120, 20);

        tf1 = new JTextField(50);
        tf1.setBounds(130, 120, 120, 20);

        lb8 = new JLabel("Last Name:");
        lb8.setBounds(20, 150, 120, 20);

        tf8 = new JTextField(50);
        tf8.setBounds(130, 150, 120, 20);

        lb2 = new JLabel("Email:");
        lb2.setBounds(20, 180, 100, 20);

        tf2 = new JTextField(100);
        tf2.setBounds(130, 180, 200, 20);

        lb3 = new JLabel("Address:");
        lb3.setBounds(20, 210, 90, 20);

        tf3 = new JTextField(50);
        tf3.setBounds(130, 210, 180, 20);

        lb4 = new JLabel("City:");
        lb4.setBounds(20, 240, 100, 20);

        tf4 = new JTextField(50);
        tf4.setBounds(130, 240, 100, 20);

        lb6 = new JLabel("Create Date:");
        lb6.setBounds(20, 270, 100, 20);

        tf6 = new JTextField(50);
        tf6.setBounds(130, 270, 100, 20);

        lb7 = new JLabel("Record Type:");
        lb7.setBounds(20, 300, 100, 20);

        tf7 = new JTextField(50);
        tf7.setBounds(130, 300, 100, 20);

        lb9 = new JLabel("Active:");
        lb9.setBounds(20, 330, 80, 20);

        tf9 = new JTextField(50);
        tf9.setBounds(130, 330, 80, 20);

        setLayout(null);

        //on action text
        onActionText.setBounds(340, 20, 210, 20);
        onActionText.setVisible(false);
        onActionText.setEditable(false);
        onActionText.setBorder(BorderFactory.createEmptyBorder());


        //Add components to the JFrame
        add(onActionText);
        add(lb5);
        add(tf5);
        add(submitBtn);
        add(setFirstName);
        add(setLastName);
        add(setAddress);
        add(setRecord);
        add(setActive);
        add(updateFirstName);
        add(updateLastname);
        add(updateAddress);
        add(updateRecord);
        add(seeRentals);
        add(lb);
        add(lb1);
        add(tf1);
        add(lb2);
        add(tf2);
        add(lb3);
        add(tf3);
        add(lb4);
        add(tf4);
        add(lb6);
        add(tf6);
        add(lb7);
        add(tf7);
        add(lb8);
        add(tf8);
        add(lb9);
        add(tf9);
        //Set TextField Editable False
        tf1.setEditable(false);

        tf2.setEditable(false);

        tf3.setEditable(false);

        tf4.setEditable(false);

        tf6.setEditable(false);

        tf7.setEditable(false);

        tf8.setEditable(false);

        tf9.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitBtn) {
            List<Customer> customerInfo = DatabaseQueries.getCustomerFullInfoQuery(
                    "SELECT c.customer_id, " +
                            "c.first_name, " +
                            "c.last_name, " +
                            "c.email, " +
                            "a.address, " +
                            "ct.city, " +
                            "c.active, " +
                            "c.create_date, " +
                            "r.record_type " +
                            "FROM customer AS c " +
                            "INNER JOIN address AS a ON a.address_id = c.address_id " +
                            "INNER JOIN city AS ct ON ct.city_id = a.city_id " +
                            "INNER JOIN record_type AS r ON r.record_type_id = c.record_type_id " +
                            "WHERE c.email = '" + tf5.getText() + "'");

            for (Customer c :
                    customerInfo) {
                tf1.setText(c.getFirst_name());
                tf8.setText(c.getLast_name());
                tf2.setText(c.getEmail());
                tf3.setText(c.getAddress());
                tf4.setText(c.getCity());
                tf6.setText(c.getCreate_date().toString());
                tf7.setText(c.getRecord_type());
                tf9.setText(c.isActiveText());

                if (c.getEmail() != null && c.getEmail().equals(tf2.getText())) {
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.GREEN);
                    onActionText.setText("FOUND!");
                }
                this.customerID = c.getCustomer_id();
            }

            this.email = tf2.getText();

        }

        if (e.getSource() == setFirstName) {
            tf1.setEditable(true);
        }
        if (e.getSource() == updateFirstName) {
            DatabaseQueries.updateCustomerInfoQuery("UPDATE customer AS c SET c.first_name = '" + tf1.getText() + "' " +
                    "WHERE c.email = '" + this.email + "'");
            tf1.setEditable(false);
        }


        if (e.getSource() == setLastName) {
            tf8.setEditable(true);
        }
        if (e.getSource() == updateLastname) {
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c SET c.last_name = '" + tf8.getText() + "' " +
                            "WHERE c.email = '" + this.email + "'");
            tf8.setEditable(false);
        }


        if (e.getSource() == setAddress) {
            tf3.setEditable(true);
        }
        if (e.getSource() == updateAddress) {
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "INNER JOIN address AS a " +
                            "ON a.address = '" + tf3.getText() + "' " +
                            "SET c.address_id = a.address_id " +
                            "WHERE c.email = '" + this.email + "'");
            tf3.setEditable(false);
        }


        if (e.getSource() == setRecord) {
            tf7.setEditable(true);
        }
        if (e.getSource() == updateRecord) {
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "INNER JOIN record_type AS rt " +
                            "ON rt.record_type = '" + tf7.getText() + "' " +
                            "SET c.record_type_id = rt.record_type_id " +
                            "WHERE c.email = '" + this.email + "'");
            tf7.setEditable(false);
        }

        if (e.getSource() == setActive) {
            activeCheckBox.setVisible(true);
            inactiveCheckBox.setVisible(true);
        }

        if (e.getSource() == seeRentals) {
            CustomerRentals customerRentals = new CustomerRentals();
            customerRentals.rentalUI(this.customerID);
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
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "SET c.active = true " +
                            "WHERE c.customer_id = '" + this.customerID + "'");
        }

        if (inactiveCheckBox.isSelected()) {
            DatabaseQueries.updateCustomerInfoQuery(
                    "UPDATE customer AS c " +
                            "SET c.active = false " +
                            "WHERE c.customer_id = '" + this.customerID + "'");
        }
    }
}
