package net.glowstone.io.entity;

import net.glowstone.entity.monster.GlowPigZombie;
import net.glowstone.entity.monster.GlowZombie;
import net.glowstone.util.nbt.CompoundTag;

import java.util.UUID;

class PigZombieStore extends ZombieStore<GlowPigZombie> {

    public PigZombieStore() {
        super(GlowPigZombie.class, "PigZombie");
    }

    @Override
    public void load(GlowZombie entity, CompoundTag tag) {
        super.load(entity, tag);
        if (tag.isInt("Anger")) {
            ((GlowPigZombie) entity).setAnger(tag.getInt("Anger"));
        } else {
            ((GlowPigZombie) entity).setAnger(0);
        }

        if (tag.isString("HurtBy")) {
            try {
                UUID uuid = UUID.fromString(tag.getString("HurtBy"));
                ((GlowPigZombie) entity).setHurtBy(uuid);
            } catch (IllegalArgumentException ex) {
                ((GlowPigZombie) entity).setHurtBy(null);
            }

        } else {
            ((GlowPigZombie) entity).setHurtBy(null);
        }
    }

    @Override
    public void save(GlowZombie entity, CompoundTag tag) {
        super.save(entity, tag);
        tag.putInt("Anger", ((GlowPigZombie) entity).getAnger());
        if (((GlowPigZombie) entity).getHurtBy() != null) {
            tag.putString("HurtBy", ((GlowPigZombie) entity).getHurtBy().toString());
        } else {
            if (tag.containsKey("HurtBy")) {
                tag.remove("HurtBy");
            }
        }
    }

}
