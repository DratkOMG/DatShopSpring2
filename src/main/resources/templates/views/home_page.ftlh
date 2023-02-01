<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="/style.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/script.js"></script>
    <style>
        img {
            height: 350px;
            border-radius: 0.8rem;
        }

        .product {
            margin-top: 1.5rem;
        }

        button#up, #btvn {
            margin-top: 1.5rem;
            float: right;
        }
    </style>
</head>
<body>

<#include "*/parts/_menu.ftlh">
<div class="container">
    <div class="row">
        <#include "*/parts/_left.ftlh">

        <div class="col-sm-9">
            <div id="content" class="row">
                <#list listBook as book>
                    <div class="product col-12 col-md-6 col-lg-4">
                        <div class="card">
                            <img class="" src="${book.image}" alt="Book image">
                            <div class="card-body">
                                <h4 class="card-title show_txt">
                                    <a href="/home/show-book?bid=${book.bookId}" title="${book.title}">${book.title}</a>
                                </h4>
                                <p class="card-text show_txt">${book.description}</p>
                                <div class="row">
                                    <div class="col">
                                        <a href="" class="btn btn-danger btn-block">${book.price} $</a>
                                    </div>
                                    <div class="col">
                                        <button onclick="addToCart(${book.bookId})" class="btn btn-success btn-block">
                                            Add to cart
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>

            </div>

            <button onclick="loadMore()" class="btn btn-primary" id="btvn">Load More</button>

            <button onclick="up()" class="btn btn-primary" id="up">Top</button>


        </div>

    </div>
</div>

<#include "*/parts/_footer.ftlh">
</body>
</html>
