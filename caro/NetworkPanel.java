package caro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class NetworkPanel extends JPanel {

    public static Client myClient;
    public BackButton myBackButton;
    public String host;
    public int port;
    public static Server myServer;

    public static JButton joinButton;

    public ImagePanel waitingPanel;

    ImagePanel background = new ImagePanel("picture/main.png", 0, 0, 800, 600);

    public void addJoinButton() {
        joinButton = new JButton("Sẵn sàng");
        joinButton.setBounds(50, 150 + 10, 100, 50);
        joinButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //  tao thread  đẻ quá trình kết nối song song với game
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        while (true) {

                            String serverAddress;
                            try {
                                serverAddress = InetAddress.getLocalHost().getHostAddress();; 
                                serverAddress = (String) JOptionPane.showInputDialog(null, "Nhập địa chỉ IP", "Thông tin",
                                        JOptionPane.INFORMATION_MESSAGE, null, null, serverAddress);

                                System.out.println("server address: " + serverAddress);

                                myClient = new Client(serverAddress);

                                JOptionPane.showMessageDialog(null, "Để kết nối, người chơi của bạn cần nhập IP : " + serverAddress);

                                Main.myFrame.remove(Main.myNetworkPanel);
                                Main.twoLanPlayerPanel = myClient.background;
                                Main.myFrame.add(Main.twoLanPlayerPanel);
                                Main.myFrame.repaint();
                                myClient.play();

                                if (myClient.wantsToPlayAgain()) {
                                    Main.myFrame.remove(Main.twoLanPlayerPanel);

                                } else {
                                    Main.myFrame.remove(Main.twoLanPlayerPanel);
                                    Main.myFrame.add(Main.myStartPanel);

                                    break;
                                }
                            } catch (Exception ex) {

                            }

                        }
                    }

                }).start();

                joinButton.setEnabled(false);

            }
        });

        add(joinButton);

    }

    public NetworkPanel() {
        myServer = new Server();

        new Thread(
                new Runnable() {

                    @Override
                    public void run() {
                        setBounds(0, 0, 800, 600);
                        setLayout(null);

                        // button to return start menu
                        myBackButton = new BackButton("NetworkPanel");
                        add(myBackButton);

                        /*----------button to join to host---------- */
                        addJoinButton();

                        /*----------- Picture of backround----------- */
                        add(background);

                    }
                }
        ).start();

    }

}
