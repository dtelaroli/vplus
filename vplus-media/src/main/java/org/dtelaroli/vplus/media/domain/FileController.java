package org.dtelaroli.vplus.media.domain;

import org.dtelaroli.vplus.core.controller.Crud;
import org.dtelaroli.vplus.core.controller.Scaffold;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Resource
public class FileController extends Scaffold<File> {

	public FileController(Crud crud) {
		super(crud);
	}
	
	@Post("/put")
	public void put(File file) {
		System.out.println(file);
	}
	
	@Post("/put2")
	public void put2(UploadedFile file) {
		System.out.println(file);
	}

}
