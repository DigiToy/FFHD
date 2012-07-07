package tk.digitoy.ffhd.activities;

import java.util.ArrayList;
import java.util.Random;

import tk.digitoy.ffhd.utils.AppSettings;
import tk.digitoy.ffhd.utils.ImageRect;
import tk.digitoy.ffhd.utils.ImagesRealization;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PuzzleGame extends Activity {
	Dialog alertDialog;
	/** Called when the activity is first created. */
	DrawView main;
	RelativeLayout layout;
	private ImageView buttonSound;
	private ImageView buttonRestart;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!AppSettings.isInit) {
			initAll();
		}
		setContentView(R.layout.puzzle_game);
		layout = (RelativeLayout) findViewById(R.id.puzzle_game_rl);
		main = new DrawView(this, AppSettings.dispHeight, AppSettings.dispWidth);

		layout.addView(main);

		drawStaticLayout();
		setListeners();
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

	@Override
	protected void onResume() {
		super.onResume();

		sing();
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

	class DrawView extends View {
		ImageRect frameColorRect;
		ImageRect backFrameColorRect;
		ArrayList<ImageRect> colorRects = new ArrayList<ImageRect>();
		boolean isSelected = false;
		int recentX;
		int recentY;
		int countOfUndragebleImages;
		int recID = 0; // variable to know what rectangle is being dragged
		Paint mTextPaint;
		Paint mTextPaintOutline;
		ImageRect currentImg;

		// ImageRect imgBack;
		// ImageRect imgSoundOn;
		// ImageRect imgSoundOff;
		ImageRect imgShow;
		boolean isPressedInShow;
		boolean gameIsOnProcess = true;

		// private boolean isPressedInBack;

		public DrawView(Context context, int height, int width) {
			super(context);
			setFocusable(true); // necessary for getting the touch events

			Point point = new Point();
			point.x = 0;
			point.y = 0;
			Point point1 = new Point();
			point1.x = width / 4;
			point1.y = height / 5;
			Point point2 = new Point();
			point2.x = width / 15;
			point2.y = height / 4;

			countOfUndragebleImages = 0;

			int random = (int) Math.ceil(Math.random() * 10);

			// declare each rectangle with the ColorRect class

			// backgroundImage = new ImageRect(context, R.drawable.puzzlebg,
			// point, width, height);
			// point.set(0, 0);
			// imgBack = new ImageRect(context, R.drawable.back_button, point);
			// point.set(width - imgBack.getWidth(), 0);
			// imgSoundOn = new ImageRect(context, R.drawable.sound_icon_on,
			// point);
			// point.set(width - imgBack.getWidth(), 0);
			// imgSoundOff = new ImageRect(context, R.drawable.sound_icon_off,
			// point);
			point.set(width / 15, 2 * height / 3);
			imgShow = new ImageRect(context, R.drawable.show, point);

			ImagesRealization imagesRealization = new ImagesRealization(
					context, random, point2, point1, width, height);

			backFrameColorRect = imagesRealization.getBackFrameColorRect();
			frameColorRect = imagesRealization.getFrameColorRect();
			colorRects = imagesRealization.getArrayListOfImages();

			RandomPermutation(colorRects);

			mTextPaint = new Paint();
			mTextPaint.setAntiAlias(true);
			mTextPaint.setTextSize(24);
			mTextPaint.setColor(0xFF000000);
			mTextPaint.setStyle(Paint.Style.FILL);

			mTextPaintOutline = new Paint();
			mTextPaintOutline.setAntiAlias(true);
			mTextPaintOutline.setTextSize(24);
			mTextPaintOutline.setColor(0xFF000000);
			mTextPaintOutline.setStyle(Paint.Style.STROKE);
			mTextPaintOutline.setStrokeWidth(2);

			setPadding(3, 3, 3, 3);

			isPressedInShow = false;
			// isPressedInBack = false;

		}

		// the method that draws the rectangles
		@Override
		protected void onDraw(Canvas canvas) {
			// canvas.drawBitmap(backgroundImage.getBitmap(), 0, 0, null);
			// canvas.drawBitmap(imgBack.getBitmap(), imgBack.getX(),
			// imgBack.getY(), null);
			//
			// if (AppSettings.soundIsOn) {
			// canvas.drawBitmap(imgSoundOn.getBitmap(), imgSoundOn.getX(),
			// imgSoundOn.getY(), null);
			// } else {
			// canvas.drawBitmap(imgSoundOff.getBitmap(), imgSoundOff.getX(),
			// imgSoundOff.getY(), null);
			// }

			canvas.drawBitmap(imgShow.getBitmap(), imgShow.getX(),
					imgShow.getY(), null);

			if (isPressedInShow) {
				canvas.drawBitmap(frameColorRect.getBitmap(),
						frameColorRect.getX(), frameColorRect.getY(), null);
			} else {
				canvas.drawBitmap(backFrameColorRect.getBitmap(),
						backFrameColorRect.getX(), backFrameColorRect.getY(),
						null);
			}
			for (int i = 0; i < colorRects.size(); i++) {

				canvas.drawBitmap(colorRects.get(i).getBitmap(), colorRects
						.get(i).getX(), colorRects.get(i).getY(), null);
			}
			countOfUndragebleImages = 0;
			for (int i = 0; i < colorRects.size(); i++) {
				if (!colorRects.get(i).getIsDrawable()) {
					countOfUndragebleImages++;
				}

			}
			if (countOfUndragebleImages == 12 && gameIsOnProcess) {
				PuzzleGame.this.showWinDialog();
				gameIsOnProcess = false;
			}
		}

		// events when touching the screen
		public boolean onTouchEvent(MotionEvent event) {
			int eventaction = event.getAction();

			int X = (int) event.getX();
			int Y = (int) event.getY();

			switch (eventaction) {

			case MotionEvent.ACTION_DOWN: // touch down so check if the finger
											// is on
											// a rectangle
											// recID = 0;
				if (imgShow.isBelong(X, Y)) {
					isPressedInShow = true;
				}
				// if (imgBack.isBelong(X, Y)) {
				// // isPressedInBack = true;
				//
				// PuzzleGame.this.finish();
				//
				// }
				// if (imgSoundOn.isBelong(X, Y) || imgSoundOff.isBelong(X, Y))
				// {
				// if (AppSettings.soundIsOn)
				// AppSettings.music.pause();
				// else
				// AppSettings.music.start();
				// AppSettings.soundIsOn = !AppSettings.soundIsOn;
				//
				// }
				for (int i = 0; i < colorRects.size(); i++) {
					if (colorRects.get(i).isBelong(X, Y)) {
						select(X, Y);
						// recID = colorRects.get(i).getID();
						break;
					} else {
						isSelected = false;

					}
				}

				break;

			case MotionEvent.ACTION_MOVE: // touch drag with the rectangle
				// move the rectangles the same as the finger

				currentImg = getImageById(recID);

				if (isSelected) {
					currentImg = getImageById(recID);
					switch (recID) {

					case 3:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX());
							currentImg.setY(frameColorRect.getY());

							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}
						}
						break;
					case 4:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX()
									+ currentImg.getWidth());
							currentImg.setY(frameColorRect.getY());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 5:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- 2 * currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX() + 2
									* currentImg.getWidth());
							currentImg.setY(frameColorRect.getY());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 6:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- 3 * currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX() + 3
									* currentImg.getWidth());
							currentImg.setY(frameColorRect.getY());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 7:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY()
										- currentImg.getHeight()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX());
							currentImg.setY(frameColorRect.getY()
									+ currentImg.getHeight());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 8:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY()
										- currentImg.getHeight()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX()
									+ currentImg.getWidth());
							currentImg.setY(frameColorRect.getY()
									+ currentImg.getHeight());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 9:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- 2 * currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY()
										- currentImg.getHeight()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX() + 2
									* currentImg.getWidth());
							currentImg.setY(frameColorRect.getY()
									+ currentImg.getHeight());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 10:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- 3 * currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY()
										- currentImg.getHeight()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX() + 3
									* currentImg.getWidth());
							currentImg.setY(frameColorRect.getY()
									+ currentImg.getHeight());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 11:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY() - 2
										* currentImg.getHeight()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX());
							currentImg.setY(frameColorRect.getY() + 2
									* currentImg.getHeight());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 12:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY() - 2
										* currentImg.getHeight()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX()
									+ currentImg.getWidth());
							currentImg.setY(frameColorRect.getY() + 2
									* currentImg.getHeight());

							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 13:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- 2 * currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY() - 2
										* currentImg.getHeight()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX() + 2
									* currentImg.getWidth());
							currentImg.setY(frameColorRect.getY() + 2
									* currentImg.getHeight());

							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;
					case 14:
						if (Math.abs(currentImg.getX() - frameColorRect.getX()
								- 3 * currentImg.getWidth()) <= currentImg
								.getWidth() / 3
								&& Math.abs(currentImg.getY()
										- frameColorRect.getY() - 2
										* currentImg.getHeight()) <= currentImg
										.getHeight() / 2) {
							currentImg.setX(frameColorRect.getX() + 3
									* currentImg.getWidth());
							currentImg.setY(frameColorRect.getY() + 2
									* currentImg.getHeight());
							if (currentImg.getIsDrawable()) {

								PuzzleGame.this.playClick();
								currentImg.setIsDragable(false);
							}

						}
						break;

					}
					if (currentImg.getIsDrawable()) {
						getSelected().move(X - recentX, Y - recentY);
						recentX = X;
						recentY = Y;
					}

				}

				break;

			case MotionEvent.ACTION_UP:
				// touch drop - just do things here after dropping
				isPressedInShow = false;

				break;
			}
			// redraw the canvas
			invalidate();
			return true;

		}

		public void setIsSelected(boolean newValue) {
			isSelected = newValue;
		}

		public ImageRect getSelected() {
			return colorRects.get(colorRects.size() - 1);
		}

		public ImageRect getImageById(int id) {
			ImageRect sss = null;
			for (int i = 0; i < colorRects.size(); i++) {
				if (colorRects.get(i).getID() == id) {
					sss = colorRects.get(i);
				}
			}
			return sss;
		}

		public void select(int eventX, int eventY) {
			for (int i = colorRects.size() - 1; i >= 0; i--) {
				ImageRect rect = colorRects.get(i);
				if (rect.isBelong(eventX, eventY) && rect.getIsDrawable()) {
					colorRects.remove(i);
					colorRects.add(rect);
					recID = rect.getID();
					isSelected = true;
					recentX = eventX;
					recentY = eventY;
					// invalidate();
					return;
				}
			}
		}

		public void RandomPermutation(ArrayList<ImageRect> sequence) {
			Random random = new Random();
			for (int i = 0; i < sequence.size(); i++) {

				int swapIndex = random.nextInt(12);
				if (swapIndex != i) {
					ImageRect temp = sequence.get(i);
					sequence.remove(i);
					sequence.add(swapIndex, temp);
				}
			}

		}

	}

	public void showWinDialog() {
		alertDialog = new Dialog(this);
		alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		alertDialog = new Dialog(this,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		alertDialog.setContentView(R.layout.dialog);
		alertDialog.setCancelable(true);

		Button okButton = (Button) alertDialog.findViewById(R.id.OK_btn);
		okButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				alertDialog.cancel();
				layout.addView(buttonRestart);
			}
		});
		alertDialog.show();
	}

	public void playClick() {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
	}

	private void drawStaticLayout() {

		// Handling from XML
		buttonSound = (ImageView) findViewById(R.id.buttonSound);
		if (!AppSettings.soundIsOn) {
			buttonSound.setImageResource(R.drawable.sound_icon_off);
		} else {
			buttonSound.setImageResource(R.drawable.sound_icon_on);
		}
		buttonRestart = (ImageView) findViewById(R.id.buttonRestart);

		layout.removeView(buttonSound);
		layout.removeView(buttonRestart);

		layout.addView(buttonSound);
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

		buttonRestart.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				ImageRect.resetCount();
				startActivity(new Intent(getBaseContext(), PuzzleGame.class));
				finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ImageRect.resetCount();
	}

	private AdView adView;
	private String MY_AD_UNIT_ID;

	public void AdMobAdsRequest() {
		MY_AD_UNIT_ID = "a14fc5f9d5f1926";

		adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);

		// Lookup your LinearLayout assuming it’s been given
		// the attribute android:id="@+id/mainLayout"
		RelativeLayout.LayoutParams adMobLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		adMobLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		adView.setLayoutParams(adMobLayoutParams);

		// Add the adView to it
		layout.addView(adView);

		// Initiate a generic request to load it with an ad
		adView.loadAd(new AdRequest());
	}

}
