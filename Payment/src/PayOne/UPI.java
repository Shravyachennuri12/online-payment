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

public class UPI extends JFrame {

    private JLabel titleLabel;
    private JLabel UPIIDLabel;
    private JLabel UPIPinLabel;
    private JButton ConfirmButton;
    private JButton backButton;
    private JLabel backgroundLabel;

    public UPI() {
        setTitle("UPI Payment");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        titleLabel = new JLabel("Enter UPI Payment Details");
        titleLabel.setFont(new Font("TAHOMA", Font.BOLD, 22));
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(540, 20, 400, 30);
        add(titleLabel);

        UPIIDLabel = new JLabel("UPI ID:");
        UPIIDLabel.setFont(new Font("TAHOMA", Font.BOLD, 14));
        UPIIDLabel.setBounds(547, 120,70, 23);
        UPIIDLabel.setForeground(Color.white);
        add(UPIIDLabel);

        JTextField UPIIDField = new JTextField();
        UPIIDField.setBounds(615, 120, 150, 20);
        add(UPIIDField);

        UPIPinLabel = new JLabel("UPI PIN:");
        UPIPinLabel.setFont(new Font("TAHOMA", Font.BOLD, 14));
        UPIPinLabel.setBounds(540, 160, 100, 23);
        UPIPinLabel.setForeground(Color.white);
        add(UPIPinLabel);

        JTextField UPIPinField = new JTextField();
        UPIPinField.setBounds(615, 160,50, 20);
        add(UPIPinField);

        ConfirmButton = new JButton("Confirm Payment");
        ConfirmButton.setBounds(615, 230, 140, 25);
        add(ConfirmButton);

        backButton = new JButton("Back");
        backButton.setBounds(635, 275, 100, 25);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String upiID = UPIIDField.getText();
                String upiPIN = UPIPinField.getText();

                boolean isValid = isValidDetailsFromDatabase(upiID, upiPIN);

                if (isValid) {
                    JOptionPane.showMessageDialog(UPI.this, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(UPI.this, "Invalid UPI details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backgroundLabel = new JLabel();
        Image backgroundImage = new ImageIcon("upi.jpeg").getImage();
        ImageIcon resizedImage = new ImageIcon(backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(resizedImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        setVisible(true);
    }

    private boolean isValidDetailsFromDatabase(String upiID, String upiPIN) {
        Connection connection = null;
        boolean isValid = false;

        try {
            String query = "SELECT * FROM upi_details WHERE upi_id = ? AND upi_pin = ?";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "admin123");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, upiID);
            statement.setString(2, upiPIN);

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
        SwingUtilities.invokeLater(() -> new UPI());
    }
}
