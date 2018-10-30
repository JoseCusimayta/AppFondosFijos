package yiwo.appfondosfijos.model;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TableAdapter {
    private Boolean addButonEliminar = false;
    private Boolean addNumeracion = false;

    private Integer count = 1;

    private int PaddingLeft = 20;
    private int PaddingTop = 10;
    private int PaddingRight = 20;
    private int PaddingBottom = 10;


    public void setAddButonEliminar(Boolean addButonEliminar) {
        this.addButonEliminar = addButonEliminar;
    }

    public void setAddNumeracion(Boolean addNumeracion) {
        this.addNumeracion = addNumeracion;
    }

    public void clear(Context context, TableLayout tableLayout, String[] Cabecera){
        tableLayout.removeAllViews();
        count = 1;
        setHeader(context,tableLayout,Cabecera);
    }

    public void setHeader(Context context, TableLayout tableLayout, String[] Cabecera) {
        TableRow tr_head = new TableRow(context);
        tr_head.setBackgroundColor(Color.parseColor("#aa66cc"));
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        if (addNumeracion) {
            TextView tv_numero = new TextView(context);
            tv_numero.setText("Número");
            tv_numero.setTextColor(Color.WHITE);
            tv_numero.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
            tr_head.addView(tv_numero);// add the column to the table row here
        }
        for (int i = 0; i < Cabecera.length; i++) {
            TextView tv_header = new TextView(context);
            tv_header.setText(Cabecera[i]);
            tv_header.setTextColor(Color.WHITE);
            tv_header.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
            tr_head.addView(tv_header);// add the column to the table row here
        }
        if (addButonEliminar) {
            TextView tv_eliminar = new TextView(context);
            tv_eliminar.setText("Eliminar");
            tv_eliminar.setTextColor(Color.WHITE);
            tv_eliminar.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
            tr_head.addView(tv_eliminar);// add the column to the table row here
        }

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }


    public void populateContenido(final Context context, TableLayout tableLayout, String[][] Contenido) {

        for (int i = 0; i < Contenido.length; i++) {
            final TableRow tr = new TableRow(context);

            if (count % 2 != 0) tr.setBackgroundColor(Color.GRAY);
            tr.setId(100 + count);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            if (addNumeracion) {
                TextView tv_numero = new TextView(context);
                tv_numero.setText(count.toString());
                tv_numero.setTextColor(Color.WHITE);
                tv_numero.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
                tr.addView(tv_numero);// add the column to the table row here
            }

            for (int j = 0; j < Contenido[0].length; j++) {
                String dato = Contenido[i][j];
                TextView tv_dato = new TextView(context);
//                tv_dato.setId(200 + count);
                tv_dato.setText(dato);
                tv_dato.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
                tv_dato.setTextColor(Color.WHITE);
                tv_dato.setGravity(Gravity.CENTER);
                tr.addView(tv_dato);
            }
            if (addButonEliminar) {
                Button btnEliminar = new Button(context);
                btnEliminar.setText("Eliminar");
                btnEliminar.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Fila eliminada ", Toast.LENGTH_SHORT).show();
                        tr.removeAllViews();
                    }
                });
                tr.addView(btnEliminar);
            }
            tableLayout.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            count++;
        }
    }

    public void addRow(final Context context, TableLayout tableLayout, String[] Contenido) {

        final TableRow tr = new TableRow(context);

        if (count % 2 != 0) tr.setBackgroundColor(Color.GRAY);
        tr.setId(100 + count);
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        if (addNumeracion) {
            TextView tv_numero = new TextView(context);
            tv_numero.setText(count.toString());
            tv_numero.setTextColor(Color.WHITE);
            tv_numero.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
            tr.addView(tv_numero);// add the column to the table row here
        }
        for (int j = 0; j < Contenido.length; j++) {
            String dato = Contenido[j];
            TextView tv_dato = new TextView(context);
//                tv_dato.setId(200 + count);
            tv_dato.setText(dato);
            tv_dato.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
            tv_dato.setTextColor(Color.WHITE);
            tv_dato.setGravity(Gravity.CENTER);
            tr.addView(tv_dato);


        }
        if (addButonEliminar) {
            Button btnEliminar = new Button(context);
            btnEliminar.setText("Eliminar");
            btnEliminar.setPadding(PaddingLeft, PaddingTop, PaddingRight, PaddingBottom);
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Fila eliminada ", Toast.LENGTH_SHORT).show();
                    tr.removeAllViews();
                }
            });
            tr.addView(btnEliminar);
        }
        tableLayout.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        count++;
    }
}