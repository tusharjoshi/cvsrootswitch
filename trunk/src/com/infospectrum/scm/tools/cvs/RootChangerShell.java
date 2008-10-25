/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infospectrum.scm.tools.cvs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides a command line interface.
 *
 * @author <a href="tusharvjoshi@gmail.com">Tushar Joshi</a>
 */
public class RootChangerShell {

    /**
     * @param args
     */
    public static void main(String[] args) {

        /**
         * If we do not have required parameters show
         * message and do nothing.
         */
        if (args.length != 3) {
            System.err.println("Pass arguments as follows:");
            System.err.println("path-of-folder old-root new-root");
            return;
        }

        final String path = args[0];
        final String oldRoot = args[1];
        final String newRoot = args[2];

        /*
         * Confirm from the user about his inputs
         * and provide a way to cancel the transaction
         */
        System.out.println("I will start from the folder: " + path);
        System.out.println("I will search for CVS Root files and replace,");
        System.out.println("all occurences of \"" + oldRoot + "\" with ");
        System.out.println("new value \"" + newRoot + "\"");
        System.out.println("");
        System.out.print("Are you sure (y/n)?");

        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            if (line != null && line.equalsIgnoreCase("Y")) {
                /**
                 * we got the users confirmation hence proceed
                 * and change the roots.
                 */
                System.out.println("Starting root switch process...");
                changeRoots(path, oldRoot, newRoot);
                System.out.println("Root switch succesfully done!");
            }
        } catch (IOException ex) {
            Logger.getLogger(RootChangerShell.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

    }

    private static void changeRoots(String path, String oldRoot, String newRoot) {
        final RootChanger rootChanger = new RootChanger(path, oldRoot, newRoot);
        rootChanger.changeRoots(new RootChangeAdapter() {

            @Override
            public void rootChanged(String rootFileName) {
                System.out.println(rootFileName);

            }
        });
    }
}
