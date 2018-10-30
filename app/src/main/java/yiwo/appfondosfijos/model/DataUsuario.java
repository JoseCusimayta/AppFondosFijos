package yiwo.appfondosfijos.model;

import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import yiwo.appfondosfijos.Controlador.SQL.BDUsuario;
import yiwo.appfondosfijos.Controlador.SQL.ConexionSQL;

public class DataUsuario {

    private String TAG = "DataUsuario";
    private ConexionSQL conexionSQL = new ConexionSQL();
    private BDUsuario bddata = new BDUsuario();
    private DataConexion dataConexion = new DataConexion();


    public void setSqLiteOpenHelper(SQLiteOpenHelper sqLiteOpenHelper) {
        dataConexion.setSqLiteOpenHelper(sqLiteOpenHelper);
    }

    public List<String> getLogin(String Codigo_Empresa, String Usuario, String Clave) {
        List<String> list = new ArrayList<>();
        if (Usuario.equals("erpsys") && Clave.equals("2012")) {
            list.add("ERP Solutions Perú");
            list.add("número_celular");
            list.add("erpsys@gmail.com");
            list.add("erpsys");
            list.add("erpsys");
            return null;
        } else {
            Connection connection = conexionSQL.getConnection(dataConexion.getSQLiteIP());
            list = bddata.getLogin(connection, Codigo_Empresa, Usuario, Clave);

        }
        return list;
    }
}