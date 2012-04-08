package cms.entity.account;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.reflection.ConvertUtils;

import com.google.common.collect.Lists;

import cms.entity.IdEntity;

@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TagLabel extends IdEntity {
	
		private TagLabelType tagLabelType;//标签类型
		
		@ManyToOne
		@JoinColumn(name="tltid")
		public TagLabelType getTagLabelType() {
			return tagLabelType;
		}
		public void setTagLabelType(TagLabelType tagLabelType) {
			this.tagLabelType = tagLabelType;
		}

		//通用标签类	
		private String tagName ;//标签名
		private int typeid ;//栏目ID
		private int row ;//调用数
		private String body;//
		
	//栏目标签类
		
	
	//文章标签类	
		private int titlelen ;//标题长度
		private String orderby ;//排序方式
		private String orderbyType;//升序还是降序 desc asc
		private List<Arcatt> flagList  = Lists.newArrayList();//自定义属性值 
			
		public String getTagName() {
			return tagName;
		}
		public void setTagName(String tagName) {
			this.tagName = tagName;
		}
		public int getTypeid() {
			return typeid;
		}
		public void setTypeid(int typeid) {
			this.typeid = typeid;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getTitlelen() {
			return titlelen;
		}
		public void setTitlelen(int titlelen) {
			this.titlelen = titlelen;
		}
		public String getOrderby() {
			return orderby;
		}
		public void setOrderby(String orderby) {
			this.orderby = orderby;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getOrderbyType() {
			return orderbyType;
		}
		public void setOrderbyType(String orderbyType) {
			this.orderbyType = orderbyType;
		}
		
		@ManyToMany
		@JoinTable(name = "TAGLABEL_ARCATT", joinColumns = { @JoinColumn(name = "TAGLABEL_ID") }, inverseJoinColumns = { @JoinColumn(name = "ARCATT_ID") })
		@Fetch(FetchMode.SUBSELECT)
		@OrderBy("id")
		@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
		public List<Arcatt> getFlagList() {
			return flagList;
		}
		
		@Transient
		@SuppressWarnings("unchecked")
		public List<Long> getflagIds() {
			return ConvertUtils.convertElementPropertyToList(flagList, "id");
		}
		
		public void setFlagList(List<Arcatt> flagList) {
			this.flagList = flagList;
		}
		
		
		
	
}
