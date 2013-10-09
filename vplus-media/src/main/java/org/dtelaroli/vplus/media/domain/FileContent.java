package org.dtelaroli.vplus.media.domain;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.google.common.io.ByteStreams;

@Entity
public class FileContent implements Serializable {

	private static final long serialVersionUID = 4262796994127983344L;

	@Id
	private Long id;
	
	@MapsId
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "fileContent")
	@JoinColumn(name = "id")
	private File file;
	
	@NotNull
	@Lob
	private byte[] content;
	
	public FileContent() {
	}
	
	public FileContent(InputStream inputStream, File file) throws IOException {
		content = ByteStreams.toByteArray(inputStream);
		this.file = file;
	}

	protected byte[] getContent() {
		return content;
	}

}
