package org.dtelaroli.vplus.media.domain;

import java.io.Serializable;

import org.dtelaroli.vplus.core.model.ModelPlus;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class Fi implements Serializable {

	private static final long serialVersionUID = -6535829981664790661L;

	private String text;
	
	private UploadedFile upload;

	@Override
	public String toString() {
		return "Fiii [text=" + text + ", upload=" + upload + "]" + super.toString();
	}

	public Fi() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UploadedFile getUpload() {
		return upload;
	}

	public void setUpload(UploadedFile upload) {
		this.upload = upload;
	}
	
	
}
