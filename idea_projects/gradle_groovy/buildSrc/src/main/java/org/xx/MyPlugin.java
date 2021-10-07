package org.xx;

import org.gradle.api.Plugin;
import org.gradle.api.Project;


class MyPlugin implements Plugin<Project> {
    public void apply(Project project) {
        //实现,task要修改为project.task
        if(project.getPlugins().hasPlugin("java"))
        {
            System.out.println ("In MyPlugin query has java plugin");
        }
        project.task ("myPluginTask", (task1)->{
            System.out.println ( "configuration myPluginTask ");
            task1.doLast((task2)->{
                    System.out.println ( "exeuction myPluginTask ");
            });

        });//建立Task

        project.getExtensions().create("myExtension", MyExtension.class);
       // project.getTasks().getByName("myPluginTask").getExtensions().create("myPluginSubTask",MySubExtension.class);

//        StringUtils.isNotBlank("");//这里如何引用第三方库????
    }
}