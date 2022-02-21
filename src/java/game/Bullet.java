package game;

import javax.swing.ImageIcon;

public class Bullet {
    int x;            //设置子弹的坐标
    int y;
    ImageIcon bullet = new ImageIcon("img/Bullet.png");  //设置子弹的图片

    public Bullet(int x, int y) {            //子弹坐标初始化
        this.x = x;
        this.y = y;
    }

    public void move() {  //子弹移动函数
        y = y - 1;
    }
}
