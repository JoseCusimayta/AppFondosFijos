package yiwo.appfondosfijos.model;

import android.util.Log;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import yiwo.appfondosfijos.Controlador.SQL.BDCaja;
import yiwo.appfondosfijos.Controlador.SQL.ConexionSQL;

public class DataCaja {
    private String TAG = "DataCaja";
    private ConexionSQL conexionSQL= new ConexionSQL();
    private BDCaja bdCaja= new BDCaja();
    private ArrayList<List<String>> Fulllist= new ArrayList<>();
    private DataConexion dataConexion= new DataConexion();

    public ArrayList<List<String>> getList(String nombre){
        ArrayList<List<String>> list= new ArrayList<>();
        try {
            if(Fulllist.size()>0 && nombre.equals("")){
                return Fulllist;
            }else {
                Connection connection = conexionSQL.getConnection("192.168.1.111");
                list= bdCaja.getList(connection, dataConexion.getEmpresa().get(0),nombre);
                connection.close();
                if (nombre.equals("")){
                    Fulllist=list;
                    return Fulllist;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "getList " + e.getMessage());
        }
        return list;
    }

    public Boolean garudarCaja(){
        try {
            Connection connection = conexionSQL.getConnection("192.168.1.111");
            bdCaja.insertarCabecera(connection);
            bdCaja.insertarDetalle(connection);
            connection.close();
            return true;
        } catch (Exception e) {
            Log.d(TAG, "garudarCaja " + e.getMessage());
        }
        return false;
    }
}
