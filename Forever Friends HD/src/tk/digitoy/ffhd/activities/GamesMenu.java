package tk.digitoy.ffhd.activities;

import tk.digitoy.ffhd.utils.AppSettings;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class GamesMenu extends Activity {

	// Button Images
	private ImageView buttonSound;
	private ImageButton buttonPuzzle;
	private ImageButton buttonMemory;

	// Layout Parameters
	// Buttons selected
	private LayoutParams paramMemory;
	private LayoutParams paramPuzzle;

	// Activity Layout
	private RelativeLayout rl;

	// Called when the activity is first created.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_menu);

		if (!AppSettings.isInit) {
			initAll();
		}

		initLayoutParams();
		drawStaticLayout();

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
		buttonMemory.setLayoutParams(paramMemory);
		buttonPuzzle.setLayoutParams(paramPuzzle);
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

		// "Memory Game" button Click Listener
		buttonMemory.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(arg0.getContext(), MemoryHelp.class));
			}
		});

		// "Puzzle Game" button Click Listener
		buttonPuzzle.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(arg0.getContext(), PuzzleGame.class));
			}
		});
	}

	// Initializing Layout Parameters
	private void initLayoutParams() {
		// Buttons unselected Parameters
		paramMemory = new LayoutParams(AppSettings.dispWidth * 265 / 800,
				AppSettings.dispHeight * 108 / 480);
		paramMemory.leftMargin = AppSettings.dispWidth * 56 / 800;
		paramMemory.topMargin = AppSettings.dispHeight * 88 / 480;

		paramPuzzle = new LayoutParams(AppSettings.dispWidth * 266 / 800,
				AppSettings.dispHeight * 108 / 480);
		paramPuzzle.leftMargin = AppSettings.dispWidth * 55 / 800;
		paramPuzzle.topMargin = AppSettings.dispHeight * 214 / 480;

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

		// Getting activite's RelativeLAyout
		rl = (RelativeLayout) findViewById(R.id.games_menu);

		// "Memory Game" button
		buttonMemory = new ImageButton(this);
		buttonMemory.setBackgroundResource(R.drawable.button_memory);

		// "Puzzle Game" button
		buttonPuzzle = new ImageButton(this);
		buttonPuzzle.setBackgroundResource(R.drawable.button_puzzle);

		// Adding views to layout
		rl.addView(buttonMemory);
		rl.addView(buttonPuzzle);
	}

	// Creating and adding dynamic views
	private void drawDynamicLayout() {
		// Handling from XML
		buttonSound = (ImageView) findViewById(R.id.buttonSound);
		if (!AppSettings.soundIsOn) {
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
}