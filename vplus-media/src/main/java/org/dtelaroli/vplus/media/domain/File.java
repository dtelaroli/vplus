package org.dtelaroli.vplus.media.domain;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.dtelaroli.vplus.core.model.ModelPlus;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

@Entity
public class File extends ModelPlus {

	private static final long serialVersionUID = -2975538224089747881L;

	@NotEmpty
	private String originalName;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String fileDescription;
	
	@NotEmpty
	private String ext;
	
	@NotEmpty
	private String type;
	
	@NotNull
	private Long size;
	
	@NotEmpty
	private String hash;
	
	@NotNull
	@Lob
	private byte[] content;
	
	public File() {
	}
	
	public File(UploadedFile uploadedFile) throws IOException {
		parse(uploadedFile);
	}

	public void parse(UploadedFile uploadedFile) throws IOException {
		originalName = uploadedFile.getFileName();
		ext = Files.getFileExtension(originalName);
		name = originalName.replace("\\." + ext, "");
		size = uploadedFile.getSize();
		type = uploadedFile.getContentType();
		content = ByteStreams.toByteArray(uploadedFile.getFile());
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getExt() {
		return ext;
	}

	protected void setExt(String ext) {
		this.ext = ext;
	}

	protected String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	protected Long getSize() {
		return size;
	}

	protected void setSize(Long size) {
		this.size = size;
	}

	protected String getHash() {
		return hash;
	}

	protected void setHash(String hash) {
		this.hash = hash;
	}

	protected byte[] getContent() {
		return content;
	}

	protected void setContent(byte[] content) {
		this.content = content;
	}

	protected String getOriginalName() {
		return originalName;
	}

	protected void setOriginalName(String fullName) {
		this.originalName = fullName;
	}

	protected String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	@Override
	public String toString() {
		return "File [fullName=" + originalName + ", name=" + name
				+ ", fileDescription=" + fileDescription + ", ext=" + ext
				+ ", type=" + type + ", size=" + size + ", hash=" + hash
				+ ", Super."
				+ super.toString() + "]";
	}
	
}