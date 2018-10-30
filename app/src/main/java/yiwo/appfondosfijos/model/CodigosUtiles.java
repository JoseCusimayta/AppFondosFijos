package yiwo.appfondosfijos.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import yiwo.appfondosfijos.R;

public class CodigosUtiles {

    private String TAG = "CodigosUtiles";
    private Boolean addtoBackStack = true;

    public void setAddtoBackStack(Boolean addtoBackStack) {
        this.addtoBackStack = addtoBackStack;
    }

    public void replaceFragment(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        transaction.replace(R.id.frag_contenedor, fragment);
        if (addtoBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public String getFecha() {
        Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return  df.format(c);
    }
    public String getStringNotNull(EditText editText){
        try{
            return  editText.getText().toString();
        }catch (Exception ex){
            return "";
        }
    }
}