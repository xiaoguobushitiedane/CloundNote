/***
 * 加载普通笔记本
 */
function loadNormalNoteBook(){
	$.post(basePath+"/notebook/findNormal.do",{},function(result){
		if(result.success){
			 list=result.data;
			$(list).each(function(){
			//alert(this.cn_notebook_name);	
				
			
	var li = '<li class="online"><a class="unchecked"><i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i> '+this.cn_notebook_name+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button></a></li>';					
			//将li插入到笔记本列表ul中
	/**
	 * append() 方法在被选元素的结尾（仍然在内部）插入指定内容。
	 */
			$("#first_side_right ul").append(li);
			//给li绑定笔记本数据,将来修改,删除笔记本,或者根据笔记本查询笔记时要使用这个绑定数据
			$("#first_side_right ul li:last").data("notebook",this);	
			
			});
			
		}else{
			alert(result.messager);
		}
		
	});
}

/***
 * 加载特殊笔记本
 */
function loadSpecialNoteBook(){
						
	$.post(basePath+"/notebook/specialnote.do",{},function(result){
		
		if(result.success){
			var list=result.data;
			
			//var notebook=$("#first_side_right ul li:last").data("notebook");
			$(list).each(function(){
			//alert(this.cn_notebook_type_id);	
			
				if(this.cn_notebook_type_id==1){
					
				$("#like_button").data("like_button",this);
					//alert("收藏");
				}else if(this.cn_notebook_type_id==2){
					$("#rollback_button").data("rollback",this);
					
					//alert("回收站");
				}else if(this.cn_notebook_type_id==3){
					$("#action_button").data("action_button",this);
					
					//alert("活动");
				}else if(this.cn_notebook_type_id==4){
					
					$("#first_side_right ul li:first").data("notebook",this);
					alert("推送");
				}
			
			});
			
		}else{
			alert(result.messager);
		}
		
	});
	
	
	
}

/****
 * 添加笔记本
 */
function addNoteBook(){
	var name=$('#input_notebook').val();
	$.post(basePath+"/notebook/add.do",{"notebookname":name},function(result){
		if(result.success){
			//隐藏弹出页面
	        $('.modal.fade.in').hide();
	        //隐藏弹出的div背景图片
	        $('.opacity_bg').hide();
			var no=result.data;
			
			var li = '<li class="online"><a class="unchecked"><i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i> '+no.cn_notebook_name+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button></a></li>';		
			$("#first_side_right ul").append(li);
			$("#first_side_right ul li:last").data("notebook",no);
			
			
		}else{
			alert(result.messager);
		}
		
		
	});
	
	
	
}

/***
 * 重命名笔记本
 */
function updateNoteBook(newname,notebook,checked_li){
	
//	var name=$("#input_notebook_rename").val();
//	var id=$("#pc_part_1 li:gt(0)").data("id");
//	$.post(basePath+"/notebook/rename.do",{"newbookname":name,"bookid":id},function(result){
//		if(result.success){
//
//			//隐藏弹出页面
//	        $('.modal.fade.in').hide();
//	        //隐藏弹出的div背景图片
//	        $('.opacity_bg').hide();
//	        //找到id为pc_part_1下的li标签属性为:大于0的li标签
//			$("#pc_part_1 li:gt(0)").remove();
//			loadNormalNoteBook();
//			
//		}
//		
//	});
	
	
	$.post(
			basePath+"/notebook/rename.do",	{"newbookname":newname,"bookid":notebook.cn_notebook_id},function(result) {
			if(result.success) {
				//成功
				//关闭弹出框
				$(".close,.cancle").trigger("click");
				//将li中的笔记本名换成新名称
				var a = '<a class="checked"><i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i> '+newname+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button></a>';
				checked_li.html(a);
				//将修改后的数据绑定到li
				checked_li.data("notebook",notebook);
			} else {
				//失败
				alert(result.message);
			}
		}
	);
	
	
//	alert("重命名");
}

/***
 * 删除笔记本
 */
function deleteNoteBook(bookid,checked_li){
	var len=$("#second_side_right ul li").length;
	console.log(len);
	
	if(len>0){
		alert("该笔记本中有笔记");
		
	}else{
	$.post(basePath+"/notebook/delete.do",{"bookid":bookid},function(result){
		if(result.success){
			$(".close,.cancle").trigger("click");
			if(result.data){
				checked_li.remove();
	
			}
			
		}else{
			alert(result.messager);
		}
		
	});
	}
	
	//alert("删除笔记本");
}

/**
 * 将笔记本列表放置到select组件中
 */
function setNoteBookToSelect(){
	//将默认笔记本
	//将默认笔记本添加到下拉选项框中
	var book=$("#first_side_right ul li:first").data("notebook");
	var option2='<option value='+book.cn_notebook_id+'>-默认笔记本 -</option>';
	$("#moveSelect").append(option2);
	$("#replaySelect").append(option2);
	
	$(list).each(function(){
		//将笔记本id赋值给option标签的value值
		var option='<option value='+this.cn_notebook_id+'>-'+this.cn_notebook_name+' -</option>';
		$("#moveSelect").append(option);
		//$("#moveSelect option:last").data("option",this);
		
		$("#replaySelect").append(option);
		//$("#replaySelect option:last").data("option",this);
	});
	
	
	console.log("将笔记本列表放置到select组件中");
}