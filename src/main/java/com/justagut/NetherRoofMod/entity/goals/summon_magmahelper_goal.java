package com.justagut.NetherRoofMod.entity.goals;

import com.justagut.NetherRoofMod.entity.client.MagmaBossAnimations;
import com.justagut.NetherRoofMod.entity.custom.MagmaBossEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class summon_magmahelper_goal<T extends Mob> extends Goal {
    private final T mob;
    public static int tickcount;

    @Override
    public void start() {
        tickcount = 0;
    }

    public summon_magmahelper_goal(T mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }
    @Override
    public boolean canUse() {
        return Math.floor(Math.random() * 6 + 1) == 1;
    }

    @Override
    public boolean canContinueToUse() {
        return tickcount< 100;
    }

    @Override
    public void tick() {
        ++tickcount;

    }
}
