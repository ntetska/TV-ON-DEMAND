import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RentalUI extends JFrame implements ActionListener {

    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();

    private final JButton rent = new JButton("RENT");

    private final JTextField idText = new JTextField(20);

    private int customerID;
    private String recordType;

    private final JTextField onActionText = new JTextField(10);

    private final List<Integer> inventoryFilmIDs = DatabaseQueries.getFilmsInventoryID();
    private final List<Integer> inventorySerieIDs = DatabaseQueries.getSeriesInventoryID();
    private final List<Integer> inventoryFilmAndSerieIDs = DatabaseQueries.getFilmsAndSeriesInventoryID();

    public void rentalUI(int customerID, String recordType) {
        this.customerID = customerID;
        this.recordType = recordType;

        List<Rental> movieRentals = DatabaseQueries.getRentalsFromCustomerQuery(
                "SELECT r.rental_id AS rentalID, " +
                        "r.rental_date AS rentalDate, " +
                        "f.title AS title, " +
                        "r.isPaid AS isPaid " +
                        "FROM rental AS r " +
                        "INNER JOIN inventory AS i " +
                        "ON r.inventory_id = i.inventory_id " +
                        "INNER JOIN film AS f " +
                        "ON f.film_id = i.film_id " +
                        "WHERE r.customer_id = '" +this.customerID + "'" + " AND i.serie_id IS NULL "
        );

        List<Rental> serieRentals = DatabaseQueries.getRentalsFromCustomerQuery(
                "SELECT r.rental_id AS rentalID, " +
                        "r.rental_date AS rentalDate, " +
                        "s.title AS title, " +
                        "r.isPaid " +
                        "FROM rental AS r " +
                        "INNER JOIN inventory AS i " +
                        "ON r.inventory_id = i.inventory_id " +
                        "INNER JOIN serie AS s " +
                        "ON s.serie_id = i.serie_id " +
                        "WHERE r.customer_id = '" + this.customerID + "'" + " AND i.serie_id IS NOT NULL "
        );

        DefaultTableModel movieRentalTableModel = new DefaultTableModel();
        JTable movieRentalTable = new JTable(movieRentalTableModel);

        DefaultTableModel serieRentalTableModel = new DefaultTableModel();
        JTable serieRentalTable = new JTable(serieRentalTableModel);

        movieRentalTable.setRowHeight(30);

        movieRentalTable.setEnabled(false);
        movieRentalTableModel.addColumn("ID");
        movieRentalTableModel.addColumn("RENTAL DATE");
        movieRentalTableModel.addColumn("FILM TITLE");
        movieRentalTableModel.addColumn("PAYMENT");

        for (int i = 0; i < movieRentals.size(); i++) {
            movieRentalTableModel.insertRow(i, new Object[]{
                    movieRentals.get(i).getRentalID(),
                    movieRentals.get(i).getRentalDate(),
                    movieRentals.get(i).getTitle(),
                    movieRentals.get(i).isPaidText()}
            );
        }

        serieRentalTable.setRowHeight(30);

        serieRentalTable.setEnabled(false);
        serieRentalTableModel.addColumn("ID");
        serieRentalTableModel.addColumn("RENTAL DATE");
        serieRentalTableModel.addColumn("SERIE TITLE");
        serieRentalTableModel.addColumn("PAYMENT");

        for (int i = 0; i < serieRentals.size(); i++) {
            serieRentalTableModel.insertRow(i, new Object[]{
                    serieRentals.get(i).getRentalID(),
                    serieRentals.get(i).getRentalDate(),
                    serieRentals.get(i).getTitle(),
                    serieRentals.get(i).isPaidText()}
            );
        }

        if(movieRentals.size() == 0){

            JLabel label = new JLabel("NO RECORDS FOUND! CHANGE YOUR RECORD TYPE TO RENT MOVIES");
            label.setSize( label.getPreferredSize() );
            movieRentalTable.add(label);
            label.setForeground(Color.red);
            movieRentalTable.setFillsViewportHeight(true);
        }

        if(serieRentals.size() == 0){
            JLabel label = new JLabel("NO RECORDS FOUND! CHANGE YOUR RECORD TYPE TO RENT SERIES");
            label.setSize( label.getPreferredSize() );
            serieRentalTable.add(label);
            label.setForeground(Color.red);
            serieRentalTable.setFillsViewportHeight(true);
        }

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 500;
        int height = 500;

        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);

        frame.setTitle("RENT");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);

        frame.add(panel);
        panel.setLayout(null);


        //id label
        int idLabelXPos = (width - 270) / 2;
        JLabel idLabel = new JLabel("Movie or Serie ID:");
        idLabel.setBounds(idLabelXPos, 25, 100, 20);
        panel.add(idLabel);

        //id text
        int idTextXPos = (width - 65) / 2;
        idText.setBounds(idTextXPos, 25, 65, 20);
        idText.setBorder(BorderFactory.createEmptyBorder());
        panel.add(idText);
        //======================================================================================//

        //rent button
        int rentButtonXPos = (width - 80) / 2;

        rent.setBounds(rentButtonXPos, 60, 80, 25);
        rent.setFocusPainted(false);

        rent.addActionListener(this);
        panel.add(rent);

        //on action text
        int onActionTextXPos = 290;
        onActionText.setBounds(onActionTextXPos, 25, 210, 20);
        onActionText.setVisible(false);
        onActionText.setEditable(false);
        onActionText.setBorder(BorderFactory.createEmptyBorder());
        panel.add(onActionText);

        JScrollPane serieRentalTableScrollPane = new JScrollPane(serieRentalTable);
        serieRentalTableScrollPane.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "SERIE RENTALS",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));

        JScrollPane movieRentalTableScrollPane = new JScrollPane(movieRentalTable);
        movieRentalTableScrollPane.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "MOVIE RENTALS",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));


        GridLayout gridLayout = new GridLayout(3, 0);

        frame.add(movieRentalTableScrollPane, BorderLayout.CENTER);
        frame.add(serieRentalTableScrollPane, BorderLayout.CENTER);
        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setLayout(gridLayout);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int inventoryID = Integer.parseInt(idText.getText());
        if (this.recordType.equals("MOVIES")) {
            if (e.getSource() == rent) {
                if (inventoryFilmIDs.contains(inventoryID)) {
                    onActionText.setVisible(true);
                    DatabaseQueries.setRentalForCustomerQuery(
                            "INSERT INTO rental(rental_date, inventory_id,customer_id) " +
                                    "VALUES " +
                                    "(NOW()," + inventoryID + "," + customerID + ")"
                    );
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.GREEN);
                    onActionText.setText("REQUESTED SUCCESSFULLY");
                }else{
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.RED);
                    onActionText.setText("GIVEN ID NOT FOUND");
                }
            }
        }

        if (this.recordType.equals("SERIES")) {
            if (e.getSource() == rent) {
                if (inventorySerieIDs.contains(inventoryID)) {
                    onActionText.setVisible(true);
                    DatabaseQueries.setRentalForCustomerQuery(
                            "INSERT INTO rental(rental_date, inventory_id,customer_id) " +
                                    "VALUES " +
                                    "(NOW()," + inventoryID + "," + customerID + ")"
                    );
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.GREEN);
                    onActionText.setText("REQUESTED SUCCESSFULLY");
                }else{
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.RED);
                    onActionText.setText("GIVEN ID NOT FOUND");
                }
            }
        }

        if (this.recordType.equals("BOTH")) {
            if (e.getSource() == rent) {
                if (inventoryFilmAndSerieIDs.contains(inventoryID)) {
                    onActionText.setVisible(true);
                    DatabaseQueries.setRentalForCustomerQuery(
                            "INSERT INTO rental(rental_date, inventory_id,customer_id) " +
                                    "VALUES " +
                                    "(NOW()," + inventoryID + "," + customerID + ")"
                    );
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.GREEN);
                    onActionText.setText("REQUESTED SUCCESSFULLY");
                }else{
                    onActionText.setVisible(true);
                    onActionText.setForeground(Color.RED);
                    onActionText.setText("GIVEN ID NOT FOUND");
                }
            }
        }
    }
}
