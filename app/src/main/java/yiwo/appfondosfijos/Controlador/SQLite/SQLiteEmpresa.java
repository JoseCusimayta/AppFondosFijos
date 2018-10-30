package yiwo.appfondosfijos.Controlador.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteEmpresa {

    private final String TABLE_NAME = "Empresa";
    private final String COL_1 = "Codigo";
    private final String COL_2 = "Nombre";
    private final String COL_3 = "RUC";
    private final String COL_4 = "IP";

    public  final String Create="create table " + TABLE_NAME +"" +
            " (" +
            ""+COL_1+" TEXT PRIMARY KEY, " +
            ""+COL_2+" TEXT, " +
            ""+COL_3+" TEXT, " +
            ""+COL_4+" TEXT " +
            ")";
    public  final String Drop="DROP TABLE IF EXISTS "+TABLE_NAME;


    public  boolean insert(String Codigo,
                                 String Nombre,
                                 String RUC,
                                 String IP,
                                 SQLiteOpenHelper sqLiteOpenHelper) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Codigo);
        contentValues.put(COL_2,Nombre);
        contentValues.put(COL_3,RUC);
        contentValues.put(COL_4,IP);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        return result != -1;
    }


    public  Cursor getAllData(SQLiteOpenHelper sqLiteOpenHelper)  {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_NAME,null);
    }


    public  boolean update(String id, String CodEmp,SQLiteOpenHelper sqLiteOpenHelper) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,CodEmp);
        db.update(TABLE_NAME, contentValues, COL_1+" = ?",new String[] { id });
        return true;
    }

    public  Integer deleteData ( String id, SQLiteOpenHelper sqLiteOpenHelper) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1+" = ?",new String[] {id});
    }
    public  void deleteAllData ( SQLiteOpenHelper sqLiteOpenHelper) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.execSQL("Delete from "+TABLE_NAME);
    }
}
