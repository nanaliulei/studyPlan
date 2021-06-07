package com.liuliu.factory.material;

import com.liuliu.dynamic.pojo.cheese.Cheese;
import com.liuliu.dynamic.pojo.dough.Dough;
import com.liuliu.dynamic.pojo.sauce.Sauce;

public interface MaterialFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
}
