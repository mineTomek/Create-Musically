package com.minetomek.musically;

import com.minetomek.musically.blocks.DiscRecorderBlock;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
	public static final Block DISC_RECORDER = register(
			new DiscRecorderBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD)),
			"disc_recorder", true);
	// public static final Block BLOCK_NAME = registerItem(new
	// Block(AbstractBlock.Settings().create()), "block_name", true);

	public static Block register(Block block, String name, boolean shouldRegisterItem) {
		Identifier id = Identifier.of(Musically.ID, name);

		if (shouldRegisterItem) {
			BlockItem blockItem = new BlockItem(block, new Item.Settings());
			Registry.register(Registries.ITEM, id, blockItem);
		}

		return Registry.register(Registries.BLOCK, id, block);
	}

	public static void initialize() {
		ItemGroupEvents.modifyEntriesEvent(ModItems.ITEM_GROUP_KEY)
				.register((itemGroup) -> {
					itemGroup.add(DISC_RECORDER.asItem());
				});
	}
}
