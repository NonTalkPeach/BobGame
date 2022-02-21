package game;

import game.start.Main;

import java.awt.event.MouseEvent;

import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel implements MouseMotionListener {
    static int planeX = 350;        //�����������
    static int planeY = 500;
    static int x;        //���������������
    static int y;
    boolean quit = false;//������Ϸ���̣�ʧ�ܣ�
    boolean bossquit = false;//������Ϸ���̣�ʤ����
    int score = 0;//�÷�
    Boss boss = new Boss();
    Random r = new Random();
    ImageIcon plane = new ImageIcon("img/Star.png");
    ImageIcon background = new ImageIcon("img/beijing.png");
    ArrayList<Enemy> enemys = new ArrayList<Enemy>();//������̬�����ŵ���
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<LittleEnemyDie> LittleDie = new ArrayList<LittleEnemyDie>();
    Music music = new Music("music/die.wav");
    Music musicBobDie = new Music("music/BobDie.wav");

    public boolean isHit(Enemy x, Bullet y) { //��ײ��⣬����ӵ�������Ƿ���ײ
        if (y.x >= x.enemyX && y.x <= x.enemyX + x.enemy.getIconWidth()) {
            if (y.y <= x.enemyY && y.y >= x.enemyY - x.enemy.getIconHeight()) {
                return true;
            }
        }
        return false;
    }

    public void move() {    //move����������������Ϸ����Ԫ���ƶ�
        int flag = 0;
        int enemyNum = 30;//���ڿ����ӵ�����Ƶ��
        int enemyNumMiddle = 130;//���ڿ������͵��˳���Ƶ��
        int bulletNum = 50;
        while (true) {
            if (quit == true) {    //��Ϸ����
                Main.judge = 0;
                try (FileWriter wi = new FileWriter("range.txt", true);) {//�ļ�����������������ĵ���
                    wi.write("" + score + "\n");
                    wi.close();
                } catch (Exception e) {

                }
                break;
            }
            flag++;

            if (flag % bulletNum == 0) {    //�����ӵ���������ӵ���̬������
                Bullet bullet = new Bullet(planeX + plane.getIconWidth() / 3, planeY - plane.getIconHeight() / 2);
                bullets.add(bullet);
            }
            if (flag % enemyNum == 0) {//���ɵ��˶���
                Enemy enemy = new Enemy(r.nextInt(700), 0, new ImageIcon("img/Bob0.png"), 1);
                enemys.add(enemy);
            }
            if (score > 30) { //�������͵��˶���
                if (score < 50)
                    enemyNum = 70;
                else {
                    enemyNum = 200;
                    enemyNumMiddle = 300;
                }
                if (flag % enemyNumMiddle == 0) {
                    Enemy enemy1 = new Enemy(r.nextInt(700), 0, new ImageIcon("img/Bob.png"), 3);
                    enemys.add(enemy1);
                }
            }

            //1
            for (int i = 0; i < bullets.size(); i++) {//�����ӵ���һ���ƶ�
                Bullet temp = bullets.get(i);
                temp.move();
            }
            for (int j = 0; j < bullets.size(); j++) {//Խ���ӵ�ɾ��
                if (bullets.get(j).y < 0)
                    bullets.remove(j);
            }
            //2
            for (int i = 0; i < enemys.size(); i++) {//����������һ���ƶ�
                Enemy temp = enemys.get(i);
                temp.move();
            }
            for (int j = 0; j < enemys.size(); j++) {//Խ�����ɾ��
                if (enemys.get(j).enemyY > 850)
                    enemys.remove(j);
            }
            //3
            for (int i = 0; i < bullets.size(); i++) {//�ж�ÿ���ӵ�������Ƿ���ײ
                for (int j = 0; j < enemys.size() && i < bullets.size(); j++) {
                    if (isHit(enemys.get(j), bullets.get(i))) {
                        if (--enemys.get(j).HP <= 0) { //�������˵���HP��һ
                            LittleDie.add(new LittleEnemyDie(enemys.get(j).enemyX, enemys.get(j).enemyY, 0));
                            score += enemys.get(j).score;
                            enemys.remove(j);
                            music.play();
                        }
                        bullets.remove(i);
                        i++;
                    }

                }
            }


            //4
            for (int i = 0; i < LittleDie.size(); i++) {
                LittleDie.get(i).count++;
                if (LittleDie.get(i).count > 30)  //����ÿˢ��30������Ч��Ц��
                    LittleDie.remove(i);
            }

            //5Boss
            if (score > 50 && boss.HP > 0) { //��������50����Boss
                boss.move(); //boss�ƶ�����
                for (int i = 0; i < bullets.size(); i++) { //�����ײ
                    if (boss.x < bullets.get(i).x && bullets.get(i).x < boss.x + boss.ima.getIconWidth()) {
                        if (boss.y < bullets.get(i).y && bullets.get(i).y < boss.y + boss.ima.getIconHeight()) {
                            bullets.remove(i);
                            if (--boss.HP <= 0) {  //boss��������Ч��ͼ����������
                                LittleDie.add(new LittleEnemyDie(boss.x, boss.y, -100));
                                LittleDie.add(new LittleEnemyDie(boss.x + 50, boss.y, -100));
                                LittleDie.add(new LittleEnemyDie(boss.x + 50, boss.y, -100));
                                LittleDie.add(new LittleEnemyDie(boss.x, boss.y + 60, -100));
                                LittleDie.add(new LittleEnemyDie(boss.x, boss.y + 100, -100));
                                score += boss.score - flag;
                                music.play();
                                music.play();
                                music.play();
                                music.play();
                                music.play();
                                quit = true;
                                bossquit = true;

                            }
                        }
                    }
                }
            }

            repaint();  //���»�ͼ


            try {
                Thread.sleep(3);   //����ˢ��Ƶ��
            } catch (Exception e) {

            }
        }
    }


    public void paint(Graphics g) {  //��ͼ����
        super.paint(g);
        g.setFont(new Font("", Font.BOLD, 30));
        g.drawImage(background.getImage(), 0, 0, null);
        g.drawString("�÷֣�" + score, 10, 30);
        g.drawImage(plane.getImage(), planeX, planeY, null);
        if (score > 50) {
            g.drawString("BossHP:" + boss.HP, 500, 30);
        }
        for (int i = 0; i < bullets.size(); i++) { //�����ӵ�
            g.drawImage(bullets.get(i).bullet.getImage(), bullets.get(i).x, bullets.get(i).y, null);
        }
        for (int i = 0; i < enemys.size(); i++) {//���Ƶ���
            g.drawImage(enemys.get(i).enemy.getImage(), enemys.get(i).enemyX, enemys.get(i).enemyY, null);
        }
        for (int i = 0; i < LittleDie.size(); i++) {//���Ƶ�������Ч��ͼ
            g.drawImage(LittleDie.get(i).si.getImage(), LittleDie.get(i).x, LittleDie.get(i).y, null);
        }

        for (int i = 0; i < enemys.size(); i++) {//�������Ƿ�����
            if (enemys.get(i).enemyX >= planeX + 20 && enemys.get(i).enemyX + 20 <= planeX + plane.getIconWidth()) {
                if (enemys.get(i).enemyY >= planeY + 20 && enemys.get(i).enemyY + 20 <= planeY + plane.getIconHeight()) {
                    quit = true;
                }
            }
        }
        if (score > 50 && boss.HP > 0) {//����boss
            g.drawImage(boss.ima.getImage(), boss.x, boss.y, null);
            if (planeX > boss.x && planeX < boss.x + boss.ima.getIconWidth()) {
                if (planeY > boss.y && planeY < boss.y + boss.ima.getIconHeight()) {
                    quit = true;
                }
            }
        }
        if (quit) {
            if (bossquit) {
                g.drawImage(new ImageIcon("img/victory.png").getImage(), 100, 180, null);
            } else {
                g.drawImage(new ImageIcon("img/GameOver.png").getImage(), 200, 100, null);
                g.drawImage(new ImageIcon("img/StarDie.png").getImage(), planeX + 3, planeY, null);
                musicBobDie.play();
            }

        }

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) { //����������������ƶ�
        x = e.getX();
        y = e.getY();
        planeX = x - plane.getIconWidth() / 2;
        planeY = y - plane.getIconHeight() / 2;
        if (planeX < 0 || planeX > 680 - plane.getIconWidth()) {
            if (planeX < 0) {
                planeX = 0;
            } else {
                planeX = 680 - plane.getIconWidth();
            }
        }
        if (planeY < 0 || planeY > 820 - plane.getIconHeight()) {
            if (planeY < 0) {
                planeY = 0;
            } else {
                planeY = 820 - plane.getIconHeight();
            }
        }
        if (quit == false)
            repaint();

    }

    public void reset() {//����Ԫ��
        planeX = 350;
        planeY = 500;
        quit = false;
        bossquit = false;
        score = 0;
        enemys.clear();
        bullets.clear();
        LittleDie.clear();
        boss.HP = 50;
        boss.x = r.nextInt(600);
    }

}
