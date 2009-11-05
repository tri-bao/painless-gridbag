/*
 * Copyright 2009 Ho Tri Bao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.painlessgridbag;

import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.painlessgridbag.engine.IGridCell;

/**
 * 
 * @author Ho Tri Bao
 * 
 */
public final class LayoutUtils {

    private LayoutUtils() {
    }

    /**
     * Adds the given list of <code>buttons</code> to the container that
     * is being layed out by the given <code>containterGbl</code>. All 
     * button will be displayed in the center of the container.
     */
    public static void addButtonPanel(final PainlessGridBag containterGbl,
            final JButton... buttons) {
        PainlessGridbagConfiguration config = creatNoOuterSpacingConfig();

        // create button panel
        JPanel pnlButton = new JPanel();
        PainlessGridBag gbl = new PainlessGridBag(pnlButton, config, false);
        addButtons(buttons, GridBagConstraints.LINE_START, gbl, gbl.row());
        gbl.done();

        // add the panel to the container
        containterGbl.row().cellXRemainder(pnlButton);
        containterGbl.constraints(pnlButton).anchor = GridBagConstraints.CENTER;
    }
    
    private static void addButtons(final JButton[] buttons,
                                   final int anchor,
                                   final PainlessGridBag gbl,
                                   final IGridCell row) {
        if (buttons == null) {
            return;
        }
        IGridCell gridCell = row;
        boolean firstBtn = true;
        for (JButton button : buttons) {
            gridCell = gridCell.cell(button);
            gbl.constraints(button).anchor = anchor;
            
            // Spacing between 2 button should be double default
            // spacing of other component.
            if (!firstBtn) {
                gbl.constraints(button).insets.left = 
                    gbl.getConfig().getVirticalSpacing() * 2;
            }
            firstBtn = false;
        }
    }
    
    private static PainlessGridbagConfiguration creatNoOuterSpacingConfig() {
        PainlessGridbagConfiguration config = 
            new PainlessGridbagConfiguration();
        config.setFirstRowTopSpacing(0);
        config.setLastRowBottomSpacing(0);
        config.setFirstColumnLeftSpacing(0);
        config.setLastColumnRightSpacing(0);
        return config;
    }

    /**
     * Adds the given list of <code>leftBtns</code> and <code>rightBtns</code>
     * to the container that is being layed out by the given 
     * <code>containterGbl</code>. <code>leftBtns</code> button will be 
     * displayed on the left of the the container. <code>rightBtns</code> 
     * button will be displayed on the left of the the container.
     */
    public static void addButtonPanel(final PainlessGridBag containterGbl,
            final JButton[] leftBtns, final JButton[] rightBtns) {
        PainlessGridbagConfiguration config = creatNoOuterSpacingConfig();

        JPanel pnlButton = new JPanel();
        PainlessGridBag gbl = new PainlessGridBag(pnlButton, config, false);
        
        IGridCell row = gbl.row();
        addButtons(leftBtns, GridBagConstraints.LINE_START, gbl, row);
        row.cell(new JPanel()).fillX();
        addButtons(rightBtns, GridBagConstraints.LINE_END, gbl, row);

        gbl.done();

        containterGbl.row().cellXRemainder(pnlButton).fillX();
        containterGbl.constraints(pnlButton).anchor = GridBagConstraints.CENTER;
        containterGbl.constraints(pnlButton).insets.left = 0;
        containterGbl.constraints(pnlButton).insets.right = 0;
    }
}
