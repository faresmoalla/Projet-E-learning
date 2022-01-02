<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\Cours;
use App\Entity\Panier;
use App\Entity\Reclamation;
use App\Form\RechCoursType;
use App\Repository\CommandeRepository;
use App\Repository\CoursRepository;
use App\Repository\PanierRepository;
use App\Repository\ReclamationRepository;
use App\Repository\UtilisateurRepository;
use Doctrine\ORM\EntityManagerInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;
use PDO;
use PDOException;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CommandeController extends AbstractController
{
    /**
     * @Route("/commande", name="commande")
     */
    public function index(): Response
    {
        return $this->render("commande/show.html.twig", [
            'controller_name' => 'CommandeController',
        ]);
    }

    /**
     * @Route("/ajouterCommande/{coursid}",name="ajoutCommande")
     */
    public function addCommande(
        Request                $request,
        UtilisateurRepository  $utilisateurRepo,
        PanierRepository       $panierRepo,
        CommandeRepository     $commandeRepo,
        CoursRepository        $coursRepo,
        ReclamationRepository  $reclamationRepo,
        EntityManagerInterface $entityManager): Response
    {
        $prixTotal = 0;
        $unseen = [];
        $connectedUser = $utilisateurRepo->findOneBy(["id" => "93"]);
        $cours = $coursRepo->findOneBy(["id" => $request->get("coursid")]);
        $panierOuvert = $panierRepo->findOneBy(["utilisateurid" => $connectedUser->getId(), "etatpanier" => "En cours"]);
        $commandes = $commandeRepo->findBy(["panierid" => $panierOuvert, "coursid" => (string)$cours->getId()]);
        if (empty($panierOuvert)) {
            $panier = new Panier();
            $panier->setUtilisateurid($connectedUser);
            $panier->setDatepanier(new \DateTime('now'));
            $panier->setEtatpanier("En cours");
            $entityManager->persist($panier);
            $entityManager->flush();
            $panierOuvert = $panierRepo->findOneBy(["utilisateurid" => "93", "etatpanier" => "En cours"]);
        }
        if (empty($commandes)) {
            $commande = new Commande();
            $commande->setPanierid($panierOuvert);
            $commande->setCoursid($cours);
            $entityManager->persist($commande);
            $entityManager->flush();
            $this->addFlash('info', 'Votre commande a été ajoutée avec succès.');
        } else {
            $this->addFlash('info', 'Ce cours a été déjà commandé.');
        }
        $reclamations = $reclamationRepo->findBy(["utilisateurid" => $connectedUser->getId(), "etatreclamation" => "Vérifiée"]);
        foreach ($reclamations as $reclamation) {
            if ($reclamation->getEtatreponse() == 1) {
                array_push($unseen, $reclamation->getEtatreponse());
            }
        }
        $commandes = $entityManager
            ->createQueryBuilder()
            ->select('c.nbrchapitres as prixCours')
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
        $cours = $this->getDoctrine()->getRepository(Cours::class)->listCoursByNsc();
        $form = $this->createForm(RechCoursType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $nomcours = $form->getData()->getNomcours();
            $coursResult = $this->getDoctrine()->getRepository(Cours::class)->getCoursByNom($nomcours);
            return $this->render('cours/show.html.twig', ['courss' => $coursResult, 'form2' => $form->createView()]);
        }
        return $this->render('cours/show.html.twig', [
            'courss' => $cours,
            'form2' => $form->createView(),
            'reclamations' => $reclamations,
            'unseen' => $unseen,
            'commandes' => $commandes,
            'prixTotal' => $prixTotal
        ]);
    }

    /**
     * @Route("/supprimerCommande/{commandeid}",name="suppressionCommande")
     */
    public
    function deleteCommande(Request $request, Commande $commande, CommandeRepository $commandeRepo, EntityManagerInterface $entityManager): Response
    {
        $commande = $commandeRepo->findOneBy(["commandeid" => $request->get("commandeid")]);
        $cde = $commandeRepo->find($commande);
        $entityManager->remove($cde);
        $entityManager->flush();
        $this->get('session')->getFlashBag()->add('success', 'Votre commande a été retirée avec succès.');
        return $this->redirectToRoute("panierOuverte");
    }


}
