import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class EmployeeUI extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();

    private final JButton searchCustomers = new JButton("SEARCH CUSTOMER");
    private final JButton firstFiveMovies = new JButton("THE FIRST FIVE MOVIES");
    private final JButton firstFiveSeries = new JButton("THE FIRST FIVE SERIES");

    private final JTextField onActionText = new JTextField(10);

    public void employeeUI() {

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 300;
        int height = 200;

        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);

        frame.setTitle("EMPLOYEE MENU");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);

        //search customer button
        searchCustomers.setFocusPainted(false);
        searchCustomers.addActionListener(this);
        panel.add(searchCustomers);

        //first five movies button
        firstFiveMovies.setFocusPainted(false);
        firstFiveMovies.addActionListener(this);
        panel.add(firstFiveMovies);

        //first five series button
        firstFiveSeries.setFocusPainted(false);
        firstFiveSeries.addActionListener(this);
        panel.add(firstFiveSeries);


        frame.add(panel);
        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == searchCustomers){
            SearchUI searchUI = new SearchUI();
        }

        if(e.getSource() == firstFiveMovies){
            try {
               List<FirstFiveRentals> results = DatabaseQueries.callGetMovieOrSerieProcedure('m',5,java.sql.Date.valueOf("2021-11-01"), java.sql.Date.valueOf("2021-11-30"));
               FirstFiveRentalsUI firstFiveRentals = new FirstFiveRentalsUI();
               firstFiveRentals.showFirstFive(results);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource() == firstFiveSeries){
            try {
                List<FirstFiveRentals> results = DatabaseQueries.callGetMovieOrSerieProcedure('s',5,java.sql.Date.valueOf("2021-11-01"), java.sql.Date.valueOf("2021-11-30"));
                FirstFiveRentalsUI firstFiveRentals = new FirstFiveRentalsUI();
                firstFiveRentals.showFirstFive(results);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
