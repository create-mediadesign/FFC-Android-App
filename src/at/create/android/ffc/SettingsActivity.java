package at.create.android.ffc;

import android.os.Bundle;
import android.preference.PreferenceActivity;

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
