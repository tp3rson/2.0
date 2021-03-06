package powercraft.machines;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import powercraft.api.annotation.PC_FieldObject;
import powercraft.api.block.PC_Block;
import powercraft.api.gres.PC_GresBaseWithInventory;
import powercraft.api.recipes.PC_3DRecipe;
import powercraft.api.recipes.PC_I3DRecipeHandler;
import powercraft.api.recipes.PC_IRecipe;
import powercraft.api.registry.PC_ItemRegistry;
import powercraft.api.utils.PC_Struct2;
import powercraft.api.utils.PC_Utils;
import powercraft.launcher.PC_Property;
import powercraft.launcher.loader.PC_Module;
import powercraft.launcher.loader.PC_Module.PC_InitProperties;
import powercraft.launcher.loader.PC_Module.PC_InitRecipes;
import powercraft.launcher.loader.PC_Module.PC_Instance;
import powercraft.launcher.loader.PC_Module.PC_RegisterContainers;
import powercraft.launcher.loader.PC_ModuleObject;
import powercraft.machines.block.PCma_BlockAutomaticWorkbench;
import powercraft.machines.block.PCma_BlockBlockBuilder;
import powercraft.machines.block.PCma_BlockChunkLoader;
import powercraft.machines.block.PCma_BlockFishingMachine;
import powercraft.machines.block.PCma_BlockHarvester;
import powercraft.machines.block.PCma_BlockReplacer;
import powercraft.machines.block.PCma_BlockRoaster;
import powercraft.machines.block.PCma_BlockTransmutabox;
import powercraft.machines.block.PCma_BlockXPBank;
import powercraft.machines.container.PCma_ContainerAutomaticWorkbench;
import powercraft.machines.container.PCma_ContainerBlockBuilder;
import powercraft.machines.container.PCma_ContainerReplacer;
import powercraft.machines.container.PCma_ContainerRoaster;
import powercraft.machines.container.PCma_ContainerTransmutabox;

@PC_Module(name = "Machines", version = "@Version@")
public class PCma_App {

	@PC_FieldObject(clazz = PCma_BlockAutomaticWorkbench.class)
	public static PC_Block automaticWorkbench;
	@PC_FieldObject(clazz = PCma_BlockRoaster.class)
	public static PC_Block roaster;
	@PC_FieldObject(clazz = PCma_BlockReplacer.class)
	public static PC_Block replacer;
	@PC_FieldObject(clazz = PCma_BlockTransmutabox.class)
	public static PC_Block transmutabox;
	@PC_FieldObject(clazz = PCma_BlockXPBank.class)
	public static PC_Block xpBank;
	@PC_FieldObject(clazz = PCma_BlockBlockBuilder.class)
	public static PC_Block blockBuilder;
	@PC_FieldObject(clazz = PCma_BlockHarvester.class)
	public static PC_Block harvester;
	@PC_FieldObject(clazz = PCma_BlockFishingMachine.class)
	public static PC_Block fishingMachine;
	@PC_FieldObject(clazz = PCma_BlockChunkLoader.class)
	public static PC_Block chunkLoader;

	public static List<Integer> roasterIgnoreBlockIDs;
	@PC_Instance
	public static PC_ModuleObject instance;

	// @PC_PostInit
	// public void postInit() {
	// PCma_ItemRanking.init();
	// }

	@PC_InitProperties
	public void initProperties(PC_Property config) {
		roasterIgnoreBlockIDs = PC_Utils.parseIntList(config.getString("PCma_BlockRoaster.roasterIgnoreBlockIDs", "1"));
	}

