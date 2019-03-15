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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;

/**
 * 
 * 
 * @author Ho Tri Bao
 */
public class PainlessGridBagConfiguration {
    // CHECKSTYLE:OFF MagicNumber they are default configuration, not magic
    private int verticalSpacing = 5;
    private int horizontalSpacing = 5;
    private int firstColumnLeftSpacing = 10;
    private int lastColumnRightSpacing = 10;
    private int firstRowTopSpacing = 10;
    private int lastRowBottomSpacing = 10;
    
    private boolean alignAllLabelsToRight = false;
    private final Set<JLabel> leftAlignLabels = new HashSet<JLabel>();
    private final Set<JLabel> rightAlignLabels = new HashSet<JLabel>();
    private final Map<JLabel, Integer> labelsWithSpecificAnchor =
        new HashMap<JLabel, Integer>();
    // CHECKSTYLE:ON MagicNumber

    public int getVerticalSpacing() {
        return verticalSpacing;
    }

    public void setVerticalSpacing(final int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    public int getHorizontalSpacing() {
        return horizontalSpacing;
    }

    public void setHorizontalSpacing(final int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public int getFirstColumnLeftSpacing() {
        return firstColumnLeftSpacing;
    }

    public void setFirstColumnLeftSpacing(final int firstColumnLeftSpacing) {
        this.firstColumnLeftSpacing = firstColumnLeftSpacing;
    }

    public int getLastColumnRightSpacing() {
        return lastColumnRightSpacing;
    }

    public void setLastColumnRightSpacing(final int lastColumnRightSpacing) {
        this.lastColumnRightSpacing = lastColumnRightSpacing;
    }

    public int getFirstRowTopSpacing() {
        return firstRowTopSpacing;
    }

    public void setFirstRowTopSpacing(final int firstRowTopSpacing) {
        this.firstRowTopSpacing = firstRowTopSpacing;
    }

    public int getLastRowBottomSpacing() {
        return lastRowBottomSpacing;
    }

    public void setLastRowBottomSpacing(final int lastRowBottomSpacing) {
        this.lastRowBottomSpacing = lastRowBottomSpacing;
    }

    public boolean isAlignAllLabelsToRight() {
        return alignAllLabelsToRight;
    }

    /**
     * @param alignAllLabelsToRight default is <code>false</code>. If set
     * to true, all JLabel will be aligned on the right.
     */
    public void setAlignAllLabelsToRight(
            final boolean alignAllLabelsToRight) {
        this.alignAllLabelsToRight = alignAllLabelsToRight;
    }

    /**
     * @return never null.
     */
    public Set<JLabel> getLeftAlignLabels() {
        return leftAlignLabels;
    }

    /**
     * This is only used in conjunction with the configuartion 
     * <code>AlignAllLabelsToRight</code>. If that config is 
     * <code>true</code>, all labels that are set here will be
     * align to left.
     */
    public void setLeftAlignLabels(final JLabel[] leftAlignLabels) {
        this.leftAlignLabels.clear();
        if (leftAlignLabels != null) {
            this.leftAlignLabels.addAll(Arrays.asList(leftAlignLabels));
        }
    }

    /**
     * @return never null.
     */
    public Set<JLabel> getRightAlignLabels() {
        return rightAlignLabels;
    }

    /**
     * This is only used in conjunction with the configuartion 
     * <code>AlignAllLabelsToRight</code>. If that config is 
     * <code>false</code>, all labels that are set here will be
     * align to right.
     */
    public void setRightAlignLabels(final JLabel[] rightAlignLabels) {
        this.rightAlignLabels.clear();
        if (rightAlignLabels != null) {
            this.rightAlignLabels.addAll(Arrays.asList(rightAlignLabels));
        }
    }
    
    /**
     * By default, the util bases on the flag 
     * <code>alignAllLabelsToRight</code> to set anchor (left or right) for 
     * all labels. If there are some labels that need to be anchored differently
     * from the rest, use this method.
     *  
     * @param lbl the label to set specific anchor.
     * @param anchor one of the "anchor" values from GrigBagConstraint class.
     */
    public void addLabelAnchor(final JLabel lbl, final int anchor) {
        labelsWithSpecificAnchor.put(lbl, anchor);
    }
    
    // package protected, should be only accessed by PainlessGridBag class.
    Map<JLabel, Integer> getLabelsWithSpecificAnchor() {
        return labelsWithSpecificAnchor;
    }
}
