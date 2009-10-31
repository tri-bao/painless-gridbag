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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Ho Tri Bao
 * 
 */
public class Demo extends JFrame {
    private static final long serialVersionUID = 4916421449290728730L;

    public Demo() {
        super("Painless-GridBag Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        anotherLayout();
    }

    private void anotherLayout() {
        JLabel lblFirstName = new JLabel("First name");
        JTextField txtFirstName = new JTextField();
        JLabel lblFamilyName = new JLabel("Family name");
        JTextField txtFamilyName = new JTextField();
        JLabel lblAddress = new JLabel("Address");
        JTextField txtAddress = new JTextField();
        
        PainlessGridBag gbl = new PainlessGridBag(getContentPane(), false);
        gbl.row().cell(lblFirstName).cell(txtFirstName).fillX()
                    .cell(lblFamilyName).cell(txtFamilyName).fillX();
        gbl.row().cell(lblAddress).cellXRemainder(txtAddress).fillX();
        gbl.doneAndPushEverythingToTop();
    }

    public static void main(final String[] args) {
        Demo demo = new Demo();
        demo.pack();
        demo.setVisible(true);
    }

}
