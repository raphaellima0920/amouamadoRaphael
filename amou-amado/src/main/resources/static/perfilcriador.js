
    // Utilitário: obter parâmetros da URL
    function getUrlParams() {
        const params = {};
        const queryString = window.location.search.substring(1);
        const pairs = queryString.split('&');

        pairs.forEach(pair => {
            const [key, value] = pair.split('=');
            if (key) {
                params[key] = decodeURIComponent(value || '');
            }
        });

        return params;
    }

    // Formatar data ISO -> DD/MM/AAAA
    function formatarData(dataIso) {
        const data = new Date(dataIso);
        return data.toLocaleDateString('pt-BR');
    }

    // Carregar perfil do organizador
    async function loadOrganizerProfile() {
        const params = getUrlParams();
        const organizerId = params.id;

        try {
            const res = await fetch(`http://localhost:8080/api/organizadores/${organizerId}`);
            const organizador = await res.json();

            // Nome e bio
            document.querySelector('.organizer-name').textContent = organizador.nome;
            document.querySelector('.organizer-bio p').textContent = organizador.bio;

            // Metadados
            document.querySelectorAll('.meta-content p')[0].textContent = organizador.localizacao;
            document.querySelectorAll('.meta-content p')[1].textContent = organizador.membroDesde;
            document.querySelectorAll('.meta-content p')[2].textContent = `${organizador.quantidadeEventos} eventos`;

            // Contato
            const contactItems = document.querySelectorAll('.contact-list li');
            contactItems[0].innerHTML = `<i class="fas fa-envelope"></i> ${organizador.contato.email}`;
            contactItems[1].innerHTML = `<i class="fas fa-phone"></i> ${organizador.contato.telefone}`;
            contactItems[2].innerHTML = `<i class="fas fa-globe"></i> ${organizador.contato.site}`;
            contactItems[3].innerHTML = `<i class="fab fa-instagram"></i> ${organizador.contato.instagram}`;

            // Foto de perfil e capa
            document.querySelector('.organizer-avatar img').src = organizador.fotoPerfilUrl;
            const slides = document.querySelectorAll('.carousel-slide img');
            slides[0].src = organizador.fotoCapaUrl;

        } catch (error) {
            console.error('Erro ao carregar perfil:', error);
        }
    }

    // Carregar galeria do organizador
    async function initGallery() {
        const galleryContainer = document.getElementById('gallery-container');
        const overlay = document.getElementById('gallery-overlay');
        const overlayImage = document.getElementById('overlay-image');
        const closeBtn = document.getElementById('close-btn');
        const prevBtn = document.getElementById('prev-btn');
        const nextBtn = document.getElementById('next-btn');

        const params = getUrlParams();
        const organizerId = params.id;

        try {
            const res = await fetch(`http://localhost:8080/api/organizadores/${organizerId}/galeria`);
            const images = await res.json();
            let currentIndex = 0;

            images.forEach((imgSrc, index) => {
                const thumbnail = document.createElement('div');
                thumbnail.className = 'gallery-thumbnail';
                thumbnail.innerHTML = `<img src="${imgSrc}" alt="Foto da galeria ${index + 1}">`;

                thumbnail.addEventListener('click', function () {
                    currentIndex = index;
                    overlayImage.src = imgSrc;
                    overlay.classList.add('active');
                    document.body.style.overflow = 'hidden';
                });

                galleryContainer.appendChild(thumbnail);
            });

            closeBtn.addEventListener('click', () => {
                overlay.classList.remove('active');
                document.body.style.overflow = 'auto';
            });

            prevBtn.addEventListener('click', () => {
                currentIndex = (currentIndex - 1 + images.length) % images.length;
                overlayImage.src = images[currentIndex];
            });

            nextBtn.addEventListener('click', () => {
                currentIndex = (currentIndex + 1) % images.length;
                overlayImage.src = images[currentIndex];
            });

            overlay.addEventListener('click', (e) => {
                if (e.target === overlay) {
                    overlay.classList.remove('active');
                    document.body.style.overflow = 'auto';
                }
            });

            document.addEventListener('keydown', (e) => {
                if (overlay.classList.contains('active')) {
                    if (e.key === 'ArrowLeft') {
                        currentIndex = (currentIndex - 1 + images.length) % images.length;
                        overlayImage.src = images[currentIndex];
                    } else if (e.key === 'ArrowRight') {
                        currentIndex = (currentIndex + 1) % images.length;
                        overlayImage.src = images[currentIndex];
                    } else if (e.key === 'Escape') {
                        overlay.classList.remove('active');
                        document.body.style.overflow = 'auto';
                    }
                }
            });

        } catch (error) {
            console.error('Erro ao carregar galeria:', error);
        }
    }

    // Carregar eventos organizados
    async function loadEventosDoOrganizador() {
        const params = getUrlParams();
        const organizerId = params.id;
        const eventsGrid = document.getElementById('events-grid');

        try {
            const res = await fetch(`http://localhost:8080/api/eventos/organizador/${organizerId}`);
            const eventos = await res.json();

            eventsGrid.innerHTML = '';

            eventos.forEach(event => {
                const card = document.createElement('div');
                card.className = 'event-card';
                card.innerHTML = `
                    <a href="detalhes-evento.html?id=${event.id}">
                        <div class="event-card-image">
                            <img src="${event.imagemUrl}" alt="${event.titulo}">
                        </div>
                        <div class="event-card-content">
                            <h3 class="event-card-title">${event.titulo}</h3>
                            <div class="event-card-meta">
                                <div class="event-card-meta-item">
                                    <i class="far fa-calendar-alt"></i>
                                    <span>${formatarData(event.data)}</span>
                                </div>
                                <div class="event-card-meta-item">
                                    <i class="fas fa-map-marker-alt"></i>
                                    <span>${event.local}</span>
                                </div>
                            </div>
                            <div class="event-card-price">R$ ${event.preco}</div>
                        </div>
                    </a>
                `;
                eventsGrid.appendChild(card);
            });

        } catch (error) {
            console.error('Erro ao carregar eventos:', error);
        }
    }

    // Carrossel
    function initCarousel() {
        const slides = document.querySelectorAll('.carousel-slide');
        const prevBtn = document.querySelector('.prev-btn');
        const nextBtn = document.querySelector('.next-btn');
        let currentIndex = 0;

        function showSlide(index) {
            slides.forEach(slide => slide.classList.remove('active'));
            slides[index].classList.add('active');
        }

        prevBtn.addEventListener('click', () => {
            currentIndex = (currentIndex - 1 + slides.length) % slides.length;
            showSlide(currentIndex);
        });

        nextBtn.addEventListener('click', () => {
            currentIndex = (currentIndex + 1) % slides.length;
            showSlide(currentIndex);
        });

        let autoplay = setInterval(() => {
            currentIndex = (currentIndex + 1) % slides.length;
            showSlide(currentIndex);
        }, 5000);

        const carousel = document.querySelector('.carousel-container');
        carousel.addEventListener('mouseenter', () => clearInterval(autoplay));
        carousel.addEventListener('mouseleave', () => {
            autoplay = setInterval(() => {
                currentIndex = (currentIndex + 1) % slides.length;
                showSlide(currentIndex);
            }, 5000);
        });
    }

    // Navegação horizontal dos eventos
    function setupEventsNavigation() {
        const leftArrow = document.querySelector('.events-arrow-left');
        const rightArrow = document.querySelector('.events-arrow-right');
        const container = document.querySelector('.events-container');

        leftArrow.addEventListener('click', () => {
            container.scrollBy({ left: -300, behavior: 'smooth' });
        });

        rightArrow.addEventListener('click', () => {
            container.scrollBy({ left: 300, behavior: 'smooth' });
        });
    }

    // Inicialização
    document.addEventListener('DOMContentLoaded', () => {
        loadOrganizerProfile();
        loadEventosDoOrganizador();
        initGallery();
        initCarousel();
        setupEventsNavigation();
    });
