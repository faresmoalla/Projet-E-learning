<?php

namespace App\Controller;

use App\Entity\Panier;
use App\Form\PaiementType;
use App\Repository\PanierRepository;
use App\Repository\UtilisateurRepository;
use Doctrine\ORM\EntityManagerInterface;
use PDO;
use PDOException;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PanierController extends AbstractController
{
    /**
     * @Route("/panier", name="panier")
     */
    public function index(PanierRepository $panierRepo): Response
    {
        return $this->render("panier/show.html.twig", [
            'paniers' => $panierRepo->findAll(),
        ]);
    }

    /**
     * @Route("/panierOuverte", name="panierOuverte")
     */
    public function panierOuverte(UtilisateurRepository $utilisateurRepo, EntityManagerInterface $entityManager): Response
    {
        $prixTotal = 0;
        $connectedUser = $utilisateurRepo->findOneBy(["id" => "93"]);
        $commandes = $entityManager
            ->createQueryBuilder()
            ->select('c.coursimg as coursImg')
            ->addSelect('c.nomcours as nomCours')
            ->addSelect('c.nbrchapitres as prixCours')
            ->addSelect('cde.commandeid as commandeID')
            ->from('App\Entity\Panier', 'p')
            ->innerJoin('App\Entity\Commande', 'cde', 'with', "p.panierid = cde.panierid")
            ->innerJoin('App\Entity\Cours', 'c', 'with', "cde.coursid = c.id")
            ->where('p.utilisateurid = :utilisateur')
            ->andWhere('p.etatpanier = :etatPanier')
            ->setParameter('utilisateur', $connectedUser)
            ->setParameter('etatPanier', 'En cours')
            ->getQuery()
            ->getResult();
        foreach ($commandes as $commande) {
            $prixTotal += $commande["prixCours"];
        }
        return $this->render("panier/index.html.twig", compact("commandes", "prixTotal"));
    }

    /**
     * @Route("/payerPanier", name="paiementPanier")
     */
    public function payerPanier(): Response
    {
        return $this->render('paiement/index.html.twig');
    }

    /**
     * @Route("/confirmerPaiement",name="confirmationPaiement")
     */
    public function confirmerPaiement(UtilisateurRepository $utilisateurRepo, PanierRepository $panierRepo, EntityManagerInterface $entityManager): RedirectResponse
    {
        $connectedUser = $utilisateurRepo->findOneBy(["id" => "93"]);
        $panier = $panierRepo->findOneBy(["utilisateurid" => $connectedUser->getId(), "etatpanier" => "En cours"]);
        $panier->setEtatpanier("Payé");
        $entityManager->merge($panier);
        $entityManager->flush();
        $this->get('session')->getFlashBag()->add('success', 'Votre panier a été payée avec succès.');
        return $this->redirectToRoute("rechercher");
    }


}
