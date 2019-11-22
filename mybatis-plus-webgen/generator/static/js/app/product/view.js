$(function(){
    $this = {
        ids: {
            id: $.getUrlParam("id")
        },
        entityData: new Object(),
        init: function () {
            $.post('/product/product/data', $this.ids, $this.buildDataView);
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
            window.location='/product/product/edit?'+kisin.toUrlQuery($this.ids);
        },
        toList: function () {
            parent.location.reload();
        }
    }
    $this.init();
});
