package cms.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.EntityModeDao;
import cms.entity.account.EntityMode;

//模型类型管理
@Component
public class EntityModeManager {
	private EntityModeDao emDao ;//模型类型DAO
	
	//获取对象
	@Transactional(readOnly= true)
	public EntityMode getEntity(Long id){
		return emDao.get(id);
	}
	
	//保存对象
	@Transactional
	public void save(EntityMode entity){
		emDao.save(entity);
	}
	
	//搜索
	@Transactional(readOnly= true)
	public Page search(Page<EntityMode> page, List<PropertyFilter> filters) {
		return emDao.findPage(page, filters);
	}

	
	//删除
	@Transactional
	public void delete(Long id) {
		emDao.delete(id);
	}
	
	//获取所有数据模型对象
	@Transactional
	public List<EntityMode> getAll(){
		return emDao.getAll();
	}
	
	
	@Autowired
	public void setEmDao(EntityModeDao emDao) {
		this.emDao = emDao;
	}

	
}
