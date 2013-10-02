package org.dtelaroli.vplus.media.domain;

import org.dtelaroli.vplus.core.controller.Crud;
import org.dtelaroli.vplus.core.controller.Scaffold;

import br.com.caelum.vraptor.Resource;

@Resource
public class FileController extends Scaffold<File> {

	public FileController(Crud crud) {
		super(crud);
	}

}
