package com.amouamado.amou_amado.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Evento {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@ManyToOne(optional = true)
@JoinColumn(name = "usuario_id", nullable = true)
private Usuario usuario;

    private String titulo;
   
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private String local;
    private String imagem;
    private String duracao;
    private String classificacaoEtaria;
    private String dataEvento;
    private String horario;
    private Double preco;
    private String descricao;
    private String politicas;
    private String destaques;
    private String artistas;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String telefoneContato;
    private String emailContato;
    private String websiteContato;
    private String linkedin;
    private String twitter; 

    public Long getId() {
    return id;
    }

    public void setId(Long id) {
    this.id = id;
    }
 }
