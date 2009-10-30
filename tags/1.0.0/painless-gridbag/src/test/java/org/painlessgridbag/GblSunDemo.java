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
import javax.swing.JFrame;

/**
 * @author Ho Tri Bao
 * 
 */
public class GblSunDemo extends JFrame {
    private static final long serialVersionUID = 4916421449290728730L;

    public GblSunDemo() {
        super("GridBagLayoutDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildLayout();
    }

    private void buildLayout() {
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        JButton button5 = new JButton("Button 5");

        PainlessGridBag pgbl = new PainlessGridBag(getContentPane(), true);

        pgbl.row().cell(button1).fillX().cell(button2).fillX().cell(button3)
                .fillX();
        pgbl.row().cellXRemainder(button4).fillX();
        pgbl.row().cell().cellXRemainder(button5).fillX();

        // customize default constraints set by the utility
        pgbl.constraints(button4).ipady = 40;
        pgbl.constraints(button4).anchor = GridBagConstraints.PAGE_START;
        pgbl.constraints(button4).weighty = 1.0;
        pgbl.constraints(button5).anchor = GridBagConstraints.PAGE_END;
        pgbl.constraints(button5).insets.top = 10;
        pgbl.constraints(button5).weighty = 1.0;

        pgbl.done();
    }

    public static void main(final String[] args) {
        GblSunDemo demo = new GblSunDemo();
        demo.pack();
        demo.setVisible(true);
    }

}
