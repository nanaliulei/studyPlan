package com.liuliu.dynamic.pojo;

import java.util.List;

public interface Composite {

    public List<Composite> getMenuItems();

    public void display();
}
