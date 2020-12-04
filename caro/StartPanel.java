package caro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StartPanel extends JPanel {

    public StartPanel() {
        setLayout(null);
        setBounds(0, 0, 800, 600);

        /* hình nền cho Game : */
        ImagePanel background = new ImagePanel("picture/StartGame.png", 0, 0, 800, 600);

        //Thêm các button chức năng
        JButton oneButton = new JButton("1 Player");
        JButton twoButton = new JButton("2 Players");
        JButton LANButton = new JButton("LAN game");
        JButton exitButton = new JButton("Exit");

        // định vị trí các button 
        LANButton.setBounds(350, 300, 100, 30);
        exitButton.setBounds(350, 350, 100, 30);


        /* 2 người chơi kết nối 2 máy qua mạng LAN  */
        LANButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                /* chương trình gỡ bỏ chế độ  START MENU, 
                 thay vào là menu kết nối mạng LAN 
                 */
                Main.myFrame.remove(Main.myStartPanel);
                NetworkPanel.joinButton.setEnabled(true);
                Main.myFrame.add(Main.myNetworkPanel);
                Main.myFrame.repaint();
            }
        });

        // thêm hành động cho EXIT button 
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(LANButton);
        add(exitButton);

        this.add(background);

    }

}
