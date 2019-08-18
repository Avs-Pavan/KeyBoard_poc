package com.kevin.keyboard_poc;

public class KeyboardDetails {
   private  String  appLAbel;
   private  String packageName;

    public KeyboardDetails(String appLAbel, String packageName) {
        this.appLAbel = appLAbel;
        this.packageName = packageName;
    }

    public String getAppLAbel() {
        return appLAbel;
    }

    public void setAppLAbel(String appLAbel) {
        this.appLAbel = appLAbel;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "KeyboardDetails{" +
                "appLAbel='" + appLAbel + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
