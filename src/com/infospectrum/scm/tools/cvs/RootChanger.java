/**
 * 
 */
package com.infospectrum.scm.tools.cvs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author tusharj
 * 
 */
public class RootChanger {

	String path;
	String oldRoot;
	String newRoot;

	public RootChanger(final String path, final String oldRoot,
			final String newRoot) {
		this.path = path;
		this.oldRoot = oldRoot;
		this.newRoot = newRoot;
	}

	public void changeRoots(final RootChangeListener listener) {
		changeRoots(new File(this.path), listener);

	}

	private void changeRoots(final File currentPathFile,
			final RootChangeListener listener) {

		if (currentPathFile.isFile()) {
			// not a directory so return
			return;
		}

		// process this directory first
		if (currentPathFile.getName().equals("CVS")) {
			changeRootFile(currentPathFile, oldRoot, newRoot, listener);

		} else {

			// iterate on the sub directories
			File[] listFiles = currentPathFile.listFiles();
			for (File subItem : listFiles) {
				// recursive call to process
				// in same way
				changeRoots(subItem, listener);
			}
		}
	}

	public static void changeRootFile(final File currentPathFile, final String oldRoot,
			final String newRoot, final RootChangeListener listener) {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			final String rootFileName = currentPathFile.getCanonicalPath()
					+ File.separator + "Root";
			if (listener != null) {
				listener.beforeRootChange(rootFileName);
			}

			// change the root
			fileReader = new FileReader(rootFileName);
			bufferedReader = new BufferedReader(fileReader);

			fileWriter = new FileWriter(rootFileName + ".tmp");
			bufferedWriter = new BufferedWriter(fileWriter);

			String line = bufferedReader.readLine();
			while (line != null) {
				String replacedLine = line.replace(oldRoot, newRoot);
				bufferedWriter.write(replacedLine);
				line = bufferedReader.readLine();
			}
			bufferedWriter.write("\n");

			bufferedReader.close();
			bufferedReader = null;
			fileReader.close();
			fileReader = null;

			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedWriter = null;
			fileWriter.close();
			fileWriter = null;

			File rootFile = new File(rootFileName);
			rootFile.delete();

			File newRootFile = new File(rootFileName + ".tmp");
			newRootFile.renameTo(rootFile);

			// done
			if (listener != null) {
				listener.rootChanged(rootFileName);
			}
		} catch (Exception e) {
			if (listener != null) {
				listener.rootChangeFailed(e);
			}
		} finally {

			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (bufferedWriter != null) {
				try {
					bufferedWriter.flush();
					bufferedWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
