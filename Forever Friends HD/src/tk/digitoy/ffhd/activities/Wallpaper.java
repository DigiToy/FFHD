package tk.digitoy.ffhd.activities;

import java.io.IOException;
import tk.digitoy.ffhd.utils.AppSettings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Wallpaper extends Activity implements Runnable {

	private ProgressDialog pDialog;
	// Button Images
	private ImageView buttonSet;

	// generate bitmap
	private int resID;
	private String imagePath;

	private int[] walls;

	// decide wall size
	private String wSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wallpaper_view);

		if (!AppSettings.isInit) {
			initAll();
		}

		walls = new int[] { R.drawable.wt_01, R.drawable.wt_02,
				R.drawable.wt_03, R.drawable.wt_04, R.drawable.wt_05,
				R.drawable.wt_06, R.drawable.wt_07, R.drawable.wt_08,
				R.drawable.wt_09, R.drawable.wt_10, R.drawable.wt_11,
				R.drawable.wt_12, R.drawable.wt_13, R.drawable.wt_14,
				R.drawable.wt_15, R.drawable.wt_16 };

		int wallNumber = getIntent().getIntExtra("wallNumber", 1000);
		resID = walls[wallNumber];

		if (AppSettings.dispHeight == 480) {
			wSize = "960x800";
		} else if (AppSettings.dispHeight == 600) {
			wSize = "1200x1024";
		} else if (AppSettings.dispHeight == 720) {
			wSize = "1440x1280";
		} else {
			wSize = "1920x1408";
		}

		imagePath = wSize + "/w_" + (wallNumber < 10 ? "0" : "") + wallNumber
				+ ".jpg";
		drawStaticLayout();
	}

	// Initializing display metrics
	private void initAll() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		AppSettings.dispHeight = metrics.heightPixels;
		AppSettings.dispWidth = metrics.widthPixels;
		AppSettings.kittySong = MediaPlayer.create(getBaseContext(),
				R.raw.music);
		AppSettings.kittySong.setLooping(true);
		AppSettings.isInit = true;
	}

	// Called when the activity returning to foreground.
	@Override
	protected void onResume() {
		super.onResume();

		setListeners();
		sing();
	}

	// Activating click listeners
	private void setListeners() {
		// Sound button Click Listener
		buttonSet.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SetWallpaper();
			}
		});

	}

	// Creating and adding views
	private void drawStaticLayout() {

		// Handling from XML
		buttonSet = (ImageView) findViewById(R.id.buttonSetWall);

		ImageView img = (ImageView) findViewById(R.id.wallPaper);
		img.setImageResource(resID);
	}

	// Start playing sound if sound is on
	private void sing() {
		if (AppSettings.soundIsOn) {
			AppSettings.kittySong.start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (AppSettings.isApplicationSentToBackground(this)) {
			AppSettings.kittySong.pause();
		}
		if (pDialog != null && pDialog.isShowing()) {
			pDialog.dismiss();
		}
	}

	public void SetWallpaper() {
		pDialog = ProgressDialog.show(this, "", "", true, false);
		pDialog.setContentView(R.layout.wait_dialog);
		TextView wait = (TextView) pDialog.findViewById(R.id.wait);
		wait.setText(R.string.please_wait);
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		// TODO Auto-generated method stub
		WallpaperManager wm = WallpaperManager.getInstance(Wallpaper.this);
		try {
			wm.setBitmap(BitmapFactory
					.decodeStream(getAssets().open(imagePath)));
			this.runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(Wallpaper.this, "Wallpaper changed",
							Toast.LENGTH_SHORT).show();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pDialog.dismiss();
		}
	}
}
