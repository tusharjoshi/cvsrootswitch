/**
 * 
 */
package com.infospectrum.scm.tools.cvs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * @author tusharj
 * 
 */
public class RootChangerTest {

	@Test
	public void testChangeRootFor() {
		File cvsFolderPath = createTestCVSFolder();

		RootChanger.changeRootFile(cvsFolderPath, "server", "myserver2", null);
	}

	@Test
	public void testChangeRoot() {
//		RootChanger rootChanger = new RootChanger("D:\\testcvs\\admin",
//				"10.150.133.206", "216.105.106.75");
//		rootChanger.changeRoots(new RootChangeAdapter() {
//
//			@Override
//			public void rootChanged(String rootFileName) {
//				System.out.println(rootFileName);
//			}
//		});
	}

	private File createTestCVSFolder() {
		File cvsFolderPath = new File("CVS");
		if (!cvsFolderPath.exists()) {
			cvsFolderPath.mkdir();
		}

		File rootFile = new File("CVS" + File.separator + "Root");
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(rootFile);
			bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(":extssh:username@server:/cvs");
			bufferedWriter.write("\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

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

		return cvsFolderPath;
	}
}
