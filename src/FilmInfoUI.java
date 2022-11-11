import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FilmInfoUI implements ActionListener {
    private final CustomerInfoUI customerInfoUI = new CustomerInfoUI();
    private List<Customer> customerInfo = new ArrayList<>();
    private final JFrame frame = new JFrame("TV ON DEMAND(MODE:FILMS)");

    private int customerID;
    private String recordType;

    private List<Film> films;

    FilmInfoUI(){

    }

    public void infoUI(List<Film> filmList, List<Customer> customerInfo) {
        this.films = filmList;

        DefaultTableModel filmTableModel = new DefaultTableModel();
        JTable filmTable = new JTable(filmTableModel);

        for (Customer c :
                customerInfo) {
            this.customerID = c.getCustomer_id();
            this.recordType = c.getRecord_type();
        }

        filmTable.setRowHeight(30);

        filmTable.setEnabled(false);
        filmTableModel.addColumn("ID");
        filmTableModel.addColumn("TITLE");
        filmTableModel.addColumn("DESCRIPTION");
        filmTableModel.addColumn("RELEASE YEAR");
        filmTableModel.addColumn("LANGUAGE");
        filmTableModel.addColumn("ORIGINAL LANGUAGE");
        filmTableModel.addColumn("LENGTH");
        filmTableModel.addColumn("RATING");
        filmTableModel.addColumn("SPECIAL FEATURES");
        for(int i =0; i<filmList.size();i++){
            filmTableModel.insertRow(i, new Object[] {filmList.get(i).getId(),
                    filmList.get(i).getTitle(),
                    filmList.get(i).getDescription(),
                    filmList.get(i).getRelease_year(),
                    filmList.get(i).getLanguage(),
                    filmList.get(i).getOriginal_language(),
                    filmList.get(i).getLength(),
                    filmList.get(i).getRating(),
                    filmList.get(i).getSpecial_features()});
        }

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 1280;
        int height = 720;

        frame.setSize(width, height);
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);

        JScrollPane filmTableScrollPane = new JScrollPane(filmTable);

        filmTableScrollPane.setBorder(
                BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                        "MOVIES",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));

        filmTableScrollPane.setPreferredSize(new Dimension(width, height));

        this.customerInfo = customerInfo;
        createMenuBar();

        frame.add(filmTableScrollPane, BorderLayout.CENTER);

        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setVisible (true);

    }


    private final JMenuBar menuBar = new JMenuBar();

    //settings
    private final JMenu settings = new JMenu("SETTINGS");
    private final JMenuItem myAccount = new JMenuItem("My Account");

    //rent
    private final JMenu rent = new JMenu("RENT");
    private final JMenuItem makeARent = new JMenuItem("Make a rental");

    //pay
    private final JMenu pay = new JMenu("PAY");
    private final JMenuItem makeAPayment = new JMenuItem("Make a payment");


    private void createMenuBar() {
        Font  settingFo  = new Font(Font.SERIF, Font.BOLD,  17);
        Font  rentFo  = new Font(Font.SERIF, Font.BOLD,  17);
        Font  paymentFo  = new Font(Font.SERIF, Font.BOLD,  17);

        Font  myAccountFo  = new Font(Font.SERIF, Font.BOLD,  15);
        Font  makeARentFo  = new Font(Font.SERIF, Font.BOLD,  15);
        Font  makeAPaymentFo  = new Font(Font.SERIF, Font.BOLD,  15);


        menuBar.setBackground(Color.lightGray);


        //settings
        settings.setForeground(Color.white);
        settings.setFont(settingFo);
        settings.add(myAccount);

        myAccount.addActionListener(this);
        myAccount.setFont(myAccountFo);

        //Rentals
        rent.setForeground(Color.white);
        rent.setFont(rentFo);
        rent.add(makeARent);

        makeARent.setFont(makeARentFo);
        makeARent.addActionListener(this);

        //Payments
        pay.setForeground(Color.white);
        pay.setFont(paymentFo);
        pay.add(makeAPayment);

        makeAPayment.setFont(makeAPaymentFo);
        makeAPayment.addActionListener(this);

        menuBar.add(settings);
        menuBar.add(rent);
        menuBar.add(pay);

        frame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == myAccount){
            customerInfoUI.infoUI(customerInfo);
        }

        if(e.getSource() == makeARent){
            RentalUI rentalUI = new RentalUI();
            rentalUI.rentalUI(this.customerID, this.recordType);
        }

        if(e.getSource() == makeAPayment){
            PaymentUI paymentUI = new PaymentUI();

            paymentUI.paymentUI(this.customerID, this.recordType);
        }
    }
}
