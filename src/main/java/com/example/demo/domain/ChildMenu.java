package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by LXX on 2018/11/28.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "child_menu")
public class ChildMenu implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "child_menu_name")
    private String childMenuName;

    @Column(name = "child_menu_no")
    private String childMenuNo;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "parent_menu_id",referencedColumnName = "id")
    @JsonIgnore
    private ParentMenu parentMenu;

    /**
     * 避免无限递归，内存溢出
     * @return
     */
    @Override
    public String toString() {
        return "ChildMenu{" +
                "id=" + id +
                ", childMenuName='" + childMenuName + '\'' +
                ", childMenuNo='" + childMenuNo + '\'' +
                '}';
    }
}
