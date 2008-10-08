/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infospectrum.scm.tools.cvs;

/**
 *
 * @author <a href="tusharvjoshi@gmail.com">Tushar Joshi</a>
 */
public class RootChangerShell {

    /**
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Pass arguments as follows:");
            System.err.println("pathtofolder oldroot newroot");
            return;
        }

        final String path = args[0];
        final String oldRoot = args[1];
        final String newRoot = args[2];

        final RootChanger rootChanger = new RootChanger(path, oldRoot, newRoot);
        rootChanger.changeRoots(new RootChangeAdapter() {

            @Override
            public void rootChanged(String rootFileName) {
                System.out.println(rootFileName);

            }
        });

    }
}
