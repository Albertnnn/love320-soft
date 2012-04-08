package cms.service.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.google.common.collect.Lists;

import cms.bean.RiaJsonObject;
import cms.dao.account.SmsDao;
import cms.entity.account.SMS;
import cms.entity.account.TagLabel;
import cms.sms.SmsClient;

@Service
public class SmsManager {
	
	private SmsClient smsClient;
	
	private SmsDao smsDao;
	
	public boolean sendSms(String mobile,String content){
		return smsClient.sendSMS(mobile, content);
	}
	
	@Transactional
	public boolean sendSms(String mobile,String content,String strType){
		SMS entity = new SMS();
		entity.setPhone(mobile);//手机号
		entity.setContent(content);//内容
		entity.setStrType(strType);//信息类别
		entity.setSmsAction(1);
		save(entity);
		return true;
	}
	
	public boolean sendSms(SMS entity){
		if(entity.getOptionDate() != null){
			return smsClient.sendSMS(entity.getPhone(), entity.getContent(),entity.getOptionDate());
		}else{
			return smsClient.sendSMS(entity.getPhone(), entity.getContent());
		}
	}
	
	@Transactional
	public String receiving(){
		
		String reStr = smsClient.receivingSMS();
		
		//本地测试
		//String reStr = "||15817433071#申通快递#2012-03-09 23:17:07#0||15817433076#申通快递#2012-03-09 23:17:07#0";
		
		List<SMS> listSms = receivingToList(reStr);
		
		int listNum = saveList(listSms);
		if(listNum > 0){
			return listNum+"条";
		}else{
			return "0条";
		}
		
	}
	
	private int saveList(List<SMS> listSms){
		int num = 0;
		Date nowDate = new Date();
		for(SMS smsEntity : listSms){
			smsEntity.setNewDate(nowDate);//设置接收短信时间 
			smsEntity.setTypeId(2);
			smsDao.save(smsEntity);
			num++;
		}
		return num;
	}
	
	private List<SMS> receivingToList(String smsStr){
		
		/*
		 * 
||15817433071#中政局dghjnvdsfhjgfth!@#$%&*()~/__:??;#2012-03-10 15:39:15#0||15817433071#我足巾&vnhfvbhs||gffg｜｜申诉#2012-03-10 15:40:15#0

		 * */
		
		/*
		 * Smszucp.java
		 * 56054313,120464,15817433071,%ce%d2%d4%da,2012-3-9 17:33:19
		 * 
		 * Smsvv106.java
		 * ||15817433071#申通快递#2012-03-09 23:17:07#0
		 * ||15817433071#我足巾&vnhfvbhs||gffg｜｜申诉#2012-03-10 15:40:15#0
		 * */

		
		List<SMS> listSms = Lists.newArrayList();
		//2012-03-10 15:01:51
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);

		
		String strs[] = smsStr.split("\\|\\|");
		for(int i = 1 ; i < strs.length ;i++){
			
			String smsOne[] = strs[i].split("#");//分解字符串 #
			if(smsOne.length == 4){
				SMS smsEntity = new SMS();//实例短信个体对象
				smsEntity.setPhone(smsOne[0]);//设置手机号
				smsEntity.setContent(smsOne[1]);//设置手机内容
				try {
					smsEntity.setSmsDate(sdf.parse(smsOne[2]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				listSms.add(smsEntity);//载体到list列表中
			}
		}
		
		return listSms;
	}

	@Autowired
	public void setSmsClient(SmsClient smsClient) {
		this.smsClient = smsClient;
	}
	
	public String balance(){
		return smsClient.balance();
	}

	@Autowired
	public void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}

	@Transactional(readOnly=true)
	public SMS getEntity(Long id) {
		return smsDao.get(id);
	}

	@Transactional(readOnly=true)
	public Page search(Page page, List<PropertyFilter> filters) {
		return smsDao.findPage(page, filters);
	}

	@Transactional
	public void save(SMS entity) {
		Date nowDate = new Date();
		entity.setNewDate(nowDate);//设置创建时候
		entity.setTypeId(1);//发短信标记
		
		boolean sendSmsState = sendSms(entity);//发送短信
		if(sendSmsState == true){
			Date sendDate = new Date();
			entity.setSmsDate(sendDate);
		}
		
		smsDao.save(entity);
		
	}
	
	//处理短信后保存
	@Transactional
	public void save(SMS entity,boolean state) {
		smsDao.save(entity);
	}

	@Transactional
	public void deleteId(Long id) {
		smsDao.delete(id);
	}

	//没有处理的短信
	@Transactional(readOnly=true)
	public List<SMS> getNotP() {
		return smsDao.find(Restrictions.eq("smsAction",0));
	}
	
}
