$(document).ready(function () {
    ajaxGet();
})

function ajaxGet() {
    $.ajax({
        type: "GET",
        url: "/admin/getUsers",
        success: function (result) {
            $('#users_table tbody').empty();
            $.each(result, function (i, user) {
                var userRow = '<tr>' +
                    '<td>' + user.id + '</td>' +
                    '<td>' + user.login + '</td>' +
                    '<td>' + user.password + '</td>' +
                    '<td>' + getRoles(user.roles) + '</td>' +
                    '<td> \n' +
                    '<div class="btn-group-vertical btn-group-xs"> \n' +
                    '<a onclick="showModal(' + user.id + ')" \n' +
                    'class="btn btn-default"> \n' +
                    '<i class="glyphicon glyphicon-pencil"></i>Edit</a> \n' +
                    '<a class="btn btn-success"\n' +
                    'onclick="delRest(' + user.id + ')"><i class="glyphicon glyphicon-trash"></i>Delete</a> \n' +
                    '</div></td> \n' +
                    '</tr>';

                $('#users_table tbody').append(userRow);

            });

        },
        error: function (message) {
            console.log(message);
        }
    });
}

function showModal(id) {
    $.ajax({
        type:'get',
        url:'/admin/getUser',
        data: {id:id},
        success: function (user) {
            $('#id-input-edit-hidden').val(user.id);
            $('#login-input-edit').val(user.login);
            $('#pass-input-edit').val(user.password);
            $('#myModal').modal();
        },
        error: function (message) {
            console.log(message);
        }
    })

}


$(document).ready(function () {
    $("#edit-submit").click(function () {
        var idRoles = [];
        idRoles = $('#roled').val();
        var logRoles = [];
        logRoles = $('#roled').text();
        var roles = [];

        if (idRoles && idRoles.length) {
            for (var i = 0; i < idRoles.length; i++) {
                roles[i] = {
                    id: idRoles[i],
                    role: logRoles[i]
                };
            }
        }

        var user = {
            id: $('#id-input-edit-hidden').val(),
            login: $('#login-input-edit').val(),
            password: $('#pass-input-edit').val(),
            roles: roles
        };
        $.ajax({
            type: 'post',
            url: '/admin/editUser',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(user),
            error: function (message) {
                console.log(message);
                $("#edit-form").submit();
            },
            success: function () {
                $("#edit-form").submit();
            }
        });
    })
})

function delRest(id) {
    $.ajax({
        type: 'get',
        url: '/admin/delRest',
        data: {id: id},
        error: function (message) {
            console.log(message);
        },
        success: function () {
            ajaxGet();
            $('#myTab a[href="#user-panel"]').tab('show');
        }
    });

}


//Dima Sensei
function addUser(login, password) {
    var idRoles = [];
    idRoles = $('#rol').val();
    var logRoles = [];
    logRoles = $('#rol').text();
    var roles = [];

    if (idRoles && idRoles.length) {
        for (var i = 0; i < idRoles.length; i++) {
            roles[i] = {
                id: idRoles[i],
                role: logRoles[i]
            };
        }
    }
    var user = {login: login, password: password, roles: roles};
    $.ajax({
        type: 'post',
        url: '/admin/addUser',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(user),
        error: function (message) {
            console.log(message);
            ajaxGet();
            $('#myTab a[href="#user-panel"]').tab('show');
        },
        success: function () {
            ajaxGet();
            $('#myTab a[href="#user-panel"]').tab('show');
        }
    });

}

$(document).ready(function () {
    getAllRoles();
})

function getAllRoles() {
    $.ajax({
        type: 'get',
        url: '/admin/getAllRoles',
        success: function (result) {
            $.each(result, function (i, role) {
                var selectForm = '<option value="' + role.id + '">' + role.role + '</option>';
                $('#rol').append(selectForm);
            });
            $.each(result, function (i, role) {
                var selectForm = '<option value="' + role.id + '">' + role.role + '</option>';
                $('#roled').append(selectForm);

            });
        },
        error: function (message) {
            console.log(message);
        }

    })
}

function getRoles(roles) {
    var roleList = '';
    if (roles.length == 0) {
        roleList = 'anonymus';
    }
    for (var i = 0; i < roles.length; i++) {
        roleList += roles[i].role;
        roleList += ' ';
    }
    return roleList;
}

