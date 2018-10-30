package yiwo.appfondosfijos.Controlador.SQL;

import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    String TAG="ConexionSQL";
    private String CarpetaImagenes = "Imagenes";
    private int PuertoSQL = 1433;
    private int PuertoImagenes = 8080;

    private String UsuarioSQL = "sa";
    private String PasswordSQL = "Solu123456";


    private String UsuarioAPP = "Admin";
    private String ClaveAPP = "12345678";

    private String DriverSQL = "net.sourceforge.jtds.jdbc.Driver";

    static String root = Environment.getExternalStorageDirectory().toString(); //Obetner el directorio padre
    public static File myDirectorio = new File(root + "/pedidos"); // Crear una carpeta para guardar las imagenes
    //Configuración ERP


    private String IP_LAN = "192.168.1.111";
    private String IP_Publica = "148.102.21.175";
    private String BD_Empresa = "Bd_Consultoria_2015";
    private String ServerSQL = "SQLSERVER2008R2";
    //Configuración Gumisa

/*public static String IP_LAN="192.168.0.5";
public static String IP_Publica="190.187.39.250";
public static String BD_Empresa="BD_Gumisa_2018_08_23";
public static String ServerSQL="SQLSERVER2008R2";*/

    /*
    RUC Empresa: 20600124782
    TLF Señor Pun: 962341227
    */


    public Connection getConnection(String IP) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;

        IP = IP + "/" + ServerSQL;
        Log.d(TAG, "Conectandose a: " + IP);

        try {
            Class.forName(DriverSQL).newInstance();
            String ConnectionURL = "jdbc:jtds:sqlserver://" + IP + ";"
                    + "databaseName=" + BD_Empresa + ";user=" + UsuarioSQL + ";password="
                    + PasswordSQL + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e(TAG, "ERROR SQLException - " + se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "ERROR ClassNotFound - " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "ERROR Exception - " + e.getMessage());
        }

        return connection;
    }

    public String getUsableIP() {
        if (isServerAvailable(IP_Publica, PuertoSQL))
            return IP_Publica;
        else if (isServerAvailable(IP_LAN, PuertoSQL))
            return IP_LAN;
        else
            return null;
    }


    private boolean isServerAvailable(String DATABASE_ADDR, int DATABASE_PORT) {
        final int TIMEOUT_MS = 1000; // 2 seconds
        // First check if we have network connectivity

        // Attempt to bind to database port
        boolean available = false;
        SocketAddress sockAddr = new InetSocketAddress(DATABASE_ADDR, DATABASE_PORT);
        Socket sock = new Socket();

        // On timeout, SocketTimeoutException is thrown.
        try {
            sock.connect(sockAddr, TIMEOUT_MS);
            available = true;
        } catch (Exception e) {
            Log.d(TAG,"isServerAvailable e "+e.getMessage());
        } finally {
            try {
                sock.close();
            } catch (Exception e2) {
                Log.d(TAG,"isServerAvailable e2 "+e2.getMessage());
            }
        }

        return available;
    }
}