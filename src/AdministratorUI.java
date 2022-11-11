import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AdministratorUI extends JFrame implements ActionListener, ItemListener {
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    private final JButton createCustomers = new JButton("CREATE CUSTOMER");
    private final JButton createEmployees = new JButton("CREATE EMPLOYEE");
    private final JButton incomePerMonthButton = new JButton("INCOME PER MONTH");
    private final JButton deleteUsers = new JButton("DELETE");
    private final JButton change = new JButton("CHANGE");
    private final JButton priceChange = new JButton("SET PRICE");

    private final JCheckBox adminCheckBox = new JCheckBox("Admin");
    private final JCheckBox employeeCheckBox = new JCheckBox("Employee");

    private final JLabel emailLabel = new JLabel();
    private final JTextField emailText = new JTextField(10);

    private final JLabel changeUserToAdminLabel = new JLabel();
    private final JTextField changeUserToAdminText = new JTextField(10);

    private boolean isChangingToAdmin;

    public void adminUI() {

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 350;
        int height = 500;

        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);

        frame.setTitle("ADMINISTRATOR MENU");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);

        //create customers button
        createCustomers.setFocusPainted(false);
        createCustomers.setBounds(95, 300, 150, 25);
        createCustomers.addActionListener(this);
        panel.add(createCustomers);

        //create employees button
        createEmployees.setFocusPainted(false);
        createEmployees.setBounds(95, 340, 150, 25);
        createEmployees.addActionListener(this);
        panel.add(createEmployees);

        //get income per month button
        incomePerMonthButton.setFocusPainted(false);
        incomePerMonthButton.setBounds(95, 380, 150, 25);
        incomePerMonthButton.addActionListener(this);
        panel.add(incomePerMonthButton);

        //set price button
        priceChange.setFocusPainted(false);
        priceChange.setBounds(95, 420, 150, 25);
        priceChange.addActionListener(this);
        panel.add(priceChange);


        //email label
        emailLabel.setText("Email:");
        emailLabel.setBounds(25, 35, 40, 20);
        panel.add(emailLabel);

        //email text
        emailText.setBounds(65, 35, 250, 20);
        emailText.setEditable(true);
        emailText.setBorder(BorderFactory.createEmptyBorder());
        //add label
        JLabel deleteLabel = new JLabel("Delete User By Email");
        deleteLabel.setForeground(Color.RED);
        deleteLabel.setBounds(65, 13, 120, 25);
        deleteLabel.setVisible(true);
        //=============================================//
        panel.add(emailText);
        panel.add(deleteLabel);

        //delete employees or customers button
        deleteUsers.setFocusPainted(false);
        deleteUsers.setBounds(135, 65, 100, 25);
        deleteUsers.addActionListener(this);
        panel.add(deleteUsers);

        //email for change employee to admin or the opposite label
        changeUserToAdminLabel.setText("Email:");
        changeUserToAdminLabel.setBounds(25, 150, 40, 20);
        panel.add(changeUserToAdminLabel);

        //email for change employee to admin or the opposite text
        changeUserToAdminText.setBounds(65, 150, 250, 20);
        changeUserToAdminText.setEditable(true);
        changeUserToAdminText.setBorder(BorderFactory.createEmptyBorder());
        //add label
        JLabel changeLabel = new JLabel("Change Employee To Admin And Opposite");
        changeLabel.setForeground(Color.RED);
        changeLabel.setBounds(65, 128, 250, 25);
        changeLabel.setVisible(true);
        //=============================================================================//
        panel.add(changeUserToAdminText);
        panel.add(changeLabel);

        //admin checkbox
        adminCheckBox.setBounds(100, 185, 80, 20);
        panel.add(adminCheckBox);
        adminCheckBox.addItemListener(this);

        //employee checkbox
        employeeCheckBox.setBounds(190, 185, 80, 20);
        panel.add(employeeCheckBox);
        employeeCheckBox.addItemListener(this);

        //change employee to admin and opposite
        change.setFocusPainted(false);
        change.setBounds(135, 220, 100, 25);
        change.addActionListener(this);
        panel.add(change);

        panel.setLayout(null);
        frame.add(panel);
        frame.validate();
        frame.setLayout(new GridLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == createCustomers) {
            CreateUserUI createUserUI = new CreateUserUI();
            createUserUI.createCustomerUI();
        }

        if (e.getSource() == createEmployees) {
            CreateUserUI createUserUI = new CreateUserUI();
            createUserUI.createEmployeeUI();
        }

        if (e.getSource() == deleteUsers) {
            DatabaseQueries.deleteUserQuery(emailText.getText());
        }


        if (e.getSource() == change) {
            if (adminCheckBox.isSelected() || employeeCheckBox.isSelected()) {
                adminCheckBox.setForeground(Color.BLACK);
                employeeCheckBox.setForeground(Color.BLACK);
                DatabaseQueries.changeUserPrivilegesQuery(this.isChangingToAdmin, changeUserToAdminText.getText());
            } else {
                adminCheckBox.setForeground(Color.RED);
                employeeCheckBox.setForeground(Color.RED);
            }
        }

        if (e.getSource() == incomePerMonthButton) {
            List<IncomePerMonth> incomePerMonths = DatabaseQueries.callGetIncomePerMonthProcedure();
            IncomePerMonthUI incomePerMonthUI = new IncomePerMonthUI();
            incomePerMonthUI.incomeUI(incomePerMonths);
        }

        if(e.getSource() == priceChange){
            PriceUI priceUI = new PriceUI();
            priceUI.setPriceUI();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == adminCheckBox) {
            employeeCheckBox.setEnabled(!employeeCheckBox.isEnabled());
        }

        if (e.getSource() == employeeCheckBox) {
            adminCheckBox.setEnabled(!adminCheckBox.isEnabled());
        }

        if (adminCheckBox.isSelected()) {
            this.isChangingToAdmin = true;
        }

        if (employeeCheckBox.isSelected()) {
            this.isChangingToAdmin = false;
        }
    }
}
