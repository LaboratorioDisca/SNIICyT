package SNIICT.actions;

import com.opensymphony.xwork2.ActionSupport;

public abstract class PartialAwareAction extends ActionSupport {

	public String renderPartial;
	
	public String getRenderPartial() {
		return this.renderPartial;
	}
	
	public void setRenderPartial(String partial) {
		this.renderPartial = partial;
	}


}