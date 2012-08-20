package at.create.android.ffc.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import at.create.android.ffc.R;
import at.create.android.ffc.domain.Setting;
import at.create.android.ffc.http.FormBasedAuthentication;

public class MainActivity extends Activity implements OnClickListener {
    private EditText urlField;
    private EditText usernameField;
    private EditText passwordField;
    private Button   loginButton;
    private SharedPreferences setting;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViews();
        setting = getSharedPreferences(Setting.SHARED_PREF, 0);
        loginButton.setOnClickListener(this);
    }
    
    @Override
    protected void onResume() {
        restoreInputFieldValues();
        super.onResume();
    }
    
    @Override
    protected void onPause() {
        storeInputFieldValues();
        super.onPause();
    }
    
    @Override
    public void onClick(View v) {
        if (authenticate()) {
            Intent intent = new Intent(getBaseContext(),
                                       ContactListActivity.class);
            startActivity(intent);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Authentication");
            alertDialog.setCancelable(true);
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.setMessage("failed");
            alertDialog.show();
        }
    }
    
    private void findViews() {
        urlField      = (EditText) findViewById(R.id.url);
        usernameField = (EditText) findViewById(R.id.username);
        passwordField = (EditText) findViewById(R.id.password);
        loginButton   = (Button) findViewById(R.id.sign_in_button);
    }
    
    private void storeInputFieldValues() {
        Editor editor = setting.edit();
        
        editor.putString("url",
                         urlField.getText().toString());
        editor.putString("username",
                         usernameField.getText().toString());
        editor.putString("password",
                         passwordField.getText().toString());
        
        editor.commit();
    }
    
    private void restoreInputFieldValues() {
        urlField.setText(setting.getString("url", ""));
        usernameField.setText(setting.getString("username", ""));
        passwordField.setText(setting.getString("password", ""));
    }
    
    private boolean authenticate() {
        if (usernameField.length() == 0 ||
            passwordField.length() == 0 ||
            urlField.length() == 0) {
            return false;
        }
        
        FormBasedAuthentication auth = new FormBasedAuthentication(usernameField.getText().toString(),
                                                                   passwordField.getText().toString(),
                                                                   urlField.getText().toString());
        return auth.authenticate();
    }
}
