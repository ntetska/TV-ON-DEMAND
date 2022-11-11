import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PriceUI extends JFrame implements ActionListener, ItemListener {
    private String recordType;
    private boolean isSerie;

    //text input
    private final JTextField onActionText = new JTextField(10);

    //buttons
    private final JButton changePrice = new JButton("CHANGE");
    private final JCheckBox moviesCheckBox = new JCheckBox("For Movies");
    private final JCheckBox seriesCheckBox = new JCheckBox("For Series");
    private final JCheckBox bothCheckBox = new JCheckBox("For Movies And Series");
    private final JCheckBox isSerieCheckBox = new JCheckBox("Serie");
    private final JCheckBox isMovieCheckBox = new JCheckBox("Movie");

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    private final JLabel priceLabel = new JLabel("Price(DECIMAL):");
    private final JTextField priceText = new JTextField(20);

    public void setPriceUI() {
        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //size of frame
        int width = 350;
        int height = 350;

        //Calculate the frame location
        int x = ((screenSize.width - width) / 2);
        int y = ((screenSize.height - height) / 2);

        frame.setTitle("Change price");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocation(x, y);

        frame.add(panel);
        panel.setLayout(null);

        //onAction text
        onActionText.setBounds(25, 10, 250, 20);
        onActionText.setEditable(false);
        onActionText.setVisible(false);
        panel.add(onActionText);
        onActionText.setBorder(BorderFactory.createEmptyBorder());

        //recordType label
        JLabel recordTypeLabel = new JLabel("Record type:");
        recordTypeLabel.setBounds(25, 40, 75, 20);
        panel.add(recordTypeLabel);

        //======================================================================================//

        //change record checkboxes
        //movies checkbox
        moviesCheckBox.setBounds(100, 40, 100, 20);
        panel.add(moviesCheckBox);
        moviesCheckBox.addItemListener(this);

        //series checkbox
        seriesCheckBox.setBounds(100, 70, 100, 20);
        panel.add(seriesCheckBox);
        seriesCheckBox.addItemListener(this);

        //both checkbox
        bothCheckBox.setBounds(100, 100, 180, 20);
        panel.add(bothCheckBox);
        bothCheckBox.addItemListener(this);

        //both is Serie checkbox
        isSerieCheckBox.setBounds(100, 130, 55, 20);
        panel.add(isSerieCheckBox);
        isSerieCheckBox.addItemListener(this);

        //both is Movie checkbox
        isMovieCheckBox.setBounds(175, 130, 65, 20);
        panel.add(isMovieCheckBox);
        isMovieCheckBox.addItemListener(this);

        isSerieCheckBox.setVisible(false);
        isMovieCheckBox.setVisible(false);

        //price label
        priceLabel.setBounds(25, 175, 100, 20);
        panel.add(priceLabel);

        //price text
        priceText.setBounds(123, 175, 65, 20);
        priceText.setBorder(BorderFactory.createEmptyBorder());
        panel.add(priceText);


        //change price button
        changePrice.setBounds(130, 230, 85, 25);
        changePrice.setFocusPainted(false);
        panel.add(changePrice);
        changePrice.addActionListener(this);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changePrice){
            if(
                    (
                            moviesCheckBox.isSelected() ||
                             seriesCheckBox.isSelected() ||
                                bothCheckBox.isSelected()
                    ) &&
                            !priceText.getText().isEmpty()
            )
            {
                if(bothCheckBox.isSelected() &&
                        (
                                !isMovieCheckBox.isSelected() &&
                                    !isSerieCheckBox.isSelected()
                        )
                  )
                    {
                    onActionText.setText("ALL FIELDS ARE REQUIRED");
                    onActionText.setForeground(Color.RED);
                    onActionText.setVisible(true);
                    return;
                    }
                DatabaseQueries.changePriceQuery(this.recordType,this.isSerie,Double.parseDouble(priceText.getText()));
                onActionText.setText("PRICE TABLE UPDATED SUCCESSFULLY");
                onActionText.setForeground(Color.GREEN);
            }else {
                onActionText.setText("ALL FIELDS ARE REQUIRED");
                onActionText.setForeground(Color.RED);
            }
            onActionText.setVisible(true);

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == isMovieCheckBox) {
            isSerieCheckBox.setEnabled(!isSerieCheckBox.isEnabled());
            this.isSerie = false;
        }

        if (e.getSource() == isSerieCheckBox) {
            isMovieCheckBox.setEnabled(!isMovieCheckBox.isEnabled());
            this.isSerie = true;
        }

        if (e.getSource() == moviesCheckBox) {
            if (seriesCheckBox.isEnabled() && bothCheckBox.isEnabled()) {
                seriesCheckBox.setEnabled(false);
                bothCheckBox.setEnabled(false);
            } else {
                seriesCheckBox.setEnabled(true);
                bothCheckBox.setEnabled(true);
            }
        }

        if (e.getSource() == seriesCheckBox) {
            if (bothCheckBox.isEnabled() && moviesCheckBox.isEnabled()) {
                bothCheckBox.setEnabled(false);
                moviesCheckBox.setEnabled(false);
            } else {
                bothCheckBox.setEnabled(true);
                moviesCheckBox.setEnabled(true);

            }
        }

        if (e.getSource() == bothCheckBox) {
            if (moviesCheckBox.isEnabled() && seriesCheckBox.isEnabled()) {
                moviesCheckBox.setEnabled(false);
                seriesCheckBox.setEnabled(false);
            } else {
                moviesCheckBox.setEnabled(true);
                seriesCheckBox.setEnabled(true);
            }
        }

        if (moviesCheckBox.isSelected()) {
            this.recordType = "MOVIES";

        }

        if (seriesCheckBox.isSelected()) {
            this.recordType = "SERIES";

        }

        if (bothCheckBox.isSelected()) {
            this.recordType = "BOTH";
            isSerieCheckBox.setVisible(true);
            isMovieCheckBox.setVisible(true);

        }else {
            isSerieCheckBox.setVisible(false);
            isMovieCheckBox.setVisible(false);
        }
    }
}
