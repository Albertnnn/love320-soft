package cms.service.account;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cms.dao.account.ArctypeDao;
import cms.entity.account.Arctype;

@Component
@Transactional()
public class Typeunit{
		private Long id; //栏目ID
		private String typename ; //栏目名
		private int typeNum ;//栏目级别 
		
		private int reid ;//上级栏目ID
		private int topid; //顶级栏目
		public List<Typeunit> typeunits;//下级栏目类
		
		private ArctypeDao arctypeDao;
		

		//构造
		public Typeunit(){
			
		}
		
		//获取顶级栏目
		public List<Typeunit> getTypeTop(){
			List<Typeunit> typeunits =  Lists.newArrayList();
			List<Arctype> arctypelist = arctypeDao.find("from Arctype where topid = id");
			//System.out.println("arctypelist条数:"+arctypelist.size());
			for(int i = 0 ;i< arctypelist.size();i++){
				Typeunit typeunitTest = new Typeunit();
				typeunitTest.id = arctypelist.get(i).getId();
				typeunitTest.typename = arctypelist.get(i).getTypename();
				typeunitTest.reid = arctypelist.get(i).getReid();
				typeunitTest.topid = arctypelist.get(i).getTopid();
				//System.out.println(typeunitTest.getTypename());
				typeunits.add(typeunitTest);
			}	
			return typeunits;
		}
		
		//获取所有栏目
		@Transactional
		public Typeunit getTyenuit(){
			List<Typeunit> typeunits =  Lists.newArrayList();
			List<Arctype> arctypelist = arctypeDao.find("from Arctype where topid = id");
			//System.out.println("arctypelist条数:"+arctypelist.size());
			for(int i = 0 ;i< arctypelist.size();i++){
				Typeunit typeunitTest = new Typeunit();
				typeunitTest.id = arctypelist.get(i).getId();
				typeunitTest.typename = "【"+arctypelist.get(i).getTypename()+"】";
				typeunitTest.reid = arctypelist.get(i).getReid();
				typeunitTest.topid = arctypelist.get(i).getTopid();
				//System.out.println(typeunitTest.getTypename());
				typeunits.add(typeunitTest);
			}	
			this.setTypeunits(typeunits);
			for(int i=0;i<typeunits.size();i++){
				int k = 1 ;
				//System.out.println("初始化递归:"+k);
				typeunits.set(i,this.typeunitReid2(typeunits.get(i),arctypeDao,k));
			}
			return this;
		}

		//递归获取所有子栏目
		private Typeunit typeunitReid(Typeunit typeunitReid,ArctypeDao arctypeDao,int ico){
			List<Typeunit> typeunits =  Lists.newArrayList();
			//System.out.println("栏目ID："+typeunitReid.getId());
			List<Arctype> arctypelist = arctypeDao.find(Restrictions.eq("reid", typeunitReid.getId().intValue()));	
			//System.out.println(typeunitReid.getTypename()+"-栏目数:"+arctypelist.size());
			if(arctypelist.size() > 0){
			for(int i = 0 ;i< arctypelist.size();i++){
				Typeunit typeunitTest = new Typeunit();
				typeunitTest.id = arctypelist.get(i).getId();
				String icos = "　";
				//System.out.println("递归层数:"+ico);
				for(int k= 0 ;k<ico ;k++){
					icos += "　  .";
				}
				//System.out.println("ico数为:"+ico);
				typeunitTest.typename = icos + " ★ " + arctypelist.get(i).getTypename();
				typeunitTest.reid = arctypelist.get(i).getReid();
				typeunitTest.topid = arctypelist.get(i).getTopid();
				typeunits.add(typeunitTest);
				//System.out.println(">>"+typeunitReid.getTypename()+">>"+arctypelist.get(i).getTypename());
			}	
			typeunitReid.setTypeunits(typeunits);
			int icotemporary = ico + 1;
			for(int i=0;i<typeunitReid.getTypeunits().size();i++){
				typeunitReid.typeunits.set(i,typeunitReid.typeunitReid(typeunitReid.typeunits.get(i),arctypeDao,icotemporary));
			}
			}
			
			return typeunitReid;
		}
		
		//递归获取所有子栏目2
		private Typeunit typeunitReid2(Typeunit typeunitReid,ArctypeDao arctypeDao,int ico){
			List<Typeunit> typeunits =  Lists.newArrayList();
			//System.out.println("栏目ID："+typeunitReid.getId());
			List<Arctype> arctypelist = arctypeDao.find(Restrictions.eq("reid", typeunitReid.getId().intValue()));	
			//System.out.println(typeunitReid.getTypename()+"-栏目数:"+arctypelist.size());
			if(arctypelist.size() > 0){
			for(int i = 0 ;i< arctypelist.size();i++){
				Typeunit typeunitTest = new Typeunit();
				typeunitTest.id = arctypelist.get(i).getId();
				typeunitTest.typename =arctypelist.get(i).getTypename();
				typeunitTest.reid = arctypelist.get(i).getReid();
				typeunitTest.topid = arctypelist.get(i).getTopid();
				typeunits.add(typeunitTest);
				//System.out.println(">>"+typeunitReid.getTypename()+">>"+arctypelist.get(i).getTypename());
			}	
			typeunitReid.setTypeunits(typeunits);
			int icotemporary = ico + 1;
			for(int i=0;i<typeunitReid.getTypeunits().size();i++){
				typeunitReid.typeunits.set(i,typeunitReid.typeunitReid2(typeunitReid.typeunits.get(i),arctypeDao,icotemporary));
			}
			}
			
			return typeunitReid;
		}
		
		//递归获取所有栏目
		public List ArctypeList(){
			List<Typeunit> typeList =  Lists.newArrayList();
			Typeunit typeunitTOP = new Typeunit();
			typeunitTOP.setId(new Long(0));
			typeunitTOP.setTypename("顶级栏目");
			typeList.add(typeunitTOP);
			Typeunit typeunitAll = getTyenuit();
			if(typeunitAll != null ){
				typeList = ArctypeListAll(typeList,typeunitAll);
			}
			return typeList;
		}
		
		//递归获取Typeunit类所有栏目
		private List ArctypeListAll(List<Typeunit> typeList,Typeunit typeunitAll){
			if((typeList != null)&&(typeunitAll != null)&&(typeunitAll.typeunits != null)){
				//System.out.println("Typeunit栏目数:"+typeunitAll.typeunits.size());
			for(int i = 0 ;i < typeunitAll.typeunits.size(); i++){
				typeList.add(typeunitAll.getTypeunits().get(i));
				ArctypeListAll(typeList,typeunitAll.getTypeunits().get(i));
			}
			}
			return typeList;
		}
		/* -------------------------------------- */
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTypename() {
			return typename;
		}

		public void setTypename(String typename) {
			this.typename = typename;
		}

		public int getTypeNum() {
			return typeNum;
		}

		public void setTypeNum(int typeNum) {
			this.typeNum = typeNum;
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

		public List<Typeunit> getTypeunits() {
			return typeunits;
		}

		public void setTypeunits(List<Typeunit> typeunits) {
			this.typeunits = typeunits;
		}

		@Autowired
		public void setArctypeDao(ArctypeDao arctypeDao) {
			this.arctypeDao = arctypeDao;
		}	
}
