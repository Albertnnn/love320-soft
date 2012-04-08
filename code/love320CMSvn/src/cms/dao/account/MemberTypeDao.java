package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.MemberType;

@Component
public class MemberTypeDao extends HibernateDao<MemberType, Long> {

}
