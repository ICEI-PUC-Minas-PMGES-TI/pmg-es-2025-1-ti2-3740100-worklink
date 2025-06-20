const URL = "http://localhost:8080";

function limparMascaraCPF(cpf) {
    return cpf.replace(/\D/g, '');
}

document.addEventListener("DOMContentLoaded", function() {
    // Validação do campo Nome
    const nomeInput = document.getElementById('nome');
    if (nomeInput) {
        nomeInput.addEventListener('input', function() {
            this.value = this.value.replace(/[^A-Za-zÀ-ÿ\s]/g, '');
        });
    }

    // Formatação do CPF (XXX.XXX.XXX-XX)
    const cpfInput = document.getElementById('cpf');
    if (cpfInput) {
        cpfInput.addEventListener('input', function() {
            let cpf = this.value.replace(/\D/g, '');
            cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2');
            cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2');
            cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
            this.value = cpf.slice(0, 14);
        });
    }

    // Validação dos campos obrigatórios ao submit
    const form = document.getElementById('formCadastroCandidato');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            let isValid = true;
            document.querySelectorAll('input[required], textarea[required], select[required]').forEach(function(input) {
                if (input.value.trim() === '') {
                    isValid = false;
                    input.classList.add('is-invalid');
                } else {
                    input.classList.remove('is-invalid');
                }
            });

            const errorMsg = document.getElementById('errorMessage');
            if (!isValid) {
                if (errorMsg) errorMsg.style.display = 'block';
                return;
            } else {
                if (errorMsg) errorMsg.style.display = 'none';
            }

            // Monta o objeto com os dados do formulário
            const dataCandidato = {
                nome: document.getElementById("nome").value,
                email: document.getElementById("email").value,
                senha: document.getElementById("senha").value,
                confirmarSenha: document.getElementById("confirmarSenha").value,
                telefone: document.getElementById("telefone").value.replace(/\D/g, ''),
                endereco: document.getElementById("endereco").value,
                cepusuario: document.getElementById("cep").value.replace(/\D/g, ''), // <-- ALTERE AQUI
                cidade: document.getElementById("cidade").value,
                numero: document.getElementById("numero").value,
                fotoPerfil: "",
                cpf: document.getElementById("cpf").value.replace(/\D/g, ''),
                dataNasc: document.getElementById("dataNasc").value,
                areaAtuacao: document.getElementById("areaAtuacao").value,
                sexo: document.getElementById("sexo").value,
                formacao: document.getElementById("formacao").value,
                experiencia: parseInt(document.getElementById("experiencia").value),
                habilidades: document.getElementById("habilidades").value,
                sobre: document.getElementById("sobre").value,
                links: document.getElementById("links").value,
                idiomas: document.getElementById("idiomas").value,
                disponibilidade: document.getElementById("disponibilidade").value
            };

            // Exibe os dados no console
            console.log("Dados inseridos no formulário:", dataCandidato);

            // Aqui você pode continuar com o envio dos dados para o backend
            fetch("http://localhost:8080/candidatos", {
                method: "POST",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(dataCandidato)
            })
            .then(function (res) {
                if (res.status === 409) {
                    alert("E-mail, CPF ou nome já cadastrado!");
                    return;
                }
                if (res.ok) {
                    alert("Candidato cadastrado com sucesso!");
                    window.location.href = "Login.html";
                } else {
                    res.text().then(text => {
                        alert("Erro ao cadastrar candidato: " + text);
                        console.log(text);
                    });
                }
            })
            .catch(function (err) {
                alert("Erro ao cadastrar candidato.");
                console.log(err);
            });
        });
    }

    // Remove a classe is-invalid quando o usuário começa a digitar
    document.querySelectorAll('input[required], textarea[required], select[required]').forEach(function(input) {
        input.addEventListener('input', function() {
            input.classList.remove('is-invalid');
            const errorMsg = document.getElementById('errorMessage');
            if (errorMsg && document.querySelectorAll('input[required].is-invalid').length === 0) {
                errorMsg.style.display = 'none';
            }
        });
    });

    // Formatação do telefone ((XX) XXXXX-XXXX)
    const telefoneInput = document.getElementById('telefone');
    if (telefoneInput) {
        telefoneInput.addEventListener('input', function() {
            let telefone = this.value.replace(/\D/g, '');
            telefone = telefone.replace(/^(\d{2})(\d)/g, '($1) $2');
            telefone = telefone.replace(/(\d)(\d{4})$/, '$1-$2');
            this.value = telefone.slice(0, 15);
        });
    }

    // Formatação do CEP (XXXXX-XXX) e busca automática do endereço
    const cepInput = document.getElementById('cep');
    if (cepInput) {
        cepInput.addEventListener('blur', function() {
            let cep = this.value.replace(/\D/g, '');
            if (cep.length === 8) {
                const cidadeInput = document.getElementById('cidade');
                const enderecoInput = document.getElementById('endereco');
                if (cidadeInput) cidadeInput.value = 'Buscando...';
                if (enderecoInput) enderecoInput.value = 'Buscando...';
                fetch(`https://viacep.com.br/ws/${cep}/json/`)
                    .then(response => response.json())
                    .then(data => {
                        if (!data.erro) {
                            if (cidadeInput) cidadeInput.value = data.localidade;
                            if (enderecoInput) enderecoInput.value = data.logradouro;
                        } else {
                            if (cidadeInput) cidadeInput.value = '';
                            if (enderecoInput) enderecoInput.value = '';
                            alert('CEP não encontrado');
                        }
                    })
                    .catch(error => {
                        console.error('Erro ao buscar CEP:', error);
                        if (cidadeInput) cidadeInput.value = '';
                        if (enderecoInput) enderecoInput.value = '';
                        alert('Erro ao buscar CEP');
                    });
            }
        });

        // Formatação do CEP enquanto digita
        cepInput.addEventListener('input', function() {
            let cep = this.value.replace(/\D/g, '');
            cep = cep.replace(/^(\d{5})(\d)/, '$1-$2');
            this.value = cep.slice(0, 9);
        });
    }

    // Limitar data de nascimento para no máximo 16 anos atrás
    const dataNascInput = document.getElementById('dataNasc');
    if (dataNascInput) {
        const hoje = new Date();
        const anoLimite = hoje.getFullYear() - 16;
        // Formata para yyyy-mm-dd
        const mes = String(hoje.getMonth() + 1).padStart(2, '0');
        const dia = String(hoje.getDate()).padStart(2, '0');
        dataNascInput.max = `${anoLimite}-${mes}-${dia}`;
    }

    // Função para mostrar/ocultar senha
    window.togglePassword = function(inputId) {
        const input = document.getElementById(inputId);
        const icon = input.nextElementSibling;
        if (input.type === 'password') {
            input.type = 'text';
            icon.classList.remove('fa-eye');
            icon.classList.add('fa-eye-slash');
        } else {
            input.type = 'password';
            icon.classList.remove('fa-eye-slash');
            icon.classList.add('fa-eye');
        }
    };

    // Idiomas
    let idiomasArray = [];

    document.getElementById('add-idioma-btn').onclick = function() {
        const idioma = document.getElementById('novo-idioma').value.trim();
        const nivel = document.getElementById('novo-nivel').value;
        if (idioma && nivel) {
            idiomasArray.push({ nome: idioma, nivel: nivel });
            atualizarIdiomasList();
            document.getElementById('novo-idioma').value = '';
            document.getElementById('novo-nivel').value = '';
        }
    };

    function atualizarIdiomasList() {
        const list = document.getElementById('idiomas-list');
        list.innerHTML = '';
        idiomasArray.forEach((item, idx) => {
            const div = document.createElement('span');
            div.className = 'idioma-badge mb-2';
            div.innerHTML = `
                <span><strong>${item.nome}</strong> - ${item.nivel}</span>
                <button type="button" class="remove-idioma-btn" title="Remover" onclick="removerIdioma(${idx})">
                    <i class="fas fa-times"></i>
                </button>
            `;
            list.appendChild(div);
        });
        document.getElementById('idiomas').value = JSON.stringify(idiomasArray);
    }

    window.removerIdioma = function(idx) {
        idiomasArray.splice(idx, 1);
        atualizarIdiomasList();
    };

    // Experiências
    let experienciasArray = [];

    document.getElementById('add-exp-btn').onclick = function() {
        const empresa = document.getElementById('exp-empresa').value.trim();
        const cargo = document.getElementById('exp-cargo').value.trim();
        const anoInicio = document.getElementById('exp-ano-inicio').value.trim();
        const anoFim = document.getElementById('exp-ano-fim').value.trim();
        const atualmente = document.getElementById('exp-atual').checked;
        const desc = document.getElementById('exp-desc').value.trim();

        let periodo = "";
        if (anoInicio && (anoFim || atualmente)) {
            periodo = atualmente ? `${anoInicio} - Atualmente` : `${anoInicio} - ${anoFim}`;
        } else {
            periodo = "";
        }

        if (empresa && cargo && periodo && desc) {
            experienciasArray.push({ empresa, cargo, periodo, desc });
            atualizarExperienciasList();
            document.getElementById('exp-empresa').value = '';
            document.getElementById('exp-cargo').value = '';
            document.getElementById('exp-ano-inicio').value = '';
            document.getElementById('exp-ano-fim').value = '';
            document.getElementById('exp-atual').checked = false;
            document.getElementById('exp-desc').value = '';
        }
    };

    function atualizarExperienciasList() {
        const list = document.getElementById('experiencias-list');
        list.innerHTML = '';
        experienciasArray.forEach((item, idx) => {
            const div = document.createElement('div');
            div.className = 'exp-card';
            div.innerHTML = `
                <button type="button" class="exp-remove-btn" onclick="removerExperiencia(${idx})" title="Remover">
                    <i class="fas fa-times"></i>
                </button>
                <div class="exp-title">${item.empresa} — ${item.cargo}</div>
                <div class="exp-period">${item.periodo}</div>
                <div class="exp-desc">${item.desc}</div>
            `;
            list.appendChild(div);
        });
        document.getElementById('experiencias').value = JSON.stringify(experienciasArray);
    }

    window.removerExperiencia = function(idx) {
        experienciasArray.splice(idx, 1);
        atualizarExperienciasList();
    };

    // Desabilita ano fim se "Atualmente" estiver marcado
    document.getElementById('exp-atual').addEventListener('change', function () {
        document.getElementById('exp-ano-fim').disabled = this.checked;
        if (this.checked) {
            document.getElementById('exp-ano-fim').value = '';
        }
    });
});