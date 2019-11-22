$(function () {
    let $this = {
        showDataTable: $('.showDataTable'),
        newBtn: $('.newBtn'),
        init: function () {
            this.showDataTable.bootstrapTable({
                method: 'get',
                url: '/data/gen/quote/table/list',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                showColumns: false,
                columns: [
                    {formatter: (v, r, i) => i + 1},
                    {field: 'tableName', title: '表名'},
                    {field: 'descs', title: '描述'},
                    {field: 'fields', title: '查询字段'},
                    {field: 'whereFieldJson', title: '条件'},
                    {field: 'matchField', title: '匹配字段'},
                ],
                onLoadSuccess: $this.initEvent
            });
        },
        initEvent: function () {
            $this.newBtn.click(function () {
                window.location='/gen/quote/table/edit';
            });

        }
    };

    $this.init();
});
