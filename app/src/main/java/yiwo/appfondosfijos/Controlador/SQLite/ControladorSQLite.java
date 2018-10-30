package yiwo.appfondosfijos.Controlador.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControladorSQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FondosFijos";

    public ControladorSQLite(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }
    private SQLiteEmpresa sqLiteEmpresa= new SQLiteEmpresa();
    private SQLiteConfiguraciones sqLiteConfiguraciones= new SQLiteConfiguraciones();



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqLiteEmpresa.Create);
        db.execSQL(sqLiteConfiguraciones.Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqLiteEmpresa.Drop);
        db.execSQL(sqLiteConfiguraciones.Drop);
        onCreate(db);
    }

}
