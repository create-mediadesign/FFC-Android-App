package at.create.android.ffc.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import at.create.android.ffc.R;

/**
 * @author Philipp Ullmann
 * Show or hide progress dialog.
 */
public final class AsyncActivityImpl implements AsyncActivity {
    private ProgressDialog progressDialog;
    private Activity       activity;
    private boolean        destroyed = false;
    
    public AsyncActivityImpl(Activity activity) {
        this.activity = activity;
    }
    
    protected void onDestroy() {
        destroyed = true;
    }
    
    @Override
    public void showLoadingProgressDialog() {
        this.showProgressDialog(activity.getString(R.string.loading_please_wait));
    }
    
    @Override
    public void showProgressDialog(CharSequence message) {
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(activity);
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
