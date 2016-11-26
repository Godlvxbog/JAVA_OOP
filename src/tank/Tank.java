package tank;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 类：当你考虑完属性之后，你需要考虑的是构造方法，其他的方法
 */
public class Tank {

    int x,y;
    public static final int TANNK_VEL = 10;



    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        //把图形颜色取出来，用完了，你在返回去
        Color color =g.getColor();

        g.setColor(Color.RED);
        g.fillOval(x,y,30,30);//绘

        g.setColor(color);//返回你原来的颜色
    }

    //按键按下，坦克自己有反应，应该坦克自己来，而不是让大关键，军长来开车

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                x -= TANNK_VEL;
                break;
            case KeyEvent.VK_UP:
                y -= TANNK_VEL;
                break;

            case KeyEvent.VK_RIGHT:
                x += TANNK_VEL;
                break;

            case KeyEvent.VK_DOWN:
                y +=TANNK_VEL;
                break;

        }
        System.out.println("输出了key");
    }
}
