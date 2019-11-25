
var userid = getCookie("CurrentUserId");

if (userid == null) {
    alert("您的登录已过期，请重新登录！");
    window.location.href = '/RedirectLogin.htm';
}