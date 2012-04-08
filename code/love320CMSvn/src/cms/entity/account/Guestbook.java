package cms.entity.account;

import java.util.Date;
import javax.persistence.Entity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import cms.entity.IdEntity;

@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Guestbook extends IdEntity {
	
	private String title;//标题
	private String mid;//
	private Date postime;//时间
	private String uname;//留言姓名
	private String email;//邮箱
	private String homepage;//个人主页
	private String qq;//QQ
	private String face;//头像
	private String ip;//留言IP
	private Date dtime ;//留言时间
	private String ischeck ;//
	private String msg ;//留言信息
	private String replymsg ;//回复留言信息
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Date getPostime() {
		return postime;
	}
	public void setPostime(Date postime) {
		this.postime = postime;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getDtime() {
		return dtime;
	}
	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}
	public String getIscheck() {
		return ischeck;
	}
	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReplymsg() {
		return replymsg;
	}
	public void setReplymsg(String replymsg) {
		this.replymsg = replymsg;
	}
	
	
	
	
}
