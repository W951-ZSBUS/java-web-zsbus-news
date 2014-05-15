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
		<script type="text/javascript" src="ckfinder/ckfinder.js"></script>
		<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="js/ckeditor/adapters/jquery.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#article_articleContent").ckeditor(); 
			});
		
			function add() {
				var category = $("#tree_list").datagrid("getSelected");
				if (category == null) {
					funtl_easyui_dialog.info("请选择文章分类");
				} else {
					$("#dlg_manager").dialog("open");
					$("#fm_manager").form("clear");
					$("#article_articleContent").val("");
					$("#article_categoryName").val(category.categoryName);
					$("#article_categoryId").val(category.categoryId);
				}
			}
			
			function edit() {
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条记录");
				} else {
					$("#dlg_manager").dialog("open");
					
					$("#article_categoryName").val(row.categoryName);
					$("#article_categoryId").val(row.categoryId);
					
					$("#article_articleId").val(row.articleId);
					$("#article_articleTitle").val(row.articleTitle);
					$("#article_articleSubtitle").val(row.articleSubtitle);
					$("#article_articleContent").val(row.articleContent);
				}
			}
			
			function del() {
				var row = $("#dg_list").datagrid("getSelected");
				if (row == null) {
					funtl_easyui_dialog.info("请选择一条记录");
				} else {
					funtl_easyui_dialog.confirm("确定要删除这条记录吗？", function() {
						var data = {
							"article.articleId" : row.articleId
						};
						funtl_easyui_ajax.post("news/Article/action/delete", data, function(data) {
							if (data.message == null || data.message.length == 0) {
								$("#dg_list").datagrid("reload");
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
			    	if ($("#article_articleId").val() == "") {
			    		$("#fm_manager").attr("action", "news/Article/action/insert");
			    	} else {
			    		$("#fm_manager").attr("action", "news/Article/action/update");
			    	}
			    	
			    	funtl_easyui_form.submit("fm_manager", function(data) {
						if (data.message == null || data.message.length == 0) {
							$("#fm_manager").form("clear");
							$("#dlg_manager").dialog("close");
							$("#dg_list").datagrid("reload");
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
	
	<body class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',title:'文章分类'" style="width:180px;">
			<table id="tree_list" class="easyui-treegrid" 
				data-options="
					idField:'categoryId',
					treeField:'categoryName',
					rownumbers:true,
					singleSelect:true,
					url:'news/Category/action/queryTreeArray',
					onLoadError:funtl_easyui_ajax.onLoadError,
					onClickRow:function(row) {
						if (row.categoryName == '系统目录') {
							$('#dg_list').datagrid({url:'news/Article/action/query'});
						} else {
							$('#dg_list').datagrid({url:'news/Article/action/queryByCategory?categoryId=' + row.categoryId});
						}
						$('#dg_list').datagrid('reload');
					}
				">
				<thead>
		  			<tr>
		  				<th data-options="field:'categoryName'"></th>
		  			</tr>
	  			</thead>
			</table>
		</div>
		
		<div data-options="region:'center',title:'文章列表'">
			<table id="dg_list" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,pagination:true,pageSize:50,url:'news/Article/action/query',toolbar:'#dg_list_toolbar',onLoadError:funtl_easyui_ajax.onLoadError">
				<thead>
		  			<tr>
		  				<th data-options="field:'articleTitle'">文章标题</th>
		  				<th data-options="field:'articleSubtitle'">文章简介</th>
		  				<th data-options="field:'articleAuthor'">文章作者</th>
		  				<th data-options="field:'articleCreatedate'">创建时间</th>
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
		   		$("#dg_list").height($(document).height() * 0.96);
		   	</script>
		   	
		   	<div id="dlg_manager" class="easyui-dialog" style="width:800px;height:auto;padding:10px" data-options="title:'管理',buttons:dlgManagerBtn,modal:true,closed:true,maximizable:true">
		   		<form id="fm_manager" method="post" action="">
		   			<input id="article_articleId" type="hidden" name="article.articleId" />
		   			<table align="center" style="width:100%;">
		   				<tr>
		   					<td align="right">文章分类</td>
		   					<td colspan="5">
		   						<input id="article_categoryName" class="easyui-validatebox" type="text" name="" data-options="required:true" readonly="readonly" style="width:80%;"></input>
		   						<input id="article_categoryId" type="hidden" name="article.category.categoryId" />
		   					</td>
		   				</tr>
		   				<tr>
			    			<td align="right">文章标题</td>
			    			<td><input id="article_articleTitle" class="easyui-validatebox" type="text" name="article.articleTitle" data-options="required:true" style="width:80%;"></input></td>
			    		</tr>
		   				<tr>
			    			<td align="right">文章简介</td>
			    			<td><input id="article_articleSubtitle" class="easyui-validatebox" type="text" name="article.articleSubtitle" data-options="required:true" style="width:80%;"></input></td>
			    		</tr>
		   				<tr>
			    			<td align="right">文章内容</td>
			    			<td colspan="5">
		   						<textarea id="article_articleContent" name="article.articleContent"></textarea>
		   					</td>
			    		</tr>
		   			</table>
		   		</form>
		   	</div>
		</div>
	</body>
</html>