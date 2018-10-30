package yiwo.appfondosfijos.Controlador.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteConfiguraciones {
    private String TAG="SQLiteConfiguraciones";
    private String TABLE_NAME = "Configuraciones";
    private String COL_1 = "Codigo";
    private String COL_2 = "Descripcion";

    public String Create = "create table " + TABLE_NAME + "" +
            " (" +
            "" + COL_1 + " TEXT PRIMARY KEY, " +
            "" + COL_2 + " TEXT   " +
            ")";
    public String Drop = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public boolean insert(SQLiteOpenHelper sqLiteOpenHelper,
                          String Codigo,
                          String Descripcion
    ) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Codigo);
        contentValues.put(COL_2, Descripcion);
        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.d(TAG,"insert : "+Codigo + " y "+Descripcion);
        return result != -1;
    }


    public Cursor getAllData(SQLiteOpenHelper sqLiteOpenHelper) {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public String getPropiedad(SQLiteOpenHelper sqLiteOpenHelper, String TAG_Propiedad) {
        String descripcion = "";
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_2}, COL_1 + "=?", new String[]{TAG_Propiedad}, null, null, null);

        Log.d(TAG,"getPropiedad - Cantidad de datos: "+cursor.getCount());
        while (cursor.moveToNext()) {
            descripcion = cursor.getString(0);
        }
        cursor.close();
        Log.d(TAG,"getPropiedad : "+TAG_Propiedad + " y "+descripcion);
        return descripcion;
    }

    public Cursor getData(SQLiteOpenHelper sqLiteOpenHelper, String[] Columns, String selection, String[] selectionArgs) {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        return db.query(TABLE_NAME, Columns, selection, selectionArgs, null, null, null);

    }

    public boolean update(SQLiteOpenHelper sqLiteOpenHelper, String Codigo, String Descripcion) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Descripcion);
        db.update(TABLE_NAME, contentValues, COL_1 + " = ?", new String[]{Codigo});
        Log.d(TAG,"update : "+Codigo + " y "+Descripcion);
        return true;
    }

    public Integer deleteData(SQLiteOpenHelper sqLiteOpenHelper, String id) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1 + " = ?", new String[]{id});
    }

    public void deleteAllData(SQLiteOpenHelper sqLiteOpenHelper) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.execSQL("Delete from " + TABLE_NAME);
    }

}