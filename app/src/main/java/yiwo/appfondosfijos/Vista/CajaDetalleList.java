package yiwo.appfondosfijos.Vista;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yiwo.appfondosfijos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CajaDetalleList extends Fragment {


    public CajaDetalleList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caja_detalle_list, container, false);
    }

}
