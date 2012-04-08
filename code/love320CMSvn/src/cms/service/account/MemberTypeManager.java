package cms.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.MemberTypeDao;
import cms.entity.account.MemberType;

@Service
public class MemberTypeManager {

	private MemberTypeDao mtDao;
	
	@Transactional(readOnly=true)
	public Page searchList(Page page, List<PropertyFilter> filters) {
		return mtDao.findPage(page, filters);
	}

	@Autowired
	public void setMtDao(MemberTypeDao mtDao) {
		this.mtDao = mtDao;
	}

	@Transactional(readOnly=true)
	public MemberType getEntity(Long id) {
		return mtDao.get(id);
	}

	@Transactional
	public void save(MemberType entity) {
		mtDao.save(entity);
	}

	@Transactional
	public void deleteId(Long id) {
		mtDao.delete(id);
	}

	@Transactional
	public List<MemberType> getAll() {
		return mtDao.getAll();
	}

	
	
}
