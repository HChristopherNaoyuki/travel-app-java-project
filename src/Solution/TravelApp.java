package Solution;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TravelApp
{
    // Declare GUI components
    private final JFrame frame; // Main application window
    private final JComboBox<String> comboStartLocation; // Dropdown for start locations
    private final JComboBox<String> comboEndLocation; // Dropdown for end locations
    private final JComboBox<String> comboTravelBy; // Dropdown for travel methods
    private final JTextArea textAreaTravelLog; // Area to display travel log
    private final JButton btnSubmit; // Button to submit travel information
    private final File file = new File("travel.txt"); // File to store travel data


    // Constructor to set up the GUI
    public TravelApp()
    {
        frame = new JFrame("Travel Log"); // Creating main frame with title
        frame.setSize(450, 400); // Setting size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exiting on close
        frame.setLayout(null); // Using absolute positioning
        frame.setBackground(Color.WHITE); // Setting background color

        // Define a font for a modern look
        Font mainFont = new Font("Helvetica Neue", Font.PLAIN, 15);

        // Creating and adding labels and combo boxes for user input
        JLabel lblStartLocation = new JLabel("Start Location:");
        lblStartLocation.setBounds(20, 20, 100, 25); // Positioning
        lblStartLocation.setFont(mainFont); // Setting font
        frame.add(lblStartLocation); // Adding label to frame

        // Combo box for start locations
        comboStartLocation = new JComboBox<>(new String[]{"Cape Town", "Durban", "Port Elizabeth"});
        comboStartLocation.setBounds(140, 20, 200, 25); // Positioning
        comboStartLocation.setFont(mainFont); // Setting font
        frame.add(comboStartLocation); // Adding to frame

        // Similar setup for end location label and combo box
        JLabel lblEndLocation = new JLabel("End Location:");
        lblEndLocation.setBounds(20, 60, 100, 25);
        lblEndLocation.setFont(mainFont);
        frame.add(lblEndLocation);

        comboEndLocation = new JComboBox<>(new String[]{"Cape Town", "Durban", "Port Elizabeth"});
        comboEndLocation.setBounds(140, 60, 200, 25);
        comboEndLocation.setFont(mainFont);
        frame.add(comboEndLocation);

        // Similar setup for travel method label and combo box
        JLabel lblTravelBy = new JLabel("Travel By:");
        lblTravelBy.setBounds(20, 100, 100, 25);
        lblTravelBy.setFont(mainFont);
        frame.add(lblTravelBy);

        comboTravelBy = new JComboBox<>(new String[]{"Airplane", "Train"});
        comboTravelBy.setBounds(140, 100, 200, 25);
        comboTravelBy.setFont(mainFont);
        frame.add(comboTravelBy);

        // Setting up the submit button with styling
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(140, 140, 100, 25);
        btnSubmit.setBackground(new Color(75, 180, 245)); // Light blue background
        btnSubmit.setForeground(Color.WHITE); // White text
        btnSubmit.setBorderPainted(false); // No border
        btnSubmit.setFocusPainted(false); // No focus border
        btnSubmit.setFont(mainFont); // Setting font
        frame.add(btnSubmit); // Adding button to frame

        // Text area for displaying travel logs
        textAreaTravelLog = new JTextArea();
        textAreaTravelLog.setEditable(false); // Not editable by user
        textAreaTravelLog.setText("TRAVEL LOG\n****************************\n"); // Initial text
        textAreaTravelLog.setFont(mainFont); // Setting font
        textAreaTravelLog.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding
        textAreaTravelLog.setBackground(Color.WHITE); // Background color

        // Adding text area to a scroll pane for scrolling
        JScrollPane scrollPane = new JScrollPane(textAreaTravelLog);
        scrollPane.setBounds(20, 180, 390, 150); // Positioning
        frame.add(scrollPane); // Adding scroll pane to frame

        // Adding action listener for the submit button
        btnSubmit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                handleSubmission(); // Call submission handler on click
            }
        });

        // Load existing travel information into the text area
        loadTravelData(); 

        // Make the frame visible
        frame.setVisible(true);
    }


    // Method to handle submission of travel information
    private void handleSubmission()
    {
        String startLocation = comboStartLocation.getSelectedItem().toString(); // Get selected start location
        String endLocation = comboEndLocation.getSelectedItem().toString(); // Get selected end location
        String travelBy = comboTravelBy.getSelectedItem().toString(); // Get selected travel method

        // Check if start and end locations are the same
        if (startLocation.equals(endLocation))
        {
            JOptionPane.showMessageDialog(frame, "Destinations can not be the same!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit method if the same
        }

        // Create travel information string
        String travelInfo = "Destination 1: " + startLocation + "\n" +
                            "Destination 2: " + endLocation + "\n" +
                            "Travel By: " + travelBy + "\n****************************\n";

        // Save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)))
        {
            writer.write(travelInfo); // Write info to file
            writer.newLine(); // New line in file
        }
        catch (IOException ex) // Handle exceptions
        {
            JOptionPane.showMessageDialog(frame, "Error saving data!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit method on error
        }

        // Add the new travel info to the text area
        textAreaTravelLog.append(travelInfo);
    }


    // Method to load existing travel data from the file
    private void loadTravelData()
    {
        if (!file.exists()) // Check if the file exists
        {
            return; // Exit if not
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            StringBuilder travelLog = new StringBuilder("TRAVEL LOG\n****************************\n"); // Header for logs
            
            while ((line = reader.readLine()) != null) // Read lines from file
            {
                travelLog.append(line).append("\n"); // Append to log
            }
            
            textAreaTravelLog.setText(travelLog.toString()); // Set text area with loaded log
        }
        catch (IOException ex) // Handle exceptions
        {
            JOptionPane.showMessageDialog(frame, "Error loading data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Main method to start the application
    public static void main(String[] args)
    {
        // Attempt to set FlatLaf or use Nimbus if not available
        try
        {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf"); // Set modern look
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            // Fallback to Nimbus if FlatLaf is not available
            try
            {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                {
                    if ("Nimbus".equals(info.getName()))
                    {
                        UIManager.setLookAndFeel(info.getClassName()); // Set Nimbus look
                        break; // Exit loop once set
                    }
                }
            }
            catch (Exception fallbackEx)
            {
                // If Nimbus is also not available, print stack trace
                fallbackEx.printStackTrace();
            }
        }

        new TravelApp(); // Create and run the application
    }
}
