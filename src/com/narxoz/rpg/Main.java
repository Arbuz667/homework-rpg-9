package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===\n");

        // Hero 1 — heavy warrior, lots of gold
        Inventory aldricInv = new Inventory();
        aldricInv.addArtifact(new Weapon("Iron Longsword", 50, 5, 8));
        Hero aldric = new Hero("Aldric", 150, 20, 25, 12, 80, aldricInv);

        // Hero 2 — fragile mage, high mana
        Inventory lyraInv = new Inventory();
        lyraInv.addArtifact(new Potion("Minor Mana Flask", 20, 1, 30));
        Hero lyra = new Hero("Lyra", 70, 120, 8, 5, 40, lyraInv);

        System.out.println("Party enters the vault:");
        System.out.println("  " + aldric);
        System.out.println("  " + lyra + "\n");

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(aldric, lyra));

        System.out.println("=== VAULT RUN COMPLETE ===");
        System.out.println("  " + result);
        System.out.println("\nFinal party state after rewind:");
        System.out.println("  " + aldric);
        System.out.println("  " + lyra);
    }
}