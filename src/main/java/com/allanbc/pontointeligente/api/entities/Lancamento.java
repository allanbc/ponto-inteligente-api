package com.allanbc.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;

import com.allanbc.pontointeligente.api.enums.TipoEnum;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 6524560251526772839L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "descricao", nullable = true)
    private String descricao;

    @Column(name = "localizacao", nullable = true)
    private String localizacao;

    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEnum tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="funcionario_id")
    private Funcionario funcionario;

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

    @Override
    public String toString() {
        return "Lancamento [id=" + id + ", data=" + data + ", descricao=" + descricao + ", localizacao=" + localizacao
                + ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", tipo=" + tipo
                + ", funcionario=" + funcionario + "]";
    }

}
