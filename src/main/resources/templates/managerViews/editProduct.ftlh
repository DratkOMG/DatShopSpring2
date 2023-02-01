<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Book</title>
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
                    <h2><b>Edit Book</b></h2>
                </div>
            </div>
        </div>

        <div class="modal-dialog">
            <div class="modal-content">
                <form action="/manage-product/edit-product?bid=${book.bookId}" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Title</label>
                            <input name="title" value="${book.title}" type="text" class="form-control" required>
                            <p class="ui-state-error-text"><#if errorTitle??>${errorTitle}</#if></p>
                            <p class="ui-state-active-text"><#if niceTitle??>${niceTitle}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Image</label>
                            <input name="image" value="${book.image}" type="text" class="form-control" required>
                            <p class="ui-state-error-text"><#if errorImage??>${errorImage}</#if></p>
                            <p class="ui-state-active-text"><#if niceImage??>${niceImage}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input name="price" value="${book.price}" type="text" class="form-control" required>
                            <p class="ui-state-error-text"><#if errorPrice??>${errorPrice}</#if></p>
                            <p class="ui-state-active-text"><#if nicePrice??>${nicePrice}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Author</label>
                            <input name="author" value="${book.author}" type="text" class="form-control" required>
                            <p class="ui-state-error-text"><#if errorAuthor??>${errorAuthor}</#if></p>
                            <p class="ui-state-active-text"><#if niceAuthor??>${niceAuthor}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea name="description" class="form-control" required>${book.description}</textarea>
                            <p class="ui-state-error-text"><#if errorDescription??>${errorDescription}</#if></p>
                            <p class="ui-state-active-text"><#if niceDescription??>${niceDescription}</#if></p>
                        </div>
                        <div class="form-group">
                            <label>Category</label>
                            <select name="categoriesId" class="form-select" aria-label="Default select example">
                                <#list listCategory as category>
                                    <option value="${category.categoriesId}"
                                            <#if book.categoriesId == category>selected</#if>>
                                        ${category.categoriesName}
                                    </option>
                                </#list>
                            </select>
                            <p class="ui-state-error-text"><#if errorCategory??>${errorCategory}</#if></p>
                            <p class="ui-state-active-text"><#if niceCategory??>${niceCategory}</#if></p>
                        </div>

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
