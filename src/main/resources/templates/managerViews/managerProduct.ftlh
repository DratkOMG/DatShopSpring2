<html>
<head>
    <title>Manager Product</title>
    <#include "*/parts/_headerManager.ftlh">
</head>
<body>
<div class="container">
    <div class="table-wrapper">

        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Product</b></h2>
                </div>
                <div class="col-sm-6">
                    <#if admin == true>
                        <a href="#addNewCategoryBook" class="btn btn-success" data-toggle="modal">
                            <i class="material-icons">&#xE147;</i>
                            <span>Add New Category Book</span></a>
                    </#if>

                    <a href="/manage-product/add-product" class="btn btn-success" data-toggle="modal">
                        <i class="material-icons">&#xE147;</i>
                        <span>Add New Product</span></a>
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
                <th>Title</th>
                <th>Image</th>
                <th>Price</th>
                <th>Sold</th>
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
                    <td>${book.quantitySold}</td>
                    <td>${book.author} </td>
                    <td>${book.description} </td>
                    <td>
                        <a href="/manage-product/load-product?bid=${book.bookId}" class="edit"
                        data-toggle="modal">
                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                        <a href="/manage-product/delete-product?bid=${book.bookId}" class="delete"
                        data-toggle="modal">
                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                        </a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        <button onclick="loadMoreManager()" class="btn btn-primary" id="btvn"> Load More</button>
        <button onclick="up()" class="btn btn-primary" hidden = "" id="up">Top</button>

    </div>
</div>

<div id="addNewCategoryBook" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="post">

                <div class="modal-header">
                    <h4 class="modal-title">Add Category Book</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label> Category Name</label>
                        <input name="category" type="text" class="form-control" required>
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
