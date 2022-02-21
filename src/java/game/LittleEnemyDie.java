package game;

import javax.swing.ImageIcon;

public class LittleEnemyDie {
    ImageIcon si = new ImageIcon("img/si.png");        //敌人死亡效果图
    int x;                //死亡效果图的坐标
    int y;
    int count;            //记录存在时间

    public LittleEnemyDie(int x, int y, int c) { //初始化
        this.x = x;
        this.y = y;
        count = c;
    }
}
