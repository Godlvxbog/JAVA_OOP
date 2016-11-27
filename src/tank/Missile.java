package tank;

import java.awt.*;

/**
 * 思考：
 * 属性
 */
public class Missile {
    public static final int MISLE_VEL = 40;

    private  int x;
    private int y;

    private Tank.Direction dir ;//反向，类名叫Tank.Direction
    //构造方法
    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void draw(Graphics g){
        Color color = g.getColor();

        g.setColor(Color.black);
        g.fillRect(x,y,10,10);

        g.setColor(color);
        move();
    }

    private void move() {

        switch (dir) {
            //每次你的顺序都这样就不会出错了
            case L:
                x -= MISLE_VEL;
                break;
            case LU:
                x -= MISLE_VEL;
                y -= MISLE_VEL;
                break;

            case U:
                y -= MISLE_VEL;
                break;

            case RU:
                x += MISLE_VEL;
                y -= MISLE_VEL;
                break;

            case R:
                x += MISLE_VEL;
                break;

            case RD:
                x += MISLE_VEL;
                y += MISLE_VEL;
                break;

            case D:
                y += MISLE_VEL;
                break;

            case LD:
                x -= MISLE_VEL;
                y += MISLE_VEL;
                break;

        }
    }


}
