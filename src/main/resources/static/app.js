function showAllUsers() {
    let tBody = document.getElementById("tBody");
    tBody.innerHTML = "";
    fetch('http://localhost:8080/rest')
        .then(response => response.json())
        .then(users => {
            users.forEach(function (user) {
                let row = tBody.insertRow();
                row.setAttribute("id", user.id);
                let cell0 = row.insertCell();
                cell0.innerHTML = user.id;
                let cell1 = row.insertCell();
                cell1.innerHTML = user.firstName;
                let cell2 = row.insertCell();
                cell2.innerHTML = user.lastName;
                let cell3 = row.insertCell();
                cell3.innerHTML = user.age;
                let cell4 = row.insertCell();
                cell4.innerHTML = user.username;
                let cell5 = row.insertCell();
                cell5.innerHTML = listRoles(user);

                let cell6 = row.insertCell();
                cell6.innerHTML =
                    '<button type="button" onclick="getModalEdit(' + user.id + ')" class="btn btn-info btn-sm" data-bs-toggle="modal">Изменить</button>';

                let cell7 = row.insertCell();
                cell7.innerHTML =
                    '<button type="button" onclick="getModalDelete(' + user.id + ')" class="btn btn-danger btn-sm" data-bs-toggle="modalDelete">Удалить</button>';
            })
        });
}