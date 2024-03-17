let $toRegistBtn = $('#toRegistBtn')
let $num = $('#num')
let $password = $('#password')
let $code = $('#code')
let $codeBox = $('#codeBox')
let $loginBtn = $('#loginBtn')
let $registBtn = $('#registBtn')

const AI_PLATFORM_TOKEN_NAME = "APP-AI-PLATFORM-TOKEN"

// 点击去往注册按钮
$toRegistBtn.on('click',function(e) {
    e.stopPropagation();
    $codeBox.show()
    $loginBtn.hide()
    $registBtn.show()

    return




})
// 点击注册按钮
$registBtn.on('click',function(e) {
    e.stopPropagation();

    var userNameText = $num.val();
    var passwordText = $password.val();
    var codeText = $code.val();
    if(userNameText === '') {
        alert('账号不能为空');
        return false;
    }
    if(passwordText === '') {
        alert('密码不能为空');
        return false;
    }
    if(codeText === '') {
        alert('邀请码不能为空');
        return false;
    }

    var jsonData = {
        "username": userNameText,
        "password": passwordText,
        "code": codeText
    };

    // 将JSON数据对象转换为字符串
    var jsonString = JSON.stringify(jsonData);

    $.ajax({
        type: 'POST',
        url: '/user/regist',
        dataType: "json",
        contentType: "application/json",
        data: jsonString,
        success: function(res) {
            // 请求成功处理逻辑
            if (res.success) {
                $codeBox.hide()
                $loginBtn.show()
                $registBtn.hide()
            } else {
                alert(res.msg);
            }

        },
        error: function(res) {
            alert(res.msg)
        },

    });


})
$loginBtn.on('click',function(e) {
    e.stopPropagation();
    var userNameText = $num.val();
    var passwordText = $password.val();
    if(userNameText === '') {
        alert('账号不能为空');
        return false;
    }
    if(passwordText === '') {
        alert('密码不能为空');
        return false;
    }

    var jsonData = {
        "username": userNameText,
        "password": passwordText
    };

    $.ajax({
        type: 'POST',
        url: '/user/login',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        success: function(res) {
            console.log("success")
            if (res.code === "200") {
                localStorage.setItem(AI_PLATFORM_TOKEN_NAME, res.data);
                const expires = "expires="+ new Date(new Date().getTime() + 1 * 60 * 60 * 1000).toUTCString();
                document.cookie = `${AI_PLATFORM_TOKEN_NAME}=${res.data}; ${expires}; path=/`;
                window.location.href = '/index.html';
            } else if (res.code === "401") {
                alert(res.message)
            }
        },
        error: function(res) {
            console.log("error ----:", res)
        },

    });


})