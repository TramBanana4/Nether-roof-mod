package com.justagut.NetherRoofMod.entity.goals;

import com.justagut.NetherRoofMod.entity.client.MagmaBossModel;
import com.justagut.NetherRoofMod.entity.custom.MagmaBossEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.naming.Context;
import java.util.EnumSet;

public class meteorite_rain_goal<T extends Mob> extends Goal
 {
     private int maxattacks;
    protected final T mob;
    private int ticksUntilNextAttack;
    private int attackcount;
    public meteorite_rain_goal(T mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));

    }

    public boolean canUse() {
        if (this.mob instanceof MagmaBossEntity magmaboss){
            System.out.println("meteorite   " + !magmaboss.doinggoal);
            return !magmaboss.doinggoal;
        }else{
        return  false;
    }}
    public boolean canContinueToUse(){
        return attackcount != 10;
    }
    public void start(){
        maxattacks = (int) Math.floor(Math.random()* 6 + 5);
        ticksUntilNextAttack = 20;
        attackcount = 0;
        if (this.mob instanceof MagmaBossEntity magmaboss){
            magmaboss.doinggoal(true);
            System.out.println(magmaboss.doinggoal);
        }
    }
    public void stop(){
        if (this.mob instanceof MagmaBossEntity magmaboss){
            magmaboss.doinggoal(false);
        }
    }
    public void tick(){
        --ticksUntilNextAttack;
        if (ticksUntilNextAttack == 0){
            ++attackcount;
            ticksUntilNextAttack = 20;
            summonmeteorite();
        }
    }
    public void summonmeteorite(){

        LargeFireball fireball = new LargeFireball(EntityType.FIREBALL, mob.level()) {
            // override explosion strength and ensure it collides with blocks
            @Override
            protected void onHitEntity(EntityHitResult result) {
                super.onHitEntity(result);
                explode();
            }

            @Override
            protected void onHitBlock(BlockHitResult result) {
                super.onHitBlock(result);
                explode(); // trigger explosion on block hit
            }

            private void explode() {
                if (!this.level().isClientSide) {
                    this.level().explode(
                            this,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            2.0F,
                            Level.ExplosionInteraction.NONE
                    );
                    this.discard();
                }
            }
        };

        fireball.setPos(mob.getX() + Math.random() * 20 - 10, mob.getY() + 100, mob.getZ() + Math.random() * 20 - 10);
        fireball.setDeltaMovement(0,-1,0);
        mob.level().addFreshEntity(fireball);
    }
}
