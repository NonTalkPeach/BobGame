package game;

import java.awt.Graphics;
import javax.swing.*;

public class BackgroundPanel extends JPanel {        //�̳�JPanel��
    private final ImageIcon ima;                                    //��ű���ͼ
    private final ImageIcon ima2 = new ImageIcon("img/GameName.png");    //�����Ϸ����

    public BackgroundPanel(ImageIcon ima) {                                //���췽��
        this.setLayout(null);
        this.ima = ima;
    }

    public void paintComponent(Graphics g) {                    //��������ͼ�ͱ���
        g.drawImage(ima.getImage(), 0, 0, null);
        g.drawImage(ima2.getImage(), 0, 50, null);
    }
}
