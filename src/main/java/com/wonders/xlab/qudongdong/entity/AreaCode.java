package com.wonders.xlab.qudongdong.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mars on 15/9/7.
 */
@Entity
@Table(name = "qdd_areacode")
public class AreaCode extends AbstractPersistable<Long> {

    /**
     * 名字
     */
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private AreaCode parent;

    /**
     * 孩纸
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<AreaCode> children;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaCode getParent() {
        return parent;
    }

    public void setParent(AreaCode parent) {
        this.parent = parent;
    }

    public Set<AreaCode> getChildren() {
        return children;
    }

    public void setChildren(Set<AreaCode> children) {
        this.children = children;
    }
}
