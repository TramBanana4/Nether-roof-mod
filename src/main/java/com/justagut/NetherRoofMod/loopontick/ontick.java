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


    }

    @SubscribeEvent
    public static void serverTick(ServerTickEvent.Pre event){

        }




    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {

    }
}
