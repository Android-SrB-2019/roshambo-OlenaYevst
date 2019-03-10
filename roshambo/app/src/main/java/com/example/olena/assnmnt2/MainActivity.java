package com.example.olena.assnmnt2;

//This assignment was done by Olena Y
//Date: 2019-03-07

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private ImageView playerChoice;
    private ImageView gameChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = (TextView)findViewById(R.id.result_textView);
        playerChoice = (ImageView)findViewById(R.id.your_move_imageView);
        gameChoice = (ImageView)findViewById(R.id.game_move_imageView5);
    }

    /**
     * This method is called once any of ImageViews with three options get clicked
     * @param view
     */
    public void makeMove(View view) {
        String choice1 = (String)view.getTag();

        Rochambo rochambo = new Rochambo();
        rochambo.playerMakesMove(Integer.valueOf(choice1));
        Integer result = rochambo.winLoseOrDraw();
        resultText.setText(result);

        //show player`s and game`s choice
        Context ctx = getApplicationContext();
        Drawable d1 = getImage(ctx, convertToString(Integer.valueOf(choice1)));
        playerChoice.setImageDrawable(d1);
        Integer choice2 = rochambo.getGameMove();
        Drawable d2 = getImage(ctx, convertToString(choice2));
        gameChoice.setImageDrawable(d2);

        //set up animation for player`s and game`s choices
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(playerChoice,
                "rotationX", 0f, 360f)
                .setDuration(500);
        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(gameChoice,
                "rotationY", 0f, 360f)
                .setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();

    }

    /**
     * This method returns one of three choices (rock, paper or scissors) based on the given integer
     * @param i
     * @returns string
     */
    private String convertToString(Integer i){
        String response = "";
        switch(i){
            case 0:
                response = "rock";
                break;
            case 1:
                response = "paper";
                break;
            case 2:
                response = "scissor";
                break;
            case 3:
                response = "none";
                break;
        }
        return response;
    }

    /**
     * This method finds image using imageName as a parameter
     * @param c
     * @param imageName
     * @return
     */
    public static Drawable getImage(Context c, String imageName) {
        return ContextCompat.getDrawable(c, c.getResources().getIdentifier(imageName, "drawable", c.getPackageName()));
    }

}
