$(function () {
    let $this = {
        showDataTable: $('.showDataTable'),
        init: function () {
            $('.showDataTable').bootstrapTable({
                method: 'post',
                url: '/data/gen/list',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                showColumns: false,
                columns: [
                    {formatter: (v, r, i) => i + 1},
                    {field: 'moduleName', title: '模块名'},
                    {field: 'packageName', title: '包名'},
                    {field: 'mainTable', title: '主表'},
                    {field: 'subTableStr', title: '子表'},
                    {field: 'genId', title: '操作', align: 'center', formatter:$this.operateFun}
                ],
                onLoadSuccess: $this.initEvent
            });
        },
        initEvent: function () {
            $('.newBtn').click(function () {
                window.location = '/gen/edit';
            });

            $('.rowEditBtn').click(function () {
                window.location = '/gen/table/edit?genId='+$(this).attr('val');
            });
        },
        operateFun: function (value) {
            return '<a href="javascript:void(0);" class="rowEditBtn" val="' + value + '">编辑</a>';
        }
    };
    $this.init();
});
