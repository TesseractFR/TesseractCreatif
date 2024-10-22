package onl.tesseract.entity;

import lombok.Getter;

public enum PlotWorld {
    WORLD_100("100"),
    WORLD_250("250"),
    WORLD_500("500"),
    WORLD_1000("1000"),
    ;
    @Getter
    private final String world;

    PlotWorld(String world) {
        this.world = world;
    }

}
