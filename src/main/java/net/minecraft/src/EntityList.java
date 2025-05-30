package net.minecraft.src;

import net.peyton.eagler.minecraft.EntityConstructor;

import java.util.HashMap;
import java.util.Map;

public class EntityList {
	private static Map<String, EntityConstructor> stringToClassMapping = new HashMap();
	private static Map classToStringMapping = new HashMap();
	private static Map<Integer, EntityConstructor> IDtoClassMapping = new HashMap();
	private static Map classToIDMapping = new HashMap();

	private static void addMapping(Class var0, EntityConstructor var3, String var1, int var2) {
		stringToClassMapping.put(var1, var3);
		classToStringMapping.put(var0, var1);
		IDtoClassMapping.put(Integer.valueOf(var2), var3);
		classToIDMapping.put(var0, Integer.valueOf(var2));
	}

	public static Entity createEntityByName(String var0, World var1) {
		Entity var2 = null;

		try {
			EntityConstructor var3 = stringToClassMapping.get(var0);
			if(var3 != null) {
				var2 = var3.createEntity(var1);
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	public static Entity createEntityFromNBT(NBTTagCompound var0, World var1) {
		Entity var2 = null;

		try {
			EntityConstructor var3 = stringToClassMapping.get(var0.getString("id"));
			if(var3 != null) {
				var2 = var3.createEntity(var1);
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		if(var2 != null) {
			var2.readFromNBT(var0);
		} else {
			System.out.println("Skipping Entity with id " + var0.getString("id"));
		}

		return var2;
	}

	public static Entity createEntityByID(int var0, World var1) {
		Entity var2 = null;

		try {
			EntityConstructor var3 = IDtoClassMapping.get(Integer.valueOf(var0));
			if(var3 != null) {
				var2 = var3.createEntity(var1);
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		if(var2 == null) {
			System.out.println("Skipping Entity with id " + var0);
		}

		return var2;
	}

	public static int getEntityID(Entity var0) {
		return ((Integer)classToIDMapping.get(var0.getClass())).intValue();
	}

	public static String getEntityString(Entity var0) {
		return (String)classToStringMapping.get(var0.getClass());
	}

	static {
		addMapping(EntityArrow.class, EntityArrow::new, "Arrow", 10);
		addMapping(EntitySnowball.class, EntitySnowball::new, "Snowball", 11);
		addMapping(EntityItem.class, EntityItem::new, "Item", 1);
		addMapping(EntityPainting.class, EntityPainting::new, "Painting", 9);
		addMapping(EntityLiving.class, EntityLiving::new, "Mob", 48);
		addMapping(EntityMob.class, EntityMob::new, "Monster", 49);
		addMapping(EntityCreeper.class, EntityCreeper::new, "Creeper", 50);
		addMapping(EntitySkeleton.class, EntitySkeleton::new, "Skeleton", 51);
		addMapping(EntitySpider.class, EntitySpider::new, "Spider", 52);
		addMapping(EntityGiantZombie.class, EntityGiantZombie::new, "Giant", 53);
		addMapping(EntityZombie.class, EntityZombie::new, "Zombie", 54);
		addMapping(EntityPig.class, EntityPig::new, "Pig", 90);
		addMapping(EntitySheep.class, EntitySheep::new, "Sheep", 91);
		addMapping(EntityTNTPrimed.class, EntityTNTPrimed::new, "PrimedTnt", 20);
		addMapping(EntityFallingSand.class, EntityFallingSand::new, "FallingSand", 21);
		addMapping(EntityMinecart.class, EntityMinecart::new, "Minecart", 40);
		addMapping(EntityBoat.class, EntityBoat::new, "Boat", 41);
	}
}
