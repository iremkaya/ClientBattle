package com.example.battle;

import com.example.battle.GameeActivity;
import com.example.battle.NasillActivity;
import com.example.battle.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public int []Id= new int[1000] ;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Button button1 = (Button) findViewById(R.id.button1);
		   final Context context=this;
		       button1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					silme();
					for(int i=0;i<Id.length;i++){
						
						deleteEmployee(Id[i]);
					}
					Intent intent = new Intent(context, GameeActivity.class);
		            startActivity(intent);
					
				}
			});
		       Button button2 = (Button) findViewById(R.id.button2);
		         button2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(context, NasillActivity.class);
			            startActivity(intent);
						
					}
				});
			return true;
	}
	private String[] sutunlar = {"_id", "x","y"};
	Veritabani V1= new Veritabani(this);
	SQLiteDatabase db;
	void silme(){
		SQLiteDatabase db = V1.getReadableDatabase();
		Cursor cursorKayit = db.query("Oyun", sutunlar,
		null, null, null, null, null);
		int i=0;
		while(cursorKayit.moveToNext()){
		int id=cursorKayit.getInt(cursorKayit.getColumnIndex("_id"));
		Id[i]=id;
		i++;
	}
		cursorKayit.close();
	}
	protected void deleteEmployee(int id) {
		   
		  SQLiteDatabase db = V1.getWritableDatabase();
	         db.delete("Oyun","_id = ?", new String[] { String.valueOf(id) });
	         db.close();
	            
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
