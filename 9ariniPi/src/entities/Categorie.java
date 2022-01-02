/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fares
 */
public class Categorie {
  private int id;
private String categorieNom;  
private String categorieImage;
        List<Cours> css;

    public Categorie() {
    }

    public Categorie(int id, String categorieNom,String categorieImage) {
        this.id = id;
        this.categorieNom = categorieNom;
        this.categorieImage=categorieImage;
    }

    public Categorie(String categorieNom,String categorieImage) {
        this.categorieNom = categorieNom;
                this.categorieImage=categorieImage;

            css = new ArrayList<Cours>();

    }

    public Categorie(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    public String getCategorieImage() {
        return categorieImage;
    }

    public void setCategorieImage(String categorieImage) {
        this.categorieImage = categorieImage;
    }

    public Categorie(int id) {
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }






}
