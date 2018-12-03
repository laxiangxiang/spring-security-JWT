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
@Table(name = "parent_menu")
public class ParentMenu implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parent_menu_name")
    private String parentMenuName;

    @Column(name = "parent_menu_no")
    private String parentMenuNo;

    @ManyToMany(mappedBy = "parentMenus",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Permission> permissions;

    @OneToMany(mappedBy = "parentMenu",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private List<ChildMenu> childMenus;

    @Override
    public String toString() {
        return "ParentMenu{" +
                "id=" + id +
                ", parentMenuName='" + parentMenuName + '\'' +
                ", parentMenuNo='" + parentMenuNo + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o){
        ParentMenu p = (ParentMenu) o;
        if (this.id == p.getId() && this.parentMenuName == p.getParentMenuName() && this.parentMenuNo == p.getParentMenuNo()){
            return true;
        }else {
            return false;
        }
    }

}
