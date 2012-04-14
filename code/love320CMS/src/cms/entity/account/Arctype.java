/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.entity.account;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import cms.entity.IdEntity;


/*
 * 栏目表
 * */
@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Arctype extends IdEntity{
		private int reid ; //上级栏目 
		private int topid ; //顶级栏目
		private int sortrank ; // 栏目排序
		private String typename ; //栏目名
		private String typedir ;// 目录地址
		private int isdefault ;// 栏目列表选项（1：链接到默认页；0：链接到列表第一页；-1：使用动态页）
		private String defaultname ;// 默认页的名称
		private int issend;// 是否支持投稿（0：不支持；1：支持） 
		private int channeltype ; //所属频道ID
		private int maxpage; //
		private int ispart; //栏目属性（0：最终列表栏目；1：频道封面；2：外部连接） 
		private int corank; // 浏览权限 
		private String tempindex; //封面模板 
		private String templist ;//列表模板 
		private String temparticle ;//内容模板
		private String templistname ;//列表模板解析后出文件 
		private String temparticlename ;//内容模板解析后出文件 
		private String namerule ;//文章命名规则（{typedir}/{Y}{M}{D}/{aid}.html） 
		private String namerule2 ;//文章命名规则（{typedir}/{Y}{M}{D}/{aid}.html）
		private String modname; //
		private String description ;//栏目描述;
		private String keywords; //关键字
		private int moresite; //多站点支持（0：不启用） 
		private String sitepath ;//栏目地址 
		private String siteurl;//绑定域名
		private int ishidden;//是否隐藏栏目（0：显示）
		//private int cross;//栏目交（0：不交叉；1：自动获取同名栏目内容；2：手工指定交叉栏目ID）
		private String crossid;//交叉栏目ID
		private String content;//栏目内容
		private String clickmethods;//打开方式

		private EntityMode entitymode ;//关联对应模型
		private List<Archives> archivesList;//文章列表
		private List<Shop> shopList;//商品列表
		
		
		
		@ManyToOne
		@JoinTable(
				name="Arctype_Entitymode",
				joinColumns={
						@JoinColumn(name="Arctype_id")
				},
				inverseJoinColumns={
						@JoinColumn(name="Entitymode_id")
				}
		)
		public EntityMode getEntitymode() {
			return entitymode;
		}
		public void setEntitymode(EntityMode entitymode) {
			this.entitymode = entitymode;
		}
		
		
		@OneToMany(mappedBy="arctype")
		@Cascade(CascadeType.ALL)
		@OrderBy("id desc")
		@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
		public List<Shop> getShopList() {
			return shopList;
		}
		public void setShopList(List<Shop> shopList) {
			this.shopList = shopList;
		}
		
		
		
		@OneToMany(mappedBy="arctype")
		@Cascade(CascadeType.ALL)
		@OrderBy("id desc")
		@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
		public List<Archives> getArchivesList() {
			return archivesList;
		}
		public void setArchivesList(List<Archives> archivesList) {
			this.archivesList = archivesList;
		}
		public int getReid() {
			return reid;
		}

		public void setReid(int reid) {
			this.reid = reid;
		}
		public int getTopid() {
			return topid;
		}
		public void setTopid(int topid) {
			this.topid = topid;
		}
		public int getSortrank() {
			return sortrank;
		}
		public void setSortrank(int sortrank) {
			this.sortrank = sortrank;
		}
		public String getTypename() {
			return typename;
		}
		public void setTypename(String typename) {
			this.typename = typename;
		}
		public String getTypedir() {
			return typedir;
		}
		public void setTypedir(String typedir) {
			this.typedir = typedir;
		}
		public int getIsdefault() {
			return isdefault;
		}
		public void setIsdefault(int isdefault) {
			this.isdefault = isdefault;
		}
		public String getDefaultname() {
			return defaultname;
		}
		public void setDefaultname(String defaultname) {
			this.defaultname = defaultname;
		}
		public int getIssend() {
			return issend;
		}
		public void setIssend(int issend) {
			this.issend = issend;
		}
		public int getChanneltype() {
			return channeltype;
		}
		public void setChanneltype(int channeltype) {
			this.channeltype = channeltype;
		}
		public int getMaxpage() {
			return maxpage;
		}
		public void setMaxpage(int maxpage) {
			this.maxpage = maxpage;
		}
		public int getIspart() {
			return ispart;
		}
		public void setIspart(int ispart) {
			this.ispart = ispart;
		}
		public int getCorank() {
			return corank;
		}
		public void setCorank(int corank) {
			this.corank = corank;
		}
		public String getTempindex() {
			return tempindex;
		}
		public void setTempindex(String tempindex) {
			this.tempindex = tempindex;
		}
		public String getTemplist() {
			return templist;
		}
		public void setTemplist(String templist) {
			this.templist = templist;
		}
		public String getTemparticle() {
			return temparticle;
		}
		public void setTemparticle(String temparticle) {
			this.temparticle = temparticle;
		}
		public String getNamerule() {
			return namerule;
		}
		public void setNamerule(String namerule) {
			this.namerule = namerule;
		}
		public String getNamerule2() {
			return namerule2;
		}
		public void setNamerule2(String namerule2) {
			this.namerule2 = namerule2;
		}
		public String getModname() {
			return modname;
		}
		public void setModname(String modname) {
			this.modname = modname;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getKeywords() {
			return keywords;
		}
		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}
		public int getMoresite() {
			return moresite;
		}
		public void setMoresite(int moresite) {
			this.moresite = moresite;
		}
		public String getSitepath() {
			return sitepath;
		}
		public void setSitepath(String sitepath) {
			this.sitepath = sitepath;
		}
		public String getSiteurl() {
			return siteurl;
		}
		public void setSiteurl(String siteurl) {
			this.siteurl = siteurl;
		}
		public int getIshidden() {
			return ishidden;
		}
		public void setIshidden(int ishidden) {
			this.ishidden = ishidden;
		}
		public String getCrossid() {
			return crossid;
		}
		public void setCrossid(String crossid) {
			this.crossid = crossid;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getTemplistname() {
			return templistname;
		}
		public void setTemplistname(String templistname) {
			this.templistname = templistname;
		}
		public String getTemparticlename() {
			return temparticlename;
		}
		public void setTemparticlename(String temparticlename) {
			this.temparticlename = temparticlename;
		}
		public String getClickmethods() {
			return clickmethods;
		}
		public void setClickmethods(String clickmethods) {
			this.clickmethods = clickmethods;
		}
		
		
		
}
