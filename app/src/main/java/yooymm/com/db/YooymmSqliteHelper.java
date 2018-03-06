package yooymm.com.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import yooymm.com.util.Constant;

public class YooymmSqliteHelper extends SQLiteOpenHelper {

	

	public YooymmSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public YooymmSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	public YooymmSqliteHelper(Context context){
		super(context, Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建表
		db.execSQL(Constant.CREATE_PROVINCE);
		db.execSQL(Constant.CREATE_CITY);
		db.execSQL(Constant.CREATE_COUNTY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int i, int i1) {

	}
}