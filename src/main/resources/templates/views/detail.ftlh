<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detail</title>
    <link rel="stylesheet" href="/style.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/script.js"></script>
    <style>
        .gallery-wrap .img-big-wrap img {
            height: 450px;
            width: auto;
            display: inline-block;
            cursor: zoom-in;
        }


        .gallery-wrap .img-small-wrap .item-gallery {
            width: 60px;
            height: 60px;
            border: 1px solid #ddd;
            margin: 7px 2px;
            display: inline-block;
            overflow: hidden;
        }

        .gallery-wrap .img-small-wrap {
            text-align: center;
        }

        .gallery-wrap .img-small-wrap img {
            max-width: 100%;
            max-height: 100%;
            object-fit: cover;
            border-radius: 4px;
            cursor: zoom-in;
        }

        .img-big-wrap img {
            width: 100% !important;
            height: auto !important;
        }

        img {
            border-radius: 1rem;
        }
    </style>
</head>
<body>
<#include "*/parts/_menu.ftlh">
<div class="container">
    <div class="row">
        <#include "*/parts/_left.ftlh">
        <div class="col-sm-9">
            <div class="container">
                <div class="card">
                    <div class="row">
                        <aside class="col-sm-5 border-right">
                            <article class="gallery-wrap">
                                <div class="img-big-wrap">
                                    <div><a href="#"><img src="${book.image}"></a></div>
                                </div>
                                <div class="img-small-wrap">
                                    <div class="item-gallery"><img
                                                src="${book.image}">
                                    </div>
                                    <div class="item-gallery"><img
                                                src="${book.image}">
                                    </div>
                                    <div class="item-gallery"><img
                                                src="${book.image}">
                                    </div>
                                    <div class="item-gallery"><img
                                                src="${book.image}">
                                    </div>
                                </div>
                            </article>
                        </aside>
                        <aside class="col-sm-7">
                            <article class="card-body p-5">
                                <h3 class="title mb-3">${book.title}</h3>

                                <p class="price-detail-wrap">
                                            <span class="price h3 text-warning">
                                                <span class="currency">US $</span><span class="num">${book.price}</span>
                                            </span>
                                </p>
                                <dl class="item-property">
                                    <dt>Seller:
                                        <a href="/home/book-by?uid=${sellerIs.userId}">${sellerIs.userName}</a>
                                    </dt>
                                </dl>
                                <dl class="item-property">
                                    <dt>Sold: ${book.quantitySold}</dt>
                                </dl>
                                <dl class="item-property">
                                    <dt>Description</dt>
                                    <dd><p>${book.description} </p></dd>
                                </dl>

                                <hr>

                                <a href="/cart/add-to-cart?bid=${book.bookId}" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>

                                <button onclick="addToCart(${book.bookId})"
                                        class="btn btn-lg btn-outline-primary text-uppercase">
                                    <i class="fa fa-shopping-cart"> </i> Add to cart
                                </button>

                            </article>
                        </aside>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
<#include "*/parts/_footer.ftlh">
</body>
</html>
