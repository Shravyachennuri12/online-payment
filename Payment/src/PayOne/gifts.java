package PayOne;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class gifts extends JFrame {

    private JLabel titleLabel;
    private JLabel GiftCardCodeLabel;
    private JTextField GiftCardCodeField;
    private JButton ConfirmButton;
    private JButton backButton;
    private JLabel backgroundLabel;

    public gifts() {
        setTitle("Gift Card");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        titleLabel = new JLabel("Enter Gift Card Code");
        titleLabel.setFont(new Font("TAHOMA", Font.BOLD, 22));
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(560, 20, 400, 30);
        add(titleLabel);

        GiftCardCodeLabel = new JLabel("Gift Card Code:");
        GiftCardCodeLabel.setBounds(520, 100, 220, 23);
        GiftCardCodeLabel.setForeground(Color.white);
        add(GiftCardCodeLabel);

        GiftCardCodeField = new JTextField();
        GiftCardCodeField.setBounds(625, 100, 200, 22);
        add(GiftCardCodeField);

        ConfirmButton = new JButton("Validate Gift Card");
        ConfirmButton.setBounds(615, 160, 140, 25);
        add(ConfirmButton);

        backButton = new JButton("Back");
        backButton.setBounds(635, 205, 100, 25);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String giftCardCode = GiftCardCodeField.getText();

                boolean isValid = isValidGiftCardFromDatabase(giftCardCode);

                if (isValid) {
                    JOptionPane.showMessageDialog(gifts.this, "Gift card valid!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(gifts.this, "Invalid gift card code.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backgroundLabel = new JLabel();
        Image backgroundImage = new ImageIcon("gifts.jpg").getImage();
        ImageIcon resizedImage = new ImageIcon(backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(resizedImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        setVisible(true);
    }

    private boolean isValidGiftCardFromDatabase(String giftCardCode) {
        Connection connection = null;
        boolean isValid = false;

        try {
            String query = "SELECT * FROM gifts WHERE gift_card_code = ?";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "admin123");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, giftCardCode);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isValid;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new gifts());
    }
}
