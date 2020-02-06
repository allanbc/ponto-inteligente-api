package com.allanbc.pontointeligente.api.entities;

import com.allanbc.pontointeligente.api.enums.PerfilEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "valor_hora", nullable = true)
    private BigDecimal valorHora;

    @Column(name = "qtd_horas_trabalho_dia", nullable = true)
    private Float qtdHorasTrabalhoDia;

    @Column(name = "qtd_horas_almoco", nullable = true)
    private Float qtdHorasAlmoco;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="empresa_id", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, targetEntity = Lancamento.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    private List<Lancamento> lancamentos;

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
        return "Funcionario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
                + ", valorHora=" + valorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + ", qtdHorasAlmoco="
                + qtdHorasAlmoco + ", perfil=" + perfil + ", dataCriacao="
                + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", empresa=" + empresa + "]";
    }
}
