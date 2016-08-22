package ru.gopromo.testappgp.data_model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class Rss {

    @Element(name = "channel")
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

}
