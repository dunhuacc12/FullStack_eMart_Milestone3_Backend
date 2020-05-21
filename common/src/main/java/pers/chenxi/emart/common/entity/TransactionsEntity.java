package pers.chenxi.emart.common.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "e_transactions")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class TransactionsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(length = 32)
	private String userId;

//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "item_id", referencedColumnName = "id")
//	private ItemsEntity items;

	private Integer itemId;

	private Integer number;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "discount_id", referencedColumnName = "id")
	private DiscountsEntity discounts;

	private String transactionStatus;

	private Date transactionDate;

	private String remarks;

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
