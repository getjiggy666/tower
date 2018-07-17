package com.telecom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 通知
 */

@Entity
@JsonIgnoreProperties(value = {"JavassistLazyInitializer", "hibernateLazyInitializer", "handler", "fromAdmin", "toAdmin"})
public class Message extends BaseEntity {

	private static final long serialVersionUID = -112310144651384975L;

	// 删除状态（未删除、发送者删除、接收者删除）
	public enum DeleteStatus {
		nonDelete, fromDelete, toDelete
	};

	private String title;// 标题
	private String content;// 内容
	private DeleteStatus deleteStatus;// 删除状态
	private Boolean isRead;// 是否已读
	private Boolean isSaveDraftbox;// 是否保存在草稿箱
	
	private Admin fromAdmin;// 通知发出人
	private Admin toAdmin;// 通知接收人


	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Column(nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Enumerated
	@Column(nullable = false)
	public DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@Column(nullable = false)
	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	@Column(nullable = false)
	public Boolean getIsSaveDraftbox() {
		return isSaveDraftbox;
	}

	public void setIsSaveDraftbox(Boolean isSaveDraftbox) {
		this.isSaveDraftbox = isSaveDraftbox;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_message_from_admin")
	public Admin getFromAdmin() {
		return fromAdmin;
	}

	public void setFromAdmin(Admin fromAdmin) {
		this.fromAdmin = fromAdmin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_message_to_admin")
	public Admin getToAdmin() {
		return toAdmin;
	}

	public void setToAdmin(Admin toAdmin) {
		this.toAdmin = toAdmin;
	}
	
	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if (isRead == null) {
			isRead = false;
		}
		if (isSaveDraftbox == null) {
			isSaveDraftbox = false;
		}
	}
	
	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
		if (isRead == null) {
			isRead = false;
		}
		if (isSaveDraftbox == null) {
			isSaveDraftbox = false;
		}
	}

}