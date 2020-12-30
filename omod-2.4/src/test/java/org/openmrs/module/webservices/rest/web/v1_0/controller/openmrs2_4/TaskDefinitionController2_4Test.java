/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.webservices.rest.web.v1_0.controller.openmrs2_4;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.test.Util;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;
<<<<<<< HEAD
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs2_4.RestTestConstants2_4;
=======
>>>>>>> b524f8f3629b035e91cc364e6ed9e07721c0901a
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs2_4.TaskDefinitionResource2_4;
import org.openmrs.module.webservices.rest.web.v1_0.web.MockTaskServiceWrapper2_4;
import org.openmrs.scheduler.TaskDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class TaskDefinitionController2_4Test extends MainResourceControllerTest {

    @Autowired
    RestService restService;

    private TaskDefinition testTask = new TaskDefinition(1, "TestTask", "TestTask Description",
            "org.openmrs.scheduler.tasks.TestTask");

    private MockTaskServiceWrapper2_4 mockTaskServiceWrapper = new MockTaskServiceWrapper2_4();

    @Before
    public void setUp() throws Exception {
        testTask.setRepeatInterval(10L);
        testTask.setStartOnStartup(true);
        testTask.setProperty("propertyKey", "propertyValue");

        mockTaskServiceWrapper.registeredTasks.add(testTask);

        TaskDefinitionResource2_4 taskResource = (TaskDefinitionResource2_4) restService
                .getResourceBySupportedClass(TaskDefinition.class);
        taskResource.setTaskServiceWrapper(mockTaskServiceWrapper);
    }

    /**
     * Get all registered tasks - Used to test for registered tasks.
     *
     * @throws Exception
     */
    @Test
    @Override
    public void shouldGetAll() throws Exception {
        MockHttpServletRequest req = request(RequestMethod.GET, getURI());
        SimpleObject resultTasks = deserialize(handle(req));
        List<Object> results = Util.getResultsList(resultTasks);
        Assert.assertNotNull(resultTasks);
        Assert.assertEquals(results.size(), getAllCount());
    }

    /**
     * Get all scheduled tasks - Used to test for scheduled tasks.
     *
     * @throws Exception
     */
    @Test
    public void shouldGetAllScheduled() throws Exception {
        MockHttpServletRequest req = request(RequestMethod.GET, getURI());
        req.addParameter("q", "scheduled");
        SimpleObject resultTasks = deserialize(handle(req));
        List<Object> results = Util.getResultsList(resultTasks);
        Assert.assertNotNull(resultTasks);
        Assert.assertEquals(results.size(), getAllScheduledCount());
    }

    @Test
    public void shouldGetTaskByUuid() throws Exception {
        MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
        SimpleObject result = deserialize(handle(req));
        Assert.assertNotNull(result.get("name"));
        Assert.assertNotNull(result.get("description"));
        Assert.assertNotNull(result.get("taskClass"));
    }


    @Override
    public void shouldGetFullByUuid() throws Exception {
        MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
        req.addParameter("v", "full");

        SimpleObject result = deserialize(handle(req));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "name"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "description"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "taskClass"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "repeatInterval"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "startOnStartup"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "started"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "properties"));
    }

    @Test
    @Override
    public void shouldGetDefaultByUuid() throws Exception {
        MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
        SimpleObject result = deserialize(handle(req));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "name"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "description"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "taskClass"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "startTime"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "started"));
    }


    @Test
    @Override
    public void shouldGetRefByUuid() throws Exception {
        MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
        req.addParameter("v", "ref");
        SimpleObject result = deserialize(handle(req));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "name"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "description"));
        Assert.assertNotNull(PropertyUtils.getProperty(result, "taskClass"));
    }

    @Test
    public void shouldSaveTaskDefinition() throws Exception {
        SimpleObject mockTask = new SimpleObject();
        mockTask.add("name", "MockTask");
        mockTask.add("description", "MockTask Description");
        mockTask.add("taskClass", "org.openmrs.scheduler.tasks.TestTask");
<<<<<<< HEAD
        mockTask.add("startTime", "2020-08-28T23:59:59.000+0530");
=======
        mockTask.add("startTime", "2017-08-28T23:59:59.000+0530");
>>>>>>> b524f8f3629b035e91cc364e6ed9e07721c0901a
        mockTask.add("repeatInterval", "10");
        mockTask.add("startOnStartup", false);
        mockTask.add("properties", null);
        String json = new ObjectMapper().writeValueAsString(mockTask);

<<<<<<< HEAD
        Assert.assertNull(mockTaskServiceWrapper.getTaskByName("MockTask Description"));
        MockHttpServletRequest req = request(RequestMethod.POST, getURI());
        req.setContent(json.getBytes());
        deserialize(handle(req));
        Assert.assertEquals(mockTaskServiceWrapper.getTaskByName("MockTask").getDescription(), "MockTask Description");
=======
        Assert.assertNull(mockTaskServiceWrapper.getTaskByName("MockTask"));
        MockHttpServletRequest req = request(RequestMethod.POST, getURI());
        req.setContent(json.getBytes());
        deserialize(handle(req));
        Assert.assertEquals(mockTaskServiceWrapper.getTaskByName("MockTask").getName(), "MockTask");
>>>>>>> b524f8f3629b035e91cc364e6ed9e07721c0901a
    }

    @Override
    public String getURI() {
        return "taskdefinition";
    }

    @Override
    public String getUuid() {
<<<<<<< HEAD
        return RestTestConstants2_4.TASK_DEFINITION_UUID;
=======
        return getTestTaskName();
>>>>>>> b524f8f3629b035e91cc364e6ed9e07721c0901a
    }

    @Override
    public long getAllCount() {
        return 0;
    }

    private String getTestTaskName() {
        return "TestTask";
    }

    public long getAllRegisteredCount() {
        return mockTaskServiceWrapper.registeredTasks.size();
    }

    public long getAllScheduledCount() {
        return mockTaskServiceWrapper.scheduledTasks.size();
    }
}
