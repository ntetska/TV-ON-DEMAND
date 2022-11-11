import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PaymentUI extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();

    private final JButton pay = new JButton("PAY");

    private final JTextField idText = new JTextField(20);

    private int customerID;
    private String recordType;

    private final JTextField onActionText = new JTextField(10);

    private List<Integer> rentalList;

    private boolean isSerie;


    public void paymentUI(int customerID, String recordType) {
        this.customerID = customerID;
        this.recordType = recordType;

        //=======================================================================================//
        List<Payment> moviePayments = DatabaseQueries.getPaymentsFromCustomerQuery(
                "SELECT p.payment_id AS paymentID, " +
                        "p.amount, " +
                        "p.payment_date AS paymentDate, " +
                        "p.rental_id AS rentalID, " +
                        "p.customer_id AS customerID " +
                        "FROM payment AS p " +
                        "INNER JOIN customer AS c ON c.customer_id = p.customer_id " +
                        "INNER JOIN rental AS r ON r.rental_id = p.rental_id " +
                        "INNER JOIN inventory AS i ON i.inventory_id = r.inventory_id " +
                        "WHERE p.customer_id = '" + this.customerID + "'" + " AND i.serie_id IS NULL"
        );

        List<Payment> seriePayments = DatabaseQueries.getPaymentsFromCustomerQuery(
                "SELECT p.payment_id AS paymentID, " +
                        "p.amount, " +
                        "p.payment_date AS paymentDate, " +
                        "p.rental_id AS rentalID, " +
                        "p.customer_id AS customerID " +
                        "FROM payment AS p " +
                        "INNER JOIN customer AS c ON c.customer_id = p.customer_id " +
                        "INNER JOIN rental AS r ON r.rental_id = p.rental_id " +
                        "INNER JOIN inventory AS i ON i.inventory_id = r.inventory_id " +
                        "WHERE p.customer_id = '" + this.customerID + "'" + " AND i.serie_id IS NOT NULL "
        );
        //=================================================================================================//

        //get customer rentals
        this.rentalList = DatabaseQueries.getRentalsIDFromCustomer(customerID);

        DefaultTableModel paymentSerieTableModel = new DefaultTableModel();
        JTable paymentSerieTable = new JTable(paymentSerieTableModel);

        DefaultTableModel paymentMovieTableModel = new DefaultTableModel();
        JTable paymentMovieTable = new JTable(paymentMovieTableModel);

        paymentMovieTable.setRowHeight(30);
        paymentSerieTable.setRowHeight(30);

        paymentMovieTable.setEnabled(false);
        paymentMovieTableModel.addColumn("ID");
        paymentMovieTableModel.addColumn("AMOUNT");
        paymentMovieTableModel.addColumn("PAYMENT DATE");

        for (int i = 0; i < moviePayments.size(); i++) {
            paymentMovieTableModel.insertRow(i, new Object[]{
                    moviePayments.get(i).getPaymentID(),
                    moviePayments.get(i).getAmount(),
                    moviePayments.get(i).getPaymentDate()
                }
            );
        }

        paymentSerieTable.setEnabled(false);
        paymentSerieTableModel.addColumn("ID");
        paymentSerieTableModel.addColumn("PRICE");
        paymentSerieTableModel.addColumn("PAYMENT DATE");

        if(seriePayments.size() == 0){

            JLabel label = new JLabel("NO RECORDS FOUND!");
            label.setSize( label.getPreferredSize() );
            paymentSerieTable.add(label);
            label.setForeground(Color.red);
            paymentSerieTable.setFillsViewportHeight(true);
        }

        if(moviePayments.size() == 0){
            JLabel label = new JLabel("NO RECORDS FOUND!");
            label.setSize( label.getPreferredSize() );
            paymentMovieTable.add(label);
            label.setForeground(Color.red);
            paymentMovieTable.setFillsViewportHeight(true);
        }

        for (int i = 0; i < seriePayments.size(); i++) {
            paymentSerieTableModel.insertRow(i, new Object[]{
                    seriePayments.get(i).getPaymentID(),
                    seriePayments.get(i).getAmount(),
                    seriePayments.get(i).getPaymentDate()
                    }
            );
        }

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 500;
        int height = 500;

        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);

        frame.setTitle("PAY");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);

        frame.add(panel);
        panel.setLayout(null);


        //id label
        int idLabelXPos = (width - 180) / 2;
        JLabel idLabel = new JLabel("Rental ID:");
        idLabel.setBounds(idLabelXPos, 25, 55, 20);
        panel.add(idLabel);

        //id text
        int idTextXPos = (width - 65) / 2;
        idText.setBounds(idTextXPos, 25, 65, 20);
        idText.setBorder(BorderFactory.createEmptyBorder());
        panel.add(idText);
        //======================================================================================//

        //rent button
        int payButtonXPos = (width - 80) / 2;

        pay.setBounds(payButtonXPos, 60, 80, 25);
        pay.setFocusPainted(false);

        pay.addActionListener(this);
        panel.add(pay);

        //error text
        int onActionTextXPos = (width - 250) / 2;
        onActionText.setBounds(onActionTextXPos, 90, 250, 25);
        onActionText.setVisible(false);
        onActionText.setEditable(false);
        onActionText.setBorder(BorderFactory.createEmptyBorder());
        panel.add(onActionText);

        JScrollPane paymentMovieTableScrollPane = new JScrollPane(paymentMovieTable);
        paymentMovieTableScrollPane.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "MOVIE PAYMENTS",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));

        JScrollPane paymentSerieTableScrollPane = new JScrollPane(paymentSerieTable);
        paymentSerieTableScrollPane.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "SERIE PAYMENTS",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));

        GridLayout gridLayout = new GridLayout(3, 0);

        frame.add(paymentMovieTableScrollPane, BorderLayout.CENTER);
        frame.add(paymentSerieTableScrollPane, BorderLayout.CENTER);
        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setLayout(gridLayout);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rentalID = Integer.parseInt(idText.getText());
        if (this.recordType.equals("MOVIES")) {
            if (e.getSource() == pay) {
                if (rentalList.contains(rentalID) && !DatabaseQueries.getIfRentalIsPaidQuery(rentalID)) {
                    onActionText.setVisible(true);
                    double price = DatabaseQueries.getFilmPriceQuery(this.customerID);
                    DatabaseQueries.setPaymentForCustomerQuery(
                            "INSERT INTO payment(customer_id, rental_id,amount,payment_date) " +
                                    "VALUES " +
                                    "(" + customerID + ", " +rentalID + ", "+ price +", NOW() )"
                    );
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.GREEN);
                    onActionText.setText("PAID SUCCESSFULLY");
                    DatabaseQueries.setIsPaidRentalForCustomerQuery(rentalID);
                }else{
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.RED);
                    onActionText.setText("GIVEN ID NOT FOUND");
                }
            }
        }

        if (this.recordType.equals("SERIES")) {
            if (e.getSource() == pay) {
                if (rentalList.contains(rentalID) && !DatabaseQueries.getIfRentalIsPaidQuery(rentalID)) {
                    double price = DatabaseQueries.getSeriePriceQuery(this.customerID, rentalID);
                    DatabaseQueries.setPaymentForCustomerQuery(
                            "INSERT INTO payment(customer_id, rental_id,amount,payment_date) " +
                                    "VALUES " +
                                    "(" + customerID + ", " +rentalID + ", " +price +", NOW() )"
                    );
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.GREEN);
                    onActionText.setText("PAID SUCCESSFULLY");

                    DatabaseQueries.setIsPaidRentalForCustomerQuery(rentalID);
                }else{
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.RED);
                    onActionText.setText("GIVEN ID NOT FOUND");
                }
            }
        }

        if (this.recordType.equals("BOTH")) {
            if (e.getSource() == pay) {
                if (rentalList.contains(rentalID) && !DatabaseQueries.getIfRentalIsPaidQuery(rentalID)) {
                    double price = DatabaseQueries.getFilmAndSeriePriceQuery(this.customerID, rentalID);
                    DatabaseQueries.setPaymentForCustomerQuery(
                            "INSERT INTO payment(customer_id, rental_id,amount,payment_date) " +
                                    "VALUES " +
                                    "(" + customerID + ", " +rentalID + ", "+ price +", NOW() )"
                    );
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.GREEN);
                    onActionText.setText("PAID SUCCESSFULLY");

                    DatabaseQueries.setIsPaidRentalForCustomerQuery(rentalID);
                }else{
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.RED);
                    onActionText.setText("GIVEN ID NOT FOUND OR ALREADY PAID");
                }
            }
        }
    }
}
