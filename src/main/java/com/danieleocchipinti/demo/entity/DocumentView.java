package com.danieleocchipinti.demo.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "document_view")
public class DocumentView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;    
    
    @Column(nullable = false)    
    private Date viewedAt;

    @Column(nullable = true)    
    private Date viewedTill;    
    
    protected DocumentView() { }

	public DocumentView(Document document) {
		this.document = document;
		this.viewedAt = new Date();
	}

	public int getId() {
		return id;
	}

	public Document getDocument() {
		return document;
	}

	public Date getViewedAt() {
		return viewedAt;
	}

	public Date getViewedTill() {
		return viewedTill;
	}
	
	public void endView() {
		this.viewedTill = new Date();
	}
}