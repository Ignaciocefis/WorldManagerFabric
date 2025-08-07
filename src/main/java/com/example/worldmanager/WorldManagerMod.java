package com.example.worldmanager;

import com.example.worldmanager.commands.WorldCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class WorldManagerMod implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(WorldCommand::register);
    }
}