	@PC_InitRecipes
	public List<PC_IRecipe> initRecipes(List<PC_IRecipe> recipes) {
		if (PC_ItemRegistry.getPCItemByName("PCco_ItemCraftingCore") != null) {
			GameRegistry.addRecipe(new ItemStack(automaticWorkbench),
					new Object[] { "IXI", "IYI", "IZI", 'I', Items.iron_ingot, 'X', Items.diamond, 'Y',
							PC_ItemRegistry.getPCItemByName("PCco_ItemCraftingCore"), 'Z', Items.redstone });
		} else {
			GameRegistry.addRecipe(new ItemStack(automaticWorkbench), new Object[] { "IXI", "IYI", "IZI", 'I',
					Items.iron_ingot, 'X', Items.diamond, 'Y', Blocks.crafting_table, 'Z', Items.redstone });
		}
		GameRegistry.addRecipe(new ItemStack(roaster), new Object[] { "BBB", "OFO", "OOO", 'B', Blocks.iron_bars, 'O',
				Blocks.obsidian, 'F', Items.blaze_rod });
		GameRegistry.addRecipe(new ItemStack(xpBank), new Object[] { "ODO", "OGO", "O O", 'O', Blocks.obsidian, 'D',
				Blocks.diamond_block, 'G', Items.ghast_tear });
		GameRegistry.addRecipe(new ItemStack(transmutabox), new Object[] { "SOS", "OPO", "SOS", 'S', Blocks.iron_block,
				'O', Blocks.obsidian, 'Z', Blocks.furnace });
		if (PC_ItemRegistry.getPCItemByName("PCco_ItemFormationCore") != null) {
			GameRegistry.addRecipe(new ItemStack(blockBuilder),
					new Object[] { "F", "D", "R", 'F', PC_ItemRegistry.getPCItemByName("PCco_ItemFormationCore"), 'R',
							Items.redstone, 'D', Blocks.dispenser });
		} else {
			GameRegistry.addRecipe(new ItemStack(blockBuilder),
					new Object[] { "F", "D", "R", 'F', Items.ghast_tear, 'R', Items.redstone, 'D', Blocks.dispenser });
		}
		if (PC_ItemRegistry.getPCItemByName("PCco_ItemAnnihilationCore") != null) {
			GameRegistry.addRecipe(new ItemStack(harvester),
					new Object[] { "A", "D", "R", 'A', PC_ItemRegistry.getPCItemByName("PCco_ItemAnnihilationCore"),
							'R', Items.redstone, 'D', Blocks.dispenser });
		} else {
			GameRegistry.addRecipe(new ItemStack(harvester), new Object[] { "A", "D", "R", 'A', Items.blaze_powder, 'R',
					Items.redstone, 'D', Blocks.dispenser });

		}
		GameRegistry.addRecipe(new ItemStack(replacer),
				new Object[] { "B", "R", "H", 'B', blockBuilder, 'R', Items.redstone, 'H', harvester });

		recipes.add(new PC_3DRecipe((PC_I3DRecipeHandler) fishingMachine, new String[] { "www", "www", "www" },
				new String[] { "www", "www", "www" }, new String[] { "www", "www", "www" },
				new String[] { "www", "www", "www" }, new String[] { "www", "www", "www" },
				new String[] { "fpf", "pip", "fpf" }, new String[] { " !c ", "!cc!c", " !c " }, 'w',
				Blocks.flowing_water, Blocks.water, 'f', Blocks.fence, 'p', Blocks.planks, 'c', Blocks.chest, 'i',
				Blocks.iron_block));

		recipes.add(new PC_3DRecipe((PC_I3DRecipeHandler) chunkLoader, new String[] { "gog", "ogo", "gog" },
				new String[] { " f ", "f f", " f " }, 'g', Blocks.glass, 'o', Blocks.obsidian, 'f', Blocks.fire));

		return recipes;
	}

	@PC_RegisterContainers
	public List<PC_Struct2<String, Class<? extends PC_GresBaseWithInventory>>> registerContainers(
			List<PC_Struct2<String, Class<? extends PC_GresBaseWithInventory>>> guis) {
		guis.add(new PC_Struct2<String, Class<? extends PC_GresBaseWithInventory>>("AutomaticWorkbench",
				PCma_ContainerAutomaticWorkbench.class));
		guis.add(new PC_Struct2<String, Class<? extends PC_GresBaseWithInventory>>("BlockBuilder",
				PCma_ContainerBlockBuilder.class));
		guis.add(new PC_Struct2<String, Class<? extends PC_GresBaseWithInventory>>("Replacer",
				PCma_ContainerReplacer.class));
		guis.add(new PC_Struct2<String, Class<? extends PC_GresBaseWithInventory>>("Roaster",
				PCma_ContainerRoaster.class));
		guis.add(new PC_Struct2<String, Class<? extends PC_GresBaseWithInventory>>("Transmutabox",
				PCma_ContainerTransmutabox.class));
		return guis;
	}
}
