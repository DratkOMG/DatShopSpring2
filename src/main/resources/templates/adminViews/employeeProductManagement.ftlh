<html>
<head>
    <title>Employee product management</title>
    <#include "*/parts/_headerManager.ftlh">

</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><b>${sellerIs.userName}</b> Products</h2>
                </div>
                <div class="col-sm-6">
                    <a href="/home" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Dat Book Shop</span>
                    </a>
                    <a href="/manage-employees" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Manage Employee</span>
                    </a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Image</th>
                <th>Price</th>
                <th>Author</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody id="content">
            <#list listBook as book>
                <tr class="product">
                    <td>${book.bookId}</td>
                    <td>${book.title}</td>
                    <td>
                        <img src="${book.image}">
                    </td>
                    <td>${book.price} $</td>
                    <td>${book.author} </td>
                    <td>${book.description} </td>
                    <td>
                        <a href="/manage-employees/delete-product?bid=${book.bookId}&eid=${sellerIs.userId}" class="delete"
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
