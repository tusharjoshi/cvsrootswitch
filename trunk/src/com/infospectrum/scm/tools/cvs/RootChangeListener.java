package com.infospectrum.scm.tools.cvs;

/**
 * Interface RootChangeListener is used to get notifications
 * for the root change process.
 *
 * @author <a href="tusharvjoshi@gmail.com">Tushar Joshi</a>
 */
public interface RootChangeListener {

    /**
     * This method is called before the root file is actually
     * changed.
     *
     * @param rootFileName the full path and name of the file about to
     * change
     */
    void beforeRootChange(String rootFileName);

    /**
     * Called after the root file is changed with the given
     * replacement text
     *
     * @param rootFileName file which got changed
     */
    void rootChanged(String rootFileName);

    /**
     * called if there is an error changing the file.
     *
     * @param rootFileName file which could not be changed
     * @param e exception object with additional information
     */
    void rootChangeFailed(String rootFileName, Exception e);
}
