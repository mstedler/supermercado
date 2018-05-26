/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import supermercado.util.EnumUtil;
import supermercado.util.MathUtil;
import supermercado.util.SQLStrings;

/**
 *
 * @author KillerWorkstation
 */
@Entity
@Table(name = SQLStrings.TBL_VENDAS)
public class Venda implements Serializable {
    @Id
    @GenericGenerator(name="vendagen" , strategy="increment")
    @GeneratedValue(generator="vendagen")
    private Integer id;
    
    @Column
    private BigDecimal valor;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dataVenda;
    
    @Column
    private int caixa;
    
    
    @OneToMany(mappedBy = "venda", targetEntity = ItemVenda.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemVenda> itensVenda;
    
    @Enumerated(EnumType.ORDINAL)
    private EnumUtil.Pagamento pagamento = EnumUtil.Pagamento.DINHEIRO;

    @Column
    private BigDecimal troco;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
    
    public Venda() {
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
    
    

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    
    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public EnumUtil.Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(EnumUtil.Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    
    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public Date getData() {
        return dataVenda;
    }

    public void setData(Date data) {
        this.dataVenda = data;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Total : " + MathUtil.Currency.format(this.valor) + "  " + this.funcionario.getNome();
    }
    
}
