package com.amouamado.amou_amado.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "organizador")
public class Organizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String localizacao;
    private String membroDesde;
    private String biografia;
    private String email;
    private String telefone;
    private String website;
    private Integer eventosRealizados;

    private String instagram;
    private String facebook;
    private String twitter;
    private String linkedin;

    private String fotoPerfilUrl;
    private String fotoCapaUrl;


    @ElementCollection
    @CollectionTable(name = "organizador_galeria", joinColumns = @JoinColumn(name = "organizador_id"))
    @Column(name = "imagem_url")
    private List<String> galeriaImagens;

    public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getLocalizacao() {
    return localizacao;
}

public void setLocalizacao(String localizacao) {
    this.localizacao = localizacao;
}

public String getMembroDesde() {
    return membroDesde;
}

public void setMembroDesde(String membroDesde) {
    this.membroDesde = membroDesde;
}

public String getBiografia() {
    return biografia;
}

public void setBiografia(String biografia) {
    this.biografia = biografia;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getTelefone() {
    return telefone;
}

public void setTelefone(String telefone) {
    this.telefone = telefone;
}

public String getWebsite() {
    return website;
}

public void setWebsite(String website) {
    this.website = website;
}

public Integer getEventosRealizados() {
    return eventosRealizados;
}

public void setEventosRealizados(Integer eventosRealizados) {
    this.eventosRealizados = eventosRealizados;
}

public String getInstagram() {
    return instagram;
}

public void setInstagram(String instagram) {
    this.instagram = instagram;
}

public String getFacebook() {
    return facebook;
}

public void setFacebook(String facebook) {
    this.facebook = facebook;
}

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

public String getFotoPerfilUrl() {
    return fotoPerfilUrl;
}

public void setFotoPerfilUrl(String fotoPerfilUrl) {
    this.fotoPerfilUrl = fotoPerfilUrl;
}

public String getFotoCapaUrl() {
    return fotoCapaUrl;
}

public void setFotoCapaUrl(String fotoCapaUrl) {
    this.fotoCapaUrl = fotoCapaUrl;
}

}