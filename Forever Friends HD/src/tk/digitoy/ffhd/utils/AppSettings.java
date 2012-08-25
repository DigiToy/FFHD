package tk.digitoy.ffhd.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.PowerManager;

public class AppSettings {
	public static boolean isInit = false;

	// Display Metrics
	public static int dispWidth;
	public static int dispHeight;

	// Flags
	public static boolean soundIsOn;

	// Kitty song
	public static MediaPlayer music;

	// AdMob ID
	public static String MY_AD_UNIT_ID = "a1502e3df386d4c";

	/**
	 * Checks if the application is being sent in the background (i.e behind
	 * another application's Activity).
	 * 
	 * @param context
	 *            the context
	 * @return <code>true</code> if another application will be above this one.
	 */
	public static boolean isApplicationSentToBackground(final Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = am.getRunningTasks(1);
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		if (!pm.isScreenOn()) {
			return true;
		}
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}

		return false;
	}

}
