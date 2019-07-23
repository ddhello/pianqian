package com.dislikeyou.pianqian;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;


public class Main extends JavaPlugin implements Listener {
	public static Inventory inv = Bukkit.createInventory(null, 36,"选择您的操作");
	public static Inventory inv1 = Bukkit.createInventory(null,36,"选择您要打开的箱子" );
	public static Inventory inv2 = Bukkit.createInventory(null,36,"选择您要兑换的称号" );
	public static Inventory inv3 = Bukkit.createInventory(null,36,"选择您要出售的碎片" );
	public static ItemStack kaixiang = new ItemStack(Material.CHEST);
	public static ItemMeta kaixiangmeta = kaixiang.getItemMeta();
	public static ItemStack spring = new ItemStack(Material.CHEST);
	public static ItemMeta springmeta = spring.getItemMeta();
	private static Economy economy;
	public static ItemStack summer = new ItemStack(Material.CHEST);
	public static ItemMeta summermeta = summer.getItemMeta();
	public static ItemStack fall = new ItemStack(Material.CHEST);
	public static ItemMeta fallmeta = fall.getItemMeta();
	public static ItemStack winter = new ItemStack(Material.CHEST);
	public static ItemMeta wintermeta = winter.getItemMeta();
	public static ItemStack duihuan = new ItemStack(Material.REDSTONE);
	public static ItemMeta duihuanmeta = duihuan.getItemMeta();
	public static ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
	public static ItemMeta itemmeta = item.getItemMeta();
	public static ItemStack goldingot = new ItemStack(Material.GOLD_INGOT);
	public static ItemMeta goldingotmeta = goldingot.getItemMeta();
	public static ItemStack sale = new ItemStack(Material.IRON_INGOT);
	public static ItemMeta salemeta = sale.getItemMeta();
	  private static boolean supportVault = false;

	  public static boolean setupEconomy()
	  {
	    RegisteredServiceProvider economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
	    if (economyProvider != null) {
	      economy = (Economy)economyProvider.getProvider();
	    }
	    supportVault = economy != null;
	    return supportVault;
	  }

