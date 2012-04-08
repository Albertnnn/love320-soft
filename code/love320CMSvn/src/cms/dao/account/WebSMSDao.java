package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.WebSMS;

@Component
public class WebSMSDao extends HibernateDao<WebSMS, Long> {

}
