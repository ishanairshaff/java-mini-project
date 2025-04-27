import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.sql.*;

public class StudentProfilePanel extends JPanel {
    private JTextField studentIDField, nameField, emailField, phoneField, userTypeField;
    private JTextField registrationNumberField, batchField, degreeProgramField;
    private JLabel photoLabel;
    private JButton editButton, uploadButton, deleteButton;
    private int studentId;
    private byte[] profilePictureData;

    public StudentProfilePanel(int studentId) {
        this.studentId = studentId;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        studentIDField = createField(formPanel, "Student ID:", false);
        nameField = createField(formPanel, "Name:", false);
        emailField = createField(formPanel, "Email:", false);
        phoneField = createField(formPanel, "Phone:", false);
        userTypeField = createField(formPanel, "User Type:", false);

        registrationNumberField = createField(formPanel, "Registration Number:", false);
        batchField = createField(formPanel, "Batch:", false);
        degreeProgramField = createField(formPanel, "Degree Program:", false);

        photoLabel = new JLabel("Photo", SwingConstants.CENTER);
        photoLabel.setPreferredSize(new Dimension(150, 180));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        photoLabel.setBorder(border);
        formPanel.add(new JLabel("Profile Picture:"));
        formPanel.add(photoLabel);

        add(formPanel, BorderLayout.CENTER);

        editButton = new JButton("Edit Profile");
        editButton.addActionListener(e -> toggleEditSave());

        uploadButton = new JButton("Upload Photo");
        uploadButton.addActionListener(e -> choosePhoto());
        uploadButton.setEnabled(false);

        deleteButton = new JButton("Delete Photo");
        deleteButton.addActionListener(e -> deletePhoto());
        deleteButton.setEnabled(false);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(uploadButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(editButton);

        add(bottomPanel, BorderLayout.SOUTH);

        loadProfileData();
    }

    private JTextField createField(JPanel panel, String label, boolean editable) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        field.setEditable(editable);
        panel.add(field);
        return field;
    }

    private void toggleEditSave() {
        boolean editing = nameField.isEditable();
        if (editing) {
            saveProfileData();
            setEditable(false);
            editButton.setText("Edit Profile");
            uploadButton.setEnabled(false);
            deleteButton.setEnabled(false);
        } else {
            setEditable(true);
            editButton.setText("Save Profile");
            uploadButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
    }

    private void setEditable(boolean editable) {
        emailField.setEditable(editable);
        phoneField.setEditable(editable);
        batchField.setEditable(editable);
        degreeProgramField.setEditable(editable);
        // username, name, reg number, user type remain non-editable
    }

    private void loadProfileData() {
        String query = """
            SELECT u.user_id, CONCAT(u.first_name, ' ', u.last_name) AS full_name,
                   u.email, u.phone, u.user_type, u.profile_picture,
                   s.registration_number, s.batch, s.degree_program
            FROM allusers u
            JOIN students s ON u.user_id = s.student_id
            WHERE u.user_id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                studentIDField.setText(String.valueOf(rs.getInt("user_id")));
                nameField.setText(rs.getString("full_name"));
                emailField.setText(rs.getString("email"));
                phoneField.setText(rs.getString("phone"));
                userTypeField.setText(rs.getString("user_type"));
                registrationNumberField.setText(rs.getString("registration_number"));
                batchField.setText(rs.getString("batch"));
                degreeProgramField.setText(rs.getString("degree_program"));

                profilePictureData = rs.getBytes("profile_picture");

                if (profilePictureData != null) {
                    displayImage(profilePictureData);
                } else {
                    loadDefaultPhoto();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading profile: " + e.getMessage());
        }
    }

    private void displayImage(byte[] imageData) {
        ImageIcon icon = new ImageIcon(imageData);
        Image img = icon.getImage().getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(), Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(img));
        photoLabel.setText("");
    }

    private void loadDefaultPhoto() {
        try {
            File defaultFile = new File("C:\\Users\\prema\\Desktop\\photo.jpg");
            if (defaultFile.exists()) {
                profilePictureData = Files.readAllBytes(defaultFile.toPath());
                displayImage(profilePictureData);
            } else {
                photoLabel.setIcon(null);
                photoLabel.setText("No Photo");
            }
        } catch (Exception e) {
            photoLabel.setIcon(null);
            photoLabel.setText("No Photo");
        }
    }

    private void choosePhoto() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                profilePictureData = Files.readAllBytes(file.toPath());
                displayImage(profilePictureData);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage());
            }
        }
    }

    private void saveProfileData() {
        String updateAllUsers = "UPDATE allusers SET email = ?, phone = ?, profile_picture = ? WHERE user_id = ?";
        String updateStudent = "UPDATE students SET batch = ?, degree_program = ? WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement stmtAllUsers = conn.prepareStatement(updateAllUsers);
                 PreparedStatement stmtStudent = conn.prepareStatement(updateStudent)) {

                // Update allusers table
                stmtAllUsers.setString(1, emailField.getText());
                stmtAllUsers.setString(2, phoneField.getText());
                if (profilePictureData != null) {
                    stmtAllUsers.setBytes(3, profilePictureData);
                } else {
                    stmtAllUsers.setNull(3, java.sql.Types.BLOB);
                }
                stmtAllUsers.setInt(4, Integer.parseInt(studentIDField.getText()));
                stmtAllUsers.executeUpdate();

                // Update students table
                stmtStudent.setString(1, batchField.getText());
                stmtStudent.setString(2, degreeProgramField.getText());
                stmtStudent.setInt(3, Integer.parseInt(studentIDField.getText()));
                stmtStudent.executeUpdate();

                conn.commit(); // Commit transaction
                JOptionPane.showMessageDialog(this, "Profile updated successfully!");
            } catch (SQLException ex) {
                conn.rollback(); // Rollback on error
                JOptionPane.showMessageDialog(this, "Error updating profile: " + ex.getMessage());
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage());
        }
    }

    private void deletePhoto() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the photo?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("UPDATE allusers SET profile_picture = NULL WHERE user_id = ?")) {

                stmt.setInt(1, studentId);
                int result = stmt.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Profile photo deleted successfully!");
                    profilePictureData = null;
                    photoLabel.setIcon(null);
                    photoLabel.setText("No Photo");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete photo.");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting photo: " + e.getMessage());
            }
        }
    }
}
