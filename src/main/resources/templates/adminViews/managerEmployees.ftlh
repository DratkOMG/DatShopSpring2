<html>
<head>
    <title>Manager Employees</title>
    <#include "*/parts/_headerManager.ftlh">
</head>
<body>

<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Employees</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="/home" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Dat Book Shop</span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Username</th>
                <th>Age</th>
                <th>Sex</th>
                <th>City</th>
                <th>Phone Number</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody id="content">

            <#list listEmployees  as employee >
                <tr class="product">
                    <td>${employee.userId}</td>
                    <td>${employee.email}</td>
                    <td>${employee.userName}</td>
                    <td>${employee.age}</td>
                    <td>${employee.sex} </td>
                    <td>${employee.city}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>
                        <a href="/manage-employees/load-employee?eid=${employee.userId}" class="edit"
                           data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                        <a href="/manage-employees/delete-employee?eid=${employee.userId}" class="delete"
                           data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                        </a>
                    </td>
                </tr>
            </#list>

            </tbody>
        </table>
    </div>
</div>


</body>
</html>
