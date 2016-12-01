package tank;

import java.awt.*;

/**
 * Created by Administrator on 2016/12/1.
 */
public class Blood {

    private int x,y,WIDTH =20,HEIGHT=20;
    TankClinet tc;
    int step = 0;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live =true;

    //血块运动的轨迹有pos中各个点组成
    private int[][] pos={
        {350,300},{360,300},{375,275},{400,200},{360,270},{365,290}

    };

    public Blood(){
        x= pos[0][0];
        y= pos[0][1];
    }

    public void draw(Graphics g){
        if (!live){
            return;
        }

        Color color =g.getColor();
        g.setColor(Color.lightGray);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(color);

        //画完之后就move一次
        move();
    }

    private void move() {
        step ++;
        if (step == pos.length){
            step = 0;
        }
        x= pos[step][0];
        y= pos[step][1];
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
