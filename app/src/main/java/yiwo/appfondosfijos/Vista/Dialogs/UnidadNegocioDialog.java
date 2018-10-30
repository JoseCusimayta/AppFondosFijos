package yiwo.appfondosfijos.Vista.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import yiwo.appfondosfijos.Controlador.SQLite.ControladorSQLite;
import yiwo.appfondosfijos.model.DataUnidadNegocio;
import yiwo.appfondosfijos.model.Objects.UnidadNegocio;
import yiwo.appfondosfijos.model.ObjectsAdapter.UnidadNegocioAdapter;
import yiwo.appfondosfijos.R;

public class UnidadNegocioDialog {
    private String TAG = "UnidadNegocioDialog", Nombre = "";
    private ProgressBar progressBar;

    private ArrayList<UnidadNegocio> dataModels;
    private ListView listView;

    private Activity activity;
    private Dialog dialogo;
    private EditText et_buscar;
    private DataUnidadNegocio dataUnidadNegocio = new DataUnidadNegocio();
    private String codigo_Empresa;


    private UnidadNegocioDialog.BackGroundTask task;

    public interface Finalizar {
        void ResultadoUnidadNegocio(
                String cod
                , String name
        );

    }

    private UnidadNegocioDialog.Finalizar interfaz;


    public UnidadNegocioDialog(Activity activity, UnidadNegocioDialog.Finalizar actividad, String codigo_Empresa) {

        this.activity = activity;
        this.codigo_Empresa = codigo_Empresa;
        ControladorSQLite myDb = new ControladorSQLite(activity);
        dataUnidadNegocio.setSqLiteOpenHelper(myDb);
        dialogo = new Dialog(activity);
        dialogo.setContentView(R.layout.dialog_codname);
        dialogo.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        interfaz = actividad;
        listView = dialogo.findViewById(R.id.list);
        et_buscar = dialogo.findViewById(R.id.et_buscar);
        progressBar = dialogo.findViewById(R.id.progressBar);


        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Nombre = et_buscar.getText().toString();
                    task.cancel(true);
                    task.onPostExecute("");
                    task = new UnidadNegocioDialog.BackGroundTask();
                    task.execute("");
                } catch (Exception e) {
                    Log.d(TAG, "onTextChanged " + e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        task = new UnidadNegocioDialog.BackGroundTask();
        task.execute("");
        dialogo.show();
    }


    public class BackGroundTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            if (isCancelled())
                onPostExecute("");

            dataModels = new ArrayList<>();

            ArrayList<List<String>> arrayListDialog = dataUnidadNegocio.getList(codigo_Empresa,Nombre);

            for (int i = 0; i < arrayListDialog.size(); i++) {
                if (isCancelled())
                    break;
                dataModels.add(new UnidadNegocio(
                        arrayListDialog.get(i).get(0),
                        arrayListDialog.get(i).get(1)
                ));
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            if (!isCancelled()) {
                try {
                    listView.setAdapter(null);
                    UnidadNegocioAdapter adapter = new UnidadNegocioAdapter(dataModels, activity);

                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            UnidadNegocio dataModel = dataModels.get(position);
                            interfaz.ResultadoUnidadNegocio(
                                    dataModel.getCodigo(),
                                    dataModel.getNombre()
                            );
                            dialogo.dismiss();
                        }
                    });
                } catch (Exception e) {
                    Log.d(TAG, "onPostExecute "+e.getMessage());
                }
            }
            super.onPostExecute(s);
        }
    }
}