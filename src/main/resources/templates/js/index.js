let $video = $('#video');
let $word = $('#word');
let $kuaishou = $('#kuaishou');
let $loginOut = $('#loginOut');

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

$loginOut.on('click',function(e) {
    e.stopPropagation();
    window.location.href = 'login.html';
})