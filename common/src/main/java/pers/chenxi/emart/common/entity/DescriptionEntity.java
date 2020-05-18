package pers.chenxi.emart.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "e_description")
@Getter
@Setter
public class DescriptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	private String descriptionLabel;

	private String descriptionValue;

	private Integer sort;

	private Timestamp crtDate;

	@Column(length = 32)
	private String crtUserId;

	private Timestamp updDate;

	@Column(length = 32)
	private String updUserId;

	@Column(length = 1)
	private String delFlg;

	@ManyToOne(optional=false)
	@JoinColumn(name="item_id", referencedColumnName = "id")
	private ItemsEntity item;
}
