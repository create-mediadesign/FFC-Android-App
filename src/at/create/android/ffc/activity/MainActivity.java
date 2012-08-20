package at.create.android.ffc.activity;

import java.io.IOException;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import at.create.android.ffc.R;
import at.create.android.ffc.domain.Setting;

public class MainActivity extends Activity {
    private EditText urlField;
    private EditText usernameField;
    private EditText passwordField;
    private Button   loginButton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViews();
        initFields(savedInstanceState);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("url",
                           urlField.getText().toString());
        outState.putString("username",
                           usernameField.getText().toString());
        outState.putString("password",
                           passwordField.getText().toString());
        
        super.onSaveInstanceState(outState);
    }
    
    private void findViews() {
        urlField        = (EditText) findViewById(R.id.url);
        usernameField   = (EditText) findViewById(R.id.username);
        passwordField   = (EditText) findViewById(R.id.password);
        loginButton     = (Button) findViewById(R.id.sign_in_button);
    }
    
    private void initFields(Bundle savedInstanceState) {
        Setting setting = null;
        
        // Load settings from "assets/setting.xml" file.
        try {
            setting = Setting.load(getAssets().open("setting.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Set url.
        if (savedInstanceState.containsKey("url")) {
            urlField.setText(savedInstanceState.getString("url"));
        } else if (setting != null) {
            urlField.setText(setting.getBaseUri());
        }
        
        // Set username.
        if (savedInstanceState.containsKey("username")) {
            usernameField.setText(savedInstanceState.getString("username"));
        } else if (setting != null) {
            usernameField.setText(setting.getUsername());
        }
        
        // Set password.
        if (savedInstanceState.containsKey("password")) {
            passwordField.setText(savedInstanceState.getString("password"));
        } else if (setting != null) {
            passwordField.setText(setting.getPassword());
        }
    }
}
