$(function(){
    $this = {
        ids: {id: $.getUrlParam("id"),},
        entity: {company:'', id:'', sell:'', name:'', status:'', colors:'', typeId:'',},
        entityData: new Object(),
        init: function () {
            $.post('/product/product/data', $this.ids, $this.buildInputView);
        },
        buildInputView: function (result) {
            if(!result || result.code!='200'){
                layer.alert('数据加载失败！');
            }
            $this.entityData = result.data||$this.entity;
            $this.entityData['sellList'] = $this.queryDict('product-sell');
            $this.entityData['statusList'] = $this.queryDict('product-status');
            $this.entityData['colorsList'] = $this.queryDict('product-color');
            $('#inputHtmlTmpl').tmpl($this.entityData).appendTo('.form1');
            $('.submitBtn').click($this.submit);
            $('.cancelBtn').click($this.toList);
        },
        submit: function () {
            $.post('/product/product/edit', $('.form1').serialize(), function (result) {
                if(result && result.code=='200'){
                    layer.msg('保存成功！', {time: 600}, $this.toList);
                }else{
                    layer.msg('保存失败！');
                }
            });
        },
        queryDict: function(type) {
            let value = null;
            $.ajax({
                async: false,
                type: 'post',
                url: '/dict/dict/type/list',
                data:{type:type},
                success: function (result) {
                    if(result.code!=200){
                        value=null;
                        alert("数据拉取失败！");
                    }
                    value = result.data;
                },
                error: function (err) {
                    value=null;
                    alert("数据拉取失败！"+err);
                }
            });
            return value;
        },
        toList: function () {
            parent.location.reload();
        }
    }
    $this.init();
});
