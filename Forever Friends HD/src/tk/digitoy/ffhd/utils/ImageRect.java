package tk.digitoy.ffhd.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class ImageRect {
	 private Bitmap img; // the image of the rect
	 private int coordX = 0; // the x coordinate at the canvas
	 private int coordY = 0; // the y coordinate at the canvas
	 private int id; // gives every rect his own id, for now not necessary
	 private static int count = 0;
	 private boolean goRight = true;
	 private boolean goDown = true;
	 private int width;
	 private int height;
	 private boolean isDrawable;
	 
	 
		public ImageRect(Context context, int drawable, Point point) {

			BitmapFactory.Options opts = new BitmapFactory.Options();

	        img = BitmapFactory.decodeResource(context.getResources(), drawable, opts); 
	        width = img.getWidth();
	        height = img.getHeight();
	        isDrawable = false;	        
	        id=count;
			count++;
			coordX= point.x;
			coordY = point.y;
		}
		
		public ImageRect(Context context, int drawable, Point point, int scaleHeight, int scalewidth) {

			 BitmapFactory.Options options = new BitmapFactory.Options();
		        //Load image from resource
		    img = BitmapFactory.decodeResource(context.getResources(), 
		        		drawable, options);
		        //Scale to target size
		    img = Bitmap.createScaledBitmap(img, scaleHeight, scalewidth, true); 
//	        width = img.getWidth();
//	        height = img.getHeight();
	        height = scalewidth;
	        width = scaleHeight;
	        isDrawable = true;
	        id=count;
			count++;
			coordX= point.x;
			coordY = point.y;

		}
		
		public static int getCount() {
			return count;
		}
		
		public static void resetCount() {
			count = 0;
		}
		
		public void setX(int newValue) {
	        coordX = newValue;
	    }
		
		public int getX() {
			return coordX;
		}

		public void setY(int newValue) {
	        coordY = newValue;
	   }
		
		public int getY() {
			return coordY;
		}
		
		public int getID() {
			return id;
		}
		
		public Bitmap getBitmap() {
			return img;
		}
		
		public int getWidth(){
			return width;
		}
		
		public int setWidth(int newWidth){
			return width;
		}
		
		public int getHeight(){
			return height;
		}
		
		public boolean getIsDrawable(){
			return isDrawable;
		}
		
		public void setIsDragable(boolean value){
			isDrawable = value;
		}
		
		public void move(int dX, int dY){
			this.coordX += dX;
			this.coordY += dY;
		}
		
		public boolean isBelong (int X, int Y){
			
			return (X > getX() && X < getX() + width && Y > getY()	&& Y < getY() + height);			
		}
		
		public void moveRect(int goX, int goY) {
			// check the borders, and set the direction if a border has reached
			if (coordX > 270){
				goRight = false;
			}
			if (coordX < 0){
				goRight = true;
			}
			if (coordY > 400){
				goDown = false;
			}
			if (coordY < 0){
				goDown = true;
			}
			// move the x and y 
			if (goRight){
				coordX += goX;
			}else
			{
				coordX -= goX;
			}
			if (goDown){
				coordY += goY;
			}else
			{
				coordY -= goY;
			}
			
		}
}
