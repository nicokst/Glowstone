package net.glowstone.block.block2.details;

/**
 * Possible shapes for rails.
 */
public enum RailShape {
    NORTH_SOUTH,
    EAST_WEST,
    ASCENDING_EAST,
    ASCENDING_WEST,
    ASCENDING_NORTH,
    ASCENDING_SOUTH,
    SOUTH_EAST,
    SOUTH_WEST,
    NORTH_WEST,
    NORTH_EAST;

    public static final RailShape[] NO_CORNERS = {
            NORTH_SOUTH,
            EAST_WEST,
            ASCENDING_EAST,
            ASCENDING_NORTH,
            ASCENDING_SOUTH,
            ASCENDING_WEST
    };
}