import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FirstFiveRentalsUI {

    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();

    public void showFirstFive(List<FirstFiveRentals> results) {

        DefaultTableModel rentalsTableModel = new DefaultTableModel();
        JTable rentalsTable = new JTable(rentalsTableModel);

        rentalsTable.setRowHeight(30);

        rentalsTable.setEnabled(false);
        rentalsTableModel.addColumn("ID");
        rentalsTableModel.addColumn("TITLE");
        rentalsTableModel.addColumn("NO. OF RENTALS");


        for (int i = 0; i < results.size(); i++) {
            rentalsTableModel.insertRow(i, new Object[]{
                    results.get(i).getId(),
                    results.get(i).getTitle(),
                    results.get(i).getRentals()}
            );
        }

        if(results.size() == 0){

            JLabel label = new JLabel("NO RECORDS FOUND!");
            label.setSize( label.getPreferredSize() );
            rentalsTable.add(label);
            label.setForeground(Color.red);
            rentalsTable.setFillsViewportHeight(true);
        }

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 500;
        int height = 350;

        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);

        frame.setTitle("FIRST FIVE RENTALS");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);

        frame.add(panel);
        panel.setLayout(null);

        JScrollPane rentalTableScrollPane = new JScrollPane(rentalsTable);
        rentalTableScrollPane.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "RENTALS",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));

        frame.add(rentalTableScrollPane, BorderLayout.CENTER);
        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
