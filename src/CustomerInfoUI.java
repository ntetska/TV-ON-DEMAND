import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class CustomerInfoUI implements ActionListener {
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final JButton changeButton = new JButton("CHANGE INFO");

    private int customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String city;
    private boolean active;
    private Date create_date;
    private String record_type;

    public void infoUI(List<Customer> customerInfo) {
        //get customer info from list
        for (Customer c :
                customerInfo) {
            this.customer_id = c.getCustomer_id();
            this.first_name = c.getFirst_name();
            this.last_name = c.getLast_name();
            this.email = c.getEmail();
            this.address = c.getAddress();
            this.city = c.getCity();
            this.active = c.isActive();
            this.create_date = c.getCreate_date();
            this.record_type = c.getRecord_type();
        }

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 350;
        int height = 250;

        //Calculate the frame location
        int x = ((screenSize.width - width) / 2);
        int y = ((screenSize.height - height) / 2);

        frame.setTitle(first_name + " " + last_name + "'s" + " Profile");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocation(x, y);

        frame.add(panel);
        panel.setLayout(null);

        //fullName label
        int firstnameLabelXPos = (width - 320) / 2;
        JLabel fullNameLabel = new JLabel("Name:");
        fullNameLabel.setBounds(firstnameLabelXPos, 10, 150, 20);
        panel.add(fullNameLabel);

        Border fullNameBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        //fullName text
        int fullNameTextXPos = (width - 240) / 2;
        JTextField fullNameText = new JTextField(20);
        fullNameText.setBounds(fullNameTextXPos, 10, 150, 20);
        fullNameText.setText(this.first_name + " " + this.last_name);
        fullNameText.setEditable(false);
        fullNameText.setBorder(fullNameBorder);
        panel.add(fullNameText);
        //======================================================================================//

        //email label
        int emailLabelXPos = (width - 320) / 2;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(emailLabelXPos, 35, 150, 20);
        panel.add(emailLabel);

        Border emailBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        //email text
        int emailTextXPos = (width - 240) / 2;
        JTextField emailText = new JTextField(20);
        emailText.setBounds(emailTextXPos, 35, 250, 20);
        emailText.setText(this.email);
        emailText.setEditable(false);
        emailText.setBorder(emailBorder);
        panel.add(emailText);
        //======================================================================================//

        //address label
        int addressLabelXPos = (width - 320) / 2;
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(addressLabelXPos, 60, 150, 20);
        panel.add(addressLabel);

        Border addressBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        //email text
        int addressTextXPos = (width - 210) / 2;
        JTextField addressText = new JTextField(20);
        addressText.setBounds(addressTextXPos, 60, 250, 20);
        addressText.setText(this.address);
        addressText.setEditable(false);
        addressText.setBorder(addressBorder);
        panel.add(addressText);
        //======================================================================================//

        //city label
        int cityLabelXPos = (width - 320) / 2;
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(cityLabelXPos, 85, 150, 20);
        panel.add(cityLabel);

        Border cityBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        //city text
        int cityTextXPos = (width - 260) / 2;
        JTextField cityText = new JTextField(20);
        cityText.setBounds(cityTextXPos, 85, 150, 20);
        cityText.setText(this.city);
        cityText.setEditable(false);
        cityText.setBorder(cityBorder);
        panel.add(cityText);
        //======================================================================================//

        //======================================================================================//
        //active label
        int activeLabelXPos = (width - 320) / 2;
        JLabel activeLabel = new JLabel("Active:");
        activeLabel.setBounds(activeLabelXPos, 110, 150, 20);
        panel.add(activeLabel);

        Border activeBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        //active text
        int activeTextXPos = (width - 240) / 2;
        JTextField activeText = new JTextField(20);
        activeText.setBounds(activeTextXPos, 110, 250, 20);
        activeText.setText(this.active ? "Yes" : "No");
        activeText.setEditable(false);
        activeText.setBorder(activeBorder);
        panel.add(activeText);
        //======================================================================================//

        //======================================================================================//
        //record type label
        int recordTypeLabelXPos = (width - 320) / 2;
        JLabel recordTypeLabel = new JLabel("Record type:");
        recordTypeLabel.setBounds(recordTypeLabelXPos, 135, 150, 20);
        panel.add(recordTypeLabel);

        Border recordTypeBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        //record type text
        int recordTypeTextXPos = (width - 165) / 2;
        JTextField recordTypeText = new JTextField(20);
        recordTypeText.setBounds(recordTypeTextXPos, 135, 250, 20);
        recordTypeText.setText((this.record_type.equals("BOTH")) ? "Movies and Series" : this.record_type.substring(0,1).toUpperCase() + this.record_type.substring(1).toLowerCase());
        recordTypeText.setEditable(false);
        recordTypeText.setBorder(recordTypeBorder);
        panel.add(recordTypeText);
        //======================================================================================//

        //======================================================================================//
        //changeInfo button

        createChangeButton();

        //======================================================================================//
        frame.setVisible(true);
        frame.setResizable(false);

    }

    private void createChangeButton(){
        int changeInfoButtonXPos = (350 - 320) / 2;

        changeButton.setBounds(changeInfoButtonXPos, 170, 120, 25);
        changeButton.setFocusPainted(false);

        changeButton.addActionListener(this);
        panel.add(changeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeButton){
            ChangeInfoUI changeInfoUI = new ChangeInfoUI();
            frame.setVisible(false);
            changeInfoUI.changeInfo(customer_id);
        }

    }
}
