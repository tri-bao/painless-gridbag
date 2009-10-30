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

    public static void addButtonPanel(final PainlessGridBag containterGbl,
            final JButton... buttons) {
        // create button panel
        JPanel pnlButton = new JPanel();
        PainlessGridBag gbl = new PainlessGridBag(pnlButton, false);
        IGridCell gridCell = gbl.row();
        for (JButton button : buttons) {
            gridCell = gridCell.cell(button);
            gbl.constraints(button).anchor = GridBagConstraints.EAST;
        }
        gbl.done();

        // add the panel to the container
        containterGbl.row().cellXRemainder(pnlButton);
        containterGbl.constraints(pnlButton).anchor = GridBagConstraints.CENTER;
        containterGbl.constraints(pnlButton).insets.bottom = 5;
    }

    public static void addButtonPanel(final PainlessGridBag containterGbl,
            final JButton[] leftBtns, final JButton[] rightBtns) {
        PainlessGridbagConfiguration config = new PainlessGridbagConfiguration();
        config.setLastRowBottomSpacing(0);

        JPanel pnlButton = new JPanel();
        PainlessGridBag gbl = new PainlessGridBag(pnlButton, config, false);
        IGridCell gridCell = gbl.row();

        if (leftBtns != null) {
            for (JButton button : leftBtns) {
                gridCell = gridCell.cell(button);
                gbl.constraints(button).anchor = GridBagConstraints.EAST;
            }
        }
        gridCell.cell(new JPanel()).fillX();
        if (rightBtns != null) {
            for (JButton button : rightBtns) {
                gridCell = gridCell.cell(button);
                gbl.constraints(button).anchor = GridBagConstraints.WEST;
            }
        }
        gbl.done();

        containterGbl.row().cellXRemainder(pnlButton).fillX();
        containterGbl.constraints(pnlButton).anchor = GridBagConstraints.CENTER;
        containterGbl.constraints(pnlButton).insets.bottom = 5;
    }
}
