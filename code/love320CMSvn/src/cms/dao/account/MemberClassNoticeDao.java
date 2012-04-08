package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.MemberClassNotice;

@Component
public class MemberClassNoticeDao extends HibernateDao<MemberClassNotice, Long> {

}
