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
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_no")
    private String roleNo;

    /**
     * mappedBy表示role为关系的被维护端
     */
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    @JsonIgnore//序列化时忽略此参数，避免出现死循环
    private List<User> users;

    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"))
    private List<Permission> permissions;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleNo='" + roleNo + '\'' +
                '}';
    }
}
