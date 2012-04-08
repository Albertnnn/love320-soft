package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.Advertising;


@Component
public class AdvertisingDao extends HibernateDao<Advertising, Long> {

}
