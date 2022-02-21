package game;

import javax.swing.ImageIcon;

public class Enemy {
    int enemyX;        //敌人坐标
    int enemyY;
    int HP;            //敌人HP值
    int score;        //敌人被击杀后的得分
    ImageIcon enemy;    //放敌人图片
    int pa;                //敌人移动模式

    public Enemy(int x, int y, ImageIcon image, int HP) {  //初始化敌人属性
        enemyX = x;
        enemyY = y;
        enemy = image;
        this.HP = HP;
        score = HP;
        pa = x % 2;
    }

    public void move() {            //敌人移动函数
        if (score > 1) {        //分数大于1的为中级敌人，中级敌人移动方式
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
        } else {        //小型敌人的移动方式
            enemyY += 1;
        }
    }
}
