let orderData = {}
let itemData = {}

async function getOrderData() {
    let profileURL = '/profile'
    let response = fetch(profileURL, {
        method: 'POST', headers: {
            'Content-Type': 'application/json;charset=utf-8',
        }, body: JSON.stringify(orderData)
    })
    return (await response).json()
}

async function getOrderItemData(map) {
    let profileURL = '/cart'
    let response = fetch(profileURL, {
        method: 'POST', headers: {
            'Content-Type': 'application/json;charset=utf-8',
        }, body: map
    })

    return (await response).json()
}

function getJSONFromStringOrder(order) {
    let map = new Map()
    let arr1 = order.split(",");
    for (let i = 0; i < arr1.length; i++) {
        let arr2 = arr1[i].split(":")
        for (let j = 0; j < arr2.length; j++) {
            arr2[j] = arr2[j].replaceAll(/\D/gm, '');
        }
        map.set(arr2[0], arr2[1])
    }
    let obj = Object.fromEntries(map);

    return JSON.stringify(obj);
}

function getMapFromStringOrder(order) {
    let map = new Map()
    let arr1 = order.split(",");
    for (let i = 0; i < arr1.length; i++) {
        let arr2 = arr1[i].split(":")
        for (let j = 0; j < arr2.length; j++) {
            arr2[j] = arr2[j].replaceAll(/\D/gm, '');
        }
        map.set(arr2[0], arr2[1])
    }
    return map;
}

function calcOrderTotal(quantityID, order) {
    let total = 0
    for (let i = 0; i < order.length; i++) {
        console.log("order[i].price")
        console.log(order[i].price)
        console.log("quantityID.get((order[i].id).toString())")
        console.log(quantityID.get((order[i].id).toString()))
        total += order[i].price * quantityID.get((order[i].id).toString())
    }
    return total
}

getOrderData().then(
    (data) => {
        orderData = data
        let out = ""

        if (data.length === 0) {
            out += '<p class="empty-order-message">' + 'Список заказов пуст' + '</p>'
            document.getElementById('order-data').innerHTML = out
            return
        }

        for (let i = 0; i < data.length; i++) {
            if (data[i].items !== null) {
                let orderMap = getJSONFromStringOrder(data[i].items)
                let quantityID = getMapFromStringOrder(data[i].items)
                getOrderItemData(orderMap).then(
                    (iData) => {
                        itemData = iData
                        out += '<h1 class="order-title">ID Заказа ' + data[i].id + '</h1>'
                        for (let j = 0; j < itemData.length; j++) {
                            out += '<div class="order-item">'
                            out += '<img class = "order-item-img" alt = "" src="images/' + itemData[j].imgPath + '">'
                            out += '<div class="order-item-text">'
                            out += '<p>' + itemData[j].name + '</p>'
                            out += '<p>' + itemData[j].price + ' * ' + quantityID.get((itemData[j].id).toString()) + '</p>'
                            out += '</div>'
                            out += '</div>'
                        }
                        out += '<h1 class="order-title">Итого ' + calcOrderTotal(quantityID, itemData) + '</h1>'
                        document.getElementById('order-data').innerHTML = out
                    }
                )
            }
        }

    }
)
