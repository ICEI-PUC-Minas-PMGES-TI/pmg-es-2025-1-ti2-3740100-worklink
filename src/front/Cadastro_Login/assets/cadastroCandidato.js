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
                cep: document.getElementById("cep").value.replace(/\D/g, ''),
                cidade: document.getElementById("cidade").value,
                numero: document.getElementById("numero").value,
                fotoPerfil: "",
                cpf: document.getElementById("cpf").value.replace(/\D/g, ''),
                dataNasc: document.getElementById("dataNasc").value,
                areaAtuacao: document.getElementById("areaAtuacao").value,
                sexo: document.getElementById("sexo").value,
                formacao: document.getElementById("formacao").value,
                experiencia: parseInt(document.getElementById("experiencia").value),
                habilidades: document.getElementById("habilidades").value
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
});