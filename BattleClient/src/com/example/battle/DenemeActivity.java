package com.example.battle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class DenemeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deneme);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		   getMenuInflater().inflate(R.menu.deneme, menu);
		  TableLayout table= (TableLayout) findViewById(R.id.tableforbuttons);
	       for(int row=0;row<5;row++){
	    	   TableRow tablerow= new TableRow(this);
	    	   tablerow.setLayoutParams(new TableLayout.LayoutParams(
	    	   TableLayout.LayoutParams.MATCH_PARENT,
	    	   TableLayout.LayoutParams.MATCH_PARENT,1.0f));
	    	   table.addView(tablerow);
	    	   
	       
	       for(int col=0;col<10;col++){
	    	    ImageView image = new ImageView(this);
	    	    image.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,1.0f));
	    	    
	    	    image.setMaxHeight(20);
	    	    image.setMaxWidth(20);
	    	    image.setImageResource(R.drawable.blue_square);
	    	    tablerow.addView(image);
	    	    
	    	  
	       }
	       }
	     
		return true;
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
