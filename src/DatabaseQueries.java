import com.google.protobuf.StringValue;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DatabaseQueries {
    private static final String url = "jdbc:mysql://localhost:3306/tvondemand";
    private static final String user = "root";
    private static final String password = "1q2w3e4r@manos";

    //fetch all details from Customer
    public static List<Customer> getCustomerFullInfoQuery(String query) {
        List<Customer> getData = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            (query);

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getBoolean("active"),
                        rs.getDate("create_date"),
                        rs.getString("record_type")
                );
                getData.add(customer);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getData;
    }

    //call procedure MovieOrSerie information
    public static List<FirstFiveRentals> callGetMovieOrSerieProcedure(char character, int limit, java.sql.Date startDate, java.sql.Date endDate) throws SQLException {
        CallableStatement cstmt = null;

        List<FirstFiveRentals> results = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            cstmt = con.prepareCall("{call getMovieOrSerieInfo(?,?,?,?)}");

            cstmt.setString(1, String.valueOf(character));
            cstmt.setInt(2, limit);
            cstmt.setDate(3, startDate);
            cstmt.setDate(4, endDate);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                FirstFiveRentals firstFiveRentals = new FirstFiveRentals(
                        rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getInt("RENTALS")
                );

                results.add(firstFiveRentals);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    //fetch all details from Films
    public static List<Film> getFilmsFullInfoQuery(String query) {
        List<Film> filmList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            (query);

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                Film film = new Film(
                        rs.getInt("inventoryID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("release_year"),
                        rs.getString("language"),
                        rs.getString("original_language"),
                        rs.getInt("length"),
                        rs.getString("rating"),
                        rs.getString("special_features")
                );
                filmList.add(film);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmList;
    }

    //fetch record type from Customer
    public static String getCustomerRecordTypeQuery(String query) {
        String recordType = "";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            (query);

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            if (rs.next()) {
                recordType = rs.getString("record_type");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordType;
    }

    //fetch all details from Films
    public static List<Serie> getSeriesFullInfoQuery(String query) {
        List<Serie> serieList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            (query);

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                Serie serie = new Serie(
                        rs.getInt("inventoryID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("release_year"),
                        rs.getString("rating"),
                        rs.getString("special_features"),
                        rs.getString("language"),
                        rs.getString("original_language")
                );
                serieList.add(serie);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serieList;
    }

    //update customer information
    public static void updateCustomerInfoQuery(String query) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            int rs =
                    stmt.executeUpdate(query);

            if (rs == 1) {
                System.out.println("row updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //fetch customer's rentals
    public static List<Rental> getRentalsFromCustomerQuery(String query) {
        List<Rental> rentalList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();

            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            (query);

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                Rental rental = new Rental(
                        rs.getInt("rentalID"),
                        rs.getDate("rentalDate"),
                        rs.getString("title"),
                        rs.getBoolean("isPaid")
                );
                rentalList.add(rental);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentalList;
    }

    //fetch customer's payments
    public static List<Payment> getPaymentsFromCustomerQuery(String query) {
        List<Payment> paymentList = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();

            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            (query);

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("paymentID"),
                        rs.getDouble("amount"),
                        rs.getDate("paymentDate"),
                        rs.getInt("rentalID"),
                        rs.getInt("customerID")
                );
                paymentList.add(payment);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    //add a rental for customer
    public static void setRentalForCustomerQuery(String query) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            int rs =
                    stmt.executeUpdate(query);

            if (rs == 1) {
                System.out.println("One row inserted to rentals");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //add a payment for customer
    public static void setPaymentForCustomerQuery(String query) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            int rs =
                    stmt.executeUpdate(query);

            if (rs == 1) {
                System.out.println("One row inserted to payments");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //add true if rental isPaid for customer
    public static void setIsPaidRentalForCustomerQuery(int rentalID) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE rental AS r SET r.isPaid = true " +
                    "WHERE r.rental_id = ? ");
           preparedStatement.setInt(1,rentalID);

            int rs =
                    preparedStatement.executeUpdate();

            if (rs == 1) {
                System.out.println("One column updated to rental");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //fetch paid rentals from customer
    public static boolean getIfRentalIsPaidQuery(int rentalID) {
        boolean isPaid = false;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();

            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            ("SELECT r.isPaid FROM rental AS r " +
                                    "WHERE r.rental_id = '" + rentalID + "'");

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            if (rs.next()) {
                isPaid = rs.getBoolean("isPaid");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isPaid;
    }


    //add a payment for customer
    public static double getFilmPriceQuery(int customerID) {
        double price = 0;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery("SELECT pr.price " +
                            "FROM prices AS pr " +
                            "INNER JOIN customer AS c ON c.customer_id = '" + customerID + "' " +
                            "INNER JOIN record_type AS rt ON rt.record_type_id = c.record_type_id " +
                            "WHERE pr.record_type = rt.record_type"
                    );

            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    //add a payment for customer
    public static double getSeriePriceQuery(int customerID, int rentalID) {
        double price = 0;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery("SELECT (pr.price * COUNT(sn.episode_id)) AS price " +
                            "FROM prices AS pr " +
                            "INNER JOIN rental AS r ON r.rental_id = '" + rentalID + "' " +
                            "INNER JOIN inventory AS i ON i.inventory_id = r.inventory_id " +
                            "INNER JOIN season AS sn ON sn.serie_id = i.serie_id " +
                            "INNER JOIN customer AS c ON c.customer_id = '" + customerID + "' " +
                            "INNER JOIN record_type AS rt ON rt.record_type_id = c.record_type_id " +
                            "WHERE pr.record_type = rt.record_type"
                    );

            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    //get if rental references to serie and use this method to 'getFilmAndSeriePriceQuery'
    private static int getIsRentalForSerie(int rentalID) {
        int serie_id = 0;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery("SELECT i.serie_id FROM rental AS r " +
                            "INNER JOIN inventory i on r.inventory_id = i.inventory_id " +
                            "WHERE r.rental_id = '" + rentalID + "'");
            if (rs.next()) {
                serie_id = rs.getInt("serie_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serie_id;
    }

    //add a payment for customer
    public static double getFilmAndSeriePriceQuery(int customerID, int rentalID) {
        int serie_id = getIsRentalForSerie(rentalID);

        double price = 0;

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data

            ResultSet rs;
            if (serie_id == 0) {
                rs = stmt.executeQuery(
                        "SELECT pr.price AS price " +
                                "FROM prices AS pr " +
                                "INNER JOIN rental AS r ON r.rental_id = '" + rentalID + "' " +
                                "INNER JOIN inventory AS i ON i.inventory_id = r.inventory_id " +
                                "INNER JOIN customer AS c ON c.customer_id = '" + customerID + "' " +
                                "INNER JOIN record_type AS rt ON rt.record_type_id = c.record_type_id " +
                                "WHERE pr.record_type = rt.record_type AND pr.isSerie = false"
                );
            } else {
                rs = stmt.executeQuery(
                        "SELECT pr.price * COUNT(sn.episode_id) AS price " +
                                "FROM prices AS pr " +
                                "INNER JOIN rental AS r ON r.rental_id = '" + rentalID + "' " +
                                "INNER JOIN inventory AS i ON i.inventory_id = r.inventory_id " +
                                "INNER JOIN season AS sn ON sn.serie_id = i.serie_id " +
                                "INNER JOIN customer AS c ON c.customer_id = '" + customerID + "' " +
                                "INNER JOIN record_type AS rt ON rt.record_type_id = c.record_type_id " +
                                "WHERE pr.record_type = rt.record_type AND pr.isSerie = true"
                );
            }
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    //fetch all films and series ids that are in inventory
    public static List<Integer> getFilmsAndSeriesInventoryID() {
        List<Integer> inventoryIDs = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            ("SELECT i.inventory_id FROM inventory AS i ");

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                inventoryIDs.add(rs.getInt("inventory_id"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventoryIDs;
    }

    //fetch all series ids that are in inventory
    public static List<Integer> getSeriesInventoryID() {
        List<Integer> inventoryIDs = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            ("SELECT i.inventory_id FROM inventory AS i " +
                                    "INNER JOIN serie AS s ON s.serie_id = i.serie_id");

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                inventoryIDs.add(rs.getInt("inventory_id"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventoryIDs;
    }

    //fetch all films ids that are in inventory
    public static List<Integer> getFilmsInventoryID() {
        List<Integer> inventoryIDs = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            ("SELECT i.inventory_id FROM inventory AS i " +
                                    "INNER JOIN film AS f ON f.film_id = i.film_id");

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                inventoryIDs.add(rs.getInt("inventory_id"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventoryIDs;
    }

    //fetch all films and series ids that are in inventory
    public static List<Integer> getRentalsIDFromCustomer(int customerID) {
        List<Integer> rentalIDs = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();
            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            ("SELECT r.rental_id FROM rental AS r " +
                                    "WHERE r.customer_id = '" + customerID + "'");

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                rentalIDs.add(rs.getInt("rental_id"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentalIDs;
    }

    //get employees
    public static List<Employee> getEmployeesQuery() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();

            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            ("SELECT e.employee_id AS employeeID, " +
                                    "e.first_name AS firstName," +
                                    "e.last_name AS lastName, " +
                                    "e.email " +
                                    "FROM employee e " +
                                    "WHERE e.admin = FALSE");

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("employeeID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email")
                );
                employeeList.add(employee);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    //get employees who are admins
    public static List<Administrator> getAdminsQuery() {
        List<Administrator> adminList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            //Creating a Statement object
            Statement stmt = con.createStatement();

            //Retrieving the data
            ResultSet rs =
                    stmt.executeQuery
                            ("SELECT e.employee_id AS employeeID, " +
                                    "e.first_name AS firstName," +
                                    "e.last_name AS lastName, " +
                                    "e.email, " +
                                    "e.admin " +
                                    "FROM employee e " +
                                    "WHERE e.admin = TRUE");

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            }
            while (rs.next()) {
                Administrator admin = new Administrator(
                        rs.getInt("employeeID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getBoolean("admin")
                );
                adminList.add(admin);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }

    //create customer
    public static void createCustomerQuery(String firstName, String lastName, String email, int addressID, int active, int record_type_id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO customer(first_name,last_name,email,address_id,active,create_date,record_type_id) " +
                    "VALUES(?,?,?,?,?,NOW(),?)");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setInt(4, addressID);
            pstmt.setInt(5, active);
            pstmt.setInt(6, record_type_id);
            //Retrieving the data
            int rs =
                    pstmt.executeUpdate();

            if (rs == 1) {
                System.out.println("one row inserted in customer table");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //create employee
    public static void createEmployeeQuery(String firstName, String lastName, String email, int addressID, boolean admin) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO employee(first_name,last_name,email,address_id,create_date,admin) " +
                    "VALUES(?,?,?,?,NOW(),?)");

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setInt(4, addressID);
            pstmt.setBoolean(5, admin);

            //Retrieving the data
            int rs =
                    pstmt.executeUpdate();

            if (rs == 1) {
                System.out.println("one row inserted in employee table");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //create employee
    public static void deleteUserQuery(String email) {
        List<Administrator> administrators = DatabaseQueries.getAdminsQuery();
        List<Employee> employees = DatabaseQueries.getEmployeesQuery();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            for (Administrator ad :
                    administrators) {
                if (ad.getEmail().equals(email)) {
                    PreparedStatement pstmt = con.prepareStatement("DELETE FROM employee WHERE email = ?");
                    pstmt.setString(1, email);
                    //Retrieving the data
                    int rs =
                            pstmt.executeUpdate();

                    if (rs == 1) {
                        System.out.println("one row deleted from employee table");
                    }
                    return;
                }
            }

            for (Employee ep :
                    employees) {
                if (ep.getEmail().equals(email)) {
                    PreparedStatement pstmt = con.prepareStatement("DELETE FROM employee WHERE email = ?");
                    pstmt.setString(1, email);
                    //Retrieving the data
                    int rs =
                            pstmt.executeUpdate();

                    if (rs == 1) {
                        System.out.println("one row deleted from employee table");
                    }
                    return;
                }
            }

            PreparedStatement pstmt = con.prepareStatement("DELETE FROM customer WHERE email = ?");
            pstmt.setString(1, email);

            //Retrieving the data
            int rs =
                    pstmt.executeUpdate();

            if (rs == 1) {
                System.out.println("one row deleted from customer table");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //create employee
    public static void changeUserPrivilegesQuery(boolean isChangingToAdmin, String email) {
        List<Administrator> administrators = DatabaseQueries.getAdminsQuery();
        List<Employee> employees = DatabaseQueries.getEmployeesQuery();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            for (Administrator ad :
                    administrators) {
                if (ad.getEmail().equals(email)) {
                    PreparedStatement pstmt = con.prepareStatement("UPDATE employee as c SET c.admin = ? WHERE email = ?");
                    pstmt.setBoolean(1, isChangingToAdmin);
                    pstmt.setString(2, email);
                    //Retrieving the data
                    int rs =
                            pstmt.executeUpdate();

                    if (rs == 1) {
                        System.out.println("one row updated in employee table");
                    }
                    return;
                }
            }

            for (Employee ep :
                    employees) {
                if (ep.getEmail().equals(email)) {
                    PreparedStatement pstmt = con.prepareStatement("UPDATE employee as c SET c.admin = ? WHERE email = ?");
                    pstmt.setBoolean(1, isChangingToAdmin);
                    pstmt.setString(2, email);
                    int rs =
                            pstmt.executeUpdate();

                    if (rs == 1) {
                        System.out.println("one row updated in employee table");
                    }
                    return;
                }
            }

            System.out.println("No email found for updating employee to admin or opposite");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //call procedure getIncomePerMonth
    public static List<IncomePerMonth> callGetIncomePerMonthProcedure() {
        CallableStatement cstmt = null;

        List<IncomePerMonth> incomePerMonths = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            cstmt = con.prepareCall("{call getIncomePerMonth()}");

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                IncomePerMonth incomePerMonth = new IncomePerMonth(
                        rs.getString("YEAR_MONTH"),
                        rs.getDouble("INCOME"),
                        rs.getString("TYPE")
                );
                incomePerMonths.add(incomePerMonth);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomePerMonths;
    }

    //change price
    public static void changePriceQuery(String recordType, boolean isSerie, double price) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established...");

            if (recordType.equals("BOTH")) {
                PreparedStatement pstmt = con.prepareStatement("UPDATE prices as pr SET pr.price = ? " +
                                                                    "WHERE pr.record_type = ? AND isSerie = ?");
                pstmt.setDouble(1, price);
                pstmt.setString(2, recordType);
                pstmt.setBoolean(3, isSerie);

                int rs =
                        pstmt.executeUpdate();

                if (rs == 1) {
                    System.out.println("one row updated in price table");
                }
                return;
            }

            PreparedStatement pstmt = con.prepareStatement("UPDATE prices as pr SET pr.price = ? " +
                                                                "WHERE pr.record_type = ?");
            pstmt.setDouble(1, price);
            pstmt.setString(2, recordType);

            int rs =
                    pstmt.executeUpdate();

            if (rs == 1) {
                System.out.println("one row updated in price table");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
