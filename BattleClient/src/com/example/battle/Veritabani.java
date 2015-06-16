package com.example.battle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Veritabani extends SQLiteOpenHelper {
	private static final String Veritabani_Adi = "Veritabanim";
	private static final int Veritabani_Version = 1;
	
	public Veritabani(Context context) {
	super(context, Veritabani_Adi, null, Veritabani_Version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	db.execSQL("CREATE TABLE Oyun(_id INTEGER primary key AUTOINCREMENT,"
	+ "x int , y int, boyut int, oyuncu int,tip int);");
	db.execSQL("CREATE TABLE Skor(_id INTEGER primary key AUTOINCREMENT,"
			+ "skor int,oyuncu int);");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF EXIST Oyun");
	db.execSQL("DROP TABLE IF EXIST Skor");
	onCreate(db);
	}
  
	
}
