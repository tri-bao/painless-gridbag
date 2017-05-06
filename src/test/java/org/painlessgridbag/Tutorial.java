package org.painlessgridbag;

import javax.swing.*;

public class Tutorial extends JFrame {

    public static void main(final String[] args) {
        Tutorial tutor = new Tutorial();
        tutor.pack();
        tutor.setVisible(true);
    }

    public Tutorial() {
        super("Tutorial");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        anotherLayout();
    }

    private void anotherLayout() {
        PainlessGridBag gbl = new PainlessGridBag(getContentPane(), config(),false);

        gbl.row().cell(new JLabel("PainlessGridbagConfiguration config = new PainlessGridbagConfiguration();"));
        gbl.row().cell(new JLabel("config.setFirstColumnLeftSpacing(5);"));
        gbl.row().cell(new JLabel("config.setFirstRowTopSpacing(5);"));
        gbl.row().cell(new JLabel("config.setLastColumnRightSpacing(5);"));
        gbl.row().cell(new JLabel("config.setLastRowBottomSpacing(5);"));

        gbl.row().separator();

        gbl.row().cell(new JLabel("PainlessGridBag gbl = new PainlessGridBag(getContentPane(), config, false);"));
        gbl.row().cell(pnlForm()).fillX();
        gbl.row().separator(new JLabel("gbl.row().separator(...)"));
        gbl.row().cell(new JLabel("gbl.doneAndPushEverythingToTop();"));

        gbl.doneAndPushEverythingToTop();
    }

    private PainlessGridbagConfiguration config() {
        PainlessGridbagConfiguration config = new PainlessGridbagConfiguration();
        config.setFirstColumnLeftSpacing(5);
        config.setFirstRowTopSpacing(5);
        config.setLastColumnRightSpacing(5);
        config.setLastRowBottomSpacing(5);
        return config;
    }


    private JPanel pnlForm() {
        JPanel pnl = new JPanel();
        PainlessGridBag gbl = new PainlessGridBag(pnl, config(),true);

        gbl.row().cell(new JLabel("gbl.row()"))
                .cellXRemainder(text(".cellXRemainder(...).fillX()")).fillX();
        gbl.row().cell(new JLabel("gbl.row()"))
                .cell(text(".cell(...)"))
                .cell(text(".cell(...)"))
                .cell(text(".cell(...)"))
                .cell(text(".cell(...)"))
                .cellYRemainder(text(".cellYRemainder(...).fillY()")).fillY();
        gbl.row().cell(new JLabel("gbl.row()"))
                .cellX(text(".cellX(..., 2).fillX()"), 2).fillX()
                .cell(text(".cell(...)"))
                .cellY(text(".cellY(..., 2).fillY()"), 2).fillY();
        gbl.row().cell(new JLabel("gbl.row()"))
                .cellX(text(".cellX(..., 3).fillX()"), 3).fillXY();

        gbl.done();
        return pnl;
    }

    private JComponent text(String txt) {
        JButton btn = new JButton(txt);
        btn.setVerticalAlignment(SwingConstants.TOP);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }
}
