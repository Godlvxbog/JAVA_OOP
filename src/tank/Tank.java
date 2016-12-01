package tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

/**
 * 类：当你考虑完属性之后，你需要考虑的是构造方法，其他的方法
 * 面向对象强调的是：关于状态的变化而非过程
 */
public class Tank {


    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live = true;

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    private boolean good ;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    TankClinet tc;
    private int x,y;
    //按键是否已经被按下
    private  boolean BL =false;
    private  boolean BU =false;
    private  boolean BR =false;
    private  boolean BD =false;

    private int oldX;
    private int oldY;




    protected enum Direction{
        L,LU,U,RU,R,RD,D,LD,STOP
    }

    private Direction dir =Direction.STOP;
    private Direction ptDir =Direction.D;//炮筒与坦克的方向应该是独立的

    public static final int TANNK_VEL = 20;

    private int step = random.nextInt(8)+2;

    //需要把这里的missile传到tankclient中missile，方法是拿到missile的引
    public Tank(int x,int y,boolean good,Direction dir, TankClinet tc){
        this(x, y,good);
        this.dir = dir;
        this.tc = tc;
    }


    public Tank(int x, int y,boolean good) {
        this.x = x;
        this.y = y;
        this.good =good;
        this.oldX = x;//记录上一步的坐标
        this.oldY = y;
    }

//    随机数产生器有一个就够了

    private static Random random = new Random();

    public void draw(Graphics g){
        if (!live){
            return;
        }
        //把图形颜色取出来，用完了，你在返回去
        Color color =g.getColor();

        if (good ==true){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.ORANGE);
        }
        g.fillOval(x,y,WIDTH,HEIGHT);//绘

        g.setColor(color);//返回你原来的颜色

        drawPt(g);
        move();







    }

    public void drawPt(Graphics g){
        //根据方向来画出不同的炮筒方向

        switch (ptDir) {
            //每次你的顺序都这样就不会出错了
            case L:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT / 2);
                break;
            case LU:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y );

                break;

            case U:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH/2, y );

                break;

            case RU:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x +Tank.WIDTH , y );

                break;

            case R:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x  +Tank.WIDTH , y + Tank.HEIGHT / 2);

                break;

            case RD:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x  +Tank.WIDTH, y + Tank.HEIGHT);

                break;

            case D:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x  +Tank.WIDTH/2, y + Tank.HEIGHT);

                break;

            case LD:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT );
                break;
        }

        if (this.dir != Direction.STOP){
            this.ptDir = this.dir;
        }

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
            case KeyEvent.VK_CONTROL:
                fire();//直接用tc来接收，初始化missle
                System.out.println("ctrl=====");
                break;
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


    public void stay(){
        x= oldX;
        y =oldY;
    }




    //根据方向来移动坦克
    public void move(){
        //每当完成了移动之前就保存上一步的坐标
        this.oldX = x;
        this.oldY = y;

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

        //防止出界问题
        if (x < 0){
            x = 0;
        }
        if (y < 0){
            y = 0;
        }
        if ( x+Tank.WIDTH > TankClinet.GAME_WIDTH){
            x = TankClinet.GAME_WIDTH - Tank.WIDTH;
        }

        if (y + Tank.HEIGHT > TankClinet.GAME_HEIGHT){
            y = TankClinet.GAME_HEIGHT - Tank.HEIGHT;
        }

        //每一栋一步后需要随机产生坦克的方向
        if (!good){
            Direction[] dirs =Direction.values();//把枚举换成数组
            if (step == 0){
                step = random.nextInt(10) +2;
                int randNum = random.nextInt(dirs.length);//随机整数作为索引
                dir = dirs[randNum];
            }
            step --;

            if (random.nextInt(40) >30){
                this.fire();
            }

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

    //坦克打出来一发子弹
    public Missile fire(){
        if (!live ){
            return null;
        }

        int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
        int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
        Missile misle = new Missile(x,y,good, ptDir,this.tc);//把大管家tc传给misile
        tc.missiles.add(misle);
        return misle;
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public boolean collidesWall(Wall wall){
        if (live && getRect().intersects(wall.getRect())){
            this.stay();
            return true;
        }
        return false;
    }

    public boolean collidesWalls(List<Wall> walls){
        for (Wall wall : walls){
            if (collidesWall(wall)){
                return true;
            }
        }
        return false;
    }


    //写和坦克的相撞
    public boolean collidesTank(Tank tank){
        if (live && getRect().intersects(tank.getRect()) && tank.live){
            this.stay();
            tank.stay();
            return true;
        }
        return false;
    }

    public boolean collideTanks(List<Tank> tanks){
        for (Tank tank : tanks){
            if (this != tank){//不是同一辆坦克才检测相撞

                tank.collidesTank(this);
                return true;
            }
        }
        return false;
    }

}
