package com.justagut.NetherRoofMod.entity.goals;

import com.justagut.NetherRoofMod.entity.custom.MagmaBossEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class dash_goal<T extends Mob> extends Goal {
    private final T mob;
    public int dashamount;
    public int dashesused;
    public boolean starteddash;
    public dash_goal(T mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));

    }

    @Override
    public void start() {
        dashamount = (int) Math.floor(Math.random()* 3 + 2);
        starteddash = false;
        dashesused = 0;
    }

    @Override
    public boolean canUse() {
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return dashamount != dashesused;
    }

    @Override
    public void tick() {
        if (!starteddash && this.mob.getTarget() != null){

            double dx = this.mob.getTarget().getX() - this.mob.getX();
            double dz = this.mob.getTarget().getZ() - this.mob.getZ();
            float yaw = (float)(Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F;

            // Instantly set rotation
            System.out.println(yaw);
            this.mob.setYRot(yaw);
        }
    }
}
