package tank;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 类：当你考虑完属性之后，你需要考虑的是构造方法，其他的方法
 */
public class Tank {

    private int x,y;
    //按键是否已经被按下
    private  boolean BL =false;
    private  boolean BU =false;
    private  boolean BR =false;
    private  boolean BD =false;


    private enum Direction{
        L,LU,U,RU,R,RD,D,LD,STOP
    }

    private Direction dir =Direction.STOP;

    public static final int TANNK_VEL = 20;




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

        move();
    }

    //按键按下，坦克自己有反应，应该坦克自己来，而不是让大关键，军长来开车
    //按键之后，会把所按键转换成dir
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                BL = true;
                break;
            case KeyEvent.VK_UP:
                BU = true;
                break;

            case KeyEvent.VK_RIGHT:
                BR = true;
                break;

            case KeyEvent.VK_DOWN:
                BD = true;
                break;

        }
        //按下键之后重新定义坦克的方向
        locateDirection();
        System.out.println("输出了key");
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                BL = false;
                break;
            case KeyEvent.VK_UP:
                BU = false;
                break;

            case KeyEvent.VK_RIGHT:
                BR = false;
                break;

            case KeyEvent.VK_DOWN:
                BD = false;
                break;

        }
        //按下键之后重新定义坦克的方向
        locateDirection();
    }






    //根据方向来移动坦克
    public void move(){
        switch (dir){
            //每次你的顺序都这样就不会出错了
            case L:
                x -=TANNK_VEL;
                break;
            case LU:
                x -=TANNK_VEL;
                y -=TANNK_VEL;
                break;

            case U:
                y -= TANNK_VEL;
                break;

            case RU:
                x += TANNK_VEL;
                y -=TANNK_VEL;
                break;

            case R:
                x += TANNK_VEL;
                break;

            case RD:
                x +=TANNK_VEL;
                y += TANNK_VEL;
                break;

            case D:
                y +=TANNK_VEL;
                break;

            case LD:
                x -=TANNK_VEL;
                y +=TANNK_VEL;
                break;

            case STOP:
                break;
        }
    }

    //通过按键来得到方向dir
    public void locateDirection(){
        if (BL && !BU && !BR && !BD) dir = Direction.L;
        else if (BL && BU && !BR && !BD) dir = Direction.LU;
        else if (!BL && BU && !BR && !BD) dir = Direction.U;
        else if (!BL && BU && BR && !BD) dir = Direction.RU;
        else if (!BL && !BU && BR && !BD) dir = Direction.R;
        else if (!BL && !BU && BR && BD) dir = Direction.RD;
        else if (!BL && !BU && !BR && BD) dir = Direction.D;
        else if (BL && !BU && !BR && BD) dir = Direction.LD;
        else if (!BL && !BU && !BR && !BD) dir = Direction.STOP;


    }
}
