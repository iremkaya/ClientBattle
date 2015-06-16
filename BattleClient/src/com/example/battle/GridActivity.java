package com.example.battle;



import com.example.battle.Ship;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



public class GridActivity extends View{

	public  static final int BOYUT = 3;
	private final Context context;
	private Paint paint;
	private float cellSize;
	private Rect border;// dikdörtgen çizsin diye
	public static int SHIP_START_Y;
	public static int CARRIER_START_X;
	public static int BATTLESHIP_START_X;
	public static int CRUISER_START_X;
	public static int SUBMARINE_START_X;
	public static int DESTROYER_START_X;
	public Ship ships[] = new Ship[3];
	public static Ship cruiser;
	public static Ship submarine;
	public static Ship destroyer;
	public static Ship draggingShip = null;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			for (int i = 0; i < ships.length; i++) {
				if (ships[i].containsPoint((int)event.getX(), (int)event.getY())) {
					draggingShip = ships[i];
					break;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (draggingShip != null) {
				draggingShip.setX((int)(event.getX() - draggingShip.getWidth() / 2));
				draggingShip.setY((int)(event.getY() - draggingShip.getHeight() / 2));
			}
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			if (draggingShip != null) {
				draggingShip.snapToGrid();
				draggingShip = null;
				invalidate();
			}
			break;
		}
		return true;
	}
	public GridActivity(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
	}
	public void resetShips() {
		cruiser = new Ship(context, this, Ship.ShipType.CRUISER);
		submarine = new Ship(context, this, Ship.ShipType.SUBMARINE);
		destroyer = new Ship(context, this, Ship.ShipType.DESTROYER);
     	ships[0] = cruiser;
		ships[1] = submarine;
		ships[2] = destroyer;
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		this.border = new Rect(BOYUT, BOYUT, w - BOYUT, w - BOYUT);
		this.cellSize = (float) (border.width() / 10.0);
		calculateInitialShipPlacements();
		resetShips();
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(border, paint);
		drawGridLines(canvas);
		for (Ship ship : ships) {
			ship.draw(canvas);
		}
		
	}

	private void drawGridLines(Canvas canvas) {
		for (int col = 1; col < 10; col++) {// SUTUN 
			canvas.drawLine(BOYUT +(int) cellSize * col, BOYUT,
					BOYUT + (int) cellSize * col, BOYUT + border.height(), paint);
		}
		for (int row = 1; row < 10; row++) {//SATIR
			canvas.drawLine(BOYUT, BOYUT +(int) cellSize * row,
					BOYUT + border.height(), BOYUT +(int) cellSize * row, paint);
		}
	}
	private void calculateInitialShipPlacements() {
		SHIP_START_Y = (int) (BOYUT * 2 + cellSize * 10);
		CRUISER_START_X = (int) (BOYUT + cellSize * 4);
		SUBMARINE_START_X = (int) (BOYUT + cellSize * 6);
		DESTROYER_START_X = (int) (BOYUT + cellSize * 8);
	}
	public float getCellSize() {
		return cellSize;
	}
	



	}

