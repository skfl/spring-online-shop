let url = window.location.search

if (url.includes('error')) {
    document.getElementById('error-message').style.visibility = "visible";
}
console.log(URLSearchParams)