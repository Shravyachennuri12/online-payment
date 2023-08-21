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

public class DebitCard extends JFrame {

    private JLabel titleLabel;
    private JLabel DebitCardBankLabel;
    private JLabel DebitCardNumberLabel;
    private JLabel DebitCardCVVLabel;
    private JLabel DebitCardExpiryDateLabel;
    private JLabel SlashSymbolLabel;
    private JTextField DebitCardBankField;
    private JTextField DebitCardNumberField1;
    private JTextField DebitCardNumberField2;
    private JTextField DebitCardNumberField3;
    private JTextField DebitCardNumberField4;
    private JTextField DebitCardCVVField;
    private JTextField DebitCardExpiryDate1Field;
    private JTextField DebitCardExpiryDate2Field;
    private JButton ConfirmButton;
    private JButton backButton;
    private JLabel backgroundLabel;

    public DebitCard() {
        setTitle("Debit Card");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        titleLabel = new JLabel("Enter Debit Card Details");
        titleLabel.setFont(new Font("TAHOMA", Font.BOLD, 22));
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(560, 20, 400, 30);
        add(titleLabel);

        DebitCardBankLabel = new JLabel("Debit Card Bank Name:");
        DebitCardBankLabel.setBounds(480, 70, 220, 23);
        DebitCardBankLabel.setForeground(Color.white);
        add(DebitCardBankLabel);

        DebitCardBankField = new JTextField();
        DebitCardBankField.setBounds(625, 70, 220, 22);
        add(DebitCardBankField);

        DebitCardNumberLabel = new JLabel("Debit Card Number:");
        DebitCardNumberLabel.setBounds(490, 120, 220, 23);
        DebitCardNumberLabel.setForeground(Color.white);
        add(DebitCardNumberLabel);

        DebitCardNumberField1 = new JTextField();
        DebitCardNumberField1.setBounds(625, 120, 40, 22);
        add(DebitCardNumberField1);

        DebitCardNumberField2 = new JTextField();
        DebitCardNumberField2.setBounds(670, 120, 40, 22);
        add(DebitCardNumberField2);

        DebitCardNumberField3 = new JTextField();
        DebitCardNumberField3.setBounds(715, 120, 40, 22);
        add(DebitCardNumberField3);

        DebitCardNumberField4 = new JTextField();
        DebitCardNumberField4.setBounds(760, 120, 40, 22);
        add(DebitCardNumberField4);

        DebitCardCVVLabel = new JLabel("Debit Card CVV:");
        DebitCardCVVLabel.setBounds(500, 170, 220, 20);
        DebitCardCVVLabel.setForeground(Color.white);
        add(DebitCardCVVLabel);

        DebitCardCVVField = new JTextField();
        DebitCardCVVField.setBounds(625, 170, 30, 22);
        add(DebitCardCVVField);

        DebitCardExpiryDateLabel = new JLabel("Expiry Date(MM/YY):");
        DebitCardExpiryDateLabel.setBounds(480, 220, 220, 20);
        DebitCardExpiryDateLabel.setForeground(Color.white);
        add(DebitCardExpiryDateLabel);

        SlashSymbolLabel = new JLabel("/");
        SlashSymbolLabel.setBounds(656, 220, 200, 20);
        Font slashFont = new Font("Tahoma", Font.BOLD, 15);
        SlashSymbolLabel.setFont(slashFont);
        SlashSymbolLabel.setForeground(Color.black);
        add(SlashSymbolLabel);

        DebitCardExpiryDate1Field = new JTextField();
        DebitCardExpiryDate1Field.setBounds(625, 220, 30, 22);
        add(DebitCardExpiryDate1Field);

        DebitCardExpiryDate2Field = new JTextField();
        DebitCardExpiryDate2Field.setBounds(665, 220, 30, 22);
        add(DebitCardExpiryDate2Field);

        ConfirmButton = new JButton("Confirm Payment");
        ConfirmButton.setBounds(615, 280, 140, 25);
        add(ConfirmButton);

        backButton = new JButton("Back");
        backButton.setBounds(635, 325, 100, 25);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bankName = DebitCardBankField.getText();
                String cardNumber1 = DebitCardNumberField1.getText();
                String cardNumber2 = DebitCardNumberField2.getText();
                String cardNumber3 = DebitCardNumberField3.getText();
                String cardNumber4 = DebitCardNumberField4.getText();
                String cvv = DebitCardCVVField.getText();
                String expiryMonth = DebitCardExpiryDate1Field.getText();
                String expiryYear = DebitCardExpiryDate2Field.getText();
                String expiryDate = expiryMonth + "/" + expiryYear;

                String combinedCardNumber = cardNumber1 + cardNumber2 + cardNumber3 + cardNumber4;

                boolean isValid = isValidDetailsFromDatabase(bankName, combinedCardNumber, cvv, expiryDate);

                if (isValid) {
                    JOptionPane.showMessageDialog(DebitCard.this, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(DebitCard.this, "Invalid debit card details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backgroundLabel = new JLabel();
        Image backgroundImage = new ImageIcon("debit card.jpg").getImage(); // Update image path
        ImageIcon resizedImage = new ImageIcon(backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(resizedImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        setVisible(true);
    }

    private boolean isValidDetailsFromDatabase(String bankName, String cardNumber, String cvv, String expiryDate) {
        Connection connection = null;
        boolean isValid = false;

        try {
            String query = "SELECT * FROM debit_cards WHERE debit_bank_name = ? AND debit_card_number = ? AND debit_card_cvv = ? AND debit_card_expiry_date = ?";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "admin123");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bankName);
            statement.setString(2, cardNumber);
            statement.setString(3, cvv);
            statement.setString(4, expiryDate);

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
        SwingUtilities.invokeLater(() -> new DebitCard());
    }
}
