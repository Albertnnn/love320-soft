package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.Feedback;

@Component
public class FeedBackDao extends HibernateDao<Feedback, Long> {

}
