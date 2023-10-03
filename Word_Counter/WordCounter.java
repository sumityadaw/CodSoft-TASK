import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounter extends JFrame implements ActionListener {
    private JTextField fileNameField;
    private JTextArea resultArea;

    public WordCounter() {
        setTitle("Word Counter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Enter a file name or text:");
        fileNameField = new JTextField(20);
        JButton countButton = new JButton("Count Words");
        countButton.addActionListener(this);

        inputPanel.add(label, BorderLayout.NORTH);
        inputPanel.add(fileNameField, BorderLayout.CENTER);
        inputPanel.add(countButton, BorderLayout.SOUTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String fileNameOrText = fileNameField.getText();
        String input;
        if (fileNameOrText.endsWith(".txt")) {
            input = readFromFile(fileNameOrText);
        } else {
            input = fileNameOrText;
        }

        // Split input into words
        String[] words = input.split("\\s+|\\p{Punct}");

        // Initialize a counter for word occurrences
        Map<String, Integer> wordCount = new HashMap<>();

        // Count word occurrences
        for (String word : words) {
            if (!word.isEmpty()) {
                word = word.toLowerCase(); // Convert to lowercase for case-insensitive counting
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        // Display word count and frequency
        StringBuilder result = new StringBuilder();
        result.append("Total words: ").append(words.length).append("\n");
        result.append("Unique words: ").append(wordCount.size()).append("\n");
        result.append("Word frequency:\n");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        resultArea.setText(result.toString());
    }

    private static String readFromFile(String fileName) {
        StringBuilder input = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input.toString();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordCounter());
    }}
