package com.cdboo.business.ui.player.view.setting;


import com.cdboo.business.util.Config;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JColorChooser;

/**
 *
 */
public class PlayListPanel extends javax.swing.JPanel implements Initable {

    private Color titleColor, hilightColor,  indexColor,  lengthColor,  selectedColor,  bg1Color,  bg2Color;
    private Color selectedBGColor;
    private Font font;
    public static final int width = 15;
    public static final int height = 15;

    /** Creates new form PlayListPanel */
    public PlayListPanel() {
        initComponents();
        init();
    }

    public void init() {
        Config config = Config.getConfig();
        titleColor = config.getPlaylistTitleColor();
        hilightColor = config.getPlaylistHiLightColor();
        indexColor = config.getPlaylistIndexColor();
        lengthColor = config.getPlaylistLengthColor();
        selectedColor = config.getPlaylistSelectedColor();
        selectedBGColor=config.getPlaylistSelectedBG();
        bg1Color = config.getPlaylistBackground1();
        bg2Color = config.getPlaylistBackground2();
        font = config.getPlaylistFont();
        title.setIcon(Util.createColorIcon(titleColor, width, height));
        hilight.setIcon(Util.createColorIcon(hilightColor, width, height));
        index.setIcon(Util.createColorIcon(indexColor, width, height));
        length.setIcon(Util.createColorIcon(lengthColor, width, height));
        selected.setIcon(Util.createColorIcon(selectedColor, width, height));
        bg1.setIcon(Util.createColorIcon(bg1Color, width, height));
        bg2.setIcon(Util.createColorIcon(bg2Color, width, height));
        selectBG.setIcon(Util.createColorIcon(selectedBGColor, width, height));
        canDnd.setSelected(config.isCanDnD());
        disableDelete.setSelected(config.isDisableDelete());
        useAbsolutePath.setSelected(config.isSavePlayListByAbsolutePath());
        ignoreBadFile.setSelected(config.isIgnoreBadFile());
        showTip.setSelected(config.isShowTooltipOnPlayList());
        String read = config.getReadTagInfoStrategy();
        if (read != null) {
            readTagStrategy.setSelectedItem(Config.getResource(read));
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        canDnd = new javax.swing.JCheckBox();
        disableDelete = new javax.swing.JCheckBox();
        useAbsolutePath = new javax.swing.JCheckBox();
        ignoreBadFile = new javax.swing.JCheckBox();
        showTip = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        readTagStrategy = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        title = new javax.swing.JButton();
        hilight = new javax.swing.JButton();
        index = new javax.swing.JButton();
        length = new javax.swing.JButton();
        selected = new javax.swing.JButton();
        bg1 = new javax.swing.JButton();
        bg2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        selectBG = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(Config.getResource("PlayListPanel.option"))); // NOI18N

        canDnd.setText(Config.getResource("PlayListPanel.canDnd")); // NOI18N

        disableDelete.setText(Config.getResource("PlayListPanel.disableDelete")); // NOI18N

        useAbsolutePath.setText(Config.getResource("PlayListPanel.saveFileUseAbsolutePath")); // NOI18N

        ignoreBadFile.setText(Config.getResource("PlayListPanel.ignoreErrorFile")); // NOI18N

        showTip.setText(Config.getResource("PlayListPanel.showTooltip")); // NOI18N

        jLabel1.setText(Config.getResource("PlayListPanel.when")); // NOI18N

        readTagStrategy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "添加", "显示", "播放" }));

        jLabel2.setText(Config.getResource("PlayListPanel.readTag")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(canDnd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(useAbsolutePath, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ignoreBadFile)
                            .addComponent(disableDelete)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(showTip)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(readTagStrategy, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(canDnd)
                    .addComponent(disableDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ignoreBadFile)
                    .addComponent(useAbsolutePath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showTip)
                    .addComponent(jLabel2)
                    .addComponent(readTagStrategy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(Config.getResource("PlayListPanel.view"))); // NOI18N

        jLabel3.setText(Config.getResource("PlayListPanel.color")); // NOI18N

        title.setText(Config.getResource("PlayListPanel.title")); // NOI18N
        title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleActionPerformed(evt);
            }
        });

        hilight.setText(Config.getResource("PlayListPanel.hilight")); // NOI18N
        hilight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hilightActionPerformed(evt);
            }
        });

        index.setText(Config.getResource("PlayListPanel.index")); // NOI18N
        index.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexActionPerformed(evt);
            }
        });

        length.setText(Config.getResource("PlayListPanel.length")); // NOI18N
        length.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lengthActionPerformed(evt);
            }
        });

        selected.setText(Config.getResource("PlayListPanel.selected")); // NOI18N
        selected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedActionPerformed(evt);
            }
        });

        bg1.setText(Config.getResource("PlayListPanel.bg1")); // NOI18N
        bg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bg1ActionPerformed(evt);
            }
        });

        bg2.setText(Config.getResource("PlayListPanel.bg2")); // NOI18N
        bg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bg2ActionPerformed(evt);
            }
        });

        jLabel4.setText(Config.getResource("PlayListPanel.font")); // NOI18N

        jButton8.setText(Config.getResource("PlayListPanel.changeFont")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        selectBG.setText(Config.getResource("PlayListPanel.selectedBG")); // NOI18N
        selectBG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(selected)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectBG)
                                .addGap(10, 10, 10)
                                .addComponent(bg1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bg2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(title)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hilight)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(index)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(length))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(title)
                    .addComponent(hilight)
                    .addComponent(index)
                    .addComponent(length))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selected)
                    .addComponent(bg1)
                    .addComponent(selectBG)
                    .addComponent(bg2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton9.setText(Config.getResource("save")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9);

        jButton10.setText(Config.getResource("reset")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Font f = FontChooser.showDialog(null, Config.getResource("PlayListPanel.selectFont"), font);
        if (f != null) {
            font = f;
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleActionPerformed
        // TODO add your handling code here:
        Color c = JColorChooser.showDialog(this, Config.getResource("PlayListPanel.selectTitleColor"), titleColor);
        if (c != null) {
            titleColor = c;
            title.setIcon(Util.createColorIcon(c, width, height));
        }
    }//GEN-LAST:event_titleActionPerformed

    private void hilightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hilightActionPerformed
        // TODO add your handling code here:
        Color c=JColorChooser.showDialog(this, Config.getResource("PlayListPanel.selectHilightColor"), hilightColor);
        if(c!=null){
            hilightColor=c;
            hilight.setIcon(Util.createColorIcon(c, width, height));
        }
    }//GEN-LAST:event_hilightActionPerformed

    private void indexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexActionPerformed
        // TODO add your handling code here:
        Color c = JColorChooser.showDialog(this, Config.getResource("PlayListPanel.selectIndexColor"), indexColor);
        if (c != null) {
            indexColor = c;
            index.setIcon(Util.createColorIcon(c, width, height));
        }
    }//GEN-LAST:event_indexActionPerformed

    private void lengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthActionPerformed
        // TODO add your handling code here:
        Color c = JColorChooser.showDialog(this, Config.getResource("PlayListPanel.selectLengthColor"), lengthColor);
        if (c != null) {
            lengthColor = c;
            length.setIcon(Util.createColorIcon(c, width, height));
        }
    }//GEN-LAST:event_lengthActionPerformed

    private void selectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedActionPerformed
        // TODO add your handling code here:
        Color c = JColorChooser.showDialog(this, Config.getResource("PlayListPanel.selectSelectedColor"), selectedColor);
        if (c != null) {
            selectedColor = c;
            selected.setIcon(Util.createColorIcon(c, width, height));
        }
    }//GEN-LAST:event_selectedActionPerformed

    private void bg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bg1ActionPerformed
        // TODO add your handling code here:
        Color c = JColorChooser.showDialog(this, Config.getResource("PlayListPanel.selectBg1Color"), bg1Color);
        if (c != null) {
            bg1Color = c;
            bg1.setIcon(Util.createColorIcon(c, width, height));
        }
    }//GEN-LAST:event_bg1ActionPerformed

    private void bg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bg2ActionPerformed
        // TODO add your handling code here:
        Color c = JColorChooser.showDialog(this, Config.getResource("PlayListPanel.selectBg2Color"), bg2Color);
        if (c != null) {
            bg2Color = c;
            bg2.setIcon(Util.createColorIcon(c, width, height));
        }
    }//GEN-LAST:event_bg2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        doSave();
        Config.getConfig().getPlWindow().repaint();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void selectBGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBGActionPerformed
        // TODO add your handling code here:
        Color c = JColorChooser.showDialog(this, Config.getResource("PlayListPanel.selectSelectedBGColor"), selectedBGColor);
        if (c != null) {
            selectedBGColor = c;
            selectBG.setIcon(Util.createColorIcon(c, width, height));
        }
}//GEN-LAST:event_selectBGActionPerformed

    private void doSave() {
        Config config = Config.getConfig();
        config.setPlaylistBackground1(bg1Color);
        config.setPlaylistBackground2(bg2Color);
        config.setPlaylistFont(font);
        config.setPlaylistHiLightColor(hilightColor);
        config.setPlaylistIndexColor(indexColor);
        config.setPlaylistLengthColor(lengthColor);
        config.setPlaylistSelectedColor(selectedColor);
        config.setPlaylistTitleColor(titleColor);
        config.setPlaylistSelectedBG(selectedBGColor);
        config.setCanDnD(canDnd.isSelected());
        config.setDisableDelete(disableDelete.isSelected());
        config.setSavePlayListByAbsolutePath(useAbsolutePath.isSelected());
        config.setIgnoreBadFile(ignoreBadFile.isSelected());
        config.setShowTooltipOnPlayList(showTip.isSelected());
        config.setReadTagInfoStrategy(getReadTagInfoStategy(readTagStrategy.getSelectedItem().toString()));
        if (config.isAutoCloseDialogWhenSave()) {
            config.getOptionDialog().setVisible(false);
        }
    }

    private String getReadTagInfoStategy(String s) {
        String[] ss = {Config.READ_WHEN_ADD, Config.READ_WHEN_DISPLAY, Config.READ_WHEN_PLAY};
        for (String temp : ss) {
            if (Config.getResource(temp).equals(s)) {
                return temp;
            }
        }
        return Config.READ_WHEN_ADD;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bg1;
    private javax.swing.JButton bg2;
    private javax.swing.JCheckBox canDnd;
    private javax.swing.JCheckBox disableDelete;
    private javax.swing.JButton hilight;
    private javax.swing.JCheckBox ignoreBadFile;
    private javax.swing.JButton index;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton length;
    private javax.swing.JComboBox readTagStrategy;
    private javax.swing.JButton selectBG;
    private javax.swing.JButton selected;
    private javax.swing.JCheckBox showTip;
    private javax.swing.JButton title;
    private javax.swing.JCheckBox useAbsolutePath;
    // End of variables declaration//GEN-END:variables
}
