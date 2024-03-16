let $video = $('#video');
let $word = $('#word');
let $kuaishou = $('#kuaishou');
let $loginOut = $('#loginOut');

const AI_PLATFORM_TOKEN_NAME = "APP-AI-PLATFORM-TOKEN"

$video.on('click',function(e) {
    e.stopPropagation();
    window.open('https://aic.oceanengine.com/tools/smart_clip/edit?bpId=1793434281831452&mode=0&industry=0')
})

$word.on('click',function(e) {
    e.stopPropagation();
    window.open('https://chat.baidu.com/')
})

$kuaishou.on('click',function(e) {
    e.stopPropagation();
    window.open('https://jigou.kuaishou.com/workbench/v2')
})

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
}

$loginOut.on('click',function(e) {
    e.stopPropagation();
    localStorage.removeItem(AI_PLATFORM_TOKEN_NAME);
    deleteCookie(AI_PLATFORM_TOKEN_NAME)
    $.ajax({
        type: 'GET',
        url: '/user/logout',
        success: function(res) {
            console.log(res.data)
        },
        error: function(res) {
            console.log("logout error ----:", res)
        },

    });
    window.location.href = '/login.html';
})