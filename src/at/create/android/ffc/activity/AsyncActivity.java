/*
 * Fat Free CRM Android App
 * Copyright 2012 create mediadesign GmbH
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
