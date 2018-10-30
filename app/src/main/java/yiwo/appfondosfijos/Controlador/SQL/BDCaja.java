package yiwo.appfondosfijos.Controlador.SQL;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class BDCaja {
    String TAG = "BDCaja";

    public ArrayList<List<String>> getList(Connection connection, String Codigo_Empresa, String nombre) {
        ArrayList<List<String>> arrayList = new ArrayList<>();
        try {
            String stsql =
                    "select \n" +
                    "cnum_ctacte," +
                    "ccod_banco," +
                    "descripcion," +
                    "cmoneda," +
                    "ccuenta," +
                    "conciliacion," +
                    "tipo_doc \n" +
                    "from \n" +
                    "HCTACTEB \n" +
                    "where \n" +
                    "ccod_empresa=? \n" +
                    "and caj_ban='C'";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, Codigo_Empresa);
//            query.setString(2, nombre + "%");
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                arrayList.add(Arrays.asList(
                        rs.getString("cnum_ctacte"),
                        rs.getString("ccod_banco"),
                        rs.getString("descripcion"),
                        rs.getString("cmoneda"),
                        rs.getString("ccuenta"),
                        rs.getString("conciliacion"),
                        rs.getString("tipo_doc")
                ));
            }
        } catch (Exception e) {
            Log.d(TAG, "- getList: " + e.getMessage());
        }
        return arrayList;
    }

    public Boolean insertarCabecera(Connection connection){
        try{
            String sql =
                    "INSERT INTO tabla_caja (campo1,campo2,campo3)values('valor1','valor2','valor3')";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            return  true;
        } catch (Exception e){
            Log.d(TAG, "- InsertarCabecera: " + e.getMessage());
            return false;
        }
    }
    public Boolean insertarDetalle(Connection connection){
        try{
            String sql =
                    "INSERT INTO tabla_caja (campo1,campo2,campo3)values('valor1','valor2','valor3')";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            return  true;
        } catch (Exception e){
            Log.d(TAG, "- InsertarCabecera: " + e.getMessage());
            return false;
        }
    }
}