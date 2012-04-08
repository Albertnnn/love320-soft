package cms.service.account;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Love320SpringBeanFactory implements BeanFactoryAware{
	
	private BeanFactory beanFactory;

	@Autowired
	public void setBeanFactory(BeanFactory bf) throws BeansException {
		beanFactory = bf ;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	

}
