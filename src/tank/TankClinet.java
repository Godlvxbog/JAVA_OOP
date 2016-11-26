package tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 坦克大战
 */
public class TankClinet extends Frame{

    //要你很多次修改尺寸时候，所以你定义一个容易扩展维护的const常量
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    int x = 50,y = 50;//左上角的位置


    Image offsetImage = null;

    //此方法默认会被调动，一段需要重新绘制的话
    public void paint(Graphics g) {
        //把图形颜色取出来，用完了，你在返回去
        Color color =g.getColor();

        g.setColor(Color.RED);
        g.fillOval(x,y,30,30);//绘

        g.setColor(color);//返回你原来的颜色
        System.out.println("paintMethod");

        //每次重绘就y + = 5；

        y += 5;

    }

    @Override
    public void update(Graphics g) {//这个画笔就是原来paint的画笔,update截住
        if (offsetImage == null){
            offsetImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);//所有的重新画在这个图片
        }

        Graphics gOff = offsetImage.getGraphics();//拿到这个了

        Color color = gOff.getColor();
        //绘制背景图
        gOff.setColor(Color.GREEN);
        gOff.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOff.setColor(color);

        //绘制圆
        paint(gOff);

        g.drawImage(offsetImage,0,0,null);//把后面的图片画在原来的图片中

    }

    //弹出一个frame
    public void launchFrame(){
        this.setLocation(400,300);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);

        this.setBackground(Color.GREEN);

        this.setTitle("Tank_War");
        //不让改变大小
        this.setResizable(false);
        //window的函数
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                System.out.println(e);
            }
        });

        this.setVisible(true);

        //窗口起来就启动这个线程
        new Thread(new PaintThread()).start();

    }

    //内部类，重新绘制的线程
   public class PaintThread  implements Runnable{

        @Override
        public void run() {
            while (true){
                repaint();//调用包装类的repaint，如果没有，就是去其父类去找

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        TankClinet tankClinet = new TankClinet();
        tankClinet.launchFrame();

    }

}
