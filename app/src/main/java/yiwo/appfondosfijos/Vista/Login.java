package yiwo.appfondosfijos.Vista;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yiwo.appfondosfijos.Controlador.SQLite.ControladorSQLite;
import yiwo.appfondosfijos.model.CodigosUtiles;
import yiwo.appfondosfijos.model.DataConexion;
import yiwo.appfondosfijos.model.DataEmpresa;
import yiwo.appfondosfijos.model.DataUsuario;
import yiwo.appfondosfijos.R;
import yiwo.appfondosfijos.Vista.Dialogs.CentroCostosDialog;
import yiwo.appfondosfijos.Vista.Dialogs.EmpresaDialog;
import yiwo.appfondosfijos.Vista.Dialogs.PuntoVentaDialog;
import yiwo.appfondosfijos.Vista.Dialogs.UnidadNegocioDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements View.OnClickListener, EmpresaDialog.Finalizar, PuntoVentaDialog.Finalizar, CentroCostosDialog.Finalizar, UnidadNegocioDialog.Finalizar {
    private String TAG = "Login";

    TextInputEditText et_empresa, et_puntoVenta, et_centroCostos, et_unidadNegocios, et_usuario, et_clave;
    Button btn_ingresar;
    LinearLayout ly_datos;
    View view;

    String codigo_Empresa;

    ControladorSQLite myDb;

    CodigosUtiles codigosUtiles= new CodigosUtiles();

    DataEmpresa dataEmpresa = new DataEmpresa();
    DataUsuario dataUsuario = new DataUsuario();
    DataConexion dataConexion= new DataConexion();

    ArrayList<List<String>> predeterminados;
    List<String> list_empresa;
    List<String> list_centroCostos;
    List<String> list_puntoVenta;
    List<String> list_unidadNegocios;
    List<String> list_usuario;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        setVariables();
        setActionClick();
        setSQLite();
        return view;
    }

    private void setSQLite() {
        myDb = new ControladorSQLite(getContext());
        dataEmpresa.setSqLiteOpenHelper(myDb);
        dataUsuario.setSqLiteOpenHelper(myDb);

    }

    private void setVariables() {
        //region TextInputEditText
        et_empresa = view.findViewById(R.id.et_empresa);
        et_puntoVenta = view.findViewById(R.id.et_puntoVenta);
        et_centroCostos = view.findViewById(R.id.et_centroCostos);
        et_unidadNegocios = view.findViewById(R.id.et_unidadNegocios);
        et_usuario = view.findViewById(R.id.et_usuario);
        et_clave = view.findViewById(R.id.et_clave);
        //endregion

        //region Button
        btn_ingresar = view.findViewById(R.id.btn_ingresar);
        //endregion
        //region LinearLayout
        ly_datos = view.findViewById(R.id.ly_datos);
        //endregion


    }

    private void setActionClick() {
        et_empresa.setOnClickListener(this);
        et_puntoVenta.setOnClickListener(this);
        et_centroCostos.setOnClickListener(this);
        et_unidadNegocios.setOnClickListener(this);
        btn_ingresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.et_empresa):
                new EmpresaDialog(getActivity(), this);
                break;
            case (R.id.et_puntoVenta):
                new PuntoVentaDialog(getActivity(), this, codigo_Empresa);
                break;
            case (R.id.et_centroCostos):
                new CentroCostosDialog(getActivity(), this, codigo_Empresa);
                break;
            case (R.id.et_unidadNegocios):
                new UnidadNegocioDialog(getActivity(), this, codigo_Empresa);
                break;
            case (R.id.btn_ingresar):
                list_usuario = dataUsuario.getLogin(codigo_Empresa, codigosUtiles.getStringNotNull(et_usuario), codigosUtiles.getStringNotNull(et_clave));
                if (list_usuario.size() > 0) {
                    if(dataConexion.setDatosIngreso(list_empresa, list_usuario, list_centroCostos, list_puntoVenta, list_unidadNegocios))
                    {
                        codigosUtiles.replaceFragment(new AgregarDocumentos(),getFragmentManager());
                        Toast.makeText(getContext(), "Bienvenido "+list_usuario.get(3), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "No se ha podido iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                    Log.d(TAG, "Ingresado");
                } else
                    Toast.makeText(getContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "No logueado");
                break;
        }
    }

    @Override
    public void ResultadoEmpresa(String cod, String name, String ruc) {
        list_empresa=new ArrayList<>();
        list_empresa.add(cod);
        list_empresa.add(name);
        list_empresa.add(ruc);

        codigo_Empresa=cod;
        et_empresa.setText(ruc + " - " + name);

        predeterminados = dataEmpresa.getPredeterminados(cod);

        list_puntoVenta=predeterminados.get(0);
        list_centroCostos=predeterminados.get(1);
        list_unidadNegocios=predeterminados.get(2);

        et_puntoVenta.setText(list_puntoVenta.get(0) + " - " + list_puntoVenta.get(1));
        et_centroCostos.setText(list_centroCostos.get(0) + " - " + list_centroCostos.get(1));
        et_unidadNegocios.setText(list_unidadNegocios.get(0) + " - " + list_unidadNegocios.get(1));

        ly_datos.setVisibility(View.VISIBLE);
    }

    @Override
    public void ResultadoPuntoVenta(String codigo, String nombre, String almacen, String direccion) {
        list_puntoVenta=new ArrayList<>();
        list_puntoVenta.add(codigo);
        list_puntoVenta.add(nombre);
        list_puntoVenta.add(almacen);
        list_puntoVenta.add(direccion);
        et_puntoVenta.setText(codigo + " - " + nombre);
    }

    @Override
    public void ResultadoCentroCostos(String cod, String name) {
        list_centroCostos=new ArrayList<>();
        list_centroCostos.add(cod);
        list_centroCostos.add(name);
        et_centroCostos.setText(cod + " - " + name);

    }

    @Override
    public void ResultadoUnidadNegocio(String cod, String name) {
        list_unidadNegocios=new ArrayList<>();
        list_unidadNegocios.add(cod);
        list_unidadNegocios.add(name);
        et_unidadNegocios.setText(cod + " - " + name);

    }
}