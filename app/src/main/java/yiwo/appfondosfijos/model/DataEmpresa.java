package yiwo.appfondosfijos.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import yiwo.appfondosfijos.Controlador.SQL.BDEmpresa;
import yiwo.appfondosfijos.Controlador.SQL.ConexionSQL;

public class DataEmpresa {

    private String TAG = "DataEmpresa";

    private ConexionSQL conexionSQL = new ConexionSQL();
    private BDEmpresa bddata = new BDEmpresa();
    private ArrayList<List<String>> Fulllist = new ArrayList<>();
    private DataConexion dataConexion = new DataConexion();
    private DataPuntoVenta dataPuntoVenta = new DataPuntoVenta();
    private DataCentroCostos dataCentroCostos = new DataCentroCostos();
    private DataUnidadNegocio dataUnidadNegocios = new DataUnidadNegocio();

    public void setSqLiteOpenHelper(SQLiteOpenHelper sqLiteOpenHelper) {
        dataConexion.setSqLiteOpenHelper(sqLiteOpenHelper);
        dataPuntoVenta.setSqLiteOpenHelper(sqLiteOpenHelper);
        dataCentroCostos.setSqLiteOpenHelper(sqLiteOpenHelper);
        dataUnidadNegocios.setSqLiteOpenHelper(sqLiteOpenHelper);
    }

    public ArrayList<List<String>> getList(String nombre) {
        ArrayList<List<String>> list = new ArrayList<>();
        try {
            if (Fulllist.size() > 0 && nombre.equals("")) {
                return Fulllist;
            } else {
                Connection connection = conexionSQL.getConnection(dataConexion.getSQLiteIP());
                list = bddata.getList(connection, nombre);
                connection.close();
                if (nombre.equals("")) {
                    Fulllist = list;
                    return Fulllist;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "getList " + e.getMessage());
        }
        return list;
    }


    public ArrayList<List<String>> getPredeterminados(String Codigo_Empresa) {

        ArrayList<List<String>> list = new ArrayList<>();
        try {
            Connection connection = conexionSQL.getConnection(dataConexion.getSQLiteIP());
            list.add(dataPuntoVenta.getPredeterminado(connection, Codigo_Empresa));
            list.add(dataCentroCostos.getPredeterminado(connection, Codigo_Empresa));
            list.add(dataUnidadNegocios.getPredeterminado(connection, Codigo_Empresa));
            connection.close();

        } catch (Exception e) {
            Log.d(TAG, "getList " + e.getMessage());
        }
        return list;
    }
}