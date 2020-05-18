package pers.chenxi.emart.common.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "e_items")
@Getter
@Setter
public class ItemsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	private Integer categoryId;

	private Integer subCategoryId;

	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	@Column(nullable = false)
	private String itemsName;

	private String imgUrl;

	@OneToMany(mappedBy = "item", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<ImgUrlEntity> imgUrls4Detail;

	@OneToMany(mappedBy = "item", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<DescriptionEntity> descriptions;

	private Integer stockNum;

	private String remark;

	private Timestamp crtDate;

	@Column(length = 32)
	private String crtUserId;

	private Timestamp updDate;

	@Column(length = 32)
	private String updUserId;

	@Column(length = 1)
	private String delFlg;

	@OneToOne(mappedBy = "item", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private StoreItemsEntity storeItems;
}
