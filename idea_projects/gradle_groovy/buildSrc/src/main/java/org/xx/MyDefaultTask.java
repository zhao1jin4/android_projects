package org.xx;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class MyDefaultTask extends DefaultTask {
    public MyDefaultTask() {
        setGroup("org.xx");
    }
    @TaskAction //在执行阶段，类似的doFirst,doLast运行在里
    public void doAction()
    {
        mydoLast();
    }
    public void mydoLast()
    {
        MyExtension myPluginTask=(MyExtension) getProject().getExtensions().getByName("myExtension");
        System.out.println("in MyDefaultTask get department is "+myPluginTask.getDepartment());
    }
}
