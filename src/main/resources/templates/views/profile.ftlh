<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="/style.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="/script.js"></script>

</head>
<body>
<#include "*/parts/_menu.ftlh">
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" width="150px"
                     src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                <span class="font-weight-bold">${userProfile.userName}</span>
                <span class="text-black-50">${userProfile.email}</span>
                <br>

                <form action="/profile/donate" method="post">
                    <span class="font-weight-bold">Balance: ${userProfile.balance} $</span><br>
                    <input type="number" name="donate" class="form-control" placeholder="Donate" required><br>
                    <#if errorDonate??>
                        <p class="ui-state-error-text">${errorDonate}</p>
                    </#if>

                    <#if niceDonate??>
                        <p class="ui-state-active-text">${niceDonate}</p>
                    </#if>

                    <button type="submit" class="btn btn-success">
                        <i class="material-icons">&#xE147;</i>
                        <span>Donate</span></button>
                </form>


            </div>
        </div>

        <form action="/profile/save-profile" method="post" class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Settings</h4>
                </div>
                <div class="row mt-3">

                    <div class="col-md-12">
                        <label class="labels">Username</label>
                        <input type="text" name="username" class="form-control" value="${userProfile.userName}"
                               placeholder="Username">

                        <#if errorUsername??>
                            <p class="ui-state-error-text">${errorUsername}</p>
                        </#if>
                        <#if niceUsername??>
                            <p class="ui-state-active-text">${niceUsername}</p>
                        </#if>
                    </div>

                    <div class="col-md-12">
                        <label class="labels">Mobile Number</label>
                        <input type="tel" name="phoneNumber" class="form-control"
                               placeholder="Phone number" value="${userProfile.phoneNumber}">
                        <#if errorPhoneNumber??>
                            <p class="ui-state-error-text">${errorPhoneNumber}</p>
                        </#if>
                        <#if nicePhoneNumber??>
                            <p class="ui-state-active-text">${nicePhoneNumber}</p>
                        </#if>
                    </div>

                    <div class="col-md-12">
                        <label class="labels">City</label>

                        <select name="city" class="form-control">
                            <option></option>
                            <#list cities as city>
                                <option value="${city}" <#if userProfile.city == city>selected</#if>>
                                    ${city}
                                </option>
                            </#list>
                        </select>

                        <#if niceCity??>
                            <p class="ui-state-active-text">${niceCity}</p>
                        </#if>
                    </div>

                    <div class="col-md-12">
                        <label class="labels">Sex </label> <br>

                        <select name="sex" class="form-control">
                            <option></option>
                            <option value="${userProfile.sex}" <#if userProfile.sex == "Male">selected</#if>>
                                Male
                            </option>
                            <option value="${userProfile.sex}" <#if userProfile.sex == "Female">selected</#if>>
                                Female
                            </option>
                        </select>

                        <#if niceSex??>
                            <p class="ui-state-active-text">${niceSex}</p>
                        </#if>
                    </div>

                    <div class="col-md-12">
                        <label class="labels">Age</label>
                        <input type="number" name="age" class="form-control"
                               placeholder="Age" value="${userProfile.age}">
                        <#if errorAge??>
                            <p class="ui-state-error-text">${errorAge}</p>
                        </#if>
                        <#if niceAge??>
                            <p class="ui-state-active-text">${niceAge}</p>
                        </#if>
                    </div>
                </div>

                <div class="mt-5 text-center">
                    <button class="btn btn-primary profile-button" type="submit">Save Profile
                    </button>
                </div>
            </div>
        </form>

        <form action="/profile/change-password" method="post" class="col-md-4">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Change password</h4>
                </div>
                <br>
                <div class="col-md-12">
                    <label class="labels">Password</label>
                    <input type="password" name="oldPassword" class="form-control"
                           placeholder="Old password" value="">
                </div>
                <div class="col-md-12">
                    <label class="labels">New password</label>
                    <input type="password" name="newPassword" class="form-control"
                           placeholder="New password" value="">
                </div>
                <div class="col-md-12">
                    <label class="labels">Repeat new password</label>
                    <input type="password" name="repeatNewPassword" class="form-control"
                           placeholder="Repeat new password" value="">
                </div>

                <#if errorChange??>
                    <p class="ui-state-error-text">${errorChange}</p>
                </#if>
                <#if niceChange??>
                    <p class="ui-state-active-text">${niceChange}</p>
                </#if>


                <div class="mt-5 text-center">
                    <button class="btn btn-primary profile-button" type="submit">Change</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "*/parts/_footer.ftlh">
</body>
</html>
