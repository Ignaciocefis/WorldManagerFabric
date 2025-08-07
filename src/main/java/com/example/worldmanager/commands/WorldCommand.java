package com.example.worldmanager.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.level.storage.LevelStorage;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import com.mojang.brigadier.arguments.StringArgumentType;

public class WorldCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, net.minecraft.server.command.CommandRegistryAccess access, net.minecraft.command.CommandManager.RegistrationEnvironment env) {
        dispatcher.register(literal("world")
            .then(literal("list").executes(ctx -> {
                File worldsFolder = new File("."); // List folders
                File[] worlds = worldsFolder.listFiles(File::isDirectory);
                if (worlds != null) {
                    for (File world : worlds) {
                        ctx.getSource().sendFeedback(() -> Text.literal("World: " + world.getName()), false);
                    }
                }
                return 1;
            }))
            .then(literal("create")
                .then(argument("name", StringArgumentType.string())
                    .executes(ctx -> {
                        String name = StringArgumentType.getString(ctx, "name");
                        MinecraftServer server = ctx.getSource().getServer();
                        LevelStorage.Session session = server.getSession();
                        Path worldDir = session.getDirectory(WorldSavePath.ROOT).resolveSibling(name);
                        if (!Files.exists(worldDir)) {
                            try {
                                Files.createDirectories(worldDir);
                                ctx.getSource().sendFeedback(() -> Text.literal("Created world: " + name), false);
                            } catch (IOException e) {
                                ctx.getSource().sendError(Text.literal("Failed to create world"));
                            }
                        } else {
                            ctx.getSource().sendError(Text.literal("World already exists"));
                        }
                        return 1;
                    })))
            .then(literal("backup")
                .then(argument("name", StringArgumentType.string())
                    .executes(ctx -> {
                        String name = StringArgumentType.getString(ctx, "name");
                        Path sourceDir = Paths.get(name);
                        Path zipPath = Paths.get(name + "_backup.zip");

                        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(zipPath))) {
                            Files.walk(sourceDir)
                                .filter(path -> !Files.isDirectory(path))
                                .forEach(path -> {
                                    ZipEntry zipEntry = new ZipEntry(sourceDir.relativize(path).toString());
                                    try {
                                        zs.putNextEntry(zipEntry);
                                        Files.copy(path, zs);
                                        zs.closeEntry();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            ctx.getSource().sendFeedback(() -> Text.literal("Backup created: " + zipPath.getFileName()), false);
                        } catch (IOException e) {
                            ctx.getSource().sendError(Text.literal("Failed to create backup"));
                        }

                        return 1;
                    })))
        );
    }
}