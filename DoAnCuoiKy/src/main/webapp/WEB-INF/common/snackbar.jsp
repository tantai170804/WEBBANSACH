<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#snackbar {
    visibility: hidden;
    min-width: 280px;
    background-color: #333;
    color: #fff;
    text-align: center;
    border-radius: 4px;
    padding: 14px;
    position: fixed;
    z-index: 9999;
    left: 50%;
    bottom: 30px;
    transform: translateX(-50%);
}

#snackbar.show {
    visibility: visible;
    animation: fadein 0.4s, fadeout 0.4s 2.6s;
}

#snackbar.success { background-color: #4CAF50; }
#snackbar.error   { background-color: #F44336; }
#snackbar.warning { background-color: #FF9800; }

@keyframes fadein {
    from { opacity: 0; bottom: 0; }
    to   { opacity: 1; bottom: 30px; }
}

@keyframes fadeout {
    from { opacity: 1; bottom: 30px; }
    to   { opacity: 0; bottom: 0; }
}
</style>

<div id="snackbar"></div>

<c:if test="${not empty snackbarMsg}">
<script>
    window.onload = function () {
        showSnackbar("${snackbarMsg}", "${snackbarType}");
    }
</script>
</c:if>
<c:if test="${not empty sessionScope.snackbarMsg}">
<script>
    window.onload = function () {
        showSnackbar("${sessionScope.snackbarMsg}",
                     "${sessionScope.snackbarType}");
    }
</script>

<c:remove var="snackbarMsg" scope="session"/>
<c:remove var="snackbarType" scope="session"/>
</c:if>

<script>
function showSnackbar(message, type = "success") {
    let snackbar = document.getElementById("snackbar");
    snackbar.className = "";
    snackbar.classList.add("show", type);
    snackbar.innerText = message;

    setTimeout(() => {
        snackbar.className = "";
    }, 3000);
}
</script>
