package com.minetomek.musically;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
	public static final RegistryKey<ItemGroup> ITEM_GROUP_KEY = RegistryKey.of(
			Registries.ITEM_GROUP.getKey(), Identifier.of(Musically.ID, "musically"));
	public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ModItems.WRITTEN_DISC))
			.displayName(Text.translatable("itemGroup.musically"))//.noScrollbar()
			.build();

	public static final Item RECORDING_STYLUS = register(new Item(new Item.Settings().maxCount(16)),
			"recording_stylus");
	public static final Item KELP_SHEET = register(new Item(new Item.Settings()), "kelp_sheet");
	public static final Item MICROPHONE = register(new Item(new Item.Settings().maxCount(16)),
			"microphone");
	public static final Item EMPTY_DISC = register(new Item(new Item.Settings().maxCount(1)), "empty_disc");
	public static final Item WRITTEN_DISC = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)), "written_disc");
	// public static final Item ITEM_NAME = registerItem(new Item(new
	// Item.Settings()), "item_name");

	public static Item register(Item item, String id) {
		return register(Identifier.of(Musically.ID, id), item);
	}

	public static Item register(Identifier identifier, Item item) {
		return Registry.register(Registries.ITEM, identifier, item);
	}

	public static void initialize() {
		Registry.register(Registries.ITEM_GROUP, ITEM_GROUP_KEY, ITEM_GROUP);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
				.register((itemGroup) -> {
					itemGroup.addAfter(
						Items.PHANTOM_MEMBRANE,

						RECORDING_STYLUS,
						KELP_SHEET,
						MICROPHONE,
						EMPTY_DISC,
						WRITTEN_DISC);
				});

		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_KEY)
				.register((itemGroup) -> {
					itemGroup.addAll(java.util.Arrays.asList(
						RECORDING_STYLUS,
						KELP_SHEET,
						MICROPHONE,
						EMPTY_DISC,
						WRITTEN_DISC).stream().map(Item::getDefaultStack).toList());
				});
	}
}
