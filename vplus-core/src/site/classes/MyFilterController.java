
import static br.com.caelum.vraptor.view.Results.json;

import org.vplus.core.generics.StatusFilter;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.persistence.Persistences;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import java.util.List;
import org.vplus.core.model.StatusFilter;

@Resource
public class MyController {

	private Persistence persistence;
	private Result result;

	public MyController(Persistence persistence, Result result, StatusFilter filter) {
		this.persistence = persistence;
		this.result = result;
		filter.removed();
	}
	
	public void all() {
		filter.disableFilter();
		List<MyModel> list = persistence.use(Persistences.list())
				.of(MyModel.class).find();
		result.use(json()).from(list).serialize();
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