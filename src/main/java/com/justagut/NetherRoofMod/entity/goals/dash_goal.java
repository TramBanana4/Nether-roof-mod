package com.justagut.NetherRoofMod.entity.goals;

import com.justagut.NetherRoofMod.entity.custom.MagmaBossEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import java.util.EnumSet;

import static net.minecraft.world.level.block.TntBlock.explode;

public class dash_goal<T extends Mob> extends Goal {
    private final T mob;
    public float dashrunspeed = 0.7f;
    public float xvel = 0;
    public float zvel = 0;
    public int tickcount = 0;
    public float yaw = 0;
    public int dashamount;
    public int dashesused;
    public boolean starteddash;
    public dash_goal(T mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));

    }
    public void stop(){
        if (this.mob instanceof MagmaBossEntity magmaboss){
            magmaboss.doinggoal(false);
        }
    }

    @Override
    public void start() {
        dashamount = 1;
        starteddash = false;
        dashesused = 0;
    }

    @Override
    public boolean canUse() {
        if (this.mob instanceof MagmaBossEntity magmaboss){
            return !magmaboss.doinggoal && magmaboss.selectedgoal == 2;
        }else{
            return  false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return dashamount != dashesused;
    }

    @Override
    public void tick() {


        if (!starteddash) {
            if(this.mob.getTarget() != null) {


                double dx = this.mob.getTarget().getX() - this.mob.getX();
                double dz = this.mob.getTarget().getZ() - this.mob.getZ();
                yaw = (float) (Mth.atan2(dz, dx) * (180F / Math.PI)) + 90;


                this.mob.setYBodyRot(yaw);
                this.mob.setYRot(yaw);
                starteddash = true;
                tickcount = 0;
                float yawinradians = (float) ((yaw + 180) * Math.PI / 180);
                xvel = (float) (-Math.sin(yawinradians) * dashrunspeed);
                zvel = (float) (Math.cos(yawinradians) * dashrunspeed);
            }
        } else {
            ++tickcount;
            if (tickcount > 10) {
                if (tickcount < 25) {
                    this.mob.setDeltaMovement(xvel, this.mob.getDeltaMovement().y, zvel);
                    AABB box = this.mob.getBoundingBox();
                    for (BlockPos pos : BlockPos.betweenClosed(
                            Mth.floor(box.minX), Mth.floor(box.minY+ 0.8), Mth.floor(box.minZ),
                            Mth.floor(box.maxX), Mth.floor(box.maxY), Mth.floor(box.maxZ))) {

                        BlockState state = this.mob.level().getBlockState(pos);
                        Entity player = this.mob.level().getNearestPlayer(this.mob,1);
                        if (!state.isAir() || player != null) {
                            this.mob.level().explode(
                                    this.mob,
                                    this.mob.getX(),
                                    this.mob.getY()+ 3,
                                    this.mob.getZ(),
                                    4F,
                                    Level.ExplosionInteraction.NONE
                            );
                            this.mob.level().explode(
                                    this.mob,
                                    this.mob.getX(),
                                    this.mob.getY()+ 3,
                                    this.mob.getZ(),
                                    2F,
                                    Level.ExplosionInteraction.NONE
                            );
                            starteddash = false;
                            this.mob.setDeltaMovement(xvel / 10, this.mob.getDeltaMovement().y, zvel / 10);
                            dashesused += 1;
                        }
                    }
                }
                else {
                    starteddash = false;
                    this.mob.setDeltaMovement(xvel / 10, this.mob.getDeltaMovement().y, zvel / 10);
                    dashesused += 1;
                }
            }
        }
    }
}