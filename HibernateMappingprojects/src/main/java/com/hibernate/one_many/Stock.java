package com.hibernate.one_many;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Stock")
public class Stock {
	@Id
	@GeneratedValue
	@Column(name="Stock_id")
	private Long id;

	@Column(name="name")
	private String name;
	
	@Column(name="value")
	private int value;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="id")
	private Market market;
	
	public Stock(String name, int value, Market market) {
		this.name = name;
		this.value=value;
		this.market = market;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}

	public Market getMarket() {
		return this.market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String toString() {
		return id + " - " + name  + " - enquiry: " + market;
	}
}



