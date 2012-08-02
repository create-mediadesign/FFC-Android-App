package at.create.android.ffc.activity;


/**
 * @author Philipp Ullmann
 *  
 */
public interface AsyncActivity {
    public void showLoadingProgressDialog();
    public void showProgressDialog(CharSequence message);
    public void dismissProgressDialog();
}
