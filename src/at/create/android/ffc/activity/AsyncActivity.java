package at.create.android.ffc.activity;


/**
 * @author Philipp Ullmann
 * Interface definition for activities loading data asynchron.
 */
public interface AsyncActivity {
    /**
     * Shows a loading indicator with a default message.
     */
    public void showLoadingProgressDialog();
    
    /**
     * Shows a loading indicator with an explicit message.
     * @param message loading indicator message
     */
    public void showProgressDialog(CharSequence message);
    
    /**
     * Hides the progress indicator.
     */
    public void dismissProgressDialog();
    
    /**
     * Shows an alert dialog with the given message.
     * @param message alert dialog message
     */
    public void showAlert(CharSequence message);
}
