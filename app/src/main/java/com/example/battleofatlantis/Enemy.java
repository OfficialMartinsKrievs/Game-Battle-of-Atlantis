package com.example.battleofatlantis;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import static com.example.battleofatlantis.GameView.screenRatioX;
import static com.example.battleofatlantis.GameView.screenRatioY;

public class Enemy
{
    int x,y,width, height, HP,MP, ATT,DEF;
    Bitmap AlvisEnemy;
    public Bitmap AlvisHealth;
    int healthWidth,healthHeight;

    Enemy(int screnX, int screenY, Resources res)
    {
        HP = 300;
        MP = 50;
        ATT = 10;
        DEF = 30;

        AlvisEnemy = BitmapFactory.decodeResource(res, R.drawable.enemy);
        width = AlvisEnemy.getWidth();
        height = AlvisEnemy.getHeight();

        AlvisHealth = BitmapFactory.decodeResource(res, R.drawable.enemyhealth);
        healthWidth = AlvisHealth.getWidth() * HP /10;
        healthHeight = AlvisHealth.getHeight();


        width *= (int)screenRatioX;
        height *= (int)screenRatioY;






        x = screnX / 4;
        y = screenY / 4;

    }

    Bitmap getEnemy()
    {
        return AlvisEnemy;
    }

    int EnemyAttack(int playerDef)
    {
        return ATT*(100/(100+playerDef));
    }

    void EnemyGuard()
    {
        HP+=10;
        MP-=5;
        ATT +=4;
        DEF +=5;
    }


    void TakeDamage(int playerATT)
    {
        HP -= 10;
    }



}
