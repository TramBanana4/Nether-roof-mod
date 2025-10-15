package com.justagut.NetherRoofMod.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class MagmaBoss extends Monster {
    public MagmaBoss(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false; // Never despawns
    }
}
