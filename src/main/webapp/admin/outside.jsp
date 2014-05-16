<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<jsp:include page="/resources.jsp" />
		<script type="text/javascript">
			function add() {
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择父类");
				} else {
					$("#dlg_manager").dialog("open");
					$("#fm_manager").form("clear");
					$("#outside_outsidePTitle").val(row.outsideName);
					$("#outside_outsidePid").val(row.outsideId);
				}
			}
			
			function edit() {
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条记录");
				} else {
					$("#dlg_manager").dialog("open");
					
					var parent = $("#dg_list").treegrid("getParent", row.outsideId);
					$("#outside_outsidePTitle").val(parent.outsideName);
					$("#outside_outsidePid").val(parent.outsideId);
					
					$("#outside_outsideId").val(row.outsideId);
					$("#outside_outsideName").val(row.outsideName);
					$("#outside_outsideSort").val(row.outsideSort);
					$("#outside_outsideUrl").val(row.outsideUrl);
				}
			}
			
			function del() {
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条记录");
				} else {
					funtl_easyui_dialog.confirm("确定要删除这条记录吗？", function() {
						var data = {
							"outside.outsideId" : row.outsideId
						};
						funtl_easyui_ajax.post("news/Outside/action/delete", data, function(data) {
							if (data.message == null || data.message.length == 0) {
								$("#dg_list").treegrid("reload");
								funtl_easyui_dialog.info("数据已删除");
							} else {
								funtl_easyui_dialog.info(data.message);
							}
						});
					});
				}
			}
			
			var dlgManagerBtn = [{
			    text:"保存",
			    iconCls:"icon-ok",
			    handler:function() {
			    	if ($("#outside_outsideId").val() == "") {
			    		$("#fm_manager").attr("action", "news/Outside/action/insert");
			    	} else {
			    		$("#fm_manager").attr("action", "news/Outside/action/update");
			    	}
			    	
			    	funtl_easyui_form.submit("fm_manager", function(data) {
						if (data.message == null || data.message.length == 0) {
							$("#fm_manager").form("clear");
							$("#dlg_manager").dialog("close");
							$("#dg_list").treegrid("reload");
							funtl_easyui_dialog.info("数据已保存");
						} else {
							funtl_easyui_dialog.info(data.message);
						}
					});
			    }
			},{
			    text:"取消",
			    iconCls:"icon-cancel",
			    handler:function() {
			    	$("#dlg_manager").dialog("close");
			    }
			}];
		</script>
		<title><%=System.getProperty("systemName") %></title>
	</head>
	
	<body>
		<table id="dg_list" class="easyui-treegrid" 
			data-options="
				idField:'outsideId',
				treeField:'outsideName',
				rownumbers:true,
				singleSelect:true,
				url:'news/Outside/action/queryTreeArray',
				toolbar:'#dg_list_toolbar',
				onLoadError:funtl_easyui_ajax.onLoadError
			">
			<thead>
	  			<tr>
	  				<th data-options="field:'outsideName'">外链名称</th>
	  				<th data-options="field:'outsideSort'">外链排序</th>
	  				<th data-options="field:'outsideUrl'">外链地址</th>
	  				<th data-options="field:'outsideCreatename'">创建人</th>
	  				<th data-options="field:'outsideCreatedate'">创建时间</th>
	  			</tr>
  			</thead>
		</table>
		<div id="dg_list_toolbar" style="padding:5px;height:auto">
	   		<div style="margin-bottom:5px">
		  		<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add();">新增</a>
		  		<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del();">删除</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit();">编辑</a>
			</div>
	   	</div>
		<script>
	   		$("#dg_list").height($(document).height() * 0.98);
	   	</script>
	   	
	   	<div id="dlg_manager" class="easyui-dialog" style="width:800px;height:auto;padding:10px" data-options="title:'管理',buttons:dlgManagerBtn,modal:true,closed:true,maximizable:true">
	   		<form id="fm_manager" method="post" action="">
	   			<input id="outside_outsideId" type="hidden" name="outside.outsideId" />
	   			<table align="center" style="width:100%;">
	   				<tr>
	   					<td align="right">父类标题</td>
	   					<td colspan="5">
	   						<input id="outside_outsidePTitle" class="easyui-validatebox" type="text" name="" data-options="required:true" readonly="readonly" style="width:80%;"></input>
	   						<input id="outside_outsidePid" type="hidden" name="outside.outsidePid" />
	   					</td>
	   				</tr>
	   				<tr>
		    			<td align="right">外链名称</td>
		    			<td><input id="outside_outsideName" class="easyui-validatebox" type="text" name="outside.outsideName" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">外链排序</td>
		    			<td><input id="outside_outsideSort" class="easyui-validatebox" type="text" name="outside.outsideSort" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   				<tr>
		    			<td align="right">外链地址</td>
		    			<td><input id="outside_outsideUrl" class="easyui-validatebox" type="text" name="outside.outsideUrl" data-options="required:true" style="width:80%;"></input></td>
		    		</tr>
	   			</table>
	   		</form>
	   	</div>
	</body>
</html>