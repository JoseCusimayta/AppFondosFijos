package yiwo.appfondosfijos.Vista;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import yiwo.appfondosfijos.R;
import yiwo.appfondosfijos.model.DataCaja;
import yiwo.appfondosfijos.model.TableAdapter;
import yiwo.appfondosfijos.Vista.Dialogs.CajaDialog;

public class AgregarDocumentos extends Fragment implements View.OnClickListener, CajaDialog.Finalizar {

    View view;
    Context context;
    TextInputLayout txtInput_caja, txtInput_saldo;
    TextInputEditText et_caja, et_saldo;
    TextInputEditText et_asiento, et_importe, et_tipoDocumento, et_serieDocumento, et_numeroDocumento, et_centroCostos, et_descripcion;
    Button btn_ingresar_documentos;
    Button btn_agregar, btn_nuevo, btn_guardar;
    LinearLayout linearly_caja, linearly_ingresar_caja;
    TextView tv_fecha;

    TableAdapter tableAdapter = new TableAdapter();
    TableLayout tl;

    String[] cabecera = {"No", "Name", "Propellant", "Destination", "Destination2", "Destination6", "Destination7", "Destination8"};

    String[][] spaceProbes = {
            {"1", "Pioneer", "Chemical", "Jupiter", "Jupiter", "Texto largo", "Jupiter", "Jupiter"},
            {"2", "Voyager", "Plasma", "Andromeda", "Jupiter", "Texto más largo", "Jupiter", "Jupiter"},
            {"3", "Casini", "Solar", "Saturn", "Jupiter", "Texto demasiado largo", "Jupiter", "Jupiter"},
            {"4", "Spitzer", "Anti-Matter", "Andromeda", "Texto muchisimo más largo", "Chemical", "Jupiter", "Jupiter"},
            {"5", "Apollo", "Chemical", "Moon", "Jupiter", "Chemical", "Jupiter", "Jupiter"},
            {"6", "Curiosity", "Solar", "Mars", "Jupiter", "Chemical", "Jupiter", "Jupiter"},

    };

    DataCaja dataCaja=new DataCaja();

    public AgregarDocumentos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_agregar_documentos, container, false);
        context= getContext();
        setVariables();
        setActionClick();

        linearly_caja.setVisibility(View.VISIBLE);
        linearly_ingresar_caja.setVisibility(View.GONE);

        tableAdapter.setAddButonEliminar(true);
        tableAdapter.setAddNumeracion(true);
        tableAdapter.setHeader(context, tl, cabecera);
        tableAdapter.populateContenido(context, tl, spaceProbes);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.et_caja):
                CajaDialog cajaDialog= new CajaDialog(getActivity(),this);
                SimpleDateFormat FormatoFechas = new SimpleDateFormat("dd-MM-yyyy");
                String FechaActual = FormatoFechas.format(new Date());
                txtInput_saldo.setVisibility(View.VISIBLE);
                btn_ingresar_documentos.setVisibility(View.VISIBLE);
                et_caja.setText("Caja elegida Número 1");
                et_saldo.setText("S/. 100.00");
                tv_fecha.setText(FechaActual);
                hideSoftKeyboard(getActivity());
                break;
            case (R.id.btn_ingresar_documentos):
                linearly_ingresar_caja.setVisibility(View.VISIBLE);
                linearly_caja.setVisibility(View.GONE);
                break;
            case (R.id.btn_agregar):
                String[] Contenido = {
                        "NuevaFila",
                        getTextFromEditText(et_asiento),
                        getTextFromEditText(et_importe),
                        getTextFromEditText(et_tipoDocumento),
                        getTextFromEditText(et_serieDocumento),
                        getTextFromEditText(et_numeroDocumento),
                        getTextFromEditText(et_centroCostos),
                        getTextFromEditText(et_descripcion)};

                tableAdapter.addRow(context, tl, Contenido);
                break;
            case (R.id.btn_nuevo):
                tableAdapter.clear(context, tl, cabecera);
                break;
            case (R.id.btn_guardar):
                if(dataCaja.garudarCaja())
                    Toast.makeText(getActivity(), "Registro guardado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "No se ha podido guardar", Toast.LENGTH_SHORT).show();

                break;
        }
    }


    private void setVariables() {
        //region Inicio
        //region TextInputLayout
        txtInput_caja = view.findViewById(R.id.txtInput_caja);
        txtInput_saldo = view.findViewById(R.id.txtInput_saldo);
        //endregion
        //region Button
        btn_ingresar_documentos = view.findViewById(R.id.btn_ingresar_documentos);
        //endregion
        //endregion

        //region Detalle
        //region TextView
        tv_fecha = view.findViewById(R.id.tv_fecha);
        //endregion
        //region EditText
        et_caja = view.findViewById(R.id.et_caja);
        et_saldo = view.findViewById(R.id.et_saldo);
        et_asiento = view.findViewById(R.id.et_asiento);
        et_importe = view.findViewById(R.id.et_importe);
        et_tipoDocumento = view.findViewById(R.id.et_tipoDocumento);
        et_serieDocumento = view.findViewById(R.id.et_serieDocumento);
        et_numeroDocumento = view.findViewById(R.id.et_numeroDocumento);
        et_centroCostos = view.findViewById(R.id.et_centroCostos);
        et_descripcion = view.findViewById(R.id.et_descripcion);
        //endregion
        //region Button
        btn_agregar = view.findViewById(R.id.btn_agregar);
        btn_nuevo = view.findViewById(R.id.btn_nuevo);
        btn_guardar = view.findViewById(R.id.btn_guardar);
        //endregion
        //region LinearLayout
        linearly_caja = view.findViewById(R.id.linearly_caja);
        linearly_ingresar_caja = view.findViewById(R.id.linearly_ingresar_caja);
        //endregion
        //region TableLayout
        tl = view.findViewById(R.id.main_table);
        //endregion
        //endregion

    }

    private void setActionClick() {
        et_caja.setOnClickListener(this);
        btn_ingresar_documentos.setOnClickListener(this);
        btn_agregar.setOnClickListener(this);
        btn_nuevo.setOnClickListener(this);
        btn_guardar.setOnClickListener(this);
    }

    private static void hideSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private String getTextFromEditText(EditText editText) {
        try {
            return editText.getText().toString();
        } catch (Exception ex) {
            return "";
        }
    }

    @Override
    public void Resultado_CajaDialog(String cod, String name, String ruc) {

    }
}