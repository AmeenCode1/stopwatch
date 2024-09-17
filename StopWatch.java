import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopWatch extends JFrame implements ActionListener {
    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0;

    public StopWatch() {
        // Set up the main frame
        setTitle("Modern Stopwatch");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
        getContentPane().setBackground(new Color(40, 44, 52));  // Dark background

        // Modern time label
        timeLabel = new JLabel("00:00:00", JLabel.CENTER);
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 60));
        timeLabel.setForeground(Color.WHITE);

        // Create modern buttons
        startButton = createModernButton("Start", new Color(76, 175, 80));
        stopButton = createModernButton("Stop", new Color(244, 67, 54));
        resetButton = createModernButton("Reset", new Color(255, 152, 0));

        // Button panel with modern spacing
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // 10px spacing
        buttonPanel.setBackground(new Color(40, 44, 52)); // Match background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        // Main layout
        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize timer
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                updateTimeLabel();
            }
        });
    }

    // Create modern styled button
    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Modern font
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
//        button.setFocusPainted(false);  // Remove button focus outline
//        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Change cursor on hover
//        button.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50), 1)); // Subtle border
//        button.setOpaque(true);
        button.setContentAreaFilled(false); // Flat look
        button.setBackground(bgColor); // Set background color
        button.setBorderPainted(true); // Keep the border

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startTimer();
        } else if (e.getSource() == stopButton) {
            stopTimer();
        } else if (e.getSource() == resetButton) {
            resetTimer();
        }
    }

    private void startTimer() {
        timer.start();
    }

    private void stopTimer() {
        timer.stop();
    }

    private void resetTimer() {
        timer.stop();
        elapsedTime = 0;
        updateTimeLabel();
    }

    private void updateTimeLabel() {
        int hours = elapsedTime / 3600000;
        int minutes = (elapsedTime % 3600000) / 60000;
        int seconds = (elapsedTime % 60000) / 1000;
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public static void main(String[] args) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.setVisible(true);
    }
}
