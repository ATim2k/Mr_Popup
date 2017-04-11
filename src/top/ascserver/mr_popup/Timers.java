package top.ascserver.mr_popup;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.List;

/**
 * Created by ZERO on 2016/12/11.
 */
public class Timers extends PluginBase implements Listener {
    private static Timers plugin;
    public static Timers getPlugin() {
        return plugin;
    }
    public void onEnable(){
        plugin = this;
        new update().updateCheck();
                this.getServer().getPluginManager().registerEvents(this , this);
                saveResource("config.yml",false);
                saveResource("nopopupworld.yml",false);
                this.getServer().getScheduler().scheduleRepeatingTask(new Mr_Popup(this), 20);
                this.getLogger().info("§e插件加载成功,本插件作者Mr_sky,zxda-uid:9886(贱哥啊哈哈)");
    }
    public boolean onCommand(CommandSender sender, Command command, String zhiling, String[] liebiao) {
        if(!sender.isOp()) {
            sender.sendMessage("你又不是OP，一边凉快去!");
            return false;
        } else {
            if(zhiling.equals("popup")) {
                if (liebiao.length < 1){
                    sender.sendMessage("[Mr_Popup]请使用/popup help 以查看帮助");
                    return false;
                }
                if(liebiao[0].equals("help")) {
                    sender.sendMessage("[Mr_Popup]请使用/popup reload 以重载插件!");
                    sender.sendMessage("[Mr_Popup]请使用/popup world <add/del> <世界名> 以添加不显示底部的世界!");
                    return true;
                }
                if(liebiao[0].equals("reload")) {
                    sender.sendMessage("[Mr_Popup]插件正在重载config.yml");
                    this.getConfig().reload();
                    sender.sendMessage("[Mr_Popup]插件重载完成!");
                    return true;
                }
                if (liebiao[0].equals("world")){
                    if (liebiao.length < 3){
                        sender.sendMessage("请输入/popup world <add/del> <世界名>");
                        return false;
                    }
                    else if (liebiao[1].equals("add")){
                        if (liebiao[2].isEmpty()){
                            sender.sendMessage("请输入/popup world add <世界名>");
                            return false;
                        }else{
                            Config noseepopup = new Config(this.getDataFolder()+"\\nopopupworld.yml",Config.YAML);
                            List noseepopuplist= noseepopup.getStringList("No-see-popup-world");
                            noseepopuplist.add(liebiao[2]);
                            noseepopup.set("No-see-popup-world",noseepopuplist);
                            noseepopup.save();
                            sender.sendMessage("成功添加世界["+liebiao[2]+"]到不显示底部列表");
                            return true;
                        }
                    }else if (liebiao[1].equals("del")){
                        if (liebiao.length < 3){
                            sender.sendMessage("请输入/popup world del <世界名>");
                            return false;
                        }else{
                            Config noseepopup = new Config(this.getDataFolder()+"\\nopopupworld.yml",Config.YAML);
                            List noseepopuplist= noseepopup.getStringList("No-see-popup-world");
                            noseepopuplist.remove(liebiao[2]);
                            noseepopup.set("No-see-popup-world",noseepopuplist);
                            noseepopup.save();
                            sender.sendMessage("成功移除世界["+liebiao[2]+"]出不显示底部列表");
                            return true;
                        }
                    }else{
                        sender.sendMessage("请输入/popup world <add/del> <世界名>");
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
