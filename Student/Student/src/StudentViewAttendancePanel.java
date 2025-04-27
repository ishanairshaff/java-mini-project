import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentViewAttendancePanel extends JPanel {

    private JTable attendanceTable;
    private DefaultTableModel model;
    private JTextField courseCodeField, courseTypeField;
    private String loggedInUsername;

    public StudentViewAttendancePanel(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
        setLayout(new BorderLayout());

        // Top filter panel (course code and course type)
        JPanel filterPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        courseCodeField = new JTextField();
        courseTypeField = new JTextField();
        JButton searchButton = new JButton("Search");

        filterPanel.setBorder(BorderFactory.createTitledBorder("View Your Attendance"));

        filterPanel.add(new JLabel("Course Code:"));
        filterPanel.add(courseCodeField);
        filterPanel.add(new JLabel("Course Type (L/T/P):"));
        filterPanel.add(courseTypeField);
        filterPanel.add(new JLabel()); // empty
        filterPanel.add(searchButton);

        add(filterPanel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Course Code", "Date", "Hours", "Type", "Attendance Status"};
        model = new DefaultTableModel(columnNames, 0);
        attendanceTable = new JTable(model);
        attendanceTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load all attendance on start
        loadAttendanceData("", "");

        // Button action
        searchButton.addActionListener(e -> {
            String courseCode = courseCodeField.getText().trim();
            String courseType = courseTypeField.getText().trim();
            loadAttendanceData(courseCode, courseType);
        });
    }

    private void loadAttendanceData(String cCode, String cType) {
        model.setRowCount(0); // Clear previous
        List<AttendanceRecord> records = fetchAttendanceDataFromDatabase(loggedInUsername, cCode, cType);
        for (AttendanceRecord record : records) {
            model.addRow(new Object[]{
                    record.getCourseCode(),
                    record.getDate(),
                    record.getHours(),
                    record.getCourseType(),
                    record.getAttendanceStatus()
            });
        }
    }

    private List<AttendanceRecord> fetchAttendanceDataFromDatabase(String username, String cCode, String cType) {
        List<AttendanceRecord> records = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();

        StringBuilder sql = new StringBuilder("SELECT * FROM attendance WHERE username = ?");

        if (!cCode.isEmpty()) sql.append(" AND c_code = ?");
        if (!cType.isEmpty()) sql.append(" AND c_type = ?");

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            stmt.setString(index++, username);
            if (!cCode.isEmpty()) stmt.setString(index++, cCode);
            if (!cType.isEmpty()) stmt.setString(index++, cType);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                records.add(new AttendanceRecord(
                        rs.getString("username"),
                        rs.getString("c_code"),
                        rs.getDate("lec_date"),
                        rs.getInt("c_hours"),
                        rs.getString("c_type"),
                        rs.getString("at_state")
                ));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        return records;
    }

    // Inner class
    static class AttendanceRecord {
        private final String username, courseCode, courseType, attendanceStatus;
        private final Date date;
        private final int hours;

        public AttendanceRecord(String username, String courseCode, Date date, int hours, String courseType, String attendanceStatus) {
            this.username = username;
            this.courseCode = courseCode;
            this.date = date;
            this.hours = hours;
            this.courseType = courseType;
            this.attendanceStatus = attendanceStatus;
        }

        public String getUsername() { return username; }
        public String getCourseCode() { return courseCode; }
        public Date getDate() { return date; }
        public int getHours() { return hours; }
        public String getCourseType() { return courseType; }
        public String getAttendanceStatus() { return attendanceStatus; }
    }
}
