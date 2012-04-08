package cms.service.ov;

import org.springside.modules.orm.Page;

import cms.entity.account.Arctype;

public class TypeListOV {
	private Page<Arctype> typePage;

	public Page<Arctype> getTypePage() {
		return typePage;
	}

	public void setTypePage(Page<Arctype> typePage) {
		this.typePage = typePage;
	}

	
	
}
