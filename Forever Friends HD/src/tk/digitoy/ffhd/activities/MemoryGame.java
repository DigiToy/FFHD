package tk.digitoy.ffhd.activities;

import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import tk.digitoy.ffhd.utils.AppSettings;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class MemoryGame extends Activity implements OnClickListener {

	// Button Images
	private ImageView buttonSound;

	Dialog alertDialog;

	public static int appgridX = 4;
	public static int appgridY = 3;
	public static int folderName = 0;

	public static int top = AppSettings.dispHeight * 90 / 480;
	public static int left = AppSettings.dispWidth * 190 / 800;

	// Images
	private int[] images = new int[] { R.drawable.m_01, R.drawable.m_02,
			R.drawable.m_03, R.drawable.m_04, R.drawable.m_05, R.drawable.m_06,
			R.drawable.m_07, R.drawable.m_08, R.drawable.m_09, R.drawable.m_10,
			R.drawable.m_11, R.drawable.m_12 };

	RelativeLayout rel;
	RelativeLayout.LayoutParams[] lps;
	private imgClass[] image;
	int q = 0;
	int firstImageId, secondImageId;
	boolean sec = false;
	int randImage;
	String imageName;
	Bitmap[] bitmaps;
	InputStream is = null;
	int grid;
	int winner = 0;
	private int[] index;
	private int[] index2;
	AlphaAnimation animation;

	Timer timer;
	TextView txttimer;

	Random r = new Random();
	Handler myHandler = new Handler();

	private int imgSizeW = AppSettings.dispWidth * 98 / 800;
	private int imgDistW = AppSettings.dispWidth * 12 / 800;
	private int imgSizeH = AppSettings.dispHeight * 95 / 480;
	private int imgDistH = AppSettings.dispHeight * 14 / 480;
	final static int INTERVAL = 1000;

	MediaPlayer sparkSound;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memory_game);

		if (!AppSettings.isInit) {
			initAll();
		}

		grid = appgridX * appgridY;

		drawStaticLayout();
		setListeners();

		generateIndexes();
		generateIndexes2();

		createLayoutParams();

		createImages();

		AdMobAdsRequest();

		initSparkSound();
	}

	private void initSparkSound() {
		sparkSound = MediaPlayer.create(getBaseContext(), R.raw.sparks_music);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sing();
	}

	// Initializing display metrics
	private void initAll() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		AppSettings.dispHeight = metrics.heightPixels;
		AppSettings.dispWidth = metrics.widthPixels;
		AppSettings.music = MediaPlayer.create(getBaseContext(), R.raw.music);
		AppSettings.music.setLooping(true);
		AppSettings.isInit = true;
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
		int j = v.getId();
		int k;
		k = (j % 2 == 0) ? j + 1 : j - 1;

		q++;
		if (q == 1) {
			image[j].imgdef.setVisibility(View.INVISIBLE);
			image[j].img.setVisibility(View.VISIBLE);
			image[j].vis = true;
			firstImageId = j;
			myHandler.postDelayed(image[j], 1500);

		} else if (q == 2 && sec == false) {

			if (image[k].vis == true) {
				if (sparkSound.isPlaying()) {
					sparkSound.reset();
					initSparkSound();
				}
				sparkSound.start();
				myHandler.removeCallbacks(image[k]);
				animation = new AlphaAnimation(1.0f, 0.0f);
				animation.setDuration(1000);
				image[j].img.setAnimation(animation);
				image[k].img.setAnimation(animation);

				image[j].imgdef.setVisibility(View.GONE);
				image[j].img.setVisibility(View.GONE);
				image[k].imgdef.setVisibility(View.GONE);
				image[k].img.setVisibility(View.GONE);
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						q = 0;
						timer.cancel();

					}

				}, 1000, 10000);
				winner++;
				if (winner >= grid / 2) {
					winGame();
				}

			} else {
				sec = true;
				image[j].imgdef.setVisibility(View.INVISIBLE);
				image[j].img.setVisibility(View.VISIBLE);
				image[j].vis = true;
				secondImageId = j;
				myHandler.postDelayed(image[j], 1500);
			}
		} else {
			if (sec == true) {
				myHandler.removeCallbacks(image[firstImageId]);
				myHandler.removeCallbacks(image[secondImageId]);
				myHandler.post(image[firstImageId]);
				myHandler.post(image[secondImageId]);

			}
			q--;
		}
	}

	private void winGame() {
		alertDialog = new Dialog(this);
		alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		alertDialog = new Dialog(this,
				android.R.style.Theme_Translucent_NoTitleBar);
		alertDialog.setContentView(R.layout.dialog);
		alertDialog.setCancelable(true);

		Button okButton = (Button) alertDialog.findViewById(R.id.OK_btn);
		okButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				alertDialog.cancel();
				MemoryGame.this.finish();
			}
		});
		alertDialog.show();
	}

	public void createLayoutParams() {
		lps = new RelativeLayout.LayoutParams[grid];

		for (int i = 0; i < grid; i++) {
			lps[i] = new RelativeLayout.LayoutParams(imgSizeW, imgSizeH);
			lps[i].leftMargin = left + (imgSizeW + imgDistW) * (i % appgridX);
			lps[i].topMargin = top + (imgSizeH + imgDistH) * (i / appgridX);
		}
	}

	public void createImages() {
		rel = (RelativeLayout) findViewById(R.id.memory_game_rl);
		image = new imgClass[grid];

		for (int i = 0; i < grid; i++) {
			image[i] = new imgClass();
			image[i].img = new ImageView(getBaseContext());
			image[i].imgdef = new ImageView(getBaseContext());

			image[i].imgdef.setBackgroundResource(R.drawable.m_cover);
			image[i].img.setVisibility(View.INVISIBLE);

			image[i].img.setLayoutParams(lps[index[i]]);
			image[i].imgdef.setLayoutParams(lps[index[i]]);
			image[i].imgdef.setId(i);

			rel.addView(image[i].img);
			rel.addView(image[i].imgdef);

			image[i].imgdef.setOnClickListener(this);
		}
		for (int i = 0; i < grid / 2; i++) {
			// image[2 * i].imgdef.setImageResource(images[index2[i]]);
			// image[2 * i + 1].imgdef.setImageResource(images[index2[i]]);
			image[2 * i].img.setBackgroundResource(images[index2[i]]);
			image[2 * i + 1].img.setBackgroundResource(images[index2[i]]);
		}
	}

	public void generateIndexes() {
		index = new int[grid];
		boolean g;
		int temp;
		for (int m = 0; m < grid; m++) {
			do {
				g = false;
				temp = r.nextInt(grid - 0) + 0;
				for (int n = 0; n < m; n++) {
					if (index[n] == temp) {
						g = true;
						break;
					}
				}
			} while (g == true);
			index[m] = temp;
		}
	}

	public void generateIndexes2() {
		index2 = new int[grid / 2];
		for (int k = 0; k < grid / 2; k++)
			index2[k] = -1;
		int imageCount = 12;

		boolean g2;
		int temp2;
		for (int m = 0; m < grid / 2; m++) {
			do {
				g2 = false;
				temp2 = r.nextInt(imageCount);
				for (int n = 0; n < m; n++) {
					if (index2[n] == temp2) {
						g2 = true;
						break;
					}
				}
			} while (g2 == true);
			index2[m] = temp2;
		}
	}

	private class imgClass implements Runnable {

		public ImageView img = new ImageView(getBaseContext());
		public ImageView imgdef = new ImageView(getBaseContext());
		public boolean vis = false;

		public void run() {
			if (vis == true && imgdef.getId() == firstImageId) {
				imgdef.setVisibility(View.VISIBLE);
				img.setVisibility(View.INVISIBLE);
				vis = false;
				q--;
			} else if (vis == true && imgdef.getId() == secondImageId) {
				imgdef.setVisibility(View.VISIBLE);
				img.setVisibility(View.INVISIBLE);
				vis = false;
				q--;
				sec = false;
			}
		}
	}

	private AdView adView;

	public void AdMobAdsRequest() {

		adView = new AdView(this, AdSize.BANNER, AppSettings.MY_AD_UNIT_ID);

		// Lookup your LinearLayout assuming it’s been given
		// the attribute android:id="@+id/mainLayout"
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.memory_game_rl);
		RelativeLayout.LayoutParams adMobLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		adMobLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		adView.setLayoutParams(adMobLayoutParams);

		// Add the adView to it
		layout.addView(adView);

		// Initiate a generic request to load it with an ad
		adView.loadAd(new AdRequest());
	}

	@Override
	protected void onDestroy() {
		sparkSound.release();
		super.onDestroy();
	}

}