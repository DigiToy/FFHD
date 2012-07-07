package tk.digitoy.ffhd.activities;

import java.util.Random;

import tk.digitoy.ffhd.utils.AppSettings;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainMenu extends Activity {

	// Button Images
	private ImageView buttonSound;
	private ImageView buttonGame;
	private ImageView buttonWall;
	private ImageView buttonAbout;

	// Images
	private ImageView imageKitty;
	private ImageView imageWindow;

	// Layout Parameters
	// Buttons selected
	private LayoutParams paramGame;
	private LayoutParams paramWall;
	private LayoutParams paramAbout;
	private LayoutParams paramSpark;

	// Buttons unselected
	private LayoutParams paramGameSelect;
	private LayoutParams paramWallSelect;
	private LayoutParams paramAboutSelect;

	// Images
	private LayoutParams paramKitty;
	private LayoutParams paramWindow;

	// Activity Layout
	private RelativeLayout rl;

	// Generated Random Object
	private Random rand;

	// Muted Or Not
	// private AudioManager mAudioManager;

	// Called when the activity is first created.

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		if (!AppSettings.isInit) {
			initAll();
		}
		// Generating Random object
		rand = new Random();

		initFlags();
		initLayoutParams();
		drawStaticLayout();
		// checkIfPhoneIsSilent();
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

		drawDynamicLayout();
		setParams();
		setListeners();
		sing();
	}

	// Setting images and buttons parameters
	private void setParams() {
		buttonGame.setLayoutParams(paramGame);
		buttonWall.setLayoutParams(paramWall);
		buttonAbout.setLayoutParams(paramAbout);
		imageKitty.setLayoutParams(paramKitty);
		imageWindow.setLayoutParams(paramWindow);
	}

	// Initializing flags
	private void initFlags() {
		AppSettings.eyeIsOpen = true;
		AppSettings.windowIsOpen = false;
		AppSettings.soundIsOn = true;
	}

	// Activating click listeners
	private void setListeners() {
		// Sound button Click Listener
		buttonSound.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (AppSettings.soundIsOn) {
					buttonSound.setImageResource(R.drawable.sound_icon_off);
					AppSettings.kittySong.pause();
					AppSettings.soundIsOn = !AppSettings.soundIsOn;
				} else {
					buttonSound.setImageResource(R.drawable.sound_icon_on);
					AppSettings.kittySong.start();
					AppSettings.soundIsOn = !AppSettings.soundIsOn;
				}
			}
		});

		// "Games" button Click Listener
		buttonGame.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				animateAndGo(buttonGame, paramGame, paramGameSelect,
						new Intent(arg0.getContext(), GamesMenu.class));
			}
		});

		// "Wallpapers" button Click Listener
		buttonWall.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				animateAndGo(buttonWall, paramWall, paramWallSelect,
						new Intent(arg0.getContext(), Wallpapers.class));
			}
		});

		// "About" button Click Listener
		buttonAbout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				animateAndGo(buttonAbout, paramAbout, paramAboutSelect,
						new Intent(arg0.getContext(), AboutKitty.class));
			}
		});

		// Eye Click Listener
		imageKitty.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (AppSettings.eyeIsOpen) {
					imageKitty.setImageResource(R.drawable.kitty_eye_closed);
					AppSettings.eyeIsOpen = !AppSettings.eyeIsOpen;
				} else {
					imageKitty.setImageResource(R.drawable.kitty_eye_open);
					AppSettings.eyeIsOpen = !AppSettings.eyeIsOpen;
				}
			}
		});

		// Window Click Listener
		imageWindow.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (AppSettings.windowIsOpen) {
					imageWindow.setImageResource(R.drawable.window_closed);
					AppSettings.windowIsOpen = !AppSettings.windowIsOpen;
				} else {
					imageWindow.setImageResource(R.drawable.window_open);
					sparkle();
					AppSettings.windowIsOpen = !AppSettings.windowIsOpen;
				}
			}
		});
	}

	// Initializing Layout Parameters
	private void initLayoutParams() {
		// Buttons unselected Parameters
		paramGame = new LayoutParams(AppSettings.dispWidth * 265 / 800,
				AppSettings.dispHeight * 44 / 480);
		paramGame.leftMargin = AppSettings.dispWidth * 371 / 800;
		paramGame.topMargin = AppSettings.dispHeight * 60 / 480;

		paramWall = new LayoutParams(AppSettings.dispWidth * 265 / 800,
				AppSettings.dispHeight * 43 / 480);
		paramWall.leftMargin = AppSettings.dispWidth * 355 / 800;
		paramWall.topMargin = AppSettings.dispHeight * 102 / 480;

		paramAbout = new LayoutParams(AppSettings.dispWidth * 265 / 800,
				AppSettings.dispHeight * 46 / 480);
		paramAbout.leftMargin = AppSettings.dispWidth * 371 / 800;
		paramAbout.topMargin = AppSettings.dispHeight * 143 / 480;

		// Buttons selected Parameters
		paramGameSelect = new LayoutParams(AppSettings.dispWidth * 270 / 800,
				AppSettings.dispHeight * 48 / 480);
		paramGameSelect.leftMargin = AppSettings.dispWidth * 368 / 800;
		paramGameSelect.topMargin = AppSettings.dispHeight * 58 / 480;

		paramWallSelect = new LayoutParams(AppSettings.dispWidth * 270 / 800,
				AppSettings.dispHeight * 47 / 480);
		paramWallSelect.leftMargin = AppSettings.dispWidth * 355 / 800;
		paramWallSelect.topMargin = AppSettings.dispHeight * 100 / 480;

		paramAboutSelect = new LayoutParams(AppSettings.dispWidth * 270 / 800,
				AppSettings.dispHeight * 50 / 480);
		paramAboutSelect.leftMargin = AppSettings.dispWidth * 368 / 800;
		paramAboutSelect.topMargin = AppSettings.dispHeight * 141 / 480;

		// Image Parameters
		paramKitty = new LayoutParams(AppSettings.dispWidth * 245 / 800,
				AppSettings.dispHeight * 230 / 480);
		paramKitty.leftMargin = AppSettings.dispWidth * 375 / 800;
		paramKitty.topMargin = AppSettings.dispHeight * 250 / 480;

		paramWindow = new LayoutParams(AppSettings.dispWidth * 214 / 800,
				AppSettings.dispHeight * 171 / 480);
		paramWindow.leftMargin = AppSettings.dispWidth * 107 / 800;
		paramWindow.topMargin = AppSettings.dispHeight * 236 / 480;

		paramSpark = new LayoutParams(AppSettings.dispWidth * 54 / 800,
				AppSettings.dispHeight * 27 / 480);
	}

	// Creating and adding static views
	private void drawStaticLayout() {

		// Getting activite's RelativeLAyout
		rl = (RelativeLayout) findViewById(R.id.main_menu);

		// "Game" button
		buttonGame = new ImageView(this);
		buttonGame.setImageResource(R.drawable.button_games);
		buttonGame.setScaleType(ScaleType.FIT_XY);

		// "Wallpapers" button
		buttonWall = new ImageView(this);
		buttonWall.setImageResource(R.drawable.button_wallpapers);
		buttonWall.setScaleType(ScaleType.FIT_XY);

		// "About Hello Kitty" button
		buttonAbout = new ImageView(this);
		buttonAbout.setImageResource(R.drawable.button_about);
		buttonAbout.setScaleType(ScaleType.FIT_XY);

		// Adding views to layout
		rl.addView(buttonGame);
		rl.addView(buttonWall);
		rl.addView(buttonAbout);

		// Cat's eye
		imageKitty = new ImageView(this);
		imageKitty.setScaleType(ScaleType.FIT_XY);

		// Window
		imageWindow = new ImageView(this);
		imageWindow.setScaleType(ScaleType.FIT_XY);

		// Adding views to layout
		rl.addView(imageKitty);
		rl.addView(imageWindow);

	}

	// Creating and adding dynamic views
	private void drawDynamicLayout() {
		// Sound On/Off icon (Handling from XML)
		buttonSound = (ImageView) findViewById(R.id.buttonSound);
		if (AppSettings.soundIsOn) {
			buttonSound.setImageResource(R.drawable.sound_icon_on);
		} else {
			buttonSound.setImageResource(R.drawable.sound_icon_off);
		}

		// Eye Open/Close
		if (AppSettings.eyeIsOpen) {
			imageKitty.setImageResource(R.drawable.kitty_eye_open);
		} else {
			imageKitty.setImageResource(R.drawable.kitty_eye_closed);
		}

		// Window Open/Close
		if (AppSettings.windowIsOpen) {
			imageWindow.setImageResource(R.drawable.window_open);
		} else {
			imageWindow.setImageResource(R.drawable.window_closed);
		}
	}

	// Sparkling
	private void sparkle() {
		final ImageView imageSpark = new ImageView(this);
		imageSpark.setScaleType(ScaleType.FIT_XY);
		rl.addView(imageSpark);

		MediaPlayer mp = MediaPlayer.create(this, R.raw.sparks_music);
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});

		new CountDownTimer(3000, 100) {
			@Override
			public void onTick(long millisUntilFinished) {
				imageSpark.setImageResource(R.drawable.spark_white);
				genSparkleCo();
				imageSpark.setLayoutParams(paramSpark);
			}

			@Override
			public void onFinish() {
				rl.removeView(imageSpark);
			};
		}.start();

	}

	// Generate Sparkle Random Coordinates
	private void genSparkleCo() {
		int rw = rand.nextInt(200);
		int rh = rand.nextInt(230);

		paramSpark.leftMargin = AppSettings.dispWidth * (150 + rw) / 800;
		paramSpark.topMargin = AppSettings.dispHeight * (100 + rh) / 480;
	}

	// Start playing sound if sound is on
	private void sing() {
		if (AppSettings.soundIsOn) {
			AppSettings.kittySong.start();
		}
	}

	// Animate Button on click
	private void animateAndGo(final ImageView iv, final LayoutParams first,
			final LayoutParams second, final Intent go) {
		new CountDownTimer(500, 100) {

			boolean b = true;

			@Override
			public void onTick(long millisUntilFinished) {
				if (b) {
					iv.setLayoutParams(second);
				} else {
					iv.setLayoutParams(first);
				}
				b = !b;
			}

			@Override
			public void onFinish() {
				MainMenu.this.startActivity(go);
			}
		}.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (AppSettings.isApplicationSentToBackground(this)) {
			AppSettings.kittySong.pause();
		}
	}

	@Override
	protected void onDestroy() {
		AppSettings.kittySong.release();
		AppSettings.isInit = false;
		super.onDestroy();
	}

}