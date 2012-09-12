package SNIICT.actions;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({
        @Result(name = Action.SUCCESS, location = "${redirectName}", type = "redirectAction")
})
public class Index extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String redirectName;

    /**
	 *  Redirecciona a la acción que despliega el formulario de búsqueda
	 */
    public String execute() {
        redirectName = "busqueda";
        return Action.SUCCESS;
    }

    public String getRedirectName() {
        return redirectName;
    }

}
