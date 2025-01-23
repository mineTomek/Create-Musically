package com.minetomek.musically;

import com.minetomek.musically.blocks.DiscRecorderBlock;
import com.minetomek.musically.blocks.DiscRecorderBlockEntity;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModBlocks {
	public static final Block DISC_RECORDER = register(
			new DiscRecorderBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD)),
			"disc_recorder", true);

	public static final BlockEntityType<DiscRecorderBlockEntity> DISC_RECORDER_BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new Identifier(Musically.ID, "disc_recorder_block_entity"),
			BlockEntityType.Builder.create(DiscRecorderBlockEntity::new, DISC_RECORDER)
					.build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY,
							"disc_recorder_block_entity")));

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
