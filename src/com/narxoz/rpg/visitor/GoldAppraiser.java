package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

public class GoldAppraiser implements ArtifactVisitor {
    private int totalGold = 0;

    public int getTotalGold() { return totalGold; }

    @Override
    public void visit(Weapon weapon) {
        int v = weapon.getValue() + weapon.getAttackBonus() * 5;
        totalGold += v;
        System.out.printf("  [GoldAppraiser] %-24s -> %d gold (base %d + atk %dx5)%n",
                weapon.getName(), v, weapon.getValue(), weapon.getAttackBonus());
    }

    @Override
    public void visit(Potion potion) {
        totalGold += potion.getValue();
        System.out.printf("  [GoldAppraiser] %-24s -> %d gold (standard price)%n",
                potion.getName(), potion.getValue());
    }

    @Override
    public void visit(Scroll scroll) {
        totalGold += scroll.getValue();
        System.out.printf("  [GoldAppraiser] %-24s -> %d gold (spell: %s)%n",
                scroll.getName(), scroll.getValue(), scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        int v = ring.getValue() + ring.getMagicBonus() * 10;
        totalGold += v;
        System.out.printf("  [GoldAppraiser] %-24s -> %d gold (base %d + magic %dx10)%n",
                ring.getName(), v, ring.getValue(), ring.getMagicBonus());
    }

    @Override
    public void visit(Armor armor) {
        int v = armor.getValue() + armor.getDefenseBonus() * 4;
        totalGold += v;
        System.out.printf("  [GoldAppraiser] %-24s -> %d gold (base %d + def %dx4)%n",
                armor.getName(), v, armor.getValue(), armor.getDefenseBonus());
    }
}