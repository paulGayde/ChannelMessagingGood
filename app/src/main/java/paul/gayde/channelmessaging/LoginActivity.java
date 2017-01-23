package paul.gayde.channelmessaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.StringTokenizer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Downloader.OnDownloadCompleteListener {

    public static final String PREFS_NAME = "stockage";

    private EditText etLogin;
    private EditText etPwd;
    private Button btnValider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);


        etPwd = (EditText) findViewById(R.id.etPwd);


        btnValider = (Button) findViewById(R.id.btnValider);
        btnValider.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {

        HashMap<String,String> params = new HashMap<String, String>();
        params.put("username", etLogin.getText().toString());
        params.put("password", etPwd.getText().toString());
        if (v.getId() == R.id.btnValider) {
            String Login = String.valueOf(etLogin.getText());
            String Pwd = String.valueOf(etPwd.getText());
            Downloader d = new Downloader(this,"http://www.raphaelbischof.fr/messaging/?function=connect",params);
            d.addOnDownloadCompleteListener(this);
            d.execute();


        }
    }

    @Override
    public void onDownloadComplete(String result) {

        Gson gson = new Gson();

        Result r = gson.fromJson(result, Result.class);
        if(r.code==200){
            Toast.makeText(this, "Vous êtes connecté ! ", Toast.LENGTH_SHORT).show();
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("accesstoken", r.accesstoken);

            Intent appel = new Intent(getApplicationContext(),ChannelListActivity.class);
            startActivity(appel);
        }
        else{
            Toast.makeText(this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
        }
    }
}
