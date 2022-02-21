package game;

import javax.swing.ImageIcon;

public class Enemy {
    int enemyX;        //��������
    int enemyY;
    int HP;            //����HPֵ
    int score;        //���˱���ɱ��ĵ÷�
    ImageIcon enemy;    //�ŵ���ͼƬ
    int pa;                //�����ƶ�ģʽ

    public Enemy(int x, int y, ImageIcon image, int HP) {  //��ʼ����������
        enemyX = x;
        enemyY = y;
        enemy = image;
        this.HP = HP;
        score = HP;
        pa = x % 2;
    }

    public void move() {            //�����ƶ�����
        if (score > 1) {        //��������1��Ϊ�м����ˣ��м������ƶ���ʽ
            if (pa == 1) {
                enemyX -= 1;
                enemyY += 1;
                if (enemyX < 0)
                    pa = 0;
            } else if (pa == 0) {
                enemyY += 1;
                enemyX += 1;
                if (enemyX > 650)
                    pa = 1;
            }
        } else {        //С�͵��˵��ƶ���ʽ
            enemyY += 1;
        }
    }
}
