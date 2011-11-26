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

/**
 * Interface of "fill" operations.
 * 
 * @author Ho Tri Bao
 * 
 */
public interface IFillable {
    /**
     * Makes the component horizontally fills the cell.
     * This is a equivalent to fillX(1.0).
     */
    IGridCell fillX();

    /**
     * Makes the component horizontally fills the cell.
     */
    IGridCell fillX(double weightx);

    /**
     * Makes the component vertically fills the cell.
     * This is a equivalent to fillY(1.0).
     */
    IGridCell fillY();

    /**
     * Makes the component vertically fills the cell.
     */
    IGridCell fillY(double weighty);

    /**
     * Makes the component fills both directions of the cell.
     *  This is a equivalent to fillXY(1.0, 1.0).
     */
    IGridCell fillXY();

    /**
     * Makes the component fills both directions of the cell.
     */
    IGridCell fillXY(double weightx, double weighty);
}
