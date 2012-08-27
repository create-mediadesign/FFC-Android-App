package at.create.android.ffc.activity;

import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockAccountAuthenticatorActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import at.create.android.ffc.R;
import at.create.android.ffc.R.id;
import at.create.android.ffc.R.menu;
import at.create.android.ffc.domain.Setting;
import at.create.android.ffc.http.FormBasedAuthentication;
import at.create.android.ffc.ui.TextWatcherAdapter;

/**
 * @author Philipp Ullmann
 * Input of base URI, username and password, in order to perform a login.
 */
public class MainActivity extends RoboSherlockAccountAuthenticatorActivity {
    protected static final String  TAG = MainActivity.class.getSimpleName();
    @InjectView(id.base_uri)
    private EditText               baseUriField;
    @InjectView(id.username)
    private EditText               usernameField;
    @InjectView(id.password)
    private EditText               passwordField;
    private MenuItem               loginItem = null;
    private SharedPreferences      setting;
    private AsyncActivityImpl      asyncActivity;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTextWatcher();
        setting       = getSharedPreferences(Setting.SHARED_PREF, 0);
        asyncActivity = new AsyncActivityImpl(this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncActivity.onDestroy();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        restoreInputFieldValues();
    }
    
    @Override
    protected void onPause() {
        storeInputFieldValues();
        super.onPause();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu optionMenu) {
        getSupportMenuInflater().inflate(menu.login,
                                         optionMenu);
        loginItem = optionMenu.findItem(id.m_login);
        updateEnablement();
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case id.m_login:
                if (inputIsValid()) {
                    authenticate();
                }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
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
    
    private void authenticate() {
        asyncActivity.showLoadingProgressDialog();
        
        RoboAsyncTask<Boolean> authenticationTask = new RoboAsyncTask<Boolean>(this) {
            @Override
            public Boolean call() throws Exception {
                FormBasedAuthentication auth = new FormBasedAuthentication(usernameField.getText().toString(),
                                                                           passwordField.getText().toString(),
                                                                           baseUriField.getText().toString());
                return auth.authenticate();
            }
            
            @Override
            protected void onException(Exception e) throws RuntimeException {
                asyncActivity.dismissProgressDialog();
                Log.d(TAG, "Authentication exception", e);
                onAuthenticationResult(false);
            }
            
            @Override
            protected void onSuccess(Boolean success) throws Exception {
                asyncActivity.dismissProgressDialog();
                onAuthenticationResult(success);
            }
        };
        
        authenticationTask.execute();
    }
    
    private void onAuthenticationResult(boolean result) {
        if (result) {
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
    
    private boolean loginEnabled() {
        return !TextUtils.isEmpty(baseUriField.getText()) &&
               !TextUtils.isEmpty(usernameField.getText()) &&
               !TextUtils.isEmpty(passwordField.getText());
    }
    
    private void updateEnablement() {
        if (loginItem != null)
            loginItem.setEnabled(loginEnabled());
    }
    
    private void setTextWatcher() {
        TextWatcher watcher = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable gitDirEditText) {
                updateEnablement();
            }
        };
        
        baseUriField.addTextChangedListener(watcher);
        usernameField.addTextChangedListener(watcher);
        passwordField.addTextChangedListener(watcher);
    }
}
