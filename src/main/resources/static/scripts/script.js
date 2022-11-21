var storage = window.localStorage

function toggleDropdown() {
    document.getElementById("drpdwn").classList.toggle("show");
}

window.onclick = function (event) {
    if (!event.target.matches('.animated-button')) {

        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}

function addToCart(key) {
    if (storage.getItem(key) == null) {
        storage.setItem(key, "1")
    } else {
        storage.setItem(key, parseInt(storage.getItem(key)) + 1)
    }
    outputCartCount()
}

function clearCart() {
    storage.clear()
}

function countCart() {
    let cnt = 0;
    for (i = 0; i < storage.length; i++) {
        let key = storage.key(i)
        cnt += parseInt(storage.getItem(key))
    }
    return cnt;
}


function outputCartCount() {
    cnt = countCart()
    var div = document.getElementById('cart-cnt')
    if (cnt === 0) {
        div.innerHTML = ""
    }
    div.innerHTML = cnt
}


outputCartCount()

