package com.example.jameszmq.dejaphotorevised;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private CheckBox disp_user, disp_friend, mode_loc, mode_time;
    private Switch share_swi, dejavu_swi;
    private Spinner album_choose;
    private EditText slide_time;
    private boolean login = false;
    private TextView friend_text, share_title, loc_text;

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
            editor.putString(SLIDE_TIME, "300");
            editor.commit();
        }

    }

    public void initializeUI() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        friend_text = (TextView) findViewById(R.id.friend_text);
        share_title = (TextView) findViewById(R.id.share_text);
        loc_text = (TextView) findViewById(R.id.location_text);

        disp_user = (CheckBox) findViewById(R.id.user_id);
        disp_user.setChecked(sharedPreferences.getBoolean(DISP_USER, false));
        disp_user.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // TODO: Add user photos to queue
                }
                else if (!disp_friend.isChecked()) {
                    // TODO: Prompt to user, if cancel stay checked, otherwise restore wallpaper
                }
                else {
                    // TODO: Remove user photos from queue
                }
            }
        });

        disp_friend = (CheckBox) findViewById(R.id.friend_id);
        disp_friend.setChecked(sharedPreferences.getBoolean(DISP_FRIEND, false));
        disp_friend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // TODO: Add friend photos to queue
                }
                else if (!disp_user.isChecked()) {
                    // TODO: Prompt to user, if cancel stay checked, otherwise restore wallpaper
                }
                else {
                    // TODO: Remove friend photos from queue
                }
            }
        });

        mode_loc = (CheckBox) findViewById(R.id.locationCheck);
        mode_loc.setChecked(sharedPreferences.getBoolean(MODE_LOC, false));
        mode_loc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // TODO: Check location service permission
                    // TODO: Add location to score calculation
                }
                else {
                    // TODO: Remove location from score calculation
                }
            }
        });

        mode_time = (CheckBox) findViewById(R.id.timeCheck);
        mode_time.setChecked(sharedPreferences.getBoolean(MODE_TIME, false));
        mode_time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // TODO: Add time to score calculation
                }
                else {
                    // TODO: Remove time from score calculation
                }
            }
        });

        share_swi = (Switch) findViewById(R.id.share_switch);
        share_swi.setChecked(sharedPreferences.getBoolean(SHARE_SWI, false));
        share_swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // TODO: Upload photos to database and update friends
                }
                else {
                    // TODO: Remove photos from database and update friends
                }
            }
        });

        dejavu_swi = (Switch) findViewById(R.id.dejavu_switch);
        dejavu_swi.setChecked(sharedPreferences.getBoolean(DEJAVU_SWI, false));
        dejavu_swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // TODO: Stop Dejavu calculation
                }
                else {
                    // TODO: Enable Dejavu calculation
                }
            }
        });

        slide_time = (EditText) findViewById(R.id.each_edit);
        slide_time.setText(sharedPreferences.getString(SLIDE_TIME, "300"));
        slide_time.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText(MainActivity.this, "Slideshow now set to " + v.getText() + " seconds", Toast.LENGTH_LONG).show();
                    // TODO: Update the slideshow time
                }
                return false;
            }
        });

        // TODO: Check location permission, if yes create MyLocation Instance, else grey out selection

        if (!login) {
            disableView(disp_friend, friend_text);
            disableView(share_swi, share_title);
        }

    }

    private void disableView(View button, TextView text) {
        button.setEnabled(false);
        text.setTextColor(getResources().getColor(R.color.grey_out));
    }

    private void enableView(View button, TextView text, boolean isTitle) {
        button.setEnabled(true);
        if (isTitle) {
            text.setTextColor(getResources().getColor(R.color.colorTitle));
        }
        else {
            text.setTextColor(getResources().getColor(R.color.colorText));
        }
    }

    public void login_google(View v) {
        // TODO: Login with google
    }

    public void setWallpaper(View v) {
        // TODO: Start Service
    }

    public void restoreWallpaper(View v) {
        // TODO: Stop Service
    }

    public void more_photo(View v) {
        // TODO: Show more photo
    }

    public void take_photo(View v) {
        // TODO: Call camera
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save current settings to SharedPreference
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DISP_USER, disp_user.isChecked());
        editor.putBoolean(DISP_FRIEND, disp_friend.isChecked());
        editor.putBoolean(MODE_LOC, mode_loc.isChecked());
        editor.putBoolean(MODE_TIME, mode_time.isChecked());
        editor.putBoolean(SHARE_SWI, share_swi.isChecked());
        editor.putBoolean(DEJAVU_SWI, dejavu_swi.isChecked());
        editor.putInt(ALBUM_CHOOSE, 0);
        editor.putString(SLIDE_TIME, slide_time.getText().toString());
        editor.commit();

    }
}