$(function () {

    let $this = {
        menuSelBtn :null,
        showTableFiled :$('.showTableFiled'),
        whereFieldJsonTable :$('.whereFieldJsonTable'),
        tableFieldValue: [],
        tableName: '',
        parentTableFieldArray: [],
        parentEventFieldIndex: null,
        init: function () {
            $this.parentTableFieldArray = window.parent.tableFieldArray;
            $this.parentEventFieldIndex = window.parent.eventFieldIndex;
            $.get('/data/gen/data/db/table-info/list', $this.buildLeftMenu);
        },
        buildLeftMenu: function (result) {
            $('#leftMenuTemp').tmpl({tableInfo:result.data}).appendTo('.menuSelBtn');
            $this.menuSelBtn = $('.menuSelBtn').find('span');
            $this.menuSelBtn.click($this.tableSelEvent);
        },
        tableSelEvent: function () {
            let currentDom = $(this), oldDom = $('.activation');
            oldDom && oldDom.removeClass('activation');
            currentDom.addClass('activation');
            $this.tableName = currentDom.attr('val');
            $this.buildTableInfo({name:$this.tableName, comment:currentDom.attr('comment')});
            $this.buildTableField();
            $this.whereFieldJsonTable.show();
            $('.matchFieldParent').html('');
            $('.addWhereFieldJsonBtn').click($this.buildWhereFieldJson);
            $('#matchFieldTemp').tmpl({
                fieldArray: $this.tableFieldValue[$this.tableName],
                fieldName: $this.parentTableFieldArray[$this.parentEventFieldIndex].name
            }).appendTo('.matchFieldParent');
            $('.desc').focus();
            $('#tableName').val($this.tableName);
            $('.subBtn').click($this.submit);
        },
        buildTableInfo: function (tableInfo) {
            let tableInfoDom = $('.tableInfo');
            tableInfoDom.html('');
            $('#tableInfoTemp').tmpl(tableInfo).appendTo(tableInfoDom);
        },
        buildTableField: function () {
            $this.showTableFiled.html('');
            let fieldArray = $this.tableFieldValue[$this.tableName];
            if(!fieldArray){
                fieldArray = $this.getFieldArray();
                $this.tableFieldValue[$this.tableName] = fieldArray;
            }
            $('#checkboxFieldTemp').tmpl({fieldArray:fieldArray}).appendTo($this.showTableFiled);
        },
        buildWhereFieldJson: function () {
            let fieldArray = $this.tableFieldValue[$this.tableName];
            let whereFieldJsonDom = $('#whereFieldJsonTemp').tmpl({fieldArray:fieldArray});
            let wfjType = whereFieldJsonDom.find('.wfj-type');
            let wfjValueP = whereFieldJsonDom.find('.wfj-value-p');
            let wfjDel = whereFieldJsonDom.find('.wfj-del');
            buildVal();
            wfjType.change(buildVal);
            wfjDel.click(function () {
                whereFieldJsonDom.remove();
            });
            whereFieldJsonDom.appendTo($('.whereFieldJsonTable'));
            function buildVal() {
                wfjValueP.html('');
                $('#whereFieldJsonTemp_'+wfjType.val()).tmpl({parentTableData:$this.parentTableFieldArray}).appendTo(wfjValueP);
            }
        },
        getFieldArray: function () {
            console.log('加载'+$this.tableName+"表的字段。。。")
            let fieldArray = null;
            $.ajax({
                type: 'get',
                url: '/data/gen/data/db/table/field-info/list',
                data:{tableName:$this.tableName},
                async: false,
                success: function (result) {
                    fieldArray = result.data;
                },
                error: function (err) {
                    alert("数据拉取失败！"+err);
                }
            });
            return fieldArray;
        },
        submit:function () {
            let jsonVal = $('#form1').serializeJSON();
            jsonVal.quote.fields = jsonVal.quote.fields.join(', ');
            jsonVal.quote.whereFieldJson = JSON.stringify(jsonVal.quote.whereFieldJson);
            $.ajax({
                type:'post',
                url:'/data/gen/quote/table/edit',
                contentType : 'application/json;charset=utf-8',
                dataType:"json",
                data:JSON.stringify(jsonVal),
                success:$this.editSaveOk,
                error:$this.editSaveErr
            });
        },
        editSaveOk: function (result) {
            let action = $.getUrlParam('action');
            if(!action || action!='choose') action = 'list';
            window.location='/gen/quote/table/'+action;
        },
        editSaveErr: function (err) {
            alert('保存失败！');
        }
    };

    $this.init();
});
