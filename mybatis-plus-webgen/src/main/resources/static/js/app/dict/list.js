$(function(){
    $this = {
        showTable:$('.showDataTable'),
        init: function(){
            this.showTable.bootstrapTable({
                method: 'post',
                url: '/dict/dict/list',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                showColumns: false,
                pagination: true,
                pageSize: 10,
                sidePagination: "server",
                contentType: 'application/x-www-form-urlencoded',
                columns: [
                    {formatter: (v, r, i) => i + 1},
                    {field: 'dictValue', title: '字典数值'},
                    {field: 'typeName', title: '类型名称'},
                    {field: 'delFlag', title: '删除标识'},
                    {field: 'dictName', title: '字典名称'},
                    {field: 'dictType', title: '字典类型'},
                    {title: '操作', align: 'center', formatter:$this.operateFun}
                ],
                onLoadSuccess: $this.initEvent
            });
        },
        initEvent: function () {
            $('.newBtn').click(function () {
                $this.openTo('/dict/dict/edit');
            });

            $('.rowViewBtn').click(function () {
                let index = $(this).attr('val');
                $this.openTo('/dict/dict/view?'+kisin.toUrlQuery($this.ids(index)));
            });

            $('.rowEditBtn').click(function () {
                let index = $(this).attr('val');
                $this.openTo('/dict/dict/edit?'+kisin.toUrlQuery($this.ids(index)));
            });

            $('.rowRemoveBtn').click(function () {
                let index = $(this).attr('val');
                layer.confirm('你确定要删除当前数据吗？', function () {
                    $.post('/dict/dict/remove', $this.ids(index), function(result){
                        if(result && result.code=='200'){
                            layer.msg('删除成功！', {time: 600}, function(){location.reload()});
                        }else{
                            layer.msg('删除失败！');
                        }
                    });
                });
            });
        },
        ids: function (index) {
            let tableData = $this.showTable.bootstrapTable('getData');
            let ids = new Object();
                ids.dictId = tableData[index].dictId;
            return ids;
        },
        operateFun: function (v, r, i) {
            return '<a href="javascript:void(0);" class="rowViewBtn" val="'+i+'">查看</a>' +
                '<a href="javascript:void(0);" class="rowEditBtn" val="'+i+'">编辑</a>' +
                '<a href="javascript:void(0);" class="rowRemoveBtn" val="'+i+'">删除</a>';
        },
        openTo: function(url){
            layer.open({
                type: 2,
                title: false,
                shadeClose: false, // 点击遮罩关闭层
                area: ['90%', '90%'],
                content: url
            });
        }
    };
    $this.init();
});
