import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FilmAndSerieInfoUI extends JFrame implements ActionListener {

    private List<Customer> customerInfo = new ArrayList<>();

    private final JFrame frame = new JFrame("TV ON DEMAND(MODE:FILMS AND SERIES)");

    private int customerID;
    private String recordType;


    FilmAndSerieInfoUI(){

    }

    public void infoUI(List<Film> filmList, List<Serie> serieList, List<Customer> customerInfo) {

        for (Customer c :
                customerInfo) {
            this.customerID = c.getCustomer_id();
            this.recordType = c.getRecord_type();
        }

        DefaultTableModel filmTableModel = new DefaultTableModel();
        DefaultTableModel serieTableModel = new DefaultTableModel();

        JTable filmTable = new JTable(filmTableModel);
        JTable serieTable = new JTable(serieTableModel);

        filmTable.setRowHeight(30);
        serieTable.setRowHeight(30);

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
            filmTableModel.insertRow(i, new Object[] {
                    filmList.get(i).getId(),
                    filmList.get(i).getTitle(),
                    filmList.get(i).getDescription(),
                    filmList.get(i).getRelease_year(),
                    filmList.get(i).getLanguage(),
                    filmList.get(i).getOriginal_language(),
                    filmList.get(i).getLength(),
                    filmList.get(i).getRating(),
                    filmList.get(i).getSpecial_features()});
        }

        serieTable.setEnabled(false);
        serieTableModel.addColumn("ID");
        serieTableModel.addColumn("TITLE");
        serieTableModel.addColumn("DESCRIPTION");
        serieTableModel.addColumn("RELEASE YEAR");
        serieTableModel.addColumn("RATING");
        serieTableModel.addColumn("SPECIAL FEATURES");
        serieTableModel.addColumn("LANGUAGE");
        serieTableModel.addColumn("ORIGINAL LANGUAGE");

        for(int i =0; i<serieList.size();i++){
            serieTableModel.insertRow(i, new Object[] {
                    serieList.get(i).getId(),
                    serieList.get(i).getTitle(),
                    serieList.get(i).getDescription(),
                    serieList.get(i).getRelease_year(),
                    serieList.get(i).getLanguage(),
                    serieList.get(i).getOriginal_language(),
                    serieList.get(i).getRating(),
                    serieList.get(i).getSpecial_features()});
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
        frame.setLocation(x,y);

        JScrollPane serieTableScrollPane = new JScrollPane(serieTable);
        serieTableScrollPane.setBorder(
                BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "SERIES",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        JScrollPane filmTableScrollPane = new JScrollPane(filmTable);
        filmTableScrollPane.setBorder(
                BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                        "MOVIES",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));

        this.customerInfo = customerInfo;
        createMenuBar();

        frame.add(filmTableScrollPane, BorderLayout.SOUTH);
        frame.add(serieTableScrollPane, BorderLayout.NORTH);

        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2,0));
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
            CustomerInfoUI customerInfoUI = new CustomerInfoUI();
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
