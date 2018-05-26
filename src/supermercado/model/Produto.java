/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import supermercado.util.EnumUtil.Unidade;
import supermercado.util.SQLStrings;

/**
 *
 * @author KillerWorkstation
 */
@Entity
@Table(name = SQLStrings.TBL_PRODUTOS)
public class Produto implements Serializable {
    @Id
    @GenericGenerator(name="produtogen" , strategy="increment")
    @GeneratedValue(generator="produtogen")
    private int id;
    
    @NaturalId
    private String codigo;
    
    @Column
    private String descricao;
    
    @Column
    private Unidade unidade;
    
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    
    @OneToOne(mappedBy = "produto")
    private ItemEstoque itemEstoque;

    public Produto() {
    }

    public Produto(String codigo, String descricao, Unidade unidade, BigDecimal valorUnitario) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.unidade = unidade;
        this.valorUnitario = valorUnitario;
    }

    public ItemEstoque getItemEstoque() {
        return itemEstoque;
    }

    public void setItemEstoque(ItemEstoque itemEstoque) {
        this.itemEstoque = itemEstoque;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }  
    
}
