package at.create.android.ffc;


/**
 * @author Philipp Ullmann
 *  
 */
public interface AsyncActivity {
    public void showLoadingProgressDialog();
    public void showProgressDialog(CharSequence message);
    public void dismissProgressDialog();
}
