package tank;

import java.awt.*;

/**
 * Created by Administrator on 2016/11/28.
 */
public class Explode {


    //每个部分都持有大管家的引用
    private TankClinet tc;
    int x,y;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live = true;
    //多个圆在同一个位置显示出来
    int[] diameter = {4,7,12,18,26,32,49,30,14,6};

    int step = 0;//当前画到的步数

    public Explode(int x, int y ,TankClinet tc){
        this.x= x;
        this.y =y;
        this.tc =tc;
    }

    public void draw(Graphics g){
        if (!live){
            tc.explodes.remove(this);//移除掉
            return;
        }
        //已经画完了
        if (step == diameter.length){
            live = false;
            step =0;
            return;
        }

        Color color = g.getColor();

        g.setColor(Color.BLACK);

        g.fillOval(x,y,diameter[step],diameter[step]);

        step++;

        g.setColor(color);
    }


}
