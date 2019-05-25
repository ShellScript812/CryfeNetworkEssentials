package de.cryfe.api.utilities;

import de.cryfe.api.Main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public enum ConsoleMessagePrinter {

    INFORMATION,
    FATAL_ERROR,
    IMPORTANT;

    public static ConsoleMessagePrinter printMessage(ConsoleMessagePrinter consoleMessageType, String message) {

        if (consoleMessageType == null || message == null)
            throw new NullPointerException("Object is null!");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:MM");
           simpleDateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        String currentTime = simpleDateFormat.format(new Date());

        if (consoleMessageType.equals(ConsoleMessagePrinter.INFORMATION))
            Main.getInstance().getLogger().info("[Information/" + currentTime + "]: " + message);

        if (consoleMessageType.equals(ConsoleMessagePrinter.IMPORTANT))
            Main.getInstance().getLogger().info("[Important/" + currentTime + "]: " + message);

        if (consoleMessageType.equals(ConsoleMessagePrinter.FATAL_ERROR))
            Main.getInstance().getLogger().info("[Error/" + currentTime + "]: " + message);

        return null;

    }

}
