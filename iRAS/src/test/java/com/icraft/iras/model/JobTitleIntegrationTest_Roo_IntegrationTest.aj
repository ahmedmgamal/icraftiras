// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.icraft.iras.model;

import com.icraft.iras.model.JobTitleDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect JobTitleIntegrationTest_Roo_IntegrationTest {
    
    declare @type: JobTitleIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: JobTitleIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: JobTitleIntegrationTest: @Transactional;
    
    @Autowired
    private JobTitleDataOnDemand JobTitleIntegrationTest.dod;
    
    @Test
    public void JobTitleIntegrationTest.testCountJobTitles() {
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to initialize correctly", dod.getRandomJobTitle());
        long count = com.icraft.iras.model.JobTitle.countJobTitles();
        org.junit.Assert.assertTrue("Counter for 'JobTitle' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void JobTitleIntegrationTest.testFindJobTitle() {
        com.icraft.iras.model.JobTitle obj = dod.getRandomJobTitle();
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to provide an identifier", id);
        obj = com.icraft.iras.model.JobTitle.findJobTitle(id);
        org.junit.Assert.assertNotNull("Find method for 'JobTitle' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'JobTitle' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void JobTitleIntegrationTest.testFindAllJobTitles() {
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to initialize correctly", dod.getRandomJobTitle());
        long count = com.icraft.iras.model.JobTitle.countJobTitles();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'JobTitle', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<com.icraft.iras.model.JobTitle> result = com.icraft.iras.model.JobTitle.findAllJobTitles();
        org.junit.Assert.assertNotNull("Find all method for 'JobTitle' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'JobTitle' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void JobTitleIntegrationTest.testFindJobTitleEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to initialize correctly", dod.getRandomJobTitle());
        long count = com.icraft.iras.model.JobTitle.countJobTitles();
        if (count > 20) count = 20;
        java.util.List<com.icraft.iras.model.JobTitle> result = com.icraft.iras.model.JobTitle.findJobTitleEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'JobTitle' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'JobTitle' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void JobTitleIntegrationTest.testFlush() {
        com.icraft.iras.model.JobTitle obj = dod.getRandomJobTitle();
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to provide an identifier", id);
        obj = com.icraft.iras.model.JobTitle.findJobTitle(id);
        org.junit.Assert.assertNotNull("Find method for 'JobTitle' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyJobTitle(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'JobTitle' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void JobTitleIntegrationTest.testMerge() {
        com.icraft.iras.model.JobTitle obj = dod.getRandomJobTitle();
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to provide an identifier", id);
        obj = com.icraft.iras.model.JobTitle.findJobTitle(id);
        boolean modified =  dod.modifyJobTitle(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        com.icraft.iras.model.JobTitle merged = (com.icraft.iras.model.JobTitle) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'JobTitle' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void JobTitleIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to initialize correctly", dod.getRandomJobTitle());
        com.icraft.iras.model.JobTitle obj = dod.getNewTransientJobTitle(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'JobTitle' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'JobTitle' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void JobTitleIntegrationTest.testRemove() {
        com.icraft.iras.model.JobTitle obj = dod.getRandomJobTitle();
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'JobTitle' failed to provide an identifier", id);
        obj = com.icraft.iras.model.JobTitle.findJobTitle(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'JobTitle' with identifier '" + id + "'", com.icraft.iras.model.JobTitle.findJobTitle(id));
    }
    
}
