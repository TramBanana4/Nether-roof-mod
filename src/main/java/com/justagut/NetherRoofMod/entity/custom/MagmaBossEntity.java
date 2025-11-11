package com.justagut.NetherRoofMod.entity.custom;


import com.justagut.NetherRoofMod.entity.goals.dash_goal;
import com.justagut.NetherRoofMod.entity.goals.meteorite_rain_goal;
import com.justagut.NetherRoofMod.entity.goals.summon_magmahelper_goal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagmaBossEntity extends Monster {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public boolean doinggoal = false;
    public int selectedgoal = 0;

    public MagmaBossEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 0;
        this.fireImmune();
    }
    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {

    }
    public void doinggoal(boolean value) {
        doinggoal = value;
    }

    @Override
    protected void registerGoals() {

            this.goalSelector.addGoal(3, new summon_magmahelper_goal<>(this));
            this.goalSelector.addGoal(4, new meteorite_rain_goal<>(this));
            this.goalSelector.addGoal(2, new dash_goal<>(this));
            this.targetSelector.addGoal(1,
                    new NearestAttackableTargetGoal<>(this, Player.class, false));
    }
    @Override
    protected float getJumpPower() {
        return 0.4F; // default is around 0.42F for most mobs
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        // check if the damage is caused by a fireball (any Fireball subclass)
        if (source.getDirectEntity() instanceof Fireball) {
            return false; // cancel damage completely
        }

        // otherwise, handle damage normally
        return super.hurt(source, amount);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 120d)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D)
                .add(Attributes.ATTACK_DAMAGE, 5D)
                .add(Attributes.ATTACK_KNOCKBACK, 1D)
                ;

    }





    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
        if (getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D) {
            // start animation if not already running
            if (walkAnimationState.isStarted()) {
                walkAnimationState.start(tickCount);
            }
        } else {
            // stop animation if the entity stops walking
            walkAnimationState.stop();
        }
    }
}
