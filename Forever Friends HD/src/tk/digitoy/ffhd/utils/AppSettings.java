package tk.digitoy.ffhd.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.media.MediaPlayer;

public class AppSettings {
	public static boolean isInit = false;

	// Display Metrics
	public static int dispWidth;
	public static int dispHeight;

	// Flags
	public static boolean soundIsOn;

	// Kitty song
	public static MediaPlayer music;
	
	//AdMob ID
	public static String MY_AD_UNIT_ID = "a14ff8400c1ddb6";

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
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}

		return false;
	}

}
