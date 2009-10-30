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

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComponent;

import org.painlessgridbag.engine.Debugger;
import org.painlessgridbag.engine.GridCell;
import org.painlessgridbag.engine.IGridCell;

/**
 * Utility that help use GridBagLayout easily.
 * <p>
 * <b>Usage:</b>
 * <ul>
 * <li><b>Starting point: </b> method <code>row()</code>
 * <li>if you want to change constraints of a certain component, call method
 * <code>constraints(JComponent)</code>
 * <li><b>End point: </b> method <code>done()</code>. By calling that method,
 * you tell the utility that you have finished defining the layout. Then the
 * utility can do its job. After calling this method:
 * <ul>
 * <li>You will not be able to call to <code>row()</code> method.
 * <li>But you still can call to the <code>constraints(JComponent)</code>
 * method.
 * </ul>
 * </ul>
 * </p>
 * 
 * @author Ho Tri Bao
 */
public class PainlessGridBag {
    private final Container container;
    private final Debugger debugger;
    private boolean done = false;
    private final GridCell layout;

    /**
     * @param container
     *            this is where your components are layout.
     * @param debug
     *            if <code>true</code>, there will be a red border drawn around
     *            your components and when you move the mouse over the
     *            component, a tooltip will be shown up with information about
     *            the grid bag constraints set to the component.
     */
    public PainlessGridBag(final Container container, final boolean debug) {
        this(container, new PainlessGridbagConfiguration(), debug);
    }

    public PainlessGridBag(final Container container,
            final PainlessGridbagConfiguration config, final boolean debug) {
        this.container = container;
        this.debugger = new Debugger(debug);
        this.layout = new GridCell(config);
    }

    /**
     * Creates a row of components in the grid (this is your starting method).
     * 
     * @return an object from which you can continue filling in cells in that
     *         row.
     */
    public IGridCell row() {
        checkDone();
        layout.newRow();

        return layout;
    }

    /**
     * Gets the grid bag constraints currently assigned to the given component.
     * Use this method when you want to change default constraints set by the
     * utility.
     */
    public GridBagConstraints constraints(final JComponent component) {
        checkDone();
        return layout.getChildren().get(component);
    }

    public void done() {
        checkDone();
        container.setLayout(new GridBagLayout());
        layout.makeColumnsSameWidth();
        layout.done();

        Map<JComponent, GridBagConstraints> children = layout.getChildren();
        for (Entry<JComponent, GridBagConstraints> entry
                : children.entrySet()) {
            container.add(debugger.getDebugPanel(entry.getKey(), entry
                    .getValue()), entry.getValue());
        }
        done = true;
    }

    private void checkDone() {
        if (done) {
            throw new IllegalStateException(
                    "The layout has been applied (by calling 'done' method)"
                            + " No further operation is allowed");
        }
    }
}
