/**********公共函数**********/
//格式化字符串，转义<和>
function formate_name(e){
	e=e.replace(/</g,'&lt;');
	e=e.replace(/>/g,'&gt;');
	return e;
}
//去掉空格
function check_null(s){
	s=s.replace(/ /g,'');
	s=s.length;
	return s;
}


/**********HTML初始化时直接调用的函数**********/
//获取笔记本列表,edit.html初始化时调用
function get_nb_list(){
	loadNormalNoteBook();
}

//获取特殊笔记本列表,edit.html初始化时调用
function get_spnb_list(){
	loadSpecialNoteBook();
}

//获取活动列表,activity.html初始化时调用
function activity_list(){
	getActivityList();
}

//$(document).on("click", "#action_part .col-sm-4 div a", function() {
//		var da=$(this).data("div");
//			
//		console.log("111111111");
//		console.log(da);
//	});
	


//获取活动页面参加活动笔记列表,activity_detail.html初始化时调用
function get_activity_list(){
	var param=window.location.hash;
	global_ac_id=param.replace(/#/,'');
	
	var da=parseInt(global_ac_id)+1;
	//console.log("22222222");
	//console.log(da);
	//console.log("33333333");
	
	$("#more_activity_note").data("morebutun",1);
	$("#action_part_1").data("ddd",da);
	getNoteActivitys(da,1);


	
}



/**********HTML初始化后为其按钮绑定函数**********/
//注册事件
$(function(){
	//显示用户名
	
	$(".profile-username").text(getCookie("userName"));
	
	
	//----关闭，取消
	$(document).on("click", ".close,.cancle", function() {
		//清空弹出页面中输入的内容
		$('#input_notebook,#input_note').val('');
		//隐藏弹出页面
        $('.modal.fade.in').hide();
        //隐藏弹出的div背景图片
        $('.opacity_bg').hide();
    }),
	
    
    /***********笔记本模块************/
	//----单击笔记本,查询笔记
	$(document).on("click", "#pc_part_1 li", function() {
		/**
		 * 1.通过选择器获得的对象都是jQuery对象.
		 * 2.on()方法是绑定一个事件的处理函数到一个jQuery对象上
		 * 3.当id为pc_part_1标签下的li标签获得一个点击事件时,会产生事件冒泡,当前的li标签会首先获得
		 * 处理事件的机会
		 * 
		 */
		//var a=$(this).data("notebook").cn_notebook_name;
		/**
		 * this指代当前的jQuery对象
		 */
		//alert(a);
		$('#pc_part_2,#pc_part_3').show();
		//$('#pc_part_2 ul').empty();
		$('#pc_part_4,#pc_part_5,#pc_part_6,#pc_part_7,#pc_part_8').hide();
		//回收站,收藏,活动选中的样式移除
		$('#rollback_button,#like_button,#action_button').removeClass('clicked');
		//获取当前元素的所有兄弟元素,但不包括自己,this指代当前点击的li
		//$(this).siblings('li')是选中其他的li
		$(this).siblings('li').children('a').removeClass('checked');
		//选中当前li元素中的元素a
		$(this).children('a').addClass('checked');
		
		
		
		//console.log(checkq);
		//获取笔记本下的笔记列表
		getNormalNoteList();
    }),
    
	//----打开创建笔记本界面
	$(document).on("click", "#add_notebook", function() {
		//在这个div里,显示alert_notebook.html的内容
		//并且在显示之后调用function
		$('#can').load('./alert/alert_notebook.html', function(){
			$('#input_notebook').focus();
			
		});
		$('.opacity_bg').show();
    }),
    
	//----创建笔记本
	$(document).on("click", "#modalBasic .btn.btn-primary.sure", function() {
		
		addNoteBook();
    }),
    
	//----双击,打开修改笔记本界面
	$(document).on("dblclick", "#pc_part_1 li:gt(0)", function() {
		
		var b=$(this).data("notebook").cn_notebook_id;
		$("#pc_part_1 li:gt(0)").data("id",b);
		//弹出文本框的页面
		$('#can').load('./alert/alert_rename.html',function(){
			//弹出页面的文本框赋予焦点
			$('#input_notebook_rename').focus();
		});
		$('.opacity_bg').show();
    }),
    
    //修改笔记本
	$(document).on("click",'#modalBasic_4 .sure',function() {
		//获取新名称
		var newname=$('#input_notebook_rename').val();
		//获取选中的li,取得绑定的笔记本数据,单机选中对象时其对应的class属性变为checked
		var checked_li = $("#first_side_right .checked").parent();
		var notebook = checked_li.data("notebook");
		//校验新名称非空,新名称不能与就名称相同
		if(newname && newname!=notebook.cn_notebook_name) {
			//调用notebook.js中的方法
			updateNoteBook(newname,notebook,checked_li);
		}
	
	});
    
	//----打开删除笔记本界面
	$(document).on("click", "#first_side_right .btn_delete", function() {
		$('#can').load('./alert/alert_delete_notebook.html');
		$('.opacity_bg').show();
    }),
	
    //----删除笔记本
	$(document).on('click','#modalBasic_6 .btn.btn-primary.sure',function(){
		//删除
		//取得id为first_side_right标签下class属性为:checked的标签的"唯一"父元素标签
		var checked_li = $("#first_side_right .checked").parent();
		var obj = checked_li.data("notebook");
		var bookid=obj.cn_notebook_id;
		
		deleteNoteBook(bookid,checked_li);
	});
    
	
	
	/***********笔记模块************/
	//----点击笔记
	$(document).on("click", "#pc_part_2 li", function() {
		$(this).siblings('li').children('a').removeClass('checked');
		$(this).children('a').addClass('checked');
		
		
		getNoteDetail();
    }),
    
	//----打开创建笔记界面
	$(document).on("click", "#add_note", function() {
		$('#can').load('./alert/alert_note.html',function() {
			$('#input_note').focus();
		});
		$('.opacity_bg').show();
    }),
    
	//----创建笔记
	$(document).on("click", "#modalBasic_2 .btn.btn-primary.sure", function() {
		var check=$("#first_side_right .checked").parent();
		var checkdata=check.data("notebook");
		
		
		//保存
		createNormalNote(checkdata);
    }),
    
    //----保存笔记内容
    $(document).on("click","#save_note", function() {
		//修改

		updateNormalNote();
    }),
    
    //----点击笔记下拉按钮
	$(document).on("click", ".btn_slide_down", function() {
		$(this).parents('li').children('.note_menu').addClass('note_menu_show').mouseleave(function(){
			$(this).removeClass('note_menu_show');
		});
    }),
    
    //----打开删除笔记界面
	$(document).on("click", "#second_side_right .btn_delete", function() {
		$('#can').load('./alert/alert_delete_note.html');
		$('.opacity_bg').show();
    }),
    
	//----确认删除
	$(document).on('click','#modalBasic_7 .btn.btn-primary.sure', function() {
		var checked_li = $("#second_side_right .checked").parent();
		
		//var no1=$("#second_side_right .checked").parent();
		var no=checked_li.data("note");
		var rid=$("#rollback_button").data("rollback");
		
		var bi=rid.cn_notebook_id;
		
		deleteNormalNote(no,checked_li,bi);
	});
    
	//----打开移动笔记界面
	$(document).on("click", "#second_side_right .btn_move", function() {
		$('#can').load('./alert/alert_move.html',function(){
			// 获取笔记本列表
			setNoteBookToSelect();
			
					});
		$('.opacity_bg').show();
    }),
    
	//----确认移动
	$(document).on('click','#modalBasic_11 .btn.btn-primary.sure',function(){
		var name=$(this).html();
		
		var name1=name.trim();
		moveNote(name1);
	});
    
	//----分享笔记
	$(document).on("click", "#second_side_right .btn_share", function() {
		//延时600ms后将分享按钮标签谈出
		$(this).fadeOut(600);
		createShareNote();
    }),
    
    
    /***********回收站模块************/
	//----点击回收站按钮
	$(document).on("click", "#rollback_button", function() {
		$('#pc_part_2,#pc_part_3,#pc_part_6,#pc_part_7,#pc_part_8').hide();
		$('#pc_part_4,#pc_part_5').show();
		$('#first_side_right li a').removeClass('checked');
		$('#like_button,#action_button').removeClass('clicked');
		$(this).addClass('clicked');
		//每次加载前先清空所有li
		//$('#pc_part_4 ul').empty();
		$("#noput_note_title").siblings().remove();
		$("#noput_note_title").text("");
		getRecycleNoteList();
    }),
    
	//----点击回收站笔记
	$(document).on("click", "#pc_part_4 li", function() {
		$(this).siblings('li').children('a').removeClass('checked');
		$(this).children('a').addClass('checked');
		getRecycleNoteDetail();
    }),
    
	//----点击回收站恢复按钮
	$(document).on("click", "#four_side_right .btn_replay", function() {
		$('#can').load('./alert/alert_replay.html',function(){
			setNoteBookToSelect();
			$('#replaySelect').focus();
		});
		$('.opacity_bg').show();
    }),
    
	//----确认恢复
	$(document).on('click','#modalBasic_3 .btn.btn-primary.sure', function(){
		var name=$(this).text();
		//console.log(name);
		moveNote(name);
	});

	//----点击回收站删除按钮
	$(document).on("click", "#four_side_right .btn_delete", function() {
		$('#can').load('./alert/alert_delete_rollback.html');
		$('.opacity_bg').show();
    }),
    
	//----确认删除
	$(document).on('click','#modalBasic_10 .btn.sure', function() {
		
		
		
		deleteRecycleNote();
	});
    
	
	/***********搜索笔记模块************/
	//----搜索笔记
	$(document).on("keyup", "body", function(e) {
		if($('#search_note').is(':focus')&&(e.keyCode==108||e.keyCode==13)){
			var m=$('#search_note').val();
			var n=m.replace(/ /g,'');
			var a=n.length;
			if(a!=0){
				$("#pc_part_2,#pc_part_4,#pc_part_3,#pc_part_8,#pc_part_7").hide();
				$("#pc_part_6,#pc_part_5").show();
				$("#sixth_side_right ul").empty();
				//将当前页1绑定到[更多]按钮上
				 $("#more_note").data("currentPage",1);
				 
				getShareNoteList(1);
			}
		}
    }),
    
	//----更多搜索笔记
	$(document).on("click", "#more_note", function(){
		
		var n=$("#more_note").data("currentPage");
		//将当前页加1绑定到按钮上
		 $("#more_note").data("currentPage",n+1);
		getShareNoteList(n+1);
    }),
    
	//----点击搜索笔记
	$(document).on("click", "#sixth_side_right li", function() {
		$(this).siblings('li').children('a').removeClass('checked');
		$(this).children('a').addClass('checked');
		getShareNoteDetail();
    }),
    
	//----收藏分享笔记
	$(document).on("click", "#pc_part_6 .btn_like", function() {
		$('#can').load('./alert/alert_like.html', function(){
			$('#modalBasic_5 .btn.btn-primary.sure').click(function(){
				
				likeShareNote();
			});
		});
		$('.opacity_bg').show();
    }),
    
    
    /***********注册收藏笔记相关操作************/
	//----点击收藏按钮
	$(document).on("click", "#like_button", function() {
		$('#pc_part_2,#pc_part_3,#pc_part_4,#pc_part_6,#pc_part_8').hide();
		$('#pc_part_7,#pc_part_5').show();
		$('#first_side_right li a').removeClass('checked');
		$('#rollback_button,#action_button').removeClass('clicked');
		$(this).addClass('clicked');
		//每次加载前先清空所有li
		$('#pc_part_7 ul').empty();
		//清空预览笔记内容
		$("#noput_note_title").siblings().remove();
		
		
		
		getLikeNoteList();
    }),
	
	//----点击收藏笔记
	$(document).on("click", "#pc_part_7 li", function() {
		$(this).siblings('li').children('a').removeClass('checked');
		$(this).children('a').addClass('checked');
		//清空预览笔记内容
		$("#noput_note_title").siblings().remove();
		$("#noput_note_title").text("");
	
		getLikeNoteDetail();
    }),
    
	//----点击取消收藏
	$(document).on("click", "#pc_part_7 li .btn_delete", function() {
		$('#can').load('./alert/alert_delete_like.html');
		$('.opacity_bg').show();
    }),
    
	//----确认取消
	$(document).on('click','#modalBasic_9 .btn.btn-primary.sure', function(){
		deleteLikeNote();
		
	});
    
	
	/***********参加活动笔记模块************/
	//----点击参加活动笔记按钮
	$(document).on("click", "#action_button", function() {
		$('#pc_part_2,#pc_part_3,#pc_part_6,#pc_part_7,#pc_part_4').hide();
		$('#pc_part_8,#pc_part_5').show();
		$('#first_side_right li a').removeClass('checked');
		$('#rollback_button,#like_button').removeClass('clicked');
		$(this).addClass('clicked');
		//$("#eighth_side_right ul").empty();
		getNoteActivityList();
    }),
    
    //----点击参加活动笔记
	$(document).on("click", "#pc_part_8 li", function() {
		$(this).siblings('li').children('a').removeClass('checked');
		$(this).children('a').addClass('checked');
		$("#noput_note_title").siblings().remove();
		
		getActivityNoteDetail();
    }),
	//------绑定活动笔记

    
    
    
    /***********活动模块************/
	//----更多活动笔记
	$(document).on("click", "#more_activity_note", function() {
			 
		var n=$("#more_activity_note").data("morebutun");
		$("#more_activity_note").data("morebutun",n+1);
		var da=$("#action_part_1").data("ddd");
		//console.log(n+"1111111111");
		//console.log(da+"22222222222");
		getNoteActivitys(da,n+1);
    });
	
	
	//----点击笔记(活动页面)
	$(document).on("click", "#action_part_1 li", function() {
		$('#rollback_button').removeClass('clicked');
		$(this).siblings('li').children('a').removeClass('checked');
		$(this).children('a').addClass('checked');
		$("#content_body").empty();
		getNoteActivityDetail();
    }),
	
	//----点击参加活动（活动页面）
	$(document).on("click", "#join_action", function() {
		//显示选择笔记本,笔记的窗
		$('#modalBasic_15,.opacity_bg').show();
		//$('#select_notebook ul').empty();
		//$('#select_note ul').empty();
		getSelectNoteBook();
    }),
    
	//----准备选择参加活动笔记（活动页面）
	$(document).on("click", "#select_notebook li", function() {
		$(this).siblings('li').children('a').removeClass('checked');
		$(this).children('a').addClass('checked');
		//$('#select_note ul').empty();
		getSelectNoteList();
    }),
    
	//----选择笔记（活动页面）
	$(document).on("click", "#select_note li", function() {
		$(this).siblings('li').children('a').removeClass('checked');
		$(this).children('a').addClass('checked');
    }),

	//----确认选择的笔记（活动页面）
	$(document).on("click", "#modalBasic_15 .btn.btn-primary.sure", function() {
		//var get_notename=$('#select_note li a.checked').text();
		//$('.close,.cancle').trigger('click');
		var d=$("#action_part_1").data("ddd");
		//console.log("5566");
		//console.log(d);//活动笔记本id
		createNoteActivity(d);
    }),
    
	//----点击收藏（活动页面）
	$(document).on('click',"#first_action .btn_like", function() {
		var acnote=$("#first_action .checked").parent();	
		var acn=acnote.data("acnote");
		//console.log(acn);
		
		likeActivityNote(acn);
    }),
	
	//----顶笔记（活动页面）
	$(document).on("click", "#first_action .btn_up", function() {
		
		up();
    }),
    
	//----踩笔记（活动页面）
	$(document).on("click", "#first_action .btn_down", function() {
		
		down();
    });
	
});