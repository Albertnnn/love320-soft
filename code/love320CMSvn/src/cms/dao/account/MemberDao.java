package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.Member;

@Component
public class MemberDao extends HibernateDao<Member, Long> {

}
