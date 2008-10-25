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

        RootChangeListener listener = new RootChangeAdapter() {

            @Override
            public void rootChanged(String rootFileName) {
                System.out.println(rootFileName);
            }
        };
        RootChanger.changeRootFile(cvsFolderPath, "server", "myserver2", listener);
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

    @Test
    public void testGetFirstRootText() {
        File cvsFolderPath = createTestCVSFolder();
        String path = RootChanger.getFirstRootText("..");
        System.out.println("CVSROOTPATH:" + path);
    }

}
