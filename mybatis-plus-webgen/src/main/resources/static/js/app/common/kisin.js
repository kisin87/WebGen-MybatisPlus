(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

$(function () {
    let $this = {
        reqDictData:function(dictId, async, reback){
            $.ajax({
                type: 'post',
                url: '/dict/context/list',
                data:{dictId:dictId},
                async: async,
                success: reback,
                error: function (err) {
                    alert("数据拉取失败！"+err);
                }
            });
        },
        initDatetimepicker: function ($dom, havaTime) {
            $dom.datetimepicker({
                language:  'zh-CN',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: havaTime?0:2,
                forceParse: 0
            });
        },
        jsTreeBean:{
            id: "string",
            parent: "string",
            text: "string",
            children: true,
            state: {
                opened: true,
                disabled: false,
                selected: false
            },
            li_attr: {},
            a_attr: {}
        },
        toUrlQuery: function (object) {
            let query = [];
            for (let key in object) {
                query.push(key+'='+object[key]);
            }
            return query.join('&');
        }
    };
    window.kisin = $this;
});
