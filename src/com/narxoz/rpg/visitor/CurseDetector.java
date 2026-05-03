package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;
import java.util.ArrayList;
import java.util.List;

public class CurseDetector implements ArtifactVisitor {
    private final List<String> cursedItems = new ArrayList<>();

    public List<String> getCursedItems() { return List.copyOf(cursedItems); }
    public int getCurseCount()           { return cursedItems.size(); }

    private boolean isSuspicious(Artifact a) {
        return a.getWeight() > 0 && a.getValue() < a.getWeight() * 2;
    }

    @Override
    public void visit(Weapon weapon) {
        if (isSuspicious(weapon)) {
            cursedItems.add(weapon.getName());
            System.out.printf("  [CurseDetector] WARNING %-22s CURSED — heavy for its value!%n",
                    weapon.getName());
        } else {
            System.out.printf("  [CurseDetector] OK      %-22s clean%n", weapon.getName());
        }
    }

    @Override
    public void visit(Potion potion) {
        if (potion.getHealing() < 0) {
            cursedItems.add(potion.getName());
            System.out.printf("  [CurseDetector] WARNING %-22s CURSED — negative healing!%n",
                    potion.getName());
        } else {
            System.out.printf("  [CurseDetector] OK      %-22s clean (heals %d)%n",
                    potion.getName(), potion.getHealing());
        }
    }

    @Override
    public void visit(Scroll scroll) {
        String spell = scroll.getSpellName().toLowerCase();
        if (spell.contains("death") || spell.contains("curse") || spell.contains("doom")) {
            cursedItems.add(scroll.getName());
            System.out.printf("  [CurseDetector] WARNING %-22s CURSED — dark inscription [%s]!%n",
                    scroll.getName(), scroll.getSpellName());
        } else {
            System.out.printf("  [CurseDetector] OK      %-22s clean (spell: %s)%n",
                    scroll.getName(), scroll.getSpellName());
        }
    }

    @Override
    public void visit(Ring ring) {
        if (ring.getMagicBonus() > 8) {
            cursedItems.add(ring.getName());
            System.out.printf("  [CurseDetector] WARNING %-22s CURSED — suspiciously powerful (+%d magic)!%n",
                    ring.getName(), ring.getMagicBonus());
        } else {
            System.out.printf("  [CurseDetector] OK      %-22s clean%n", ring.getName());
        }
    }

    @Override
    public void visit(Armor armor) {
        if (isSuspicious(armor)) {
            cursedItems.add(armor.getName());
            System.out.printf("  [CurseDetector] WARNING %-22s CURSED — wrong weight distribution!%n",
                    armor.getName());
        } else {
            System.out.printf("  [CurseDetector] OK      %-22s clean (def +%d)%n",
                    armor.getName(), armor.getDefenseBonus());
        }
    }
}