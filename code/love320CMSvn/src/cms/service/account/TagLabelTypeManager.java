package cms.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.TagLabelTypeDao;
import cms.entity.account.TagLabelType;


@Component
public class TagLabelTypeManager {
		
		private TagLabelTypeDao tagLabelTypeDao ;//标签类型Dao
		
		//获取所有对象
		@Transactional(readOnly = true)
		public List getAll(){
			return tagLabelTypeDao.getAll();
		}
		
		//获取标签类型对象
		@Transactional(readOnly = true)
		public TagLabelType getEntity(Long id){
			return tagLabelTypeDao.get(id);
		}

		//保存标签类型对象
		@Transactional
		public void save(TagLabelType entity){
			tagLabelTypeDao.save(entity);
		}
		
		@Transactional(readOnly= true)
		public Page searchArchives(Page<TagLabelType> page,List<PropertyFilter> filters){
			return tagLabelTypeDao.findPage(page, filters);
		}
		
		@Autowired
		public void setTagLabelTypeDao(TagLabelTypeDao tagLabelTypeDao) {
			this.tagLabelTypeDao = tagLabelTypeDao;
		}

	
		
		
		
		
		
}
