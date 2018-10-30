package yiwo.appfondosfijos.Controlador.SQL;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BDUnidadNegocio {
    private String TAG="BDPuntoVenta";
    public ArrayList<List<String>> getList(Connection connection, String Codigo_Empresa,String Nombre){
        ArrayList<List<String>> arrayList = new ArrayList<>();
        try {
            String stsql = "Select erp_codune, erp_nomune from erp_unidad_negocio where erp_codemp = ? and ( erp_nomune like ? or erp_codune like ?) order by erp_codune";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, Codigo_Empresa); // Codigo de la empresa
            query.setString(2, Nombre+"%"); //Codigo de la Unidad de Negocio
            query.setString(3, Nombre+"%"); //Nombre de la Unidad de Negocio
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                arrayList.add(Arrays.asList(
                        rs.getString("erp_codune"),
                        rs.getString("erp_nomune")
                ));
            }

        } catch (Exception e) {
            Log.d(TAG, "- getList: "+e.getMessage());
        }
        return arrayList;
    }

    public List<String> getPredeterminado(Connection connection,String Codigo_Empresa) {

        List<String> list=new ArrayList<>();
        try {
            String stsql = "Select TOP(1) erp_codune, erp_nomune from erp_unidad_negocio where erp_codemp = ? order by erp_codune";
            PreparedStatement query = connection.prepareStatement(stsql);
            query.setString(1, Codigo_Empresa); // Codigo de la empresa
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("erp_codune"));
                list.add(rs.getString("erp_nomune"));
            }
        } catch (Exception e) {
            Log.d(TAG, "- getPredeterminado: "+e.getMessage());
        }
        return list;
    }
}
