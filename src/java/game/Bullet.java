package game;

import javax.swing.ImageIcon;

public class Bullet {
    int x;            //�����ӵ�������
    int y;
    ImageIcon bullet = new ImageIcon("img/Bullet.png");  //�����ӵ���ͼƬ

    public Bullet(int x, int y) {            //�ӵ������ʼ��
        this.x = x;
        this.y = y;
    }

    public void move() {  //�ӵ��ƶ�����
        y = y - 1;
    }
}
