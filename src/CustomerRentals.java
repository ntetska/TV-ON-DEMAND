import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerRentals extends JFrame {
    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();

    public void rentalUI(int customerID) {
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
                        "WHERE r.customer_id = '" +customerID + "'" + " AND i.serie_id IS NULL "
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
                        "WHERE r.customer_id = '" + customerID + "'" + " AND i.serie_id IS NOT NULL "
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

            JLabel label = new JLabel("NO RECORDS FOUND!");
            label.setSize( label.getPreferredSize() );
            movieRentalTable.add(label);
            label.setForeground(Color.red);
            movieRentalTable.setFillsViewportHeight(true);
        }

        if(serieRentals.size() == 0){
            JLabel label = new JLabel("NO RECORDS FOUND!");
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

        frame.setTitle("CUSTOMER RENTALS");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);
        frame.add(panel);
        panel.setLayout(new GridLayout(2,0));


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

        panel.add(movieRentalTableScrollPane, BorderLayout.CENTER);
        panel.add(serieRentalTableScrollPane, BorderLayout.CENTER);
        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