	  public static boolean isSupportVault()
	  {
	    if (economy != null) return true;
	    return setupEconomy();
	  }
	@Override
	public void onEnable() 
	{
		setupEconomy();
		getLogger().info("[骗钱]骗钱插件已开启！");
		getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) 
	{
		e.getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]我们诚挚邀请您试用由橡皮开发的为璀璨繁华服务器所定制的骗钱插件。输入/pianqian即可打开UI!目前夏日炎炎箱子特惠80G!当前处于测试阶段，请勿打开夏日炎炎和冬季攻势，对应称号尚未制作完成！");
	}
	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String str,String[] str1) 
	{
		if(cmd.getName().equalsIgnoreCase("pianqian")) 
		{
			kaixiangmeta.setDisplayName("打开一个称号箱子");
			kaixiang.setItemMeta(kaixiangmeta);
			inv.setItem(1, kaixiang);
			duihuanmeta.setDisplayName("兑换称号");
			duihuan.setItemMeta(duihuanmeta);
			inv.setItem(3, duihuan);
			salemeta.setDisplayName("出售碎片");
			sale.setItemMeta(salemeta);
			inv.setItem(5, sale);
			Bukkit.getPlayer(sender.getName()).openInventory(inv);
			if(str1[1]=="reload") 
			{
				if(sender.isOp()) 
				{
				this.reloadConfig();
				sender.sendMessage("重载完毕");
				}
			}
			return true;
		}
		return false;
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) 
	{
		if(e.getView().getTitle().equals("选择您的操作")) 
		{
			if(e.getSlot() == 1) 
			{
				inv1.clear();
				springmeta.setDisplayName("春暖花开称号箱 50G");
				springmeta.addEnchant(Enchantment.DIG_SPEED, 20, true);
			    spring.setItemMeta(springmeta);
			    inv1.setItem(1, spring);
			    summermeta.setDisplayName("夏日炎炎称号箱80G");
			    summermeta.addEnchant(Enchantment.DIG_SPEED,20,true);
			    summer.setItemMeta(summermeta);
			    inv1.setItem(3, summer);
			    fallmeta.setDisplayName("秋风瑟瑟称号箱100G");
			    fallmeta.addEnchant(Enchantment.DIG_SPEED,20,true);
			    fall.setItemMeta(fallmeta);
			    inv1.setItem(5, fall);
			    wintermeta.setDisplayName("冬季攻势称号箱250G");
			    wintermeta.addEnchant(Enchantment.DIG_SPEED,20,true);
			    winter.setItemMeta(wintermeta);
			    inv1.setItem(7,winter);
				e.getView().getPlayer().openInventory(inv1);
				e.setCancelled(true);
			}
			if(e.getSlot()==3) 
			{
				try 
				{
				List<String> lore = itemmeta.hasLore()?itemmeta.getLore():new ArrayList<String>();
				int count = getConfig().getInt("count");
				getLogger().info(""+count);
				for(int a= 0;a<5;a++) 
				{
					lore.clear();
					itemmeta.setDisplayName(getConfig().getString(a+"b"));
					if(getConfig().getString(a+"t").equals("fish")) 
					{
						lore.add( getConfig().getString(a+"p")+"咸鱼碎片");
						lore.add( getConfig().getString(a+"d"));
						itemmeta.setLore(lore);
						item.setItemMeta(itemmeta);
						inv2.setItem(a, item);
					}
					if(getConfig().getString(a+"t").equals("baka")) 
					{
						lore.clear();
						lore.add( getConfig().getString(a+"p")+"baka!碎片");
						lore.add( getConfig().getString(a+"d"));
						itemmeta.setLore(lore);
						item.setItemMeta(itemmeta);
						inv2.setItem(a, item);
					}
					if(getConfig().getString(a+"t").equals("summer")) 
					{
						lore.clear();
						lore.add( getConfig().getString(a+"p")+"烈焰王者碎片");
						lore.add( getConfig().getString(a+"d"));
						itemmeta.setLore(lore);
						item.setItemMeta(itemmeta);
						inv2.setItem(a, item);
					}
					if(getConfig().getString(a+"t").equals("winter")) 
					{
						lore.clear();
						lore.add(getConfig().getString(a+"p")+"非酋碎片");
						lore.add( getConfig().getString(a+"d"));
						itemmeta.setLore(lore);
						item.setItemMeta(itemmeta);
						inv2.setItem(a, item);
					}
				}
				goldingotmeta.setDisplayName("查询碎片余额");
				goldingotmeta.addEnchant(Enchantment.DIG_SPEED, 20, true);
				goldingot.setItemMeta(goldingotmeta);
				inv2.setItem(34, goldingot);
				e.getView().getPlayer().openInventory(inv2);
				}
				catch(Exception e1)
				{
					getLogger().info("已打开");
				}
			}
			if(e.getSlot() == 5) 
			{
				inv3.clear();
				List<String> lore = itemmeta.hasLore()?itemmeta.getLore():new ArrayList<String>();
				lore.clear();
				lore.add("出售：10G");
				itemmeta.setDisplayName("咸鱼碎片");
				item.setLore(lore);
				item.setItemMeta(itemmeta);
				inv3.setItem(0, item);
				lore.clear();
				lore.add("出售：15G");
				itemmeta.setDisplayName("烈焰王者碎片");
				item.setLore(lore);
				item.setItemMeta(itemmeta);
				inv3.setItem(1, item);
				lore.clear();
				lore.add("出售：20G");
				itemmeta.setDisplayName("baka!碎片");
				item.setLore(lore);
				item.setItemMeta(itemmeta);
				inv3.setItem(2, item);
				lore.clear();
				lore.add("出售：25G");
				itemmeta.setDisplayName("冰霜行者碎片");
				item.setLore(lore);
				item.setItemMeta(itemmeta);
				inv3.setItem(3, item);
				e.getView().getPlayer().openInventory(inv3);
				e.setCancelled(true);
			}
			e.setCancelled(true);
		}
		if(e.getView().getTitle().equals("选择您要出售的碎片")) 
		{
			int slot = e.getSlot();
			int fish = getConfig().getInt("fish_"+e.getView().getPlayer().getName());
			int fire = getConfig().getInt("fire_"+e.getView().getPlayer().getName());
			int baka = getConfig().getInt("baka_"+e.getView().getPlayer().getName());
			int afir = getConfig().getInt("afir_"+e.getView().getPlayer().getName());
			if(slot == 0) 
			{
				if(fish>=1) 
				{
					fish-=1;
					getConfig().set("fish_"+e.getView().getPlayer().getName(), fish);
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]成功卖掉了一个碎片，你还剩"+fish);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+e.getView().getPlayer().getName()+" 10");
				}
			}
			if(slot == 1) 
			{
				if(fire>=1) 
				{
					fire-=1;
					getConfig().set("fire_"+e.getView().getPlayer().getName(), fire);
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]成功卖掉了一个碎片，你还剩"+fire);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+e.getView().getPlayer().getName()+" 15");
				}
			}
			if(slot == 2) 
			{
				if(baka>=1) 
				{
					baka-=1;
					getConfig().set("baka_"+e.getView().getPlayer().getName(), baka);
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]成功卖掉了一个碎片，你还剩"+baka);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+e.getView().getPlayer().getName()+" 20");
				}
			}
			if(slot == 3) 
			{
				if(afir>=1) 
				{
					afir-=1;
					getConfig().set("afir_"+e.getView().getPlayer().getName(), afir);
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]成功卖掉了一个碎片，你还剩"+afir);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+e.getView().getPlayer().getName()+" 25");
				}
			}
			e.setCancelled(true);
		}
		if(e.getView().getTitle().equals("选择您要兑换的称号")) 
		{
			int slot = e.getSlot();
			int fish = getConfig().getInt("fish_"+e.getView().getPlayer().getName());
			int fire = getConfig().getInt("fire_"+e.getView().getPlayer().getName());
			int baka = getConfig().getInt("baka_"+e.getView().getPlayer().getName());
			int afir = getConfig().getInt("afir_"+e.getView().getPlayer().getName());
			if(e.getSlot() == 34) 
			{
				e.getView().getPlayer().sendMessage("您的咸鱼碎片数量是"+fish+"您的烈焰王者碎片数量是"+fire+"您的baka!碎片数量是"+baka+"您的冰霜行者碎片数量是"+afir);
				e.setCancelled(true);
			}
			if(getConfig().getString(slot+"t").equals("fish")) 
			{
				if(fish<getConfig().getInt(slot+"p")) 
				{
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你的碎片不够！");
				}
				else 
				{
					fish-=getConfig().getInt(slot+"p");
					getConfig().set("fish_"+e.getView().getPlayer().getName(), fish);
					this.saveConfig();
					this.reloadConfig();
					String chenghao = getConfig().getString(slot+"r");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" "+chenghao);
					e.getView().getPlayer().sendMessage(ChatColor.GREEN+"[骗钱]"+ChatColor.YELLOW+"您已成功兑换！");
					Bukkit.broadcastMessage(ChatColor.YELLOW+"[骗钱]+恭喜玩家"+e.getView().getPlayer().getName()+"兑换了一个称号！");
				}
			}
			if(getConfig().getString(slot+"t").equals("fire")) 
			{
				if(fire<getConfig().getInt(slot+"p")) 
				{
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你的碎片不够！");
				}
				else 
				{
					fire-=getConfig().getInt(slot+"p");
					getConfig().set("fire_"+e.getView().getPlayer().getName(), fire);
					this.saveConfig();
					this.reloadConfig();
					String chenghao = getConfig().getString(slot+"r");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" "+chenghao);
					e.getView().getPlayer().sendMessage(ChatColor.GREEN+"[骗钱]"+ChatColor.YELLOW+"您已成功兑换！");
					Bukkit.broadcastMessage(ChatColor.YELLOW+"[骗钱]+恭喜玩家"+e.getView().getPlayer().getName()+"兑换了一个称号！");
				}
			}
			if(getConfig().getString(slot+"t").equals("baka")) 
			{
				if(baka<getConfig().getInt(slot+"p")) 
				{
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你的碎片不够！");
				}
				else 
				{
					baka-=getConfig().getInt(slot+"p");
					getConfig().set("fire_"+e.getView().getPlayer().getName(), baka);
					this.saveConfig();
					this.reloadConfig();
					String chenghao = getConfig().getString(slot+"r");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" "+chenghao);
					e.getView().getPlayer().sendMessage(ChatColor.GREEN+"[骗钱]"+ChatColor.YELLOW+"您已成功兑换！");
					Bukkit.broadcastMessage(ChatColor.YELLOW+"[骗钱]+恭喜玩家"+e.getView().getPlayer().getName()+"兑换了一个称号！");
				}
			}
			if(getConfig().getString(slot+"t").equals("afir")) 
			{
				if(afir<getConfig().getInt(slot+"p")) 
				{
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你的碎片不够！");
				}
				else 
				{
					afir-=getConfig().getInt(slot+"p");
					getConfig().set("fire_"+e.getView().getPlayer().getName(), afir);
					this.saveConfig();
					this.reloadConfig();
					String chenghao = getConfig().getString(slot+"r");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" "+chenghao);
					e.getView().getPlayer().sendMessage(ChatColor.GREEN+"[骗钱]"+ChatColor.YELLOW+"您已成功兑换！");
					Bukkit.broadcastMessage(ChatColor.YELLOW+"[骗钱]+恭喜玩家"+e.getView().getPlayer().getName()+"兑换了一个称号！");
				}
			}
			e.setCancelled(true);
		}
		if(e.getView().getTitle().equals("选择您要打开的箱子")) 
		{

			inv1.setItem(1, spring);
			if(e.getSlot() == 1) 
			{
				//春暖花开称号箱
				int one = 0;
				int two = 40;
				int three = 80;
				int four = 98;
				int five = 99;
				int six = 100;
				getLogger().info("1");
				if(minusmoney(Bukkit.getPlayer(e.getView().getPlayer().getName()),50)) 
				{
					int random = (int)(Math.random() * 100);
					getLogger().info("2");
					if(random>= one && random <= two) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'咸鱼称号'碎片x1");
						int fish = getConfig().getInt("fish_"+e.getView().getPlayer().getName());
						if(getConfig().get("fish_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 1;
						getConfig().set("fish_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= two && random <= three) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'咸鱼称号'碎片x2");
						int fish = getConfig().getInt("fish_"+e.getView().getPlayer().getName());
						if(getConfig().get("fish_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 2;
						getConfig().set("fish_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= three && random <= four) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'咸鱼称号'碎片x10");
						int fish = getConfig().getInt("fish_"+e.getView().getPlayer().getName());
						if(getConfig().get("fish_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 10;
						getConfig().set("fish_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= four && random <= five) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'咸鱼称号'");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.fish");
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= five && random <= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'咸鱼称号+咸鱼碎片x5");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.fish");
						int fish = getConfig().getInt("fish_"+e.getView().getPlayer().getName());
						if(getConfig().get("fish_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 5;
						getConfig().set("fish_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'大佬称号+咸鱼碎片x5");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.dalao");
						int fish = getConfig().getInt("fish_"+e.getView().getPlayer().getName());
						if(getConfig().get("fish_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 5;
						getConfig().set("fish_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					e.setCancelled(true);
				}
				else 
				{
					e.setCancelled(true);
				}
			}
			if(e.getSlot() == 3) //夏日炎炎
			{
				int one = 0;
				int two = 40;
				int three = 80;
				int four = 98;
				int five = 99;
				int six = 100;
				getLogger().info("1");
				if(minusmoney(Bukkit.getPlayer(e.getView().getPlayer().getName()),80)) 
				{
					int random = (int)(Math.random() * 100);
					getLogger().info("2");
					if(random>= one && random <= two) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'烈焰王者'碎片x1");
						int fish = getConfig().getInt("fire_"+e.getView().getPlayer().getName());
						if(getConfig().get("fire_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 1;
						getConfig().set("fire_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= two && random <= three) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'烈焰王者'碎片x2");
						int fish = getConfig().getInt("fire_"+e.getView().getPlayer().getName());
						if(getConfig().get("fire_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 2;
						getConfig().set("fire_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= three && random <= four) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'烈焰王者'碎片x10");
						int fish = getConfig().getInt("fire_"+e.getView().getPlayer().getName());
						if(getConfig().get("fire_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 10;
						getConfig().set("fire_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= four && random <= five) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'熟鱼'称号");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.cooked");
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= five && random <= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'熟鱼称号+熟鱼碎片x5");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.cooked");
						int fish = getConfig().getInt("fire_"+e.getView().getPlayer().getName());
						if(getConfig().get("fire_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 5;
						getConfig().set("fire_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'大佬称号+熟鱼碎片x5");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.dalao");
						int fish = getConfig().getInt("fire_"+e.getView().getPlayer().getName());
						if(getConfig().get("fire_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 5;
						getConfig().set("fire_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					e.setCancelled(true);
				}
				else 
				{
					e.setCancelled(true);
				}
				
			}
			if(e.getSlot() == 5) //秋风瑟瑟
			{
				int one = 0;
				int two = 40;
				int three = 80;
				int four = 98;
				int five = 99;
				int six = 100;
				getLogger().info("1");
				if(minusmoney(Bukkit.getPlayer(e.getView().getPlayer().getName()),100)) 
				{
					int random = (int)(Math.random() * 100);
					getLogger().info("2");
					if(random>= one && random <= two) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'baka!'碎片x1");
						int fish = getConfig().getInt("baka_"+e.getView().getPlayer().getName());
						if(getConfig().get("baka_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 1;
						getConfig().set("baka_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= two && random <= three) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'baka!'碎片x2");
						int fish = getConfig().getInt("baka_"+e.getView().getPlayer().getName());
						if(getConfig().get("baka_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 2;
						getConfig().set("baka_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= three && random <= four) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'baka!'碎片x10");
						int fish = getConfig().getInt("baka_"+e.getView().getPlayer().getName());
						if(getConfig().get("baka_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 10;
						getConfig().set("baka_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= four && random <= five) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'baka!称号'");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.baka");
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= five && random <= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'baka!称号+baka!碎片x5");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.baka");
						int fish = getConfig().getInt("baka_"+e.getView().getPlayer().getName());
						if(getConfig().get("baka_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 5;
						getConfig().set("baka_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'大佬称号+baka!碎片x5");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.dalao");
						int fish = getConfig().getInt("baka_"+e.getView().getPlayer().getName());
						if(getConfig().get("baka_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 5;
						getConfig().set("baka_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					e.setCancelled(true);
				}
				else 
				{
					e.setCancelled(true);
				}
			}
			if(e.getSlot() == 7) //冬季攻势
			{
				int one = 0;
				int two = 40;
				int three = 80;
				int four = 98;
				int five = 99;
				int six = 100;
				getLogger().info("1");
				if(minusmoney(Bukkit.getPlayer(e.getView().getPlayer().getName()),250)) 
				{
					int random = (int)(Math.random() * 100);
					getLogger().info("2");
					if(random>= one && random <= two) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'非酋'碎片x1");
						int fish = getConfig().getInt("afir_"+e.getView().getPlayer().getName());
						if(getConfig().get("afir_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 1;
						getConfig().set("afir_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= two && random <= three) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'非酋'碎片x2");
						int fish = getConfig().getInt("afir_"+e.getView().getPlayer().getName());
						if(getConfig().get("afir_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 2;
						getConfig().set("afir_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= three && random <= four) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'非酋'碎片x10");
						int fish = getConfig().getInt("afir_"+e.getView().getPlayer().getName());
						if(getConfig().get("afir_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 10;
						getConfig().set("afir_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= four && random <= five) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'非酋称号'");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.afir");
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= five && random <= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'非酋称号+非酋碎片x5");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.afir");
						int fish = getConfig().getInt("afir_"+e.getView().getPlayer().getName());
						if(getConfig().get("afir_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 5;
						getConfig().set("afir_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[骗钱]你开到了'非酋称号+非酋碎片x5");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.afir");
						int fish = getConfig().getInt("afir_"+e.getView().getPlayer().getName());
						if(getConfig().get("afir_"+e.getView().getPlayer().getName()) == null) 
							fish = 0;
						fish += 5;
						getConfig().set("afir_"+e.getView().getPlayer().getName(), fish);
						this.saveConfig();
						this.reloadConfig();
					}
					e.setCancelled(true);
				}
				else 
				{
					e.setCancelled(true);
				}
			}
		}
		
	}
	public boolean minusmoney(Player player,double amount) 
	{
		getLogger().info("3");
		Player playere = (Player)player;
		double moneyamoutnow = this.economy.getBalance(playere);
		getLogger().info("4");
		if(moneyamoutnow < amount) 
		{
			player.sendMessage(ChatColor.YELLOW+"[骗钱]你的钱不够！");
			return false;
		}
		else 
		{
			this.economy.withdrawPlayer(playere, amount);
			return true;
		}
	}
	
}
