// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.icraft.iras.model;

import com.icraft.iras.model.Resource;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect ResourceDataOnDemand_Roo_DataOnDemand {
    
    declare @type: ResourceDataOnDemand: @Component;
    
    private Random ResourceDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Resource> ResourceDataOnDemand.data;
    
    public Resource ResourceDataOnDemand.getNewTransientResource(int index) {
        com.icraft.iras.model.Resource obj = new com.icraft.iras.model.Resource();
        obj.setEmailAddress("emailAddress_" + index);
        obj.setExpectedSalary(new Integer(index).doubleValue());
        obj.setFullName("fullName_" + index);
        obj.setYearsOfExperience(new Integer(index));
        return obj;
    }
    
    public Resource ResourceDataOnDemand.getSpecificResource(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Resource obj = data.get(index);
        return Resource.findResource(obj.getId());
    }
    
    public Resource ResourceDataOnDemand.getRandomResource() {
        init();
        Resource obj = data.get(rnd.nextInt(data.size()));
        return Resource.findResource(obj.getId());
    }
    
    public boolean ResourceDataOnDemand.modifyResource(Resource obj) {
        return false;
    }
    
    public void ResourceDataOnDemand.init() {
        data = com.icraft.iras.model.Resource.findResourceEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Resource' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<com.icraft.iras.model.Resource>();
        for (int i = 0; i < 10; i++) {
            com.icraft.iras.model.Resource obj = getNewTransientResource(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
