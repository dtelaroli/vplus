package org.dtelaroli.vplus.media.domain;

import org.dtelaroli.vplus.core.controller.Crud;
import org.dtelaroli.vplus.core.controller.Scaffold;
import org.dtelaroli.vplus.core.exception.CrudException;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

@Resource
public class FileController extends Scaffold<File> {

	public FileController(Crud crud) {
		super(crud);
	}
	
	public static class newModel {
		File file;
		String bla;
	}
	
	@Override
	@Post("")
	public void add(File model) throws CrudException {
		super.add(model);
	}
	
	public void b(newModel model) {
		System.out.println(model);
	}

}
