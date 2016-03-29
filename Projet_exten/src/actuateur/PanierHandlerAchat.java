package actuateur;
import donnees.IMagasin;
import donnees.IProduit;


public class PanierHandlerAchat implements IPanierHandler {

	// ajoute au panier un nombre de produits disponible dans le magasin
	public void ajouter(IProduit produit, IMagasin magasin, int quantite) {
		for(IProduit p: magasin.getProduits()){
			if(produit.getNom().equals(p.getNom())) {
				produit.setType(p.getType());
				//produit.setQuantites(quantite);
				magasin.getPanier().getContenu().add(produit);
			}
		}
	}

	// modifie le nombre de produits ajoute dans un panier
	public void modifier(IProduit produit, IMagasin magasin, int quantite) {
		for(IProduit p: magasin.getPanier().getContenu()){
			if(produit.getNom().equals(p.getNom())) {
				p.setQuantites(produit.getQuantites() + quantite);
			}
		}
	}
	
	// valide simule un achat, donc vidage du contenu du panier
	public boolean valider(IMagasin magasin) {
		int prix = 0;
		for(IProduit proPan: magasin.getPanier().getContenu()){
			for (IProduit proMag : magasin.getProduits()) {
				
				if(proMag.getNom().equals(proPan.getNom())) {
					if(proMag.getQuantites() >= proPan.getQuantites()) { // si assez de quantites en magasin
						prix += proPan.getPrix() * proPan.getQuantites();
						proMag.setQuantites(proMag.getQuantites() - proPan.getQuantites());
						magasin.getPanier().getContenu().remove(proPan);
					} else { // sinon manque de stock dans magasin
						int reste = Math.abs(proMag.getQuantites() - proPan.getQuantites());
						prix += proPan.getPrix() * (proPan.getQuantites() - reste);	
						proMag.setQuantites(0);
						modifier(proPan, magasin, reste);
					}
				}
			}
		}
	
		System.out.println("Vous allez devoir payer : " + prix + " €.");
		return true;
	}
	
}