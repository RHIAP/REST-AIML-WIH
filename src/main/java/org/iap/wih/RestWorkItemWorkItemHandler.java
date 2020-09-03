/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iap.wih;

import java.util.HashMap;
import java.util.Map;
import org.jbpm.process.workitem.core.AbstractLogOrThrowWorkItemHandler;
import org.jbpm.process.workitem.core.util.RequiredParameterValidator;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.jbpm.process.workitem.core.util.Wid;
import org.jbpm.process.workitem.core.util.WidParameter;
import org.jbpm.process.workitem.core.util.WidResult;
import org.jbpm.process.workitem.core.util.service.WidAction;
import org.jbpm.process.workitem.core.util.service.WidAuth;
import org.jbpm.process.workitem.core.util.service.WidService;
import org.jbpm.process.workitem.core.util.WidMavenDepends;

@Wid(widfile="RestWorkItemDefinitions.wid", name="RestWorkItemDefinitions",
        displayName="RestWorkItemDefinitions",
        defaultHandler="mvel: new org.iap.wih.RestWorkItemWorkItemHandler()",
        documentation = "rest-wih/index.html",
        category = "rest-wih",
        icon = "RestWorkItemDefinitions.png",
        parameters={
            @WidParameter(name="SampleParam", required = true),
            @WidParameter(name="SampleParamTwo", required = true)
        },
        results={
            @WidResult(name="SampleResult")
        },
        mavenDepends={
            @WidMavenDepends(group="org.iap", artifact="rest-wih", version="0.0.1-SNAPSHOT")
        },
        serviceInfo = @WidService(category = "rest-wih", description = "${description}",
                keywords = "",
                action = @WidAction(title = "Sample Title"),
                authinfo = @WidAuth(required = true, params = {"SampleParam"},
                        paramsdescription = {"SampleParam"},
                        referencesite = "referenceSiteURL")
        )
)
public class RestWorkItemWorkItemHandler extends AbstractLogOrThrowWorkItemHandler {
        private String sampleParam;

    public RestWorkItemWorkItemHandler(String SampleParam, String SampleParamTwo){
            this.sampleParam = sampleParam;
        }

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            RequiredParameterValidator.validate(this.getClass(), workItem);

            // sample parameters
            sampleParam = (String) workItem.getParameter("Message");

            // complete workitem impl...

            // return results
            String sampleResult = "Jeff result string goes here";
            Map<String, Object> results = new HashMap<String, Object>();
            results.put("Result", sampleResult);


            manager.completeWorkItem(workItem.getId(), results);
        } catch(Throwable cause) {
            handleException(cause);
        }
    }

    @Override
    public void abortWorkItem(WorkItem workItem,
                              WorkItemManager manager) {
        // stub
    }
}


