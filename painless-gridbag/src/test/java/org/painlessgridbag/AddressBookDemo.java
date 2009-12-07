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

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author Ho Tri Bao
 * 
 */
public class AddressBookDemo extends JFrame {
    JScrollPane scrPeople = new JScrollPane();
    private final JList lstPeople = new JList(new String[] {
            "Bunny, Bugs",
            "Cat, Sylvester",
            "Coyote, Wile E.",
            "Devil, Tasmanian",
            "Duck, Daffy",
            "Fudd, Elmer",
            "Le Pew, Pepe",
            "Martian, Marvin",
    });
    private final JTextField txtFirstName = new JTextField("Martian");
    private final JTextField txtLastName = new JTextField("Marvin");
    private final JTextField txtPhone = new JTextField("805-123-4567");
    private final JTextField txtEmail = new JTextField("marvin@wb.com");
    private final JTextField txtAdd1 = new JTextField("1001001010101 Martian Way");
    private final JTextField txtAdd2 = new JTextField("Suite 10111011");
    private final JTextField txtCity = new JTextField("Ventura");
    private final JTextField txtState = new JTextField("CA");
    private final JTextField txtPostal = new JTextField("93001");
    private final JTextField txtCountry = new JTextField("USA");
    private final JButton btnNew = new JButton("New");
    private final JButton btnDelete = new JButton("Delete");
    private final JButton btnEdit = new JButton("Edit");
    private final JButton btnSave = new JButton("Save");
    private final JButton btnCancel = new JButton("Cancel");

    // Because they are special-aligned labels :-)
    private final JLabel lblFirstName = new JLabel("First name");
    private final JLabel lblEmail = new JLabel("Email");
    private final JLabel lblPostal = new JLabel("Postal code");

    
    public AddressBookDemo() {
        super("Address book demo with painless-gridbag");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        initLayout();
        
    }

    private void initComponents() {
        setPreferredSize(new Dimension(600, 309));
        setMinimumSize(new Dimension(600, 309));
        scrPeople.setViewportView(lstPeople);
    }

    private void initLayout() {
        PainlessGridbagConfiguration config = new PainlessGridbagConfiguration();
        // All JLabels have to be right-aligned.
        config.setAlignAllLabelsToRight(true);
        // Except these lables
        config.setLeftAlignLabels(new JLabel[] {lblFirstName, lblPostal, lblEmail});
        
        PainlessGridBag gbl = new PainlessGridBag(getContentPane(), config, false);
        gbl.row().cellYRemainder(scrPeople).fillXY().cell(newLbl("Last name")).cell(txtLastName).fillX().cellX(lblFirstName, 2).cell(txtFirstName).fillX();
        gbl.row().cell().cell(newLbl("Phone")).cell(txtPhone).fillX().cell(lblEmail).cellX(txtEmail, 2).fillX();
        gbl.row().cell().cell(newLbl("Address 1")).cellXRemainder(txtAdd1).fillX();
        gbl.row().cell().cell(newLbl("Address 2")).cellXRemainder(txtAdd2).fillX();
        gbl.row().cell().cell(newLbl("City")).cell(txtCity).fillX();
        gbl.row().cell().cell(newLbl("State")).cell(txtState).fillX().cellX(lblPostal, 2).cell(txtPostal).fillX();
        gbl.row().cell().cell(newLbl("Country")).cell(txtCountry).fillX();
        
        // Add button panel
        JPanel pnlButton = new JPanel();
        PainlessGridBag gblBtn = new PainlessGridBag(pnlButton, false);
        LayoutUtils.addButtonPanel(gblBtn, btnNew, btnDelete, btnEdit, btnSave, btnCancel);
        gblBtn.done();
        gbl.row().cell().cellXRemainder(pnlButton).fillX();

        // This is the trick in GridBagLayout which is used to push everything up.
        gbl.row().cell().cellXRemainder(new JPanel()).fillXY();
        
        // Adjust some spacings that don't meet the requirement.
        gbl.constraints(scrPeople).insets.bottom = config.getLastRowBottomSpacing();
        gbl.constraints(txtCity).insets.right = config.getVirticalSpacing();
        gbl.constraints(txtCountry).insets.right = config.getVirticalSpacing();
        gbl.constraints(pnlButton).insets.right = 0;
        gbl.constraints(lblFirstName).anchor = GridBagConstraints.LINE_START;
        
        gbl.done();
    }

    private JLabel newLbl(String lbl) {
        return new JLabel(lbl);
    }
    
    public static void main(final String[] args) {
        AddressBookDemo demo = new AddressBookDemo();
        demo.pack();
        demo.setVisible(true);
    }

}
