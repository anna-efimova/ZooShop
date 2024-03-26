package com.zoo_shop.model;

public enum ProductCategory {
    DRY("Сухой"),
    DOG_FOOD("Корм для собак"),
    ALL_BREEDS("Все породы"),
    NATURAL("Натуральный"),
    MEDIUM_BREEDS("Средние породы"),
    SUPER_PREMIUM("Супер-премиум"),
    THERAPEUTIC("Лечебный"),
    HYPOALLERGENIC("Гипоаллергенный"),
    ORGANIC("Органический"),
    ADULT("Взрослые"),
    KITTEN_FOOD("Корм для котят"),
    PREMIUM("Премиум"),
    FOR_ADULT_CATS("Для взрослых кошек"),
    VETERINARY("Ветеринарный"),
    GRAIN_FREE("Беззерновой"),
    FOR_CATS("Для кошек"),
    FOR_ACTIVE_CATS("Для высокоактивных кошек"),
    RABBIT_FOOD("Корм для кроликов"),
    TREATS("Лакомства"),
    FOR_HAMSTERS("Для хомяков"),
    FOR_GUINEA_PIGS("Корм для морских свинок"),
    NUTRITIOUS("Питательный"),
    FOR_CHINCHILLAS("Для шиншилл"),
    ORTHOPEDIC("Ортопедическая"),
    FOR_PUPPIES("Для щенков"),
    CAT_BED("Кошачья лежанка"),
    FOR_RELAXATION("Для расслабления"),
    WITH_VIBRATION("С вибрацией"),
    MASSAGE_BED("Лежанка с массажем"),
    FOR_ALL_SIZES("Для собак всех размеров"),
    HEATED("Подогреваемая"),
    WARM("Теплая"),
    AQUARIUM("Аквариум"),
    FOR_FISH("Для рыб"),
    LARGE("Большой"),
    CAGE("Клетка"),
    FOR_HAMSTERS_2("Для хомяков"),
    MULTI_LEVEL("Многоуровневая"),
    SPACIOUS("Просторная");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
