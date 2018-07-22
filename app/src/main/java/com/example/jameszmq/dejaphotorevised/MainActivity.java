package com.example.jameszmq.dejaphotorevised;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private CheckBox disp_user, disp_friend, mode_loc, mode_time;
    private Switch share_swi, dejavu_swi;
    private Spinner album_choose;
    private EditText slide_time;

    public static final String SHARED_PREF = "DejaPhoto_shared_pref";
    public static final String FIRST_TIME = "first_time";
    public static final String DISP_USER = "display_user";
    public static final String DISP_FRIEND = "display_friend";
    public static final String MODE_LOC = "mode_location";
    public static final String MODE_TIME = "mode_time";
    public static final String SHARE_SWI = "share_switch";
    public static final String DEJAVU_SWI = "Dejavu_switch";
    public static final String ALBUM_CHOOSE = "album_choose";
    public static final String SLIDE_TIME = "slide_time";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: First time initialization
        firstTimeCheck();
        // TODO: Initilize UI
        initializeUI();
        // TODO: Permission
        // TODO: Initilize objects
    }

    private void firstTimeCheck() {

        // Initialize SharedPreference variables
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        if (!sharedPreferences.contains(FIRST_TIME)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(FIRST_TIME, false);
            editor.putBoolean(DISP_USER, true);
            editor.putBoolean(DISP_FRIEND, false);
            editor.putBoolean(MODE_LOC, false);
            editor.putBoolean(MODE_TIME, false);
            editor.putBoolean(SHARE_SWI, false);
            editor.putBoolean(DEJAVU_SWI, false);
            editor.putInt(ALBUM_CHOOSE, 0);
            editor.putInt(SLIDE_TIME, 300);
            editor.commit();
        }

    }

    public void initializeUI() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        disp_user = (CheckBox) findViewById(R.id.user_id);
        disp_user.setChecked(sharedPreferences.getBoolean(DISP_USER, false));
        disp_user.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        disp_friend = (CheckBox) findViewById(R.id.friend_id);
        disp_friend.setChecked(sharedPreferences.getBoolean(DISP_FRIEND, false));

        mode_loc = (CheckBox) findViewById(R.id.locationCheck);
        mode_loc.setChecked(sharedPreferences.getBoolean(MODE_LOC, false));

        mode_time = (CheckBox) findViewById(R.id.timeCheck);
        mode_time.setChecked(sharedPreferences.getBoolean(MODE_TIME, false));

        share_swi = (Switch) findViewById(R.id.share_switch);
        share_swi.setChecked(sharedPreferences.getBoolean(SHARE_SWI, false));

        dejavu_swi = (Switch) findViewById(R.id.dejavu_switch);
        dejavu_swi.setChecked(sharedPreferences.getBoolean(DEJAVU_SWI, false));

        slide_time = (EditText) findViewById(R.id.each_edit);
        slide_time.setText(Integer.toString(sharedPreferences.getInt(SLIDE_TIME, 300)));

    }

    public void setWallpaper(View v) {

    }

    public void restoreWallpaper(View v) {

    }

}