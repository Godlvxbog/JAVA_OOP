package tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 坦克大战
 */
public class TankClinet extends Frame{

    //此方法默认会被调动，一段需要重新绘制的话
    public void paint(Graphics g) {
        //把图形颜色取出来，用完了，你在返回去
        Color color =g.getColor();

        g.setColor(Color.RED);
        g.fillOval(30,50,30,30);//绘

        g.setColor(color);//返回你原来的颜色
        System.out.println("paintMethod");



    }

    //弹出一个frame
    public void launchFrame(){
        this.setLocation(400,300);
        this.setSize(800,600);

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
    }

    public static void main(String[] args) {
        TankClinet tankClinet = new TankClinet();
        tankClinet.launchFrame();



    }

}
