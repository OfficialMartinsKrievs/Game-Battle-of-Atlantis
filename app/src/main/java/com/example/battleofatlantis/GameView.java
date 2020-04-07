package com.example.battleofatlantis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.Random;


public class GameView extends SurfaceView implements Runnable
{
    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    static float screenRatioX, screenRatioY;
    private Paint paint;
    private Background background;
    private Player player;
    private Enemy Alvis;
    int turn = 0;


    public GameView(Context context, int screenX, int screenY)
    {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        screenRatioY = 1920f / screenY;
        screenRatioX = 1080f / screenX;



        background = new Background(screenX,screenY, getResources());
        player = new Player(screenX,screenY,getResources());
        Alvis = new Enemy(screenX,screenY,getResources());

        paint = new Paint();
        paint.setTextSize(70);
        paint.setColor(Color.WHITE);

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.battle);
        mediaPlayer.start();



    }

    @Override
    public void run()
    {
        while (isPlaying)
        {
            Update();
            Draw();
            Sleep();
        }
    }
    private void Update()
    {
        if(turn == 1)
        {
            Random random = new Random();
            if(random.nextInt() %2 != 0)
            {
                Alvis.EnemyGuard();
                turn = 0;
            }
            else
            {
                player.HP -=(random.nextInt() % 20);
                turn = 0;

            }
        }
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        int x = (int)event.getX();  // or getRawX();
        int y = (int)event.getY();

        switch(action)
        {
            case MotionEvent.ACTION_DOWN:
                if (x >= player.x1 && x < (player.x1 + player.width1)
                        && y >= player.y1 && y < (player.y1 + player.height1) && turn == 0)
                {
                    Alvis.TakeDamage(player.ATT);
                    turn = 1;
                }
                else if (x >= player.x2 && x < (player.x2 + player.width2)
                        && y >= player.y2 && y < (player.y2 + player.height2) && turn == 0)
                {
                    player.PlayerGuard();
                    turn = 1;
                }
                break;
        }
        return true;
    }




    private void Draw()
    {
        Canvas canvas = getHolder().lockCanvas();
        if(getHolder().getSurface().isValid()) {



            canvas.drawBitmap(background.background, background.x, background.y, paint);
            canvas.drawBitmap(Alvis.getEnemy(), Alvis.x, Alvis.y, paint);

            canvas.drawBitmap(player.getAttack(), player.x1, player.y1, paint);
            canvas.drawBitmap(player.getGuard(), player.x2, player.y2, paint);

        //  canvas.drawBitmap(player.PlayerHealth, 100, 70, paint);
        //  canvas.drawBitmap(Alvis.AlvisHealth, 950, 70, paint);

            canvas.drawText( "  Player HP" + player.HP + " vs Enemy HP" + Alvis.HP, 10,200, paint);


            getHolder().unlockCanvasAndPost(canvas);
        }


    }



    private void Sleep()
    {
        try
        {
            thread.sleep(17);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void resume()
    {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause()
    {
        try
        {
            isPlaying = false;
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
