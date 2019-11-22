$(function () {
    let $this = {
        genId: $.getUrlParam("genId"),
        subBtn: $('.sub-btn'),
        javaTypeArray: [],
        inputTypeArray: [],
        rowCellSizeArray: [],
        dictTypeArray: [],
        init: function () {
            $.ajax({
                async : false,
                method: 'get',
                url: '/data/gen/data',
                dataType:"json",
                success:function (result) {
                    $this.javaTypeArray = result.data['JAVA_TYPE_LIST'].map(v=>({value:v, text:v}));
                    $this.inputTypeArray = result.data['INPUT_TYPE_LIST'];
                    $this.rowCellSizeArray = result.data['ROW_CELL_SIZE_LIST'];
                    $this.dictTypeArray = result.data['DICT_TYPE_LIST'];
                }
            });
            $.get('/data/gen/table/list?genId=' + $this.genId, $this.initTableData);
            $this.javaTypeArray.sort((a, b)=>a.value.localeCompare(b.value));
        },
        initEvent: function () {
            $('.showTableInfo').click(function () {
                $this.tableInfoToggle($(this), $(this).parent().find('.tableInfo'));
            });
            $this.subBtn.click(function () {
                let isGen = $(this).attr('gen');
                let jsonVal = $('#form1').serializeJSON();
                jsonVal.gen={genId: $this.genId, gen:isGen};
                jsonVal.table.forEach(table => {
                    table.fields = $('#'+table.tableId).bootstrapTable('getData');
                    table.fields.forEach(v=>delete v.columnType);
                });
                $this.submitData(jsonVal);
            });
        },
        tableInfoToggle: function (btn, tableInfo) {
            let showText = '点击展开Java对象详细命名信息<span class="glyphicon glyphicon-chevron-down"></span>';
            let hideText = '点击收起 <span class="glyphicon glyphicon-chevron-up"></span>';
            tableInfo.slideToggle('fast', function () {
                btn.html(tableInfo.is(':hidden')?showText:hideText);
            });
        },
        initTableData: function (result) {
            result.data.forEach(tableInfo=>{
                let tableInfoHtml = $('#tableContentTmpl').tmpl(tableInfo);
                tableInfoHtml.appendTo('#tableInfoContent');
                $this.initFieldData(tableInfo, tableInfoHtml.find('.showDataTable'));
            });
            $this.initEvent();
        },
        initFieldData: function (tableInfo, fieldTable) {
            fieldTable.bootstrapTable({
                method: 'get',
                url: '/data/gen/table/field/list?tableId='+tableInfo.tableId,
                cellInputEnabled: true,
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                showColumns: false,
                pagination: true,
                pageSize: 10,
                sidePagination: "server",
                columns: [
                    {formatter: (v, r, i) => i + 1},
                    {field: 'name', title: '字段名'},
                    {field: 'type', title: '字段类型'},
                    {field: 'comment', title: '描述'},
                    {field: 'propertyName', title: '属性名', align: 'center', valign: 'middle', formatter:(v)=>(v||''), width:'150px', cellInputEnabled: true, cellInputType: 'text'},
                    {field: 'propertyType', title: '属性类型', align: 'center', valign: 'middle', formatter:(v)=>(v+''), width:'150px', cellInputEnabled: true, cellInputType: 'select', cellInputSelectOptinons:$this.javaTypeArray},
                    {field: 'lableName', title: '表签名', align: 'center', valign: 'middle', formatter:$this.defaultLableName, width:'150px', cellInputEnabled: true, cellInputType: 'text'},
                    {field: 'list', title: '列表', align: 'center', valign: 'middle', width:'36px', formatter:(v)=>(v?'1':'0'), cellInputEnabled: true, cellInputType: 'checkbox', cellCheckboxSelVal:'1'},
                    {field: 'edit', title: '编辑', align: 'center', valign: 'middle', width:'36px', formatter:(v)=>(v?'1':'0'), cellInputEnabled: true, cellInputType: 'checkbox', cellCheckboxSelVal:'1'},
                    {field: 'dictType', title: '引用字典', align: 'center', valign: 'middle', formatter:$this.defaultDictType, width:'56px', cellInputEnabled: true, cellInputType: 'select', cellInputSelectOptinons:$this.dictTypeArray},
                    {field: 'cellNum', title: '编辑页行列', align: 'center', valign: 'middle', formatter:$this.defaultRowNum, width:'36px', cellInputEnabled: true, cellInputType: 'select', cellInputSelectOptinons:$this.rowCellSizeArray},
                    {field: 'inputType', title: '录入方式', align: 'center', valign: 'middle', formatter:$this.defaultInputType, width:'150px', cellInputEnabled: true, cellInputType: 'select', cellInputSelectOptinons:$this.inputTypeArray},
                ],
                onCellInputSelectChange:$this.tableInputSelectEvent
            });
        },
        defaultDictType: function (v, r) {
            if (!v) {
                r['dictType'] = '';
            }
            return r['dictType'];
        },
        defaultRowNum: function (v, r) {
            if (!v) {
                r['cellNum'] = 1;
            }
            return r['cellNum'];
        },
        defaultInputType: function (v, r) {
            if (!v) {
                r['inputType'] = 0;
            }
            return r['inputType'];
        },
        defaultLableName: function (v, r) {
            if(!v) {
                r['lableName'] = r['comment'];
            }
            return r['lableName'];
        },
        tableInputSelectEvent: function (field, row, oldValue, index, $el) {
            // switch (row[field]) {
            //     case '2' : $this.inputTypeEvent(field, row, oldValue, index, $el);
            // }
            // if(!row[field])return false;
            // let funs=[$this.fieldDataSource_input, $this.fieldDataSource_dict, $this.fieldDataSource_read];
            // funs[row[field]](row, index, $el);
        },
        inputTypeEvent: function (field, row, oldValue, index, $el) {
            window.tableFieldArray = $('#'+row['tableId']).bootstrapTable('getData');
            window.eventFieldIndex = index;
            layer.open({
                type: 2,
                title: false,
                shadeClose: false, // 点击遮罩关闭层
                area: ['90%', '90%'],
                content: '/gen/quote/table/choose',
                btn: ['提交', '取消'],
                yes: function(index) {
                    let $body = $(layer.getChildFrame('body', index));
                    let val = $body.find('[name=objectItems]:checked').val();
                    if(!val){
                        row["inputType"] = '0';
                        layer.alert('请选择一个要引入的表');
                        return false;
                    }
                    row["inputTypeValue"] = val;
                    layer.close(index);
                },
                btn2: function(index){
                    this.cancel(index);
                },
                cancel: function(index){
                    row[field] = '0';
                    $el.val('0');
                    row["inputTypeValue"] = '';
                    console.log(row["inputType"]);
                }
            });
        },
        submitData: function (data) {
            console.log(data);
            $.ajax({
                type:'post',
                url:'/data/gen/table/edit',
                contentType : 'application/json;charset=utf-8',
                dataType:"json",
                data:JSON.stringify(data),
                success:$this.editSaveOk,
                error:$this.editSaveErr
            });
        },
        editSaveOk: function (result) {
            
        },
        editSaveErr: function (err) {
            
        }
    };
    $this.init();
});
