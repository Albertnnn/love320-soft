package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.Guestbook;

@Component
public class GuestbookDao extends HibernateDao<Guestbook, Long> {

}
