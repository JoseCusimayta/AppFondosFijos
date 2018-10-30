package yiwo.appfondosfijos.Controlador.SQL;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDCentroCostos {
    private String TAG="BDCentroCostos";
    public ArrayList<List<String>> getList(Connection connection, String Codigo_Empresa, String Nombre) {

        ArrayList<List<String>> arrayList = new ArrayList<>();
        try {

            String stsql="select \n" +
                    "ccod_cencos, \n" +
                    "cnom_cencos \n" +
                    "from \n" +
                    "hcencos \n" +
                    "where \n" +
                    "ccod_empresa = ? \n" +
                    "and \n" +
                    "(\n" +
                    "ccod_cencos like ? \n" +
                    "or cnom_cencos like ?\n" +
                    ") \n" +
                    "order by ccod_cencos";

            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, Codigo_Empresa); // Codigo de la empresa
            query.setString(2, Nombre + "%"); //Codigo del Punto de Venta
            query.setString(3, Nombre + "%"); //Nombre del Punto de Venta

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                arrayList.add(Arrays.asList(
                        rs.getString("ccod_cencos"),
                        rs.getString("cnom_cencos")
                ));
            }

        } catch (Exception e) {
            Log.d(TAG, "- getList: " + e.getMessage());
        }
        return arrayList;
    }

    public List<String> getPredeterminado(Connection connection, String Codigo_Empresa) {

        List<String> list = new ArrayList<>();

        try {

            String stsql="select TOP(1) \n" +
                    "ccod_cencos, \n" +
                    "cnom_cencos \n" +
                    "from \n" +
                    "hcencos \n" +
                    "where \n" +
                    "ccod_empresa = ? \n" +
                    "order by ccod_cencos";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, Codigo_Empresa); // Codigo de la empresa

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("ccod_cencos"));
                list.add(rs.getString("cnom_cencos"));
            }

        } catch (Exception e) {
            Log.d(TAG, "- getPredeterminado: " + e.getMessage());
        }
        return list;
    }
}
