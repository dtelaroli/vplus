import static org.vplus.core.persistence.Persistences.load;

import org.vplus.core.generics.MyEntity;
import org.vplus.core.persistence.MyFind;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MyController {

	private Persistence persistence;
	private Result result;

	public MyController(Persistence persistence, Result result) {
		this.persistence = persistence;
		this.result = result;
	}
	
	@Get("/{entity.id}")
	public MyEntity findById(MyEntity entity) {
		persistence.use(load()).find(entity);
	}
	
	public void find() {
		MyEntity entity = persistence.use(MyFind.class).find();
		result.include(entity);
	}
	
}