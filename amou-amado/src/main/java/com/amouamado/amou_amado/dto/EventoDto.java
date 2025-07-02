package com.amouamado.amou_amado.dto;

public class EventoDto {

    private Long id;
    private String titulo;
    private String categoria;
    private String local;
    private String imagem;
    private String duracao;
    private String classificacaoEtaria;
    private String data;
    private String horario;
    private Double preco;
    private String descricao;

    // Campos adicionais do front-end:
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
    private String twitter;
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    private String linkedin;


    // Construtor padrão
    public EventoDto() {
    }

    // Construtor principal (com os campos mais usados)
    public EventoDto(Long id, String titulo, String categoria, String local, String imagem,
                     String duracao, String classificacaoEtaria, String data, String horario,
                     Double preco, String descricao, String politicas, String destaques,
                     String artistas, String bairro, String cidade, String estado,
                     String cep, String telefoneContato, String emailContato,
                     String websiteContato,String twitter,String linkedin) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.local = local;
        this.imagem = imagem;
        this.duracao = duracao;
        this.classificacaoEtaria = classificacaoEtaria;
        this.data = data;
        this.horario = horario;
        this.preco = preco;
        this.descricao = descricao;
        this.politicas = politicas;
        this.destaques = destaques;
        this.artistas = artistas;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefoneContato = telefoneContato;
        this.emailContato = emailContato;
        this.websiteContato = websiteContato;
        this.twitter=twitter;
        this.linkedin=linkedin;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(String classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPoliticas() {
        return politicas;
    }

    public void setPoliticas(String politicas) {
        this.politicas = politicas;
    }

    public String getDestaques() {
        return destaques;
    }

    public void setDestaques(String destaques) {
        this.destaques = destaques;
    }

    public String getArtistas() {
        return artistas;
    }

    public void setArtistas(String artistas) {
        this.artistas = artistas;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getWebsiteContato() {
        return websiteContato;
    }

    public void setWebsiteContato(String websiteContato) {
        this.websiteContato = websiteContato;
    }
}
