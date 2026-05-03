package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

public class EnchantmentScanner implements ArtifactVisitor {
    private int enchantedCount = 0;

    public int getEnchantedCount() { return enchantedCount; }

    @Override
    public void visit(Weapon weapon) {
        if (weapon.getAttackBonus() > 10) {
            enchantedCount++;
            System.out.printf("  [EnchantmentScanner] %-24s STRONG aura: +%d attack%n",
                    weapon.getName(), weapon.getAttackBonus());
        } else if (weapon.getAttackBonus() > 0) {
            enchantedCount++;
            System.out.printf("  [EnchantmentScanner] %-24s mild aura:  +%d attack%n",
                    weapon.getName(), weapon.getAttackBonus());
        } else {
            System.out.printf("  [EnchantmentScanner] %-24s no enchantment%n", weapon.getName());
        }
    }

    @Override
    public void visit(Potion potion) {
        enchantedCount++;
        System.out.printf("  [EnchantmentScanner] %-24s alchemical aura: +%d HP%n",
                potion.getName(), potion.getHealing());
    }

    @Override
    public void visit(Scroll scroll) {
        enchantedCount++;
        System.out.printf("  [EnchantmentScanner] %-24s arcane inscription: [%s]%n",
                scroll.getName(), scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        enchantedCount++;
        String strength = ring.getMagicBonus() > 5 ? "POTENT" : "faint";
        System.out.printf("  [EnchantmentScanner] %-24s %s resonance: +%d magic%n",
                ring.getName(), strength, ring.getMagicBonus());
    }

    @Override
    public void visit(Armor armor) {
        if (armor.getDefenseBonus() > 8) {
            enchantedCount++;
            System.out.printf("  [EnchantmentScanner] %-24s RUNIC ward: +%d defense%n",
                    armor.getName(), armor.getDefenseBonus());
        } else {
            System.out.printf("  [EnchantmentScanner] %-24s no enchantment (+%d def)%n",
                    armor.getName(), armor.getDefenseBonus());
        }
    }
}