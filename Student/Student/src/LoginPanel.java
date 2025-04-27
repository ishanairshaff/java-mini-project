import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginPanel extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {
        setTitle("TECLMS Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        panel.add(usernameField);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        panel.add(passwordField);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            try {
                authenticate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private void authenticate() throws SQLException, ClassNotFoundException {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT user_id, password, user_type, first_name FROM allusers WHERE username = ?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                String userType = rs.getString("user_type");
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");

                if (password.equals(dbPassword)) {
                    JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose(); // Close login window

                    if ("student".equals(userType)) {
                        new UORStudentUI(userId, firstName, username);
                    } else {
                        JOptionPane.showMessageDialog(this, "Unknown user type.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPanel());
    }
}
