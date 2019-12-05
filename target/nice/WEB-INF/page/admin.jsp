<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="/nice/static/plugin/layui/css/layui.css">
<style type="text/css">
.layui-form{
	margin:0px;
}
</style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <%@include file="../common/top.jsp"%>
  <%@include file="../common/left.jsp"%>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <table  style="padding:0px;margin: 0px" id="test" lay-filter="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
           
		    <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
		    <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
		    <button class="layui-btn layui-btn-sm" lay-event="update">修改</button>
			<button class="layui-btn layui-btn-sm" lay-event="reload">重载</button>
        </div>
    </script>    
  </div>
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>    
<script type="text/javascript" src="/nice/static/plugin/layui/layui.js"></script>
<script>
	//JavaScript代码区域
	layui.use('element', function(){
	  var element = layui.element;
	});
	layui.use('table', function(){
	        var table = layui.table;
	        table.render({
	          elem: '#test',
	          toolbar: '#toolbarDemo',
	          defaultToolbar: [''],
	          url:'/nice/admin/getorders',
	          cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
	          cols: [[
		            {type: 'radio', fixed: 'left'},
		            {field:'user_id', title:'ID', width:80, fixed: 'left', unresize: true},
		            {field:'createtime', title:'时间', width:120},
		            {field:'note', title:'说明', width:120}
	          ]]
	        });

	        //头工具栏事件
	        table.on('toolbar(test)', function(obj){
	          	var checkStatus = table.checkStatus(obj.config.id);
	          	switch(obj.event){
	                case 'add':
	                    layer.open({
	                        title:'更新论坛信息',
	                        type: 1,
	                        skin: 'layui-layer-rim', 
	                        area: ['500px', '580px'], 
	                        content: $('#dialog')
	                      });
	                break;
	                case 'update':
	                  layer.msg('编辑');
	                break;
	                case 'delete':
	                  layer.msg('删除');
	                break;
	                case 'reload':
	                  var data = checkStatus.data;
	                  layer.alert(JSON.stringify(data));
	                break;
	              };
	        });          
	      });	
</script>
<div id="dialog" style="display: none;padding: 20px;">
 
<form class="layui-form layui-form-pane1" action="/nice/admin/addorder" lay-filter="first">
  <div class="layui-form-item">
    <label class="layui-form-label">输入框</label>
    <div class="layui-input-block">
      <input type="number" name="user_id" lay-verify="required|title" lay-reqText="标题不能为空" required placeholder="请输入标题" autocomplete="off" class="layui-input" >
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="text" name="createtime" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">请务必填写用户名</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">选择框</label>
    <div class="layui-input-block">
      <select name="note" lay-filter="interest">
        <option value="0">写作</option>
        <option value="1">阅读</option>
        <option value="2">游戏</option>
        <option value="3">音乐</option>
        <option value="4">旅行</option>
      </select>
    </div>
  </div>
  
 
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
</div>
</body>
</html>
