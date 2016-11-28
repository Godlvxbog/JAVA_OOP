package tank;

import java.awt.*;

/**
 * 思考：
 * 属性
 * 需要移除当前的missle，在client中的missiles所以你需要引入missile
 */
public class Missile {
    public static final int MISLE_VEL = 40;

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private  TankClinet tc;

    private  int x;
    private int y;

    public boolean isLive() {
        return live;
    }


    private boolean live =true; //添加是否活着的变量，如果死亡就从要绘制的容器中取出来

    private Tank.Direction dir ;//反向，类名叫Tank.Direction
    //构造方法
    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Missile(int x,int y ,Tank.Direction dir,TankClinet tc){
        this(x,y,dir);
        this.tc =tc;
    }


    public void draw(Graphics g){
        if (!live){
            tc.missiles.remove(this);
            return;
        }
        Color color = g.getColor();

        g.setColor(Color.black);
        g.fillRect(x,y,WIDTH,HEIGHT);

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
        //移动完成之后就判断是否出界
        if (x <0 || x >TankClinet.GAME_WIDTH || y <0 ||y >TankClinet.GAME_HEIGHT){
            live = false;
        }
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public boolean hitTank(Tank tank){
        if (this.getRect().intersects(tank.getRect())  && tank.isLive()){//碰撞检测
            tank.setLive(false);
            this.live = false;//当前的子弹，如果tank已经死掉了，那么后面的子弹，将不会被修改live

            return true;
        }
        return false;

    }




}