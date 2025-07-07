
// const token = localStorage.getItem('token');
// if (!token) {
//     alert("Você precisa estar logado para acessar esta página.");
//     window.location.href = "login.html";
// }



    // Navegação entre etapas
    document.querySelectorAll('.step').forEach(step => {
        step.addEventListener('click', function() {
            const targetId = this.getAttribute('data-target');

            document.querySelectorAll('.form-section').forEach(section => {
                section.classList.remove('active-section');
            });

            document.getElementById(targetId).classList.add('active-section');

            document.querySelectorAll('.step').forEach(s => {
                s.classList.remove('active');
            });
            this.classList.add('active');
        });
    });

    // Adicionar novo ingresso
    document.getElementById('add-ticket').addEventListener('click', function () {
        const container = document.getElementById('ticket-container');
        const newItem = document.createElement('div');
        newItem.className = 'price-item';
        newItem.innerHTML = `
            <button class="remove-btn" type="button">
                <i class="fas fa-times-circle"></i>
            </button>
            <div class="form-group">
                <label>Nome do Ingresso</label>
                <input type="text" class="form-control" placeholder="Ex: Pista Comum">
            </div>
            <div class="form-group">
                <label>Preço (R$)</label>
                <input type="number" class="form-control" placeholder="0.00" min="0" step="0.01">
            </div>
            <div class="form-group">
                <label>Quantidade</label>
                <input type="number" class="form-control" placeholder="Ilimitado" min="0">
            </div>
        `;
        container.appendChild(newItem);

        newItem.querySelector('.remove-btn').addEventListener('click', function () {
            container.removeChild(newItem);
        });
    });

    // Remover ingresso
    document.querySelectorAll('.remove-btn').forEach(button => {
        button.addEventListener('click', function () {
            this.closest('.price-item').remove();
        });
    });

    // Validação dos campos obrigatórios
    function validateRequiredFields() {
        const requiredFields = [
            'event-title', 'event-category', 'event-date', 'event-time',
            'event-location-name', 'event-address', 'event-neighborhood',
            'event-city', 'event-state', 'event-description'
        ];

        for (const fieldId of requiredFields) {
            const field = document.getElementById(fieldId);
            if (!field.value.trim()) {
                alert(`O campo "${field.previousElementSibling.textContent.replace('*', '').trim()}" é obrigatório!`);
                field.focus();
                return false;
            }
        }
        return true;
    }

    // Submissão do formulário
// Submissão do formulário sem validações obrigatórias
document.getElementById('event-creation-form').addEventListener('submit', async function (e) {
    e.preventDefault();

    const firstTicket = document.querySelector('.price-item');
    const precoInput = firstTicket ? firstTicket.querySelector('input[type="number"]') : null;
    const preco = precoInput ? parseFloat(precoInput.value) : 0.0;

    const evento = {
        titulo: document.getElementById('event-title').value,
        categoria: document.getElementById('event-category').value,
        local: document.getElementById('event-location-name').value,
        imagem: "placeholder.jpg",
        duracao: document.getElementById('event-duration').value,
        classificacaoEtaria: document.getElementById('event-rating').value,
        data: document.getElementById('event-date').value,
        horario: document.getElementById('event-time').value,
        preco: preco,
        descricao: document.getElementById('event-description').value,
        politicas: document.getElementById('event-policies').value,
        destaques: document.getElementById('event-highlights').value,
        artistas: document.getElementById('event-artists').value,
        bairro: document.getElementById('event-neighborhood').value,
        cidade: document.getElementById('event-city').value,
        estado: document.getElementById('event-state').value,
        cep: document.getElementById('event-zipcode').value,
        telefoneContato: document.getElementById('event-contact-phone').value,
        emailContato: document.getElementById('event-contact-email').value,
        websiteContato: document.getElementById('event-contact-website').value
    };

    try {
        const res = await fetch('http://localhost:8080/api/eventos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(evento)
        });

        if (res.ok) {
            alert('Evento criado com sucesso!');
            window.location.href = 'gerenciareventos.html';
        } else {
            const errorText = await res.text();
            alert('Erro ao criar evento: ' + errorText);
        }
    } catch (err) {
        console.error('Erro:', err);
        alert('Erro ao criar evento: ' + err.message);
    }
});

// Botão de salvar rascunho (sem validação)
document.querySelector('.btn-save').addEventListener('click', function () {
    alert('Rascunho salvo (validação desativada).');
});

  