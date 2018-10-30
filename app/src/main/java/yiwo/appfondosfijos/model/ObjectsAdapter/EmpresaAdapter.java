package yiwo.appfondosfijos.model.ObjectsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import yiwo.appfondosfijos.model.Objects.Empresa;
import yiwo.appfondosfijos.R;

public class EmpresaAdapter  extends ArrayAdapter<Empresa> {

    ArrayList<Empresa> data;
    Context context;


    private static class ViewHolder {
        TextView tv_code;
        TextView tv_name;
    }

    public EmpresaAdapter(ArrayList<Empresa> data, Context context) {
        super(context, R.layout.rows_empresa, data);
        this.data = data;
        this.context = context;
    }

    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Empresa dataModel = getItem(position);
        EmpresaAdapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new EmpresaAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.rows_codname, parent, false);
            viewHolder.tv_code = convertView.findViewById(R.id.cod);
            viewHolder.tv_name = convertView.findViewById(R.id.name);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (EmpresaAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tv_code.setText(dataModel.getCodigo());
        viewHolder.tv_name.setText(dataModel.getNombre());

        // Return the completed view to render on screen
        return convertView;
    }
}
