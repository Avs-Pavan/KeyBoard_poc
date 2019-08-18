package com.kevin.keyboard_poc;

import android.content.Context;
import android.provider.Settings;

public interface KeyboardManger {


      boolean isUsingCustomKeyboard();
      boolean isWhiteListedByMarket();
      KeyboardDetails getKeyboardDetails();

}
