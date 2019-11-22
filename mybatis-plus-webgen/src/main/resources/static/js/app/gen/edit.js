$(function () {
    let $this = {
        dbTableListResult: [],
        nextBtn: $(".nextBtn"),
        selDbTable: $(".selDbTable"),
        showDataTable: $(".showDataTable"),
        init: function () {
            $.ajax({
                async: false,
                type: 'get',
                url: '/data/gen/data/db/table-info/list',
                success: function (result) {
                    if (result.code != 200) {
                        alert("数据拉取失败！");
                        return;
                    }
                    $this.dbTableListResult = result;
                },
                error: function (err) {
                    alert("数据拉取失败！" + err);
                }
            });
        },
        initEvent: function () {
            this.nextBtn.click(function () {
                $.ajax({
                    type: 'post',
                    url: '/data/gen/edit?' + $this.showDataTable.find('[name=subTableArray]:checked').serialize(),
                    data: {
                        mainTable: $this.selDbTable.val(),
                        moduleName:$('#moduleName').val(),
                        packageName:$('#packageName').val()
                    },
                    success: $this.editSaveOk,
                    error: $this.editSaveErr
                });
            });
        },
        loadSelect: function () {
            this.dbTableListResult.data.forEach(function (v) {
                $this.selDbTable.append("<option value='" + v.Name + "'>" + v.Name + "：" + v.Comment + "</option>");
            });
        },
        loadTable: function () {
            this.showDataTable.bootstrapTable({
                method: 'post',
                data: $this.dbTableListResult.data,
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                showColumns: false,
                clickToSelect: true,
                idField: "Name",
                selectItemName: "subTableArray",
                columns: [
                    {checkbox: true},
                    {field: 'Name', title: '表名'},
                    {field: 'Comment', title: '说明'}
                ]
            });
        },
        editSaveOk: function (result) {
            if (result.code != 200) {
                alert("数据保存失败！");
                return;
            }
            window.location = "/gen/table/edit?genId=" + result.data.genId;
        },
        editSaveErr: function (err) {
            alert("数据保存失败！" + err);
        }

    };

    $this.init();
    $this.loadSelect();
    //$this.loadTable();
    $this.initEvent();
});
