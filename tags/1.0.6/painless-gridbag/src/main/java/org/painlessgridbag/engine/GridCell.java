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
package org.painlessgridbag.engine;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.painlessgridbag.PainlessGridBag;
import org.painlessgridbag.PainlessGridbagConfiguration;
import org.painlessgridbag.util.Assert;
import org.painlessgridbag.util.Dynamic2DArray;

/**
 * This is where the engine is actually implemented.
 * 
 * @author Ho Tri Bao
 * 
 */
public class GridCell implements IGridCell, IGridFillable {

    private JComponent currentCompo;
    private JComponent lastCompoOnRow = null;
    private List<JComponent> lastRowComp = null;
    private int rowCounter = -1;
    private int colCounter;

    private final Dynamic2DArray<ComponentInCell> grid =
        new Dynamic2DArray<ComponentInCell>(ComponentInCell.class, false);

    private final Map<JComponent, GridBagConstraints> children = 
        new HashMap<JComponent, GridBagConstraints>();

    private final PainlessGridbagConfiguration config;

    public GridCell(final PainlessGridbagConfiguration config) {
        this.config = config;
        rowCounter++;
        colCounter = -1;
    }

    public void newRow() {
        if (lastCompoOnRow != null) { // last component on the previous row
            children.get(lastCompoOnRow).insets.right = 
                config.getLastColumnRightSpacing();
        }
        rowCounter++;
        lastRowComp = new ArrayList<JComponent>();
        lastCompoOnRow = null;
        colCounter = -1;
    }

    public void done() {
        if (lastCompoOnRow != null) { // this is for the last component on the
                                      // last row
            children.get(lastCompoOnRow).insets.right =
                config.getLastColumnRightSpacing();
        }
        if (lastRowComp != null) {
            for (JComponent comp : lastRowComp) {
                children.get(comp).insets.bottom = 
                    config.getLastRowBottomSpacing();
            }
        }
    }

    public Map<JComponent, GridBagConstraints> getChildren() {
        return children;
    }

    public IGridCell cell() {
        skipCell();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public IGridFillable cell(final JComponent component) {
        fillCells(component, 1, 1);
        return this;
    }

    public IFillableRemainder cellXRemainder(final JComponent component) {
        fillCells(component, 1, GridBagConstraints.REMAINDER);
        return new XYRemainderCell(this);
    }

    public IFillable cellYRemainder(final JComponent component) {
        fillCells(component, GridBagConstraints.REMAINDER, 1);
        return this;
    }

    public IFillableRemainder cellXYRemainder(final JComponent component) {
        fillCells(component, GridBagConstraints.REMAINDER,
                GridBagConstraints.REMAINDER);
        return new XYRemainderCell(this);
    }

    public void separator() {
        fillCells(new JSeparator(), 1, GridBagConstraints.REMAINDER);
        children.get(currentCompo).fill = GridBagConstraints.HORIZONTAL;
        children.get(currentCompo).weightx = 1.0;
    }

    public void separator(final JLabel label) {
        JPanel pnl = new JPanel();
        PainlessGridBag gbl = new PainlessGridBag(pnl, false); // we eat our dog
                                                               // food :-)
        gbl.row().cell(label).cell(new JSeparator()).fillX();
        gbl.done();

        fillCells(pnl, 1, GridBagConstraints.REMAINDER);
        children.get(currentCompo).fill = GridBagConstraints.HORIZONTAL;
        children.get(currentCompo).weightx = 1.0;
        children.get(currentCompo).insets.left = 0;
        children.get(currentCompo).insets.right = 0;
    }

    private void skipCell() {
        colCounter++;
    }

    private void fillCells(
            final JComponent component, final int rowNum, final int colNum) {
        currentCompo = component;
        colCounter++;
        int realNumberOfRows = (rowNum == 0 ? 1 : rowNum);
        int realNumberOfCols = (colNum == 0 ? 1 : colNum);
        for (int row = rowCounter;
                row < (rowCounter + realNumberOfRows);
                row++) {
            for (int col = colCounter;
                    col < (colCounter + realNumberOfCols);
                    col++) {
                checkRemainderOnRow(row);
                checkRemainderOnCol(col);
                grid.put(col, row, new ComponentInCell(component,
                        colNum == GridBagConstraints.REMAINDER,
                        rowNum == GridBagConstraints.REMAINDER));
            }
        }
        GridBagConstraints constraint = makeDefaultConstraint();
        constraint.gridx = colCounter;
        constraint.gridy = rowCounter;
        constraint.gridwidth = colNum;
        constraint.gridheight = rowNum;
        if (colCounter == 0) {
            constraint.insets.left = config.getFirstColumnLeftSpacing();
        }
        if (rowCounter == 1) {
            constraint.insets.top = config.getFirstRowTopSpacing();
        }
        children.put(component, constraint);
        lastCompoOnRow = component;
        lastRowComp.add(component);
        colCounter += (colNum - 1);
    }

    private void checkRemainderOnRow(final int row) {
        // Whenever a component is placed in the grid, it's always put on the
        // right side (never put
        // backward). Hence, just if there is a remainder component existed on
        // the row, no component
        // is allows placing on that row.
        for (int col = 0; col < grid.getXLength(); col++) {
            if ((row < grid.getYLength()) && (col < grid.getXLength())
                    && (grid.get(col, row) != null)
                    && grid.get(col, row).isRemainderX()) {
                throw new IllegalArgumentException(
                        "The component at cell (row, col) = " + "(" + row
                                + ", " + col
                                + ") takes the remainder of the row. "
                                + "No other component can be put on this row.");
            }
        }
    }

    private void checkRemainderOnCol(final int col) {
        // Whenever a component is placed in the grid, it's always put on the
        // down side (never put
        // backward). Hence, just if there is a remainder component existed on
        // the column,
        // no component is allows placing on that col.
        for (int row = 0; row < grid.getYLength(); row++) {
            if ((row < grid.getYLength()) && (col < grid.getXLength())
                    && (grid.get(col, row) != null)
                    && grid.get(col, row).isRemainderY()) {
                throw new IllegalArgumentException(
                        "The component at cell (row, col) = "
                            + "("
                            + row
                            + ", "
                            + col
                            + ") takes the remainder of the column. "
                            + "No other component can be put on this column.");
            }
        }
    }

    private GridBagConstraints makeDefaultConstraint() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.ipadx = 0;
        constraint.ipady = 0;
        constraint.insets = new java.awt.Insets(config.getVirticalSpacing(), 0,
                0, config.getHorizontalSpacing());
        constraint.anchor = java.awt.GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        constraint.weightx = 0.0;
        constraint.weighty = 0.0;
        return constraint;
    }

