package com.example.battleofatlantis;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player
{
    int x1,y1,width1, height1,x2,y2,width2, height2, HP,MP, ATT,DEF;
    Bitmap AttackButton, GuardButton;
    public Bitmap PlayerHealth;
    int healthWidth, healthHeight;

    Player(int screnX, int screenY, Resources res)
    {
        HP = 300;
        MP = 50;
        ATT = 10;
        DEF = 30;

        AttackButton = BitmapFactory.decodeResource(res, R.drawable.attackbutton);
        width1 = AttackButton.getWidth();
        height1 = AttackButton.getHeight();

        GuardButton = BitmapFactory.decodeResource(res, R.drawable.guardbutton);
        PlayerHealth = BitmapFactory.decodeResource(res, R.drawable.playerhealth);
        healthWidth = PlayerHealth.getWidth();
        healthHeight = PlayerHealth.getHeight();
        width2 = GuardButton.getWidth();
        height2 = GuardButton.getHeight();

        AttackButton = Bitmap.createScaledBitmap(AttackButton,width1,height1,false);
        GuardButton = Bitmap.createScaledBitmap(GuardButton,width2,height2,false);
        PlayerHealth = Bitmap.createScaledBitmap(PlayerHealth,healthWidth,healthHeight,false);


        x1 = screnX / 2;
        y1 = screenY / 2;

        x2 = screnX / 4;
        y2 = screenY / 2;


    }

    Bitmap getAttack()
    {
        return AttackButton;
    }


    Bitmap getGuard()
    {
        return GuardButton;
    }


    void TakeAttack(int AttackPower)
    {
        HP-=AttackPower;
    }

    void PlayerGuard()
    {
        HP+=10;
        MP-=5;
        ATT +=4;
        DEF +=5;
    }

    int DealDamage()
    {
        return ATT;
    }

}
