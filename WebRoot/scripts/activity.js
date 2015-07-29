/***
 * 获得活动列表
 */
function getActivityList(){
//加载后绑定每个活动笔记本的id到到对应的标签
	$.post(basePath+"/activity/findall.do",{},function(result){
		//console.log("111111111111");
		
		if(result.success){
			
			var list=result.data;
			$(list).each(function(){
				//颜色数组
				var arr=["bg-primary","bg-warning","bg-inverse","bg-danger"];
				//产生0-3的随机数
				var x=Math.random()*4;
				//将随机数向下取整
				x=Math.floor(x);
				//console.log(x);
				
				var id=this.cn_activity_id;
				var nid=id-1;
				//console.log(id);
				//console.log(nid);
				if(nid==0){
		var d0='<div id="contentfeeds'+nid+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+arr[x]+'"><p class="lead"><a href="activity_detail.html#'+nid+'">'+this.cn_activity_title+'</a></p></div><p class="text-muted">'+this.cn_activity_body+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+this.cn_activity_end_time+'</small></div></div></div>';		
				$("#col_0").append(d0);
				$("#col_0 #contentfeeds"+nid+" a").data("div",this);
				}
				if(nid==1){
		var d1='<div id="contentfeeds'+nid+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+arr[x]+'"><p class="lead"><a href="activity_detail.html#'+nid+'">'+this.cn_activity_title+'</a></p></div><p class="text-muted">'+this.cn_activity_body+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+this.cn_activity_end_time+'</small></div></div></div>';		
				$("#col_1").append(d1);
				$("#col_1 #contentfeeds"+nid+" a").data("div",this);
				}
				if(nid==2){
	    var d2='<div id="contentfeeds'+nid+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+arr[x]+'"><p class="lead"><a href="activity_detail.html#'+nid+'">'+this.cn_activity_title+'</a></p></div><p class="text-muted">'+this.cn_activity_body+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+this.cn_activity_end_time+'</small></div></div></div>';		
				$("#col_2").append(d2);
				$("#col_2 #contentfeeds"+nid+" a").data("div",this);
				}
				
				
				if(nid%3==0&&nid!=0){
	 var d3='<div id="contentfeeds'+nid+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+arr[x]+'"><p class="lead"><a href="activity_detail.html#'+nid+'">'+this.cn_activity_title+'</a></p></div><p class="text-muted">'+this.cn_activity_body+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+this.cn_activity_end_time+'</small></div></div></div>';					
	 			$("#col_0").append(d3);
	 			$("#col_0 #contentfeeds"+nid+" a").data("div",this);
				}
				if(nid%3==1&&nid!=1){
     var d4='<div id="contentfeeds'+nid+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+arr[x]+'"><p class="lead"><a href="activity_detail.html#'+nid+'">'+this.cn_activity_title+'</a></p></div><p class="text-muted">'+this.cn_activity_body+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+this.cn_activity_end_time+'</small></div></div></div>';					
				$("#col_1").append(d4);
				$("#col_1 #contentfeeds"+nid+" a").data("div",this);
				}
				if(nid%3==2&&nid!=2){
	 var d5='<div id="contentfeeds'+nid+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+arr[x]+'"><p class="lead"><a href="activity_detail.html#'+nid+'">'+this.cn_activity_title+'</a></p></div><p class="text-muted">'+this.cn_activity_body+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+this.cn_activity_end_time+'</small></div></div></div>';					
				$("#col_2").append(d5);
				$("#col_2 #contentfeeds"+nid+" a").data("div",this);
				}
			
			});
			
		}else{
			alert(result.messager);
		}
		
		
	});
	//alert("获得活动列表");
}

/***
 * 查询指定活动下已参加活动的笔记列表
 */
function getNoteActivitys(da,morebutrun){
//在此方法中通过传递对应活动笔记本的Id来显示该笔记本对应的内容.click()
	
	$.post(basePath+"/activity/findallacbook.do",{"acnotebookid":da,"fenye":morebutrun},function(result){
		//console.log(result);	
		if(result.success){
			//console.log("4444444");
				var list=result.data;
				//console.log(this.cn_note_activity_title);
				$(list).each(function(){
					var li='<li class="online"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+this.cn_note_activity_title+'<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up"><i class="fa fa-thumbs-o-up"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_down"><i class="fa fa-thumbs-o-down"></i></button><button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button></a></li>';			
					$("#first_action ul").append(li);
					$("#first_action ul li:last").data("acnote",this);
					
				});
		}
	});	
}

/***
 * 查询活动笔记内容
 */
function getNoteActivityDetail(){
var acnote=$("#first_action .checked").parent();	
var acn=acnote.data("acnote");
	var t='<h4><strong>笔记标题: </strong>'+acn.cn_note_activity_title+'</h4>';
	$("#content_body").append(t);
	//console.log(sa);
	var body='<p>'+acn.cn_note_activity_body+'</p>';
	$("#content_body").append(body);
	
	
}

