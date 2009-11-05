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

import javax.swing.JButton;
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
        super("Painless-GridBag Tutorial :-)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        anotherLayout();
        layItOut();
        setPreferredSize(new Dimension(800, 309));
    }

    private void layItOut() {
        int i = 0;
        PainlessGridbagConfiguration config = new PainlessGridbagConfiguration();
        config.setVirticalSpacing(0);
        config.setHorizontalSpacing(0);
        
        PainlessGridBag gbl = new PainlessGridBag(getContentPane(), config, false);

        gbl.row().cellXRemainder(new JLabel("PainlessGridBag gbl = new PainlessGridBag(getContentPane( ), false);"));
        gbl.row().cell(new JLabel("gbl.row( )"))
                    .cell(new JButton(".cell(btn1).fillX( )")).fillX()
                    .cell(new JButton(".cell(btn2).fillX( )")).fillX()
                    .cell(new JButton(".cell(btn3).fillX( )")).fillX()
                    .cell(new JButton(".cell(btn4).fillX( );")).fillX();
        gbl.row().cell(new JLabel("gbl.row( )")).cellXRemainder(new JButton(".cellXRemainder(btn5).fillX( );")).fillX();
        gbl.row().cell(new JLabel("gbl.row( )"))
                    .cellX(new JButton(".cellX(btn6, 3).fillX( )"), 3).fillX()
                    .cell(new JButton(".cell(btn7).fillX( );")).fillX();
        gbl.row().cell(new JLabel("gbl.row( )"))
                    .cellY(new JButton(".cellY(btn8, 2).fillXY( )"), 2).fillXY()
                    .cellXRemainder(new JButton(".cellXRemainder(btn9).fillXY( );")).fillXY();
        gbl.row().cell(new JLabel("gbl.row( ).cell( )"))
                    .cell()
                    .cellXRemainder(new JButton(".cellXRemainder(btn10).fillXY( );")).fillXY();
        gbl.row().cell(new JLabel("gbl.row( )"))
                 .cell(new JButton(".cell(btn11).fillX( )")).fillX()
                 .cellXY(new JButton(".cellX(btn12, 2, 2).fillX( )"), 2, 2).fillX()
                 .cell(new JButton(".cell(btn13).fillX( );")).fillX();
        gbl.row().cell(new JLabel("gbl.row( )"))
                 .cell(new JButton(".cell(btn14).fillX( ).cell( ).cell( )")).fillX()
                 .cell().cell()
                 .cell(new JButton(".cell(btn15).fillX( );")).fillX();
        gbl.row().cellXRemainder(new JLabel("gbl.doneAndPushEverythingToTop( );"));
        gbl.doneAndPushEverythingToTop();
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
        System.out.println(demo.getSize());
    }

}
