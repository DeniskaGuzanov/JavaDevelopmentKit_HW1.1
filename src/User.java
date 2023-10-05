import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class User extends JFrame {

    private static final int WINDOWS_POS_Y = 250;
    private static final int WINDOWS_POS_X = 1000;
    private static final int WINDOWS_HEIGHT = 400;
    private static final int WINDOWS_WIDTH = 500;

    private static final String FILE_NAME = "LOCK_TEXT";

    JButton btnConnect = new JButton("Connect");
    JButton btnSend = new JButton("Send");
    JTextField ipField = new JTextField("ip address: ");
    JTextField portField = new JTextField("port address: ");
    JTextField passwordField = new JTextField("password");
    JTextField loginField = new JTextField("Login: ");

    JTextArea messengerField = new JTextArea();
    JTextField textSendField = new JTextField();


    JPanel jPanelTop = new JPanel(new GridLayout(2, 3));
    JPanel jPanelButton = new JPanel(new BorderLayout());
    JPanel jPanelUserList = new JPanel(new FlowLayout());

    public User() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOWS_POS_X, WINDOWS_POS_Y);
        setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        setAlwaysOnTop(true);
        setTitle("USERS_CHAT");
        setResizable(false);

        jPanelTop.add(ipField);
        jPanelTop.add(portField);
        jPanelTop.add(passwordField);
        jPanelTop.add(loginField);
        jPanelTop.add(btnConnect);

        add(jPanelTop, BorderLayout.NORTH);
        add(messengerField);

        String[] users = {"Maksim, Igor, Anton, Sergey, Denis"};
        JList<String> userlist = new JList<>(users);

        jPanelUserList.add(userlist);
        add(jPanelUserList, BorderLayout.EAST);
        jPanelButton.add(textSendField, BorderLayout.CENTER);
        jPanelButton.add(btnSend, BorderLayout.EAST);
        add(jPanelButton, BorderLayout.SOUTH);
        messengerField.setEditable(false);


        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messengerField.setText(String.valueOf(readLogTFromFile()));
            }
        });

        textSendField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (e.getKeyChar() == KeyEvent.VK_ENTER) ;
                {
                    btnSend.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String result = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ": " +
                        loginField.getText().substring(7) + ": " + " :" + textSendField.getText() +
                        "\n";
                messengerField.append(result);
                writeLogToFile(result);
                textSendField.setText("");
            }
        });
        setVisible(true);
    }

    private void writeLogToFile(String data) {

        try (FileWriter writer = new FileWriter(User.FILE_NAME, true);
             BufferedWriter bwr = new BufferedWriter(writer)) {
            bwr.write(data);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private StringBuffer readLogTFromFile() {
        StringBuffer stringBuffer = new StringBuffer();
        try (FileReader reader = new FileReader(User.FILE_NAME);
             BufferedReader brr = new BufferedReader(reader)) {
            String line = brr.readLine();
            if (line == null || line.isBlank()) {
                System.out.println("log is empty");

                return stringBuffer.append("log is empty.\n ");
            }

        while (line != null) {
            stringBuffer.append(line);
            stringBuffer.append("\n ");
            line = brr.readLine();
        }
        return stringBuffer;
    }
        catch  (IOException ioE)

    {
        System.out.println("log File is not found: " + FILE_NAME);

    }
    return stringBuffer.append("log File is not found : "+ FILE_NAME + "\n ");
    }

}