/***
 * 查询可选择的笔记本
 */
function getSelectNoteBook(){
	$("#select_notebook ul").empty();
	$.post(basePath+"/notebook/findNormal.do",{},function(result){
		if(result.success){
			 list=result.data;
			$(list).each(function(){
			//alert(this.cn_notebook_name);	
	var li = '<li class="online"><a ><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+this.cn_notebook_name+'</a></li>';					
			//将li插入到笔记本列表ul中
	/**
	 * append() 方法在被选元素的结尾（仍然在内部）插入指定内容。
	 */
			$("#select_notebook ul").append(li);
			//给li绑定笔记本数据,将来修改,删除笔记本,或者根据笔记本查询笔记时要使用这个绑定数据
			$("#select_notebook ul li:last").data("acnotebook",this);	
			});
		}else{
			alert(result.messager);
		}
		
	});
	//alert("查询可选择的笔记本");
}

/***
 * 查询可选择的笔记
 */
function getSelectNoteList(){
	//获取选中笔记本对应的标签
	var checkq=$("#select_notebook .checked").parent();
	//在标签上获取之前绑定的笔记本数据
	var checkdataq=checkq.data("acnotebook");
	//获取对应笔记本的id,赋值给一个公共变量
	 boid=checkdataq.cn_notebook_id;
		$.post(basePath+"/note/findnote.do",{"notebookid":boid},function(result){
		if(result.success){
			var list=result.data;
			//在加载笔记本之前先清空其他笔记本的笔记所对应的节点
			$("#select_note ul").empty();	
			//遍历笔记本中的笔记
			$(list).each(function(){
			//把笔记一次加载到节点上
	var li='<li class="online"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+this.cn_note_title+'</a></li>';
			    $("#select_note ul").append(li);	
				//绑定每个笔记的所有数据在其对应的节点上
			$("#select_note ul li:last").data("acnote",this);
			
			});
		
		}else{
			alert(result.messager);
		}
		
	});
	
	//alert("查询可选择的笔记");
}

/***
 *	将用户选择的笔记参加活动
 */
function createNoteActivity(d){
	var acn=$("#select_note .checked").parent();
	var acno=acn.data("acnote");//选中参加活动的普通笔记
	//console.log(acno);
	//console.log(d);
	$.post(basePath+"/activity/addacnote.do",{"acnoteid":acno.cn_note_id,"acbookid":d},function(result){
		if(result.success){
			$("footer div strong").text("参加活动成功").parent().fadeIn(100);
			setTimeout(function(){$("footer div").fadeOut(500);},1500);
		}else{
			alert(result.messager);
		}
		
		
	});	
	alert("将用户选择的笔记参加活动");
	$('.close,.cancle').trigger('click');
}

/***
 *	收藏活动笔记
 */


function likeActivityNote(acn) {
	
//	var acnote=$("#first_action .checked").parent();	
//	var acn=acnote.data("acnote");
	
	console.log(acn.cn_note_activity_id);//笔记id
	
	$.post(basePath+"/activity/shoucangAC.do",{"acnoteid":acn.cn_note_activity_id},function(){
		  $("footer div strong").text("收藏成功").parent().fadeIn(100);
		  setTimeout(function(){$("footer div").fadeOut(500);},1500);
		
	});
	
	//alert("收藏活动笔记");
}

/***
 *	顶笔记
 */
function up() {
	var acnote=$("#first_action .checked").parent();	
	var acn=acnote.data("acnote");
	var acnoteid=acn.cn_note_activity_id;
	var upn=acn.cn_note_activity_up+1;
	
	
	console.log(upn);
	$.post(basePath+"/activity/updateac.do",{"acnoteid":acnoteid,"updown":upn,"biaoji":"up"},function(result){
		if(result.success){
			$("footer div strong").text("顶成功").parent().fadeIn(100);
			setTimeout(function(){$("footer div").fadeOut(500);},1500);
		}
	});
	
	//console.log(acnoteid);
	
	
	//alert("顶笔记");
}

/***
 *	踩笔记
 */
function down(acn) {
	var acnote=$("#first_action .checked").parent();	
	var acn=acnote.data("acnote");
	var acnoteid=acn.cn_note_activity_id;
	var downn=acn.cn_note_activity_down+1;
	
	$.post(basePath+"/activity/updateac.do",{"acnoteid":acnoteid,"updown":downn,"biaoji":"down"},function(result){
		if(result.success){
			$("footer div strong").text("踩成功").parent().fadeIn(100);
			setTimeout(function(){$("footer div").fadeOut(500);},1500);
		}
		
	});
	
	
	console.log("222222"+acn);
	
	//alert("踩笔记");
}
