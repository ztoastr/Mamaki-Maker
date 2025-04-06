package net.ztoast.mamaki.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.AttackWithOwnerGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.PounceAtTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.TrackOwnerAttackerGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ztoast.mamaki.entity.MamakiEntities;
import net.ztoast.mamaki.item.MamakiItems;
import net.ztoast.mamaki.utill.MamakiTags;

public class MenaceEntity extends TameableEntity{
    private static final float WILD_MAX_HEALTH = 10.0f;
    private static final float TAME_MAX_HEALTH = 40.0f;
    private static final float DEFAULT_MOVEMENT_SPEED = 0.25f;
    private static final float DEFAULT_FOLLOW_RANGE = 30.0f;
    private static final float DEFAULT_ATTACK_DAMAGE = 3.0f;

    public MenaceEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false, false);
        this.setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1.0f);
        this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1.0f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(0, new TameableEntity.TameableEscapeDangerGoal(1.5, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new PounceAtTargetGoal(this, 0.4f));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.0F, true));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.0F, 10.0F, 3.0F));
        this.goalSelector.add(5, new AnimalMateGoal(this, 1.05D));
        this.goalSelector.add(6, new TemptGoal(this, 1.1D, Ingredient.ofItems(MamakiItems.BAKED_SWEET_POTATO), false));
        this.goalSelector.add(6, new TemptGoal(this, 1.1D, Ingredient.ofItems(MamakiItems.SWEET_POTATO), false));
        this.goalSelector.add(7, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(10, new LookAroundGoal(this));

        this.targetSelector.add(0, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(1, new AttackWithOwnerGoal(this));
        this.targetSelector.add(2, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, WILD_MAX_HEALTH)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, DEFAULT_FOLLOW_RANGE);
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        super.playStepSound(pos, state);
    }

    public boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
        if (target instanceof PlayerEntity) {
            // Don't attack other players
            return false;
        } else if (target instanceof TameableEntity) {
            // Don't attack other tamed mobs
            return !((TameableEntity)target).isTamed();
        } else if (target instanceof AbstractHorseEntity) {
            // Don't attack tamed horses, they don't extend TameableEntity apparently
            return !((AbstractHorseEntity)target).isTame();
        } else if (target instanceof CreeperEntity || target instanceof GhastEntity || target instanceof ArmorStandEntity) {
            // Don't attack armor stands or exploding mobs
            return false;
        }
        return true;
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        ActionResult result = super.interactMob(player, hand);
        if (stack.isIn(MamakiTags.Items.MENACE_FOOD)) {
            if (this.isTamed()) {
                if (this.getHealth() < this.getMaxHealth()) {
                    stack.decrementUnlessCreative(1, player);
                    FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
                    this.heal(2.0f * (float)foodComponent.nutrition());
                    return ActionResult.success(this.getWorld().isClient());
                } else if (this.getWorld().isClient && !this.isBaby() && this.isOwner(player)) {
                    return ActionResult.CONSUME;
                }
            } else {
                stack.decrementUnlessCreative(1, player);
                this.tryTame(player);
                return ActionResult.SUCCESS;
            }
        }
        return result;
    }

    private void tryTame(PlayerEntity player) {
        if (this.random.nextInt(3) == 0) {
            this.setOwner(player);
            this.navigation.stop();
            this.setTarget((LivingEntity)null);
            this.getWorld().sendEntityStatus(this, (byte)7);
        } else {
            this.getWorld().sendEntityStatus(this, (byte)6);
        }
    }

    protected void updateAttributesForTamed() {
        if (this.isTamed()) {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(TAME_MAX_HEALTH);
            this.setHealth(TAME_MAX_HEALTH);
        } else {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(WILD_MAX_HEALTH);
        }
    }

    public Vec3d getLeashOffset() {
        return new Vec3d(0.0, (double)(this.getStandingEyeHeight() * 0.9f), (double)(this.getWidth() * 0.1f));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(MamakiTags.Items.MENACE_FOOD);
    }

    public boolean canBreedWith(AnimalEntity other) {
        if (other == this || !this.isTamed()) {
            // Don't breed with self or if not tamed
            return false;
        } else if (!(other instanceof MenaceEntity)) {
            // Don't breed with non Menace mobs
            return false;
        } else {
            MenaceEntity parent = (MenaceEntity)other;
            if (!parent.isTamed() || parent.getOwner() != this.getOwner()) {
                // Other parent must be tamed and have same owner
                return false;
            }
            return this.isInLove() && other.isInLove();
        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        MenaceEntity child = (MenaceEntity)MamakiEntities.MENACE.create(world);
        if (this.isTamed()) {
            child.setOwnerUuid(this.getOwnerUuid());
            child.setTamed(true, true);
        }
        return child;
    }
    
}
