package game;

import javax.swing.ImageIcon;

public class LittleEnemyDie {
    ImageIcon si = new ImageIcon("img/si.png");        //��������Ч��ͼ
    int x;                //����Ч��ͼ������
    int y;
    int count;            //��¼����ʱ��

    public LittleEnemyDie(int x, int y, int c) { //��ʼ��
        this.x = x;
        this.y = y;
        count = c;
    }
}
