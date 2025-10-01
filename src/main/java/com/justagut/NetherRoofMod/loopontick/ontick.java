package com.justagut.NetherRoofMod.loopontick;

import com.justagut.NetherRoofMod.block.ModBlocks;
import com.justagut.NetherRoofMod.netherroofmod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = netherroofmod.MODID)
public class ontick {
    public static List<String> alrdonechunks = new ArrayList<>(List.of(""));

    @SubscribeEvent
    public static void onServerStarted(ServerStartingEvent event){
        System.out.println("started");
        System.out.println("started");
        System.out.println("started");


    }

    @SubscribeEvent
    public static void serverTick(ServerTickEvent.Pre event){
        MinecraftServer server = event.getServer();
        ServerLevel overworld = server.getLevel(Level.OVERWORLD);




        int count;
        for (count = 0;count < overworld.players().size();count++){

            int playerx = overworld.players().get(count).chunkPosition().x * 16;
            int playerz = overworld.players().get(count).chunkPosition().z * 16;
            if (!alrdonechunks.contains(playerx + "," + playerz)) {
                alrdonechunks.add(playerx + "," + playerz);
                for (int i = (int)(Math.random() * 6);i < 5;i++){
                BlockPos oregenpos = new BlockPos(playerx + (int)(Math.random() * 17) - 8,(int)(Math.random() * 8) - 63,playerz + (int)(Math.random() * 17));
                if (overworld.getBlockState(oregenpos).getBlock() == Blocks.DEEPSLATE)
                {
                    overworld.setBlockAndUpdate(oregenpos,ModBlocks.BISMUTH_DEEPSLATE_ORE.get().defaultBlockState());
                    System.out.println(oregenpos);
                }
                }
                }
            }
        }




    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            player.sendSystemMessage(Component.literal("hoi"));
        }
    }
}
