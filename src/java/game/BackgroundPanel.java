package game;

import java.awt.Graphics;
import javax.swing.*;

public class BackgroundPanel extends JPanel {        //继承JPanel类
    private final ImageIcon ima;                                    //存放背景图
    private final ImageIcon ima2 = new ImageIcon("img/GameName.png");    //存放游戏标题

    public BackgroundPanel(ImageIcon ima) {                                //构造方法
        this.setLayout(null);
        this.ima = ima;
    }

    public void paintComponent(Graphics g) {                    //画出背景图和标题
        g.drawImage(ima.getImage(), 0, 0, null);
        g.drawImage(ima2.getImage(), 0, 50, null);
    }
}
