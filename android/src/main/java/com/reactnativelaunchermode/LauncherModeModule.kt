package com.reactnativelaunchermode

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

import android.content.ComponentName
import android.content.pm.PackageManager
import android.content.Intent
import android.content.Context
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.IntentFilter
import android.util.Log
import java.util.ArrayList

class LauncherModeModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "LauncherMode"
    }

    // Example method
    // See https://facebook.github.io/react-native/docs/native-modules-android

    @ReactMethod
    fun resetPreferredLauncherAndOpenChooser(promise: Promise): Boolean {
      val packageManager = reactContext.getPackageManager()
      val componentName = ComponentName(reactContext, "com.reactnativelaunchermode.FakeLauncherActivity")
      packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
      val selector = Intent(Intent.ACTION_MAIN)
      selector.addCategory(Intent.CATEGORY_HOME)
      selector.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      reactContext.startActivity(selector)
      packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP)
      promise.resolve(true);
      return true;
    }

    @ReactMethod
    fun isPreferredLauncher(promise: Promise): Boolean {
        val filter = IntentFilter(Intent.ACTION_MAIN)
        filter.addCategory(Intent.CATEGORY_HOME)
        val filters = ArrayList<IntentFilter>()
        filters.add(filter)
        val myPackageName = reactContext.getPackageName()
        val activities = ArrayList<ComponentName>()
        val packageManager = reactContext.getPackageManager() as PackageManager
        // You can use name of your package here as third argument
        packageManager.getPreferredActivities(filters, activities, null)
        for (activity in activities)
        {
          if (myPackageName == activity.getPackageName())
          {
            promise.resolve(true);
            return true
          }
        }
        promise.resolve(false);
        return false
      }


}
