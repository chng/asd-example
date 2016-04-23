package me.db.manager;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import org.springframework.stereotype.Component;

/**
 * Created by chn on 15/12/15.
 */
@Component
public class EbeanServerManager {

    private static EbeanServer server = Ebean.getServer(null);
}
