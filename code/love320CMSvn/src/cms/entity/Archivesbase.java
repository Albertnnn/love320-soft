package cms.entity;


import java.util.Date;

import javax.persistence.MappedSuperclass;

import cms.entity.IdEntity;
@MappedSuperclass
public class Archivesbase extends IdEntity {
	private int typeid ;//栏目ID
	private Date sortrank;//时间排序 
	//private String flag;//自定义属性值 
	private int ismake;//是否审核 
	private int channel ;//所属模型ID
	private int arcrank;//阅读权限
	private int click;//点击数
	private int moeny;//消费点数
	private String title;//标题 
	private String shorttitle;//简明标题
	private String color ;//颜色
	private String writer ;//作者
	private String source;//文档来源
	private String litpic;//缩略图
	private Date pubdate;//时间
	private Date senddate;//时间
	private int mid;//会员ID
	private String mname;//管理员名
	private String keywords;//关键词
	private String templet;//
	private int lastpost;//最后回复
	private int scores ;//阅读权限
	private int goodpost;//好评 
	private int badpost;//差评
	private int notpost;//评论选项（1：充许评论）

	private String userip;//IP
	private String redirecturl;//跳转地址
	private String description;//摘要
	private String body; //内容
	

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
	public Date getSortrank() {
		return sortrank;
	}
	public void setSortrank(Date sortrank) {
		this.sortrank = sortrank;
	}
	public int getIsmake() {
		return ismake;
	}
	public void setIsmake(int ismake) {
		this.ismake = ismake;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getArcrank() {
		return arcrank;
	}
	public void setArcrank(int arcrank) {
		this.arcrank = arcrank;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getMoeny() {
		return moeny;
	}
	public void setMoeny(int moeny) {
		this.moeny = moeny;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShorttitle() {
		return shorttitle;
	}
	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLitpic() {
		return litpic;
	}
	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}
	
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getTemplet() {
		return templet;
	}
	public void setTemplet(String templet) {
		this.templet = templet;
	}
	public int getLastpost() {
		return lastpost;
	}
	public void setLastpost(int lastpost) {
		this.lastpost = lastpost;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public int getGoodpost() {
		return goodpost;
	}
	public void setGoodpost(int goodpost) {
		this.goodpost = goodpost;
	}
	public int getBadpost() {
		return badpost;
	}
	public void setBadpost(int badpost) {
		this.badpost = badpost;
	}
	public int getNotpost() {
		return notpost;
	}
	public void setNotpost(int notpost) {
		this.notpost = notpost;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public String getRedirecturl() {
		return redirecturl;
	}
	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	

	
	
	
}
