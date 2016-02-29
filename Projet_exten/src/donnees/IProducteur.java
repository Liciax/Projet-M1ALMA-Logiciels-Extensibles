package donnees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;


public class IProducteur {

	private ArrayList<String> donnees;
	
	public IProducteur() {
		super();
		donnees = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/donnees/donnees.txt"));
			String ligne;
			while((ligne = br.readLine())!=null) {
				donnees.add(ligne);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<Produit> getProduit(){
		Class<?> produit = null;
		Produit concret;
		Vector<Produit> oProduit = new Vector<Produit>();
		for (String s : donnees) {
			if(s.contains("class=donnees.Produit")) {
				try {
					produit = Class.forName(s.split("; ")[0].split("=")[1]);
					concret = (Produit) produit.newInstance();
					concret.setNom(s.split("; ")[1].split("=")[1]);
                    concret.setPrix(Float.parseFloat(s.split("; ")[3].split("=")[1]));
                    concret.setType(s.split("; ")[2].split("=")[1]);
                    oProduit.add(concret);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return oProduit;
	}
	
	public Magasin getMagasin() {
		Class<?> magasin = null;
		Magasin oMagasin = null;
		Vector<Produit> stock = getProduit();
		for (String s : donnees) {
			if(s.contains("donnees.Magasin")) {
				try {
					magasin = Class.forName(s.split("; ")[0].split("=")[1]);
					oMagasin = (Magasin) magasin.newInstance();
					oMagasin.setNomMag(s.split("; ")[1].split("=")[1]);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		oMagasin.setProduits(stock);
		return (Magasin)oMagasin;
	}
}