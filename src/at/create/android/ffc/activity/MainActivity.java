package at.create.android.ffc.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import at.create.android.ffc.R;

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
    
    private void initFields() {
        
    }
}
