<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">正铭</div>
    <!-- 头部区域（可配合layui已有的水平导航）layui-this -->
    <ul class="layui-nav layui-layout-left" id="muens">
      <li id="fqsp" class="layui-nav-item"><a href="__APP__/Web/Public/launch">发起审批</a></li>
      <li id="dwsp" class="layui-nav-item"><a href="__APP__/Web/Public/myshenpi?type=1" >待我审批的</a></li>
      <li id="wysp" class="layui-nav-item"><a href="__APP__/Web/Public/myshenpi?type=2">我已审批的</a></li>
      <li id="wfqd" class="layui-nav-item"><a href="__APP__/Web/Public/myshenpi?type=3">我发起的</a></li>
      <li id="cswd" class="layui-nav-item"><a href="__APP__/Web/Public/myshenpi?type=4">抄送我的</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          ${sessionScope.user.username}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="/nice/home/loginout">退出</a></li>
    </ul>
  </div>
</div>
<script type="text/javascript">
    var test=window.location.search;
    var id = test.substr(-1,1);
    if(id==1)
    {
        $("#muens  li:eq(1)").addClass('layui-this');
        $("#muens  li:eq(2)").removeClass('layui-this');
        $("#muens  li:eq(3)").removeClass('layui-this');
        $("#muens  li:eq(4)").removeClass('layui-this');        
    }else if(id==2)
    {
        $("ul li:eq(2)").addClass('layui-this');
        $("ul li:eq(3)").removeClass('layui-this');
        $("ul li:eq(4)").removeClass('layui-this');
        $("ul li:eq(1)").removeClass('layui-this');        
    }else if(id==3)
    {
        $("ul li:eq(3)").addClass('layui-this');
        $("ul li:eq(4)").removeClass('layui-this');
        $("ul li:eq(2)").removeClass('layui-this');
        $("ul li:eq(1)").removeClass('layui-this');        
    }else if(id==4)
    {
        $("ul li:eq(4)").addClass('layui-this');
        $("ul li:eq(3)").removeClass('layui-this');
        $("ul li:eq(2)").removeClass('layui-this');
        $("ul li:eq(1)").removeClass('layui-this');        
    }else{
        $("ul li:eq(0)").addClass('layui-this');
        $("ul li:eq(1)").removeClass('layui-this');
        $("ul li:eq(2)").removeClass('layui-this');
        $("ul li:eq(3)").removeClass('layui-this');           
    }
</script>