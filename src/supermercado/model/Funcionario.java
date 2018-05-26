/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import supermercado.util.EnumUtil.Cargo;
import supermercado.util.SQLStrings;

/**
 *
 * @author KillerWorkstation
 */
@Entity
@Table(name = SQLStrings.TBL_FUNCIONARIOS)
public class Funcionario implements Serializable{
    @Id
    @GenericGenerator(name="funcionariogen" , strategy="increment")
    @GeneratedValue(generator="funcionariogen")
    private Integer id;
    
    @Column(length=50, nullable=false)
    private String nome;
    
    @Enumerated(EnumType.ORDINAL)
    private Cargo cargo;

    @NaturalId
    private String login;
    
    @Column(length=20, nullable=false)
    private String senha;
    
    private static Funcionario fInstance = null;
    
    public static Funcionario getFuncionario() {
        return fInstance;
    }
    
    public static void setFuncionario(Funcionario funcionario){
        fInstance = funcionario;
    }
    
    public Funcionario() {
        
    }

    public Funcionario(String nome, Cargo cargo, String login, String senha) {
        this.nome = nome;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
}
