package top.ascserver.mr_popup;
import cn.nukkit.Player;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.utils.Config;
import money.MoneyAPI;
import xyz.mrsky.mr_love.Mr_Love;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ZERO on 2016/12/7.
 */
public class Mr_Popup extends PluginTask<Timers> {
    private String QX = "管理员";
    private String GM = "生存模式";
    public Mr_Popup(Timers owner) {
        super(owner);
    }

    public void onRun(int time) {
        Map<UUID,Player>online;
        online = getOwner().getServer().getOnlinePlayers();
        for(Map.Entry player : online.entrySet()){
        Player player1  = ((Timers)this.getOwner()).getServer().getPlayer(((Player)player.getValue()).getName());
        if (player1.getGamemode() == 0)
            this.GM = String.valueOf(getOwner().getConfig().get("生存模式"));
        if (player1.getGamemode() == 1)
            this.GM = String.valueOf(getOwner().getConfig().get("创造模式"));
        if (player1.getGamemode() == 2)
            this.GM = String.valueOf(getOwner().getConfig().get("冒险模式"));
        if (player1.getGamemode() == 3)
            this.GM = String.valueOf(getOwner().getConfig().get("旁观模式"));
        if (player1.isOp())
            this.QX = String.valueOf(getOwner().getConfig().get("OP"));
        if (!player1.isOp())
            this.QX = String.valueOf(getOwner().getConfig().get("玩家"));
            int x = (int) player1.getX();
            int y = (int) player1.getY();
            int z = (int) player1.getZ();
            String setting = String.valueOf(getOwner().getConfig().get("底部显示"));
            String gps = " X：" + x  + " Y："  + y  + " Z："  + z;
            String playernamereplace = setting.replace("{player}",player1.getName());
            String worldnamereplace = playernamereplace.replace("{level}",player1.getLevel().getName());
            String gpsreplace = worldnamereplace.replace("{gps}",gps);
            String iteminhandreplace = gpsreplace.replace("{id}",String.valueOf(player1.getInventory().getItemInHand().getId()));
            String nreplace = iteminhandreplace.replace("{n}","\n");
            String gamemodereplace = nreplace.replace("{GM}",GM);
            String adminreplace = gamemodereplace.replace("{QX}",QX);
            String timereplace = adminreplace.replace("{time}",onCurrentTime());
            String money = String.valueOf(MoneyAPI.getInstance().getMoney(player1,false));
            String moneyreplace = timereplace.replace("{money}",money);
            String xreplace = moneyreplace.replace("{X}",String.valueOf(x));
            String yreplace = xreplace.replace("{Y}",String.valueOf(y));
            String zreplace = yreplace.replace("{Z}",String.valueOf(z));
            String point = String.valueOf(MoneyAPI.getInstance().getMoney(player1,true));
            String pointreplace = zreplace.replace("{point}", point);
            String onlineplayerreplace = String.valueOf(((Timers)this.getOwner()).getServer().getOnlinePlayers().size());
            String onlinereplace = pointreplace.replace("{online}", onlineplayerreplace);
            String maxplayerreplace = String.valueOf(((Timers)this.getOwner()).getServer().getMaxPlayers());
            String maxreplace = onlinereplace.replace("{maxplayer}", maxplayerreplace);
            String playerheal = String.valueOf(player1.getHealth());
            String playermaxheal = String.valueOf(player1.getMaxHealth());
            String healreplace = maxreplace.replace("{heal}", playerheal);
            String maxhealreplace = healreplace.replace("{maxheal}", playermaxheal);
            String lovereplace = maxhealreplace;
            if (this.getOwner().getServer().getPluginManager().getPlugins().containsKey("Mr_Love")){
                lovereplace = maxhealreplace.replace("{love}", Mr_Love.getPlugin().GetPlayerLoves(player1.getName()));
            }
            Config noseepopup = new Config(this.getOwner().getDataFolder()+"\\nopopupworld.yml",Config.YAML);
            List noseepopuplist = noseepopup.getStringList("No-see-popup-world");
            if (!noseepopuplist.contains(this.getOwner().getServer().getPlayer(player1.getName()).getLevel().getName())){
                player1.sendPopup(lovereplace);

            }
        }
    }
    public String onCurrentTime() {
        Date time = new Date();
        int hours = time.getHours();
        int minutes = time.getMinutes();
        int seconds = time.getSeconds();
        return hours  + "点"  + minutes  + "分" + seconds + "秒";
    }
}