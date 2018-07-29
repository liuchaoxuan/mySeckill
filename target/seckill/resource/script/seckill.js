//存放交互逻辑的代码
//模块化js（package，类，方法）

var seckill = {

    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    detail: {
        init: function (params) {

            var userPhone = $.cookie('userPhone');
            if (!seckill.validatePhone(userPhone)) {
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true, //显示弹出框
                    backdrop: 'static', //禁止位置关闭
                    keyboard: false  //禁止键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log('input phone: ' + inputPhone);

                    if (seckill.validatePhone(inputPhone)) {
                        //写入cookie，七天过期
                        $.cookie('userPhone', inputPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        //输出验证错误信息
                        $('#killPhoneMessage').hide().html('<label class="label-danger">手机号错误！</label>').show(300);
                    }
                });
            } else {
                //已经登录，计时交互
                var seckillId = params['seckillId'];
                var startTime = params['startTime'];
                var endTime = params['endTime'];
                $.get(seckill.URL.now(), {}, function (result) {
                    if (result && result['success']) {
                        var now = result['data'];
                        //时间判断，计时交互
                        seckill.countDown(seckillId, startTime, endTime, now);
                    }
                });
            }
        }
    },

    countDown: function (seckillId, startTime, endTime, now) {
        console.log(seckillId + '_' + now + '_' + startTime + '_' + endTime);
        var seckillBox = $('#seckill-box');
        if (endTime < now) {
            seckillBox.inn('秒杀已经结束！');
        } else if (now < startTime) {
            var killTime = new Date(startTime);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒 ');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //时间完成后回调事件
                //获取秒杀地址,控制现实逻辑,执行秒杀
                console.log('______fininsh.countdown');
                seckill.handleSeckill(seckillId, seckillBox);
            });
        }else {
            //秒杀开始
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },

    handleSeckill: function (seckillId, node) {
        //获取秒杀地址，控制显示器，开始秒杀
        node.hide().html('<button id="killBtn" class="btn btn-primary btn-lg">开始秒杀！</button>');
        $.get(seckill.URL.exposer(seckillId), {}, function (result) {
            //在回调函数中执行秒杀操作交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']){
                    //开启秒杀
                    //获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    $('#killBtn').one('click', function () {
                        //执行秒杀操作
                        //先禁用按钮，防止用户不停点击
                        $(this).addClass('disable');
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']){
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //显示秒杀结果
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    //未开启秒杀(浏览器计时偏差)
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countDown(seckillId, now, start, end);
                }
            } else {
                console.log('result: ' + result);
            }
        });

    }
}