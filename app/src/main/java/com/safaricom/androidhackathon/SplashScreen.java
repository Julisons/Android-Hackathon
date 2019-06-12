package com.safaricom.androidhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import static com.safaricom.util.Global.setStatusBarColorTransparent;


public class SplashScreen extends AppCompatActivity {

    public static final int STARTUP_DELAY = 1000;
    public static final int ANIM_ITEM_DURATION = 2000;
    public static final int ITEM_DELAY = 500;
    private boolean animationStarted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /**
         *
         * Static method setStatusBarColorTransparent to give status bar transparency
         *
         */
        setStatusBarColorTransparent(this);

    }
    /**
     *
     * onWindowFocusChanged timing window focus
     *
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus || animationStarted) {
            return;
        }
        animate();

        super.onWindowFocusChanged(hasFocus);
    }

    /**
     * Method animate responsible smooth for UI timer update
     * {@link SplashScreen#animate()}.
     */
    private void animate() {
        ImageView logoImageView =  findViewById(R.id.img_logo);
        ViewGroup container = findViewById(R.id.container);

        ViewCompat.animate(logoImageView)
                .translationY(-50)
                .setStartDelay(STARTUP_DELAY)
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        /**
                         *
                         * Start MainActivity after end of animation
                         *
                         */
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);

            ViewPropertyAnimatorCompat viewAnimator;

            if (!(v instanceof Button)) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(1000);
            } else {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(500);
            }

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
    }
}
