package stoper;

import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Stoper {
        
        private static final int WAIT_LENGTH = 10;
        boolean check = false;
        double start;
        double suspend;
        double lastLap;
        String lap;
        int lapCounter = 1;
        long pauseStart;
        long pauseEnd;
        long pauseTime;
        double pauseTimeSum = 0;
        boolean pauseCheck = false;
        String[] lapTab = new String[100];
        
        public void Stopwatch() {
            start = System.currentTimeMillis();
        }
        
        public String eTime() {
            DecimalFormat g = new DecimalFormat("#####0.000");
            long now = System.currentTimeMillis();
            double now1 = ((now - start) - pauseTimeSum ) / 1000.0;
            String now2 = g.format(now1);
            lap = now2;
            suspend = now1;
            return now2;
        }
        
        class ImagePanel extends JComponent {
            private Image image;
            public ImagePanel(Image image) {
                this.image = image;
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        } 
        
        public static void main(String[] args) throws IOException {
         new Stoper();
         System.out.println("Program started");
    }
        
    Stoper() throws IOException {
        
        int frameWidth=400;
        int frameHeight=395;
        ImageIcon img = new ImageIcon("pix.png");
        
        JFrame frame = new JFrame("Stoper");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setIconImage(img.getImage());
        
        Container cont = frame.getContentPane();
       
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(109, 132, 180));
        p1.setLayout(new GridLayout(0,2));
        p1.setSize(frameWidth, frameHeight);
        p1.setLayout(new GridLayout());
        p1.setBorder(BorderFactory.createEmptyBorder(40, 27, 20, 20));
        
        JPanel p2 = new JPanel();
        p2.setBackground(new Color(109, 132, 180));
        p2.setLayout(new GridLayout(0,2));
        p2.setSize(frameWidth, frameHeight);
        p2.setLayout(new GridLayout());
        p2.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        
        JPanel p3 = new JPanel();
        p3.setBackground(new Color(109, 132, 180));
        p3.setLayout(new GridLayout(0,2));
        p3.setSize(frameWidth, frameHeight);
        p3.setLayout(new GridLayout());
        p3.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JPanel p4 = new JPanel();
        p4.setBackground(new Color(109, 132, 180));
        p4.setLayout(new GridLayout(2,2));
        p4.setSize(frameWidth, frameHeight);
        p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
        p4.setBorder(BorderFactory.createEmptyBorder(0, 27, 20, 20));
        
        JPanel p5 = new JPanel();
        p5.setBackground(new Color(109, 132, 180));
        p5.setSize(frameWidth, frameHeight);
        p5.setLayout(new BorderLayout());
        p5.setBorder(BorderFactory.createEmptyBorder(0, 5, 2, 0));
        p5.setPreferredSize(p5.getPreferredSize());
        
        
        JScrollPane jsp = new JScrollPane(p4);
        jsp.setBorder(null);
        JScrollBar vertical = jsp.getVerticalScrollBar();
        
        JLabel label1 = new JLabel("Czas: ");
        label1.setForeground(Color.white);
        label1.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        
        JLabel label2 = new JLabel("0.0");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        JLabel label3 = new JLabel("Created by Łukasz Dyduch, Copyright © 2017");
        label3.setForeground(Color.white);
        label3.setFont(new Font("Tahoma", Font.ITALIC, 9));
        label3.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        label3.setPreferredSize(new Dimension(300,10));
        
        class LabelUpdater extends Thread {
            @Override
            public void run() {
                while(true) {
                    try {
                        if (check) {
                            label2.setText(eTime()+" s");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    finally {
                        frame.repaint();
                        try {
                            Thread.sleep(WAIT_LENGTH);
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        }
        LabelUpdater t = new LabelUpdater();
        t.start();
        
        JButton b1 = new JButton("Start");
        JButton b2 = new JButton("Pause");
        b1.setPreferredSize(new Dimension(120, 50));
        b1.setBackground(new Color(59, 89, 182));
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false);
        b1.setFont(new Font("Tahoma", Font.BOLD, 12));
        b1.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pauseTimeSum = 0;
                check = true;
                t.resume();
                Stopwatch();
                p4.removeAll();
                lapCounter = 1;
                lastLap = 0;
                for (int i = 0;i<lapTab.length;i++) {
                    lapTab[i] = null;
                }
                b2.setText("Pause");
                System.out.println("StopWatch started");
            }
        });
        
        b2.setPreferredSize(new Dimension(160, 50));
        b2.setBackground(new Color(59, 89, 182));
        b2.setForeground(Color.WHITE);
        b2.setFocusPainted(false);
        b2.setFont(new Font("Tahoma", Font.BOLD, 12));
        b2.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t.suspend();
                if ("Pause".equals(b2.getText())) {
                    pauseCheck = true;
                    pauseStart = System.currentTimeMillis();
                    System.out.println("StopWatch paused");
                    System.out.println("pauseStart "+pauseStart);
                    b2.setText("Wznów");
                }    
                else {
                    pauseCheck = false;
                    b2.setText("Pause");
                    pauseEnd = System.currentTimeMillis();
                    pauseTime = (pauseEnd - pauseStart);
                    pauseTimeSum += pauseTime;
                    System.out.println("pauseEnd   "+pauseEnd);
                    System.out.println("pauseTime  "+pauseTime);
                    System.out.println("StopWatch unpaused");
                    t.resume();
                }
            }
        });
        
        JButton b4 = new JButton("Okrążenie");
        b4.setPreferredSize(new Dimension(160, 50));
        b4.setBackground(new Color(59, 89, 182));
        b4.setForeground(Color.WHITE);
        b4.setFocusPainted(false);
        b4.setFont(new Font("Tahoma", Font.BOLD, 12));
        b4.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    double diff = suspend-lastLap;
                    DecimalFormat f = new DecimalFormat("#####0.000");
                    JLabel c = new JLabel();
                    if (lapCounter == 1) {
                        c.setText("Okrązenie "+lapCounter+" = "+lap);
                        lapTab[lapCounter-1] = lap;
                        System.out.println("Lap "+lapCounter+" saved");
                        lastLap = suspend;
                    }    
                    else {
                        c.setText("Okrązenie "+lapCounter+" = "+lap+" (+"+f.format(diff)+")");
                        lapTab[lapCounter-1] = lap;
                        System.out.println("Lap "+lapCounter+" saved");
                        
                    }  
                    c.setForeground(Color.white);
                    c.setFont(new Font("Tahoma", Font.BOLD, 12));
                    vertical.setValue( vertical.getMaximum() );
                    p4.add(c);
                    lapCounter++;
                    lastLap = suspend;
                    
                    int lapTabIndex = 0;
                    if (lapCounter%11 == 0) {
                        while (lapTabIndex<lapCounter-1) {
                            System.out.println("Lap "+(lapTabIndex+1)+" : "+lapTab[lapTabIndex]);
                            lapTabIndex++;
                        }
                    }
                }
            }
        });
       
        p2.add(b1);
        p2.add(b2);
        p3.add(b4);
        p1.add(label1);
        p1.add(label2); 
        p5.add(label3, BorderLayout.SOUTH);
        
        cont.setLayout( new GridLayout(5,1) );
        cont.add(p1);  
        cont.add(p2);
        cont.add(p3);
        cont.add(jsp);
        cont.add(p5);
        
        frame.show();
    }  
}
