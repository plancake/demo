package com.danieleocchipinti.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "deal")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String description;
    
    @OneToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;    
    
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "deal",fetch = FetchType.LAZY)
    private List<Document> documents;    
    
    @Column(nullable = false)    
    private Date createdAt = new Date();

    protected Deal() { }

	public Deal(String description, User seller, User buyer, List<Document> documents) {
		super();
		this.description = description;
		this.seller = seller;
		this.buyer = buyer;
		this.documents = documents;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public User getSeller() {
		return seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public Date getCreatedAt() {
		return createdAt;
	}    
}