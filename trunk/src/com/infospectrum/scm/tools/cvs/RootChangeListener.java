package com.infospectrum.scm.tools.cvs;

public interface RootChangeListener {

	void beforeRootChange(String rootFileName);

	void rootChanged(String rootFileName);

	void rootChangeFailed(Exception e);

}
