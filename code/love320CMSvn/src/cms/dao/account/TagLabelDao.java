package cms.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cms.entity.account.TagLabel;

@Component
public class TagLabelDao extends HibernateDao<TagLabel, Long> {

}
