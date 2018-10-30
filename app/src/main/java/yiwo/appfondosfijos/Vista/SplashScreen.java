package yiwo.appfondosfijos.Vista;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yiwo.appfondosfijos.Controlador.SQLite.ControladorSQLite;
import yiwo.appfondosfijos.model.DataConexion;
import yiwo.appfondosfijos.R;
import yiwo.appfondosfijos.model.CodigosUtiles;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreen extends Fragment {


    String TAG = "FragSplashScreen";
    CodigosUtiles codigosUtiles = new CodigosUtiles();
    ControladorSQLite myDb;
    Fragment fragment;
    DataConexion dataConexion= new DataConexion();

    public SplashScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        myDb = new ControladorSQLite(getContext());
        dataConexion.setSqLiteOpenHelper(myDb);
        fragment = new Login();

        try {
            BackGroundTask task = new BackGroundTask();
            task.execute("");
        } catch (Exception e) {
            Log.d(TAG, "onCreateView " + e.getMessage());
        }

        return view;
    }


    public void CambiarFragment(Fragment fragment) {
        codigosUtiles.setAddtoBackStack(false);
        codigosUtiles.replaceFragment(fragment, getFragmentManager());
    }

    public class BackGroundTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            if (dataConexion.verificarConexion()) {
                if (dataConexion.getLogin())
                    fragment = new AgregarDocumentos(); //Si ha ingrresaddo
                else
                    fragment = new Login();//Si no ha ingresado
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            CambiarFragment(fragment);
            super.onPostExecute(s);
        }
    }
}