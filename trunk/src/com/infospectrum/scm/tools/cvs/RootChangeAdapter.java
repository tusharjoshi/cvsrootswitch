/**
 * 
 */
package com.infospectrum.scm.tools.cvs;

/**
 * @author <a href="tusharvjoshi@gmail.com">Tushar Joshi</a>
 *
 */
public class RootChangeAdapter implements RootChangeListener {

    /* (non-Javadoc)
     * @see com.infospectrum.scm.tools.cvs.RootChangeListener#beforeRootChange(java.lang.String)
     */
    @Override
    public void beforeRootChange(String rootFileName) {
        // ignored for this adapter class
    }

    /* (non-Javadoc)
     * @see com.infospectrum.scm.tools.cvs.RootChangeListener#rootChangeFailed(java.lang.Exception)
     */
    @Override
    public void rootChangeFailed(String rootFileName, Exception e) {
        // ignored for this adapter class
    }

    /* (non-Javadoc)
     * @see com.infospectrum.scm.tools.cvs.RootChangeListener#rootChanged(java.lang.String)
     */
    @Override
    public void rootChanged(String rootFileName) {
        // ignored for this adapter class
    }
}
