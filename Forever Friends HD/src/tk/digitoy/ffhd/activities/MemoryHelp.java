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

public class MemoryHelp extends Activity {

	// Button Images
	private ImageView buttonSound;
	private ImageButton buttonPlay;

	// Layout Parameters
	private LayoutParams paramPlay;

	// Activity Layout
	private RelativeLayout rl;

	// Called when the activity is first created.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memory_help);

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
		setParams();
		setListeners();
		if (!AppSettings.soundIsOn) {
			buttonSound.setImageResource(R.drawable.sound_icon_off);
		} else {
			buttonSound.setImageResource(R.drawable.sound_icon_on);
		}
		sing();
	}

	// Setting images and buttons parameters
	private void setParams() {
		buttonPlay.setLayoutParams(paramPlay);
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

		// "Play" button Click Listener
		buttonPlay.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(arg0.getContext(), MemoryGame.class));
			}
		});
	}

	// Initializing Layout Parameters
	private void initLayoutParams() {
		// Buttons unselected Parameters
		paramPlay = new LayoutParams(AppSettings.dispWidth * 120 / 800,
				AppSettings.dispHeight * 69 / 480);

		paramPlay.leftMargin = AppSettings.dispWidth * 340 / 800;
		paramPlay.topMargin = AppSettings.dispHeight * 293 / 480;
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
		rl = (RelativeLayout) findViewById(R.id.memory_help_rl);

		// "Play" button
		buttonPlay = new ImageButton(this);
		buttonPlay.setBackgroundResource(R.drawable.play_memory);

		// Adding views to layout
		rl.addView(buttonPlay);
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