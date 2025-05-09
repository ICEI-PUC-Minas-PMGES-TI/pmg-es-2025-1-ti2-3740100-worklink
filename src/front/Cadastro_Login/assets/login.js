
// Cadastra um usuário
$(document).on('click', "#cadUser", function(){
    const dataUsuario = {
        "email": document.getElementById("caduser_email").value,
        "senha": document.getElementById("caduser_conSenha").value,
        "tipo": 0
    }

    $.ajax({
        url: URL + `/usuarios`,
        type: "POST",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataUsuario),
        success: function (data) {
            alert("Usuário cadastrado com sucesso!")
            window.location.href = "/View/Login/Login.html"
        }, error: function (err) {
            alert("Erro ao cadastrar usuário")
            console.log(err)
        }
    });
})

// Login do usuário
$(document).on('click', "#loginUsuario", function(){
    const email = $('#caduser_email').val()
    const senha = $('#caduser_senha').val()

    $.ajax({
        url: URL + `/usuarios/email/${email}`,
        type: "GET",
        success: function (data) {
            if(data.senha === senha){
                localStorage.setItem("id", data.id)
                localStorage.setItem("email", data.email)
                localStorage.setItem("tipo", data.tipo)
                window.location.href = "/View/Home/index.html"
            } else {
                alert("Senha incorreta")
            }
        }, error: function () {
            alert("Usuário não encontrado")
        }
    });
})

// Buscar todos os usuários
function getDataLista(){
    fetch(URL + "/usuarios", {
        method: "GET",
        headers: {'Content-Type': 'application/json'}
    }).then(res => res.json()
    ).then(data => {
        console.log(data)
        var tableContent = ""
        document.getElementById("tableUsuario").innerHTML = "";
        for(let i = 0; i < data.length; i++){
            tableContent +=  `<tr><td class="table-dark">${data[i].id}</td>
            <td class="table-dark">${data[i].email}</td>
            <td class="table-dark">${data[i].tipo}</td></tr>`   
        }
        $("#tableUsuario").append(tableContent)
    })
}

