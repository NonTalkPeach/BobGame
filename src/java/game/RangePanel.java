package game;

import game.start.Main;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class RangePanel extends JPanel {
    ImageIcon back = new ImageIcon("img/beijing.png");
    ImageIcon title = new ImageIcon("img/GameName.png");
    ImageIcon ima = new ImageIcon("img/range.png");

    public RangePanel() {
        this.setLayout(null);
    }

    public void paintComponent(Graphics g) {  //»æÖÆÅÅÐÐ°ñÍ¼
        g.setFont(new Font("", Font.BOLD, 30));
        g.drawImage(back.getImage(), 0, 0, null);
        g.drawImage(ima.getImage(), 35, 50, null);
        for (int i = 99; i >= 91; i--) {
            g.drawString("NO." + (99 - i + 1) + "   " + Main.range[i], 250, 300 + (99 - i + 1) * 40);
        }
    }
}
