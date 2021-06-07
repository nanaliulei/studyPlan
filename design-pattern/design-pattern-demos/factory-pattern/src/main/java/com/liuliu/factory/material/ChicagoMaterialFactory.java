package com.liuliu.factory.material;

import com.liuliu.dynamic.pojo.cheese.Cheese;
import com.liuliu.dynamic.pojo.cheese.SoftCheese;
import com.liuliu.dynamic.pojo.dough.Dough;
import com.liuliu.dynamic.pojo.dough.ThickDough;
import com.liuliu.dynamic.pojo.sauce.SaladSauce;
import com.liuliu.dynamic.pojo.sauce.Sauce;

public class ChicagoMaterialFactory implements MaterialFactory {
    @Override
    public Dough createDough() {
        return new ThickDough();
    }

    @Override
    public Sauce createSauce() {
        return new SaladSauce();
    }

    @Override
    public Cheese createCheese() {
        return new SoftCheese();
    }
}
