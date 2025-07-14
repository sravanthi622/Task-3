import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ATMGUI extends JFrame {
    double balance = 10000;
    ArrayList<String> history = new ArrayList<>();

    JTextArea output;
    JTextField pinField, amountField, transferField;

    ATMGUI() {
        setTitle("ATM Interface");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        int frameWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int frameHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        JLabel heading = new JLabel("ATM INTERFACE");
        heading.setBounds((frameWidth - 400) / 2, 40, 400, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 36));
        heading.setForeground(new Color(0, 102, 204));
        add(heading);

        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 18));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        output.setBackground(new Color(255, 255, 255, 230));
        output.setBounds((frameWidth - 900) / 2, 100, 900, 150);
        add(output);

        int formX = (frameWidth - 600) / 2;

        JLabel pinLabel = new JLabel("🔐 Enter PIN:");
        pinLabel.setBounds(formX, 270, 200, 30);
        pinLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(pinLabel);

        pinField = new JTextField();
        pinField.setBounds(formX + 200, 270, 250, 30);
        add(pinField);

        JLabel amtLabel = new JLabel("💰 Enter Amount:");
        amtLabel.setBounds(formX, 320, 200, 30);
        amtLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(amtLabel);

        amountField = new JTextField();
        amountField.setBounds(formX + 200, 320, 250, 30);
        add(amountField);

        JLabel recLabel = new JLabel("➡️ Recipient ID:");
        recLabel.setBounds(formX, 370, 200, 30);
        recLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(recLabel);

        transferField = new JTextField();
        transferField.setBounds(formX + 200, 370, 250, 30);
        add(transferField);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(formX + 500, 270, 150, 35);
        depositBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        depositBtn.addActionListener(e -> deposit());
        add(depositBtn);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(formX + 500, 320, 150, 35);
        withdrawBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        withdrawBtn.addActionListener(e -> withdraw());
        add(withdrawBtn);

        JButton transferBtn = new JButton("Transfer");
        transferBtn.setBounds(formX + 500, 370, 150, 35);
        transferBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        transferBtn.addActionListener(e -> transfer());
        add(transferBtn);

        JButton historyBtn = new JButton("Show History");
        historyBtn.setBounds((frameWidth - 200) / 2, 430, 200, 40);
        historyBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        historyBtn.addActionListener(e -> showHistory());
        add(historyBtn);

        JButton quitBtn = new JButton("Quit");
        quitBtn.setBounds((frameWidth - 100) / 2, 490, 100, 40);
        quitBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        quitBtn.setForeground(Color.RED);
        quitBtn.addActionListener(e -> System.exit(0));
        add(quitBtn);

        setVisible(true);
    }

    void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            balance += amount;
            history.add("Deposited: ₹" + amount);
            output.setText("✅ Deposited ₹" + amount + "\n💰 Balance: ₹" + balance);
        } catch (Exception e) {
            output.setText("❌ Invalid amount entered.");
        }
        amountField.setText("");
    }

    void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= balance) {
                balance -= amount;
                history.add("Withdrawn: ₹" + amount);
                output.setText("✅ Withdrawn ₹" + amount + "\n💰 Balance: ₹" + balance);
            } else {
                output.setText("❌ Insufficient balance.");
            }
        } catch (Exception e) {
            output.setText("❌ Invalid amount entered.");
        }
        amountField.setText("");
    }

    void transfer() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String recipient = transferField.getText();
            if (amount <= balance && !recipient.isEmpty()) {
                balance -= amount;
                history.add("Transferred ₹" + amount + " to " + recipient);
                output.setText("✅ Transferred ₹" + amount + " to " + recipient + "\n💰 Balance: ₹" + balance);
            } else {
                output.setText("❌ Transfer failed. Check amount or recipient.");
            }
        } catch (Exception e) {
            output.setText("❌ Invalid input.");
        }
        amountField.setText("");
        transferField.setText("");
    }

    void showHistory() {
        if (history.isEmpty()) {
            output.setText("No transaction history.");
        } else {
            StringBuilder sb = new StringBuilder("📋 Transaction History:\n");
            for (String h : history) {
                sb.append("• ").append(h).append("\n");
            }
            output.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}
