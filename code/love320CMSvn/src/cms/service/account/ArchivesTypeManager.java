package cms.service.account;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ArchivesTypeManager{
	private ArchivesManager archivesManager;
	private ArctypeManager arctypeManager;
	private SgpageManager sgpageManager;
	
	public ArchivesManager getArchivesManager() {
		return archivesManager;
	}
	
	@Autowired
	public void setArchivesManager(ArchivesManager archivesManager) {
		this.archivesManager = archivesManager;
	}
	public ArctypeManager getArctypeManager() {
		return arctypeManager;
	}
	
	@Autowired
	public void setArctypeManager(ArctypeManager arctypeManager) {
		this.arctypeManager = arctypeManager;
	}

	public SgpageManager getSgpageManager() {
		return sgpageManager;
	}
	
	@Autowired
	public void setSgpageManager(SgpageManager sgpageManager) {
		this.sgpageManager = sgpageManager;
	}
	
	

}
