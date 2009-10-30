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

/**
 * 
 * 
 * @author Ho Tri Bao
 */
public class PainlessGridbagConfiguration {
    // CHECKSTYLE:OFF MagicNumber they are default configuration, not magic
    private int virticalSpacing = 5;
    private int horizontalSpacing = 5;
    private int firstColumnLeftSpacing = 10;
    private int lastColumnRightSpacing = 10;
    private int firstRowTopSpacing = 10;
    private int lastRowBottomSpacing = 10;

    // CHECKSTYLE:ON MagicNumber

    public int getVirticalSpacing() {
        return virticalSpacing;
    }

    public void setVirticalSpacing(final int virticalSpacing) {
        this.virticalSpacing = virticalSpacing;
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

}
