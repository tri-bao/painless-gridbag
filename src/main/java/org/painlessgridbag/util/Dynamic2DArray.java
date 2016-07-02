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
package org.painlessgridbag.util;

import java.lang.reflect.Array;

/**
 * This is a 2-dimension array. Its size (both x and y) will be grow
 * automatically (if needed) when something is put into a cell.
 * 
 * @author Ho Tri Bao
 * 
 */
public class Dynamic2DArray<T> {
    private static final int DEF_X_LENGTH = 20;
    private static final int DEF_Y_LENGTH = 20;

    private final Class<T> dataClass;
    private final boolean muttable;
    private int maxXLength;
    private int maxYLength;
    private int xLength = 0;
    private int yLength = 0;
    private T[][] arr;

    /**
     * Constructs the array.
     * 
     * @param dataClass
     *            Class of data object stored in the array.
     * @param muttable
     *            if false, the IllegalArgumentException will be thrown if
     *            something is put into a non-empty cell.
     */
    public Dynamic2DArray(final Class<T> dataClass, final boolean muttable) {
        this(dataClass, muttable, DEF_X_LENGTH, DEF_Y_LENGTH);
    }

    /**
     * Use this constructor if you foreseen the size of the array.
     * 
     * @param dataClass
     *            Class of data object stored in the array.
     * @param muttable
     *            if false, the IllegalArgumentException will be thrown if
     *            something is put into a non-empty cell.
     * @param xLength
     *            initial horizontal length
     * @param yLength
     *            initial vertical length
     */
    public Dynamic2DArray(final Class<T> dataClass,
                          final boolean muttable,
                          final int xLength,
                          final int yLength) {
        Assert.isTrue(dataClass != null, "dataClass must not be null");

        this.dataClass = dataClass;
        this.muttable = muttable;
        this.maxXLength = xLength;
        this.maxYLength = yLength;
        arr = createArray(xLength, yLength);
    }

    @SuppressWarnings("unchecked")
    private T[][] createArray(final int lengthX, final int lengthY) {
        Assert.isTrue(lengthX > 0, "lengthX must be greater than zero");
        Assert.isTrue(lengthY > 0, "lengthY must be greater than zero");

        // not x ==> column; y ==> row
        return (T[][]) Array.newInstance(dataClass,
                new int[] {lengthY, lengthX});
    }

    /**
     * Get data at the given cell.
     * 
     * @param x
     *            column number of the cell.
     * @param y
     *            row number of the cell.
     * @return data data object.
     */
    public T get(final int x, final int y) {
        return arr[y][x];
    }

    /**
     * Put the given object to the given cell.
     * 
     * @param x
     *            column number of the cell.
     * @param y
     *            row number of the cell.
     * @param data
     *            data object.
     */
    public void put(final int x, final int y, final T data) {
        Assert.isTrue(x >= 0, "x must be possitive or zero");
        Assert.isTrue(y >= 0, "y must be possitive or zero");

        // need to grow?
        grow(x, y);

        // need to update current length
        if (x >= xLength) {
            xLength = x + 1;
        }
        if (y >= yLength) {
            yLength = y + 1;
        }

        // mutable or not
        if (!this.muttable && (arr[y][x] != null)) {
            throw new IllegalArgumentException("Cell (" + x + ", " + y
                    + ") is occupied");
        }
        arr[y][x] = data;
    }

    private void grow(final int x, final int y) {
        boolean grow = false;
        if (x >= maxXLength) {
            if (y >= maxYLength) {
                // grow both size
                maxXLength = x + 1;
                maxYLength = y + 1;
                grow = true;
            } else {
                // grow X only
                maxXLength = x + 1;
                grow = true;
            }
        } else {
            if (y >= maxYLength) {
                // grow Y only
                maxYLength = y + 1;
                grow = true;
            }
        }
        if (grow) {
            T[][] temp = createArray(maxXLength, maxYLength);
            for (int row = 0; row < arr.length; row++) {
                for (int col = 0; col < arr[row].length; col++) {
                    temp[row][col] = arr[row][col];
                }
            }
            arr = temp;
        }
    }

    public int getXLength() {
        return xLength;
    }

    public int getYLength() {
        return yLength;
    }

}
