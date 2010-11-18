// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.icraft.iras.model;

import com.icraft.iras.model.Lvl;
import java.lang.String;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Lvl_Roo_Finder {
    
    public static TypedQuery<Lvl> Lvl.findLvlsByName(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Lvl.entityManager();
        TypedQuery<Lvl> q = em.createQuery("SELECT Lvl FROM Lvl AS lvl WHERE lvl.name = :name", Lvl.class);
        q.setParameter("name", name);
        return q;
    }
    
}
