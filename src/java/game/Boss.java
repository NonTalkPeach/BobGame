package game;

import java.util.Random;

import javax.swing.ImageIcon;

public class Boss {
    int x;                //boss坐标
    int y;
    int HP;            //boss的HP值
    int patten = 1;        //boss移动模式默认为1
    int score = 50000; //击败boss的初始得分
    Random random = new Random();
    ImageIcon ima = new ImageIcon("img/Boss.png");        //放入boss图片

    public Boss() {            //构造方法
        this.x = random.nextInt(600);  //Boss出现位置x坐标上的随机
        this.y = 0;
        HP = 50;
    }

    public void move() {                            //boss的移动函数
        if (patten == 1) {            //模式1，往左移
            x += 1;
            if (x > 600)        //越界，开启模式2，往右移
                patten = 2;
            if (random.nextInt(1000) >= 998) {        //每往左移1格，有0.2%的概率往下迅速移动（boss攻击模式）
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

        if (patten == 3) {        //往下移动
            y += 4;
            if (y > 650) {      //越界启动模式4，往回走
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
