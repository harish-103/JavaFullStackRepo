package com.hibernate.one_many;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Market")
public class Market {

	@Id
	@GeneratedValue
	@Column(name="Market_id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, mappedBy="market")
	private Set<Stock> stocks;
	
	public Market() {
		super();
	}

	public Market(String name) {
		this.name = name;
		
	}

	public Market(String name, Set<Stock> stocks) {
		this.name = name;
		this.stocks = stocks;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return id + " - " + name;
	}
	
}



