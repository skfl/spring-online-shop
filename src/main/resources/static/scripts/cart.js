function plusToCart(id) {
    storage.setItem(id, parseInt(storage.getItem(id)) + 1)
    outputCartCount()
    let out = storage.getItem(id)
    let elem = "item-count-" + id
    document.getElementById(elem).innerHTML = out
}

function minusFromCart(id) {
    storage.setItem(id, parseInt(storage.getItem(id)) - 1)
    outputCartCount()
    let out = storage.getItem(id)
    let elem = "item-count-" + id
    document.getElementById(elem).innerHTML = out
}

function deleteFromCart(id) {
    alert("delete cart " + id)
}


let url = '/cart'
let cartDataPromise = fetch(url, {
    method: 'POST', headers: {
        'Content-Type': 'application/json;charset=utf-8'
    }, body: JSON.stringify(storage)
}).then(data => data.json()).then(data => {


    function calculateSubtotal(id, count) {
        let i = 0
        while (data[i].id !== id) {
            i++
        }
        let out = ""
        let subtotal = parseInt(data[i].price) * count
        out += '<p>' + subtotal + '</p>'
        let elem = "item-subtotal-" + data[i].id
        document.getElementById(elem).innerHTML = out
    }

    let out = ""
    if (data.length === 0) {
        out += '<p class="empty-cart-message">' + 'Корзина пуста' + '</p>'
        document.getElementById('cart-data').innerHTML = out
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
        calculateSubtotal(item.id, storage.getItem(item.id))
    }
})
