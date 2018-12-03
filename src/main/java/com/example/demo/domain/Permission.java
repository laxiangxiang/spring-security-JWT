package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by LXX on 2018/11/28.
 */
@Data
@NoArgsConstructor
@Entity
public class Permission implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "permission_no")
    private String permissionNo;

    @ManyToMany(mappedBy = "permissions" ,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Role> roles;

    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "permission_parent_menu",joinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "parent_menu_id",referencedColumnName = "id"))
    private List<ParentMenu> parentMenus;

    @Override
    public boolean equals(Object o){
        Permission permission = (Permission) o;
        if (this.id == permission.getId() && this.permissionName == permission.getPermissionName() && this.permissionNo == permission.getPermissionNo()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionNo='" + permissionNo + '\'' +
                '}';
    }

}
