package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.SMS;


@Component
public class SmsDao extends HibernateDao<SMS, Long> {

}
