package tank;

import java.awt.*;

/**
 * Created by Administrator on 2016/12/1.
 */
public class Wall {
    private int x;
    private int y;

    private static final int WIDTH =20;
    private static final int HEIGHT =300;

    private TankClinet tc;

    public Wall(int x,int y,TankClinet tc){
        this.x = x;
        this.y =y;
        this.tc =tc ;
    }


    public void draw(Graphics g){
        Color color =  g.getColor();

        g.setColor(Color.magenta);
        g.fillRect(x,y,WIDTH,HEIGHT);

    }

    public Rectangle getRect(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }


}
