package PayOne;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayUserRegister extends JFrame {
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel createPasswordLabel;
    private JLabel confirmPasswordLabel;
    private JTextField nameField;
    private JTextField phoneField;
    private JPasswordField createPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton exitButton; 

    public PayUserRegister() {
        setTitle("User Register");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);  // Using null layout
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        titleLabel = new JLabel("User Register");
        titleLabel.setFont(new Font("TAHOMA", Font.BOLD, 24));
        titleLabel.setBounds(140, 20, 200, 30);
        add(titleLabel);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 70, 120, 20);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 70, 200, 20);
        add(nameField);

        phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(20, 110, 120, 20);
        add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(150, 110, 200, 20);
        add(phoneField);

        createPasswordLabel = new JLabel("Create Password:");
        createPasswordLabel.setBounds(20, 150, 120, 20);
        add(createPasswordLabel);
        createPasswordField = new JPasswordField();
        createPasswordField.setBounds(150, 150, 200, 20);
        add(createPasswordField);

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(20, 190, 120, 20);
        add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 190, 200, 20);
        add(confirmPasswordField);
       
        registerButton = new JButton("Register");
        registerButton.setBounds(150, 240, 90, 25);
        add(registerButton);
        
        exitButton = new JButton("Exit"); 
        exitButton.setBounds(270, 240, 90, 25); 
        add(exitButton);
        
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose(); 
            }
        });

        setVisible(true);
    }
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new PayUserRegister());
    }
}
