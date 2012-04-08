package cms.dao.account;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.OnlineOrder;

//在线订单Dao
@Repository
public class OrderDao extends HibernateDao<OnlineOrder, Long> {

}
