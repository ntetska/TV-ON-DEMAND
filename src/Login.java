import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class Login implements ActionListener {
    private final JTextField emailText = new JTextField(20);
    private final JTextField errorText = new JTextField(10);

    private final JFrame frame = new JFrame("Login to TV ON DEMAND");
    private final JPanel panel = new JPanel();

    public void login() {
        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 350;
        int height = 170;

        //Calculate the frame location
        int x = ((screenSize.width - width) / 2);
        int y = ((screenSize.height - height) / 2);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(x, y);

        frame.add(panel);
        panel.setLayout(null);

        //error text
        int errorTextXPos = (width - 110) / 2;
        errorText.setBounds(errorTextXPos, 110, 210, 20);
        errorText.setVisible(false);
        errorText.setEditable(false);
        errorText.setBorder(BorderFactory.createEmptyBorder());
        errorText.setForeground(Color.RED);
        panel.add(errorText);

        //email label
        int emailLabelPos = (width - 230) / 2;
        JLabel userLabel = new JLabel("EMAIL");
        userLabel.setBounds(emailLabelPos, 15, 220, 25);
        panel.add(userLabel);

        //email input
        int emailTextXPos = (width - 230) / 2;
        emailText.setBounds(emailTextXPos, 40, 220, 25);
        emailText.setBorder(BorderFactory.createEtchedBorder());
        panel.add(emailText);

        //login button
        int loginButtonXPos = (width - 90) / 2;
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(loginButtonXPos, 75, 80, 25);
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        loginButton.addActionListener(this);

        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        //check if email belongs to an employee
        List<Employee> employees = DatabaseQueries.getEmployeesQuery();
        for (Employee em:
             employees) {
            if(em.getEmail().equals(emailText.getText())){
                frame.setVisible(false);
                EmployeeUI employeeUI = new EmployeeUI();
                employeeUI.employeeUI();
                return;
            }
        }

        //check if email belongs to an administrator
        List<Administrator> administrators = DatabaseQueries.getAdminsQuery();
        for (Administrator ad:
                administrators) {
            if(ad.getEmail().equals(emailText.getText())){
                frame.setVisible(false);
                AdministratorUI administratorUI = new AdministratorUI();
                administratorUI.adminUI();
                return;
            }
        }

        //else check if customer
        List<Customer> customerFullInfoQuery = DatabaseQueries.getCustomerFullInfoQuery(
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
                        "WHERE c.email = '" + emailText.getText() + "'");

        //get record type of customer
        String recordType = DatabaseQueries.getCustomerRecordTypeQuery(
                "SELECT record_type " +
                        "FROM customer AS c " +
                        "INNER JOIN record_type AS rt " +
                        "ON rt.record_type_id = c.record_type_id " +
                        "WHERE c.email = '" + emailText.getText() + "'");

        if(recordType.equals("MOVIES")){
            frame.setVisible(false);
            FilmInfoUI filmInfoUI = new FilmInfoUI();
            List<Film> filmFullInfoQuery = DatabaseQueries.getFilmsFullInfoQuery(
                    "SELECT i.inventory_id AS inventoryID, " +
                            "f.title, " +
                            "f.description, " +
                            "f.release_year, " +
                            "l.name AS language, " +
                            "l1.name AS original_language ," +
                            "f.length," +
                            "f.rating," +
                            "f.special_features " +
                            "FROM film AS f " +
                            "LEFT JOIN language AS l " +
                            "ON l.language_id = f.language_id " +
                            "LEFT JOIN language AS l1 " +
                            "ON l1.language_id = f.original_language_id " +
                            "INNER JOIN inventory AS i " +
                            "ON i.film_id = f.film_id" );
            filmInfoUI.infoUI(filmFullInfoQuery, customerFullInfoQuery);
        }

        if(recordType.equals("SERIES")){
            frame.setVisible(false);
            SerieInfoUI serieInfoUI = new SerieInfoUI();
            List<Serie> serieFullInfoQuery = DatabaseQueries.getSeriesFullInfoQuery(
                    "SELECT i.inventory_id AS inventoryID, " +
                            "s.title, " +
                            "s.description, " +
                            "s.release_year, " +
                            "s.rating," +
                            "s.special_features, " +
                            "l.name AS language, " +
                            "l1.name AS original_language " +
                            "FROM serie AS s " +
                            "LEFT JOIN language AS l " +
                            "ON l.language_id = s.language_id " +
                            "LEFT JOIN language AS l1 " +
                            "ON l1.language_id = s.original_language_id " +
                            "INNER JOIN inventory AS i " +
                            "ON i.serie_id = s.serie_id" );
            serieInfoUI.infoUI(serieFullInfoQuery, customerFullInfoQuery);
        }

        if(recordType.equals("BOTH")){
            frame.setVisible(false);
            FilmAndSerieInfoUI filmAndSerieInfoUI = new FilmAndSerieInfoUI();

            List<Film> filmFullInfoQuery = DatabaseQueries.getFilmsFullInfoQuery(
                    "SELECT i.inventory_id AS inventoryID, " +
                            "f.title, " +
                            "f.description, " +
                            "f.release_year, " +
                            "l.name AS language, " +
                            "l1.name AS original_language ," +
                            "f.length," +
                            "f.rating," +
                            "f.special_features " +
                            "FROM film AS f " +
                            "LEFT JOIN language AS l " +
                            "ON l.language_id = f.language_id " +
                            "LEFT JOIN language AS l1 " +
                            "ON l1.language_id = f.original_language_id " +
                            "INNER JOIN inventory AS i " +
                            "ON i.film_id = f.film_id" );

            List<Serie> serieFullInfoQuery = DatabaseQueries.getSeriesFullInfoQuery(
                    "SELECT i.inventory_id AS inventoryID, " +
                            "s.title, " +
                            "s.description, " +
                            "s.release_year, " +
                            "l.name AS language, " +
                            "l1.name AS original_language, " +
                            "s.rating," +
                            "s.special_features " +
                            "FROM serie AS s " +
                            "LEFT JOIN language AS l " +
                            "ON l.language_id = s.language_id " +
                            "LEFT JOIN language AS l1 " +
                            "ON l1.language_id = s.original_language_id " +
                            "INNER JOIN inventory AS i " +
                            "ON i.serie_id = s.serie_id" );

            filmAndSerieInfoUI.infoUI(filmFullInfoQuery,serieFullInfoQuery, customerFullInfoQuery);
        }
        errorText.setVisible(true);
        errorText.setText("USER NOT FOUND");


    }
}
