package cms.service.ov;

import java.util.List;

import org.springside.modules.orm.Page;

import cms.entity.account.Archives;


public class ArchivesListOV {
	private Page<Archives> hivesPage ;

	public void setHivesPage(Page<Archives> hivesPage) {
		this.hivesPage = hivesPage;
	}

	public Page<Archives> getHivesPage() {
		return hivesPage;
	}
	
	

	
}
