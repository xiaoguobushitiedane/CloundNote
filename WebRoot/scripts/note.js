/***
 * 加载普通笔记
 */
function getNormalNoteList(){
	//获取选中笔记本对应的标签
	var checkq=$("#first_side_right .checked").parent();
	//在标签上获取之前绑定的笔记本数据
	var checkdataq=checkq.data("notebook");
	//获取对应笔记本的id,赋值给一个公共变量
	 boid=checkdataq.cn_notebook_id;
		$.post(basePath+"/note/findnote.do",{"notebookid":boid},function(result){
		if(result.success){
			var list=result.data;
			//在加载笔记本之前先清空其他笔记本的笔记所对应的节点
			$("#second_side_right ul").empty();	
			//遍历笔记本中的笔记
			$(list).each(function(){
			//把笔记一次加载到节点上
	var li='<li class="online"><a class="unchecked"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+this.cn_note_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button></a><div class="note_menu" tabindex="-1"><dl><dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt><dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt><dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt></dl></div></li>';
			    $("#second_side_right ul").append(li);	
				//绑定每个笔记的所有数据在其对应的节点上
			$("#second_side_right ul li:last").data("note",this);
			
			});
		
		}else{
			alert(result.messager);
		}
		
	});
	
	//console.log("加载普通笔记");
}

/***
 * 查询普通笔记内容
 */
function getNoteDetail(){
	//获取笔记id中被选中的笔记的一个父级标签li
	var note=$("#second_side_right .checked").parent();
	//获取对应标签li上所绑定的数据
	var no=note.data("note");
	//alert(no);
	//console.log(no);
	$.post(basePath+"/note/notebody.do",{"noteid":no.cn_note_id},function(result){
		if(result.success){
			var note=result.data;
			var title=note.cn_note_title;
			var bodys=note.cn_note_body;
			//console.log(title);
			//console.log(bodys);
			//将返回的值添加到对应的id选择器中
			$("#input_note_title").val(title);
			//用html()方法可以将文本的样式属性一起封装
			$("#myEditor").html(bodys);
		}
	});
	
	//console.log("查询普通笔记内容");
}

/***
 * 创建普通笔记
 */
function createNormalNote(checkdata){
	//获取标签id为input_note的值
	 var name=$("#input_note").val();
	var bookid=checkdata.cn_notebook_id;
	
	$.post(basePath+"/note/addnote.do",{"notetitle":name,"notebookid":bookid},function(result){
		if(result.success){	
			//刷新
			getNormalNoteList();
			$(".close,.cancle").trigger("click");
		}else{
			alert(result.messager);
		}
		
	});	
}

/***
 * 更新普通笔记
 */

function updateNormalNote(){
	
	//获取数据
	var note=$("#second_side_right .checked").parent();
	
	var no=note.data("note");
	console.log(no);
	var check=$("#first_side_right .checked").parent();
	var notebook=check.data("notebook");
	var body=$("#myEditor").html();
	var name=$("#input_note_title").val();
	//console.log(body);
	//console.log(notebook);
	
	
	$.post(basePath+"/note/updatanormal.do",{"title":name,"body":body,"noteid":no.cn_note_id,"notebookid":notebook.cn_notebook_id},function(result){
		if(result.success){
			//刷新
			//getNormalNoteList();
		var a='<a class="checked"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+name+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button></a><div class="note_menu" tabindex="-1"><dl><dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt><dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt><dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt></dl></div>';	
		note.html(a);
		
		$("footer div strong").text("保存成功").parent().fadeIn(100);
		//设定时间为1500ms时间到后将执行方法:找到footer标签下的div,延时500ms后淡出
		setTimeout(function(){
			$("footer div").fadeOut(500);
		}, 1500);
		
		//alert("保存成功");
				}else{
					alert(result.messager);
				}
			
	});
	//alert("更新普通笔记");
}

/***
 * 删除普通笔记
 */
function deleteNormalNote(no,checked_li, bi){
	//console.log(no.cn_note_id);
	
	$.post(basePath+"/note/delete.do",{"noteid":no.cn_note_id,"reid":bi},function(result){
		if(result.success){
			checked_li.remove();
			$("#myEditor").html("");
			$("#input_note_title").val("");
			$(".close,.cancle").trigger("click");
		}else{
			alert(result.messager);
		}
		
	});
	
	alert("删除普通笔记");
}

/**
 * 移动笔记
 */
