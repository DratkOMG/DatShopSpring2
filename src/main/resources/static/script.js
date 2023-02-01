function loadMore() {
    let amount = document.getElementsByClassName("product").length;
    $.ajax({
        url: "/home/load-more",
        type: "post",
        data: {
            exits: amount
        },
        success: function (response) {
            if (response.toString().length === 0) {
                let element = document.getElementById("btvn");
                element.setAttribute("hidden", "hidden");

            } else {
                let row = document.getElementById("content");
                row.innerHTML += response;
            }
        },
        error: function (xhr) {

        }
    });
}

function loadMoreManager() {
    let amount = document.getElementsByClassName("product").length;
    let url = new URL(document.URL);
    let id = url.searchParams.get("cid");
    $.ajax({
        url: "/manage-product/load-more",
        type: "get",
        data: {
            exits: amount,
            cid: id
        },
        success: function (response) {
            if (response.toString().length === 0) {
                let element = document.getElementById("btvn");
                element.setAttribute("hidden", "hidden");


                let elementUpTop = document.getElementById("up")
                elementUpTop.removeAttribute("hidden");
            } else {
                let row = document.getElementById("content");
                row.innerHTML += response;
            }
        },
        error: function (xhr) {

        }
    });
}

let toggle = button => {
    let element = document.getElementById("list-category-2");
    let hidden = element.getAttribute("hidden");

    if (hidden) {
        element.removeAttribute("hidden");
        button.innerText = "Hide list";
    } else {
        element.setAttribute("hidden", "hidden");
        button.innerText = "Show list";
    }
}

function liveSearch(param) {
    let search = param.value;

    $.ajax({
        url: "/home/live-search",
        type: "post",
        data: {
            search: search
        },
        success: function (response) {
            let row = document.getElementById("content");
            row.innerHTML = response
        }
    })
}

function addToCart(param) {
    $.ajax({
        url: "/cart/add-to-cart",
        type: "post",
        data: {
            bookId: param
        },
        success: function (response) {
            let row = document.getElementById("count-product");
            row.innerText = response;
        }
    })
}


function update(param, price, id) {
    let amount = document.getElementById("amount" + id).value;
    let calculate = price * amount;
    document.getElementById("price" + id).innerText = (calculate) + " $";

    let arr = document.getElementsByClassName("sumne");
    let total = 0;

    for (let i = 0; i < arr.length; i++) {
        total += parseInt(arr[i].innerHTML);
    }

    document.getElementById("subtotal").innerHTML = "$" + total;

    let ship = parseFloat(document.getElementById("3").id);

    document.getElementById("total").innerText = "$" + (total + ship);
    document.getElementById("check-out").innerText = "$" + (total + ship);

    $.ajax({
        url: "update_cart_controller",
        type: "post",
        data: {
            amount: amount,
            bid: id
        }, success: function (response) {
            let row = document.getElementById("pay");
            if (document.getElementById("sure") == null) {
                row.innerHTML += response;
                let row2 = document.getElementById("hid");
                row2.remove();
            }
        }
    })
}

function up() {
    window.scroll({
        top: 0,
        behavior: "smooth",
    });
}

function subscribe(id) {
    let elementSub = document.getElementsByClassName("btn_subscribe");
    let elementUnsub = document.getElementsByClassName("btn_unsubscribe");

    $.ajax({
        url: "subscriptions/subscribe",
        type: "GET",
        data: {
            idUser: id
        },
        success: function (response) {
            elementSub.setAttribute("hidden", "hidden");
            elementUnsub.removeAttribute("hidden");
        }
    });
}

function unsubscribe(id) {
    let elementSub = document.getElementsByClassName("btn_subscribe");
    let elementUnsub = document.getElementsByClassName("btn_unsubscribe");

    $.ajax({
        url: "subscriptions/unsubscribe",
        type: "GET",
        data: {
            idUser: id
        },
        success: function (response) {
            elementUnsub.setAttribute("hidden", "hidden");
            elementSub.removeAttribute("hidden");
        }
    });
}





