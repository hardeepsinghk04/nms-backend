package com.vvdn.nms.nms_backend.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String customerType;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String isoCountry;
    private String phone;

    // ✅ 🆕 Role-based access mapping
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    // 🧱 No-arg constructor (required by JPA)
    public User() {}

    // 🧱 Constructor with roles
    public User(Long id, String customerType, String email, String password,
                String firstName, String lastName, String isoCountry, String phone,
                Set<String> roles) {
        this.id = id;
        this.customerType = customerType;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isoCountry = isoCountry;
        this.phone = phone;
        this.roles = roles;
    }

    // ✅ Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getIsoCountry() { return isoCountry; }
    public void setIsoCountry(String isoCountry) { this.isoCountry = isoCountry; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
