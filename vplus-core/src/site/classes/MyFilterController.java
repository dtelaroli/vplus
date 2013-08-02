
import org.vplus.core.generics.MyEntity;
import org.vplus.core.generics.StatusFilter;
import org.vplus.core.persistence.MyFind;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MyController {

	private Persistence persistence;
	private Result result;
	private StatusFilter filter;

	public MyController(StatusFilter filter) {
		this.filter = filter;
	}
	
	public void all() {
		filter.disableFilter();
	}
	
	public void actives() {
		filter.active();
	}
	
	public void inactives() {
		filter.inactive();
	}
	
	public void removeds() {
		filter.removed();
	}
	
}