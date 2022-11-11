import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class IncomePerMonthUI {

    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();


    public void incomeUI(List<IncomePerMonth> incomePerMonths) {
        DefaultTableModel incomeFromRentalTableModel = new DefaultTableModel();
        JTable incomeFromRentalTable = new JTable(incomeFromRentalTableModel);

        incomeFromRentalTable.setRowHeight(30);

        incomeFromRentalTable.setEnabled(false);
        incomeFromRentalTableModel.addColumn("YEAR AND MONTH");
        incomeFromRentalTableModel.addColumn("INCOME");
        incomeFromRentalTableModel.addColumn("TYPE");

        for (int i = 0; i < incomePerMonths.size(); i++) {
            incomeFromRentalTableModel.insertRow(i, new Object[]{
                    incomePerMonths.get(i).getYearAndMonth(),
                    incomePerMonths.get(i).getIncome(),
                    incomePerMonths.get(i).getType()}
            );
        }

        if (incomePerMonths.size() == 0) {
            JLabel label = new JLabel("NO RECORDS FOUND!");
            label.setSize(label.getPreferredSize());
            incomeFromRentalTable.add(label);
            label.setForeground(Color.red);
            incomeFromRentalTable.setFillsViewportHeight(true);
        }

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 500;
        int height = 500;

        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);

        frame.setTitle("INCOME PER MONTH");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(x, y);

        frame.add(panel);
        panel.setLayout(null);

        JScrollPane movieRentalTableScrollPane = new JScrollPane(incomeFromRentalTable);
        movieRentalTableScrollPane.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "INCOME PER MONTH",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));


        frame.add(movieRentalTableScrollPane, BorderLayout.CENTER);
        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
