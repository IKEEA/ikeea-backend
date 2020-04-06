package mif.vu.ikeea.Responses;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.ERole;

import java.util.*;

public class UserProfileResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private Set<Role> roles;
    private Long team_id;
    private String manager_firstName;
    private String manager_lastName;
    private Integer learningDays;

    public UserProfileResponse(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.enabled = user.getEnabled();
        this.roles = user.getRoles();
        this.team_id = user.getTeam().getId();
        this.manager_firstName = user.getManager().getFirstName();
        this.manager_lastName = user.getManager().getLastName();
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

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setManager_lastName(String manager_lastName) {
        this.manager_lastName = manager_lastName;
    }

    public String getManager_lastName() {
        return manager_lastName;
    }

    public void setManager_firstName(String manager_firstName) {
        this.manager_firstName = manager_firstName;
    }

    public String getManager_firstName() {
        return manager_firstName;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
