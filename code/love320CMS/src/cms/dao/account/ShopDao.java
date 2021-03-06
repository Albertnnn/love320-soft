/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.dao.account;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.Shop;


//商品DAO
@Repository
public class ShopDao extends HibernateDao<Shop, Long> {

}