function moveNote(name1){
	//console.log(name1);
	var queding="确 定";
	//console.log(queding);
	if(name1==queding.trim()){	
	var noo1=$("#second_side_right .checked").parent();
	var noo=noo1.data("note");
	//获取选中下拉框的数据
	var a=$("#moveSelect option:selected").val();//data("option");
	var b=noo.cn_note_id;
	//console.log(noo);
	//console.log(a);
	
	$.post(basePath+"/note/movenote.do",{"notebookid":a,"noteid":b},function(result){
		if(result.success){
			$("#myEditor").html("");
			$("#input_note_title").val("");
			getNormalNoteList();
			$(".close,.cancle").trigger("click");
		}else{
			alert(result.messager);
		}

	});
	
	}else{
		var noo1=$("#four_side_right .checked").parent();
		var noo=noo1.data("note");
		//var noo=$("#second_side_right ul li").data("note");
		//获取选中下拉框的数据
		//直接获取下拉选项框option的value值==笔记本id,可以不需要获取绑定值
		var a=$("#replaySelect option:selected").val();//.data("option");
		var b=noo.cn_note_id;
		//console.log(a);
		//console.log(b);
		
		$.post(basePath+"/note/movenote.do",{"notebookid":a,"noteid":b},function(result){
			if(result.success){
				
				getRecycleNoteList();
				$("#noput_note_title").text("");
				$(".close,.cancle").trigger("click");
			}else{
				alert(result.messager);
			}
		
		});	
	}
	//alert("移动笔记");
}

/***
 * 分享笔记
 */
function createShareNote(){
	//在edit.html中找到footer标签下的div下的strong,添加文本后找到其一个父级标签,延时100ms淡入
	$("footer div strong").text("分享成功").parent().fadeIn(100);
	//设定时间为1500ms时间到后将执行方法:找到footer标签下的div,延时500ms后淡出
	setTimeout(function(){
		$("footer div").fadeOut(500);
	}, 1500);
	
	var nos=$("#second_side_right .checked").parent();
	var noo=nos.data("note");
	console.log(noo);
	$.post(basePath+"/share/add.do",{"noteid":noo.cn_note_id},function(result){
		if(result.success){
			
		}else{
			alert(result.messager);
		}
		
	});
}

/***
 * 查询回收站笔记列表
 */
function getRecycleNoteList(){
	var rol=$("#rollback_button").data("rollback");
	 bi=rol.cn_notebook_id;
	 
	console.log(bi);
	
	$.post(basePath+"/note/findnote.do",{"notebookid":bi},function(result){
		if(result.success){
			var list=result.data;
			$("#four_side_right ul").empty();
			if(list.length>0){
			$(list).each(function(){
				
	var li='<li class="disable"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+this.cn_note_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>';
				
			$("#four_side_right ul").append(li);	
				
			$("#four_side_right ul li:last").data("note",this);
			
			});
			}else{
				$("#four_side_right ul").empty();	
			}
			
			
		}else{
			alert(result.messager);
		}

	});
	
}

/***
 * 查看回收站笔记内容
 */
function getRecycleNoteDetail() {
	
	
	//获取回收站笔记id
	var note=$("#four_side_right .checked").parent();
	var no=note.data("note");
	//console.log(no.cn_note_id);
	
	$("#noput_note_title").siblings().remove();
	
	$.post(basePath+"/note/notebody.do",{"noteid":no.cn_note_id},function(result){
		if(result.success){
			 nt=result.data;
			//console.log(nt);
			
			var s='<p>'+nt.cn_note_body+'</p>';
			$("#fifth_side_right div:last").append(s);
			 $("#noput_note_title").text(nt.cn_note_title);

		}
	});
	
	console.log("查看回收站笔记内容");
}

/***
 * 删除回收站笔记
 */
function deleteRecycleNote(){
	var h1=$("#four_side_right .checked").parent();
	var h=h1.data("note");
	//console.log(h)
	$.post(basePath+"/note/deletechedi.do",{"noteid":h.cn_note_id},function(result){
		if(result.success){
			//h.remove();
			getRecycleNoteList();
			$(".close,.cancle").trigger("click");
		}
	
	});
	
	alert("删除回收站笔记");
}

/***
 * 搜索分享笔记列表
 */
function getShareNoteList(currentPage){
	
	//搜索状态为status:1的笔记
	var title=$("#search_note").val();
	console.log(title);
	
	$.post(basePath+"/share/findall.do",{"title":title,"currentPage":currentPage},function(result){
		if(result.success){
			var list=result.data;
			console.log("22222");
			$(list).each(function(){
				console.log("11111");
		var li='<li class="online"><a href="#"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+this.cn_share_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button><div class="time"></div></a></li>';
			
		$("#sixth_side_right ul").append(li);
		$("#sixth_side_right ul li:last").data("share",this);
		
		
		
			});
		}else{
			alert(result.messager);
		}
		
	});
	//alert("搜索分享笔记列表");
}

