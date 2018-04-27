//首页故障
$.ajax({
    url: '/monitor/data',
    type: 'post',
    success: function (data) {
        console.log(data);
        var mydata = data, mylastchange = [];
        data.forEach(function (value, index) {
            mylastchange[index] = value.lastchange;
        });
        console.log(mylastchange);

        function diff(time) {
            var date1 = time;
            var date2 = new Date();
            var date3 = date2.getTime() - new Date(date1).getTime();
            var days = Math.floor(date3 / (24 * 3600 * 1000));
            var leave1 = date3 % (24 * 3600 * 1000);
            var hours = Math.floor(leave1 / (3600 * 1000));
            var leave2 = leave1 % (3600 * 1000);
            var minutes = Math.floor(leave2 / (60 * 1000));
            console.log(days + "d " + hours + "h " + minutes + " m");
        }

        for (var t = 0; t < mylastchange.length; t++) {
            diff(mylastchange[t])
        }
        var vue_1 = new Vue({
            el: '#vue_1',
            data: {
                d: data
            }
        })
    }
})