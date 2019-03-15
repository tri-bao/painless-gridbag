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

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Ho Tri Bao
 * 
 */
public class Debugger {
    private final boolean debug;

    public Debugger(final boolean debug) {
        this.debug = debug;
    }

    public JComponent getDebugPanel(
            final JComponent compo,
            final GridBagConstraints constraint) {
        if (!debug) {
            return compo;
        }
        final JPanel result = new JPanel();
        result.setToolTipText(compo.getToolTipText());
        compo.setToolTipText(null);
        result
                .setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                        Color.RED));
        result.setLayout(new GridBagLayout());
        GridBagConstraints clonedConst = (GridBagConstraints) constraint
                .clone();
        clonedConst.insets = new Insets(0, 0, 0, 0);
        result.add(compo, clonedConst);
        compo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                if ((e.getComponent() != null)
                        && e.getComponent() instanceof JComponent) {
                    JComponent component = (JComponent) e.getComponent();
                    if (component.getToolTipText(e) == null) {
                        component.setToolTipText(dump(component,
                                                        result.getToolTipText(),
                                                        constraint));
                    }
                }
            }
        });
        return result;
    }

    private String dump(
            final JComponent compo, 
            final String orgTt,
            final GridBagConstraints constraint) {
        StringBuilder str = new StringBuilder();
        str.append("<html>");
        if (orgTt != null) {
            str.append("<b>Component tooltip:</b>").append(
                    orgTt.replaceAll("<html>", "").replaceAll("</html>", ""));
            str.append("<br><b>PreferredSize:</b>: ");
        } else {
            str.append("<b>PreferredSize:</b>: ");
        }
        str.append("<br> - width: ").append(compo.getPreferredSize().width);
        str.append("<br> - height: ").append(compo.getPreferredSize().height);
        str.append("<br><b>Actual size:</b>: ");
        str.append("<br> - width: ").append(compo.getWidth());
        str.append("<br> - height: ").append(compo.getHeight());
        str.append("<br><b>Constraints:</b>");
        str.append("<br> - anchor: ").append(dumpAnchor(constraint.anchor));
        str.append("<br> - fill: ").append(dumpFill(constraint.fill));
        str.append("<br> - gridheight: ").append(
                dumpWidthHeight(constraint.gridheight));
        str.append("<br> - gridwidth: ").append(
                dumpWidthHeight(constraint.gridwidth));
        str.append("<br> - gridx: ").append(dumpXY(constraint.gridx));
        str.append("<br> - gridy: ").append(dumpXY(constraint.gridy));
        str.append("<br> - ipadx: ").append(constraint.ipadx);
        str.append("<br> - ipady: ").append(constraint.ipady);
        str.append("<br> - weightx: ").append(constraint.weightx);
        str.append("<br> - weighty: ").append(constraint.weighty);
        str.append("<br> - inserts: ").append(dumpInserts(constraint.insets));
        str.append("</html>");

        return str.toString();
    }

    private String dumpAnchor(final int anchor) {
        if (anchor == GridBagConstraints.NORTH) {
            return "NORTH";
        } else if (anchor == GridBagConstraints.NORTHEAST) {
            return "NORTHEAST";
        } else if (anchor == GridBagConstraints.NORTHWEST) {
            return "NORTHWEST";
        } else if (anchor == GridBagConstraints.WEST) {
            return "WEST";
        } else if (anchor == GridBagConstraints.EAST) {
            return "EAST";
        } else if (anchor == GridBagConstraints.SOUTH) {
            return "SOUTH";
        } else if (anchor == GridBagConstraints.SOUTHEAST) {
            return "SOUTHEAST";
        } else if (anchor == GridBagConstraints.SOUTHWEST) {
            return "SOUTHWEST";
        } else if (anchor == GridBagConstraints.CENTER) {
            return "CENTER";
        } else if (anchor == GridBagConstraints.FIRST_LINE_START) {
            return "FIRST_LINE_START";
        } else if (anchor == GridBagConstraints.PAGE_START) {
            return "PAGE_START";
        } else if (anchor == GridBagConstraints.FIRST_LINE_END) {
            return "FIRST_LINE_END";
        } else if (anchor == GridBagConstraints.LINE_START) {
            return "LINE_START";
        } else if (anchor == GridBagConstraints.LINE_END) {
            return "LINE_END";
        } else if (anchor == GridBagConstraints.LAST_LINE_START) {
            return "LAST_LINE_START";
        } else if (anchor == GridBagConstraints.PAGE_END) {
            return "PAGE_END";
        } else if (anchor == GridBagConstraints.LAST_LINE_END) {
            return "LAST_LINE_END";
        }

        throw new IllegalArgumentException("Invalid anchor: " + anchor);
    }

    private String dumpFill(final int fill) {
        if (fill == GridBagConstraints.BOTH) {
            return "BOTH";
        } else if (fill == GridBagConstraints.HORIZONTAL) {
            return "HORIZONTAL";
        } else if (fill == GridBagConstraints.VERTICAL) {
            return "VERTICAL";
        } else if (fill == GridBagConstraints.NONE) {
            return "NONE";
        }
        throw new IllegalArgumentException("Invalid fill: " + fill);
    }

    private String dumpWidthHeight(final int value) {
        if (value == GridBagConstraints.RELATIVE) {
            return "RELATIVE";
        } else if (value == GridBagConstraints.REMAINDER) {
            return "REMAINDER";
        }
        return Integer.toString(value);
    }

    private String dumpXY(final int value) {
        if (value == GridBagConstraints.RELATIVE) {
            return "RELATIVE";
        }
        return Integer.toString(value);
    }

    private String dumpInserts(final Insets insets) {
        StringBuilder str = new StringBuilder();
        str.append("[top = ").append(insets.top).append(", left = ").append(
                insets.left).append(", bottom = ").append(insets.bottom)
                .append(", right = ").append(insets.right).append("]");
        return str.toString();
    }
}
