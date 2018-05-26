/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import supermercado.controller.ItemEstoqueController;
import supermercado.controller.ProdutoController;
import supermercado.helper.EntityManagerHelper;
import supermercado.model.ItemEstoque;
import supermercado.model.Produto;
import supermercado.util.EnumUtil;
import supermercado.util.MathUtil;

/**
 *
 * @author KillerWorkstation
 */
public class Cadastro extends javax.swing.JDialog {

    private Produto produto;
    private ItemEstoque itemEstoque;

    public Cadastro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Cadastro de Produto");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        lblAviso.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblAviso = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lblUnidade = new javax.swing.JLabel();
        cbUnidade = new javax.swing.JComboBox<>();
        lblQuantidade = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblCodigo.setText("Código Produto");

        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        lblAviso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAviso.setText("Produto não está cadastrado, será criado um novo item de estoque");

        lblDescricao.setText("Descrição");

        lblValor.setText("Valor Unitário");

        lblUnidade.setText("Unidade");

        cbUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quilo", "Unidade" }));

        lblQuantidade.setText("Quantidade");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAviso)
                    .addComponent(lblDescricao)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValor)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUnidade)
                            .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQuantidade)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDescricao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValor)
                    .addComponent(lblUnidade)
                    .addComponent(lblQuantidade))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        String naturalId = txtCodigo.getText().trim();
        if (naturalId.isEmpty()) {
            return;
        }
        produto = new ProdutoController(EntityManagerHelper.entityManagerFactory()).bySimpleNaturalId(naturalId);
        if (produto == null) {
            produto = new Produto();
            lblAviso.setVisible(true);
            txtDescricao.setText("");
            txtQuantidade.setText("");
            cbUnidade.setSelectedIndex(EnumUtil.Unidade.QUILO.ordinal());
            txtValor.setText("");
        } else {
            lblAviso.setVisible(false);
            txtDescricao.setText(produto.getDescricao());
            txtQuantidade.setText(produto.getItemEstoque().getQuantidade() + "");
            cbUnidade.setSelectedIndex(produto.getUnidade().ordinal());
            txtValor.setText(MathUtil.Currency.format(produto.getValorUnitario()));
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        BigDecimal valorUnitario = new BigDecimal(0);
        produto.setCodigo(txtCodigo.getText().trim());
        produto.setDescricao(txtDescricao.getText().trim());
        produto.setUnidade(EnumUtil.Unidade.values()[cbUnidade.getSelectedIndex()]);
        try {
            valorUnitario = new BigDecimal(txtValor.getText().trim().replace(',', '.'));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
            return;
        }
        if (valorUnitario.compareTo(new BigDecimal(0)) <= 0) {
            JOptionPane.showMessageDialog(rootPane, "valor inválido");
            return;
        }
        produto.setValorUnitario(valorUnitario);
        if (produto.getItemEstoque() != null) {
            itemEstoque = produto.getItemEstoque();
        } else {
            itemEstoque = new ItemEstoque();
        }
        itemEstoque.setQuantidade(Double.valueOf(txtQuantidade.getText().trim()));
        itemEstoque.setProduto(produto);
        try {
            new ItemEstoqueController(EntityManagerHelper.entityManagerFactory()).edit(itemEstoque);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getLocalizedMessage());
            return;
        }
        JOptionPane.showMessageDialog(rootPane, "Operação concluída");
        dispose();
    }//GEN-LAST:event_btnSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbUnidade;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblUnidade;
    private javax.swing.JLabel lblValor;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}