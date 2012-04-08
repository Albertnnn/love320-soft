package cms.sms.com;

import java.io.IOException;
import java.util.List;

public interface SmsInterface {
	
	/*
	 * 方法名称：register 功 能：注册 参 数：对应参数 省份，城市，行业，企业名称，联系人，电话，手机，电子邮箱，传真，地址，邮编 返 回
	 * 值：注册结果（String）
	 */
	public String register(String province, String city, String trade,
			String entname, String linkman, String phone, String mobile,
			String email, String fax, String address, String postcode) ;
	
	
	/*
	 * 方法名称：chargeFee 功 能：充值 参 数：充值卡号，充值密码 返 回 值：操作结果（String）
	 */
	public String chargeFee(String cardno, String cardpwd);
	
	/*
	 * 方法名称：getBalance 功 能：获取余额 参 数：无 返 回 值：余额（String）
	 */
	public String getBalance() ;
	
	/*
	 * 方法名称：mt 功 能：发送短信 参 数：mobile,content,ext,stime,rrid(手机号，内容，扩展码，定时时间，唯一标识)
	 * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
	 */
	public String mt(String mobile, String content, String ext, String stime,
			String rrid);
	
	/*
	 * 方法名称：gxmt 功 能：发送短信 参
	 * 数：mobile,content,ext,stime,rrid(手机号，内容，扩展码，定时时间，唯一标识) 返 回
	 * 值：唯一标识，如果不填写rrid将返回系统生成的
	 */
	public String gxmt(String mobile, String content, String ext, String stime,
			String rrid) ;
	
	/*
	 * 方法名称：mo 功 能：接收短信 参 数：无 返 回 值：接收到的信息
	 */
	public String mo();
	
	/*
	 * 方法名称：RECSMSEx 功 能：接收短信 参 数：无 返 回 值：接收到的信息
	 */
	public List RECSMSEx(String subcode) throws IOException ;
	
	/*
	 * 方法名称：msgid 功 能：获取msgid（发送成功的最后100次） 参 数：无 返 回 值：msgid串
	 */
	public String msgid() ;
	
}
