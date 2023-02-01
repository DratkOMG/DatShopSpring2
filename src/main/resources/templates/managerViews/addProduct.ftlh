<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Book</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="/manager.css" rel="stylesheet" type="text/css"/>
    <style>
        textarea.form-control {
            resize: vertical;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><b>Add Book</b></h2>
                </div>
            </div>
        </div>

        <div class="modal-dialog">
            <div class="modal-content">
                <form action="/manage-product/add-product" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Title </label>
                            <input name="title" value="<#if title??>${title}</#if>" type="text" class="form-control"
                                   required>
                            <p class="ui-state-error-text"><#if errorTitle??>${errorTitle}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Image </label>
                            <input name="image" value="<#if image??>${image}</#if>" type="text" class="form-control"
                                   required>
                            <p class="ui-state-error-text"><#if errorImage??>${errorImage}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Price </label>
                            <input name="price" value="<#if price??>${price}</#if>" type="number" class="form-control"
                                   required>
                            <p class="ui-state-error-text"><#if errorPrice??>${errorPrice}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Author </label>
                            <input name="author" value="<#if author??>${author}</#if>" class="form-control" required>
                            <p class="ui-state-error-text"><#if errorAuthor??>${errorAuthor}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Description </label>
                            <textarea name="description" class="form-control"
                                      required><#if description??>${description}</#if></textarea>
                            <p class="ui-state-error-text"><#if errorDescription??>${errorDescription}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Category</label>
                            <select name="categoriesId" class="form-select" aria-label="Default select example">
                                <#list listCategory as category>
                                    <option value="${category.categoriesId}">${category.categoriesName}</option>
                                </#list>
                            </select>
                        </div>
                        <p class="ui-state-active-text"><#if Done??>${Done}</#if></p>

                    </div>
                    <div class="modal-footer">
                        <a href="/manage-product">
                            <input type="button" class="btn btn-default" value="Cancel">
                        </a>
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>


    </div>
</div>

</body>
</html>
