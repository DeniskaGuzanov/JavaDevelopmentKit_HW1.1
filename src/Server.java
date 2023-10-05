import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server extends JFrame {

    private static final int WINDOWS_HEIGHT = 400;
    private static final int WINDOWS_WIDTH = 500;
    private static final int WINDOWS_POS_X = 500;
    private static final int WINDOWS_POS_Y = 250;

    private static boolean isServerWorking;

    private final JButton btn_Start = new JButton("Server Start");
    private final JButton btn_Stop = new JButton("Server Stop");
    private final JTextArea log = new JTextArea("Server is stopped");

    public Server() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOWS_POS_X, WINDOWS_POS_Y);
        setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        setAlwaysOnTop(true);
        setTitle("Chat Server");
        setResizable(false);

        add(log, BorderLayout.SOUTH);
        JPanel jPanel = new JPanel(new GridLayout(1, 2));
        jPanel.add(btn_Start);
        jPanel.add(btn_Stop);
        add(jPanel);

        log.setEditable(false);

        btn_Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.setText("Server is already running");
                } else {
                    log.setText("Server is started");
                }
                isServerWorking = true;

            }
        });
        btn_Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.setText("Server is already stopped");
                } else {
                    log.setText("Server is stop");
                }
                isServerWorking = false;
            }
        });

        setVisible(true);


    }
}
