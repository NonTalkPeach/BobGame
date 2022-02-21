package game.start;

import game.BackgroundPanel;
import game.Panel;
import game.RangePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Main {
    public static int width = 700;        //窗口大小
    public static int height = 850;
    public static int judge = 2;        //后面控制move函数运行
    public static int[] range;            //存储玩家历史分数

    public static void main(String[] args) throws Exception {
        //1?
        File musicPath = new File("music/backgroundMusic.wav");   //打开音乐文件
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);  //导入音乐流
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();                    //播放音乐
        clip.loop(Clip.LOOP_CONTINUOUSLY);        //设置循环播放


        JFrame frame = new JFrame();    //创建窗口对象
        Panel panel = new Panel();    //创建游戏面板对象
        BackgroundPanel jp = new BackgroundPanel(new ImageIcon("img/beijing.png"));//创建背景面板对象

        ImageIcon icon = new ImageIcon("img/button_normal.png");    //导入开始游戏按钮图片
        JButton button = new JButton(icon);    //创建开始游戏按钮
        JButton button2 = new JButton("Ranking");
        button.setBounds(700 / 2 - icon.getIconWidth() + 25, 850 / 2 - icon.getIconHeight() + 50, icon.getIconWidth(), icon.getIconHeight());
        button.setContentAreaFilled(false);        //设置样式
        button.setBorderPainted(false);
        button.setPressedIcon(new ImageIcon("img/button_press.png"));
        button2.setBounds(700 / 2 - icon.getIconWidth() + 25, 850 / 2 - icon.getIconHeight() + 125, icon.getIconWidth(), icon.getIconHeight());

        jp.add(button);    //在背景面板加入按钮
        jp.add(button2);

        frame.setTitle("Battle of beechburg");    //设置窗口标题
        frame.setSize(width, height);    //设置窗口大小
        frame.setVisible(true);//设置窗口可见
        frame.setLocationRelativeTo(null);//设置窗口居中显示
        frame.setResizable(false);//设置不可调整大小
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭时程序结束

        frame.add(jp); //在窗口加入背景面板
        button.addActionListener(new ActionListener() {        //增加开始游戏按钮事件监听
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                judge = 1;                    //move函数循环模式
                panel.reset();        //调用reset函数，初始化panel里面的值
                frame.remove(jp);//移去背景面板
                frame.add(panel);//加入游戏面板
                frame.addMouseMotionListener(panel);//加入鼠标监听
                SwingUtilities.updateComponentTreeUI(frame);//刷新窗口
            }
        });
        button2.addActionListener(new ActionListener() { //排行榜按钮事件监听
            public void actionPerformed(ActionEvent e) {
                try (FileReader fi = new FileReader("range.txt"); //打开文件
                     BufferedReader bfr = new BufferedReader(fi);//缓冲读取
                ) {
                    String line;
                    range = null;
                    range = new int[100];
                    int i = 0;
                    while ((line = bfr.readLine()) != null) {    //读入文件数据
                        range[i] = Integer.parseInt(line);
                        i++;
                    }
                    Arrays.sort(range);        //数组排序
                    RangePanel r = new RangePanel();    //创建排行榜面板对象
                    JButton buttonBack = new JButton("Back");
                    buttonBack.setBounds(300, 700, 90, 40);
                    buttonBack.addActionListener(new ActionListener() {  //增加返回按钮事件监听
                        public void actionPerformed(ActionEvent e) {
                            frame.remove(r); //移去排行榜面板
                            frame.add(jp);//加入背景面板
                            SwingUtilities.updateComponentTreeUI(frame);//刷新窗口
                        }
                    });
                    r.add(buttonBack);//加入返回按钮
                    frame.remove(jp);//移去背景面板
                    frame.add(r);//加入排行榜面板
                    SwingUtilities.updateComponentTreeUI(frame);//刷新窗口

                } catch (IOException ignored) {

                }
            }
        });


        JButton b3 = new JButton("Again"); //设置按钮
        panel.setLayout(null);
        b3.setBounds(265, 455, 150, 50);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.remove(b3);
                frame.remove(panel);
                frame.add(jp);
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });


        while (true) {  //控制move()函数使用
            if (judge == 1) {           //游戏模式，此时玩家已经点击开始游戏
                panel.move();  //调用move
            } else if (judge == 0) {//游戏结束模式，玩家死亡或者胜利通过，move函数停止调用
                judge = 2;
                panel.add(b3);//弹出再玩一次按钮
                frame.removeMouseMotionListener(panel);
                SwingUtilities.updateComponentTreeUI(frame);
            } else if (judge == 2) {//开始模式，玩家为点击任何按钮，此时停留在背景菜单面板
                System.out.println("Waiting...");
            }
        }

    }
}
