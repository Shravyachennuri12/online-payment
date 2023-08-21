package PayOne;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayHome extends JFrame {
    private JLabel headingLabel;
    private JButton userLoginButton, userRegisterButton, exitButton;
    private JLabel backgroundLabel;

    public PayHome() {
        setTitle("Shravya ecommerce");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 
        setResizable(false);

        headingLabel = new JLabel("WELCOME TO ONLINE SHOPPING");
        headingLabel.setFont(new Font("TAHOMA", Font.BOLD, 30));
        headingLabel.setBounds(400, 10, 750, 50);
        headingLabel.setForeground(Color.BLACK);
        add(headingLabel);

        backgroundLabel = new JLabel(new ImageIcon("Shop Home.jpg"));
        backgroundLabel.setBounds(0, 0, 1365, 702);
        add(backgroundLabel);

        userLoginButton = new JButton("User Login");
        userLoginButton.setBounds(600, 260, 200, 30);
        backgroundLabel.add(userLoginButton);

        userRegisterButton = new JButton("User Register");
        userRegisterButton.setBounds(600, 300, 200, 30);
//        backgroundLabel.add(userRegisterButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(600, 340, 200, 30);
        backgroundLabel.add(exitButton);

        ActionListener buttonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                if (e.getSource() == exitButton) 
                {
                    System.exit(0);
                } else if (e.getSource() == userLoginButton)
                {
                    new PayUserLogin();
                } else if (e.getSource() == userRegisterButton)
                {                
                    new PayUserRegister();
                }

            }
        };

        userLoginButton.addActionListener(buttonListener);
        userRegisterButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PayHome());
    }
}
