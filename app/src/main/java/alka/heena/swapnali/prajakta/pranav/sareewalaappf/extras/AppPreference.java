package alka.heena.swapnali.prajakta.pranav.sareewalaappf.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;

public class AppPreference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public AppPreference(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.s_pref_file), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //setting usr id
    public void setUserId(String id){
        editor.putString(String.valueOf(R.string.userid) , id);
        editor.commit();
    }
    public String getUserId(){
        return sharedPreferences.getString(String.valueOf(R.string.userid),null);
    }
    //Setting login status
    public void setLoginStatus(boolean status){
        editor.putBoolean(String.valueOf(R.string.s_pref_login_status), status);
        editor.commit();
    }
    public boolean getLoginStatus(){
        return sharedPreferences.getBoolean(String.valueOf(R.string.s_pref_login_status), false);
    }


    //For email
    public void setDisplayEmail(String email){
        editor.putString(String.valueOf(R.string.s_pref_email), email);
        editor.commit();
    }
    public String getDisplayEmail(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_email), "email");
    }

    //For email
    public void setCreDate(String date){
        editor.putString(String.valueOf(R.string.s_pref_date), date);
        editor.commit();
    }
    public String getCreDate(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_date), "date");
    }

    // For TOAST Message for response
    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void setDisplayName(String name){
        editor.putString(String.valueOf(R.string.name), name);
        editor.commit();
    }

    public String getDisplayName() {
        return sharedPreferences.getString(String.valueOf(R.string.name), "Name");
    }

    public void setPhoneNO(String phoneNO){
        editor.putString(String.valueOf(R.string.phoneno), phoneNO);
        editor.commit();
    }
    public String getPhoneNO(){
        return sharedPreferences.getString(String.valueOf(R.string.phoneno), null);
    }

    public void setProductId(String productid) {
    }
}
