package com.kevin.keyboard_poc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    KeyboardManger keyboardManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keyboardManger=new KeyboardManagerImpl(this);
        Log.e("is keyboard default : ", !keyboardManger.isUsingCustomKeyboard()+ "");
        KeyboardDetails keyboardDetails = keyboardManger.getKeyboardDetails();
        Log.e("keyboard label : ",keyboardDetails.getAppLAbel());
        Log.e("keyboard  name : ",keyboardDetails.getPackageName());

    }





}
