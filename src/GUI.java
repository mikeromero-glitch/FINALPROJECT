import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI implements ActionListener {
    Timer updateTimer;
    ActionListener guiUpdate;

    Cookies logic = new Cookies();
    Color myColor = new Color(251, 171, 255);
    JFrame frame;
    JButton cookieButton;
    JButton Upgrade1Button;
    JButton Upgrade2Button;
    JPanel panel;
    JPanel panel2;
    JLabel CountLabel;
    JLabel CountLabel2;
    ImageIcon Nummie = new ImageIcon("src/crumbl.png");
    ImageIcon cookie1 = new ImageIcon("src/OG.png");
    ImageIcon cookie2 = new ImageIcon("src/cream.png");
    boolean CookiesOn=false;


    public GUI() {

        guiUpdate = _ -> {
            UpdateCountLabel();
            if(logic.getUpgrade1Purchases()>0&&!CookiesOn){
                logic.AutoTimer();
                CookiesOn=true;
            }
        };
        updateTimer = new Timer(500,guiUpdate);
        updateTimer.start();

        frame = new JFrame("COOKIE CRASH!");
        cookieButton = new JButton(Nummie);
        cookieButton.setPreferredSize(new Dimension(512,512));
        cookieButton.addActionListener(e -> {
            logic.Click();
            UpdateCountLabel();
        });
        Upgrade1Button = new JButton("Upgrade (+1 APC/AUTO) | Cost: "+logic.getUpgrade1Cost()+" Cookies");
       //Upgrade1Button = new JButton(cookie2);
        //Upgrade1Button.setVerticalTextPosition(SwingConstants.TOP);
        //Upgrade1Button.setHorizontalTextPosition(SwingConstants.CENTER);//https://stackoverflow.com/questions/2713480/is-it-possible-to-put-text-on-top-of-a-image-in-a-button
       //Upgrade1Button.setPreferredSize(new Dimension(200,200));
        Upgrade1Button.addActionListener(e -> {
            if(logic.buyUpgrade1()){
                UpdateCountLabel();
                Upgrade1Button.setText("Upgrade (+1 APC/AUTO) | Cost: "+logic.getUpgrade1Cost()+" Cookies");
            }else{
                JOptionPane.showMessageDialog(frame,
                        "Not enough cookies! You need " + logic.getUpgrade1Cost() + " cookies.",
                        "Insufficient Cookies",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        Upgrade2Button = new JButton("Upgrade (+2 APC/AUTO) | Cost: "+logic.getUpgrade2Cost()+" Cookies");
        //Upgrade2Button = new JButton(cookie1);
        //Upgrade2Button.setVerticalTextPosition(SwingConstants.TOP);
        //Upgrade2Button.setVerticalTextPosition(JButton.CENTER);
        //Upgrade2Button.setPreferredSize(new Dimension(200,200));
        Upgrade2Button.addActionListener(e -> {
            if(logic.buyUpgrade2()){
                UpdateCountLabel();
                Upgrade2Button.setText("Upgrade (+2 APC) | Cost: "+logic.getUpgrade2Cost()+" Cookies");
            }else{
                JOptionPane.showMessageDialog(frame,
                        "Not enough cookies! You need " + logic.getUpgrade2Cost() + " cookies.",
                        "Insufficient Cookies",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        CountLabel = new JLabel("Cookies: "+logic.getCookieCount()+" | APC: "+logic.getAmountPerClick());

        panel = new JPanel();
        panel2 = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(myColor);
        panel2.setBackground(myColor);

        panel.add(cookieButton);
        panel2.add(Upgrade1Button);
        panel2.add(Upgrade2Button);
        panel.add(CountLabel);



        frame.add(panel, BorderLayout.WEST);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setIconImage(Nummie.getImage());

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        Upgrade1Button = new JButton("Upgrade (+1 APC/AUTO) | Cost: "+logic.getUpgrade1Cost()+" Cookies | Owned: "+logic.getUpgrade1Purchases());
        Upgrade1Button.addActionListener(_ -> {
            if(logic.buyUpgrade1()){
                Upgrade1Button.setText("Upgrade (+1 APC/AUTO) | Cost: "+logic.getUpgrade1Cost()+" Cookies | Owned: "+logic.getUpgrade1Purchases());
            }else{
                JOptionPane.showMessageDialog(frame,
                        "Not enough cookies! You need " + logic.getUpgrade1Cost() + " cookies.",
                        "Insufficient Cookies",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        Upgrade2Button = new JButton("Upgrade (+2 APC) | Cost: "+logic.getUpgrade2Cost()+" Cookies | Owned: "+logic.getUpgrade2Purchases());
        Upgrade2Button.addActionListener(_ -> {
            if(logic.buyUpgrade1()){
                Upgrade2Button.setText("Upgrade (+2 APC) | Cost: "+logic.getUpgrade2Cost()+" Cookies | Owned: "+logic.getUpgrade2Purchases());
            }else{
                JOptionPane.showMessageDialog(frame,
                        "Not enough cookies! You need " + logic.getUpgrade2Cost() + " cookies.",
                        "Insufficient Cookies",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

    }

    public void UpdateCountLabel(){
        CountLabel.setText("Cookies: "+logic.getCookieCount()+" | APC: "+logic.getAmountPerClick());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
