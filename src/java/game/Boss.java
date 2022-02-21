package game;

import java.util.Random;

import javax.swing.ImageIcon;

public class Boss {
    int x;                //boss����
    int y;
    int HP;            //boss��HPֵ
    int patten = 1;        //boss�ƶ�ģʽĬ��Ϊ1
    int score = 50000; //����boss�ĳ�ʼ�÷�
    Random random = new Random();
    ImageIcon ima = new ImageIcon("img/Boss.png");        //����bossͼƬ

    public Boss() {            //���췽��
        this.x = random.nextInt(600);  //Boss����λ��x�����ϵ����
        this.y = 0;
        HP = 50;
    }

    public void move() {                            //boss���ƶ�����
        if (patten == 1) {            //ģʽ1��������
            x += 1;
            if (x > 600)        //Խ�磬����ģʽ2��������
                patten = 2;
            if (random.nextInt(1000) >= 998) {        //ÿ������1����0.2%�ĸ�������Ѹ���ƶ���boss����ģʽ��
                patten = 3;
            }
        } else if (patten == 2) {
            x -= 1;
            if (x < 0) {
                patten = 1;
            }
            if (random.nextInt(1000) >= 998) {
                patten = 3;
            }
        }

        if (patten == 3) {        //�����ƶ�
            y += 4;
            if (y > 650) {      //Խ������ģʽ4��������
                patten = 4;
            }
        } else if (patten == 4) {
            y -= 5;
            if (y <= 0) {
                patten = 1;
            }
        }

    }
}
