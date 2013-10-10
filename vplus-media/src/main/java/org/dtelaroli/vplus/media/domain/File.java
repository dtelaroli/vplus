package org.dtelaroli.vplus.media.domain;

import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dtelaroli.vplus.core.model.ModelPlus;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.google.common.io.Files;

@Entity
public class File extends ModelPlus {

	private static final long serialVersionUID = -2975538224089747881L;

	@NotEmpty
	private String originalName;
	
	@NotEmpty
	private String name;
	
	private String description;
	
	@NotEmpty
	private String ext;
	
	@NotEmpty
	private String type;
	
	@NotNull
	private Long size;
	
	private String hash;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private FileContent content;
	
	@Transient
	private UploadedFile upload;
	
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
		content = new FileContent(uploadedFile.getFile(), this);
		upload = uploadedFile;
	}
	
	public File build() throws IOException {
		parse(upload);
		return this;
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
		return content.getContent();
	}

	protected String getOriginalName() {
		return originalName;
	}

	protected void setOriginalName(String fullName) {
		this.originalName = fullName;
	}

	protected String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	protected UploadedFile getUpload() {
		return upload;
	}

	protected void setUpload(UploadedFile upload) {
		this.upload = upload;
	}

	@Override
	public String toString() {
		return "File [originalName=" + originalName + ", name=" + name
				+ ", description=" + description + ", ext=" + ext + ", type="
				+ type + ", size=" + size + ", hash=" + hash + ", content="
				+ content + ", upload=" + upload + ", Super="
				+ super.toString() + "]";
	}

}