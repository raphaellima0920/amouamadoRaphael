
    document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (!id) {
        alert("ID do evento não fornecido.");
        return;
    }

    fetch(`http://localhost:8080/api/eventos/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar evento.");
            }
            return response.json();
        })
        .then(evento => {
            console.log("Evento recebido:", evento); // Para debug

            // Título e categoria
            document.querySelector(".event-title").textContent = evento.titulo;
            document.querySelector(".event-category").textContent = evento.categoria;

            // Imagem
            document.querySelector(".event-image img").src = evento.imagem;

            // Meta informações
            const metaItems = document.querySelectorAll(".meta-item");
            metaItems[0].querySelector("p").textContent = evento.local;
            metaItems[1].querySelector("p").textContent = evento.data
                ? `${evento.data} às ${evento.horario}`
                : `Horário: ${evento.horario}`;
            metaItems[2].querySelector("p").textContent = evento.duracao;
            metaItems[3].querySelector("p").textContent = evento.classificacaoEtaria;

            // Preço
            document.querySelector(".event-price").textContent =
                evento.preco === 0 ? "Gratuito" : `R$ ${evento.preco.toFixed(2)}`;

            // Descrição
            const descricaoContainer = document.querySelector(".event-description");
            descricaoContainer.innerHTML = `
                <h2 class="section-title">Descrição do Evento</h2>
                <p>${evento.descricao}</p>
            `;

            // Destaques
            const destaquesContainer = document.querySelector(".event-highlights");
            destaquesContainer.innerHTML = `<h3 class="section-title">Destaques</h3>`;
            if (evento.destaques) {
                evento.destaques.split(/\n|,/).forEach(destaque => {
                    if (destaque.trim()) {
                        const item = document.createElement("div");
                        item.className = "highlight-item";
                        item.innerHTML = `
                            <div class="highlight-icon"><i class="fas fa-star"></i></div>
                            <div class="highlight-content"><p>${destaque.trim()}</p></div>
                        `;
                        destaquesContainer.appendChild(item);
                    }
                });
            }

            // Artistas
            const artistasList = document.querySelector(".artist-list");
            artistasList.innerHTML = "";
            if (evento.artistas) {
                evento.artistas.split(/\n|,/).forEach(artista => {
                    if (artista.trim()) {
                        const li = document.createElement("li");
                        li.textContent = artista.trim();
                        artistasList.appendChild(li);
                    }
                });
            }

            // Políticas
            const policyList = document.querySelector(".policy-list");
            policyList.innerHTML = "";
            if (evento.politicas) {
                evento.politicas.split(/\n|,/).forEach(politica => {
                    if (politica.trim()) {
                        const li = document.createElement("li");
                        li.textContent = politica.trim();
                        policyList.appendChild(li);
                    }
                });
            }

            // Contato
            const contactList = document.querySelector(".contact-list");
            contactList.innerHTML = `
                <li>Telefone: ${evento.telefoneContato || "(não informado)"}</li>
                <li>Email: ${evento.emailContato || "(não informado)"}</li>
                <li>Site: ${evento.websiteContato || "(não informado)"}</li>
            `;

            // Criador (simples - opcionalmente pode puxar dados reais)
            document.querySelector(".creator-name").textContent = evento.organizador?.nome || "Organizador não informado";
            document.getElementById("creator-profile").href = `perfil-criador.html?id=${evento.organizador?.id || ""}`;

            // Mapa/localização
            document.querySelector("#map-placeholder p").textContent = evento.local;
        })
        .catch(error => {
            console.error(error);
            alert("Erro ao carregar detalhes do evento.");
        });

    // Botões do cabeçalho
    document.querySelector('.btn-create').addEventListener('click', () => {
        window.location.href = "criarevento.html";
    });

    document.querySelector('.btn-events').addEventListener('click', () => {
        window.location.href = "meuseventos.html";
    });

    document.querySelector('.btn-profile').addEventListener('click', () => {
        window.location.href = "perfil.html";
    });

    document.getElementById('buy-tickets').addEventListener('click', () => {
        alert('Página de compra de ingressos ainda em desenvolvimento!');
    });
});
