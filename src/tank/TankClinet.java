package tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 坦克大战
 */
public class TankClinet extends Frame{

    //弹出一个frame
    public TankClinet(){
        this.setLocation(120,20);
        this.setSize(800,600);
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


    }

}
