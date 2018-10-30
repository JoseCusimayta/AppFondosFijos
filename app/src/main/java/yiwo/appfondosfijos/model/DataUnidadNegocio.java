package yiwo.appfondosfijos.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import yiwo.appfondosfijos.Controlador.SQL.BDUnidadNegocio;
import yiwo.appfondosfijos.Controlador.SQL.ConexionSQL;

public class DataUnidadNegocio {

    private String TAG = "DataUnidadNegocios";
    private ArrayList<List<String>> Fulllist = new ArrayList<>();

    private BDUnidadNegocio bddata = new BDUnidadNegocio();
    private ConexionSQL conexionSQL = new ConexionSQL();
    private DataConexion dataConexion = new DataConexion();

    public void setSqLiteOpenHelper(SQLiteOpenHelper sqLiteOpenHelper) {
        dataConexion.setSqLiteOpenHelper(sqLiteOpenHelper);
    }
    public ArrayList<List<String>> getList(String Codigo_Empresa, String Nombre) {
        ArrayList<List<String>> list = new ArrayList<>();
        try {
            if (Fulllist.size() > 0 && Nombre.equals("")) {
                return Fulllist;
            } else {
                Connection connection = conexionSQL.getConnection(dataConexion.getSQLiteIP());
                list = bddata.getList(connection, Codigo_Empresa, Nombre);
                connection.close();
                if (Nombre.equals("")) {
                    Fulllist = list;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "getList " + e.getMessage());
        }
        return list;
    }

    public List<String> getPredeterminado(Connection connection, String Codigo_Empresa) {
        List<String> list = new ArrayList<>();
        try {
            list = bddata.getPredeterminado(connection, Codigo_Empresa);
        } catch (Exception e) {
            Log.d(TAG, "getList " + e.getMessage());
        }
        return list;
    }

}
