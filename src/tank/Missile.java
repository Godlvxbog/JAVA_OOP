package tank;

import java.awt.*;
import java.util.List;

/**
 * 思考：
 * 属性
 * 需要移除当前的missle，在client中的missiles所以你需要引入missile
 */
public class Missile {
    public static final int MISLE_VEL = 20;

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private  TankClinet tc;

    private  int x;
    private int y;
    private boolean good;

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

    public Missile(int x,int y ,boolean good, Tank.Direction dir,TankClinet tc){
        this(x,y,dir);
        this.good = good;
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
            tc.missiles.remove(this);//移除自己
        }
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }



    public boolean hitTank(Tank tank){
        if (this.live && getRect().intersects(tank.getRect()) && tank.isLive() && this.good != tank.isGood()){
            if (tank.isGood()){
                //先去掉20
                int life = tank.getLife();
                life -= 20;
                tank.setLife(life);

                if (tank.getLife() <=0){
                    tank.setLive(false);
                }
            }

            this.live = false;
            //增加爆炸
            Explode explode =new Explode(x,y,tc);
            tc.explodes.add(explode);

            return true;//打中了
        }
        return false;
    }

    //打击多个坦克
    public boolean hitTanks(List<Tank> tanks){
        for (int i =0 ;i<tanks.size();i++){
            if (hitTank(tanks.get(i))){//打中了这个坦克
               return true;
            }
        }
        return false;
    }

    public boolean hitWall(Wall wall){
        if (live && getRect().intersects(wall.getRect())){
            this.live =false;
            return true;
        }
        return false;
    }

    public boolean hitWalls(List<Wall> walls){
        for (Wall wall : walls){
            if (hitWall(wall)){
                return true;
            }
        }
        return false;
    }




}
