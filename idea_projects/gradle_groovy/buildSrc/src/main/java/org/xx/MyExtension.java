package org.xx;

class MyExtension
{
    String department;
    MySubExtension sub;

    public MySubExtension getSub() {
        return sub;
    }

    public void setSub(MySubExtension sub) {
        this.sub = sub;
    }

    public MyExtension() {
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

  }