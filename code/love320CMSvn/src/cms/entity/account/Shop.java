package cms.entity.account;

import java.util.List;

import javax.persistence.Entity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.reflection.ConvertUtils;

import com.google.common.collect.Lists;

import cms.entity.Archivesbase;


//商品模型
@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Shop extends Archivesbase {
	
	private double price ;//市场价
	private double trueprice ;//优惠价
	private String brand ;//品牌
	private String units ;//计量单位
	private String specifications;//规格
	private String origin;//产地

	
	private List<Arcatt> flagList  = Lists.newArrayList();//自定义属性值 
	
	//FlagList 商品属性
	@ManyToMany
	@JoinTable(name = "SHOP_ARCATT", joinColumns = { @JoinColumn(name = "SHOP_ID") }, inverseJoinColumns = { @JoinColumn(name = "ARCATT_ID") })
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
	
	//FlagList 商品属性 End
	
	//评论
	private List<Feedback> feedbackList = Lists.newArrayList();//文档评论集合
	
	@OneToMany(mappedBy="shop")
	@Fetch(FetchMode.SUBSELECT)
	@Cascade(CascadeType.ALL)
	@OrderBy("dtime desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}
	
	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	
	//评论 End
	
	//栏目
	private Arctype arctype;//文章对应的栏目

	@ManyToOne
	@JoinTable(
			name="arctype_shop",
			joinColumns={
					@JoinColumn(name="shop_id")
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
	
	//栏目 End
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTrueprice() {
		return trueprice;
	}
	public void setTrueprice(double trueprice) {
		this.trueprice = trueprice;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	
}
