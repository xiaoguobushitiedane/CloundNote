package tarena.com.util;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;


/**
 * 日期类型转换器
 * @author Administrator
 *
 */
public class TimestampEditor 
extends PropertyEditorSupport {

/* 
 * 类型转换方法，参数text是页面传入的
 * 日期字符串，我们需要将此字符串转换
 * 成Timestamp类型并注入给Controller方法
 * 中的参数。
 */
@Override
public void setAsText(String text) 
	throws IllegalArgumentException {
	System.out.println("into setAsText");
	if(text == null) {
		//如果传入的数据为null，没必要转换，
		//直接将null注入
		setValue(null);
	} else {
		//如果传入的数据不是null，转型后注入
		Timestamp t = new Timestamp(
				Long.valueOf(text));
		setValue(t);
	}
}

}
