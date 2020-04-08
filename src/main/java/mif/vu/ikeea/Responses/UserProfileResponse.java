package mif.vu.ikeea.Responses;

import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.User;

import java.util.*;

public class UserProfileResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private Set<Role> roles;
    private Long teamId;
    private String managerFirstName;
    private String managerLastName;
    private Integer learningDays;

    public UserProfileResponse(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.enabled = user.getEnabled();
        this.roles = user.getRoles();
        this.teamId = user.getTeam().getId();
        this.managerFirstName = user.getManager().getFirstName();
        this.managerLastName = user.getManager().getLastName();
        //learning days from restriction
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLearningDays(Integer learningDays) {
        this.learningDays = learningDays;
    }

    public Integer getLearningDays() {
        return learningDays;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
