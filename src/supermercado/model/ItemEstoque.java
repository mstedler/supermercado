/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import supermercado.util.SQLStrings;

/**
 *
 * @author KillerWorkstation
 */
@Entity
@Table(name = SQLStrings.TBL_ITEMESTOQUE)
public class ItemEstoque implements Serializable{
    @Id
    @GenericGenerator(name="itemestoquegen" , strategy="increment")
    @GeneratedValue(generator="itemestoquegen")
    private Integer id;
    
    @OneToOne(targetEntity = Produto.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id", unique = true)
    private Produto produto;
    
    @Column
    private Double quantidade;
    

    
    public ItemEstoque(){
        
    }

    public ItemEstoque(Produto produto, Double quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return produto.getDescricao() + " x " + this.quantidade + "("+ produto.getUnidade().toString() +")";
    }
}
