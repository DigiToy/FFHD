package tk.digitoy.ffhd.activities;

import tk.digitoy.ffhd.utils.AppSettings;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class AboutKitty extends Activity {

	// Button Images
	private ImageView buttonSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		if (!AppSettings.isInit) {
			initAll();
		}
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
	}
}
