package cms.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.MemberClassDao;
import cms.entity.account.MemberClass;

@Service
public class MemberClassManager {
	private MemberClassDao mcDao;

	@Autowired
	public void setMcDao(MemberClassDao mcDao) {
		this.mcDao = mcDao;
	}

	@Transactional(readOnly=true)
	public Page searchList(Page page, List<PropertyFilter> filters) {
		return mcDao.findPage(page, filters);
	}

	@Transactional(readOnly=true)
	public MemberClass getEntity(Long id) {
		return mcDao.get(id);
	}

	@Transactional
	public void save(MemberClass entity) {
		mcDao.save(entity);
	}

	@Transactional
	public void deleteId(Long id) {
		mcDao.delete(id);
	}

	@Transactional(readOnly=true)
	public List<MemberClass> getAll() {
		return mcDao.getAll();
	}


	
}
