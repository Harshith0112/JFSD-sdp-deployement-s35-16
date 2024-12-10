package com.studentmanagement.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.studentmanagement.entity.Role;

public class UserDto {
    
    @NotBlank(message = "Username is required")
    @Size(min = 1, message = "Username is required")
    private String userName;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
    
    @NotBlank(message = "First name is required")
    @Size(min = 1, message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 1, message = "Last name is required")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    private Role role;  // Assuming Role is an entity that is properly mapped to the database
    
    public UserDto() {
        // No-argument constructor for Jackson or Spring Data binding
    }

    // Constructor to create UserDto with only the required fields
    public UserDto(String userName, String password, String firstName, String lastName, String email, Role role) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    // Getters and setters for all fields
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
