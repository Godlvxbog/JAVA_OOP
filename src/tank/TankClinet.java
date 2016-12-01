package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * 坦克大战
 */
public class TankClinet extends Frame{

    //要你很多次修改尺寸时候，所以你定义一个容易扩展维护的const常量
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    Tank myTank = new Tank(50,50,true, Tank.Direction.STOP, this);

//    Missile missile = null;//初始化是在坦克按下ctrl之后才初始化
    List<Missile> missiles =new ArrayList<Missile>();

    List<Explode> explodes =new ArrayList<Explode>();

    List<Tank> enemyTanks =new ArrayList<Tank>();

    List<Wall> walls =new ArrayList<Wall>();








    Image offsetImage = null;

    //此方法默认会被调动，一段需要重新绘制的话;每隔50ms条用一次
    public void paint(Graphics g) {
        g.drawString("Missiles:" + missiles.size(), 60, 40);
        g.drawString("explodes:" + explodes.size(), 60, 60);
        //这个大管家来调用其自己的方法
       for (int i = 0; i<walls.size() ; i++){
           Wall wall = walls.get(i);
           wall.draw(g);
        }

        for (int i = 0 ;i<missiles.size();i++) {
            Missile missile =missiles.get(i);
            missile.hitTanks(enemyTanks);//枚举所有的missile，打击每一个坦克
            missile.hitTank(myTank);
            missile.hitWalls(walls);
            missile.draw(g);
        }

        for (int i = 0; i<explodes.size();i++){
            Explode explode = explodes.get(i);
            explode.draw(g);
        }

        for (int i = 0;i<enemyTanks.size();i++){
            Tank enemy =enemyTanks.get(i);
            enemy.collidesWalls(walls);
            enemy.collideTanks(enemyTanks);
            enemy.collidesTank(myTank);
            enemy.draw(g);

        }

        myTank.draw(g);

    }

    @Override
    public void update(Graphics g) {//这个画笔就是原来paint的画笔,update截住
        if (offsetImage == null){
            offsetImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);//所有的重新画在这个图片
        }

        Graphics gOff = offsetImage.getGraphics();//拿到这个了

        Color color = gOff.getColor();
        //绘制背景图
        gOff.setColor(Color.BLUE);
        gOff.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOff.setColor(color);

        //绘制圆
        paint(gOff);

        g.drawImage(offsetImage,0,0,null);//把后面的图片画在原来的图片中

    }

    public void addTank(int tankNum){
        for (int i = 0 ;i< tankNum;i++ ){
            Tank enemy =new Tank(50 +40*(i+1),100,false, Tank.Direction.D, this);
            enemyTanks.add(enemy);
        }
    }

    public void addWalls(int wallNum){
        for (int i = 0 ;i < wallNum ; i++){
            Wall wall =new Wall(200+ i*400,200,this);
            walls.add(wall);
        }
    }

    //弹出一个frame
    public void launchFrame(){
        addTank(10);
        addWalls(2);

        this.setLocation(400,300);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);

        this.setBackground(Color.BLUE);

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

        this.addKeyListener(new KeyMonitor());

        this.setVisible(true);

        //窗口起来就启动这个线程
        new Thread(new PaintThread()).start();

    }

    //内部类，重新绘制的线程
   public class PaintThread  implements Runnable{

        @Override
        public void run() {
            while (true){
                repaint();//调用包装类的repaint，如果没有，就是去其父类去找

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //实现监听，这里不用实现KeyListener接口，而是继承KeyAdapter，因为不用实现其所有的方法
    //只需要复写你需要的方法即可
    //一旦触发就调用
    public class KeyMonitor extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        TankClinet tankClinet = new TankClinet();
        tankClinet.launchFrame();



    }

}