    /**
     * {@inheritDoc}
     */
    public IGridFillable cellX(final JComponent component, final int span) {
        Assert.isTrue(span > 0, "Column span must be greater than zero");

        fillCells(component, 1, span);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public IGridFillable cellY(final JComponent component, final int span) {
        Assert.isTrue(span > 0, "Row span must be greater than zero");

        fillCells(component, span, 1);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public IGridFillable cellXY(
            final JComponent component, final int xSpan, final int ySpan) {
        Assert.isTrue(xSpan > 0, "Column span must be greater than zero");
        Assert.isTrue(ySpan > 0, "Row span must be greater than zero");

        fillCells(component, ySpan, xSpan);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public IGridCell fillX() {
        return fillX(1.0);
    }

    public IGridCell fillX(final double weightx) {
        children.get(currentCompo).fill = GridBagConstraints.HORIZONTAL;
        children.get(currentCompo).weightx = weightx;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public IGridCell fillXY() {
        return fillXY(1.0, 1.0);
    }

    public IGridCell fillXY(final double weightx, final double weighty) {
        children.get(currentCompo).fill = GridBagConstraints.BOTH;
        children.get(currentCompo).weightx = weightx;
        children.get(currentCompo).weighty = weighty;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public IGridCell fillY() {
        return fillY(1.0);
    }

    public IGridCell fillY(final double weighty) {
        children.get(currentCompo).fill = GridBagConstraints.VERTICAL;
        children.get(currentCompo).weighty = weighty;
        return this;
    }

    public void makeColumnsSameWidth() {
        int colPrefWidth = determineMaxPreferredWidth();
        GridBagConstraints constraint;
        JComponent comp;
        for (int col = 0; col < grid.getXLength(); col++) {
            for (int row = 0; row < grid.getYLength(); row++) {
                if (grid.get(col, row) != null) { // has component at this
                                                  // position
                    comp = grid.get(col, row).getComponent();
                    constraint = children.get(comp);
                    if (isHorizontallyFilled(constraint)
                            && (constraint.gridwidth == 1)) {
                        // only adjust preferred width of horizontally-filled
                        // component
                        comp.setPreferredSize(new Dimension(colPrefWidth, comp
                                .getPreferredSize().height));
                    }
                }
            } // for row
        } // for column
    }

    private int determineMaxPreferredWidth() {
        int result = 0;
        GridBagConstraints constraint;
        JComponent comp;
        for (int col = 0; col < grid.getXLength(); col++) {
            for (int row = 0; row < grid.getYLength(); row++) {
                if (grid.get(col, row) != null) { // has component at this
                                                  // position
                    comp = grid.get(col, row).getComponent();
                    constraint = children.get(comp);
                    if (constraint.gridwidth == 1) { // not spanning component
                        if (comp.getPreferredSize().width > result) {
                            result = comp.getPreferredSize().width;
                        }
                    }
                }
            } // for row
        } // for column
        return result;
    }

    private boolean isHorizontallyFilled(final GridBagConstraints constraint) {
        return (constraint.fill == GridBagConstraints.HORIZONTAL)
                || (constraint.fill == GridBagConstraints.BOTH);
    }

    /**
     * @author Ho Tri Bao
     * 
     */
    private static class XYRemainderCell implements IFillableRemainder {
        private final IFillable fillable;

        public XYRemainderCell(final IFillable fillable) {
            this.fillable = fillable;
        }

        public void fillX() {
            fillable.fillX();
        }

        public void fillXY() {
            fillable.fillXY();
        }

        public void fillY() {
            fillable.fillY();
        }
    }
}
