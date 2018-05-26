/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import supermercado.util.MathUtil;
import supermercado.util.SQLStrings;

/**
 *
 * @author KillerWorkstation
 */
@Entity
@Table(name = SQLStrings.TBL_ITEMVENDA)
public class ItemVenda implements Serializable{
    @Id
    @GenericGenerator(name="itemvendagen" , strategy="increment")
    @GeneratedValue(generator="itemvendagen")
    private Integer id;
    
    @OneToOne(targetEntity = Produto.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    @Column
    private Double quantidade;
    
    @Column 
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;
    
    public ItemVenda(Integer id, Produto produto, Double quantidade, BigDecimal valor) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    public ItemVenda() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    @Override
    public String toString() {
        return produto.getDescricao() + " x " + this.quantidade + "("+ produto.getUnidade().toString() +") = " + MathUtil.Currency.format(valor);
    } 
    
}
