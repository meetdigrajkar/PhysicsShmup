package com.mygdx.game.components.collision;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.game.actors.Collidable;
import com.mygdx.game.components.MoneyComponent;
import com.mygdx.game.components.PlayerDataComponent;
import com.mygdx.managers.PhysicsManager;
import com.mygdx.managers.UIManager;
import com.badlogic.gdx.utils.Pool.Poolable;


public class PlayerCollisionComponent implements Component, Collidable, Poolable {
    // This is what happens when you're a bad programmer and/or lazy

    public PlayerCollisionComponent() {}

    @Override
    public void handleCollision(PooledEngine engine, Entity collider, Entity collidee) {
        short type = collidee.getComponent(TypeComponent.class).type;

        if (type == PhysicsManager.COL_MONEY) {
            long moneyVal = collidee.getComponent(MoneyComponent.class).value;
            PlayerDataComponent data = collider.getComponent(PlayerDataComponent.class);
            data.money += moneyVal;
            UIManager.setCash(data.money);
        }
    }

    @Override
    public void reset() {
        // do nothing
    }
}
