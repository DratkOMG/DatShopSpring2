<style>
    .nav-link, .navbar-brand {
        color: white;
    }

    a:hover {
        color: black;
    }

    form {
        display: block;
        margin-block-end: 0em;
    }
</style>

<nav class="navbar navbar-expand-md navbar-blue text-light">
    <div class="container">
        <a class="navbar-brand" href="/home"> DatBookShop </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">

                <#if account??>
                    <li class="nav-item">
                        <a class="nav-link" href="/sign-in/log-out">
                            Logout
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">
                            Profile
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/purchase-history">
                            Purchase History
                        </a>
                    </li>
                    <#if seller??>
                        <#if seller==true>
                            <li class="nav-item">
                                <a class="nav-link" href="/manage-product">
                                    Manager Product
                                </a>
                            </li>
                        </#if>

                        <#if admin??>
                            <#if admin==true>
                                <li class="nav-item">
                                    <a class="nav-link" href="/manage-employees">
                                        Manage Employees
                                    </a>
                                </li>
                            </#if>
                        </#if>

                    </#if>
                <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="/sign-in">Login</a>
                    </li>
                </#if>

            </ul>

            <div class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input oninput="liveSearch(this)" name="search" type="text" class="from-control" aria-label="Small"
                           aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                </div>
                <form action="/cart" method="post">
                    <button type="submit" class="btn btn-success btn-sm ml-3" onclick="">
                        <i class="fa fa-shopping-cart"></i> Cart
                        <span class="badge badge-light"
                              id="count-product"><#if countProduct??>${countProduct}</#if></span>
                    </button>
                </form>
            </div>

        </div>
    </div>
</nav>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading"> Dat Book Shop </h1>
        <p class="lead text-muted mb-0"> Beautiful books at affordable prices and an unforgettable experience </p>
    </div>
</section>

