package com.liuliu.factory.material;

import com.liuliu.dynamic.pojo.cheese.Cheese;
import com.liuliu.dynamic.pojo.cheese.FreshCheese;
import com.liuliu.dynamic.pojo.dough.Dough;
import com.liuliu.dynamic.pojo.dough.ThinDough;
import com.liuliu.dynamic.pojo.sauce.Sauce;
import com.liuliu.dynamic.pojo.sauce.TomatoSauce;

public class NYMaterialFactory implements MaterialFactory {
    @Override
    public Dough createDough() {
        return new ThinDough();
    }

    @Override
    public Sauce createSauce() {
        return new TomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new FreshCheese();
    }
}
