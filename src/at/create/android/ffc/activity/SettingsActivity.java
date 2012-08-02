package at.create.android.ffc.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import at.create.android.ffc.R;

/**
 * @author Philipp Ullmann
 * Setting of username, password and URL to Fat Free CRM web application.
 */
public final class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
