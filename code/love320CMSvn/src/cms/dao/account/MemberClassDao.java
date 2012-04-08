package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.MemberClass;

@Component
public class MemberClassDao extends HibernateDao<MemberClass, Long> {

}
