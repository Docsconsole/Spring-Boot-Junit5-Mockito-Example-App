package com.docsconsole.tutorials.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "product_id")
        })
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank
    @Size(max = 20)
    @Column(name = "product_name")
    private String productName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "product_vendor_name")
    private String productVendorName;


}
