package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.Archives;
@Component
public class ArchivesDao extends HibernateDao<Archives, Long> {

}
