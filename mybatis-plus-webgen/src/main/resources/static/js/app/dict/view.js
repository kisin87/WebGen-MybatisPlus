$(function(){
    $this = {
        ids: {
            dictId: $.getUrlParam("dictId")
        },
        entityData: new Object(),
        init: function () {
            $.post('/dict/dict/data', $this.ids, $this.buildDataView);
        },
        buildDataView: function (result) {
            if(!result || result.code!='200'){
                layer.alert('数据加载失败！');
            }
            $this.entityData = result.data;
            $('#showHtmlTmpl').tmpl($this.entityData).appendTo('.showDataView');
            $('.editBtn').click($this.toEdit);
            $('.cancelBtn').click($this.toList);
        },
        toEdit: function () {
            window.location='/dict/dict/edit?'+kisin.toUrlQuery($this.ids);
        },
        toList: function () {
            parent.location.reload();
        }
    }
    $this.init();
});
