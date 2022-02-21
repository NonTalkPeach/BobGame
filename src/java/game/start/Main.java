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
    public static int width = 700;        //���ڴ�С
    public static int height = 850;
    public static int judge = 2;        //�������move��������
    public static int[] range;            //�洢�����ʷ����

    public static void main(String[] args) throws Exception {
        //1?
        File musicPath = new File("music/backgroundMusic.wav");   //�������ļ�
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);  //����������
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();                    //��������
        clip.loop(Clip.LOOP_CONTINUOUSLY);        //����ѭ������


        JFrame frame = new JFrame();    //�������ڶ���
        Panel panel = new Panel();    //������Ϸ������
        BackgroundPanel jp = new BackgroundPanel(new ImageIcon("img/beijing.png"));//��������������

        ImageIcon icon = new ImageIcon("img/button_normal.png");    //���뿪ʼ��Ϸ��ťͼƬ
        JButton button = new JButton(icon);    //������ʼ��Ϸ��ť
        JButton button2 = new JButton("Ranking");
        button.setBounds(700 / 2 - icon.getIconWidth() + 25, 850 / 2 - icon.getIconHeight() + 50, icon.getIconWidth(), icon.getIconHeight());
        button.setContentAreaFilled(false);        //������ʽ
        button.setBorderPainted(false);
        button.setPressedIcon(new ImageIcon("img/button_press.png"));
        button2.setBounds(700 / 2 - icon.getIconWidth() + 25, 850 / 2 - icon.getIconHeight() + 125, icon.getIconWidth(), icon.getIconHeight());

        jp.add(button);    //�ڱ��������밴ť
        jp.add(button2);

        frame.setTitle("Battle of beechburg");    //���ô��ڱ���
        frame.setSize(width, height);    //���ô��ڴ�С
        frame.setVisible(true);//���ô��ڿɼ�
        frame.setLocationRelativeTo(null);//���ô��ھ�����ʾ
        frame.setResizable(false);//���ò��ɵ�����С
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڹر�ʱ�������

        frame.add(jp); //�ڴ��ڼ��뱳�����
        button.addActionListener(new ActionListener() {        //���ӿ�ʼ��Ϸ��ť�¼�����
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                judge = 1;                    //move����ѭ��ģʽ
                panel.reset();        //����reset��������ʼ��panel�����ֵ
                frame.remove(jp);//��ȥ�������
                frame.add(panel);//������Ϸ���
                frame.addMouseMotionListener(panel);//����������
                SwingUtilities.updateComponentTreeUI(frame);//ˢ�´���
            }
        });
        button2.addActionListener(new ActionListener() { //���а�ť�¼�����
            public void actionPerformed(ActionEvent e) {
                try (FileReader fi = new FileReader("range.txt"); //���ļ�
                     BufferedReader bfr = new BufferedReader(fi);//�����ȡ
                ) {
                    String line;
                    range = null;
                    range = new int[100];
                    int i = 0;
                    while ((line = bfr.readLine()) != null) {    //�����ļ�����
                        range[i] = Integer.parseInt(line);
                        i++;
                    }
                    Arrays.sort(range);        //��������
                    RangePanel r = new RangePanel();    //�������а�������
                    JButton buttonBack = new JButton("Back");
                    buttonBack.setBounds(300, 700, 90, 40);
                    buttonBack.addActionListener(new ActionListener() {  //���ӷ��ذ�ť�¼�����
                        public void actionPerformed(ActionEvent e) {
                            frame.remove(r); //��ȥ���а����
                            frame.add(jp);//���뱳�����
                            SwingUtilities.updateComponentTreeUI(frame);//ˢ�´���
                        }
                    });
                    r.add(buttonBack);//���뷵�ذ�ť
                    frame.remove(jp);//��ȥ�������
                    frame.add(r);//�������а����
                    SwingUtilities.updateComponentTreeUI(frame);//ˢ�´���

                } catch (IOException ignored) {

                }
            }
        });


        JButton b3 = new JButton("Again"); //���ð�ť
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


        while (true) {  //����move()����ʹ��
            if (judge == 1) {           //��Ϸģʽ����ʱ����Ѿ������ʼ��Ϸ
                panel.move();  //����move
            } else if (judge == 0) {//��Ϸ����ģʽ�������������ʤ��ͨ����move����ֹͣ����
                judge = 2;
                panel.add(b3);//��������һ�ΰ�ť
                frame.removeMouseMotionListener(panel);
                SwingUtilities.updateComponentTreeUI(frame);
            } else if (judge == 2) {//��ʼģʽ�����Ϊ����κΰ�ť����ʱͣ���ڱ����˵����
                System.out.println("Waiting...");
            }
        }

    }
}
