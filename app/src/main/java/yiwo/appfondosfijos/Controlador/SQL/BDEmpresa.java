package yiwo.appfondosfijos.Controlador.SQL;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDEmpresa {
    String TAG = "BDEmpresa";

    public ArrayList<List<String>> getList(Connection connection, String nombre) {
        ArrayList<List<String>> arrayList = new ArrayList<>();
        try {
            String stsql = "select TOP(10) ccod_empresa,crazonsocial,cnum_ruc from Hempresa where (crazonsocial like ?  or ccod_empresa like ?) order by ccod_empresa";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, nombre + "%"); //Razón Social de la empresa
            query.setString(2, nombre + "%"); //Código de la empresa
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                arrayList.add(Arrays.asList(
                        rs.getString("ccod_empresa"),       //Código de la empresa
                        rs.getString("crazonsocial"),       //Razón Social de la empresa
                        rs.getString("cnum_ruc")            //Número de Ruc de la empresa
                ));
            }
        } catch (Exception e) {
            Log.d(TAG, "- getList: " + e.getMessage());
        }
        return arrayList;
    }

}
