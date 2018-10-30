package yiwo.appfondosfijos.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import yiwo.appfondosfijos.Controlador.SQL.ConexionSQL;
import yiwo.appfondosfijos.Controlador.SQLite.SQLiteConfiguraciones;

public class DataConexion {
    private String TAG = "DataConexion";
    private String TAG_IP = "IP";
    private String TAG_Codigo_Empresa = "Codigo_Empresa";
    private String TAG_Codigo_Usuario = "Codigo_Usuario";
    private String TAG_Nombre_Usuario = "Nombre_Usuario";
    private String TAG_Nombre_Vendedor = "Nombre_Vendedor";
    private String TAG_Celular_Usuario = "Celular_Usuario";
    private String TAG_Email_Usuario = "Email_Usuario";
    private String TAG_Codigo_CentroCostos = "Codigo_CentroCostos";
    private String TAG_Nombre_CentroCostos = "Nombre_CentroCostos";
    private String TAG_Codigo_PuntoVenta = "Codigo_PuntoVenta";
    private String TAG_Nombre_PuntoVenta = "Nombre_PuntoVenta";
    private String TAG_Codigo_Almacen = "Codigo_Almacen";
    private String TAG_DireccionPuntoVenta_Almacen = "DireccionPuntoVenta_Almacen";
    private String TAG_Codigo_UnidadNegocio = "Codigo_UnidadNegocio";
    private String TAG_Nombre_UnidadNegocio = "Nombre_UnidadNegocio";

    private ConexionSQL conexionSQL = new ConexionSQL();

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteConfiguraciones sqLiteConfiguraciones = new SQLiteConfiguraciones();

    public void setSqLiteOpenHelper(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public Boolean verificarConexion() {
        try {
            String IP = conexionSQL.getUsableIP();
            Log.d(TAG, "Verificando conexion disponile: " + IP);
            SQLiteConfiguraciones sqLiteConfiguraciones = new SQLiteConfiguraciones();
            if (getSQLiteIP().length() > 1)
                sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_IP, IP);
            else
                sqLiteConfiguraciones.update(sqLiteOpenHelper, TAG_IP, IP);
            return true;
        } catch (Exception e) {
            Log.d(TAG, "verificarConexion " + e.getMessage());
            return false;
        }
    }

    public Boolean getLogin() {
        //Buscamos la información del codigo de la empresa que se registra al ingresra el usuario
        return getEmpresa().size() > 0;
    }

    public String getSQLiteIP() {
        return sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_IP);
    }

    public void insertarEmpresa(String Codigo, String Nombre, String RUC) {
        try {
            SQLiteConfiguraciones sqLiteConfiguraciones = new SQLiteConfiguraciones();
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Codigo_Empresa, Codigo);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Codigo_Empresa, Nombre);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Codigo_Empresa, RUC);
        } catch (Exception e) {
            Log.d(TAG, "verificarConexion " + e.getMessage());
        }
    }

    public List<String> getEmpresa() {
        List<String> list = new ArrayList<>();
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Codigo_Empresa));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Nombre_Usuario));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Nombre_Vendedor));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Celular_Usuario));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Email_Usuario));
        return list;
    }

    public void insertarUsuario(
            String Codigo,
            String Usuario,
            String Nombre,
            String Celular,
            String Email) {
        try {
            SQLiteConfiguraciones sqLiteConfiguraciones = new SQLiteConfiguraciones();
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Codigo_Usuario, Codigo);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Nombre_Usuario, Usuario);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Nombre_Vendedor, Nombre);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Celular_Usuario, Celular);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Email_Usuario, Email);
        } catch (Exception e) {
            Log.d(TAG, "insertarDatosUsuario " + e.getMessage());
        }
    }

    public List<String> getUsuario() {
        List<String> list = new ArrayList<>();
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Codigo_Usuario));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Nombre_Usuario));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Nombre_Vendedor));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Celular_Usuario));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Email_Usuario));
        return list;
    }

    public void insertarCentroCostos(
            String Codigo,
            String Nombre) {
        try {
            SQLiteConfiguraciones sqLiteConfiguraciones = new SQLiteConfiguraciones();
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Codigo_CentroCostos, Codigo);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Nombre_CentroCostos, Nombre);
        } catch (Exception e) {
            Log.d(TAG, "insertarDatosUsuario " + e.getMessage());
        }
    }

    public List<String> getCentroCostos() {
        List<String> list = new ArrayList<>();
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Codigo_CentroCostos));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Nombre_CentroCostos));
        return list;
    }

    public void insertarPuntoVenta(
            String Codigo,
            String Nombre,
            String Almacen,
            String Direccion) {
        try {
            SQLiteConfiguraciones sqLiteConfiguraciones = new SQLiteConfiguraciones();
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Codigo_PuntoVenta, Codigo);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Nombre_PuntoVenta, Nombre);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Codigo_Almacen, Almacen);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_DireccionPuntoVenta_Almacen, Direccion);
        } catch (Exception e) {
            Log.d(TAG, "insertarDatosUsuario " + e.getMessage());
        }
    }

    public List<String> getPuntoVenta() {
        List<String> list = new ArrayList<>();
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Codigo_PuntoVenta));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Nombre_PuntoVenta));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Codigo_Almacen));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_DireccionPuntoVenta_Almacen));
        return list;
    }


    public void insertarUnidadNegocio(
            String Codigo,
            String Nombre) {
        try {
            SQLiteConfiguraciones sqLiteConfiguraciones = new SQLiteConfiguraciones();
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Codigo_UnidadNegocio, Codigo);
            sqLiteConfiguraciones.insert(sqLiteOpenHelper, TAG_Nombre_UnidadNegocio, Nombre);
        } catch (Exception e) {
            Log.d(TAG, "insertarDatosUsuario " + e.getMessage());
        }
    }

    public List<String> getUnidadNegocio() {
        List<String> list = new ArrayList<>();
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Codigo_UnidadNegocio));
        list.add(sqLiteConfiguraciones.getPropiedad(sqLiteOpenHelper, TAG_Nombre_UnidadNegocio));
        return list;
    }

    public Boolean setDatosIngreso(
            List<String> list_empresa,
            List<String> list_usuario,
            List<String> list_centroCostos,
            List<String> list_puntoVenta,
            List<String> list_unidadNegocios) {
        try {
            insertarEmpresa(list_empresa.get(0), list_empresa.get(1), list_empresa.get(2));
            insertarUsuario(list_usuario.get(0), list_usuario.get(1), list_usuario.get(2), list_usuario.get(3), list_usuario.get(4));
            insertarCentroCostos(list_centroCostos.get(0), list_centroCostos.get(1));
            insertarPuntoVenta(list_puntoVenta.get(0), list_puntoVenta.get(1), list_puntoVenta.get(2), list_puntoVenta.get(3));
            insertarUnidadNegocio(list_unidadNegocios.get(0), list_unidadNegocios.get(1));
            return true;
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return false;
        }
    }
}