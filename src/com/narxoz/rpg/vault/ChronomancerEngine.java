package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.*;
import java.util.List;

public class ChronomancerEngine {

    public VaultRunResult runVault(List<Hero> party) {

        Inventory vault = buildVaultInventory();

        // ── Visitor 1: GoldAppraiser ─────────────────────────────────────────
        System.out.println("\n=== APPRAISAL: Gold Appraiser ===");
        GoldAppraiser gold = new GoldAppraiser();
        vault.accept(gold);
        System.out.println("  Total vault value: " + gold.getTotalGold() + " gold\n");

        // ── Visitor 2: EnchantmentScanner ────────────────────────────────────
        System.out.println("=== APPRAISAL: Enchantment Scanner ===");
        EnchantmentScanner scanner = new EnchantmentScanner();
        vault.accept(scanner);
        System.out.println("  Enchanted artifacts: " + scanner.getEnchantedCount() + "\n");

        // ── Visitor 3: CurseDetector ─────────────────────────────────────────
        System.out.println("=== APPRAISAL: Curse Detector ===");
        CurseDetector curses = new CurseDetector();
        vault.accept(curses);
        System.out.println("  Cursed items flagged: " + curses.getCurseCount());
        if (!curses.getCursedItems().isEmpty())
            System.out.println("  Flagged: " + curses.getCursedItems());
        System.out.println();

        // ── MEMENTO: save snapshot for every hero ────────────────────────────
        System.out.println("=== MEMENTO: Saving snapshots ===");
        Caretaker[] caretakers = new Caretaker[party.size()];
        for (int i = 0; i < party.size(); i++) {
            Hero hero = party.get(i);
            caretakers[i] = new Caretaker();
            HeroMemento snap = hero.createMemento();
            caretakers[i].save(snap);
            System.out.printf("  Snapshot saved for %s -> %s [stack size: %d]%n",
                    hero.getName(), hero, caretakers[i].size());
        }
        System.out.println();

        // ── VAULT TRAP ───────────────────────────────────────────────────────
        System.out.println("=== VAULT EVENT: Chrono-Trap! ===");
        for (Hero hero : party) {
            hero.takeDamage(40);
            hero.spendGold(Math.min(30, hero.getGold()));
            System.out.printf("  %s hit by trap -> %s%n", hero.getName(), hero);
        }
        System.out.println();

        // ── MEMENTO: rewind ──────────────────────────────────────────────────
        System.out.println("=== MEMENTO: Chronomancer Rewind! ===");
        int restored = 0;
        for (int i = 0; i < party.size(); i++) {
            Hero hero = party.get(i);
            HeroMemento saved = caretakers[i].undo();
            if (saved != null) {
                hero.restoreFromMemento(saved);
                restored++;
                System.out.printf("  %s rewound -> %s%n", hero.getName(), hero);
            }
        }
        System.out.println();

        // ── Visitor 4: WeightCalculator (OCP proof) ──────────────────────────
        System.out.println("=== APPRAISAL: Weight Calculator (4th visitor, OCP proof) ===");
        System.out.println("  No artifact class was modified to add this visitor.");
        WeightCalculator weight = new WeightCalculator();
        vault.accept(weight);
        System.out.println("  Total encumbrance: " + weight.getTotalWeight() + " kg\n");

        return new VaultRunResult(vault.size(), party.size(), restored);
    }

    private Inventory buildVaultInventory() {
        Inventory inv = new Inventory();
        inv.addArtifact(new Weapon("Shadowfang Blade",    120, 8,  15));
        inv.addArtifact(new Potion("Elixir of Clarity",   35,  1,  50));
        inv.addArtifact(new Scroll("Scroll of Doom Fire", 80,  1,  "DoomFire"));
        inv.addArtifact(new Ring  ("Ring of the Lich King",200, 0, 12));
        inv.addArtifact(new Armor ("Plate of the Fallen", 150, 20, 10));
        inv.addArtifact(new Weapon("Rusty Shortsword",    10,  15, 2));
        inv.addArtifact(new Potion("Venom Tonic",         5,   1,  -5));
        return inv;
    }
}