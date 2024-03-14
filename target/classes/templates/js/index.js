let $video = $('#video');
let $word = $('#word');
let $kuaishou = $('#kuaishou');
let $loginOut = $('#loginOut');

$video.on('click',function(e) {
    e.stopPropagation();
    window.open('https://aic.oceanengine.com/login')
})

$word.on('click',function(e) {
    e.stopPropagation();
    window.open('https://chat.baidu.com/')
})

$kuaishou.on('click',function(e) {
    e.stopPropagation();
    window.open('https://jigou.kuaishou.com/workbench/v2')
})

$loginOut.on('click',function(e) {
    e.stopPropagation();
    window.location.href = 'login.html';
})