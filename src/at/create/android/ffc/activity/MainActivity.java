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

/**
 * @author Philipp Ullmann
 * Input of base URI, username and password, in order to perform a login.
 */
public class MainActivity extends Activity implements OnClickListener {
    private EditText          baseUriField;
    private EditText          usernameField;
    private EditText          passwordField;
    private Button            loginButton;
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
        if (inputIsValid()) {
            if (authenticate()) {
                Intent intent = new Intent(this,
                                           ContactListActivity.class);
                startActivity(intent);
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle(getString(R.string.authentication_failed));
                alertDialog.setCancelable(true);
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        }
    }
    
    private void findViews() {
        baseUriField       = (EditText) findViewById(R.id.base_uri);
        usernameField = (EditText) findViewById(R.id.username);
        passwordField = (EditText) findViewById(R.id.password);
        loginButton   = (Button) findViewById(R.id.sign_in_button);
    }
    
    private void storeInputFieldValues() {
        Editor editor = setting.edit();
        
        editor.putString("baseUri",
                         baseUriField.getText().toString());
        editor.putString("username",
                         usernameField.getText().toString());
        editor.putString("password",
                         passwordField.getText().toString());
        
        editor.commit();
    }
    
    private void restoreInputFieldValues() {
        baseUriField.setText(setting.getString("baseUri", ""));
        usernameField.setText(setting.getString("username", ""));
        passwordField.setText(setting.getString("password", ""));
    }
    
    private boolean authenticate() {
        if (usernameField.length() == 0 ||
            passwordField.length() == 0 ||
            baseUriField.length() == 0) {
            return false;
        }
        
        FormBasedAuthentication auth = new FormBasedAuthentication(usernameField.getText().toString(),
                                                                   passwordField.getText().toString(),
                                                                   baseUriField.getText().toString());
        return auth.authenticate();
    }
    
    private boolean inputIsValid() {
        boolean isValid = true;
        
        Setting setting = new Setting(baseUriField.getText().toString(),
                                      usernameField.getText().toString(),
                                      passwordField.getText().toString());
        
        if (setting.baseUriIsBlank()) {
            isValid = false;
            baseUriField.setError(getString(R.string.base_uri_cant_be_blank));
        } else if (!setting.baseUriIsAnUrl()) {
            isValid = false;
            baseUriField.setError(getString(R.string.base_uri_is_not_a_valid_uri));
        }
        
        if (setting.usernameIsBlank()) {
            isValid = false;
            usernameField.setError(getString(R.string.username_cant_be_blank));
        }
        
        if (setting.passwordIsBlank()) {
            isValid = false;
            passwordField.setError(getString(R.string.password_cant_be_blank));
        }
        
        return isValid;
    }
}
