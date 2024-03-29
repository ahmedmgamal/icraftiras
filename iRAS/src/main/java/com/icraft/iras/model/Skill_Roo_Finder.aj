// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.icraft.iras.model;

import com.icraft.iras.model.Skill;
import java.lang.String;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Skill_Roo_Finder {
    
    public static TypedQuery<Skill> Skill.findSkillsByName(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Skill.entityManager();
        TypedQuery<Skill> q = em.createQuery("SELECT Skill FROM Skill AS skill WHERE skill.name = :name", Skill.class);
        q.setParameter("name", name);
        return q;
    }
    
}
