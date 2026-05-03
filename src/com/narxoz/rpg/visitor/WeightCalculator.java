package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

/**
 * 4th visitor — added WITHOUT touching any file under artifact/.
 * Proves the artifact hierarchy is closed to modification
 * while the visitor layer is open for extension.
 */
public class WeightCalculator implements ArtifactVisitor {
    private int totalWeight = 0;

    public int getTotalWeight() { return totalWeight; }

    @Override
    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
        System.out.printf("  [WeightCalculator] %-24s %d kg (weapon)%n",
                weapon.getName(), weapon.getWeight());
    }

    @Override
    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
        System.out.printf("  [WeightCalculator] %-24s %d kg (potion — fragile)%n",
                potion.getName(), potion.getWeight());
    }

    @Override
    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
        System.out.printf("  [WeightCalculator] %-24s %d kg (scroll)%n",
                scroll.getName(), scroll.getWeight());
    }

    @Override
    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
        System.out.printf("  [WeightCalculator] %-24s %d kg (ring)%n",
                ring.getName(), ring.getWeight());
    }

    @Override
    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
        System.out.printf("  [WeightCalculator] %-24s %d kg (armor — heavy)%n",
                armor.getName(), armor.getWeight());
    }
}