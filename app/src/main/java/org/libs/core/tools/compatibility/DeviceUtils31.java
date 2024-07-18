package org.libs.core.tools.compatibility;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.os.Build.VERSION;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.libs.core.tools.Log;

public class DeviceUtils31 {
   public static void logPreviousCrashesIfAny(Context context) {
      ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
      List<ApplicationExitInfo> exitInfos = activityManager.getHistoricalProcessExitReasons((String)null, 0, 5);

      for(Iterator var3 = exitInfos.iterator(); var3.hasNext(); Log.i("=========================================")) {
         ApplicationExitInfo exitInfo = (ApplicationExitInfo)var3.next();
         Log.i("==== Previous exit reason information dump ====");
         Log.i("REASON=", DeviceUtils30.getReasonAsString(exitInfo.getReason()));
         Log.i("TIMESTAMP=", DeviceUtils30.getHumanReadableDateAndTimeFromTimestamp(exitInfo.getTimestamp()));
         Log.i("DESCRIPTION=", exitInfo.getDescription());
         if (exitInfo.getReason() == 6 || exitInfo.getReason() == 5) {
            try {
               InputStream inputStream = exitInfo.getTraceInputStream();
               if (inputStream != null) {
                  if (exitInfo.getReason() != 5) {
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                     String trace = (String)bufferedReader.lines().collect(Collectors.joining("\n"));
                     Log.w("TRACE=", trace);
                     bufferedReader.close();
                  }
               } else {
                  Log.w("[Device Utils 31] No input stream for exit info");
               }
            } catch (IOException var8) {
               Log.e("[Device Utils 31] IO Exception while trying to get trace input stream: ", var8);
            } catch (IllegalStateException var9) {
               Log.e("[Device Utils 31] Illegal State Exception while trying to get trace input stream: ", var9);
            } catch (Exception var10) {
               Log.e("[Device Utils 31] Exception while trying to get trace input stream: ", var10);
            }
         }
      }

   }

   public static int getPerformanceClass() {
      return VERSION.MEDIA_PERFORMANCE_CLASS;
   }

   public static boolean isBluetoothConnectPermissionGranted(Context context) {
      return context.checkSelfPermission("android.permission.BLUETOOTH_CONNECT") == 0;
   }

   public static void startForegroundService(Service service, int notifId, Notification notif) {
      try {
         service.startForeground(notifId, notif);
      } catch (Exception var4) {
         Log.e("[Device Utils 31] Can't start service as foreground!", var4);
      }

   }
}
