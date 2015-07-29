package tarena.com.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tarena.com.dao.ActivityMapper;
import tarena.com.dao.ActivityNoteMapper;
import tarena.com.dao.NoteBookMapper;
import tarena.com.dao.NoteMapper;
import tarena.com.dao.ShareMapper;
import tarena.com.dao.UserMapper;
import tarena.com.entity.Activity;
import tarena.com.entity.ActivityNote;
import tarena.com.entity.Note;
import tarena.com.entity.NoteBook1;
import tarena.com.entity.Result;
import tarena.com.entity.ShareNote;
import tarena.com.entity.User;
import tarena.com.service.ActivityService;
import tarena.com.service.LoginService;
import tarena.com.service.NoteBookService;
import tarena.com.service.NoteService;
import tarena.com.service.ShareNoteService;

public class TestCase {
private String cfg="spring.xml";
ApplicationContext ctx=new ClassPathXmlApplicationContext(cfg);
/**
 * findByName
 */
//@Test
public void Test1(){
	UserMapper a=ctx.getBean(UserMapper.class);
	User user=a.findByName("pc");
	System.out.println(user);
}
/**
 * USERsave
 */
//@Test
public void Test2(){
	UserMapper a=ctx.getBean(UserMapper.class);
	User u=new User();
	u.setCn_user_id(UUID.randomUUID().toString());
	u.setCn_user_name("zhangsanfeng");
	u.setCn_user_token("张三丰");
	u.setCn_user_password("12345");
	u.setCn_user_desc("");
	a.save(u);
	System.out.println(u);
}
/**
 * notebook save
 */
//@Test
public void Test3(){
	NoteBookMapper b=ctx.getBean(NoteBookMapper.class);
	NoteBook1 book=new NoteBook1();
	book.setcn_notebook_id(UUID.randomUUID().toString());
	book.setcn_notebook_name("PUSH");
	book.setcn_notebook_type_id("4");
	book.setcn_user_id("0c395149-b749-43cb-8d34-a89a67e5ff2a");
	book.setcn_notebook_createtime("2014-9-8 15:30:54");
	
	b.saveNote(book);
	System.out.println(book);
}
/**
 * LoginService  addUser
 */
//@Test
public void Test4(){
	LoginService n=ctx.getBean(LoginService.class);
	User u=new User();
	u.setCn_user_id(UUID.randomUUID().toString());
	u.setCn_user_name("张无忌");
	u.setCn_user_password("12345");
	u.setCn_user_desc("张翠山");
	u.setCn_user_token("45");
	n.addUser(u);
}
//@Test
public void D(){
	LoginService l=ctx.getBean(LoginService.class);
	
	UserMapper u=ctx.getBean(UserMapper.class);
	User user=new User();
	user.setCn_user_password("12345");
	user.setCn_user_name("luowei");
	u.updatapassword(user);
	System.out.println("ok");
}
//@Test
public void E(){
	NoteBookMapper n=ctx.getBean(NoteBookMapper.class);
	
	List<NoteBook1> list=n.findNoteBook("0c395149-b749-43cb-8d34-a89a67e5ff2a");
	
	for(NoteBook1 li:list){
		System.out.println(li);
	}
}
//@Test
public void F(){
	NoteBookService k=ctx.getBean(NoteBookService.class);
	List<NoteBook1> list=k.findNormalNoteBook("0c395149-b749-43cb-8d34-a89a67e5ff2a");
	
	System.out.println(list);
}
//@Test
public void G(){
	NoteBookMapper n=ctx.getBean(NoteBookMapper.class);
	List<NoteBook1> list=n.findSpecialNoteBook("5c7e6cbe-dc49-4a0f-beca-2c170da3198a");
	System.out.println(list);
}
/**
 * 修改
 */
//@Test
public void h(){
	NoteBookMapper n=ctx.getBean(NoteBookMapper.class);
	NoteBook1 notebook=new NoteBook1();
	
	notebook.setcn_notebook_id("c3ef2b8c-5e9c-49b8-8428-d3b943945f54");
	notebook.setcn_notebook_name("蛤蟆神功9");
	n.updatename(notebook);
	System.out.println("OK");
}
/**
 * 添加笔记本
 */
//@Test
public void k(){
	NoteBookService n=ctx.getBean(NoteBookService.class);
	
	NoteBook1 book=n.AddNoteBookdd("蛤蟆神功", "10101", "111222");
	
	System.out.println(book);
}

/**
 * 重命名service
**/
//@Test
public void l(){
	NoteBookService n=ctx.getBean(NoteBookService.class);
	
	n.updateNoteBook("蛤蟆神功5","c3ef2b8c-5e9c-49b8-8428-d3b943945f54");
	System.out.println("ok");
}
/**
 * 彻底删除笔记本
 */
//@Test
public void dd(){
	NoteBookMapper dd=ctx.getBean(NoteBookMapper.class);
	NoteBook1 notebook=new NoteBook1();
	notebook.setcn_notebook_id("8b461d7f-227c-4b8e-9419-211b85c06acb");
	dd.deletenotebook(notebook);
	System.out.println("ok");
}
/**
 * 创建笔记
 */
//@Test
public void NN(){
	NoteMapper dd=ctx.getBean(NoteMapper.class);
	Note note=new Note();
	note.setCn_note_title("武大郎大战西门庆");
	note.setCn_note_id(UUID.randomUUID().toString());
	note.setCn_note_body("222222222222222222");
	note.setCn_note_status_id(UUID.randomUUID().toString());
	note.setCn_note_type_id(UUID.randomUUID().toString());
	note.setCn_notebook_id("6fbe697e-84b8-40f5-aa61-317de8609a1d");
	note.setCn_user_id("0c395149-b749-43cb-8d34-a89a67e5ff2a");
	
	dd.addnote(note);
	System.out.println("ok");
	
	
}
/**
 * 创建笔记service
 */
//@Test
public void hn(){
	
	NoteService nn=ctx.getBean(NoteService.class);
	nn.addnote("武大郎大战西门庆", "6fbe697e-84b8-40f5-aa61-317de8609a1d", "0c395149-b749-43cb-8d34-a89a67e5ff2a");
	
	System.out.println("ok");
}

/**
 * 查询笔记
 */
//@Test
public void cc(){
	NoteMapper cc=ctx.getBean(NoteMapper.class);
	List list=cc.findnote("3456c91b-e58b-4136-b217-e39b31248629");
	System.out.println(list);
}

//@Test
public void ff(){
	NoteService vv=ctx.getBean(NoteService.class);
	List<String> list=vv.findnote("3456c91b-e58b-4136-b217-e39b31248629");
	System.out.println(list);
}
//删除笔记到回收站
//@Test
public void ddd(){
	NoteService dd=ctx.getBean(NoteService.class);
	Note note=new Note();
	
	note.setCn_note_title("空军2号");
	note.setCn_notebook_id("692a1939-a63f-4ed4-a4b0-732a08382e77");
	
	note.setCn_note_id("3704d168-57f8-4831-9edc-710afc5126e0");
	Date date=new Date();
	long time=date.getTime();
	note.setCn_note_last_modify_time(time);
	//Note n=dd.deletenote(note, "e5f6c286-a0b9-48ac-9265-98db5cc8540c");
	//System.out.println(n);
}
//删除回收站笔记
//@Test
public void ee(){
	NoteMapper dd=ctx.getBean(NoteMapper.class);
	dd.deletenote("712c8d26-1350-4402-92c0-3befb494bf54");
	System.out.println("ok");
}
//添加分享笔记
//@Test
public void rr(){
	ShareMapper dd=ctx.getBean(ShareMapper.class);
	ShareNote note=new ShareNote();
	note.setCn_note_id("81135ce4-1d92-4d0b-abf8-0307e0a4b191");
	note.setCn_share_body("摧毁世界");
	note.setCn_share_id(UUID.randomUUID().toString());
	note.setCn_share_title("带土");
	dd.addSharenote(note);
	System.out.println("ok");
}
//添加分享笔记sservice
//@Test
public void rrr(){
	ShareNoteService dd=ctx.getBean(ShareNoteService.class);
	
	dd.addsharenote("81135ce4-1d92-4d0b-abf8-0307e0a4b191");
	System.out.println("ok");
}
//查询说有分享笔记
/**
 * 分页查询#########################################3
 */
//@Test
public void aaaa(){
	ShareMapper dd=ctx.getBean(ShareMapper.class);
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("title",1);
	map.put("begin", 1);
	map.put("pagesize", 3);
	List<ShareNote> list=dd.findAll(map);
	System.out.println(list);
	for(ShareNote s:list){
		System.out.println(s);
	}
}
//查询说有分享笔记service
//@Test
public void bb(){
//	ShareNoteService dd=ctx.getBean(ShareNoteService.class);
//	List<ShareNote> list=dd.findAll("阿尔萨斯");
//	for(ShareNote s:list){
//		System.out.println(s);
	//}
}
//收藏笔记
//@Test
public void sc(){
	NoteService dd=ctx.getBean(NoteService.class);
	ShareNote snote =new ShareNote();
	snote.setCn_note_id("8def672b-f6da-4b53-83bf-d0b45943b267");
	snote.setCn_share_body("我已经沉睡了000年");
	snote.setCn_share_id("491010b0-6319-4a3f-a788-7790c579f634");
	snote.setCn_share_title("阿尔萨斯");
	
	//dd.addfavoritenote(snote,"0a6ecea9-3157-4e42-a0e4-6d8654a2fa67","abe94124-a8e8-47f1-8fdb-ea8a030c0148");
	System.out.println("ok");
	
}
//@Test
public void ccc(){
	ShareMapper dd=ctx.getBean(ShareMapper.class);
	ShareNote ss=dd.findone("7751d1fc-0811-46a5-8a26-2afa09a2af14");
	System.out.println(ss);
}
//@Test
public void uu(){
	ShareMapper dd=ctx.getBean(ShareMapper.class);
	ShareNote snote =new ShareNote();
	snote.setCn_note_id("81135ce4-1d92-4d0b-abf8-0307e0a4b191");
	snote.setCn_share_body("我已经沉睡了1000年");
	snote.setCn_share_id(UUID.randomUUID().toString());
	snote.setCn_share_title("阿尔萨斯");
	dd.updatefacnote(snote);
	System.out.println("ok");
}
//@Test
public void oo(){
	ActivityService dd=ctx.getBean(ActivityService.class);
	List<Activity> list=dd.FindAllAc();
	for(Activity a:list){
		System.out.println(a);
	}
}
//@Test
public void qw(){
	ActivityNoteMapper dd=ctx.getBean(ActivityNoteMapper.class);
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("title",1);
	map.put("begin", 1);
	map.put("pagesize", 3);
	List<ActivityNote>list=dd.findAllnote(map);
	for(ActivityNote ac:list){
		System.out.println(ac);
	}	
}
//@Test
public void we(){
//	ActivityService dd=ctx.getBean(ActivityService.class);
//	List <ActivityNote>list=dd.FindAllAcNote("1");
//	
//	for(ActivityNote s:list){
//		System.out.println(s);
//	}
}
//@Test
public void aaas(){
	ActivityNoteMapper dd=ctx.getBean(ActivityNoteMapper.class);
	ActivityNote note=new ActivityNote();
	note.setCn_activity_id("10");
	note.setCn_note_activity_body("春节快乐");
	//note.setCn_note_activity_down("");
	note.setCn_note_activity_id(UUID.randomUUID().toString());
	note.setCn_note_activity_title("罗威是天才");
	//note.setCn_note_activity_up("");
	note.setCn_note_id("1111111111111111111");
	
	dd.addAcNote(note);
	System.out.println("ok");
}
//@Test
public void ddda(){
	ActivityService dd=ctx.getBean(ActivityService.class);
	dd.AddActivityNote("054449b4-93d4-4f97-91cb-e0043fc4497f", "4");
}
//查询参加活动笔记
//@Test
public void asasa(){
	NoteMapper dd=ctx.getBean(NoteMapper.class);
	List<Note> list=dd.findacnote("3");
	for(Note s:list){
		System.out.println(s);
	}
}
//@Test
public void kkju(){
	ActivityNoteMapper dd=ctx.getBean(ActivityNoteMapper.class);
	ActivityNote ac=dd.findoneAcNote("12ab86dd-4cca-44e1-a36b-ba2f52e60428");
	System.out.println(ac);
}
//更新活动笔记顶-踩
//@Test
public void adsd(){
	ActivityNoteMapper dd=ctx.getBean(ActivityNoteMapper.class);
	ActivityNote acnote=new ActivityNote();
	acnote.setCn_activity_id("11");
	acnote.setCn_note_activity_body("降龙十八掌");
	acnote.setCn_note_activity_down(0);
	
	acnote.setCn_note_activity_title("郭靖");
	acnote.setCn_note_activity_up(1);
	acnote.setCn_note_id("cf1b6623-507c-4848-827d-9ff16eb202bb");
	acnote.setCn_note_activity_id("5a436113-e331-4387-b408-ced35abdfeff");
	dd.updateACnote(acnote);
}
@Test
public void asdeed(){
	ActivityService dd=ctx.getBean(ActivityService.class);
	dd.UpdateAcNote("5a436113-e331-4387-b408-ced35abdfeff", 3, "down");
	System.out.println("ok");
}

}
