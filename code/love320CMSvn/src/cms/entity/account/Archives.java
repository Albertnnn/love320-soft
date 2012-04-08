package cms.entity.account;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.reflection.ConvertUtils;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.util.KeyProperty;

import cms.entity.Archivesbase;

@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Archives extends Archivesbase{
	
	private List<Arcatt> flagList  = Lists.newArrayList();//自定义属性值 
	
	@ManyToMany
	@JoinTable(name="ARCHIVES_ARCATT", 
			joinColumns ={@JoinColumn(name="ARCHIVES_ID")},
			inverseJoinColumns = @JoinColumn(name="ARCATT_ID")
			)
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
	
	private List<Feedback> feedbackList = Lists.newArrayList();//文档评论集合
	
	@OneToMany(mappedBy="archives",cascade=CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("dtime desc")
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}
	
	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	
	private Arctype arctype;//文章对应的栏目

	@ManyToOne
	@JoinTable(
			name="archives_arctype",
			joinColumns={
					@JoinColumn(name="archives_id")
			},
			inverseJoinColumns={
					@JoinColumn(name="arctype_id")
			}
	)
	public Arctype getArctype() {
		return arctype;
	}
	public void setArctype(Arctype arctype) {
		this.arctype = arctype;
	}
	
}
