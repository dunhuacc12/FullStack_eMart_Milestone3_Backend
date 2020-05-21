package pers.chenxi.emart.common.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "e_sub_category")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class SubCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(nullable = false)
	private String subCategoryName;

	private Integer categoryId;

	private String brief;

	@Column(precision = 10, scale = 2)
	private BigDecimal gst;

	@CreatedDate
	private Timestamp crtDate;

	@Column(length = 32)
	private String crtUserId;

	@LastModifiedDate
	private Timestamp updDate;

	@Column(length = 32)
	private String updUserId;

	@Column(length = 1)
	private String delFlg;
}