/***
 * 查询分享笔记内容
 */
function getShareNoteDetail(){
	//获取分享笔记的数据
	var snot=$("#sixth_side_right .checked").parent();
	var sn=snot.data("share");
	//console.log(sn);
	$("#noput_note_title").siblings().remove();
	
	
	var s='<p>'+sn.cn_share_body+'</p>';
	$("#fifth_side_right div:last").append(s);
	 $("#noput_note_title").text(sn.cn_share_title);
	//alert("查询分享笔记内容");
}

/***
 * 收藏分享笔记
 */
function likeShareNote(){
	var snot=$("#sixth_side_right .checked").parent();
	var snote=snot.data("share");
	var noteb=$("#like_button").data("like_button");
	console.log(snote);
	console.log(noteb);
	$.post(basePath+"/note/addfanote.do",{"snoteid":snote.cn_note_id,"fanotebookid":noteb.cn_notebook_id},function(result){
		if(result.success){
			$(".close,.cancle").trigger("click");
			$("footer div strong").text("收藏成功").parent().fadeIn(100);
			//设定时间为1500ms时间到后将执行方法:找到footer标签下的div,延时500ms后淡出
			setTimeout(function(){
				$("footer div").fadeOut(500);
			}, 1500);
			
		}
		
		
	});
	
	
	//alert("收藏分享笔记");
}

/***
 * 加载收藏笔记
 */
function getLikeNoteList(likeNoteId){
	var a=$("#like_button").data("like_button");
	var notenookid=a.cn_notebook_id;
	$.post(basePath+"/note/findnote.do",{"notebookid":notenookid},function(result){
		if(result.success){
			var list=result.data;
			$(list).each(function(){
				
		var li='<li class="idle"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+this.cn_note_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button></a></li>';		
			$("#seventh_side_right ul").append(li);
			$("#seventh_side_right ul li:last").data("favnote",this);
				
			$(".close,.cancle").trigger("click");
			});
			
			
		}
		
	});
	
	
	//alert(a.cn_notebook_id);
}

/***
 * 查看收藏笔记内容
 */
function getLikeNoteDetail(noteId) {
	
	var fano=$("#seventh_side_right .checked").parent();
	var fa=fano.data("favnote");
	console.log(fa);
	//console.log("查看收藏笔记内容");
	
	
	var s='<p>'+fa.cn_note_body+'</p>';
	$("#fifth_side_right div:last").append(s);
	 $("#noput_note_title").text(fa.cn_note_title);
}

/***
 * 删除收藏笔记
 */
function deleteLikeNote(){
	var aa= $("#seventh_side_right .checked").parent();
	var bb=aa.data("favnote");
	console.log(bb);
	
	$.post(basePath+"/note/deletechedi.do",{"noteid":bb.cn_note_id},function(result){
		if(result.success){
			aa.remove();
			$("footer div strong").text("删除成功").parent().fadeIn(100);
			setTimeout(function(){$("footer div").fadeOut(500)},1500);
			$("#noput_note_title").text("");
			$("#noput_note_title").siblings().remove();
			
			$(".close,.cancle").trigger("click");
		}else{
			alert(result.messager);
		}
		
	});
	//alert("删除收藏笔记");
}

/***
 * 加载本用户参加活动笔记列表
 */
function getNoteActivityList(noteBookId){
	var a=$("#action_button").data("action_button");
	alert(a.cn_notebook_id);
	
	$.post(basePath+"/note/findacnote.do",{},function(result){
		if(result.success){
			var list=result.data;
		$(list).each(function(){
	var li='<li class="offline"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+this.cn_note_title+'</a></li>';		
			$("#eighth_side_right div ul").append(li);
			$("#eighth_side_right div ul li:last").data("ACNote",this);
		});
		}
	});
}

/***
 * 查询参加活动的笔记内容
 */
function getActivityNoteDetail() {

	var ac=$("#eighth_side_right .checked").parent().data("ACNote");
	console.log(ac);
	$("#noput_note_title").text(ac.cn_note_title);
	var p='<p>'+ac.cn_note_body+'</p>';
	$("#noput_note_title").parent().append(p);
	
	
	console.log("查询参加活动的笔记内容");
}
