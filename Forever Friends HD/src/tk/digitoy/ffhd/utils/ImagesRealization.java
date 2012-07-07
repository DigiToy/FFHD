package tk.digitoy.ffhd.utils;

import java.util.ArrayList;

import tk.digitoy.ffhd.activities.R;

import android.content.Context;
import android.graphics.Point;

public class ImagesRealization {

	static int order;
	private ArrayList<ImageRect> colorRects = new ArrayList<ImageRect>();
	private ImageRect colorFrameRect;
	private ImageRect backFrameColorRect;

	// metrics
	private int wholeW;
	private int wholeH;
	private int partW;
	private int partH;

	public ImagesRealization(Context context, int whichImage, Point point,
			Point point2, int w, int h) {

		partW = w * 748 / 5120; // 5120 = 1280x4
		partH = h * 464 / 2304; // 2304 = 768x3

		wholeW = partW * 4;
		wholeH = partH * 3;

		backFrameColorRect = new ImageRect(context, R.drawable.image_main,
				point2, wholeW, wholeH);

		switch (whichImage) {

		case 1:
			colorFrameRect = new ImageRect(context, R.drawable.p1_main, point2,
					wholeW, wholeH);

			colorRects.add(new ImageRect(context, R.drawable.p1_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p1_12, point,
					partW, partH));
			break;
		case 2:
			colorFrameRect = new ImageRect(context, R.drawable.p2_main, point2,
					wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p2_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p2_12, point,
					partW, partH));
			break;
		case 3:
			colorFrameRect = new ImageRect(context, R.drawable.p3_main, point2,
					wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p3_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p3_12, point,
					partW, partH));
			break;
		case 4:
			colorFrameRect = new ImageRect(context, R.drawable.p4_main, point2,
					wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p4_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p4_12, point,
					partW, partH));
			break;
		case 5:
			colorFrameRect = new ImageRect(context, R.drawable.p5_main, point2,
					wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p5_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p5_12, point,
					partW, partH));
			break;
		case 6:
			colorFrameRect = new ImageRect(context, R.drawable.p6_main, point2,
					wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p6_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p6_12, point,
					partW, partH));
			break;

		case 7:
			colorFrameRect = new ImageRect(context, R.drawable.p7_main, point2,
					wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p7_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p7_12, point,
					partW, partH));
			break;

		case 8:
			colorFrameRect = new ImageRect(context, R.drawable.p8_main, point2,
					wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p8_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p8_12, point,
					partW, partH));
			break;

		case 9:

			colorFrameRect = new ImageRect(context, R.drawable.p9_main, point2,
					wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p9_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p9_12, point,
					partW, partH));
			break;

		case 10:

			colorFrameRect = new ImageRect(context, R.drawable.p10_main,
					point2, wholeW, wholeH);
			colorRects.add(new ImageRect(context, R.drawable.p10_01, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_02, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_03, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_04, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_05, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_06, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_07, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_08, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_09, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_10, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_11, point,
					partW, partH));
			colorRects.add(new ImageRect(context, R.drawable.p10_12, point,
					partW, partH));
			break;

		}
	}

	public ArrayList<ImageRect> getArrayListOfImages() {
		return colorRects;
	}

	public ImageRect getFrameColorRect() {

		return colorFrameRect;
	}

	public ImageRect getBackFrameColorRect() {

		return backFrameColorRect;
	}

}
