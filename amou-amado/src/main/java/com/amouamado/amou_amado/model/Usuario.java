package com.amouamado.amou_amado.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome completo é obrigatório")
    private String nomeCompleto;

    private String nomeSocial;
    private String pronome;
    private String identidadeGenero;

    @NotBlank(message = "DDD é obrigatório")
    @Pattern(regexp = "\\d{2}", message = "DDD deve ter 2 dígitos")
    private String ddd;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{9}", message = "Telefone deve ter 9 dígitos")
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @NotBlank(message = "Área artística é obrigatória")
    private String areaArtistica;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String avatarUrl;
    private String coverUrl;
    private String localizacao;
    private LocalDate membroDesde;
    private Integer eventosRealizados = 0;

    private String website;
    private String instagram;
    private String facebook;
    private String twitter;
    private String linkedin;

    private LocalDateTime criadoEm = LocalDateTime.now();


public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getNomeCompleto() {
    return nomeCompleto;
}

public void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
}

public String getNomeSocial() {
    return nomeSocial;
}

public void setNomeSocial(String nomeSocial) {
    this.nomeSocial = nomeSocial;
}

public String getPronome() {
    return pronome;
}

public void setPronome(String pronome) {
    this.pronome = pronome;
}

public String getIdentidadeGenero() {
    return identidadeGenero;
}

public void setIdentidadeGenero(String identidadeGenero) {
    this.identidadeGenero = identidadeGenero;
}

public String getDdd() {
    return ddd;
}

public void setDdd(String ddd) {
    this.ddd = ddd;
}

public String getTelefone() {
    return telefone;
}

public void setTelefone(String telefone) {
    this.telefone = telefone;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getSenha() {
    return senha;
}

public void setSenha(String senha) {
    this.senha = senha;
}

public String getAreaArtistica() {
    return areaArtistica;
}

public void setAreaArtistica(String areaArtistica) {
    this.areaArtistica = areaArtistica;
}

public String getBio() {
    return bio;
}

public void setBio(String bio) {
    this.bio = bio;
}

public String getAvatarUrl() {
    return avatarUrl;
}

public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
}

public String getCoverUrl() {
    return coverUrl;
}

public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
}

public String getLocalizacao() {
    return localizacao;
}

public void setLocalizacao(String localizacao) {
    this.localizacao = localizacao;
}

public LocalDate getMembroDesde() {
    return membroDesde;
}

public void setMembroDesde(LocalDate membroDesde) {
    this.membroDesde = membroDesde;
}

public Integer getEventosRealizados() {
    return eventosRealizados;
}

public void setEventosRealizados(Integer eventosRealizados) {
    this.eventosRealizados = eventosRealizados;
}

public String getWebsite() {
    return website;
}

public void setWebsite(String website) {
    this.website = website;
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

public LocalDateTime getCriadoEm() {
    return criadoEm;
}

public void setCriadoEm(LocalDateTime criadoEm) {
    this.criadoEm = criadoEm;
}
}