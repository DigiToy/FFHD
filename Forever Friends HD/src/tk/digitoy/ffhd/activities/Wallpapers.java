package tk.digitoy.ffhd.activities;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import tk.digitoy.ffhd.utils.AppSettings;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;

public class Wallpapers extends Activity implements OnClickListener {

	// Button Images
	private ImageView buttonSound;

	// Layouts
	private LayoutParams paramWallScroll;
	private LinearLayout.LayoutParams paramWall;
	RelativeLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wallpapers);

		if (!AppSettings.isInit) {
			initAll();
		}

		drawStaticLayout();
		AdMobAdsRequest();
	}

	// Initializing display metrics
	private void initAll() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		AppSettings.dispHeight = metrics.heightPixels;
		AppSettings.dispWidth = metrics.widthPixels;
		AppSettings.music = MediaPlayer.create(getBaseContext(),
				R.raw.music);
		AppSettings.music.setLooping(true);
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
		buttonSound.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (AppSettings.soundIsOn) {
					buttonSound.setImageResource(R.drawable.sound_icon_off);
					AppSettings.music.pause();
					AppSettings.soundIsOn = !AppSettings.soundIsOn;
				} else {
					buttonSound.setImageResource(R.drawable.sound_icon_on);
					AppSettings.music.start();
					AppSettings.soundIsOn = !AppSettings.soundIsOn;
				}
			}
		});
	}

	// Creating and adding views
	private void drawStaticLayout() {

		// Handling from XML
		buttonSound = (ImageView) findViewById(R.id.buttonSound);
		if (!AppSettings.soundIsOn) {
			buttonSound.setImageResource(R.drawable.sound_icon_off);
		} else {
			buttonSound.setImageResource(R.drawable.sound_icon_on);
		}

		// finding wallpaper thumbs
		ImageView[] walls = new ImageView[] {
				(ImageView) findViewById(R.id.imageView1),
				(ImageView) findViewById(R.id.imageView2),
				(ImageView) findViewById(R.id.imageView3),
				(ImageView) findViewById(R.id.imageView4),
				(ImageView) findViewById(R.id.imageView5),
				(ImageView) findViewById(R.id.imageView6),
				(ImageView) findViewById(R.id.imageView7),
				(ImageView) findViewById(R.id.imageView8),
				(ImageView) findViewById(R.id.imageView9),
				(ImageView) findViewById(R.id.imageView10),
				(ImageView) findViewById(R.id.imageView11),
				(ImageView) findViewById(R.id.imageView12),
				(ImageView) findViewById(R.id.imageView13),
				(ImageView) findViewById(R.id.imageView14),
				(ImageView) findViewById(R.id.imageView15),
				(ImageView) findViewById(R.id.imageView16) };

		HorizontalScrollView wallScroll = (HorizontalScrollView) findViewById(R.id.wallScroll);

		paramWallScroll = new LayoutParams(AppSettings.dispWidth * 606 / 800,
				AppSettings.dispHeight * 256 / 480);
		paramWallScroll.leftMargin = AppSettings.dispWidth * 97 / 800;
		paramWallScroll.topMargin = AppSettings.dispHeight * 113 / 480;

		paramWall = new LinearLayout.LayoutParams(
				AppSettings.dispWidth * 186 / 800,
				AppSettings.dispHeight * 256 / 480);
		paramWall.setMargins(AppSettings.dispWidth * 8 / 800, 0,
				AppSettings.dispWidth * 8 / 800, 0);

		wallScroll.setLayoutParams(paramWallScroll);
		for (int i = 0; i < 16; i++) {
			walls[i].setLayoutParams(paramWall);
			walls[i].setScaleType(ScaleType.FIT_XY);
			walls[i].setId(i);
			walls[i].setOnClickListener(this);
		}
	}

	// Start playing sound if sound is on
	private void sing() {
		if (AppSettings.soundIsOn) {
			AppSettings.music.start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (AppSettings.isApplicationSentToBackground(this)) {
			AppSettings.music.pause();
		}
	}

	public void onClick(View v) {
		Intent go = new Intent(this, Wallpaper.class);
		go.putExtra("wallNumber", v.getId());
		startActivity(go);
	}
	
	private AdView adView;

	public void AdMobAdsRequest() {
		
		adView = new AdView(this, AdSize.BANNER, AppSettings.MY_AD_UNIT_ID);

		// Lookup your LinearLayout assuming it’s been given
		// the attribute android:id="@+id/mainLayout"
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.walpapers_rl);
		RelativeLayout.LayoutParams adMobLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		adMobLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		adMobLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adView.setLayoutParams(adMobLayoutParams);

		// Add the adView to it
		layout.addView(adView);

		// Initiate a generic request to load it with an ad
		adView.loadAd(new AdRequest());
	}
}
