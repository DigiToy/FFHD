package tk.digitoy.ffhd.activities;

import tk.digitoy.ffhd.utils.AppSettings;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainMenu extends Activity {

	// Button Images
	private ImageView buttonSound;
	private ImageButton buttonGame;
	private ImageButton buttonWall;
	private ImageButton buttonAbout;
	private ImageButton buttonMoreGames;

	// Layout Parameters
	// Buttons selected
	private LayoutParams paramGame;
	private LayoutParams paramWall;
	private LayoutParams paramAbout;
	private LayoutParams paramMoreGames;

	// Activity Layout
	private RelativeLayout rl;

	// Called when the activity is first created.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		if (!AppSettings.isInit) {
			initAll();
		}
		initLayoutParams();
		drawStaticLayout();
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

	// Initializing display metrics
	private void initAll() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		AppSettings.dispHeight = metrics.heightPixels;
		AppSettings.dispWidth = metrics.widthPixels;
		AppSettings.music = MediaPlayer.create(getBaseContext(), R.raw.music);
		AppSettings.music.setLooping(true);
		AppSettings.isInit = true;
		AppSettings.soundIsOn = true;
	}

	// Setting images and buttons parameters
	private void setParams() {
		buttonGame.setLayoutParams(paramGame);
		buttonWall.setLayoutParams(paramWall);
		buttonAbout.setLayoutParams(paramAbout);
		buttonMoreGames.setLayoutParams(paramMoreGames);
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

		// "Games" button Click Listener
		buttonGame.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(arg0.getContext(), GamesMenu.class));
			}
		});

		// "Wallpapers" button Click Listener
		buttonWall.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(arg0.getContext(), Wallpapers.class));
			}
		});

		// "About" button Click Listener
		buttonAbout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(arg0.getContext(), About.class));
			}
		});

		// "More Games" button Click Listener
		buttonMoreGames.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://search?q=pub:DigiToy"));
				startActivity(intent);
			}
		});

	}

	// Initializing Layout Parameters
	private void initLayoutParams() {
		// Buttons unselected Parameters
		paramGame = new LayoutParams(AppSettings.dispWidth * 236 / 800,
				AppSettings.dispHeight * 76 / 480);
		paramGame.leftMargin = AppSettings.dispWidth * 88 / 800;
		paramGame.topMargin = AppSettings.dispHeight * 136 / 480;

		paramWall = new LayoutParams(AppSettings.dispWidth * 236 / 800,
				AppSettings.dispHeight * 76 / 480);
		paramWall.leftMargin = AppSettings.dispWidth * 90 / 800;
		paramWall.topMargin = AppSettings.dispHeight * 64 / 480;

		paramAbout = new LayoutParams(AppSettings.dispWidth * 236 / 800,
				AppSettings.dispHeight * 76 / 480);
		paramAbout.leftMargin = AppSettings.dispWidth * 86 / 800;
		paramAbout.topMargin = AppSettings.dispHeight * 207 / 480;

		paramMoreGames = new LayoutParams(AppSettings.dispWidth * 121 / 800,
				AppSettings.dispHeight * 69 / 480);
		paramMoreGames.leftMargin = AppSettings.dispWidth * 659 / 800;
		paramMoreGames.topMargin = AppSettings.dispHeight * 391 / 480;
	}

	// Creating and adding static views
	private void drawStaticLayout() {

		// Getting activite's RelativeLAyout
		rl = (RelativeLayout) findViewById(R.id.main_menu);

		// "Wallpapers" button
		buttonWall = new ImageButton(this);
		buttonWall.setBackgroundResource(R.drawable.button_wallpapers);

		// "Game" button
		buttonGame = new ImageButton(this);
		buttonGame.setBackgroundResource(R.drawable.button_games);

		// "About Hello Kitty" button
		buttonAbout = new ImageButton(this);
		buttonAbout.setBackgroundResource(R.drawable.button_about);

		// "More Games" button
		buttonMoreGames = new ImageButton(this);
		buttonMoreGames.setBackgroundResource(R.drawable.more_button);

		// Adding views to layout
		rl.addView(buttonGame);
		rl.addView(buttonWall);
		rl.addView(buttonAbout);
		rl.addView(buttonMoreGames);
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
	}

	// Start playing sound if sound is on
	private void sing() {
		if (AppSettings.soundIsOn) {
			AppSettings.music.start();
		}
	}

	@Override
	protected void onPause() {
		if (AppSettings.isApplicationSentToBackground(this)) {
			AppSettings.music.pause();
		}
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		AppSettings.music.release();
		super.onBackPressed();
	}

}