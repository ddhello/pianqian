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
	public static Inventory inv = Bukkit.createInventory(null, 36,"ѡ�����Ĳ���");
	public static Inventory inv1 = Bukkit.createInventory(null,36,"ѡ����Ҫ�򿪵�����" );
	public static Inventory inv2 = Bukkit.createInventory(null,36,"ѡ����Ҫ�һ��ĳƺ�" );
	public static Inventory inv3 = Bukkit.createInventory(null,36,"ѡ����Ҫ���۵���Ƭ" );
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
		getLogger().info("[ƭǮ]ƭǮ����ѿ�����");
		getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) 
	{
		e.getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]���ǳ�ֿ��������������Ƥ������Ϊ�買��������������Ƶ�ƭǮ���������/pianqian���ɴ�UI!Ŀǰ�������������ػ�80G!��ǰ���ڲ��Խ׶Σ�������������׺Ͷ������ƣ���Ӧ�ƺ���δ������ɣ�");
	}
	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String str,String[] str1) 
	{
		if(cmd.getName().equalsIgnoreCase("pianqian")) 
		{
			kaixiangmeta.setDisplayName("��һ���ƺ�����");
			kaixiang.setItemMeta(kaixiangmeta);
			inv.setItem(1, kaixiang);
			duihuanmeta.setDisplayName("�һ��ƺ�");
			duihuan.setItemMeta(duihuanmeta);
			inv.setItem(3, duihuan);
			salemeta.setDisplayName("������Ƭ");
			sale.setItemMeta(salemeta);
			inv.setItem(5, sale);
			Bukkit.getPlayer(sender.getName()).openInventory(inv);
			if(str1[1]=="reload") 
			{
				if(sender.isOp()) 
				{
				this.reloadConfig();
				sender.sendMessage("�������");
				}
			}
			return true;
		}
		return false;
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) 
	{
		if(e.getView().getTitle().equals("ѡ�����Ĳ���")) 
		{
			if(e.getSlot() == 1) 
			{
				inv1.clear();
				springmeta.setDisplayName("��ů�����ƺ��� 50G");
				springmeta.addEnchant(Enchantment.DIG_SPEED, 20, true);
			    spring.setItemMeta(springmeta);
			    inv1.setItem(1, spring);
			    summermeta.setDisplayName("�������׳ƺ���80G");
			    summermeta.addEnchant(Enchantment.DIG_SPEED,20,true);
			    summer.setItemMeta(summermeta);
			    inv1.setItem(3, summer);
			    fallmeta.setDisplayName("���ɪɪ�ƺ���100G");
			    fallmeta.addEnchant(Enchantment.DIG_SPEED,20,true);
			    fall.setItemMeta(fallmeta);
			    inv1.setItem(5, fall);
			    wintermeta.setDisplayName("�������Ƴƺ���250G");
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
						lore.add( getConfig().getString(a+"p")+"������Ƭ");
						lore.add( getConfig().getString(a+"d"));
						itemmeta.setLore(lore);
						item.setItemMeta(itemmeta);
						inv2.setItem(a, item);
					}
					if(getConfig().getString(a+"t").equals("baka")) 
					{
						lore.clear();
						lore.add( getConfig().getString(a+"p")+"baka!��Ƭ");
						lore.add( getConfig().getString(a+"d"));
						itemmeta.setLore(lore);
						item.setItemMeta(itemmeta);
						inv2.setItem(a, item);
					}
					if(getConfig().getString(a+"t").equals("summer")) 
					{
						lore.clear();
						lore.add( getConfig().getString(a+"p")+"����������Ƭ");
						lore.add( getConfig().getString(a+"d"));
						itemmeta.setLore(lore);
						item.setItemMeta(itemmeta);
						inv2.setItem(a, item);
					}
					if(getConfig().getString(a+"t").equals("winter")) 
					{
						lore.clear();
						lore.add(getConfig().getString(a+"p")+"������Ƭ");
						lore.add( getConfig().getString(a+"d"));
						itemmeta.setLore(lore);
						item.setItemMeta(itemmeta);
						inv2.setItem(a, item);
					}
				}
				goldingotmeta.setDisplayName("��ѯ��Ƭ���");
				goldingotmeta.addEnchant(Enchantment.DIG_SPEED, 20, true);
				goldingot.setItemMeta(goldingotmeta);
				inv2.setItem(34, goldingot);
				e.getView().getPlayer().openInventory(inv2);
				}
				catch(Exception e1)
				{
					getLogger().info("�Ѵ�");
				}
			}
			if(e.getSlot() == 5) 
			{
				inv3.clear();
				List<String> lore = itemmeta.hasLore()?itemmeta.getLore():new ArrayList<String>();
				lore.clear();
				lore.add("���ۣ�10G");
				itemmeta.setDisplayName("������Ƭ");
				item.setLore(lore);
				item.setItemMeta(itemmeta);
				inv3.setItem(0, item);
				lore.clear();
				lore.add("���ۣ�15G");
				itemmeta.setDisplayName("����������Ƭ");
				item.setLore(lore);
				item.setItemMeta(itemmeta);
				inv3.setItem(1, item);
				lore.clear();
				lore.add("���ۣ�20G");
				itemmeta.setDisplayName("baka!��Ƭ");
				item.setLore(lore);
				item.setItemMeta(itemmeta);
				inv3.setItem(2, item);
				lore.clear();
				lore.add("���ۣ�25G");
				itemmeta.setDisplayName("��˪������Ƭ");
				item.setLore(lore);
				item.setItemMeta(itemmeta);
				inv3.setItem(3, item);
				e.getView().getPlayer().openInventory(inv3);
				e.setCancelled(true);
			}
			e.setCancelled(true);
		}
		if(e.getView().getTitle().equals("ѡ����Ҫ���۵���Ƭ")) 
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
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�ɹ�������һ����Ƭ���㻹ʣ"+fish);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+e.getView().getPlayer().getName()+" 10");
				}
			}
			if(slot == 1) 
			{
				if(fire>=1) 
				{
					fire-=1;
					getConfig().set("fire_"+e.getView().getPlayer().getName(), fire);
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�ɹ�������һ����Ƭ���㻹ʣ"+fire);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+e.getView().getPlayer().getName()+" 15");
				}
			}
			if(slot == 2) 
			{
				if(baka>=1) 
				{
					baka-=1;
					getConfig().set("baka_"+e.getView().getPlayer().getName(), baka);
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�ɹ�������һ����Ƭ���㻹ʣ"+baka);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+e.getView().getPlayer().getName()+" 20");
				}
			}
			if(slot == 3) 
			{
				if(afir>=1) 
				{
					afir-=1;
					getConfig().set("afir_"+e.getView().getPlayer().getName(), afir);
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�ɹ�������һ����Ƭ���㻹ʣ"+afir);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+e.getView().getPlayer().getName()+" 25");
				}
			}
			e.setCancelled(true);
		}
		if(e.getView().getTitle().equals("ѡ����Ҫ�һ��ĳƺ�")) 
		{
			int slot = e.getSlot();
			int fish = getConfig().getInt("fish_"+e.getView().getPlayer().getName());
			int fire = getConfig().getInt("fire_"+e.getView().getPlayer().getName());
			int baka = getConfig().getInt("baka_"+e.getView().getPlayer().getName());
			int afir = getConfig().getInt("afir_"+e.getView().getPlayer().getName());
			if(e.getSlot() == 34) 
			{
				e.getView().getPlayer().sendMessage("����������Ƭ������"+fish+"��������������Ƭ������"+fire+"����baka!��Ƭ������"+baka+"���ı�˪������Ƭ������"+afir);
				e.setCancelled(true);
			}
			if(getConfig().getString(slot+"t").equals("fish")) 
			{
				if(fish<getConfig().getInt(slot+"p")) 
				{
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�����Ƭ������");
				}
				else 
				{
					fish-=getConfig().getInt(slot+"p");
					getConfig().set("fish_"+e.getView().getPlayer().getName(), fish);
					this.saveConfig();
					this.reloadConfig();
					String chenghao = getConfig().getString(slot+"r");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" "+chenghao);
					e.getView().getPlayer().sendMessage(ChatColor.GREEN+"[ƭǮ]"+ChatColor.YELLOW+"���ѳɹ��һ���");
					Bukkit.broadcastMessage(ChatColor.YELLOW+"[ƭǮ]+��ϲ���"+e.getView().getPlayer().getName()+"�һ���һ���ƺţ�");
				}
			}
			if(getConfig().getString(slot+"t").equals("fire")) 
			{
				if(fire<getConfig().getInt(slot+"p")) 
				{
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�����Ƭ������");
				}
				else 
				{
					fire-=getConfig().getInt(slot+"p");
					getConfig().set("fire_"+e.getView().getPlayer().getName(), fire);
					this.saveConfig();
					this.reloadConfig();
					String chenghao = getConfig().getString(slot+"r");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" "+chenghao);
					e.getView().getPlayer().sendMessage(ChatColor.GREEN+"[ƭǮ]"+ChatColor.YELLOW+"���ѳɹ��һ���");
					Bukkit.broadcastMessage(ChatColor.YELLOW+"[ƭǮ]+��ϲ���"+e.getView().getPlayer().getName()+"�һ���һ���ƺţ�");
				}
			}
			if(getConfig().getString(slot+"t").equals("baka")) 
			{
				if(baka<getConfig().getInt(slot+"p")) 
				{
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�����Ƭ������");
				}
				else 
				{
					baka-=getConfig().getInt(slot+"p");
					getConfig().set("fire_"+e.getView().getPlayer().getName(), baka);
					this.saveConfig();
					this.reloadConfig();
					String chenghao = getConfig().getString(slot+"r");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" "+chenghao);
					e.getView().getPlayer().sendMessage(ChatColor.GREEN+"[ƭǮ]"+ChatColor.YELLOW+"���ѳɹ��һ���");
					Bukkit.broadcastMessage(ChatColor.YELLOW+"[ƭǮ]+��ϲ���"+e.getView().getPlayer().getName()+"�һ���һ���ƺţ�");
				}
			}
			if(getConfig().getString(slot+"t").equals("afir")) 
			{
				if(afir<getConfig().getInt(slot+"p")) 
				{
					e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�����Ƭ������");
				}
				else 
				{
					afir-=getConfig().getInt(slot+"p");
					getConfig().set("fire_"+e.getView().getPlayer().getName(), afir);
					this.saveConfig();
					this.reloadConfig();
					String chenghao = getConfig().getString(slot+"r");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" "+chenghao);
					e.getView().getPlayer().sendMessage(ChatColor.GREEN+"[ƭǮ]"+ChatColor.YELLOW+"���ѳɹ��һ���");
					Bukkit.broadcastMessage(ChatColor.YELLOW+"[ƭǮ]+��ϲ���"+e.getView().getPlayer().getName()+"�һ���һ���ƺţ�");
				}
			}
			e.setCancelled(true);
		}
		if(e.getView().getTitle().equals("ѡ����Ҫ�򿪵�����")) 
		{

			inv1.setItem(1, spring);
			if(e.getSlot() == 1) 
			{
				//��ů�����ƺ���
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����ƺ�'��Ƭx1");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����ƺ�'��Ƭx2");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����ƺ�'��Ƭx10");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����ƺ�'");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.fish");
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= five && random <= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����ƺ�+������Ƭx5");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'���гƺ�+������Ƭx5");
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
			if(e.getSlot() == 3) //��������
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'��������'��Ƭx1");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'��������'��Ƭx2");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'��������'��Ƭx10");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����'�ƺ�");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.cooked");
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= five && random <= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����ƺ�+������Ƭx5");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'���гƺ�+������Ƭx5");
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
			if(e.getSlot() == 5) //���ɪɪ
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'baka!'��Ƭx1");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'baka!'��Ƭx2");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'baka!'��Ƭx10");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'baka!�ƺ�'");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.baka");
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= five && random <= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'baka!�ƺ�+baka!��Ƭx5");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'���гƺ�+baka!��Ƭx5");
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
			if(e.getSlot() == 7) //��������
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����'��Ƭx1");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����'��Ƭx2");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'����'��Ƭx10");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'�����ƺ�'");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+e.getView().getPlayer().getName()+" tpre.afir");
						this.saveConfig();
						this.reloadConfig();
					}
					if(random>= five && random <= six) 
					{
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'�����ƺ�+������Ƭx5");
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
						e.getView().getPlayer().sendMessage(ChatColor.YELLOW+"[ƭǮ]�㿪����'�����ƺ�+������Ƭx5");
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
			player.sendMessage(ChatColor.YELLOW+"[ƭǮ]���Ǯ������");
			return false;
		}
		else 
		{
			this.economy.withdrawPlayer(playere, amount);
			return true;
		}
	}
	
}
