<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="/login-style.css">
</head>

<body>
<div id="logreg-forms">
    <form action="/sign-in" method="post" class="form-signin">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign in</h1>
        <input type="email" name="email" value="" id="inputEmail" class="form-control"
               placeholder="Email address" required="" autofocus="">
        <input type="password" name="password" value="" id="inputPassword" class="form-control"
               placeholder="Password" required="">

        <div class="form-group form-check">
            <input name="remember" type="checkbox" <#--${cookie.remember == null?"":"checked"}-->
                   class="form-check-input"
                   id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Remember me</label>
        </div>

        <#if messError??>
            <p class="ui-state-error-text">${messError}</p>

        </#if>

        <p class="ui-state-error-text"></p>
            <button class="btn btn-success btn-block" type="submit">
                <i class="fas fa-sign-in-alt">
                </i> Sign in
            </button>

        <hr>

        <a href="/sign-up" class="btn btn-block">
            <button class="btn btn-primary btn-block" type="button" id="btn-signup">
                <i class="fas fa-user-plus">
                </i> Sign up New Account
            </button>
        </a>

        <hr>

        <a href="/home" methods="get" class="btn btn-block">
            <button class="btn btn-primary btn-block" type="button">
                <i class="fas fa-star">
                </i> Dat Book Shop
            </button>
        </a>

    </form>
</div>

</body>
</html>
