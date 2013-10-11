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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.google.common.io.Files;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private FileContent content;
	
	@Transient
	private UploadedFile upload;
	
	public File() {
		super();
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
	
	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public FileContent getContent() {
		return content;
	}

	public void setContent(FileContent content) {
		this.content = content;
	}

	public UploadedFile getUpload() {
		return upload;
	}

	public void setUpload(UploadedFile upload) throws IOException {
		this.upload = upload;
		parse(upload);
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}