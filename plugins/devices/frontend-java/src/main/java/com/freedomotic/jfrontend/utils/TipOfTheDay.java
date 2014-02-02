/**
 *
 * Copyright (c) 2009-2013 Freedomotic team http://freedomotic.com
 *
 * This file is part of Freedomotic
 *
 * This Program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2, or (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Freedomotic; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package com.freedomotic.jfrontend.utils;

import com.freedomotic.api.Plugin;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ToolTipManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author enrico
 */
public class TipOfTheDay
        extends javax.swing.JFrame {

    private Plugin main;
    private static String PAGE = "http://www.freedomotic.com/help/index.html";
    /**
     * Creates new form TipOfTheDay
     * @param main
     */
    public TipOfTheDay(Plugin main) {
        initComponents();
        this.main = main;

        try {
            browser.setPage(new URL(PAGE));
            browser.setEditable(false);
            ToolTipManager.sharedInstance().registerComponent(browser);
            browser.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (HyperlinkEvent.EventType.ACTIVATED == e.getEventType()) {
                        try {
                            browser.setPage(e.getURL());
                        } catch (IOException e1) {
                            LOG.log(Level.WARNING, "Cannot open {0} for reason: {1} {2}", new Object[]{PAGE, e1.getClass().getSimpleName(), e1.getLocalizedMessage()});
                        }
                    }
                }
            });
            setPreferredSize(new Dimension(800, 600));
            requestFocus();
            pack();
            setVisible(true);
        } catch (IOException ex) {
            LOG.log(Level.WARNING, "Cannot open {0} for reason: {1} {2}", new Object[]{PAGE, ex.getClass().getSimpleName(), ex.getLocalizedMessage()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(  )
    {
        jScrollPane1 = new javax.swing.JScrollPane(  );
        browser = new javax.swing.JTextPane(  );

        setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
        setTitle( "Tip Of The Day" );

        jScrollPane1.setViewportView( browser );

        getContentPane(  ).add( jScrollPane1, java.awt.BorderLayout.CENTER );

        pack(  );
    } // </editor-fold>//GEN-END:initComponents
      // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JTextPane browser;
    private javax.swing.JScrollPane jScrollPane1;

    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(TipOfTheDay.class.getName());
}
