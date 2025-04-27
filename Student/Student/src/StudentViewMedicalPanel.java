import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentViewMedicalPanel extends JPanel {

    private JTable medicalTable;
    private DefaultTableModel model;
    private JTextField courseCodeField;
    private String loggedInUsername;

    public StudentViewMedicalPanel(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
        setLayout(new BorderLayout());

        // Filter Panel
        JPanel filterPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        courseCodeField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 102, 204)); // Blue
        searchButton.setForeground(Color.WHITE);            // White text

        filterPanel.setBorder(BorderFactory.createTitledBorder("View Your Medical Records"));

        filterPanel.add(new JLabel("Course Code:"));
        filterPanel.add(courseCodeField);
        filterPanel.add(new JLabel());  // empty
        filterPanel.add(searchButton);

        add(filterPanel, BorderLayout.NORTH);

        // Table
        String[] columnNames = {
                "Medical ID", "Course Code", "Course Type", "Description",
                "Lecture Date", "State", "Cut Lecture Hours"
        };
        model = new DefaultTableModel(columnNames, 0);
        medicalTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(medicalTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load all medicals at start
        loadMedicalData("");

        // Search Button Action
        searchButton.addActionListener(e -> {
            String courseCode = courseCodeField.getText().trim();
            loadMedicalData(courseCode);
        });
    }

    private void loadMedicalData(String courseCode) {
        model.setRowCount(0);  // Clear previous data
        List<MedicalRecord> records = fetchMedicalRecords(loggedInUsername, courseCode);
        for (MedicalRecord record : records) {
            model.addRow(new Object[] {
                    record.getMedicalId(),
                    record.getCourseCode(),
                    record.getCourseType(),
                    record.getDescription(),
                    record.getSubDate(),
                    record.getState(),
                    record.getCutLectureHours()
            });
        }
    }

    private List<MedicalRecord> fetchMedicalRecords(String username, String courseCode) {
        List<MedicalRecord> list = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/faculty_management";
        String user = "root";
        String password = "1427";

        StringBuilder sql = new StringBuilder(
                "SELECT m.* FROM Medical m " +
                        "JOIN students s ON m.user_id = s.student_id " +
                        "JOIN allusers a ON s.user_id = a.user_id " +
                        "WHERE a.username = ?"
        );

        if (!courseCode.isEmpty()) sql.append(" AND m.c_code = ?");

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            stmt.setString(index++, username);
            if (!courseCode.isEmpty()) {
                stmt.setString(index++, courseCode);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String medicalId = rs.getString("Medical_id");
                    String c_code = rs.getString("c_code");
                    String c_type = rs.getString("c_type");
                    String description = rs.getString("Description");
                    String subDate = rs.getDate("Sub_date").toString();
                    String state = rs.getString("State");
                    int cutLectureHours = rs.getInt("cut_lec_hour");

                    list.add(new MedicalRecord(medicalId, c_code, c_type, description, subDate, state, cutLectureHours));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    // Inner class to hold medical record
    static class MedicalRecord {
        private final String medicalId, courseCode, courseType, description, subDate, state;
        private final int cutLectureHours;

        public MedicalRecord(String medicalId, String courseCode, String courseType, String description, String subDate, String state, int cutLectureHours) {
            this.medicalId = medicalId;
            this.courseCode = courseCode;
            this.courseType = courseType;
            this.description = description;
            this.subDate = subDate;
            this.state = state;
            this.cutLectureHours = cutLectureHours;
        }

        public String getMedicalId() { return medicalId; }
        public String getCourseCode() { return courseCode; }
        public String getCourseType() { return courseType; }
        public String getDescription() { return description; }
        public String getSubDate() { return subDate; }
        public String getState() { return state; }
        public int getCutLectureHours() { return cutLectureHours; }
    }
}
