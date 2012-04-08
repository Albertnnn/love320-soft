package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.TagLabelType;

//TagLabelType标签类型管理DAO

@Component
public class TagLabelTypeDao extends HibernateDao<TagLabelType, Long> {

}
