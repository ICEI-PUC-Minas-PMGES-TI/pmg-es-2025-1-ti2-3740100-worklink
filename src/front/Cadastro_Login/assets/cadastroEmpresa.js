const URL = "http://localhost:8080";


document.addEventListener("DOMContentLoaded", function() {
    
    const botao = document.getElementById("cadEmpresa");
    if (!botao) {
        alert("Botão cadEmpresa não encontrado!");
        return;
    }
    botao.addEventListener("click", function(event) {
        event.preventDefault();
        cadastrarEmpresa();
    });

    // Função para remover a máscara do CNPJ
    function limparMascaraCNPJ(cnpj) {
        return cnpj.replace(/\D/g, '');
    }

    // Formatação dos campos
    function formatarCNPJ(campo) {
        let cnpj = campo.value.replace(/\D/g, '');
        cnpj = cnpj.replace(/^(\d{2})(\d)/, '$1.$2');
        cnpj = cnpj.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3');
        cnpj = cnpj.replace(/\.(\d{3})(\d)/, '.$1/$2');
        cnpj = cnpj.replace(/(\d{4})(\d)/, '$1-$2');
        campo.value = cnpj.slice(0, 18);
    }
    window.formatarCNPJ = formatarCNPJ;

    function formatarTelefone(campo) {
        let telefone = campo.value.replace(/\D/g, '');
        telefone = telefone.replace(/^(\d{2})(\d)/, '($1) $2');
        telefone = telefone.replace(/(\d{5})(\d)/, '$1-$2');
        campo.value = telefone.slice(0, 15);
    }
    window.formatarTelefone = formatarTelefone;

    // Validação dos campos obrigatórios
    function validarCampos() {
        let isValid = true;
        document.querySelectorAll('input[required]').forEach(function(input) {
            if (input.value.trim() === '') {
                isValid = false;
                input.classList.add('is-invalid');
            } else {
                input.classList.remove('is-invalid');
            }
        });
        const errorMsg = document.getElementById('errorMessage');
        if (!isValid) {
            errorMsg.style.display = 'block';
        } else {
            errorMsg.style.display = 'none';
        }
        return isValid;
    }

    // Remove a classe is-invalid ao digitar
    document.querySelectorAll('input[required]').forEach(function(input) {
        input.addEventListener('input', function() {
            input.classList.remove('is-invalid');
            if (document.querySelectorAll('input[required].is-invalid').length === 0) {
                document.getElementById('errorMessage').style.display = 'none';
            }
        });
    });

    // Validação do campo nome (apenas letras)
    document.getElementById('caduser_nome').addEventListener('input', function() {
        this.value = this.value.replace(/[^A-Za-zÀ-ÿ\s]/g, '');
    });

    // Validação do campo email
    document.getElementById('caduser_email').addEventListener('input', function() {
        if (!this.validity.valid) {
            this.setCustomValidity('Por favor, insira um email válido.');
        } else {
            this.setCustomValidity('');
        }
    });

    // Função para cadastrar a empresa
    async function cadastrarEmpresa() {
        if (!validarCampos()) {
            alert("Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        const cnpjLimpo = limparMascaraCNPJ(document.getElementById("caduser_cnpj").value);

        const dataEmpresa = {
            nome: document.getElementById("caduser_nome").value,
            email: document.getElementById("caduser_email").value,
            senha: document.getElementById("caduser_conSenha").value,
            telefone: document.getElementById("caduser_tel").value,
            endereco: document.getElementById("caduser_endereco").value,
            cnpj: cnpjLimpo,
            descricao: document.getElementById("descricao").value
        };

        fetch(URL + "/empresas", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dataEmpresa)
        })
        .then(function (res) {
            if (res.status === 409) {
                alert("E-mail, CNPJ, nome ou telefone já cadastrado!");
                return;
            }
            if (res.ok) {
                alert("Empresa cadastrada com sucesso!");
                window.location.href = "login.html";
            } else {
                alert("Erro ao cadastrar empresa.");
                console.log(res);
            }
        })
        .catch(function (err) {
            alert("Erro ao cadastrar empresa.");
            console.log(err);
        });
    }
});
