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

/**
 * 
 * @author Ho Tri Bao
 */
public class ComponentInCell {
    private final JComponent component;
    private final boolean remainderX;
    private final boolean remainderY;

    public ComponentInCell(final JComponent component,
            final boolean remainderX, final boolean remainderY) {
        this.component = component;
        this.remainderX = remainderX;
        this.remainderY = remainderY;
    }

    public JComponent getComponent() {
        return component;
    }

    public boolean isRemainderX() {
        return remainderX;
    }

    public boolean isRemainderY() {
        return remainderY;
    }

}
