package com.kevin.keyboard_poc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

public class KeyboardManagerImpl implements KeyboardManger {

    private Context context;
    private static final String KEYBAORD_HTC_SENSE = "com.htc.android.htcime/.HTCIMEService";
    private static final String KEYBOARD_OLD_SAMSUNG = "com.sec.android.inputmethod.axt9/.AxT9IME";
    private static final String KEYBOARD_SAMSUNG = "com.sec.android.inputmethod/.SamsungKeypad";
    private static final String KEYBOARD_LG = "com.lge.ime/.LgeImeImpl";
    private static final String KEYBOARD_SWIFTKEY_TRIAL = "com.touchtype.swiftkey.phone.trial/com.touchtype.KeyboardService";
    private static final String KEYBOARD_SWYPE_DRAGON = "com.nuance.swype.trial/com.nuance.swype.input.IME";
    private static final String KEYBOARD_SWIFTKEY = "com.touchtype.swiftkey/com.touchtype.KeyboardService";

    public KeyboardManagerImpl(Context context) {
        this.context = context;
    }

    private static final String[] whiteListKeybaord = new String[]{"", ""};

    @Override
    public boolean isUsingCustomKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        List<InputMethodInfo> mInputMethodProperties = imm
                .getEnabledInputMethodList();
        final int N = mInputMethodProperties.size();
        for (int i = 0; i < N; i++) {
            InputMethodInfo imi = mInputMethodProperties.get(i);
            if (imi.getId().equals(
                    Settings.Secure.getString(
                            context.getContentResolver(),
                            Settings.Secure.DEFAULT_INPUT_METHOD))) {
                if ((imi.getServiceInfo().applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isWhiteListedByMarket() {
        String currentKeyboardName = getKeyboardName();
        for (String s : whiteListKeybaord)
            if (currentKeyboardName.contains(s))
                return true;

        return false;
    }

    @Override
    public KeyboardDetails getKeyboardDetails() {
        final String keyboardName = getKeyboardName();
        return new KeyboardDetails(getKeyboardLabel(context, keyboardName.split("/")[0]), keyboardName);
    }

    private String getKeyboardLabel(Context context, String packageName) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        String appName = null;
        try {
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            return "Name not found";
        }
        return appName;
    }

    private String getKeyboardName() {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.DEFAULT_INPUT_METHOD);
    }


    public boolean isASenseKeyboard() {
        return getKeyboardName().equals(KEYBAORD_HTC_SENSE);
    }


    public boolean isASwypeDragonKeyboard() {
        return getKeyboardName().equals(KEYBOARD_SWYPE_DRAGON);
    }


    public boolean isASwiftKeyTrialKeyboard() {
        return getKeyboardName().equals(KEYBOARD_SWIFTKEY_TRIAL);
    }


    public boolean isASwiftKeyKeyboard() {
        return getKeyboardName().equals(KEYBOARD_SWIFTKEY);
    }


    public boolean isAOldSamsungKeyboard() {
        return getKeyboardName().equals(KEYBOARD_OLD_SAMSUNG);
    }


    public boolean isASamsungKeyboard() {
        return getKeyboardName().equals(KEYBOARD_SAMSUNG);
    }


    public boolean isALGKeyboard() {
        return getKeyboardName().equals(KEYBOARD_LG);
    }

    public boolean isGboard() {
        return getKeyboardName().contains("com.google.android.inputmethod.latin") || getKeyboardName().contains("com.android.inputmethod.latin");
    }
}
