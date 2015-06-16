package com.example.battle;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.battle.GridActivity;
public class GameeActivity extends ActionBarActivity {

	public Context context;
	ViewGroup mRrootLayout ;
	int cx;
	int cy;
	int dx;
	int dy;
	int sx;
	int sy;
	  Veritabani V1 =new Veritabani(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamee);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gamee, menu);
      
     
		Button button2 = (Button) findViewById(R.id.Button2);
		   final Context context=this;
		   button2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, MainActivity.class);
		            startActivity(intent);
					
				}
			});
		   Button button1 = (Button) findViewById(R.id.button1);
		  
		   button1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				   int cruiser_x=GridActivity.cruiser.x;
                   int cruiser_y=GridActivity.cruiser.y;
                   int destroyer_x=GridActivity.destroyer.x;
                   int destroyer_y=GridActivity.destroyer.y;
                   int submarine_x=GridActivity.submarine.x;
                   int submarine_y=GridActivity.submarine.y;
                   
                   if(cruiser_x>3)
               	    cx= (((cruiser_x)-3)/65);
                  else
               	   cx=0;
                  if(cruiser_y>3)
               	   cy= (((cruiser_x)-3)/65);
                  else
               	   cy=0;
                  for(int i=0;i<3;i++){
                  kayýtEkleme(cy+i,cx,3,0);
                  }
                  if(destroyer_x>3)
               	   dx= (((destroyer_x)-3)/65);
                  else
               	   dx=0;
                  if(destroyer_y>3)
               	   dy= (((destroyer_y)-3)/65);  
                  else
               	   dy=0;
                  
                  kayýtEkleme(dy,dx,1,0);
                  if(submarine_x>3)
               	   sx= (((submarine_x)-3)/65);
                  else
               	   sx=0;
                  if(submarine_y>3)
               	   sy= (((submarine_y)-3)/65);  
                  else
               	   sy=0;
            for(int i=0;i<2;i++){
                  kayýtEkleme(sy+i,sx,2,0);				}
                  
					Intent intent = new Intent(context, ClientRenkActivity.class);
		            startActivity(intent);
					
				}
			});
		return true;
	}
	  private void kayýtEkleme(int GelenNumara,int NumaraninKodu,int oyuncu,int tip){
	   		SQLiteDatabase db = V1.getWritableDatabase();
	   		ContentValues veriler = new ContentValues();
	   		veriler.put("x", GelenNumara);
	   		veriler.put("y", NumaraninKodu);
	   		veriler.put("oyuncu", oyuncu);
	   		veriler.put("tip", tip);
	   		db.insertOrThrow("Oyun", null, veriler);
	   		}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
