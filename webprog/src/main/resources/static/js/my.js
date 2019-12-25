function tableSearch() {
    let phrase = document.getElementById('search-text');
    let table = document.getElementById('table');
    let regPhrase = new RegExp(phrase.value, 'i');
    let flag = false;
    for (let i = 1; i < table.rows.length; i++) {
        flag = false;
        flag = regPhrase.test(table.rows[i].cells[1].innerHTML);
        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }

    }
}

let name = "";
let cost = "";
let id = "";
let h = document.getElementsByClassName("remove_item");


window.onload = function () {
    let k = document.getElementsByClassName("add_item");
    for (let i = 0; i < k.length; i++) {
        k[i].id = i;
        k[i].onmouseup = function () {
            name = document.querySelectorAll(".automobilename")[this.id].innerText;
            cost = document.querySelectorAll(".automobilecost")[this.id].innerText;
        }
    }
    for (let i = 0; i < h.length; i++) {
        h[i].id = i;
        h[i].onmouseup = function () {
            id = this.id;
        }
    }
};
$(document).ready(function () {
    $(".add_item").on("click", function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "/",
            dataType: "json",
            data: {name: name, cost: cost}
        });
        alert("Товар " + name + " добавлен в корзину")
    })
});

$('select').on('change', function (e) {
    let city = $(this).val();
    document.cookie = "City=" + city +"; path=/;";
});

function readCookie(name) {
    let name_cook = name + "=";
    let spl = document.cookie.split(";");
    for (let i = 0; i < spl.length; i++) {
        let c = spl[i];
        while (c.charAt(0) == " ") {
            c = c.substring(1, c.length);
        }
        if (c.indexOf(name_cook) == 0) {
            return c.substring(name_cook.length, c.length);
        }
    }
    return null;
}

function loaddb(){
    $.ajax({
        type: "POST",
        url: "/addbase",
        dataType: "json",
    });
    alert("Заказ сделан")
    let table = document.getElementById('table2');
    for(let i = 1; i < table.rows.length-1; i++) {
        table.rows[i].remove();
        i--;
    }
    $("#FinalPrice").text(0);
}

let city = readCookie("City");
$(document).ready(function () {
    $('#city').val(city).change();
});

$(document).ready(function () {
    $(".remove_item").on("click", function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "/cart",
            dataType: "json",
            data: {id: id},
        });
        let table = document.getElementById('table2');
        table.rows[Number(id) + 1].remove();
        let price = 0;
        for (let j = 1; j < table.rows.length - 1; j++) {
            price = Number(price) + Number(table.rows[j].cells[1].innerText)
        }
        $("#FinalPrice").text(price);
        for (let i = 0; i < h.length; i++) {
            h[i].id = i;
        }
        alert("Товар удален")
    })
});

$(document).ready(function check() {
    let table = document.getElementById('table2');
    let price = 0;
    for (let i = 1; i < table.rows.length; i++) {
        price = Number(price) + Number(table.rows[i].cells[1].innerText)
    }
    $("#FinalPrice").text(price);
});

