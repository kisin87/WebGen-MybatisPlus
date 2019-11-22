$(function(){
    $this = {
        ids: {
            dictId: $.getUrlParam("dictId")
        },
        entityData: new Object(),
        init: function () {
            $.post('/dict/dict/data', $this.ids, $this.buildInputView);
        },
        buildInputView: function (result) {
            if(!result || result.code!='200'){
                layer.alert('数据加载失败！');
            }
            $this.entityData = result.data;
            $('#inputHtmlTmpl').tmpl($this.entityData).appendTo('.form1');
            $('.submitBtn').click($this.submit);
            $('.cancelBtn').click($this.toList);
        },
        submit: function () {
            $.post('/dict/dict/edit', $('.form1').serialize(), function (result) {
                if(result && result.code=='200'){
                    layer.msg('保存成功！', {time: 600}, $this.toList);
                }else{
                    layer.msg('保存失败！');
                }
            });
        },
        toList: function () {
            parent.location.reload();
        }
    }
    $this.init();
});
