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
     * button will be displayed in the center of the container. Vertical
     * spacing between 2 buttons = 2 * vertical spacing defined by the default
     * configuration.
     * 
     * @return the button panel.
     */
    public static JPanel addButtonPanel(
            final PainlessGridBag containterGbl,
            final JButton... buttons) {
        
        PainlessGridbagConfiguration config = creatNoOuterSpacingConfig();
        return addButtonPanelInternal(containterGbl, config, true, buttons);
    }
    
    /**
     * Same as method <code>addButtonPanel(PainlessGridBag, JButton...)</code>
     * excepts spacings around buttons are controlled by the given config.
     */
    public static JPanel addButtonPanel(
            final PainlessGridBag containterGbl,
            final PainlessGridbagConfiguration buttonConfig,
            final JButton... buttons) {

        return addButtonPanelInternal(
                containterGbl, buttonConfig, false, buttons);
    }

    private static JPanel addButtonPanelInternal(
            final PainlessGridBag containterGbl,
            final PainlessGridbagConfiguration buttonConfig,
            final boolean isDefaultConfig,
            final JButton... buttons) {
        // create button panel
        JPanel pnlButton = new JPanel();
        PainlessGridBag gbl = 
                new PainlessGridBag(pnlButton, buttonConfig, false);
        addButtons(buttons,
                    GridBagConstraints.LINE_START,
                    gbl,
                    gbl.row(),
                    isDefaultConfig);
        gbl.done();

        // add the panel to the container
        containterGbl.row().cellXRemainder(pnlButton);
        containterGbl.constraints(pnlButton).anchor = GridBagConstraints.CENTER;
        return pnlButton;
    }

    private static void addButtons(final JButton[] buttons,
                                   final int anchor,
                                   final PainlessGridBag gbl,
                                   final IGridCell row,
                                   final boolean isDefaultConfig) {
        if (buttons == null) {
            return;
        }
        IGridCell gridCell = row;
        boolean firstBtn = true;
        for (JButton button : buttons) {
            gridCell = gridCell.cell(button);
            gbl.constraints(button).anchor = anchor;
            
            if (!firstBtn && isDefaultConfig) {
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
     * button will be displayed on the left of the the container. Vertical
     * spacing between 2 buttons = 2 * vertical spacing defined by the default
     * configuration.
     */
    public static JPanel addButtonPanel(
            final PainlessGridBag containterGbl,
            final JButton[] leftBtns,
            final JButton[] rightBtns) {
        PainlessGridbagConfiguration config = creatNoOuterSpacingConfig();

        return addButtonPanelInternal(
                containterGbl, leftBtns, rightBtns, config, true);
    }

    private static JPanel addButtonPanelInternal(
            final PainlessGridBag containterGbl,
            final JButton[] leftBtns,
            final JButton[] rightBtns,
            final PainlessGridbagConfiguration config,
            final boolean isDefaultConfig) {
        JPanel pnlButton = new JPanel();
        PainlessGridBag gbl = new PainlessGridBag(pnlButton, config, false);
        
        IGridCell row = gbl.row();
        addButtons(leftBtns,
                    GridBagConstraints.LINE_START,
                    gbl,
                    row,
                    isDefaultConfig);
        row.cell(new JPanel()).fillX();
        addButtons(rightBtns,
                    GridBagConstraints.LINE_END,
                    gbl,
                    row,
                    isDefaultConfig);

        gbl.done();

        containterGbl.row().cellXRemainder(pnlButton).fillX();
        containterGbl.constraints(pnlButton).anchor = GridBagConstraints.CENTER;
        return pnlButton;
    }
    
    
    /**
     * Same as method 
     * <code>addButtonPanel(PainlessGridBag, JButton[], JButton[])</code>
     * excepts spacings around buttons are controlled by the given config.
     */
    public static JPanel addButtonPanel(
            final PainlessGridBag containterGbl,
            final PainlessGridbagConfiguration buttonConfig,
            final JButton[] leftBtns,
            final JButton[] rightBtns) {
        return addButtonPanelInternal(
                containterGbl, leftBtns, rightBtns, buttonConfig, false);
    }
}
