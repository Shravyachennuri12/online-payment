package PayOne;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PayUserLogin extends JFrame {
    private JLabel titleLabel;
    private JLabel phoneLabel;
    private JLabel passwordLabel;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JButton loginButton;
//    private JButton exitButton;
    private JCheckBox showPasswordCheckBox;
    private JButton backButton;
    private  JLabel backgroundLabel;

    private boolean isLoggedIn = false;

    public PayUserLogin() {
        setTitle("User Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        titleLabel = new JLabel("User Login");
        titleLabel.setFont(new Font("TAHOMA", Font.BOLD, 30));
        titleLabel.setBounds(215, 90, 200, 40);
        titleLabel.setForeground(Color.white);
        add(titleLabel);

        phoneLabel = new JLabel("Number:");
        phoneLabel.setBounds(150, 180, 220, 22);
        phoneLabel.setFont(new Font("TAHOMA", Font.BOLD, 16));
        phoneLabel.setForeground(Color.white);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(240, 180, 180, 22);
        add(phoneField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(140, 240, 220, 22);
        passwordLabel.setFont(new Font("TAHOMA", Font.BOLD, 16));
        passwordLabel.setForeground(Color.white);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(240, 240, 180, 22);
        add(passwordField);
        
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(260, 285, 120, 20);
        add(showPasswordCheckBox);

        loginButton = new JButton("Login");
        loginButton.setBounds(280, 330, 80, 25);
        add(loginButton);

        backButton = new JButton("Back");
        backButton.setBounds(280, 380, 80, 25);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        showPasswordCheckBox.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u2022');
                }
            }
        });
        backgroundLabel = new JLabel();
        Image backgroundImage = new ImageIcon("login.jpg").getImage();
        ImageIcon resizedImage = new ImageIcon(backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(resizedImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);


        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = phoneField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "admin123");
                    String sql = "SELECT * FROM users WHERE phone = ? AND pswd = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, enteredUsername);
                    preparedStatement.setString(2, enteredPassword);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        isLoggedIn = true;
                        JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                        // Create and display the products page
                        if (isLoggedIn) {
                            SwingUtilities.invokeLater(() -> new Products());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PayUserLogin());
    }
}
