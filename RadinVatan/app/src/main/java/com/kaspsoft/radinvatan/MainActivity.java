package com.kaspsoft.radinvatan;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener{
    private Button buttonPlayStope;
    private MediaPlayer mediaPlayer;
    private ImageButton imageButton;
    private final Handler handler = new Handler();
    NavigationView navigationView;
    DrawerLayout drawer;
    ProgressBar progressBar;
    final String DATA_STREAM = "http://stream1.radiostyle.ru:8001/radiovatan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        buttonPlayStope = (Button) findViewById(R.id.playAndPause);
        mediaPlayer = new MediaPlayer();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        initViews();
        if (mediaPlayer.isPlaying()) {
            buttonPlayStope.setBackground(ContextCompat.getDrawable(this, R.drawable.pause));
        } else {
            buttonPlayStope.setBackground(ContextCompat.getDrawable(this, R.drawable.play));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
                mediaPlayer.setVolume(0.5f, 0.5f);
                try{
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnErrorListener(this);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.setDataSource(DATA_STREAM);
                    mediaPlayer.prepareAsync();

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
    }
}
    public void playAndStop(View v) {
        if(!mediaPlayer.isPlaying()){
            try{
                mediaPlayer.start();
                buttonPlayStope.setBackground(ContextCompat.getDrawable(this, R.drawable.pause));
            } catch (IllegalStateException e) {
                mediaPlayer.pause();
                buttonPlayStope.setBackground(ContextCompat.getDrawable(this, R.drawable.play));
            }
        } else {
            mediaPlayer.pause();
            buttonPlayStope.setBackground(ContextCompat.getDrawable(this, R.drawable.play));
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.contact) {
            Uri address = Uri.parse("http://radiovatan.ru/contacts");
            Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlinkIntent);
        }
 else if (id == R.id.sentText1) {
            Uri address = Uri.parse("http://radiovatan.ru/feedback");
            Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlinkIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    public void ndShow(View view) {
        drawer.openDrawer(navigationView);
    }

    public void nntShow(View view)
    {
        Uri address = Uri.parse("http://nnttv.ru/");
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
    }
    public void fbShow(View view)
    {
        Uri address = Uri.parse("https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2Fgroups%2Fradiovatan%2F");
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
    }
    public void vkShow(View view)
    {
        Uri address = Uri.parse("https://vk.com/vatanradio");
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
    }
    public void instagramShow(View view)
    {
        Uri address = Uri.parse("https://www.instagram.com/radiovatan/");
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
    }
    public void youtubeShow(View view)
    {
        Uri address = Uri.parse("https://www.youtube.com/channel/UC3LM1K3Z__id9QdIHkAd8Tg");
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
    }
    public void vatanShow(View view)
    {
        Uri address = Uri.parse("http://radiovatan.ru/");
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        buttonPlayStope.setVisibility(View.VISIBLE);
        buttonPlayStope.setEnabled(true);
        progressBar.setVisibility(ProgressBar.GONE);
    }
}
