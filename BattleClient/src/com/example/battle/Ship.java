package com.example.battle;

import com.example.battle.R;
import com.example.battle.R.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

public class Ship {

	public enum ShipType {
		 CRUISER, SUBMARINE, DESTROYER
	}

	public enum Orientation {
		HORIZONTAL, VERTICAL
	}

	public int x;
	public int y;
	private int size;
	private ShipType type;
	private Orientation orientation;
	private BitmapDrawable bitmap_horizontal;
	private BitmapDrawable bitmap_vertical;
	private GridActivity grid;
	private float cellSize;
	private Paint paint;
	private static Context context;

	public Ship(Context context, GridActivity grid, ShipType type) {
		this.context = context;
		this.type = type;
		this.grid = grid;
		this.cellSize = grid.getCellSize();
		this.paint = new Paint();
		switch (type) {
			case CRUISER:
				size = 3;
				x = GridActivity.CRUISER_START_X;
				break;
			case SUBMARINE:
				size = 2;
				x = GridActivity.SUBMARINE_START_X;
				break;
			case DESTROYER:
				size = 1;
				x = GridActivity.DESTROYER_START_X;
				break;
		}
		this.y = GridActivity.SHIP_START_Y;
		this.orientation = Orientation.VERTICAL;
		this.bitmap_horizontal = getBitmapForShip(type);
		this.bitmap_horizontal = scaleBitmapToGrid(bitmap_horizontal);
		this.bitmap_vertical = rotateBitmap(bitmap_horizontal);
	}
	
	public void draw(Canvas canvas) {
		BitmapDrawable bitmap = (BitmapDrawable) valueByOrientation(bitmap_horizontal, bitmap_vertical);
		canvas.drawBitmap(bitmap.getBitmap(), x, y, paint);
	}

	public boolean containsPoint(int x, int y) {
		return (x > this.x && x < this.x + getWidth() &&
			y > this.y && y < this.y + getHeight());
	}

	public void snapToGrid() {
		this.x = (int)(((x / (int)cellSize) * cellSize) + grid.BOYUT);
		this.y = (int)(((y / (int)cellSize) * cellSize) + grid.BOYUT);
	}

	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }

	public int getWidth() {
		return ((BitmapDrawable)valueByOrientation(bitmap_horizontal, bitmap_vertical))
			.getBitmap().getWidth();
	}

	public int getHeight() {
		return ((BitmapDrawable)valueByOrientation(bitmap_horizontal, bitmap_vertical))
			.getBitmap().getHeight();
	}

	@SuppressWarnings("deprecation")
	public BitmapDrawable getBitmapForShip(ShipType type) {
			return new BitmapDrawable(
					BitmapFactory.decodeResource(context.getResources(), R.drawable.images));
		
	}

		@SuppressWarnings("deprecation")
		public BitmapDrawable rotateBitmap(BitmapDrawable bitmap) {
		Bitmap originalBitmap = bitmap.getBitmap();
		int width = originalBitmap.getWidth();
		int height = originalBitmap.getHeight();
		Matrix m = new Matrix();
		m.postRotate(90);
		Bitmap rotatedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, width, height, m,true);
		return new BitmapDrawable(rotatedBitmap);
	}
	public static void rotateShip(Ship bitmap) {
		Bitmap bitmapOrg = BitmapFactory.decodeResource(context.getResources(),R.drawable.images);

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix m = new Matrix();
		m.postRotate(90);
		Bitmap rotatedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height, m,true);
		BitmapDrawable bmd = new BitmapDrawable(rotatedBitmap);
	}
	// Assumes we're dealing with the original, horizontal image.
	public BitmapDrawable scaleBitmapToGrid(BitmapDrawable bitmap) {
		Bitmap originalBitmap = bitmap.getBitmap();
		int width = originalBitmap.getWidth();
		int height = originalBitmap.getHeight();
		float newWidth = cellSize * size;
		float newHeight = cellSize;
		Matrix m = new Matrix();
		m.postScale(newWidth / width, newHeight / height);
		Bitmap newBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, width, height, m, true);
		return new BitmapDrawable(newBitmap);
	}

	private Object valueByOrientation(Object ifHorizontal, Object ifVertical) {
		return (orientation == Orientation.HORIZONTAL ? ifHorizontal : ifVertical);
	}


}
