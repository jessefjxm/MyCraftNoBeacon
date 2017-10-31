package com.mycraft.MyCraftNoBeacon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.PistonExtensionMaterial;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class MyCraftNoBeacon extends JavaPlugin implements Listener {
	// Bukkit vars.
	private ConsoleCommandSender consoleSender = Bukkit.getServer().getConsoleSender();
	private List<Integer> metals = Arrays.asList(41, 42, 57, 133);

	// -------- Bukkit related
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		super.onEnable();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	// public method
	@EventHandler
	public void myBlockPlaceEvent(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (player.isOp())
			return;
		Block b = e.getBlock();
		Location locup = b.getLocation().clone().add(0, 1, 0);
		Location locdown = b.getLocation().clone().add(0, -1, 0);
		if (metals.contains(b.getTypeId())
				&& b.getWorld().getBlockAt(locup).getType().equals(Material.BEACON)) {
			e.setCancelled(true);
			player.sendMessage("§c§l[!] §7不允许激活信标.");
		} else if (b.getType().equals(Material.BEACON)
				&& metals.contains(b.getWorld().getBlockAt(locdown).getTypeId())) {
			e.setCancelled(true);
			player.sendMessage("§c§l[!] §7不允许激活信标.");
		}
	}

	@EventHandler
	public void myPistonExtensionMaterial(BlockPistonExtendEvent e) {
		List<Block> bs = e.getBlocks();
		if (bs.size() == 0)
			return;
		Vector diff = bs.get(0).getLocation().clone().subtract(e.getBlock().getLocation()).toVector().normalize();
		for (Block b : bs) {
			Location loc = b.getLocation().clone().add(diff);
			Location locup = loc.clone().add(0, 1, 0);
			Location locdown = loc.clone().add(0, -1, 0);
			if (metals.contains(b.getWorld().getBlockAt(b.getLocation()).getTypeId())
					&& b.getWorld().getBlockAt(locup).getType().equals(Material.BEACON)) {
				e.setCancelled(true);
				for (Player p : b.getWorld().getPlayers()) {
					if (p.getLocation().distance(b.getLocation()) <= 10)
						p.sendMessage("§c§l[!] §7不允许激活信标.");
				}
				return;
			} else if (b.getWorld().getBlockAt(b.getLocation()).getType()
					.equals(Material.BEACON)
					&& metals.contains(b.getWorld().getBlockAt(locdown).getTypeId())) {
				e.setCancelled(true);
				for (Player p : b.getWorld().getPlayers()) {
					if (p.getLocation().distance(b.getLocation()) <= 10)
						p.sendMessage("§c§l[!] §7不允许激活信标.");
				}
				return;
			}
		}
	}

	@EventHandler
	public void myBlockPistonRetractEvent(BlockPistonRetractEvent e) {
		List<Block> bs = e.getBlocks();
		if (bs.size() == 0)
			return;
		Vector diff = bs.get(0).getLocation().clone().subtract(e.getBlock().getLocation()).toVector().normalize();
		for (Block b : bs) {
			Location loc = b.getLocation().clone().subtract(diff);
			Location locup = loc.clone().add(0, 1, 0);
			Location locdown = loc.clone().add(0, -1, 0);
			if (metals.contains(b.getWorld().getBlockAt(b.getLocation()).getTypeId())
					&& b.getWorld().getBlockAt(locup).getType().equals(Material.BEACON)) {
				e.setCancelled(true);
				for (Player p : b.getWorld().getPlayers()) {
					if (p.getLocation().distance(b.getLocation()) <= 10)
						p.sendMessage("§c§l[!] §7不允许激活信标.");
				}
				return;
			} else if (b.getWorld().getBlockAt(b.getLocation()).getType()
					.equals(Material.BEACON)
					&& metals.contains(b.getWorld().getBlockAt(locdown).getTypeId())) {
				e.setCancelled(true);
				for (Player p : b.getWorld().getPlayers()) {
					if (p.getLocation().distance(b.getLocation()) <= 10)
						p.sendMessage("§c§l[!] §7不允许激活信标.");
				}
				return;
			}
		}
	}
	// -------- private methods | initialization

	// -------- private detail methods | response to commands
}