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
    fun resetPreferredLauncherAndOpenChooser(promise: Promise): Promise {

      val startMain = Intent(Intent.ACTION_MAIN)
      startMain.addCategory(Intent.CATEGORY_HOME)
      startMain.addCategory(Intent.CATEGORY_DEFAULT)
      selector.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      startActivity(startMain)

      promise.resolve(true)
      return promise
    }

    @ReactMethod
    fun isPreferredLauncher(promise: Promise): Promise {
      val localPackageManager = reactContext.getPackageManager()
      val intent = Intent(Intent.ACTION_MAIN)
      intent.addCategory(Intent.CATEGORY_HOME)
      val str = localPackageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY).activityInfo.packageName
      promise.resolve(str == reactContext.getPackageName())
      return promise
    }


}
