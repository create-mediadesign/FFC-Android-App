package at.create.android.ffc.domain;

/**
 * @author Philipp Ullmann
 * String helper methods.
 */
public final class StringHelper {
    /**
     * @param s String
     * @return True if the given string is null or empty, otherwise false is returned.
     */
    public boolean isBlank(String s) {
        if (s == null) {
            return true;
        }
        
        return s.trim().length() == 0;
    }
}
