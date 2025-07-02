package com.amouamado.amou_amado.dto;

public class UsuarioDTO {
    public String nomeCompleto;
    public String nomeSocial;
    public String pronome;
    public String identidadeGenero;
    public String ddd;
    public String telefone;
    public String email;
    public String senha;
    public String areaArtistica;
    public String bio;

    // Construtor vazio (obrigatório para Spring / Jackson)
    public UsuarioDTO() {}

    // Construtor completo
    public UsuarioDTO(String nomeCompleto, String nomeSocial, String pronome, String identidadeGenero,
                      String ddd, String telefone, String email, String senha,
                      String areaArtistica, String bio) {
        this.nomeCompleto = nomeCompleto;
        this.nomeSocial = nomeSocial;
        this.pronome = pronome;
        this.identidadeGenero = identidadeGenero;
        this.ddd = ddd;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.areaArtistica = areaArtistica;
        this.bio = bio;
    }
}

