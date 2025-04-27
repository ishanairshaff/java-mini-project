import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAttendancePanel extends JPanel {

    public AddAttendancePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Field
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        JTextField usernameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        // Course Code Field
        JLabel courseCodeLabel = new JLabel("Course Code:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(courseCodeLabel, gbc);

        JTextField courseCodeField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(courseCodeField, gbc);

        // Course Hours Field
        JLabel courseHoursLabel = new JLabel("Course Hours:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(courseHoursLabel, gbc);

        JTextField courseHoursField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(courseHoursField, gbc);

        // Date Field
        JLabel dateLabel = new JLabel("Lecture Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(dateLabel, gbc);

        JTextField dateField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(dateField, gbc);

        // Attendance State
        JLabel attendanceStateLabel = new JLabel("Attendance State:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(attendanceStateLabel, gbc);

        String[] statuses = {"Present", "Absent"};
        JComboBox<String> statusDropdown = new JComboBox<>(statuses);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(statusDropdown, gbc);

        // Course Type
        JLabel courseTypeLabel = new JLabel("Course Type (L/T/P):");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(courseTypeLabel, gbc);

        JTextField courseTypeField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(courseTypeField, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        // Action Listener
        submitButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String cCode = courseCodeField.getText().trim();
            String hoursStr = courseHoursField.getText().trim();
            String dateStr = dateField.getText().trim();
            String atState = (String) statusDropdown.getSelectedItem();
            String cType = courseTypeField.getText().trim();

            if (username.isEmpty() || cCode.isEmpty() || hoursStr.isEmpty() || dateStr.isEmpty() || cType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            try {
                int cHours = Integer.parseInt(hoursStr);
                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

                saveAttendance(username, cCode, cHours, sqlDate, atState, cType);
                JOptionPane.showMessageDialog(this, "Attendance recorded.");

                usernameField.setText("");
                courseCodeField.setText("");
                courseHoursField.setText("");
                dateField.setText("");
                courseTypeField.setText("");
                statusDropdown.setSelectedIndex(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }

    private void saveAttendance(String username, String cCode, int cHours, java.sql.Date lecDate, String atState, String cType) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();

        String sql = "INSERT INTO attendance (username, c_code, c_hours, lec_date, at_state, c_type) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, cCode);
            stmt.setInt(3, cHours);
            stmt.setDate(4, lecDate);
            stmt.setString(5, atState);
            stmt.setString(6, cType.toUpperCase());

            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
            throw e;
        } finally {
            conn.close();
        }
    }
}
