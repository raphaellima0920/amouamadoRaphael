package com.amouamado.amou_amado.dto;

import java.util.List;

public class OrganizadorDTO {

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
    private String fotoPerfilUrl;
    private String fotoCapaUrl;

    @SuppressWarnings("unused")
    private List<String> galeriaImagens;


    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public String getMembroDesde() { return membroDesde; }
    public void setMembroDesde(String membroDesde) { this.membroDesde = membroDesde; }

    public String getBiografia() { return biografia; }
    public void setBiografia(String biografia) { this.biografia = biografia; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public Integer getEventosRealizados() { return eventosRealizados; }
    public void setEventosRealizados(Integer eventosRealizados) { this.eventosRealizados = eventosRealizados; }

    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }

    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }

    public String getFotoPerfilUrl() { return fotoPerfilUrl; }
    public void setFotoPerfilUrl(String fotoPerfilUrl) { this.fotoPerfilUrl = fotoPerfilUrl; }

    public String getFotoCapaUrl() { return fotoCapaUrl; }
    public void setFotoCapaUrl(String fotoCapaUrl) { this.fotoCapaUrl = fotoCapaUrl; }
}