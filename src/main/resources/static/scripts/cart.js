let globalData = {}
globalData = getItemData()

function printEmptyCartText() {
    let out = ""
    console.log("zalupa happened")
    out += '<p class="empty-cart-message">' + 'Корзина пуста' + '</p>'
    document.getElementById('cart-data').innerHTML = out
    let elem = document.getElementById("cart-total-price");
    elem.parentNode.removeChild(elem);
}

function hideOrderForm(){
    let form = document.getElementById("order-form");
    let title = document.getElementById("order-form-title")
    form.style.display = "none";
    title.style.display = "none";
}

function plusToCart(id) {
    storage.setItem(id, parseInt(storage.getItem(id)) + 1)
    outputCartCount()
    calculateSubtotal(id, storage.getItem(id))
    calculateTotal()
    let out = storage.getItem(id)
    let elem = "item-count-" + id
    document.getElementById(elem).innerHTML = out
}

function minusFromCart(id) {
    let itemQuantity = storage.getItem(id)
    if (itemQuantity === "1") {
        deleteFromCart(id)
        if (storage.length === 0) {
            printEmptyCartText()
            hideOrderForm()
            return
        }
        return
    }
    storage.setItem(id, parseInt(storage.getItem(id)) - 1)
    outputCartCount()
    calculateSubtotal(id, storage.getItem(id))
    calculateTotal()
    let out = storage.getItem(id)
    let elem = "item-count-" + id
    document.getElementById(elem).innerHTML = out
}

function deleteFromCart(id) {
    storage.removeItem(id)
    outputCartCount()
    let elem = document.getElementById("item-data-" + id);
    elem.parentNode.removeChild(elem);
    if (storage.length === 0) {
        printEmptyCartText()
        hideOrderForm()
        return
    }
    calculateTotal()
}

function calculateSubtotal(id, count) {
    let i = 0
    while (globalData[i].id !== id) {
        i++
    }
    let out = ""
    let subtotal = parseInt(globalData[i].price) * count
    out += '<p>' + subtotal + '</p>'
    let elem = "item-subtotal-" + globalData[i].id
    document.getElementById(elem).innerHTML = out
}

function calculateTotal() {
    let total = 0
    console.log(globalData.length)
    for (let i = 0; i < globalData.length; i++) {
        total += globalData[i].price * storage.getItem(globalData[i].id)
    }
    if (total <= 0) {
        printEmptyCartText()
        return
    }
    let out = ""

    out += '<p>' + "Итого : " + total + '</p>'
    document.getElementById('cart-total-price').innerHTML = out
}

async function getItemData() {
    let cartUrl = '/cart'
    let response = fetch(cartUrl, {
        method: 'POST', headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }, body: JSON.stringify(storage)
    })

    return (await response).json()
}

let cartDataPromise = getItemData(storage).then(
    (data) => {
        globalData = data
        let out = "";
        if (data.length === 0) {
            out += '<p class="empty-cart-message">' + 'Корзина пуста' + '</p>'
            document.getElementById('cart-data').innerHTML = out
            hideOrderForm()
            return
        }

        for (let i = 0; i < data.length; i++) {
            let item = data[i]
            out += '<div class="item-data" id="item-data-' + item.id + '" >'
            out += '<img class = "item-img" alt = "" src="images/' + item.imgPath + '">'
            out += '<div class = "item-text" id="">'
            out += '<p>' + item.name + '</p>'
            out += '<div class="calculated-cart-data">'
            out += '<p class="item-price" id="item-price-' + item.id + '">' + item.price + '</p>' + '<p>' + ' * ' + '</p>' + '<p id="item-count-' + item.id + '">' + storage.getItem(item.id) + '</p>'
            out += '</div>'
            out += '<div class="item-subtotal">'
            out += '<p class="item-subtotal-text" id="item-subtotal-' + item.id + '">' + '</p>'
            out += "</div>"
            out += '</div>'
            out += '<div class = "item-tools">'
            out += '<img class = "cart-btn-ico" alt = "" src="icons/plus.png" onclick="plusToCart(' + item.id + ')">'
            out += '<img class = "cart-btn-ico" alt = "" src="icons/minus.png" onclick="minusFromCart(' + item.id + ')">'
            out += '<img class = "cart-btn-ico" alt = "" src="icons/trash.png" onclick="deleteFromCart(' + item.id + ')">'
            out += '</div>'
            out += '</div>'
        }

        document.getElementById('cart-data').innerHTML = out
        for (let i = 0; i < data.length; i++) {
            let item = data[i]
            calculateSubtotal(item.id, storage.getItem(item.id), data)
        }
        calculateTotal()
    }
)

