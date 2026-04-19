package com.lbb.customer.buygold.db.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "PRODUCT", schema = "ADMIN_MBUAT")
public class ProductEntity {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "UNIT")
    private String unit;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private String createdAt;

    @Column(name = "UPDATED_AT")
    private String updatedAt;

    @Column(name = "DELETE_AT")
    private String deleteAt;

    @Column(name = "IMAGE")
    private String image;
}
