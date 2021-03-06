/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import supermercado.controller.ItemEstoqueController;
import supermercado.controller.VendaController;
import supermercado.helper.EntityManagerHelper;
import supermercado.model.ItemEstoque;

/**
 *
 * @author KillerWorkstation
 */
public class RelatorioEstoque extends javax.swing.JDialog {
    private DefaultListModel<String> defaultListModel;
    /**
     * Creates new form RelatorioEstoque
     */
    public RelatorioEstoque(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Relatório de estoque");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        defaultListModel = new DefaultListModel<>();
        listEstoque.setModel(defaultListModel);
        populateList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listEstoque = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        listEstoque.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listEstoque);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listEstoque;
    // End of variables declaration//GEN-END:variables

    private void populateList() {
        List<ItemEstoque> itens = new ItemEstoqueController(EntityManagerHelper.entityManagerFactory()).findItemEstoqueEntities();
        HashMap<Integer, Double> idQuantidade = new VendaController(EntityManagerHelper.entityManagerFactory()).getQuantidadeProdutoByVendasDate(new Date());
        for (ItemEstoque item : itens) {
            Double quantidade = idQuantidade.get(item.getId());
            defaultListModel.addElement(item.toString() + " no inicio do dia : " + ((quantidade == null ? 0 : quantidade) + item.getQuantidade()));
        }
        
    }
}
