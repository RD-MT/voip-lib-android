package org.libs.core.tools.compatibility;

import android.app.Notification;
import android.app.Service;
import org.libs.core.tools.Log;

public class DeviceUtils34 {
   public static void startCallForegroundService(Service service, int notifId, Notification notif) {
      try {
         service.startForeground(notifId, notif, 196);
      } catch (Exception var4) {
         Log.e("[Device Utils 34] Can't start service as foreground!", var4);
      }

   }

   public static void startDataSyncForegroundService(Service service, int notifId, Notification notif) {
      try {
         service.startForeground(notifId, notif, 1);
      } catch (Exception var4) {
         Log.e("[Device Utils 34] Can't start service as foreground!", var4);
      }

   }
}
