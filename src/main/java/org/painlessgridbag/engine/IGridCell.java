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

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Interface of movement operations that defines a grid in a row.
 * 
 * @author Ho Tri Bao
 * 
 */
public interface IGridCell {
    /**
     * Skips (fill nothing) a cell on a row.
     */
    IGridCell cell();

    /**
     * Puts the given component in the cell.
     */
    IGridFillable cell(JComponent component);

    /**
     * Puts the given component in the cell and make 
     * it take all the remaining horizontal space of the row.
     * That means no other component can be put or span on that
     * space afterward (an exception will be raised in such case).
     */
    IFillableRemainder cellXRemainder(JComponent component);

    /**
     * Puts the given component in the cell and make 
     * it take all the remaining vertically space of the row.
     * That means no other component can be put or span on that
     * space afterward (an exception will be raised in such case).
     */
    IFillable cellYRemainder(JComponent component);

    /**
     * Puts the given component in the cell and make 
     * it take all the remaining space (both horizontal and vertical)
     * of the row. That means no other component can be put or span on that
     * space afterward (an exception will be raised in such case).
     */
    IFillableRemainder cellXYRemainder(JComponent component);

    /**
     * Adds a separator line.
     */
    void separator();

    /**
     * Adds a separator line but with a label on the left.
     */
    void separator(JLabel label);

    /**
     * Puts the given component in the cell and make it span on 
     * the given number of columns.
     */
    IGridFillable cellX(JComponent component, int span);

    /**
     * Puts the given component in the cell and make it span on 
     * the given number of rows.
     */
    IGridFillable cellY(JComponent component, int span);

    /**
     * Puts the given component in the cell and make it span on 
     * the given number of columns and rows.
     */
    IGridFillable cellXY(JComponent component, int xSpan, int ySpan);
}
