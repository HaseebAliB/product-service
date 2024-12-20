package com.microservice.productservice.model;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

 @Id
 @GeneratedValue
 @JdbcType(VarcharJdbcType.class)
 @Column(length = 36,updatable = false, nullable = false)
 private UUID id;
 private String name;
 @Enumerated(EnumType.STRING)
 private ProductType productType;
 private OffsetDateTime creationDate;
 private OffsetDateTime lastModifiedDate;
 private BigDecimal price;

}
