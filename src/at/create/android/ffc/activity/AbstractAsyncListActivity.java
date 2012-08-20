package at.create.android.ffc.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import at.create.android.ffc.R;

/**
 * @author Philipp Ullmann
 * Abstract class for asynchronous list activities.
 */
public class AbstractAsyncListActivity extends ListActivity implements AsyncActivity {
    private ProgressDialog progressDialog;
    private boolean destroyed = false;
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.destroyed = true;
    }
    
    @Override
    public void showLoadingProgressDialog() {
        this.showProgressDialog(getString(R.string.loading_please_wait));
    }
    
    @Override
    public void showProgressDialog(CharSequence message) {
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setIndeterminate(true);
        }
        
        this.progressDialog.setMessage(message);
        this.progressDialog.show();
    }
    
    @Override
    public void dismissProgressDialog() {
        if (this.progressDialog != null && !this.destroyed) {
            this.progressDialog.dismiss();
        }
    }
}
