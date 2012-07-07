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

	public ImagesRealization(Context context, int whichImage, Point point,
			Point point2, int w, int h) {

		backFrameColorRect = new ImageRect(context, R.drawable.image_main,
				point2, w * 3 / 5, h * 3 / 5);

		switch (whichImage) {

		case 1:
			colorFrameRect = new ImageRect(context, R.drawable.image_p1_main,
					point2, w * 3 / 5, h * 3 / 5);

			colorRects.add(new ImageRect(context, R.drawable.image_p1_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p1_12,
					point, w * 3 / 20, h * 3 / 15));
			break;
		case 2:
			colorFrameRect = new ImageRect(context, R.drawable.image_p2_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p2_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p2_12,
					point, w * 3 / 20, h * 3 / 15));
			break;
		case 3:
			colorFrameRect = new ImageRect(context, R.drawable.image_p3_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p3_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p3_12,
					point, w * 3 / 20, h * 3 / 15));
			break;
		case 4:
			colorFrameRect = new ImageRect(context, R.drawable.image_p4_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p4_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p4_12,
					point, w * 3 / 20, h * 3 / 15));
			break;
		case 5:
			colorFrameRect = new ImageRect(context, R.drawable.image_p5_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p5_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p5_12,
					point, w * 3 / 20, h * 3 / 15));
			break;
		case 6:
			colorFrameRect = new ImageRect(context, R.drawable.image_p6_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p6_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p6_12,
					point, w * 3 / 20, h * 3 / 15));
			break;

		case 7:
			colorFrameRect = new ImageRect(context, R.drawable.image_p7_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p7_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p7_12,
					point, w * 3 / 20, h * 3 / 15));
			break;

		case 8:
			colorFrameRect = new ImageRect(context, R.drawable.image_p8_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p8_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p8_12,
					point, w * 3 / 20, h * 3 / 15));
			break;

		case 9:

			colorFrameRect = new ImageRect(context, R.drawable.image_p9_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p9_1, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_2, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_3, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_4, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_5, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_6, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_7, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_8, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_9, point,
					w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p9_12,
					point, w * 3 / 20, h * 3 / 15));
			break;

		case 10:

			colorFrameRect = new ImageRect(context, R.drawable.image_p10_main,
					point2, w * 3 / 5, h * 3 / 5);
			colorRects.add(new ImageRect(context, R.drawable.image_p10_1,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_2,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_3,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_4,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_5,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_6,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_7,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_8,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_9,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_10,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_11,
					point, w * 3 / 20, h * 3 / 15));
			colorRects.add(new ImageRect(context, R.drawable.image_p10_12,
					point, w * 3 / 20, h * 3 / 15));
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
