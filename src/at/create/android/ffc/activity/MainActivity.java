package at.create.android.ffc.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import at.create.android.ffc.R;
import at.create.android.ffc.domain.Setting;

public class MainActivity extends Activity {
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
}
