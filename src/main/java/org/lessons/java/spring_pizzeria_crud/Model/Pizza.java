package org.lessons.java.spring_pizzeria_crud.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizze")
public class Pizza {
    
    // ID
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) // Create a new AI ID
    private Integer id;
    
    // Name
    @NotBlank
    @NotNull
    // @Min(value = 5, message="Pizza name must be more than 5 characters")
    private String name;

    // Description
    @NotBlank
    private String description;

    // url photo
    private String photoUrl;

    // Price
    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be more than 1")
    private Double price;


    @Override
    public String toString(){
        return String.format("Nome Pizza: %s\nDescrizione: %s\nPrezzo: %d", name, description, price);
    }

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl the photoUrl to set
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * @return Double return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

}
