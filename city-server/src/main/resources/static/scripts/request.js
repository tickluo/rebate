var TOKEN_KEY = "jwtToken";

function setJwtToken(token) {
    localStorage.setItem(TOKEN_KEY, token);
}

function createAuthorizationTokenHeader() {
    var token = localStorage.getItem(TOKEN_KEY);
    if (token) {
        return {"Authorization": token};
    } else {
        return {};
    }
}

function postAjax(url, data, callback) {
    return $.ajax({
        url: url,
        data: JSON.stringify(data),
        type: "post",
        contentType: "application/json;charset=utf-8;",
        dataType: "json",
        success: callback
    })
}

function postSyncAjax(url, data, callback) {
    return $.ajax({
        url: url,
        data: JSON.stringify(data),
        type: "post",
        async: false,
        contentType: "application/json;charset=utf-8;",
        dataType: "json",
        success: callback
    })
}

function getAjax(url, data, callback) {
    return $.ajax({
        url: url,
        data: data,
        type: "get",
        success: callback
    });
}