package com.example.battle;

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
import android.widget.TextView;

public class OyunActivity extends ActionBarActivity {

	public int []Id= new int[1000] ;
	final Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oyun);
		
	/*	RelativeLayout	rl = (RelativeLayout) findViewById(R.id.rl);
      
        
		ImageView  img = new ImageView (OyunActivity.this);
        
        img.setImageResource(R.drawable.blue_square);
        
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( 
        		(int) LayoutParams.WRAP_CONTENT, (int) LayoutParams.WRAP_CONTENT);
        
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        
        img.setLayoutParams(params);
                
        rl.addView(img);?*/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.oyun, menu);
		Button button1 = (Button) findViewById(R.id.button1);
		  
		   button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kayitGor();
			}
			});
		   Button button2 = (Button) findViewById(R.id.button2);
			  
		   button2.setOnClickListener(new View.OnClickListener() {
           
			@Override
			public void onClick(View v) {
				silme();
			for(int i=0;i<Id.length;i++){
				
				deleteEmployee(Id[i]);
			}
			TextView textView1 = (TextView) findViewById(R.id.textView1);
			textView1.setText("");
			}
			});
		   Button button3 = (Button) findViewById(R.id.button3);
			  
		   button3.setOnClickListener(new View.OnClickListener() {
           
			@Override
			public void onClick(View v) {
				queryTest();
			}
			});
		   Button button4 = (Button) findViewById(R.id.button4);
			  
		   button4.setOnClickListener(new View.OnClickListener() {
           
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ClientRenkActivity.class);
	            startActivity(intent);
			}
			});
		return true;
	}
	Veritabani V1= new Veritabani(this);
	SQLiteDatabase db;
	public void queryTest(){

		Cursor sonuc;
				sonuc=db.rawQuery("SELECT * from Oyun", null);
				TextView textView1 = (TextView) findViewById(R.id.textView1);
				while(sonuc.moveToNext()){
					int id=sonuc.getInt(sonuc.getColumnIndex("_id"));
					int x=sonuc.getInt(sonuc.getColumnIndex("x"));
					int y = sonuc.getInt(sonuc.getColumnIndex("y"));
				
					textView1.setText(textView1.getText()+"\n"+" "+id+" "+x+" "+y);
					}
				sonuc.close();

		}
	private String[] sutunlar = {"_id", "x","y"};
	
	protected void deleteEmployee(int id) {
   
		  SQLiteDatabase db = V1.getWritableDatabase();
	         db.delete("Oyun","_id = ?", new String[] { String.valueOf(id) });
	         db.close();
	            
	}
	private void kayitGor(){
	SQLiteDatabase db = V1.getReadableDatabase();
	Cursor cursorKayit = db.query("Oyun", sutunlar,
	null, null, null, null, null);
	while(cursorKayit.moveToNext()){
	int id=cursorKayit.getInt(cursorKayit.getColumnIndex("_id"));
	int x=cursorKayit.getInt(cursorKayit.getColumnIndex("x"));
	int y = cursorKayit.getInt(cursorKayit.getColumnIndex("y"));
	TextView textView1 = (TextView) findViewById(R.id.textView1);
	textView1.setText(textView1.getText()+"\n"+" "+id+" "+x+" "+y);
	}
	cursorKayit.close();
	}
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
