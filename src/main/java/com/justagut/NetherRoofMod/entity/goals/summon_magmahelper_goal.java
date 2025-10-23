package com.justagut.NetherRoofMod.entity.goals;

import com.justagut.NetherRoofMod.entity.ModEntities;
import com.justagut.NetherRoofMod.entity.client.MagmaBossAnimations;
import com.justagut.NetherRoofMod.entity.custom.MagmaBossEntity;
import com.justagut.NetherRoofMod.entity.custom.MagmaHelperEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.EnumSet;

public class summon_magmahelper_goal<T extends Mob> extends Goal {
    private final T mob;
    private int tickcount;



    public summon_magmahelper_goal(T mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));

    }

    public boolean canUse() {
        if (this.mob instanceof MagmaBossEntity magmaboss){
            int random = (int) Math.floor(Math.random() * 6 + 1);
            boolean randomistrue = random == 1;
            System.out.println("notdoinggoal  " +!magmaboss.doinggoal);
            System.out.println("random  " + randomistrue);


            return !magmaboss.doinggoal && randomistrue;
        }else{
            return  false;
        }
    }


    public boolean canContinueToUse() {
        return tickcount< 100;
    }
    public void start(){
        if(this.mob instanceof MagmaBossEntity magmaboss){
            magmaboss.doinggoal(true);
            System.out.println(magmaboss.doinggoal);
        }
        tickcount = 0;
    }
    public void stop(){
        if(this.mob instanceof MagmaBossEntity magmaboss){
            magmaboss.doinggoal(false);
        }
    }


    public void tick() {
        ++tickcount;
        if (tickcount== 50){
            summonhelpers();
        }
    }
    public void summonhelpers(){
        for(int i = 0; i < 3;i++){
            MagmaHelperEntity magmahelper = new MagmaHelperEntity(ModEntities.MAGMAHELPER.get(), mob.level()) {
            };
        magmahelper.setPos(mob.getX() + Math.random() * 5 - 2.5,mob.getY(), mob.getZ() + Math.random() * 5 - 2.5);
        mob.level().addFreshEntity(magmahelper);
    }
    }
}
